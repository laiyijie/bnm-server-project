package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.domain.OrderFarmer;

import cn.bangnongmang.service.service.OrderFarmerService;

@Service
public class OrderFarmerStateGot extends AbstractOrderFarmerState implements OrderFarmerStateName {

	@Autowired
	private OrderFarmerService orderFarmerService;
	
	@Autowired
	private OrderFarmerStateWorking nextState;

	@Autowired
	public OrderFarmerStateGot(OrderFarmerStateCollection stateCollection) {
		super(stateCollection);

	}

	@Override
	public boolean readyToWork(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {
		OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());
		if (orderFarmer == null) {
			throw new OrderStateException("订单不存在");
		}
		orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());
		orderFarmerStateManager.setCurrentState(nextState);
		return true;
	}

	@Override
	public String getStateValue() {
		return FARMER_GOT;
	}

}
