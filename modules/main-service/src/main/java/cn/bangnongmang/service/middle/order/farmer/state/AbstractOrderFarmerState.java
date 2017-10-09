package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.exception.OrderStateTransitionNotExsistException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerState;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;

import java.util.List;
import java.util.Map;


public abstract class AbstractOrderFarmerState implements OrderFarmerState {

    public AbstractOrderFarmerState(OrderFarmerStateCollection stateCollection) {
        stateCollection.registerState(this.getStateValue(), this);
    }

    @Override
    public boolean authPass(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean prePay(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean chooseField(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean grabOrder(OrderFarmerStateManager orderFarmerStateManager, Long teamInOrderId)
            throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean readyToWork(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean finishTodayWork(OrderFarmerStateManager orderFarmerStateManager)
            throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }


    @Override
    public boolean uploadWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager,
                                           String imagePath,String title) throws
            OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    /**
     * 根据ID删除本日作业的凭据
     *
     * @param orderFarmerStateManager
     * @param IDs
     * @return
     * @throws OrderStateException
     */
    @Override
    public boolean deleteWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, List<Long> IDs) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }


    /**
     * 更新今日作业亩数
     *
     * @return
     * @Title finishWork
     */

    @Override
    public boolean updateTodayTotalSize(OrderFarmerStateManager orderFarmerStateManager, Double totalSize) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean ensureTodaySize(OrderFarmerStateManager orderFarmerStateManager, Double todaySize)
            throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean distributeSize(OrderFarmerStateManager orderFarmerStateManager, Map<Long, Double>
            memberWorkSize)
            throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean continueNextDayWork(OrderFarmerStateManager orderFarmerStateManagers) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean finishOrder(OrderFarmerStateManager orderFarmerStateManagers) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

    @Override
    public boolean cancel(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
        throw new OrderStateTransitionNotExsistException();
    }

}
