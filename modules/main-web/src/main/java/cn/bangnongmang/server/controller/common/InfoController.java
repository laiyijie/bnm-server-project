package cn.bangnongmang.server.controller.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.models.auth.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.PingppObject;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.Webhooks;

import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.io.BusinessException;

@Controller
@RequestMapping("/info")
public class InfoController {

	private Resource pingxxPublicKeyResource = new ClassPathResource("pingxxPublicKey");

	private transient Logger logger = LogManager.getLogger(InfoController.class);

	@Value("${pay.pingxx.livemode}")
	private Boolean currLiveMode;
	@Autowired
	private PaymentBusiness commonPaymentBusiness;

	@RequestMapping("/pingxxChargeWebHook")
	public void pingxxChargeWebHook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, BusinessException {
		request.setCharacterEncoding("UTF8");

		String headerSig = request.getHeader("x-pingplusplus-signature");

		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}
		reader.close();
		// 解析异步通知数据
		String bodyString = buffer.toString();
		if (!authPingxxWebHook(bodyString, headerSig)) {
			response.setStatus(500);
			return;
		}
		Event event = Webhooks.eventParse(bodyString);
		if (!currLiveMode.equals(event.getLivemode())) {
			response.setStatus(500);
			return;
		}
		if ("charge.succeeded".equals(event.getType())) {

			PingppObject pingppObject = event.getData().getObject();
			Charge charge = JSON.parseObject(pingppObject.toString(), Charge.class);

			commonPaymentBusiness.doneTradeFlowByTradeOrderId(charge.getId());

			response.setStatus(200);
		} else if ("transfer.succeeded".equals(event.getType())) {

			PingppObject pingppObject = event.getData().getObject();
			Transfer transfer = JSON.parseObject(pingppObject.toString(), Transfer.class);
			commonPaymentBusiness.doneTradeFlowByTradeOrderId(transfer.getId());

			response.setStatus(200);
		} else {
			response.setStatus(500);
		}
	}

	private boolean authPingxxWebHook(String dataString, String signatureString) {
		PublicKey pubKey;
		try {
			pubKey = PingxxUtil.getPubKey(pingxxPublicKeyResource);
			return PingxxUtil.verifyData(dataString, signatureString, pubKey);
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping("test")
	@ResponseBody
	public Object test(@RequestBody IN in ) {
		System.out.println("IN:" + JSON.toJSON(in));
		List<Long> list = new ArrayList<>();
		list.add(1L);
		return list;
	}

	public static class IN{
		private String aa;
		private String bb;

		public String getAa() {
			return aa;
		}

		public void setAa(String a) {
			this.aa = a;
		}

		public String getBb() {
			return bb;
		}

		public void setBb(String bb) {
			this.bb = bb;
		}
	}

}
