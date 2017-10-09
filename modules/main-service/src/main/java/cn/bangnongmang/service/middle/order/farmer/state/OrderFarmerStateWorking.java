package cn.bangnongmang.service.middle.order.farmer.state;

import java.util.List;
import java.util.Random;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.WorkSizeService;
import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@Service
public class OrderFarmerStateWorking extends AbstractOrderFarmerState implements OrderFarmerStateName {

    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private OrderFarmerStateToDayWorkingStop nextState;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private WorkSizeService workSizeService;

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    public OrderFarmerStateWorking(OrderFarmerStateCollection stateCollection) {
        super(stateCollection);
    }

    @Override
    public boolean finishTodayWork(OrderFarmerStateManager orderFarmerStateManager)
            throws OrderStateException {

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());
        if (orderFarmer == null) {
            throw new OrderStateException("订单不存在");
        }
        List<OrderDriver> orderDrivers = orderDriverService
                .getOrderDriverListByOrderFarmerId(orderFarmerStateManager.getId());
        for (OrderDriver orderDriver : orderDrivers) {
            if (OrderDriverService.ORDER_DRIVER_STATE_WORKING.equals(orderDriver.getState())) {

                throw new OrderStateException("还有人正在作业");
            }
        }
        OrderFarmerWorkSize orderFarmerWorkSize = new OrderFarmerWorkSize();
        orderFarmerWorkSize.setId(System.currentTimeMillis() * 100 + new Random().nextInt(100));
        orderFarmerWorkSize.setOrder_farmer_id(orderFarmer.getOrder_id());
        orderFarmerWorkSize.setSize(0.0);
        orderFarmerWorkSize.setState(WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE);
        orderFarmerWorkSize.setTime(System.currentTimeMillis());

        Boolean workSize = workSizeService.createOrderFarmerWorkSize(orderFarmerWorkSize);

        if (!workSize.equals(true)) {
            throw new OrderStateException("记录当日完成亩数失败，请重试");
        }

        orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());
        orderFarmerStateManager.setCurrentState(nextState);

        return true;
    }

    @Override
    public String getStateValue() {
        return FARMER_WORKING;
    }

}
