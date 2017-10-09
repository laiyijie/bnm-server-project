package cn.bangnongmang.service.outerservice;

import com.alibaba.fastjson.JSONObject;
import com.leonoss.wechat.apppay.client.dto.WechatJsApiConfigParams;


public interface WxOcService {

	JSONObject oAuthGetTokenAndOpenid(String code);
	
	JSONObject getUserWechatInfo(String accessToken,String openId);
	JSONObject getUserWechatInfo(String openId);
	String getAccessToken();
	
	String getJsApiTicket();
	
	WechatJsApiConfigParams getJsApiConfigAuthParams(String url);
}
