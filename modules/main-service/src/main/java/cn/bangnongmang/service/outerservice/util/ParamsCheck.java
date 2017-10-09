package cn.bangnongmang.service.outerservice.util;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

import java.util.regex.Pattern;

public class ParamsCheck {

	public static void checkPhoneNumber(String input) throws ServiceLayerExeption{
		
		Pattern pattern = Pattern.compile("^[1][0-9]{10}$");
		
		if (!pattern.matcher(input).matches()) {
			throw new ServiceLayerExeption(400,"手机号码错误");
		}
	}
	
	public static void checkIdCardNumber(String input) throws ServiceLayerExeption{
		Pattern pattern = Pattern.compile("^[0-9]{17}[0-9xX]{1}$");
		
		if (!pattern.matcher(input).matches()) {
			throw new ServiceLayerExeption(400,"身份证错误");
		}
	}
	
	public static void checkImg(String input) throws ServiceLayerExeption{
		Pattern pattern = Pattern.compile("^data:image/(jpeg|png);base64,.*$");
		
		if (!pattern.matcher(input).matches()) {
			throw new ServiceLayerExeption(400,"照片不对，请重新选择");
		}
	}
}
