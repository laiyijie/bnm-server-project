package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
public class OrderFarmerStateWaittingPay extends AbstractOrderFarmerState implements OrderFarmerStateName {

	@Autowired
	private OrderFarmerService orderFarmerService;

	@Autowired
	private OrderFarmerStateWaittingGot nextState;
	@Autowired
	private TradeFlowService tradeFlowService;

	@Autowired
	public OrderFarmerStateWaittingPay(OrderFarmerStateCollection stateCollection) {
		super(stateCollection);
	}

	@Override
	public boolean prePay(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException {

		OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());

		if (orderFarmer == null) {
			throw new OrderStateException("订单不存在");
		}

		Integer money = new Double(orderFarmer.getUni_price() * orderFarmer.getSize() * orderFarmer.getDiscount() / 100
				* orderFarmer.getPre_pay_rate() / 100).intValue();

		boolean flag = false;

		try {
			PocketLog pocketLog = tradeFlowService.createTradeFlow(orderFarmer.getUid(),
					TradeFlowService.POCKETLOG_TYPE_PROVISIONS, money, TradeFlowService.POCKETLOG_METHOD_DEFAULT,
					"冻结预付款", "", orderFarmer.getOrder_id());
			flag = tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		} catch (ServiceLayerExeption e) {
			throw new OrderStateException(3025, "钱包余额不足");
		}

		if (!flag) {
			throw new OrderStateException(3025, "钱包余额不足");
		}

		flag = orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());

		if (!flag) {
			throw new OrderStateException("状态变更出错");
		}
		orderFarmerStateManager.setCurrentState(nextState);

		return true;
	}

	@Override
	public String getStateValue() {
		return FARMER_WAITTING_PAY;
	}

}
