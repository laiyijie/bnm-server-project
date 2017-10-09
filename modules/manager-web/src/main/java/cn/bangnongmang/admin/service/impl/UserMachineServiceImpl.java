package cn.bangnongmang.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.service.UserMachineService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionDetailCriteria;
import cn.bangnongmang.data.domain.OptionMachineModelMappingCriteria;
import cn.bangnongmang.data.domain.OptionMachineModelMappingKey;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OptionWorkingTypeCriteria;
import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingCriteria;
import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingKey;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineModelCriteria;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.data.domain.UserMachineTypeCriteria;
@Transactional(rollbackFor = { Exception.class })
@Service
public class UserMachineServiceImpl implements UserMachineService {

	@Autowired
	private UserMachineModelMapper userMachineModelMapper;

	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;

	@Autowired
	private OptionMachineModelMappingMapper optionMachineModelMappingMapper;

	@Autowired
	private OptionDetailMapper optionDetailMapper;

	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Autowired
	private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
	
	public static final Integer USER_MODEL_STATE_WAITING_AUTH = 200;
	public static final Integer USER_MODEL_STATE_AUTHED = 400;
	@Override
	public PageResult<UserMachineModel> getuserMachineModelList( Integer currentPage,Integer pageSize,
			 Integer state) throws BusinessException {
		
		Page<UserMachineModel> page = null;

		UserMachineModelCriteria modelCriteria = new UserMachineModelCriteria();
		modelCriteria.clear();

		PageHelper.startPage(currentPage, pageSize);

		if (state.equals(USER_MODEL_STATE_WAITING_AUTH)) {
			
			modelCriteria.or().andStateEqualTo(USER_MODEL_STATE_WAITING_AUTH);
			page = (Page<UserMachineModel>) userMachineModelMapper.selectByExample(modelCriteria);

		} else if (state.equals(USER_MODEL_STATE_AUTHED)) {

			modelCriteria.or().andStateEqualTo(USER_MODEL_STATE_AUTHED);
			page = (Page<UserMachineModel>) userMachineModelMapper.selectByExample(modelCriteria);
		}

		return new PageResult<UserMachineModel>(page);
	}

	
	
	@Override
	public UserMachineModel updateMachineWidth(long id, double modelWidth) throws BusinessException {

		UserMachineModel machineModel = userMachineModelMapper.selectByPrimaryKey(id);
		machineModel.setModel_width(modelWidth);
			return machineModel;
		
	}


	@Override
	public PageResult<UserMachineType> getuserMachineType(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
			@RequestParam(value = "userMachineTypeId") long userMachineTypeId) throws BusinessException {

		PageHelper.startPage(currentPage, pageSize);

		UserMachineTypeCriteria userMachineType = new UserMachineTypeCriteria();
		userMachineType.or().andIdEqualTo(userMachineTypeId);

		Page<UserMachineType> page = (Page<UserMachineType>) userMachineTypeMapper.selectByExample(userMachineType);

		return new PageResult<UserMachineType>(page);
	}

	@Override
	public boolean updateuserMachineType(long userMachineTypeId, String typeName, String descripetion)
			throws BusinessException {

		if (typeName == null) {
			throw new BusinessException("typeName不许为空");
		}

		UserMachineType machineType = userMachineTypeMapper.selectByPrimaryKey(userMachineTypeId);

		if (machineType == null) {
		throw new BusinessException("此车辆没有填写类型");
		}
		
		machineType.setType_name(typeName);
		machineType.setDescripetion(descripetion);

		if (userMachineTypeMapper.updateByPrimaryKey(machineType) > 0) {
			return true;
		} else {
			throw new BusinessException("此车辆类型更新失败");
		}
	}

	@Override
	public boolean insertuserMachineType(String typeName, String descripetion) throws BusinessException {

		UserMachineType machineType = new UserMachineType();
		machineType.setType_name(typeName);
		machineType.setDescripetion(descripetion);

		if (userMachineTypeMapper.insert(machineType) > 0) {
			return true;
		} else {
			throw new BusinessException("插入车辆类型出错");
		}

	}

