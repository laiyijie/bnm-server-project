package cn.bangnongmang.service.middle.order.farmer;


import cn.bangnongmang.service.middle.order.StateCollection;
import cn.bangnongmang.service.middle.order.exception.OrderStateInstanceNotFindException;

public class OrderFarmerStateManagerFactory {

	private StateCollection<String, OrderFarmerState> collection;

	public OrderFarmerStateManagerFactory(StateCollection<String, OrderFarmerState> collection) {
		this.collection = collection;
	}

	public OrderFarmerStateManager createManager(String orderState, String id)
			throws OrderStateInstanceNotFindException {
		return new OrderFarmerStateManager(this.collection, orderState, id);
	}
}
