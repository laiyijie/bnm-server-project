package cn.bangnongmang.service.service;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

public interface VerifyCodeService {

	public String sendVerifyCode(String username) throws ServiceLayerExeption;

	public String getVerifyCode(String username);

	public long getTimeOut();

	public boolean checkVerifyCode(String username, String authCode);
}
