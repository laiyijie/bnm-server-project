package cn.bangnongmang.server.controller.android;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.driverapp.models.BankCardInfo;
import cn.bangnongmang.driverapp.models.BankCardValidateInfo;
import cn.bangnongmang.server.business.common.BankCardBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidBankCardShow;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/card")
public class BankCardController {

	@Autowired
	private BankCardBusiness commonBankCardBuisness;

	@Autowired
	private AndroidBankCardShow androidBankCardShow;

	@ResponseBody
	@RequestMapping("/getCardList")
	public List<BankCardInfo> getCardList(@RequestBody AndroidRequest androidRequest,
										  @SessionAttribute(SESSION_UID) Long uid) {
		return androidBankCardShow.getBankCardList(uid);
	}

	@ResponseBody
	@RequestMapping("/deleteCard")
	public String deleteCard(@RequestBody AndroidRequest androidRequest,
							 @SessionAttribute(SESSION_UID) Long uid) throws BusinessException, AndroidOutputException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		Long id = Long.valueOf(in.get("cardID").toString());
		commonBankCardBuisness.removeCard(uid, id);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/validateCard")
	public BankCardValidateInfo validateCard(@RequestBody AndroidRequest androidRequest,
											 @SessionAttribute(SESSION_UID) Long uid) throws BusinessException, AndroidOutputException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		String cardNumber = (String) in.get("cardNumber");

		return androidBankCardShow.validateCard(cardNumber);
	}

	@ResponseBody
	@RequestMapping("/validateCardByPhone")
	public Map<String, Long> validateCardByPhone(@RequestBody AndroidRequest androidRequest,
												 @SessionAttribute(SESSION_UID) Long uid) throws BusinessException, AndroidOutputException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		String phone = (String) in.get("phone");
		String cardNumber = (String) in.get("cardNumber");
		return commonBankCardBuisness.validateCardByPhone(uid, cardNumber, phone);
	}

	@ResponseBody
	@RequestMapping("/checkCardVerifyCode")
	public String checkCardVerifyCode(@RequestBody AndroidRequest androidRequest,
									  @SessionAttribute(SESSION_UID) Long uid) throws BusinessException, AndroidOutputException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		String phone = (String) in.get("phone");
		String cardNumber = (String) in.get("cardNumber");
		String authCode = (String) in.get("authCode");
		commonBankCardBuisness.checkVerifyCode(uid, phone, cardNumber, authCode);
		return "success";
	}

}