	@Override
	public boolean deloptionMachineModel(long modelId, long optionId) throws BusinessException {

		List<OptionMachineModelMappingKey> list = optionMachineUtil(modelId, optionId);

		if (list.size() > 0) {
			OptionMachineModelMappingKey key = list.get(0);

			if (optionMachineModelMappingMapper.deleteByPrimaryKey(key) > 0) {

				return true;

			} else {
				throw new BusinessException("删除失败");
			}
		} else {
			throw new BusinessException("不存在");
		}

	}

	@Override
	public boolean insertoptionMachineModel(long modelId, long optionId) throws BusinessException {

		List<OptionMachineModelMappingKey> list = optionMachineUtil(modelId, optionId);

		if (list.size() <= 0) {

			OptionMachineModelMappingKey key = new OptionMachineModelMappingKey();
			key.setModel_id(modelId);
			key.setOption_id(optionId);

			if (optionMachineModelMappingMapper.insert(key) > 0) {

				return true;

			} else {
				throw new BusinessException("插入数据失败");
			}
		} else {
			throw new BusinessException("已经存在");
		}
	}

	@Override
	public boolean insetWorkingTypeMachineModel(long MachineModelId, long optionWorkingTypeId)
			throws BusinessException {

		List<OptionWorkingTypeMachineModelMappingKey> list = optionWorkingMachineMappingUtil(MachineModelId,
				optionWorkingTypeId);

		if (list.isEmpty()) {

			OptionWorkingTypeMachineModelMappingKey key = new OptionWorkingTypeMachineModelMappingKey();

			key.setMachine_model_id(MachineModelId);
			key.setOption_working_type_id(optionWorkingTypeId);

			if (optionWorkingTypeMachineModelMappingMapper.insert(key) > 0) {

				return true;

			} else {
				throw new BusinessException("插入数据失败");
			}
		} else {
			throw new BusinessException("已经存在");
		}

	}

	@Override
	public boolean delWorkingTypeMachineModel(long modelId, long optionWorkingTypeId) throws BusinessException {

		UserMachineModel machineModel = userMachineModelMapper.selectByPrimaryKey(modelId);
		if (machineModel == null) {
			throw new BusinessException("不存在");
		}
		
		OptionWorkingType selectByPrimaryKey = optionWorkingTypeMapper.selectByPrimaryKey(optionWorkingTypeId);
		if (selectByPrimaryKey == null) {
			throw new BusinessException("不存在");
		}
		
		List<OptionWorkingTypeMachineModelMappingKey> list = optionWorkingMachineMappingUtil(modelId,
				optionWorkingTypeId);

		if (list.isEmpty()) {
			throw new BusinessException("不存在");
		}
		
		OptionWorkingTypeMachineModelMappingKey key = list.get(0);

		if (optionWorkingTypeMachineModelMappingMapper.deleteByPrimaryKey(key) > 0) {
			return true;
		} else {
			throw new BusinessException("删除失败");
		}
	}

