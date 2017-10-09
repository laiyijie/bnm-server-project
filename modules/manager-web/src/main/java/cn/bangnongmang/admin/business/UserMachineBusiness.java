package cn.bangnongmang.admin.business;

import cn.bangnongmang.admin.io.swagger.show.UserMachineShow;
import cn.bangnongmang.admin.swagger.model.UserMachineDetail;
import cn.bangnongmang.data.domain.UserMachine;

/**
 * Created by lucq on 2017/5/26.
 */
public interface UserMachineBusiness {

	public Boolean modifyUserMachine(UserMachine userMachine);
	public Boolean modifyUserMachineDetail(UserMachineDetail userMachineDetail);

	public Boolean saveUserMachineDetail(UserMachineDetail userMachineDetail);


}
