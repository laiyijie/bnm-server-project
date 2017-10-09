package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.service.service.OrderFarmerService;

@Service
public class OrderFarmerStateWattingChooseField extends AbstractOrderFarmerState implements OrderFarmerStateName {

	@Autowired
	private OrderFarmerService orderFarmerService;

	@Autowired
	private OrderFarmerStateWaittingGot nextState;

	@Autowired
	public OrderFarmerStateWattingChooseField(OrderFarmerStateCollection stateCollection) {
		super(stateCollection);
	}

	@Override
	public boolean chooseField(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {

		boolean flag = orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());

		if (!flag) {
			throw new OrderStateException("状态变更出错");
		}
		orderFarmerStateManager.setCurrentState(nextState);

		return true;
	}

	@Override
	public String getStateValue() {
		return FARMER_WAITTING_CHOOSE_FIELD;
	}

}
