package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.BankCard;

public interface BankCardService {

	String CARD_AVALIABLE = "CARD_AVALIABLE";
	String CARD_UNAVALIABLE = "CARD_UNAVALIABLE";
	int deleteBankCard(Long id);
	BankCard getBankCard(Long id);
	int addBankCard(Long uid, String phone, String cardNumber, String type, String bank);
	List<BankCard> getBankCardByNumber(String cardNumber);
	BankCard getBankCardByUidAndCardNumber(Long uid, String cardNumber);

}
