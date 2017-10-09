package cn.bangnongmang.service.middle.order.farmer;

import cn.bangnongmang.service.middle.order.OrderStateManager;
import cn.bangnongmang.service.middle.order.StateCollection;
import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.exception.OrderStateInstanceNotFindException;

import java.util.List;
import java.util.Map;



public class OrderFarmerStateManager implements OrderStateManager<OrderFarmerState> {

    private Map<String, OrderFarmerState> allStates;

    private String id;

    private OrderFarmerState currentState;

    public OrderFarmerStateManager(StateCollection<String, OrderFarmerState> stateCollection, String orderState,
                                   String id) throws OrderStateInstanceNotFindException {

        allStates = stateCollection.getStateMap();

        if (!allStates.containsKey(orderState)) {

            throw new OrderStateInstanceNotFindException(String.valueOf(orderState));
        }

        this.currentState = allStates.get(orderState);
        this.id = id;
    }

    @Override
    public void setCurrentState(OrderFarmerState state) {
        this.currentState = state;
    }

    public String getId() {
        return id;
    }

    public boolean authPass() throws OrderStateException {
        return currentState.authPass(this);
    }

    public boolean prePay() throws OrderStateException {
        return currentState.prePay(this);
    }

    public boolean chooseField() throws OrderStateException {
        return currentState.chooseField(this);
    }

    public boolean grabOrder(Long teamInOrderId) throws OrderStateException {
        return currentState.grabOrder(this, teamInOrderId);
    }

    public boolean readyToWork() throws OrderStateException {
        return currentState.readyToWork(this);
    }

    public boolean finishTodayWork() throws OrderStateException {
        return currentState.finishTodayWork(this);
    }


    public boolean uploadWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager,
                                           String imagePath,String title) throws
            OrderStateException {
        return currentState.uploadWorkSizeAuthImage(this, imagePath,title);
    }


    public boolean deleteWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, List<Long> IDs) throws OrderStateException {
        return currentState.deleteWorkSizeAuthImage(this, IDs);
    }

    public boolean updateTodayTotalSize(OrderFarmerStateManager orderFarmerStateManager, Double totalSize) throws OrderStateException {
        return currentState.updateTodayTotalSize(this, totalSize);
    }

    public boolean ensureTodaySize(Double todaySize) throws OrderStateException {
        return currentState.ensureTodaySize(this, todaySize);
    }

    public boolean distributeSize(Map<Long, Double> memberWorksize) throws OrderStateException {
        return currentState.distributeSize(this, memberWorksize);
    }

    public boolean continueNextDayWork() throws OrderStateException {
        return currentState.continueNextDayWork(this);
    }

    public boolean finishOrder() throws OrderStateException {
        return currentState.finishOrder(this);
    }

    public boolean cancel() throws OrderStateException {
        return currentState.cancel(this);
    }
}
