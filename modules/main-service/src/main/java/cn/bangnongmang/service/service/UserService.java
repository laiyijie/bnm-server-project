package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountPortrait;
import cn.bangnongmang.data.domain.AccountRealNameAuth;


public interface UserService {

	Integer AVAILABLE = 1;
	Integer UNAVAILABLE = 0;

	Integer DRIVER_LEADER_AUTHED = 200;

	Integer FARMER = 10;
	Integer FARMER_WAITTING_AUTH = 13;
	Integer AUTHENTICATED_FARMER = 14;
	Integer DRIVER = 20;
	Integer DRIVER_WAITTING_AUTH = 30;
	Integer AUTHENTICATED_DRIVER = 40;

	Integer REAL_NAME_AUTH_STATE_INIT = 200;
	Integer REAL_NAME_AUTH_STATE_WAITTING_AUTH = 300;
	Integer REAL_NAME_AUTH_STATE_PASS = 400;

	boolean createUser(String username, int level);

	Account getUserInfoByUsername(String username);

	Account getUserInfo(Long uid);

	boolean setAutoLoginPasswordAndMachineId(String username, String authCode, String machineId);


	boolean isDriver(Long uid);

	boolean isFarmer(Long uid);

	Account getUserInfoByUnionId(String unionid);

	boolean updateWechatInfo(Long uid, String unionid, String openid);

	List<Account> getUserInfoByName(String key);

	AccountPortrait getPortraitUrl(Long uid);

	boolean setPortraitUrl(Long uid, String url);

	boolean addRealNameAuth(Long uid, String name, String idNumber, String upSide, String
            downSide);

	boolean changeRealNameAuthState(Long uid,String reason,Integer state);

	AccountRealNameAuth getRealNameAuth(Long uid);

	boolean changeAccountState(Long uid,Integer state);


	boolean changeAccountName(Long uid, String name);

	List<UserBasicInfoShow> getAccountInListPhone(List<String> phoneList);

}
