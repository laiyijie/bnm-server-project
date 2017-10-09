package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.MachineBusiness;
import cn.bangnongmang.admin.io.swagger.show.MachineShow;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingKey;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.service.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by lucq on 2017/5/24.
 */
@Service("machineBusiniss")
public class MachineBusinessImp implements MachineBusiness {

    @Autowired
    private MachineService machineService;

    @Override
    public Boolean modifyMachineModel(UserMachineModel userMachineModel) {

        if (userMachineModel == null
                || userMachineModel.getId() == null
                || userMachineModel.getState() == null
                || userMachineModel.getModel_number() == null
                || userMachineModel.getModel_brand() == null
                || userMachineModel.getUser_machine_type_id() == null) {
            throw new BusinessException("数据有误，更新失败");
        }

        if(machineService.getMachineTypeById(userMachineModel.getUser_machine_type_id())==null){
            throw new BusinessException("车的类型不存在，更新失败");
        }

        if(!machineService.modifyMachineModel(userMachineModel)){
            throw new BusinessException("该类型不存在，更新失败");
        }

        return  true;
    }

    @Override
    public Boolean saveMachineModel(UserMachineModel userMachineModel) {

        if (userMachineModel == null
                || userMachineModel.getId() != null
                || userMachineModel.getState() == null
                || userMachineModel.getModel_number() == null
                || userMachineModel.getModel_brand() == null
                || userMachineModel.getUser_machine_type_id() == null) {
            throw new BusinessException("数据有误，添加失败");
        }

        if(machineService.getMachineTypeById(userMachineModel.getUser_machine_type_id())==null){
            throw new BusinessException("车的类型不存在，添加失败");
        }


        if(!machineService.saveMachineModel(userMachineModel)){
            throw new BusinessException("服务器暂时出了点问题，请稍候再试");
        }
        return true;
    }

    @Override
    public Boolean deleteMachineModelById(Long id) {

        if(!machineService.getUserMachineByMachineModelId(id).isEmpty()){
            throw new BusinessException("该类型正在被使用，删除失败");
        }

        if(!machineService.deleteMachineModelById(id)){
            throw new BusinessException("该模型不存在，删除失败");
        }
        return true;

    }

    @Override
    public Boolean modifyMachineType(UserMachineType machineType) {

        if (machineType == null
				|| machineType.getId() == null
				|| machineType.getType_name() == null) {
            throw new BusinessException("数据有误，添加失败");
		}

        if(!machineService.modifyMachineType(machineType)){
            throw new BusinessException("该类型不存在，更新失败");
        }

        return true;
    }

    @Override
    public Boolean saveMachineType(UserMachineType machineType) {
        if (machineType == null
                || machineType.getId() != null
                || machineType.getType_name() == null) {
            throw new BusinessException("数据有误，添加失败");
        }
        if(!machineService.saveMachineType(machineType)){
            throw new BusinessException("服务器暂时出了问题，请稍候再试");
        }

        return true;
    }

    @Override
    public Boolean deleteMachineTypeById(Long id) {

        if(!machineService.getMachineModelByWorkingTypeId(id).isEmpty()){
            throw new BusinessException("该类型正在被使用，删除失败");
        }

        if(!machineService.deleteMachineTypeById(id)){
            throw new BusinessException("该类型不存在，删除失败");
        }
        return true;
    }

    @Override
    public Boolean saveMachineModelWorkingTypeRelation(OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey) {

        if(machineService.getMachineModelByWorkingTypeId(optionWorkingTypeMachineModelMappingKey.getOption_working_type_id())==null){
            throw new BusinessException("工作类型不存在，添加失败");
        }
        if(machineService.getUserMachineByMachineModelId(optionWorkingTypeMachineModelMappingKey.getMachine_model_id())==null){
            throw new BusinessException("模型不存在，添加失败");
        }
        if(!machineService.saveMachineMoleWorkingTypeRelation(optionWorkingTypeMachineModelMappingKey)){
            throw new BusinessException("服务器出了问题，请稍候再试");
        }

        return true;
    }

    @Override
    public Boolean deleteMachineModelWorkingTypeRelation(Long machineModelId, Long workingTypeId) {

        if(machineService.isMachineModelWorkingTypeOnUserMachin(machineModelId,workingTypeId)){
            throw new BusinessException("该关系正在使用，删除失败");
        }

        if(!machineService.deleteMachineMoleWorkingTypeRelation(machineModelId,workingTypeId)){
            throw new BusinessException("该关系不存在，删除失败");
        }
        return true;
    }
}
