package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;

public interface UserMachineService {

	Integer STATE_AUTH_INIT = 100;
	Integer STATE_WAITTING_AUTH = 200;
	Integer STATE_AUTH_FAILED = 300;
	Integer STATE_AUTH_PASSED = 400;

	Integer USER_MODEL_STATE_INIT = 100;
	Integer USER_MODEL_STATE_WAITING_AUTH = 200;
	Integer USER_MODEL_STATE_AUTHED = 400;

	UserMachine addUserMachine(Long uid, Long machineModelId);

	boolean addUserMachineImage(Long machineId, String cardType, String url);

	UserMachineModel getUserMachineModelByThree(String machineType, String brand, String number);

	UserMachineModel getAuthedUserMachineModelByThree(String machineType, String brand, String number);

	UserMachineModel addMachineModel(String machineType, String brand, String number);

	List<UserMachineModel> getAuthedModelListByType(String type);

	List<UserMachineType> getAllMachineType();

	List<UserMachine> getAllMyUserMachines(Long uid);

	UserMachine getUserMachineById(Long id);

	Boolean modifyUserMachine(UserMachine userMachine);

	void deleteUserMachineAuthImage(Long id);

	int deleteUserMachine(Long id);


	List<UserMachineShow> getAllUserMachineShow();

	UserMachineShow  getUserMachineShowById(Long id);


	Boolean saveUserMachine(UserMachine userMachine);

	List<OptionWorkingType> getWorkingTypeByModelId(Long modelId);
}
