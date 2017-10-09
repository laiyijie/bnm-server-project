package cn.bangnongmang.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.bangnongmang.admin.controller.api.OtherMachineAuthControllerApi;
import cn.bangnongmang.admin.service.OtherMachineAuthService;
import cn.bangnongmang.admin.util.BusinessException;

@RestController
public class OtherMachineAuthController implements OtherMachineAuthControllerApi {
	@Autowired
	private OtherMachineAuthService otherMachineAuthService;
	
	@Override
	public void otherMachineAuth(long id, long userMachineModelId) throws BusinessException {
		
		otherMachineAuthService.otherMachineAuth(id, userMachineModelId);
	}

	@Override
	public void refuseOtherMachineAuth(long id, long userMachineModelId) throws BusinessException {
		
		otherMachineAuthService.refuseOtherMachineAuth(id, userMachineModelId);
	}


	@Override
	public long getUserMachineId(long userMachineModelId) {
		
		return otherMachineAuthService.getUserMachineId(userMachineModelId);
		
	}

	
	
	


}
