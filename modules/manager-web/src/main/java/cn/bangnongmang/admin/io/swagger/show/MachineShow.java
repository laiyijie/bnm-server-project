package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.io.swagger.converter.MachineConverter;
import cn.bangnongmang.admin.io.swagger.converter.OptionConverter;
import cn.bangnongmang.admin.swagger.model.MachineModel;
import cn.bangnongmang.admin.swagger.model.MachineType;
import cn.bangnongmang.admin.swagger.model.WorkingType;
import cn.bangnongmang.service.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lucq on 2017/5/24.
 */

@Service("machineShow")
public class MachineShow {

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineConverter machineConverter;

    @Autowired
    private OptionConverter optionConverter;

    public List<MachineModel> getMachineModel() {

        return machineService.getMachineModel()
                             .stream()
                             .map(userMachineModel -> machineConverter.convertToMachineModel(userMachineModel))
                             .collect(Collectors.toList());

    }

    public MachineModel getMachineModelById(Long id) {

        return machineConverter.convertToMachineModel(machineService.getMachineModelById(id));

    }

    public List<MachineType> getMachineType() {

        return machineService.getMachineType()
                             .stream()
                             .map(userMachineType -> machineConverter.convertToMachinType(userMachineType))
                             .collect(Collectors.toList());

    }

    public MachineType getMachineTypeById(Long id) {

        return machineConverter.convertToMachinType(machineService.getMachineTypeById(id));

    }

    public List<MachineModel> getMachineModelByWorkingTypeId(Long Id){

        return machineService.getMachineModelByWorkingTypeId(Id)
                .stream()
                .map(userModelMachine->machineConverter.convertToMachineModel(userModelMachine))
                .collect(Collectors.toList());
    }

    public List<WorkingType> getWorkingTypeByMachineModelId(Long Id){

        return machineService.getWorkingTypeByMachineModelId(Id)
                             .stream()
                             .map(workingType->optionConverter.convertToWorkingType(workingType))
                             .collect(Collectors.toList());
    }
}