	@Override
	public PageResult<OptionDetail> getOptionDetailByModelId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "modelId") long modelId) throws BusinessException {

		UserMachineModel model = userMachineModelMapper.selectByPrimaryKey(modelId);

		if (model == null) {
			throw new BusinessException("机器不存在");
		}

		OptionMachineModelMappingCriteria criteria = new OptionMachineModelMappingCriteria();
		criteria.or().andModel_idEqualTo(modelId);

		List<OptionMachineModelMappingKey> list = optionMachineModelMappingMapper.selectByExample(criteria);
		
		OptionDetailCriteria optionDetailCriteria = new OptionDetailCriteria();
		optionDetailCriteria.clear();
		
		Page<OptionDetail> OptionDetailList = new Page<OptionDetail> ();
		int flag = 0;
		for (OptionMachineModelMappingKey key : list) {

			optionDetailCriteria.or().andIdEqualTo(key.getOption_id());

			flag++;
			if (flag == list.size()) {

				PageHelper.startPage(currentPage, pageSize);
				OptionDetailList = (Page<OptionDetail>) optionDetailMapper.selectByExample(optionDetailCriteria);
			}
		}
		return new PageResult<OptionDetail>(OptionDetailList);

	}

	
	/**
	 * 
	 *  Description:
	 *  @param 
	 *  @return List<OptionMachineModelMappingKey>
	 *  @author luchangquan  DateTime 2017年4月6日 上午10:04:52
	 *  @param modelId
	 *  @param optionId
	 *  @return
	 *  @throws BusinessException
	 */
	public List<OptionMachineModelMappingKey> optionMachineUtil(long modelId, long optionId) throws BusinessException {

		UserMachineModel machineModel = userMachineModelMapper.selectByPrimaryKey(modelId);

		OptionDetail optionDetail = optionDetailMapper.selectByPrimaryKey(optionId);

		if (machineModel == null && optionDetail == null) {
			throw new BusinessException("没有对于的machineModel或optionDetail");
		}

		OptionMachineModelMappingCriteria criteria = new OptionMachineModelMappingCriteria();
		criteria.clear();
		criteria.or().andModel_idEqualTo(modelId).andOption_idEqualTo(optionId);

		return optionMachineModelMappingMapper.selectByExample(criteria);

	}

	/**
	 * 
	 *  Description:
	 *  @param 
	 *  @return List<OptionWorkingTypeMachineModelMappingKey>
	 *  @author luchangquan  DateTime 2017年4月6日 上午10:05:19
	 *  @param machineModelId
	 *  @param optionWorkingTypeId
	 *  @return
	 *  @throws BusinessException
	 */
	public List<OptionWorkingTypeMachineModelMappingKey> optionWorkingMachineMappingUtil(long machineModelId,
			long optionWorkingTypeId) throws BusinessException {

		UserMachineModel machineModel = userMachineModelMapper.selectByPrimaryKey(machineModelId);
		OptionWorkingType workingType = optionWorkingTypeMapper.selectByPrimaryKey(optionWorkingTypeId);

		if (machineModel == null && workingType == null) {
			throw new BusinessException("没有对于的workingType或machineModel");
		}

		
		OptionWorkingTypeMachineModelMappingCriteria criteria = new OptionWorkingTypeMachineModelMappingCriteria();
		criteria.clear();
		criteria.or().andMachine_model_idEqualTo(machineModelId).andOption_working_type_idEqualTo(optionWorkingTypeId);

		return optionWorkingTypeMachineModelMappingMapper.selectByExample(criteria);

	}

	@Override
	public PageResult<OptionWorkingType> getWorkingTypeByModelId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "modelId") long modelId) throws BusinessException {

		UserMachineModel model = userMachineModelMapper.selectByPrimaryKey(modelId);

		if (model == null) {
			throw new BusinessException("机器不存在");
		}

		OptionWorkingTypeMachineModelMappingCriteria criteria = new OptionWorkingTypeMachineModelMappingCriteria();
		criteria.or().andMachine_model_idEqualTo(modelId);

		
		List<OptionWorkingTypeMachineModelMappingKey> list = optionWorkingTypeMachineModelMappingMapper
				.selectByExample(criteria);

		
		OptionWorkingTypeCriteria typeCriteria = new OptionWorkingTypeCriteria();
		typeCriteria.clear();
		typeCriteria.or().andIdEqualTo(Long.MAX_VALUE);
		PageHelper.startPage(currentPage, pageSize);
		Page<OptionWorkingType> OptionWorkingType =  (Page<OptionWorkingType>) optionWorkingTypeMapper.selectByExample(typeCriteria);
		
		int flag = 0;
		for (OptionWorkingTypeMachineModelMappingKey key : list) {

			typeCriteria.or().andIdEqualTo(key.getOption_working_type_id());

			flag++;
			if (flag == list.size()) {
				PageHelper.startPage(currentPage, pageSize);
				OptionWorkingType = (Page<OptionWorkingType>) optionWorkingTypeMapper.selectByExample(typeCriteria);
			}
		}
		return new PageResult<OptionWorkingType>(OptionWorkingType);
	}









}
