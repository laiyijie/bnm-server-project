package cn.bangnongmang.server.io.wechat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.UserMachineAuthImageMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineAuthImageCriteria;
import cn.bangnongmang.data.domain.UserMachineCriteria;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.server.io.wechat.data.UserMachineAuthInfo;
import cn.bangnongmang.server.io.wechat.data.UserMachineInfo;
import cn.bangnongmang.service.service.GeoService;

@Service
public class DomainToWxOutputAdapter {


	@Autowired
	private GeoService geoService;
	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;
	@Autowired
	private UserMachineModelMapper userMachineModelMapper;
	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private UserMachineAuthImageMapper userMachineAuthImageMapper;




	public List<UserMachineInfo> getUserMachineInfo(Long uid) {

		List<UserMachineInfo> userMachineInfos = new ArrayList<>();

		UserMachineCriteria userMachineCriteria = new UserMachineCriteria();

		userMachineCriteria.or().andUidEqualTo(uid);

		List<UserMachine> userMachines = userMachineMapper.selectByExample(userMachineCriteria);

		for (UserMachine userMachine : userMachines) {

			UserMachineModel userMachineModel = userMachineModelMapper
					.selectByPrimaryKey(userMachine.getMachine_model_id());
			if (userMachineModel == null) {
				continue;
			}

			UserMachineType userMachineType = userMachineTypeMapper
					.selectByPrimaryKey(userMachineModel.getUser_machine_type_id());
			if (userMachineType == null) {
				continue;
			}
			UserMachineInfo userMachineInfo = new UserMachineInfo();

			userMachineInfo.setType(userMachineType.getType_name());
			userMachineInfo.setBrand(userMachineModel.getModel_brand());
			userMachineInfo.setNumber(userMachineModel.getModel_number());

			userMachineInfos.add(userMachineInfo);

		}

		return userMachineInfos;
	}

	public List<UserMachineAuthInfo> convertUserMachinesToUserMachineInfos(List<UserMachine> userMachines) {

		List<UserMachineAuthInfo> userMachineInfos = new ArrayList<>();
		
		for (UserMachine userMachine : userMachines) {
			UserMachineAuthInfo userMachineAuthInfo = new UserMachineAuthInfo();
			userMachineAuthInfo.setFailedReason(userMachine.getFailed_reason());
			UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(userMachine.getMachine_model_id());
			if (userMachineModel != null) {
				userMachineAuthInfo.setBrand(userMachineModel.getModel_brand());
				userMachineAuthInfo.setNumber(userMachineModel.getModel_number());
				UserMachineType userMachineType = userMachineTypeMapper.selectByPrimaryKey(userMachineModel.getUser_machine_type_id());
				if (userMachineType != null) {
					userMachineAuthInfo.setType(userMachineType.getType_name());
				}
			}
			UserMachineAuthImageCriteria authImageCriteria = new UserMachineAuthImageCriteria();
			authImageCriteria.or().andUser_machine_idEqualTo(userMachine.getId());
			
			userMachineAuthInfo.setId(userMachine.getId());
			userMachineAuthInfo.setState(userMachine.getState());
			userMachineAuthInfo.setImageUrls(userMachineAuthImageMapper.selectByExample(authImageCriteria));
			
			userMachineInfos.add(userMachineAuthInfo);
		}
		
		return userMachineInfos;
	}

}
