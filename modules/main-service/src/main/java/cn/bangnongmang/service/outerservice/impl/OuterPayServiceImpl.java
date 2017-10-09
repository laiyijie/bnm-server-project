package cn.bangnongmang.service.outerservice.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.service.outerservice.OuterPayService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service("S_OuterPayService")
public class OuterPayServiceImpl implements OuterPayService {

	@Value("${pay.pingxx.sk}")
	private String sk;
	@Value("${pay.pingxx.keyPath}")
	private String keyPath;
	@Value("${pay.pingxx.appId}")
	private String appId;

	private static final Integer PAYMENT_EXPIRE_INTERVAL = 1200;

	@PostConstruct
	public void init() {
		Pingpp.apiKey = this.sk;
		Pingpp.privateKeyPath = this.keyPath;
	}

	@Override
	public Charge createWxCharge(String orderNum, Integer money, String subject, String body)
			throws ServiceLayerExeption {
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", orderNum);
		chargeParams.put("amount", money);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填
											// 100）
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		chargeParams.put("app", app);
		chargeParams.put("channel", "wx");
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", "127.0.0.1");
		chargeParams.put("subject", subject);
		chargeParams.put("body", body);
		chargeParams.put("time_expire", getPaymentExpireTime());
		try {
			Charge charge = Charge.create(chargeParams);

			return charge;
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			// TODO Auto-generated catch block
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	@Override
	public Charge createUninonPayCharge(String orderNum, Integer money, String subject, String body)
			throws ServiceLayerExeption {
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", orderNum);
		chargeParams.put("amount", money);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填
											// 100）
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		chargeParams.put("app", app);
		chargeParams.put("channel", "upacp");
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", "127.0.0.1");
		chargeParams.put("subject", subject);
		chargeParams.put("body", body);
		chargeParams.put("time_expire", getPaymentExpireTime());
		try {
			Charge charge = Charge.create(chargeParams);

			return charge;
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	@Override
	public Charge createWxPubCharge(String orderNum, Integer money, String subject, String body, String openId)
			throws ServiceLayerExeption {
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", orderNum);
		chargeParams.put("amount", money);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填
											// 100）
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		chargeParams.put("app", app);
		chargeParams.put("channel", "wx_pub");
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", "127.0.0.1");
		chargeParams.put("subject", subject);
		chargeParams.put("body", body);
		chargeParams.put("time_expire", getPaymentExpireTime());
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("open_id", openId);
		extra.put("limit_pay", "no_credit");
		chargeParams.put("extra", extra);
		try {
			return Charge.create(chargeParams);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	@Override
	public Transfer unionPayTransfer(String orderNo, Integer amount, String cardNumber, String realName,
			String openBank, String description) throws ServiceLayerExeption {
		Map<String, Object> transferMap = new HashMap<String, Object>();
		transferMap.put("channel", "unionpay");// 企业付款（银行卡）
		transferMap.put("order_no", orderNo);
		transferMap.put("amount", amount);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填
											// 100）
		transferMap.put("type", "b2c");
		transferMap.put("currency", "cny");
		transferMap.put("description", description);
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		transferMap.put("app", app);
		Map<String, Object> extra = new HashMap<String, Object>();
		extra.put("card_number", cardNumber);
		extra.put("user_name", realName);
		extra.put("open_bank", openBank);
		transferMap.put("extra", extra);
		try {
			Transfer transfer = Transfer.create(transferMap);
			return transfer;
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	@Override
	public Transfer wxPubTransfer(String orderNo, Integer amount, String openId, String description)
			throws ServiceLayerExeption {
		Map<String, Object> transferMap = new HashMap<String, Object>();
		transferMap.put("channel", "wx_pub");// 此处 wx_pub 为公众平台的支付
		transferMap.put("order_no", orderNo);
		transferMap.put("amount", amount);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填
											// 100）
		transferMap.put("type", "b2c");
		transferMap.put("currency", "cny");
		transferMap.put("recipient", openId);// 企业付款给指定用户的 open_id
		transferMap.put("description", description);
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		transferMap.put("app", app);
		// Map<String, Object> extra = new HashMap<String, Object>();
		// extra.put("user_name", "User Name");
		// extra.put("force_check", false);
		// transferMap.put("extra", extra);

		try {
			return Transfer.create(transferMap);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	@Override
	public Charge getChargeById(String chargeId) throws ServiceLayerExeption {

		try {
			return Charge.retrieve(chargeId);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	@Override
	public Transfer getTransferById(String transferId) throws ServiceLayerExeption {

		try {
			return Transfer.retrieve(transferId);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			throw new ServiceLayerExeption(e.getMessage());
		}
	}

	private Long getPaymentExpireTime() {
		return System.currentTimeMillis() / 1000 + PAYMENT_EXPIRE_INTERVAL;
	}

}
