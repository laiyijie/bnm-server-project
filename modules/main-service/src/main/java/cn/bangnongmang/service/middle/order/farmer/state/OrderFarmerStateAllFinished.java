package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderFarmerStateAllFinished extends AbstractOrderFarmerState implements OrderFarmerStateName {

	@Autowired
	public OrderFarmerStateAllFinished(OrderFarmerStateCollection stateCollection) {
		super(stateCollection);
	}

	@Override
	public String getStateValue() {
		return FARMER_ALL_FINISHED;
	}

}
