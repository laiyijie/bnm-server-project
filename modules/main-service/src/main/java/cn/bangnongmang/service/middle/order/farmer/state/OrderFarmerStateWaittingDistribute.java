package cn.bangnongmang.service.middle.order.farmer.state;

import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderDriverWorkSize;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.PocketLog;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
public class OrderFarmerStateWaittingDistribute extends AbstractOrderFarmerState implements OrderFarmerStateName {

    @Autowired
    private OrderFarmerStateTodayWorkFinished nextState;
    @Autowired
    private OrderFarmerService orderFarmerService;
    @Autowired
    private WorkSizeService workSizeService;

    @Autowired
    private OrderDriverService orderDriverService;
    @Autowired
    private TradeFlowService tradeFlowService;

    private static Double MAX_MONEY_OUT_RATE = 10000.0;

    @Autowired
    public OrderFarmerStateWaittingDistribute(OrderFarmerStateCollection stateCollection) {
        super(stateCollection);
    }

    @Override
    public boolean distributeSize(OrderFarmerStateManager orderFarmerStateManager, Map<Long, Double>
            memberWorkSize)
            throws OrderStateException {

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());

        if (orderFarmer == null) {
            throw new OrderStateException("订单不存在");
        }
        OrderFarmerWorkSize orderFarmerWorkSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(
                orderFarmerStateManager.getId(), WorkSizeService.WORK_SIZE_STATE_ENSURED);
        if (orderFarmerWorkSize == null) {
            throw new OrderStateException("没有确认过的亩数");
        }

        Double total = 0.0;

        for (Map.Entry<Long, Double> entry : memberWorkSize.entrySet()) {
            total += entry.getValue();
        }

        if ( Math.abs(total - orderFarmerWorkSize.getSize())>0.00001) {
            throw new OrderStateException("分配的亩数与确认的亩数不符");
        }

        List<OrderDriver> orderDrivers = orderDriverService
                .getOrderDriverListByOrderFarmerId(orderFarmerStateManager.getId());

        Long lid = System.currentTimeMillis() * 100 + new Random().nextInt(100);
        for (int i = 0; i < orderDrivers.size(); i++) {
            OrderDriver orderDriver = orderDrivers.get(i);
            OrderDriverWorkSize orderDriverWorkSize = new OrderDriverWorkSize();
            orderDriverWorkSize.setId(lid + i);
            Double currSize = memberWorkSize.getOrDefault(orderDriver.getUid(), 0.0);

            orderDriverWorkSize.setSize(currSize);
            orderDriverWorkSize.setOrder_farmer_work_size_id(orderFarmerWorkSize.getId());
            orderDriverWorkSize.setUid(orderDriver.getUid());
            OrderDriverWorkSize driverWorkSize = workSizeService.createOrderDriverWorkSize(orderDriverWorkSize);
            if (driverWorkSize == null) {
                throw new OrderStateException("创建失败");
            }
            Integer money = Math.toIntExact(Math.round(currSize * orderFarmer.getUni_price()));
            try {
                PocketLog pocketLog = tradeFlowService.createTradeFlow(orderDriver.getUid(),
                        TradeFlowService.POCKETLOG_TYPE_INCOME, money, TradeFlowService.POCKETLOG_METHOD_DEFAULT,
                        "作业收入", "", orderFarmer.getOrder_id());

                if (pocketLog == null) {
                    throw new OrderStateException("创建流水失败");
                }
                boolean flag = tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
                if (!flag) {
                    throw new OrderStateException("流水完成错误");
                }

                if (orderFarmer.getDay_money_out_rate() != null && orderFarmer.getDay_money_out_rate() != 0 && orderFarmer.getDay_money_out_rate()
                        <= MAX_MONEY_OUT_RATE) {
                    Integer outMoney = Math.toIntExact(Math.round(money * (orderFarmer.getDay_money_out_rate() / MAX_MONEY_OUT_RATE)));
                    PocketLog outMoneyPocketLog = tradeFlowService.createTradeFlow(orderDriver.getUid(),
                            TradeFlowService.POCKETLOG_TYPE_INCOME_UN_FREEZE, outMoney, TradeFlowService.POCKETLOG_METHOD_DEFAULT,
                            "作业收入解冻", "", orderFarmer.getOrder_id());
                    if (outMoneyPocketLog == null) {
                        throw new OrderStateException("创建流水失败");
                    }
                    flag = tradeFlowService.doneTradeFlow(outMoneyPocketLog.getPocket_log_id());
                    if (!flag) {
                        throw new OrderStateException("流水完成错误");
                    }
                }

            } catch (ServiceLayerExeption e) {
                throw new OrderStateException(e.getErrorCode(), e.getErrorMessage());
            }
        }
        orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());

        if (!workSizeService.changeOrderFarmerWorkSizeStateTo(orderFarmerWorkSize.getId(),
                WorkSizeService.WORK_SIZE_STATE_FINISH)) {
            throw new OrderStateException("状态变更失败");
        }
        orderFarmerStateManager.setCurrentState(nextState);

        return true;
    }

    @Override
    public String getStateValue() {
        return FARMER_WAITTING_DISTRIBUTE;
    }

}
