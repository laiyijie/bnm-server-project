package cn.bangnongmang.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.admin.enums.AccountLevel;
import cn.bangnongmang.admin.enums.UserModelStateEnum;
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
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.data.domain.UserMachineTypeCriteria;

@Service
@Transactional(rollbackFor = { Exception.class })
public class MachineAuthService {

	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private UserMachineModelMapper userMachineModelMapper;
	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;

	@Autowired
	private AccountMapper accountMapper;
	
	public boolean authMachine(Long userMachineId, String operator) throws BusinessException {
		UserMachine userMachine = userMachineMapper.selectByPrimaryKey(userMachineId);
		if (userMachine == null) {
			return false;
		}

		UserMachineModel userMachineModel = getUserMachineModelByUserMachineId(userMachineId);
		if (userMachineModel == null) {
			throw new BusinessException("这两机器没有选择正确的车型");
		}

		if (!userMachineModel.getState().equals(UserModelStateEnum.USER_MODEL_STATE_AUTHED.getCurrencyCode())) {
			throw new BusinessException("请先认证用户自填的车型信息");
		}

		if (userMachine.getState().equals(UserModelStateEnum.USER_MODEL_STATE_AUTHED.getCurrencyCode())) {
			throw new BusinessException("已经认证通过");
		}

		userMachine.setState(UserModelStateEnum.USER_MODEL_STATE_AUTHED.getCurrencyCode());

		userMachineMapper.updateByPrimaryKey(userMachine);
		
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(userMachine.getUid());
		
		List<Account> accounts = accountMapper.selectByExample(accountCriteria);
		if (accounts.isEmpty()) {
			throw new BusinessException("该用户不存在");
		}
		Account account = accounts.get(0);
		
		account.setLevel(AccountLevel.ACCOUNT_AUTHENTICATED.getCurrencyCode());
		
		accountMapper.updateByPrimaryKey(account);

		return true;

	}

	public boolean denyMachine(Long userMachineId, String operator, String ps) throws BusinessException {

		UserMachine userMachine = userMachineMapper.selectByPrimaryKey(userMachineId);

		if (userMachine == null) {
			return false;
		}

		userMachine.setState(UserModelStateEnum.USER_MODEL_STATE_INIT.getCurrencyCode());
		userMachine.setFailed_reason(ps);

		userMachineMapper.updateByPrimaryKey(userMachine);

		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(userMachine.getUid());
		
		List<Account> accounts = accountMapper.selectByExample(accountCriteria);
		if (accounts.isEmpty()) {
			throw new BusinessException("该用户不存在");
		}
		Account account = accounts.get(0);
		
		account.setLevel(AccountLevel.ACCOUNT_WATTING_AUTH.getCurrencyCode());
		
		accountMapper.updateByPrimaryKey(account);
		return true;
	}

	public UserMachineModel getUserMachineModelByUserMachineId(Long userMachineId) {

		UserMachine userMachine = userMachineMapper.selectByPrimaryKey(userMachineId);

		if (userMachine == null) {
			return null;
		}

		UserMachineModel userMachineModel = userMachineModelMapper
				.selectByPrimaryKey(userMachine.getMachine_model_id());

		if (userMachineModel == null) {
			return null;
		}

		return userMachineModel;
	}

	public boolean authUserMachineModel(Long userMachineModelId, String operator) throws BusinessException {

		UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(userMachineModelId);
		if (userMachineModel == null) {
			throw new BusinessException("这个模型不存在");
		}

		userMachineModel.setState(UserModelStateEnum.USER_MODEL_STATE_AUTHED.getCurrencyCode());

		userMachineModelMapper.updateByPrimaryKey(userMachineModel);

		return true;
	}

	public boolean denyAndDeleteMachineModel(Long userMachineModelId, String operator) throws BusinessException {

		UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(userMachineModelId);

		if (userMachineModel == null) {
			throw new BusinessException("这个模型已不存在");
		}

		UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
		userMachineCriteria.or().andMachine_model_idEqualTo(userMachineModelId);

		List<UserMachine> userMachines = userMachineMapper.selectByExample(userMachineCriteria);

		for (UserMachine userMachine : userMachines) {
			userMachine.setMachine_model_id(null);
			userMachineMapper.updateByPrimaryKey(userMachine);
		}

		return true;
	}

	public boolean modifyModelById(Long id, String type, String brand, String number, Double power, Double width,
			String more_info_url, String special_info, String operator) throws BusinessException {

		UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(id);
		if (userMachineModel == null) {
			throw new BusinessException("修改的机器型号不存在");
		}
		UserMachineTypeCriteria userMachineTypeCriteria = new UserMachineTypeCriteria();
		userMachineTypeCriteria.or().andType_nameEqualTo(type);
		List<UserMachineType> types = userMachineTypeMapper.selectByExample(userMachineTypeCriteria);
		if (types.isEmpty()) {
			throw new BusinessException("你选择的机器种类不存在");
		}

		userMachineModel.setModel_brand(brand);
		userMachineModel.setModel_number(number);
		userMachineModel.setModel_power(power);
		userMachineModel.setModel_width(width);
		userMachineModel.setMore_info_url(more_info_url);
		userMachineModel.setSpecial_info(special_info);
		userMachineModel.setUser_machine_type_id(types.get(0).getId());

		userMachineModelMapper.updateByPrimaryKey(userMachineModel);
		return true;
	}

	public boolean combineModel(Long currentModelId, Long dstModelId, String operator) throws BusinessException {

		UserMachineModel currUserMachineModel = userMachineModelMapper.selectByPrimaryKey(currentModelId);
		UserMachineModel dstUsermachineModel = userMachineModelMapper.selectByPrimaryKey(dstModelId);
		if (currUserMachineModel == null) {
			throw new BusinessException("当前车型不存在");
		}

		if (dstUsermachineModel == null) {
			throw new BusinessException("目标车型不存在");
		}

		UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
		userMachineCriteria.or().andMachine_model_idEqualTo(currentModelId);
		UserMachine userMachine = new UserMachine();
		userMachine.setMachine_model_id(dstModelId);
		userMachineMapper.updateByExampleSelective(userMachine, userMachineCriteria);
		
		userMachineModelMapper.deleteByPrimaryKey(currentModelId);
		
		return true;
	}

}
