package cn.bangnongmang.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.admin.enums.AccountLevel;
import cn.bangnongmang.admin.enums.UserMachineEnums;
import cn.bangnongmang.admin.enums.UserModelStateEnum;
import cn.bangnongmang.admin.service.OtherMachineAuthService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineCriteria;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineModelCriteria;
import cn.bangnongmang.data.domain.UserMachineType;
@Transactional(rollbackFor = { Exception.class })
@Service
public class OtherMachineAuthServiceImpl  implements OtherMachineAuthService {
	@Autowired
	private UserMachineModelMapper userMachineModelMapper;

	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;

	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Override
	public boolean otherMachineAuth(long id, long userMachineId) throws BusinessException {
		
		UserMachine userMachine = userMachineMapper.selectByPrimaryKey(id);
		
		UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(userMachineId);
		
		UserMachineType machineType = userMachineTypeMapper.selectByPrimaryKey(userMachineModel.getUser_machine_type_id());

		
		if (machineType != null) {

			UserMachineModelCriteria criteria = new UserMachineModelCriteria();
			criteria.or().andModel_numberEqualTo(userMachineModel.getModel_number());

			List<UserMachineModel> list = userMachineModelMapper.selectByExample(criteria);
			
			
			if (list.size() == 1 && list.get(0).getState() == 200) {
				
				
				UserMachineModel machineModel = list.get(0);
				machineModel.setState(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode());
				userMachineModelMapper.updateByPrimaryKey(machineModel);

			
				userMachine.setState(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode());
				userMachine.setMachine_model_id(machineModel.getId());
				userMachineMapper.updateByPrimaryKey(userMachine);
				
				updateAccount(userMachine);
				
			} else if (list.size() > 1) {
				
				Update(list, userMachineModel.getModel_number(), userMachine);
			}

		} else {
			throw new BusinessException("车辆类型不存在");
		}
		return true;

		
	}

	@Override
	public long getUserMachineId(long userMachineModelId) {
		
		UserMachineCriteria criteria = new UserMachineCriteria();
		criteria.or().andMachine_model_idEqualTo(userMachineModelId);
		
		List<UserMachine> list = userMachineMapper.selectByExample(criteria);
		
		for (UserMachine userMachine : list) {
			
			if (userMachine.getState().equals(UserMachineEnums.STATE_WAITTING_AUTH.getCurrencyCode())) {
				return userMachine.getId();
			}
			
		}
		return 0;
		
	}

	@Override
	public boolean refuseOtherMachineAuth(long id, long userMachineId) throws BusinessException {
		
		UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(userMachineId);
//		if (userMachineModel.getState().equals(UserModelStateEnum.USER_MODEL_STATE_WAITING_AUTH.getCurrencyCode())) {
//			throw new BusinessException("您还未认证");
//		}
		
		UserMachine userMachine = userMachineMapper.selectByPrimaryKey(id);
//		if (userMachine.getState().equals(UserMachineEnums.STATE_WAITTING_AUTH.getCurrencyCode())) {
//			throw new BusinessException("您还未认证");
//		}
		

		userMachineModel.setState(UserModelStateEnum.USER_MODEL_STATE_FAILED.getCurrencyCode());
		
		if (userMachineModelMapper.updateByPrimaryKey(userMachineModel) < 0) {
			throw new BusinessException("驳回失败");
		}

		userMachine.setState(UserMachineEnums.STATE_AUTH_FAILED.getCurrencyCode());
		
		if (userMachineMapper.updateByPrimaryKey(userMachine) < 0) {

			throw new BusinessException("驳回失败");
		}
		return true;
		
	}
	public boolean Update(List<UserMachineModel> list, String modelNumber, UserMachine userMachine)
			throws BusinessException {
		UserMachineModelCriteria criteria = new UserMachineModelCriteria();

		int flag = 0;
		for (UserMachineModel userMachineModel : list) {
			flag++;
			
			if (userMachineModel.getState() == UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode() && userMachineModel.getModel_number().equals(modelNumber)) {
				
				criteria.clear();
				criteria.or().andModel_numberEqualTo(modelNumber).andStateEqualTo(UserMachineEnums.STATE_WAITTING_AUTH.getCurrencyCode());
				userMachineModelMapper.deleteByExample(criteria);
				
				
				userMachine.setMachine_model_id(userMachineModel.getId());
				userMachine.setState(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode());
				userMachineMapper.updateByPrimaryKey(userMachine);
				
				updateAccount(userMachine);

			} else if (flag == list.size()) {
				
				UserMachineModel machineModel = list.get(0);
				machineModel.setState(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode());
				userMachineModelMapper.updateByPrimaryKey(machineModel);

				
				userMachine.setMachine_model_id(machineModel.getId());
				userMachine.setState(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode());
				userMachineMapper.updateByPrimaryKey(userMachine);

			
				criteria.clear();
				criteria.or().andModel_numberEqualTo(modelNumber).andStateEqualTo(UserMachineEnums.STATE_WAITTING_AUTH.getCurrencyCode());
				userMachineModelMapper.deleteByExample(criteria);
				
				updateAccount(userMachine);
			}

		}
		return false;
	}
	public boolean updateAccount(UserMachine userMachine)throws BusinessException{
		
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(userMachine.getUid());
		
		List<Account> accounts = accountMapper.selectByExample(accountCriteria);
		if (accounts.isEmpty()) {
			throw new BusinessException("该用户不存在");
		}
		Account account = accounts.get(0);
		
		account.setLevel(AccountLevel.ACCOUNT_AUTHENTICATED.getCurrencyCode());
		
		if (accountMapper.updateByPrimaryKey(account) > 0) {
			
			return true;
		}
		return false;
		
	}

}
