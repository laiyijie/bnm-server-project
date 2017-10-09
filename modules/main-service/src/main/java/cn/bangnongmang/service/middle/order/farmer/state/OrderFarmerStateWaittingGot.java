package cn.bangnongmang.service.middle.order.farmer.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.middle.pocket.OrderInsuranceService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderInvite;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
public class OrderFarmerStateWaittingGot extends AbstractOrderFarmerState implements OrderFarmerStateName {

	public static final String STATE = FARMER_WAITTING_GOT;

	@Autowired
	private OrderFarmerStateGot nextState;

	@Autowired
	private OrderFarmerService orderFarmerService;
	@Autowired
	private OrderDriverService orderDriverService;
	@Autowired
	private TeamOrderService teamOrderService;
	@Autowired
	private FollowOrderService followOrderService;
	@Autowired
	private TradeFlowService tradeFlowService;
	@Autowired
	private OrderInsuranceService orderInsuranceService;
	@Autowired
	private UserWorkCalendarService userWorkCalendarService;

	@Autowired
	public OrderFarmerStateWaittingGot(OrderFarmerStateCollection stateCollection) {
		super(stateCollection);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.SERIALIZABLE)
	public boolean grabOrder(OrderFarmerStateManager orderFarmerStateManager, Long teamInOrderId)
			throws OrderStateException {
		// 判断是否满足接单条件
		OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());
		Long currMemberNum = teamOrderService.countTeamJoinRequestsByTeamIdAndState(teamInOrderId,
				TeamOrderService.REQUEST_ACCEPTED);
		OrderInvite orderInvite = orderFarmerService.getOrderInvite(orderFarmerStateManager.getId());
		TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamInOrderId);
		if (orderFarmer == null) {
			throw new OrderStateException("订单不存在！");
		}

		if (currMemberNum != (orderFarmer.getDesire_num() - 1)) {
			throw new OrderStateException("队伍人数不对");
		}
		if (teamInOrder == null) {
			throw new OrderStateException("队伍不存在");
		}
		if (orderInvite != null) {
			if (!orderInvite.getUid().equals(teamInOrder.getUid())) {
				throw new OrderStateException("该订单农户已指定队长接单，您无法抢本单");
			}
		}
		orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());
		orderFarmer.setGot_time(System.currentTimeMillis() / 1000);
		orderFarmer.setDriver_leader(teamInOrder.getUid());
		orderFarmerService.updateOrderFarmerById(orderFarmer);
		orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue());

		List<TeamJoinRequest> teamJoinRequests = teamOrderService.getTeamJoinRequestsByTeamIdAndState(teamInOrderId,
				TeamOrderService.REQUEST_ACCEPTED);
		List<Long> allMember = teamJoinRequests.stream().map(a -> a.getUid()).collect(Collectors
				.toList());
		allMember.add(teamInOrder.getUid());
		Integer index = 0;
		for (Long member : allMember) {
			createOrderDriver(orderFarmer.getOrder_id(), member, index);
			userWorkCalendarService.insertCalendar(member, orderFarmer.getOrder_id(),
					orderFarmer.getDesire_start_time(),
					orderFarmer.getDesire_start_time() + UserWorkCalendarService.DEFAULT_WORKING_TIME);
			index++;
		}

		List<Long> theUserNotGotThisOrder = new ArrayList<>();

		List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrderByOrderId(orderFarmer.getOrder_id());
		for (TeamInOrder t : teamInOrders) {
			theUserNotGotThisOrder.addAll(teamOrderService.getTeamJoinRequestsByTeamId(t.getId()).stream()
					.map(a -> a.getUid()).collect(Collectors.toList()));
			theUserNotGotThisOrder.add(t.getUid());
		}
		theUserNotGotThisOrder.removeIf(user -> allMember.stream().anyMatch(member -> member.equals(user)));
		for (Long uid : theUserNotGotThisOrder) {
			try {
				orderInsuranceService.refundInsurance(uid, orderFarmerStateManager.getId());
			} catch (ServiceLayerExeption serviceLayerExeption) {
				throw new OrderStateException(serviceLayerExeption.getErrorCode(),
						serviceLayerExeption.getErrorMessage());
			}
		}

		// 清除所有和此订单相关的队伍和队员请求,除了这个队伍的队员以外
		teamOrderService.deleteTeamNotAcceptMemberByTeamId(teamInOrderId);
		teamOrderService.deleteTeamsAndRequestsByOrderId(orderFarmer.getOrder_id(), teamInOrderId);
		teamOrderService.deleteTeamRequestByTeamIdAndState(teamInOrderId, TeamOrderService.REQUEST_WAITTING);
		// 清除所有关注
		followOrderService.unfollowOrderByOrderId(orderFarmer.getOrder_id());

		orderFarmerStateManager.setCurrentState(nextState);
		return true;
	}

	@Override
	public String getStateValue() {
		return STATE;
	}

	private void createOrderDriver(String orderId, Long uid, Integer index) {
		OrderDriver orderDriver = new OrderDriver();
		orderDriver.setActual_money(0);
		orderDriver.setActual_size(0.0);
		orderDriver.setOrder_farmer_id(orderId);
		String s = "0000" + String.valueOf(index);
		orderDriver.setOrder_id(orderId + s.substring(s.length() - 4));
		orderDriver.setUid(uid);
		orderDriver.setService_end(0L);
		orderDriver.setService_start(0L);
		orderDriver.setState(OrderDriverService.ORDER_DRIVER_STATE_GOT);
		orderDriverService.createOrderDriver(orderDriver);
	}
}
