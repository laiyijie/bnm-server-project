package cn.bangnongmang.server.business.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonoss.wechat.apppay.client.dto.WechatJsApiConfigParams;

import cn.bangnongmang.server.business.common.WxPubBusiness;
import cn.bangnongmang.service.outerservice.WxOcService;
import cn.bangnongmang.service.service.CountVisitService;

@Service
public class WxPubBusinessImpl implements WxPubBusiness{

	@Autowired
	private WxOcService wxOcService;
	
	@Autowired
	private CountVisitService countVisitService;
	
	@Override
	public WechatJsApiConfigParams getWechatJsapiParams(String url){
		
		return wxOcService.getJsApiConfigAuthParams(url);
	}
	@Override
	public void count(String name) {
		
		countVisitService.count(name);
	}
	
	
	
}
