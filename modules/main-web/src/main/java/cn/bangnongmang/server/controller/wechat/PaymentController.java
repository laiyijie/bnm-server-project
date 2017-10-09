package cn.bangnongmang.server.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.business.common.PushEventBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.wechat.WxOutputException;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;

import javax.annotation.Resource;

@Controller("wxPaymentController")
@RequestMapping("/wx/payment")
public class PaymentController {

	@Autowired
	private PaymentBusiness farmerPaymentBusiness;
	@Resource(name = "commonPayment")
	private PaymentBusiness commonPaymentBusiness;
	@Autowired
	private PushEventBusiness pushEventBusiness;

	@RequestMapping("/getpocketinfo")
	@ResponseBody
	public Object getPocketInfo(@SessionAttribute(SESSION_UID) Long uid) {

		return farmerPaymentBusiness.getCurrentMoney(uid);
	}

	@RequestMapping("/getUserPocketLogs")
	@ResponseBody
	public Object getUserPocketLogs(@SessionAttribute(SESSION_UID) Long uid) {

		return farmerPaymentBusiness.getPocketLog(uid);
	}

	/**
	 *
	 * 充值接口到pingxx
	 *
	 * @Title recharge
	 *
	 * @param uid
	 * @param money
	 * @return
	 * @throws BusinessException
	 */

	@RequestMapping("/recharge")
	@ResponseBody
	public Charge recharge(@SessionAttribute(SESSION_UID) Long uid, @RequestParam("money") Integer money)
			throws BusinessException {

		return commonPaymentBusiness.charge(uid, PaymentBusiness.CHANEL_CHARGE_TYPE_WX_PUB, money);
	}

	@RequestMapping("/withdraw")
	@ResponseBody
	public Object withdraw(@SessionAttribute(SESSION_UID) Long uid, @RequestParam("money") Integer money)
			throws BusinessException, WxOutputException {

		Transfer transfer = commonPaymentBusiness.withdrawByOc(uid, money);

		if (null!=transfer) {
			return "done";
		} else {
			throw new WxOutputException("提现失败");
		}
	}

	@RequestMapping("/finishShared")
	@ResponseBody
	public Object finishShared(@SessionAttribute(SESSION_UID) Long uid)
			throws BusinessException, WxOutputException {

		PushEvent event = pushEventBusiness.finishSharedMoments(uid);

		if (event == null) {
			return false;
		}

		return event;
	}

	@RequestMapping("/checkPushEvent")
	@ResponseBody
	public Object checkPushEvent(@SessionAttribute(SESSION_UID) Long uid)
			throws BusinessException, WxOutputException {

		List<PushEvent> events = pushEventBusiness.getUserActivePushEvent(uid);

		return events;
	}

	@RequestMapping("/openRedPocket")
	@ResponseBody
	public Object openRedPocket(@SessionAttribute(SESSION_UID) Long uid, @RequestParam("id") String id)
			throws BusinessException, WxOutputException {

		PocketLog pocketLog = pushEventBusiness.openRedPocketByPushEventId(id);

		if (pocketLog == null) {
			throw new WxOutputException("这个红包不能打开");
		}

		return pocketLog.getMoney();

	}

}
