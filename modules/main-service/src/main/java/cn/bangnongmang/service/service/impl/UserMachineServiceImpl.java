package cn.bangnongmang.service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.service.service.UserMachineService;

@Service("S_UserMachineService")
//@Service("userMachineService")
public class UserMachineServiceImpl implements UserMachineService {

	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private UserMachineAuthImageMapper userMachineAuthImageMapper;
	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;
	@Autowired
	private UserMachineModelMapper userMachineModelMapper;
	@Autowired
	private UserMachineShowMapper userMachineShowMapper;
	@Autowired
	private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Override
	public UserMachine addUserMachine(Long uid, Long machineModelId) {
		UserMachine userMachine = new UserMachine();
		userMachine.setId(generateMachineId());
		userMachine.setUid(uid);
		userMachine.setMachine_model_id(machineModelId);
		userMachine.setBuy_time(null);
		userMachine.setState(STATE_WAITTING_AUTH);
		userMachineMapper.insert(userMachine);

		return userMachine;
	}

	private Long generateMachineId() {

		Integer random = new Random().nextInt(1000);
		return (System.currentTimeMillis() * 1000) + random;
	}

	@Override
	public boolean addUserMachineImage(Long machineId, String cardType, String url) {
		UserMachineAuthImage userMachineAuthImage = new UserMachineAuthImage();
		userMachineAuthImage.setType(cardType);
		userMachineAuthImage.setUpdate_time(System.currentTimeMillis() / 1000);
		userMachineAuthImage.setUrl(url);
		userMachineAuthImage.setUser_machine_id(machineId);
		userMachineAuthImageMapper.insert(userMachineAuthImage);
		return true;
	}

	private UserMachineType getUserMachineTypeByName(String machineType) {

		UserMachineTypeCriteria userMachineTypeCriteria = new UserMachineTypeCriteria();

		userMachineTypeCriteria.or().andType_nameEqualTo(machineType);

		List<UserMachineType> types = userMachineTypeMapper.selectByExample(userMachineTypeCriteria);
		if (types.isEmpty()) {
			return null;
		}
		return types.get(0);
	}

	@Override
	public UserMachineModel getUserMachineModelByThree(String type, String brand, String number) {

		UserMachineType mType = getUserMachineTypeByName(type);
		if (mType == null) {
			return null;
		}
		UserMachineModelCriteria userMachineModelCriteria = new UserMachineModelCriteria();
		userMachineModelCriteria.or().andUser_machine_type_idEqualTo(mType.getId()).andModel_brandEqualTo(brand)
				.andModel_numberEqualTo(number);
		List<UserMachineModel> models = userMachineModelMapper.selectByExample(userMachineModelCriteria);
		if (models.isEmpty()) {
			return null;
		}
		return models.get(0);
	}

	@Override
	public UserMachineModel getAuthedUserMachineModelByThree(String machineType, String brand, String number) {
		UserMachineType mType = getUserMachineTypeByName(machineType);
		if (mType == null) {
			return null;
		}
		UserMachineModelCriteria userMachineModelCriteria = new UserMachineModelCriteria();
		userMachineModelCriteria.or().andUser_machine_type_idEqualTo(mType.getId()).andModel_brandEqualTo(brand)
				.andModel_numberEqualTo(number).andStateEqualTo(USER_MODEL_STATE_AUTHED);
		List<UserMachineModel> models = userMachineModelMapper.selectByExample(userMachineModelCriteria);
		if (models.isEmpty()) {
			return null;
		}
		return models.get(0);
	}

	@Override
	public UserMachineModel addMachineModel(String machineType, String brand, String number) {
		UserMachineType mType = getUserMachineTypeByName(machineType);
		if (mType == null) {
			return null;
		}
		UserMachineModel userMachineModel = new UserMachineModel();

		userMachineModel.setId(generateMachineId());
		userMachineModel.setModel_brand(brand);
		userMachineModel.setModel_number(number);
		userMachineModel.setModel_power(null);
		userMachineModel.setModel_width(null);
		userMachineModel.setMore_info_url(null);
		userMachineModel.setSpecial_info(null);
		userMachineModel.setState(USER_MODEL_STATE_WAITING_AUTH);
		userMachineModel.setUser_machine_type_id(mType.getId());

		userMachineModelMapper.insert(userMachineModel);

		return userMachineModel;
	}

