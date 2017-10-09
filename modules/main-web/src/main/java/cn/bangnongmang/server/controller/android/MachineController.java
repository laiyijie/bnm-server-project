package cn.bangnongmang.server.controller.android;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.driverapp.models.UserMachineInfo;
import cn.bangnongmang.server.business.common.DriverAccountBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidMachineShow;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/machine")
public class MachineController {

	@Value("${machine.add.url}")
	private String ADD_CAR_URL;

	@Autowired
	private DriverAccountBusiness driverAccountBusiness;

	@Autowired
	private AndroidMachineShow androidMachineShow;

	@ResponseBody
	@RequestMapping("/getMachineList")
	public List<UserMachineInfo> getCardList(@RequestBody AndroidRequest androidRequest,
											 @SessionAttribute(SESSION_UID) Long uid) {
		return androidMachineShow.getUserMachineList(uid);
	}

	@ResponseBody
	@RequestMapping("/deleteMachine")
	public String deleteCard(@RequestBody AndroidRequest androidRequest,
							 @SessionAttribute(SESSION_UID) Long uid) throws BusinessException, AndroidOutputException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		Long id = Long.valueOf(in.get("machineID").toString());
		driverAccountBusiness.removeUserMachine(id, uid);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/addMachineWebViewURL")
	public String addMachineWebViewURL(@RequestBody AndroidRequest androidRequest,
									   @SessionAttribute(SESSION_UID) Long uid) throws BusinessException, AndroidOutputException {
		return ADD_CAR_URL;
	}

}
