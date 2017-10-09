package cn.bangnongmang.server.business.common;

import java.util.Map;

import cn.bangnongmang.server.io.BusinessException;

public interface BankCardBusiness {

	void checkVerifyCode(Long uid, String phone, String cardNumber, String authCode) throws
			BusinessException;

	void addBankCard(Long uid, String phone, String cardNumber) throws BusinessException;

	Map<String, Long> validateCardByPhone(Long uid, String cardNumber, String phone) throws
			BusinessException;

	void removeCard(Long uid, Long id) throws BusinessException;
	

}
