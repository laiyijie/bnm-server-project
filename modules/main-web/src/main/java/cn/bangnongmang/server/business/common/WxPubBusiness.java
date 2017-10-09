package cn.bangnongmang.server.business.common;

import com.leonoss.wechat.apppay.client.dto.WechatJsApiConfigParams;

public interface WxPubBusiness {

	WechatJsApiConfigParams getWechatJsapiParams(String url);

	void count(String name);

	
	
}
