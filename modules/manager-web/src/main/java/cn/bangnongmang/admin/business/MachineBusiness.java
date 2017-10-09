package cn.bangnongmang.admin.business;

;
import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingKey;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;

/**
 * Created by lucq on 2017/5/24.
 */
public interface MachineBusiness {

    Boolean modifyMachineModel(UserMachineModel userMachineModel);
    Boolean saveMachineModel(UserMachineModel userMachineModel);
    Boolean deleteMachineModelById(Long id);


    Boolean modifyMachineType(UserMachineType machineType);
    Boolean saveMachineType(UserMachineType machineType);
    Boolean deleteMachineTypeById(Long id);

    Boolean saveMachineModelWorkingTypeRelation(OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey);
    Boolean deleteMachineModelWorkingTypeRelation(Long machineModelId,Long workingTypeId);


}
