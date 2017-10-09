package cn.bangnongmang.service.service;

import cn.bangnongmang.data.domain.*;

import java.util.List;

/**
 * Created by lucq on 2017/5/24.
 */
public interface MachineService {

    UserMachineModel getMachineModelById(Long id);
    List<UserMachineModel> getMachineModel();
    Boolean modifyMachineModel(UserMachineModel userMachineModel);
    Boolean saveMachineModel(UserMachineModel userMachineModel);
    Boolean deleteMachineModelById(Long Id);

    UserMachineType  getMachineTypeById(Long id);
    List<UserMachineType> getMachineType();
    Boolean modifyMachineType(UserMachineType MachineType);
    Boolean saveMachineType(UserMachineType MachineType);
    Boolean deleteMachineTypeById(Long id);

    List<OptionWorkingType> getWorkingTypeByMachineModelId(Long machineModelId);
    List<UserMachineModel> getMachineModelByWorkingTypeId(Long workingTypeId);
    Boolean saveMachineMoleWorkingTypeRelation(OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey);
    Boolean deleteMachineMoleWorkingTypeRelation(Long machineModelId,Long workingtypeId);

    List<UserMachine>  getUserMachineByMachineModelId(Long machineModelId);

    Boolean isMachineModelWorkingTypeOnUserMachin(Long machineModelId, Long workingTypeId);

    List<OptionDetail> getAllDetialByMachineModelId(Long machineModelId);

    Boolean saveMachineandOptionDetialRelation(Long machineId,Long optionDetaiId);
    Boolean deleteOptionDetailByMachineId(Long machineId);

    List<String> getAllBrandbyTypeId(Long typeId);
    List<String> getAllNumbyBrand(String brand);

    UserMachineModel getMachineModelbyBrandandName(String brand,String num);



}
