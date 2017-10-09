package cn.bangnongmang.service.middle.order.farmer.state;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateCollection;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.PocketLog;

import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.WorkSizeService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

import java.util.List;

@Service
public class OrderFarmerStateWattingSizeEnsure extends AbstractOrderFarmerState implements OrderFarmerStateName {

	@Autowired
	private OrderFarmerService orderFarmerService;

	@Autowired
	private OrderFarmerStateWaittingDistribute nextState;

	@Autowired
	private WorkSizeService workSizeService;

	@Autowired
	private TradeFlowService tradeFlowService;

	@Autowired
	public OrderFarmerStateWattingSizeEnsure(OrderFarmerStateCollection stateCollection) {
		super(stateCollection);
	}

	@Override
	public boolean uploadWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, String imagePath, String title) throws
			OrderStateException {
		OrderFarmerWorkSize orderFarmerWorkSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(orderFarmerStateManager.getId(),
				WorkSizeService.WORK_SIZE_STATE_DENIED);
		if (orderFarmerWorkSize == null)
			orderFarmerWorkSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(orderFarmerStateManager.getId(), WorkSizeService
					.WORK_SIZE_STATE_WAITTING_ENSURE);
		return orderFarmerWorkSize != null && workSizeService.addWorkSizeAuthImage(orderFarmerWorkSize.getId(), imagePath,title);

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
		return true;
	}
	@Override
	public boolean ensureTodaySize(OrderFarmerStateManager orderFarmerStateManager, Double todaySize)
			throws OrderStateException {

		OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderFarmerStateManager.getId());

		if (orderFarmer == null) {
			throw new OrderStateException("订单不存在");
		}

		OrderFarmerWorkSize workSize = workSizeService.getOrderFarmerWorkSizeByOrderIdAndState(
				orderFarmerStateManager.getId(), WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE);

		if (workSize == null) {
			throw new OrderStateException("没有找到作业记录");
		}

		if (!todaySize.equals(workSize.getSize())) {
			throw new OrderStateException("亩数可能有变更，请刷新后重试");
		}

		if (!workSizeService.changeOrderFarmerWorkSizeStateTo(workSize.getId(),
				WorkSizeService.WORK_SIZE_STATE_ENSURED)) {
			throw new OrderStateException("状态变更失败，请重试");
		}

		Integer money = (int) (Math.round(workSize.getSize() * orderFarmer.getUni_price()));

		try {
			PocketLog pocketLog = tradeFlowService.createTradeFlow(orderFarmer.getUid(),
					TradeFlowService.POCKETLOG_TYPE_PROVISIONS_PAY, money, TradeFlowService.POCKETLOG_METHOD_DEFAULT,
					"支付订单款", "", orderFarmer.getOrder_id());

			tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());

		} catch (ServiceLayerExeption e) {
			throw new OrderStateException(e.getErrorCode(), e.getErrorMessage());
		}

		if (!orderFarmerService.changeStateTo(orderFarmerStateManager.getId(), nextState.getStateValue())) {
			throw new OrderStateException("状态变更失败，请重试");
		}

		orderFarmerStateManager.setCurrentState(nextState);

		return true;
	}

	@Override
	public String getStateValue() {
		return FARMER_WAITTING_SIZE_ENSURE;
	}

}
