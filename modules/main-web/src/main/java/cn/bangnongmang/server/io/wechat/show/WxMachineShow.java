package cn.bangnongmang.server.io.wechat.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.server.io.wechat.data.UserMachineAuthInfo;

@Service
public class WxMachineShow {

	@Autowired
	private UserMachineShowMapper userMachineShowMapper;

	public UserMachineAuthInfo convertUserMachineShowToUserMachineAuthInfo(UserMachineShow ums) {

		if (ums == null) {
			return null;
		}

		UserMachineAuthInfo userMachineAuthInfo = new UserMachineAuthInfo();
		userMachineAuthInfo.setBrand(ums.getUserMachineModel().getModel_brand());
		userMachineAuthInfo.setFailedReason(ums.getUserMachine().getFailed_reason());
		userMachineAuthInfo.setId(ums.getId());
		userMachineAuthInfo.setImageUrls(ums.getUserMachineAuthImages());
		userMachineAuthInfo.setNumber(ums.getUserMachineModel().getModel_number());
		userMachineAuthInfo.setState(ums.getUserMachine().getState());
		userMachineAuthInfo.setType(ums.getUserMachineType().getType_name());

		return userMachineAuthInfo;
	}

	public UserMachineAuthInfo getUserMachineAuthInfoById(Long uid, Long machineId) {

		UserMachineShow ums = userMachineShowMapper.selectByUserMachineId(machineId);

		if (ums == null) {
			return null;
		}

		if (!ums.getUserMachine().getUid().equals(uid)) {
			return null;
		}

		return convertUserMachineShowToUserMachineAuthInfo(ums);
	}

}
