package cn.bangnongmang.admin.io.swagger.converter;


import cn.bangnongmang.admin.service.UserService;
import cn.bangnongmang.admin.swagger.model.MachineModel;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.admin.swagger.model.UserMachineBasic;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lucq on 2017/5/26.
 */
@Service("userMachineConverter")
public class UserMachineConverter {


//	@Autowired
//	private UserService userService;

	public UserMachineBasic convertToUserMachineBasic(UserMachineShow userMachineShow){

		if(userMachineShow==null){
			return null;
		}
		UserMachineBasic userMachineBasic=new UserMachineBasic();

		userMachineBasic.setId(userMachineShow.getUserMachine().getId());
		userMachineBasic.setUid(userMachineShow.getUserMachine().getUid());
		userMachineBasic.setIntegrity(userMachineShow.getUserMachine().getIntegrity());
		userMachineBasic.setModelId(userMachineShow.getUserMachine().getMachine_model_id());
		userMachineBasic.setBrand(userMachineShow.getUserMachineModel().getModel_brand());
		userMachineBasic.setBrandNum(userMachineShow.getUserMachineModel().getModel_number());

//		Account account=userService.getAccount(userMachineShow.getUserMachine().getUid());
//		AccountRealNameAuth accountRealNameAuth=userService.getAccountRealNameAuth(userMachineShow.getUserMachine().getUid());
//		if( accountRealNameAuth!=null) {
//			userMachineBasic.setUsername(accountRealNameAuth.getReal_name());
//		}
//		if(account!=null){
//			userMachineBasic.setTel(account.getUsername());
//		}
        if(userMachineShow.getUserMachine().getState().equals(UserMachineService.STATE_AUTH_PASSED)){
			userMachineBasic.setState(UserMachineBasic.StateEnum.AUTHED);
		}else if(userMachineShow.getUserMachine().getState().equals(UserMachineService.STATE_AUTH_FAILED)){
			userMachineBasic.setState(UserMachineBasic.StateEnum.DENIED);
		}else{
			userMachineBasic.setState(UserMachineBasic.StateEnum.WAITING_AUTH);
		}

		return userMachineBasic;
	}
}
