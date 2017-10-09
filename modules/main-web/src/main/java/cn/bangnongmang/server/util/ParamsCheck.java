package cn.bangnongmang.server.util;

import java.util.regex.Pattern;

import cn.bangnongmang.server.io.BusinessException;

public class ParamsCheck {

	public static void checkPhoneNumber(String input) throws BusinessException{
		
		Pattern pattern = Pattern.compile("^[1][0-9]{10}$");
		
		if (!pattern.matcher(input).matches()) {
			throw new BusinessException(400,"手机号码错误");
		}
	}
	
	public static void checkIdCardNumber(String input) throws BusinessException{
		Pattern pattern = Pattern.compile("^[0-9]{17}[0-9xX]{1}$");
		
		if (!pattern.matcher(input).matches()) {
			throw new BusinessException(400,"身份证错误");
		}
	}
	
	public static void checkImg(String input) throws BusinessException{
		Pattern pattern = Pattern.compile("^data:image/(jpeg|png);base64,.*$");
		
		if (!pattern.matcher(input).matches()) {
			throw new BusinessException(400,"照片不对，请重新选择");
		}
	}
}
