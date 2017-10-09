package cn.bangnongmang.admin.io.swagger.converter;

import cn.bangnongmang.admin.io.swagger.show.MachineShow;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.OptionService;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lucq on 2017/5/24.
 */
@Component("machineConverter")
public class MachineConverter {

    @Autowired
    private MachineService machineService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private OptionConverter optionConverter;
    @Autowired
    private UserMachineService userMachineService;

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
        MachineType machineType = convertToMachinType(userMachineType);
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
                        opD=optionConverter.convertToOptionDetail(optionService.getOptionDetailById(10001L));
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
            }
        }

        mp.setOptionDetials(list);
        modelPropertyList.add(mp);

        mp = new ModelProperty();
        opcm = new cn.bangnongmang.admin.swagger.model.OptionCluster();
        opcm.setId(-1L);
        opcm.setName("作物类型");
        mp.setOptionCluster(opcm);
        list=new ArrayList<>();

        OptionDetail optionDetail=new OptionDetail();
        optionDetail.setId(-1L);
        StringBuffer str=new StringBuffer("");
        List<OptionWorkingType> workingTypeList=userMachineService.getWorkingTypeByModelId(machineModel.getId());

        Iterator workintypeIt=workingTypeList.iterator();

        int i=1;
        while(workintypeIt.hasNext()){
            OptionWorkingType optionWorkingType= (OptionWorkingType) workintypeIt.next();
            str.append(i+":"+optionWorkingType.getCrop_type());
            i++;
        }
        optionDetail.setName(str.toString());
        list.add(optionDetail);
        mp.setOptionDetials(list);
        modelPropertyList.add(mp);

        machineModel.setModelproperties(modelPropertyList);


        return machineModel;
    }

    public MachineType convertToMachinType(UserMachineType userMachineType) {

        if(userMachineType==null){
            return  null;
        }
        MachineType machineType = new MachineType();
        machineType.setId(userMachineType.getId());
        machineType.setName(userMachineType.getType_name());
        machineType.setDescription(userMachineType.getDescripetion());
        return machineType;
    }

}
