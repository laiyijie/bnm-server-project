package cn.bangnongmang.service.middle.order.farmer.state;

import java.util.List;

import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.middle.pocket.OrderInsuranceService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderFarmerStateTodayWorkFinished extends AbstractOrderFarmerState implements OrderFarmerStateName {

    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private OrderInsuranceService orderInsuranceService;

    @Autowired
    private OrderFarmerStateAllFinished finishedState;

    @Autowired
    private OrderFarmerStateWorking workingState;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private TradeFlowService tradeFlowService;

    @Autowired
    private WorkSizeService workSizeService;

    @Autowired
    private UserWorkCalendarService userWorkCalendarService;

    public OrderFarmerStateTodayWorkFinished(OrderFarmerStateCollection stateCollection) {
        super(stateCollection);
    }

    @Override
    public boolean finishOrder(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(
                orderFarmerStateManager.getId());
        if (orderFarmer == null) {
            throw new OrderStateException("订单不存在");
        }
        // 完成订单后需要进行资金解冻
        List<OrderDriver> orderDrivers = orderDriverService
                .getOrderDriverListByOrderFarmerId(orderFarmerStateManager.getId());

        for (OrderDriver orderDriver : orderDrivers) {
            try {
                unFreezeMoneyAndRefundInsurance(orderDriver);
            } catch (ServiceLayerExeption e) {
                throw new OrderStateException(e.getErrorCode(), e.getErrorMessage());
            }

            updateEfficiency(orderDriver, orderFarmer);
            orderDriverService.updateState(orderDriver.getOrder_id(),
                    OrderDriverService.ORDER_DRIVER_STATE_ALL_FINISHED);
        }

        userWorkCalendarService.deleteCalendarByOrderId(orderFarmerStateManager.getId());
        orderFarmerService.changeStateTo(orderFarmerStateManager.getId(),
                finishedState.getStateValue());

        orderFarmerStateManager.setCurrentState(finishedState);

        return true;
    }

    @Override
    public boolean continueNextDayWork(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(
                orderFarmerStateManager.getId());
        if (orderFarmer == null) {
            throw new OrderStateException("订单不存在");
        }

        orderFarmerService.changeStateTo(orderFarmerStateManager.getId(),
                workingState.getStateValue());

        orderFarmerStateManager.setCurrentState(workingState);
        return true;

    }

    @Override
    public String getStateValue() {
        return FARMER_TODAY_WORK_FINISHED;
    }

    private void unFreezeMoneyAndRefundInsurance(OrderDriver orderDriver) throws ServiceLayerExeption {
        int totalMoney = tradeFlowService.countMyUnfreezeOrderWaitInMoneyByOrderId(orderDriver.getUid(), orderDriver.getOrder_farmer_id());
        PocketLog pocketLog = tradeFlowService.createTradeFlow(orderDriver.getUid(),
                TradeFlowService.POCKETLOG_TYPE_INCOME_UN_FREEZE, totalMoney,
                TradeFlowService.POCKETLOG_METHOD_DEFAULT,
                "订单结束，钱入账", "", orderDriver.getOrder_farmer_id());
        tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());

        orderInsuranceService.refundInsurance(orderDriver.getUid(),
                orderDriver.getOrder_farmer_id());
    }

    private void updateEfficiency(OrderDriver orderDriver, OrderFarmer orderFarmer) {

        List<OrderDriverWorkSize> orderDriverWorkSizes = workSizeService
                .getOrderDriverWorkSizeListByOrderFarmerIdAndUsername(
                        orderDriver.getOrder_farmer_id(),
                        orderDriver.getUid());

        Double totalSize = orderDriverWorkSizes.stream()
                                               .map(a -> a.getSize())
                                               .reduce(0.0,
                                                       (x, y) -> x + y);
        workSizeService.insertOnConflictUpdateWorkEfficiency(orderDriver.getUid(),
                orderFarmer.getWorking_type_id(), totalSize, orderDriverWorkSizes.size());
    }


}
