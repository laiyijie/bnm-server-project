package cn.bangnongmang.server.business.common;

import java.util.List;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.server.io.BusinessException;

public interface DriverAccountBusiness {

	Account register(String username, String authCode, String machineId) throws BusinessException;

	Account getAccountInfo(Long uid);

	boolean androidNormalLogin(String username, String authCode, String machineId) throws BusinessException;

	boolean userAutoLogin(String username, String authCode, String machineId) throws BusinessException;

	String getDriverAppLoginOrRegisterText(String username) throws BusinessException;

	List<Account> getAccountInfoByName(String key);

	void addMachineAuthRequest(Long username, String machineType, String brand, String number,
							   String url)
			throws BusinessException;

	void addElseMachineRequest(Long uid, String machineType, String brand, String number,
							   String url)
			throws BusinessException;

	List<String> getMachineBrandList(String machineType);

	List<String> getMachineNumberList(String machineType, String brand);

	List<UserMachineType> getMachineTypes();

	List<UserMachine> getUserMachines(Long uid);

	void modifyMachineAuthRequest(Long id, Long uid, String machineType, String machineBrand,
			String machineNumber, String machinePhotoKey) throws BusinessException;

	void modifyElseMachineRequest(Long id, Long uid, String machineType, String machineBrand,
			String machineNumber, String machinePhotoKey) throws BusinessException;

	void removeUserMachine(Long id, Long uid) throws BusinessException;


}