	@Override
	public List<UserMachineModel> getAuthedModelListByType(String machineType) {
		List<UserMachineModel> result = new ArrayList<>();
		UserMachineType mType = getUserMachineTypeByName(machineType);
		if (mType == null) {
			return result;
		}

		UserMachineModelCriteria userMachineModelCriteria = new UserMachineModelCriteria();
		userMachineModelCriteria.or().andUser_machine_type_idEqualTo(mType.getId()).andStateEqualTo(STATE_AUTH_PASSED);

		return userMachineModelMapper.selectByExample(userMachineModelCriteria);

	}

	@Override
	public List<UserMachineType> getAllMachineType() {
		UserMachineTypeCriteria userMachineTypeCriteria = new UserMachineTypeCriteria();
		userMachineTypeCriteria.or();
		return userMachineTypeMapper.selectByExample(userMachineTypeCriteria);
	}

	@Override
	public List<UserMachine> getAllMyUserMachines(Long uid) {

		UserMachineCriteria userMachineCriteria = new UserMachineCriteria();

		userMachineCriteria.or().andUidEqualTo(uid);

		return userMachineMapper.selectByExample(userMachineCriteria);

	}

	@Override
	public UserMachine getUserMachineById(Long id) {

		return userMachineMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean modifyUserMachine(UserMachine userMachine) {

		if(userMachineMapper.updateByPrimaryKeySelective(userMachine)>0){
			return true;
		}

		return false;
	}

	@Override
	public void deleteUserMachineAuthImage(Long id) {

		UserMachineAuthImageCriteria userMachineAuthImageCriteria = new UserMachineAuthImageCriteria();
		userMachineAuthImageCriteria.or().andUser_machine_idEqualTo(id);

		userMachineAuthImageMapper.deleteByExample(userMachineAuthImageCriteria);
	}

	@Override
	public int deleteUserMachine(Long id) {
		return userMachineMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<UserMachineShow> getAllUserMachineShow() {

//		return userMachineMapper.selectByExample(null)
//				.stream()
//				.map(userMachine -> userMachineShowMapper.selectByUserMachineId(userMachine.getId()))
//				.collect(Collectors.toList());
		return userMachineShowMapper.selectByUserMachineId();
	}

	@Override
	public UserMachineShow getUserMachineShowById(Long id) {
		return userMachineShowMapper.selectByUserMachineId(id);
	}

	@Override
	public Boolean saveUserMachine(UserMachine userMachine) {

		if(userMachineMapper.insert(userMachine)>0){
			return  true;
		}
		return  false;
	}

	@Override
	public List<OptionWorkingType> getWorkingTypeByModelId(Long modelId) {

		OptionWorkingTypeMachineModelMappingCriteria optionWorkingTypeMachineModelMappingCriteria=new OptionWorkingTypeMachineModelMappingCriteria();
		optionWorkingTypeMachineModelMappingCriteria.or().andMachine_model_idEqualTo(modelId);

		return optionWorkingTypeMachineModelMappingMapper.selectByExample(optionWorkingTypeMachineModelMappingCriteria)
				.stream()
				.map(optionWorkingTypeMachineModelMappingKey -> {return optionWorkingTypeMapper.selectByPrimaryKey
						(optionWorkingTypeMachineModelMappingKey.getOption_working_type_id());})
				.collect(Collectors.toList());
	}

//	public Boolean deleteImagesbyMahinceId(Long machineId,String imageType){
//		UserMachineAuthImageCriteria userMachineAuthImageCriteria=new UserMachineAuthImageCriteria();
//		userMachineAuthImageCriteria.or().andUser_machine_idEqualTo(machineId).andTypeEqualTo(imageType);
//		userMachineAuthImageMapper.deleteByExample(userMachineAuthImageCriteria);
//		return  true;
//	}

}
