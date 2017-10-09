package cn.bangnongmang.server.notify;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.pingplusplus.model.Transfer;

import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.server.notify.bo.HookPaymenParams;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;

@Aspect
@Component
@Order(3)
public class PaymentNotify {

	@Autowired
	private PocketLogMapper pocketLogMapper;
	private NotifyUtil notifyUtil;

	public static final String POCKET_WITHDRAW_POST = "POCKET_WITHDRAW_POST";
	public static final String POCKET_WITHDRAW_SUCCESS = "POCKET_WITHDRAW_SUCCESS";
	public static final String POCKET_WITHDRAW_FAILED = "POCKET_WITHDRAW_FAILED";

	@Autowired
	public PaymentNotify(NotifyUtil notifyUtil) {
		this.notifyUtil = notifyUtil;
		HookPaymenParams hookPaymentNotify = new HookPaymenParams();
		hookPaymentNotify.setDescription("银联提现");
		hookPaymentNotify.setMoney(String.valueOf(2000 / 100.0));
		hookPaymentNotify.setState("交易在途");
		hookPaymentNotify.setTime(notifyUtil.convertTimemillisToString(System.currentTimeMillis()));
		hookPaymentNotify.setTradeType("提现");

		notifyUtil.registerHook(POCKET_WITHDRAW_POST, "钱包 - 提现申请提交成功 ", hookPaymentNotify);
		notifyUtil.registerHook(POCKET_WITHDRAW_SUCCESS, "钱包 - 提现完成，钱已到账 ", hookPaymentNotify);
		notifyUtil.registerHook(POCKET_WITHDRAW_FAILED, "钱包 - 提现失败，钱已退回 ", hookPaymentNotify);

	}

	@AfterReturning(value = "execution(* cn.bangnongmang.server.business.common.PaymentBusiness.withdrawByUnionPay(..))", returning = "transfer")
	public void withdrawByUnionPay(Transfer transfer) {
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andTrade_order_idEqualTo(transfer.getId());
		PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		HookPaymenParams params = createParams(pocketLog.getPocket_log_id(), "提现");
		notifyUtil.sendNotifyBusinessMessage(pocketLog.getUid(), POCKET_WITHDRAW_POST, params);
	}

	@AfterReturning(value = "execution(* cn.bangnongmang.server.business.common.PaymentBusiness.withdrawByOc(..))", returning = "transfer")
	public void withdrawByOc(Transfer transfer) {
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andTrade_order_idEqualTo(transfer.getId());
		PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		HookPaymenParams params = createParams(pocketLog.getPocket_log_id(), "提现");
		notifyUtil.sendNotifyBusinessMessage(pocketLog.getUid(), POCKET_WITHDRAW_POST, params);
	}

	@AfterReturning("execution(* cn.bangnongmang.server.business.common.PaymentBusiness.doneTradeFlowByTradeOrderId(..)) && args(tradeOrderId)")
	public void doneTradeFlowByTradeOrderId(String tradeOrderId) {
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andTrade_order_idEqualTo(tradeOrderId);
		PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		HookPaymenParams params = createParams(pocketLog.getPocket_log_id(), "提现");
		notifyUtil.sendNotifyBusinessMessage(pocketLog.getUid(), POCKET_WITHDRAW_SUCCESS, params);
	}

	@AfterReturning("execution(* cn.bangnongmang.server.business.common.PaymentBusiness.cancelTradeFlowByTradeOrderId(..)) && args(tradeOrderId)")
	public void cancelTradeFlowByTradeOrderId(String tradeOrderId) {
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andTrade_order_idEqualTo(tradeOrderId);
		PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		HookPaymenParams params = createParams(pocketLog.getPocket_log_id(), "提现");
		notifyUtil.sendNotifyBusinessMessage(pocketLog.getUid(), POCKET_WITHDRAW_FAILED, params);
	}

	public HookPaymenParams createParams(String pocketLogId, String tradeType) {
		PocketLog pocketLog = pocketLogMapper.selectByPrimaryKey(pocketLogId);
		HookPaymenParams hookPaymentNotify = new HookPaymenParams();
		hookPaymentNotify.setDescription(pocketLog.getDetail());
		hookPaymentNotify.setMoney(String.valueOf(pocketLog.getMoney() / 100.0));
		switch (pocketLog.getState()) {
		case TradeFlowService.POCKETLOG_STATE_CANCEL:
			hookPaymentNotify.setState("交易失败");
			break;

		case TradeFlowService.POCKETLOG_STATE_DONE:
			hookPaymentNotify.setState("交易成功");
			break;
		case TradeFlowService.POCKETLOG_STATE_DRAFT:
			hookPaymentNotify.setState("交易在途");
			break;
		default:
			break;
		}
		hookPaymentNotify.setTime(notifyUtil.convertTimemillisToString(System.currentTimeMillis()));
		hookPaymentNotify.setTradeType(tradeType);
		return hookPaymentNotify;
	}

}
