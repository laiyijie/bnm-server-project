package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.service.service.WorkSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2017-04-27.
 */
@Service
@Transactional
public class OrderFarmerStateToDayWorkingStop extends AbstractOrderFarmerState implements OrderFarmerStateName {

    @Autowired
    private OrderFarmerStateWattingSizeEnsure nextState;

    @Autowired
    private WorkSizeService workSizeService;
    @Autowired
    private OrderFarmerService orderFarmerService;

    @Override
    public boolean uploadWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, String imagePath, String title) throws
            OrderStateException {
        OrderFarmerWorkSize orderFarmerWorkSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(orderFarmerStateManager.getId(),
                WorkSizeService.WORK_SIZE_STATE_DENIED);
        if (orderFarmerWorkSize == null)
            orderFarmerWorkSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(orderFarmerStateManager.getId(), WorkSizeService
                    .WORK_SIZE_STATE_WAITTING_ENSURE);
        return orderFarmerWorkSize != null && workSizeService.addWorkSizeAuthImage(orderFarmerWorkSize.getId(), imagePath, title);

    }

    @Override
    public boolean deleteWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, List<Long> IDs) throws OrderStateException {
        IDs.forEach(id -> workSizeService.deleteWorkSizeAuthImage(id));
        return true;
    }

    @Override
    public boolean updateTodayTotalSize(OrderFarmerStateManager orderFarmerStateManager, Double totalSize) throws OrderStateException {
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());
        if (orderFarmer == null) {
            throw new OrderStateException("订单不存在");
        }

        OrderFarmerWorkSize orderFarmerWorkSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(
                orderFarmerStateManager.getId(), WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE);
        if (orderFarmerWorkSize == null) {
            throw new OrderStateException("作业亩数已被确认");
        }
        workSizeService.updateOrderFarmerWorkSize(orderFarmerWorkSize.getId(), totalSize);
        orderFarmerStateManager.setCurrentState(nextState);
        orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());
        return true;
    }

    @Autowired
    public OrderFarmerStateToDayWorkingStop(OrderFarmerStateCollection stateCollection) {
        super(stateCollection);
    }

    @Override
    public String getStateValue() {
        return FARMER_TODAY_WORKING_STOP;
    }


}
