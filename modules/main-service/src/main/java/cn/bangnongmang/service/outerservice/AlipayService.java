package cn.bangnongmang.service.outerservice;

import com.alibaba.fastjson.JSONObject;

public interface AlipayService {
	JSONObject validateCard(String cardNumber);

	String convertCardType(String type);

	String convertBankNameToChinese(String code);
}
