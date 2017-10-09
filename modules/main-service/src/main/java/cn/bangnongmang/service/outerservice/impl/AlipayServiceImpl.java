package cn.bangnongmang.service.outerservice.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.service.outerservice.util.ServerHttpClientUtil;

@Service("S_AlipayService")
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	private ServerHttpClientUtil serverHttpClientUtil;

	@Override
	public JSONObject validateCard(String cardNumber) {
		String response = serverHttpClientUtil
				.post("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="
						+ cardNumber + "&cardBinCheck=true");
		// TODO 增加判断 （信用卡） 等

		return JSON.parseObject(response);

	}

	@Override
	public String convertCardType(String type) {
		String result = "未知卡片类型";
		switch (type) {
		case "CC":
			result = "信用卡";
			break;
		case "DC":
			result = "储蓄卡";
			break;
		default:
			break;
		}
		return result;
	}

	private JSONObject banknameMap;

	public AlipayServiceImpl() throws UnsupportedEncodingException, IOException {
		Resource fileResource = new ClassPathResource("bankname.json");

		BufferedReader bf = new BufferedReader(new InputStreamReader(fileResource.getInputStream(), "UTF-8"));

		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line);
		}

		String result = buffer.toString();

		setBanknameMap(JSON.parseObject(result));

	}

	public JSONObject getBanknameMap() {
		return banknameMap;
	}

	public void setBanknameMap(JSONObject banknameMap) {
		this.banknameMap = banknameMap;
	}

	@Override
	public String convertBankNameToChinese(String code) {
		String result = getBanknameMap().getString(code);
		if (result == null)
			return code;
		return result;
	}
}
