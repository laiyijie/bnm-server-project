package cn.bangnongmang.server.controller.wechat;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bangnongmang.server.business.common.DriverAccountBusiness;
import cn.bangnongmang.server.business.common.WxPubBusiness;
import cn.bangnongmang.server.io.BusinessException;

@Controller("wxInfoController")
@RequestMapping("/wx/info")
public class InfoController {

	@Autowired
	private WxPubBusiness wechatBusiness;
	@Autowired
	private DriverAccountBusiness driverAccountBusiness;

	@Value("#{ systemProperties }")
	private Object va;

	@Value("${controller.info.jsAuthUrl}")
	private String jsAuthUrl;

	@ResponseBody
	@RequestMapping("/getWechatJsapiParams")
	public Object getWechatJsapiParams() {

		return wechatBusiness.getWechatJsapiParams(jsAuthUrl);
	}

	@RequestMapping("/countVisit")
	@ResponseBody
	public Object countVisit(@RequestParam(value = "name", required = false) String name, HttpServletResponse res) {

		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		res.addHeader("Access-Control-Allow-Headers", "Content-Type");
		if (name == null) {
			
			name = "forgetToSetName";
		}
		wechatBusiness.count(name);
		return "done";
	}

	@RequestMapping("/getCode")
	@ResponseBody
	public String getCode(@RequestParam("code") String code) {
		return code;
	}

	@RequestMapping("/getEnv")
	@ResponseBody
	public String getEnv() {
		return va.toString();
	}

}
