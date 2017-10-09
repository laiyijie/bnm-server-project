
package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.pingplusplus.model.Charge;

import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.driverapp.models.Wallet;
import cn.bangnongmang.driverapp.models.WalletCheck;
import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidBankCardShow;

/**
 *
 * 新的支付接口，废弃旧的接口
 *
 * @ClassName AppPayController
 * @author laiyijie
 * @date 2017年2月23日 下午8:09:24
 *
 */
@Controller
@RequestMapping("/payment")
public class AppPayController {

	@Autowired
	private PaymentBusiness commonPaymentBusiness;

	@Autowired
	private AndroidBankCardShow androidBankCardShow;

	/**
	 *
	 * 获取钱包有多少钱
	 *
	 * @Title getMyWalletInfo
	 *
	 * @param uid
	 * @return
	 */

	@RequestMapping("/getMyWalletInfo")
	@ResponseBody
	public Wallet getMyWalletInfo(@SessionAttribute(SESSION_UID) Long uid) {

		Pocket pocket = commonPaymentBusiness.getCurrentMoney(uid);

		Wallet wallet = new Wallet();
		wallet.setCredit(pocket.getCredit());
		wallet.setInsurance(pocket.getInsurance());
		wallet.setMoney(pocket.getCurr_money());
		wallet.setProvisions(pocket.getProvisions());
		wallet.setWatting_in(pocket.getWaitting_in());

		return wallet;
	}

	/**
	 *
	 * 支付
	 *
	 * @Title pay
	 *
	 * @param androidRequest
	 *            channelType: String 当前取值两种 wx_app,upacp money: Integer 单位分
	 * @param uid
	 * @return
	 * @throws AndroidOutputException
	 * @throws BusinessException
	 */

	@RequestMapping("/pay")
	@ResponseBody
	public Charge pay(@RequestBody AndroidRequest androidRequest, @SessionAttribute(SESSION_UID) Long uid)
			throws AndroidOutputException, BusinessException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		String channelType = (String) in.get("channelType");
		Integer money = (Integer) in.get("money");

		return commonPaymentBusiness.charge(uid, channelType, money);

	}

	/**
	 *
	 * 提现
	 *
	 * @Title withDraw
	 *
	 * @param androidRequest
	 *            cardNumber:卡号 money：提现金额，分为单位 openBank：银行名称，中文
	 *
	 * @param uid
	 * @return
	 * @throws AndroidOutputException
	 * @throws BusinessException
	 */
	@RequestMapping("/withDraw")
	@ResponseBody
	public String withDraw(@RequestBody AndroidRequest androidRequest,
						   @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		String cardNumber = (String) in.get("cardNumber");
		Integer money = (Integer) in.get("money");
		String openBank = (String) in.get("openBank");

		if (commonPaymentBusiness.withdrawByUnionPay(uid, money, cardNumber, openBank) != null) {
			return "success";
		}
		throw new BusinessException("提现失败");
	}

	/**
	 *
	 * 获取我的钱包log
	 *
	 * @Title getMyWalletLogList
	 *
	 * @param uid
	 * @return
	 */

	@RequestMapping("/getMyWalletLogList")
	@ResponseBody
	public List<WalletCheck> getMyWalletLogList(@SessionAttribute(SESSION_UID) Long uid) {

		return androidBankCardShow.getMyWalletCheckList(uid);

	}

}
