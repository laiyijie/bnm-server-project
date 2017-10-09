package cn.bangnongmang.server.business.common;

import cn.bangnongmang.server.swagger.model.UserMachineDetail;

/**
 * Created by lucq on 2017/6/9.
 */

public interface MachineBussiness {
	public Boolean saveMachine(UserMachineDetail userMachineDetail);
	public Boolean modifyMachine(UserMachineDetail userMachineDetail);
}
