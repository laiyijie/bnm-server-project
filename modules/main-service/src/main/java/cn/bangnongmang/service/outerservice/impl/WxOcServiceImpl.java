package cn.bangnongmang.service.outerservice.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leonoss.wechat.apppay.client.dto.WechatJsApiConfigParams;
import com.leonoss.wechat.apppay.dto.WechatJsApiPaySignParam;
import com.leonoss.wechat.apppay.util.Util;

import cn.bangnongmang.service.outerservice.WxOcService;
import cn.bangnongmang.service.outerservice.util.ServerHttpClientUtil;

@Service("S_WxOcService")
public class WxOcServiceImpl implements WxOcService {

	@Value("${wx.oacc.appid}")
	private String appid;
	@Value("${wx.oacc.appsecret}")
	private String appsecret;

	@Value("${wx.oacc.preSharedKey}")
	private String preSharedKey;

	@Value("${wx.oacc.getSaltStringUrl}")
	private String getSaltStringUrl;
	@Value("${wx.oacc.getAccessTokenUrl}")
	private String getAccessTokenUrl;
	@Value("${wx.oacc.getJsApiTicketUrl}")
	private String getJsApiTicketUrl;

	@Autowired
	private ServerHttpClientUtil httpUtil;

	private transient final Logger logger = LogManager.getLogger(WxOcServiceImpl.class);

	@Autowired
	private ServerHttpClientUtil httpClientUtil;

	private String getHostToken(String salt, String time) {
		logger.debug(preSharedKey);
		String mdString = DigestUtils.md5Hex(DigestUtils.md5Hex(preSharedKey + time) + salt);

		return mdString;
	}

	@Override
	public JSONObject oAuthGetTokenAndOpenid(String code) {

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret
				+ "&code=" + code + "&grant_type=authorization_code";

		String data = httpUtil.post(url);
		if (data == null) {
			return null;
		}
		JSONObject jb = JSON.parseObject(data);

		if (jb.containsKey("errcode")) {

			logger.error("[wechat oauth error]" + jb.getString("errmsg"));
			return null;
		}

		return jb;
	}

	@Override
	public JSONObject getUserWechatInfo(String accessToken, String openId) {

		String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
				+ "&lang=zh_CN";

		String data = httpUtil.post(userInfoUrl);

		if (data == null) {
			return null;
		}
		JSONObject jb = JSON.parseObject(data);

		if (jb.containsKey("errcode")) {

			logger.error("[wechat getUserInfo error]" + jb.getString("errmsg"));
			return null;
		}
		return jb;
	}

	@Override
	public WechatJsApiConfigParams getJsApiConfigAuthParams(String url) {

		WechatJsApiPaySignParam signParam = new WechatJsApiPaySignParam();
		signParam.setNoncestr(Util.generateString(32));
		signParam.setJsapi_ticket(getJsApiTicket());
		signParam.setTimestamp(Long.toString(System.currentTimeMillis() / 1000));
		signParam.setUrl(url);
		WechatJsApiConfigParams request = new WechatJsApiConfigParams();
		request.setAppid(appid);
		request.setTimestamp(signParam.getTimestamp());
		request.setNoncestr(signParam.getNoncestr());
		request.setSignature(signJsapi(signParam));
		return request;
	}

	@Override
	public String getAccessToken() {
		return getRemoteThings(getAccessTokenUrl, "access_token");
	}

	@Override
	public String getJsApiTicket() {
		return getRemoteThings(getJsApiTicketUrl, "ticket");
	}

	private String getRemoteThings(String burl, String keyWord) {

		String salt = httpClientUtil.post(getSaltStringUrl);

		JSONObject sJsonObject = JSON.parseObject(salt);

		logger.debug(salt);

		if (sJsonObject.getLong("message") == null) {

			return null;
		}

		String saltString = String.valueOf(sJsonObject.getLong("message"));

		String url = burl + "?token=" + getHostToken(saltString, saltString) + "&time=" + saltString;

		logger.debug(url);

		String accessTokenReponse = httpClientUtil.post(url);
		logger.debug(accessTokenReponse);
		JSONObject aJsonObject = JSONObject.parseObject(accessTokenReponse);

		return aJsonObject.getString(keyWord);
	}
	
	private String signJsapi(WechatJsApiPaySignParam params){
		
		Map<String, Object> nameValuesMap = new HashMap<String, Object>();
		
		nameValuesMap.put("jsapi_ticket", params.getJsapi_ticket());
		nameValuesMap.put("noncestr", params.getNoncestr());
		nameValuesMap.put("timestamp", params.getTimestamp());
		nameValuesMap.put("url", params.getUrl());
		
		
		
		List<Entry<String, Object>> listOfKeyValuesToBeSigned = new ArrayList<Entry<String, Object>>(
				nameValuesMap.entrySet());
		// Sort the fields
		Collections.sort(listOfKeyValuesToBeSigned, new Comparator<Entry<String, Object>>() {
			@Override
			public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
				return o1.getKey().compareToIgnoreCase(o2.getKey());
			}
		});

		// Generate the signed string
		String sperator = "";
		String signInput = "";
		for (int ii = 0; ii < listOfKeyValuesToBeSigned.size(); ii++) {
			if (ii != 0)
				sperator = "&";
			Entry<String, Object> keyValue = listOfKeyValuesToBeSigned.get(ii);
			Object value = keyValue.getValue();
			// 跳过null或者空字符串
			if (value == null) {
				continue;
			}
			if (value != null && (value instanceof String)) {
				String strValue = (String) value;
				if (Util.isEmpty(strValue)) {
					continue;
				}
			}

			if (value != null) {
				signInput += sperator + keyValue.getKey() + "=" + value.toString();
			}
		}
		
		return DigestUtils.sha1Hex(signInput);
	}

	@Override
	public JSONObject getUserWechatInfo(String openId) {
		String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + getAccessToken() + "&openid=" + openId
				+ "&lang=zh_CN";

		String data = httpUtil.post(userInfoUrl);

		if (data == null) {
			return null;
		}
		JSONObject jb = JSON.parseObject(data);

		if (jb.containsKey("errcode")) {

			logger.error("[wechat getUserInfo error]" + jb.getString("errmsg"));
			return null;
		}
		return jb;
	}
	
	


}
