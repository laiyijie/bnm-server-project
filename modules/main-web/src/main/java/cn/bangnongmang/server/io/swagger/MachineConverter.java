package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.data.combo.domain.OptionClusterDetailInfo;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.swagger.model.*;
import cn.bangnongmang.server.swagger.model.OptionCluster;
import cn.bangnongmang.server.swagger.model.OptionDetail;
import cn.bangnongmang.server.swagger.model.UserMachine;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.OptionService;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-13.
 */
@Service
public class MachineConverter {


    public UserMachine convertToUserMachine(UserMachineShow userMachineShow) {

        UserMachine userMachine = new UserMachine();
        userMachine.setBrand(userMachineShow.getUserMachineModel() != null
                ? userMachineShow.getUserMachineModel().getModel_brand() : "未知");
        userMachine.setBuyTime(userMachineShow.getUserMachine().getBuy_time());
        userMachine.setFailReason(userMachineShow.getUserMachine().getFailed_reason());
        userMachine.setId(userMachineShow.getUserMachine().getId());
        userMachine.setNumber(userMachineShow.getUserMachineModel().getModel_number());
        userMachine.setState(userMachineShow.getUserMachine().getState());
        userMachine.setType(userMachineShow.getUserMachineType().getType_name());

        userMachine.setSupportWorkingTypes(
                userMachineShow.getOptionWorkingTypes().stream().map
                        (owt -> convertToWorkingType(owt)).collect(
                        Collectors.toList()));
        userMachine.setOptions(userMachineShow.getOptions().stream()
                .map(a -> convertToOption(a)).collect(
                        Collectors.toList()));
        return userMachine;
    }


    public UserMachineBasic convertToUserMachineBasic(UserMachineShow userMachineShow){

        if(userMachineShow==null){
            return null;
        }
        UserMachineBasic userMachineBasic=new UserMachineBasic();

        userMachineBasic.setId(userMachineShow.getUserMachine().getId());
        userMachineBasic.setUid(userMachineShow.getUserMachine().getUid());
        userMachineBasic.setIntegrity(userMachineShow.getUserMachine().getIntegrity());
        userMachineBasic.setModelId(userMachineShow.getUserMachine().getMachine_model_id());

        userMachineBasic.setBrand(userMachineShow.getUserMachineModel().getModel_brand());
        userMachineBasic.setBrandNum(userMachineShow.getUserMachineModel().getModel_number());

        if(userMachineShow.getUserMachine().getState().equals(UserMachineService.STATE_AUTH_PASSED)){
            userMachineBasic.setState(UserMachineBasic.StateEnum.AUTHED);
        }else if(userMachineShow.getUserMachine().getState().equals(UserMachineService.STATE_AUTH_FAILED)){
            userMachineBasic.setState(UserMachineBasic.StateEnum.DENIED);
        }else{
            userMachineBasic.setState(UserMachineBasic.StateEnum.WAITING_AUTH);
        }

        return userMachineBasic;
    }


    public WorkingType convertToWorkingType(OptionWorkingType optionWorkingType) {
        if (optionWorkingType == null) {
            return null;
        }
        WorkingType workingType = new WorkingType();
        workingType.setCropType(optionWorkingType.getCrop_type());
        workingType.setId(optionWorkingType.getId());
        workingType.setWorkingType(optionWorkingType.getWorking_type());
        return workingType;
    }

    public Option convertToOption(OptionClusterDetailInfo oClusterDetailInfo) {
        if (oClusterDetailInfo == null) {
            return null;
        }
        Option option = new Option();
        option.setId(oClusterDetailInfo.getOptionDetail().getId());
        option.setClusterId(oClusterDetailInfo.getOptionCluster().getId());
        option.setClusterName(oClusterDetailInfo.getOptionCluster().getCluster_name());
        option.setName(oClusterDetailInfo.getOptionDetail().getOption_name());
        return option;
    }

    public MachineType convertToMachineType(UserMachineType userMachineType){
        if(userMachineType==null){
            return null;
        }
        MachineType machineType=new MachineType();
        machineType.setId(userMachineType.getId());
        machineType.setName(userMachineType.getType_name());
        machineType.setDescription(userMachineType.getDescripetion());
        return  machineType;
    }

    @Autowired
    private MachineService machineService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private OptionConverter optionConverter;

    public MachineModel convertToMachineModel(UserMachineModel userMachineModel) {

        if (userMachineModel == null) {
            return null;
        }
        MachineModel machineModel = new MachineModel();
        machineModel.setId(userMachineModel.getId());
        machineModel.setBrand(userMachineModel.getModel_brand());
        machineModel.setModelNum(userMachineModel.getModel_number());
        machineModel.setPower(userMachineModel.getModel_power());

        UserMachineType userMachineType = machineService.getMachineTypeById(userMachineModel.getUser_machine_type_id());
        MachineType machineType = convertToMachineType(userMachineType);
        machineModel.setMachineType(machineType);

        List<ModelProperty> modelPropertyList=new ArrayList<>();

        List<cn.bangnongmang.data.domain.OptionCluster> optionClusterList=optionService.getOptionClusterByMachineTypeId(machineType.getId());

        List<cn.bangnongmang.data.domain.OptionDetail> optionDetailList=optionService.getOptionDetailByModelId(machineModel.getId());

        Iterator it = optionClusterList.iterator();

        while(it.hasNext()){

            cn.bangnongmang.data.domain.OptionCluster optionCluster= (cn.bangnongmang.data.domain.OptionCluster)it.next();

            ModelProperty modelProperty=new ModelProperty();
            OptionCluster opC=optionConverter.convertToOptionCluster(optionCluster);
            modelProperty.setOptionCluster(opC);

            Iterator itD=optionDetailList.iterator();

            List<OptionDetail> opdList= new ArrayList<>();

            while(itD.hasNext()){

                cn.bangnongmang.data.domain.OptionDetail optionDetail = (cn.bangnongmang.data.domain.OptionDetail) itD.next();
                if(optionCluster.getId().equals(optionDetail.getCluster_id())){
                    OptionDetail opD=optionConverter.convertToOptionDetail(optionDetail);
                    opdList.add(opD);

                    if(optionCluster.getId().equals(100L)&&optionDetail.getId().equals(10002L)){
                        opD=optionConverter.convertToOptionDetail(optionService.getOptionDetailById(optionDetail.getId()-1));
                        opdList.add(opD);
                    }
                }
            }

            modelProperty.setOptionDetials(opdList);
            modelPropertyList.add(modelProperty);
        }

        ModelProperty mp=new ModelProperty();
        OptionCluster opcm=new OptionCluster();
        opcm.setId(0L);
        opcm.setName("割幅");
        mp.setOptionCluster(opcm);

        String width= userMachineModel.getWidth();
        List<OptionDetail> list=null;
        if(width!=null){
            list=new ArrayList<>();
            String[] strs=userMachineModel.getWidth().split("/");
            for(int i=0;i<strs.length;i++){
                OptionDetail opdm=new OptionDetail();
                opdm.setId(0L);
                opdm.setName(strs[i]);
                list.add(opdm);
            }
        }

        mp.setOptionDetials(list);
        modelPropertyList.add(mp);

        machineModel.setModelproperties(modelPropertyList);

        return machineModel;
    }

}
