package cn.bangnongmang.service.service.impl;

import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.UserMachineService;
import cn.bangnongmang.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by lucq on 2017/5/24.
 */
@Service("machineService")
public class MachineServiceImp implements MachineService {

	@Autowired
	private UserMachineModelMapper userMachineModelMapper;

	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;

	@Autowired
	private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;

	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Autowired
	private UserMachineMapper userMachineMapper;

	@Autowired
	private OptionMachineModelMappingMapper optionMachineModelMappingMapper;

	@Autowired
	private OptionDetailMapper optionDetailMapper;

	@Autowired
	private UserMachineOptionDetailMappingMapper userMachineOptionDetailMappingMapper;

	@Override
	public UserMachineModel getMachineModelById(Long id) {

		return userMachineModelMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserMachineModel> getMachineModel() {
		return userMachineModelMapper.selectByExample(null);
	}

	@Override
	public Boolean modifyMachineModel(UserMachineModel userMachineModel) {


//        if (userMachineModelMapper.selectByPrimaryKey(userMachineModel.getId()) == null) {
//            return false;
//        }
//        if (userMachineTypeMapper.selectByPrimaryKey(userMachineModel.getUser_machine_type_id()) == null) {
//            return false;
//        }

		if (userMachineModelMapper.updateByPrimaryKey(userMachineModel) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean saveMachineModel(UserMachineModel userMachineModel) {
//        if (userMachineModel == null
//                || userMachineModel.getId() != null
//                || userMachineModel.getState() == null
//                || userMachineModel.getModel_number() == null
//                || userMachineModel.getModel_brand() == null
//                || userMachineModel.getUser_machine_type_id() == null) {
//            return false;
//        }
//
//        if (userMachineTypeMapper.selectByPrimaryKey(userMachineModel.getUser_machine_type_id()) == null) {
//            return false;
//        }

		if (userMachineModelMapper.insert(userMachineModel) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteMachineModelById(Long Id) {


		return false;
	}

	@Override
	public UserMachineType getMachineTypeById(Long id) {
		return userMachineTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserMachineType> getMachineType() {
		return userMachineTypeMapper.selectByExample(null);
	}

	@Override
	public Boolean modifyMachineType(UserMachineType userMachineType) {

//		if (userMachineType == null
//				|| userMachineType.getId() == null
//				|| userMachineType.getType_name() == null) {
//			return false;
//		}
//		if (userMachineTypeMapper.selectByPrimaryKey(userMachineType.getId()) == null) {
//			return false;
//		}
		if (userMachineTypeMapper.updateByPrimaryKey(userMachineType) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean saveMachineType(UserMachineType userMachineType) {
//		if (userMachineType == null
//				|| userMachineType.getId() != null
//				|| userMachineType.getType_name() == null) {
//			return false;
//		}
		if (userMachineTypeMapper.insert(userMachineType) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteMachineTypeById(Long id) {
		return false;
	}

	@Override
	public List<OptionWorkingType> getWorkingTypeByMachineModelId(Long machineModelId) {

		List<OptionWorkingTypeMachineModelMappingKey> list = optionWorkingTypeMachineModelMappingMapper.selectByExample(null);

		OptionWorkingTypeMachineModelMappingCriteria owtmmc = new OptionWorkingTypeMachineModelMappingCriteria();
		owtmmc.or()
			  .andMachine_model_idEqualTo(machineModelId);

		return optionWorkingTypeMachineModelMappingMapper.selectByExample(owtmmc)
														 .stream()
														 .map(optionWorkingTypeMachineModelMappingKey -> optionWorkingTypeMapper.selectByPrimaryKey
																 (optionWorkingTypeMachineModelMappingKey.getOption_working_type_id()))
														 .collect(Collectors.toList());
	}

	@Override
	public List<UserMachineModel> getMachineModelByWorkingTypeId(Long workingTypeId) {

		OptionWorkingTypeMachineModelMappingCriteria owtmmc = new OptionWorkingTypeMachineModelMappingCriteria();
		owtmmc.or()
			  .andOption_working_type_idEqualTo(workingTypeId);

		return optionWorkingTypeMachineModelMappingMapper.selectByExample(owtmmc)
														 .stream()
														 .map(optionWorkingTypeMachineModelMappingKey -> getMachineModelById(
																 optionWorkingTypeMachineModelMappingKey.getMachine_model_id()))
														 .collect(Collectors.toList());
	}

	@Override
	public Boolean saveMachineMoleWorkingTypeRelation(OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey) {

		if (optionWorkingTypeMachineModelMappingKey == null
				|| optionWorkingTypeMachineModelMappingKey.getMachine_model_id() == null
				|| optionWorkingTypeMachineModelMappingKey.getOption_working_type_id() == null) {
			return false;
		}

		if (optionWorkingTypeMapper.selectByPrimaryKey(optionWorkingTypeMachineModelMappingKey.getOption_working_type_id()) == null
				|| userMachineModelMapper.selectByPrimaryKey(optionWorkingTypeMachineModelMappingKey.getMachine_model_id()) == null) {
			return false;
		}
		optionWorkingTypeMachineModelMappingMapper.insert(optionWorkingTypeMachineModelMappingKey);
		return true;
	}

	@Override
	public Boolean deleteMachineMoleWorkingTypeRelation(Long machineModelId, Long workingtypeId) {
		return false;
	}

	@Override
	public List<UserMachine> getUserMachineByMachineModelId(Long machineModelId) {
		UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
		userMachineCriteria.or()
						   .andMachine_model_idEqualTo(machineModelId);
		return userMachineMapper.selectByExample(userMachineCriteria);
	}

	@Override
	public Boolean isMachineModelWorkingTypeOnUserMachin(Long machineModelId, Long workingTypeId) {

		OptionMachineModelMappingCriteria optionMachineModelMappingCriteria = new OptionMachineModelMappingCriteria();
		optionMachineModelMappingCriteria.or()
										 .andOption_idEqualTo(machineModelId);
		List<Long> ls = optionMachineModelMappingMapper.selectByExample(optionMachineModelMappingCriteria)
													   .stream()
													   .map(optionMachineModelMappingKey -> {
														   return optionMachineModelMappingKey.getModel_id();
													   })
													   .collect(Collectors.toList());
		OptionWorkingTypeMachineModelMappingCriteria optionWorkingTypeMachineModelMappingCriteria = new OptionWorkingTypeMachineModelMappingCriteria();
		optionWorkingTypeMachineModelMappingCriteria.or()
													.andOption_working_type_idEqualTo(workingTypeId);

		List<Long> l = optionWorkingTypeMachineModelMappingMapper.selectByExample(optionWorkingTypeMachineModelMappingCriteria)
																 .stream()
																 .map(optionWorkingTypeMachineModelMappingKey -> {
																	 return optionWorkingTypeMachineModelMappingKey.getMachine_model_id();
																 })
																 .collect(
																		 Collectors.toList());

		ls.retainAll(l);

		if(ls.isEmpty()){
			return  false;
		}

		return  true;

	}

	@Override
	public List<OptionDetail> getAllDetialByMachineModelId(Long machineModelId) {

		OptionMachineModelMappingCriteria optionMachineModelMappingCriteria=new OptionMachineModelMappingCriteria();
		optionMachineModelMappingCriteria.or().andModel_idEqualTo(machineModelId);
		return optionMachineModelMappingMapper.selectByExample(optionMachineModelMappingCriteria)
				.stream()
				.map(optionMachineModelMappingKey ->{ return optionDetailMapper.selectByPrimaryKey(optionMachineModelMappingKey.getOption_id());} )
				.collect(Collectors.toList());
	}

	@Override
	public Boolean saveMachineandOptionDetialRelation(Long machineId, Long optionDetaiId) {

		UserMachineOptionDetailMappingCriteria userMachineOptionDetailMappingCriteria=new UserMachineOptionDetailMappingCriteria();
		userMachineOptionDetailMappingCriteria.or().andOption_detail_idEqualTo(optionDetaiId).andUser_machine_idEqualTo(machineId);

		if(userMachineOptionDetailMappingMapper.countByExample(userMachineOptionDetailMappingCriteria)>0){
			return  false;
		}

		UserMachineOptionDetailMappingKey userMachineOptionDetailMappingKey=new UserMachineOptionDetailMappingKey();
		userMachineOptionDetailMappingKey.setUser_machine_id(machineId);
		userMachineOptionDetailMappingKey.setOption_detail_id(optionDetaiId);

		if(userMachineOptionDetailMappingMapper.insert(userMachineOptionDetailMappingKey)>0){
			return true;
		}

		return false;
	}

	@Override
	public Boolean deleteOptionDetailByMachineId(Long machineId) {

		UserMachineOptionDetailMappingCriteria userMachineOptionDetailMappingCriteria =new UserMachineOptionDetailMappingCriteria();
		userMachineOptionDetailMappingCriteria.or().andUser_machine_idEqualTo(machineId);

		if(userMachineOptionDetailMappingMapper.deleteByExample(userMachineOptionDetailMappingCriteria)>0){
			return true;
		}

		return false;
	}

	@Override
	public List<String> getAllBrandbyTypeId(Long typeId) {

            UserMachineModelCriteria userMachineModelCriteria=new UserMachineModelCriteria();
            userMachineModelCriteria.or().andUser_machine_type_idEqualTo(typeId);

		    List<UserMachineModel> userMachineModelList= userMachineModelMapper.selectByExample(userMachineModelCriteria);
		    List<String> list=new ArrayList<>();
		    Iterator it=userMachineModelList.iterator();
		    while(it.hasNext()){
		    	UserMachineModel userMachineModel= (UserMachineModel) it.next();
		    	if(!list.contains(userMachineModel.getModel_brand())){
		    		list.add(userMachineModel.getModel_brand());
				}
			}
		    return list;
	}

	@Override
	public List<String> getAllNumbyBrand(String brand) {
		UserMachineModelCriteria userMachineModelCriteria=new UserMachineModelCriteria();
		userMachineModelCriteria.or().andModel_brandEqualTo(brand);
		return userMachineModelMapper.selectByExample(userMachineModelCriteria)
				.stream()
				.map(userMachineModel -> {return userMachineModel.getModel_number();})
				.collect(Collectors.toList());
	}

	@Override
	public UserMachineModel getMachineModelbyBrandandName(String brand, String num) {
		UserMachineModelCriteria userMachineModelCriteria=new UserMachineModelCriteria();
		userMachineModelCriteria.or().andModel_brandEqualTo(brand).andModel_numberEqualTo(num);

		List<UserMachineModel> list=userMachineModelMapper.selectByExample(userMachineModelCriteria);
		if(list.size()!=0){
			return list.get(0);
		}

		return null;
	}


}
