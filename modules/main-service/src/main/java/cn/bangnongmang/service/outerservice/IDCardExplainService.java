package cn.bangnongmang.service.outerservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class IDCardExplainService {

	private JSONObject districtMap;

	private Pattern idCardPattern = Pattern.compile("^[0-9]{17}[0-9xX]{1}$");

	public IDCardExplainService() throws UnsupportedEncodingException, IOException {
		Resource fileResource = new ClassPathResource("IDCardDistrict.data");

		BufferedReader bf = new BufferedReader(new InputStreamReader(fileResource.getInputStream(), "UTF-8"));

		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line);
		}

		String result = buffer.toString();

		setDistrictMap(JSON.parseObject(result));
	}

	public IDCardInfo explainIDCardNumber(String idCardNumber) {

		if (idCardNumber == null || !idCardPattern.matcher(idCardNumber).matches()) {

			return null;
		}

		IDCardInfo idCardInfo = new IDCardInfo();

		String districtNumber = idCardNumber.substring(0, 6);

		if (this.districtMap.containsKey(districtNumber)) {
			idCardInfo.setDistrict(districtMap.getString(districtNumber));
		}

		Integer sex = Integer.parseInt(idCardNumber.substring(16, 17));
		if ((sex % 2) == 0) {
			idCardInfo.setGender("女");
		} else {
			idCardInfo.setGender("男");
		}
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Integer birthYear = Integer.parseInt(idCardNumber.substring(6, 10));
		if (currentYear - birthYear > 0) {
			idCardInfo.setAge(String.valueOf(currentYear - birthYear));
		}

		return idCardInfo;

	}

	public JSONObject getDistrictMap() {
		return districtMap;
	}

	private void setDistrictMap(JSONObject districtMap) {
		this.districtMap = districtMap;
	}

	public static class IDCardInfo {
		private String age = "未知";
		private String gender = "未知";
		private String district = "未知";

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

	}
}
