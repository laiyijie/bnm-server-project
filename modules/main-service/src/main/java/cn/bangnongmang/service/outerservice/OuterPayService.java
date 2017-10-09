package cn.bangnongmang.service.outerservice;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

public interface OuterPayService {

	Transfer unionPayTransfer(String orderNo, Integer amount, String cardNumber, String realName, String openBank,
			String description) throws ServiceLayerExeption;

	Charge createWxCharge(String orderNum, Integer money, String subject, String body) throws ServiceLayerExeption;

	Charge createUninonPayCharge(String orderNum, Integer money, String subject, String body) throws ServiceLayerExeption;

	Charge createWxPubCharge(String orderNum, Integer money, String subject, String body, String openId) throws ServiceLayerExeption;

	Transfer wxPubTransfer(String orderNo, Integer amount, String openId, String description)
			throws ServiceLayerExeption;

	Transfer getTransferById(String transferId) throws ServiceLayerExeption;

	Charge getChargeById(String chargeId) throws ServiceLayerExeption;
}
