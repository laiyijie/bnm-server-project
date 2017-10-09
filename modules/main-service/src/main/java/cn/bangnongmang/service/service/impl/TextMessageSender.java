package cn.bangnongmang.service.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import cn.bangnongmang.service.outerservice.util.ParamsCheck;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.PhoneVerifyMapper;
import cn.bangnongmang.data.domain.PhoneVerify;
import cn.bangnongmang.data.domain.PhoneVerifyCriteria;
import cn.bangnongmang.service.service.VerifyCodeService;
import cn.bangnongmang.service.outerservice.util.DesHelper;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service("S_VerifyCodeService")
public class TextMessageSender implements VerifyCodeService {

	public static final long TIMEOUT = 300;
	public static final long INTERVAL = 60;

	@Autowired
	private PhoneVerifyMapper phoneVerifyMapper;
	@Value("${text.livemode}")
	private Boolean liveMode;
	private  static String TEST_VERIFY_CODE = "000000";
	@Override
	public String sendVerifyCode(String username) throws ServiceLayerExeption {

		ParamsCheck.checkPhoneNumber(username);

		if (!liveMode){
			saveVerifyCode(username,TEST_VERIFY_CODE);
			return TEST_VERIFY_CODE;
		}

		if (isGetTooFast(username)) {

			throw new ServiceLayerExeption(60021, "获取速度过快");
		}

		String verifyCode = randVerifyCode();

		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String Stamp = df.format(new Date());
		System.out.println(Stamp);
		String password = "a123456";
		String Secret = DigestUtils.md5Hex(password + Stamp).toUpperCase();

		try {
			JSONObject j = new JSONObject();
			j.put("UserName", "jksc435");
			j.put("Stamp", Stamp);
			j.put("Secret", Secret);
			j.put("Moblie", username);
			j.put("Text", "【帮农忙】尊敬的用户：您的校验码：" + verifyCode + " ，工作人员不会索取，请勿泄漏。");
			j.put("Ext", "");
			j.put("SendTime", "");

			String json = j.toString();
			byte[] data = json.getBytes("utf-8");
			byte[] key = password.getBytes();

			byte[] nkey = new byte[8];
			System.arraycopy(key, 0, nkey, 0, key.length > 8 ? 8 : key.length);

			String str = Base64.encodeBase64String((DesHelper.encrypt(data, nkey)));

			String Url = "http://sh2.ipyy.com/ensms.ashx";

			okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder();

			RequestBody requestBody = new FormBody.Builder().add("UserId", "2222").add("Text64", str).build();
			Request request = requestBuilder.url(Url).post(requestBody).build();
			OkHttpClient client = new OkHttpClient();
			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {

				Map<String, Object> result = JSON.parseObject(response.body().string(), Map.class);

				if (result.get("StatusCode").equals(1)) {

					saveVerifyCode(username,verifyCode);

					return verifyCode;

				} else {
					throw new ServiceLayerExeption(60022, (String) result.get("Description"));
				}
			}

		} catch (Exception e) {

			if (e instanceof ServiceLayerExeption) {
				throw new ServiceLayerExeption(((ServiceLayerExeption) e).getErrorCode(), ((ServiceLayerExeption) e).getErrorMessage());
			}
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getVerifyCode(String username) {

		PhoneVerifyCriteria phoneVerifyCriteria = new PhoneVerifyCriteria();

		phoneVerifyCriteria.or().andPhonenumberEqualTo(username);

		List<PhoneVerify> phoneVerifies = phoneVerifyMapper.selectByExample(phoneVerifyCriteria);

		if (phoneVerifies.isEmpty()) {

			return null;
		}

		if (phoneVerifies.get(0).getSend_time() + TextMessageSender.TIMEOUT < System.currentTimeMillis() / 1000) {

			return null;
		}

		return phoneVerifies.get(0).getVerify_code();

	}

	private boolean isGetTooFast(String username) {

		PhoneVerifyCriteria phoneVerifyCriteria = new PhoneVerifyCriteria();

		phoneVerifyCriteria.or().andPhonenumberEqualTo(username);

		List<PhoneVerify> phoneVerifies = phoneVerifyMapper.selectByExample(phoneVerifyCriteria);

		if (phoneVerifies.isEmpty()) {

			return false;
		}

		if (phoneVerifies.get(0).getSend_time() + TextMessageSender.INTERVAL < System.currentTimeMillis() / 1000) {

			return false;
		}

		return true;

	}

	@Override
	public long getTimeOut() {
		// TODO Auto-generated method stub
		return TextMessageSender.TIMEOUT;
	}

	private String randVerifyCode() {

		Random random = new Random();

		int randInt = random.nextInt(900000) + 100000;

		return String.valueOf(randInt);
	}

	@Override
	public boolean checkVerifyCode(String username, String authCode) {

		String verifyCode = getVerifyCode(username);

		if (verifyCode != null && verifyCode.equals(authCode)) {

			disableCurrentVerifyCode(username);

			return true;
		}
		return false;
	}

	private void disableCurrentVerifyCode(String username) {

		PhoneVerifyCriteria phoneVerifyCriteria = new PhoneVerifyCriteria();

		phoneVerifyCriteria.or().andPhonenumberEqualTo(username);

		List<PhoneVerify> phoneVerifies = phoneVerifyMapper.selectByExample(phoneVerifyCriteria);

		if (!phoneVerifies.isEmpty()) {

			phoneVerifyMapper.deleteByExample(phoneVerifyCriteria);
		}

	}

	private void saveVerifyCode(String username,String verifyCode){
		PhoneVerify phoneVerify = new PhoneVerify();

		phoneVerify.setPhonenumber(username);

		phoneVerify.setVerify_code(verifyCode);

		phoneVerify.setSend_time(System.currentTimeMillis() / 1000);

		PhoneVerifyCriteria phoneVerifyCriteria = new PhoneVerifyCriteria();

		phoneVerifyCriteria.or().andPhonenumberEqualTo(username);

		phoneVerifyMapper.deleteByExample(phoneVerifyCriteria);

		phoneVerifyMapper.insert(phoneVerify);
	}

}
