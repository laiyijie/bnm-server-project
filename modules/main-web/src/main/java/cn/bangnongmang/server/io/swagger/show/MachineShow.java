package cn.bangnongmang.server.io.swagger.show;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.OptionClusterDetailInfo;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.io.swagger.MachineConverter;
import cn.bangnongmang.server.swagger.model.*;
import cn.bangnongmang.server.swagger.model.OptionCluster;
import cn.bangnongmang.server.swagger.model.OptionDetail;
import cn.bangnongmang.server.swagger.model.UserMachine;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-13.
 */
@Service
public class MachineShow {

    @Autowired
    private MachineConverter machineConverter;
    @Autowired
    private UserMachineShowMapper userMachineShowMapper;
    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;
    @Autowired
    private MachineService machineService;
    @Autowired UserMachineService userMachineService;


    public List<UserMachine> getUserAuthMachines(Long uid) {
        return userMachineShowMapper.selectByUid(uid)
                                    .stream()
                                    .filter(userMachineShow -> UserMachineService.STATE_AUTH_PASSED
                                            .equals(userMachineShow.getUserMachine()
                                                                   .getState()))
                                    .map(userMachineShow ->
                                            machineConverter.convertToUserMachine(userMachineShow))
                                    .collect(Collectors.toList());
    }

    public List<UserMachine> getUserMachines(Long uid) {
        return userMachineShowMapper.selectByUid(uid)
                                    .stream()
                                    .map(userMachineShow ->
                                            machineConverter.convertToUserMachine(userMachineShow))
                                    .collect(Collectors.toList
                                            ());
    }

    public Double getUserAvailableMachineWidth(String orderId, Long uid) {
        final Double DEFAULT_WIDTH = 2.0;
        List<UserMachineShow> userMachineShows = userMachineShowMapper.selectByUid(uid);
        OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId);

        if (orderFarmerInfoShow == null) {
            return DEFAULT_WIDTH;
        }

        if (userMachineShows.isEmpty()) {
            return DEFAULT_WIDTH;
        }
        if (orderFarmerInfoShow.getOptionWorkingType() == null) {
            return DEFAULT_WIDTH;
        }
        Long workingTypeId = orderFarmerInfoShow.getOptionWorkingType()
                                                .getId();
        Set<Long> orderOptions = orderFarmerInfoShow.getOptions()
                                                    .stream()
                                                    .map(a -> a.getId())
                                                    .collect(Collectors.toSet());

        for (UserMachineShow userMachineShow : userMachineShows) {
            Set<Long> machineOptions = userMachineShow.getOptions()
                                                      .stream()
                                                      .map(a -> a.getId())
                                                      .collect(Collectors.toSet());
            Set<Long> machineWorkingType = userMachineShow.getOptionWorkingTypes()
                                                          .stream()
                                                          .map(a -> a.getId())
                                                          .collect(Collectors.toSet());
            if (machineOptions.containsAll(orderOptions) && machineWorkingType.contains(workingTypeId)) {

                if (userMachineShow.getUserMachineModel() != null
                        && userMachineShow.getUserMachineModel()
                                          .getModel_width() != null) {
                    return userMachineShow.getUserMachineModel()
                                          .getModel_width();
                }
            }
        }
        return DEFAULT_WIDTH;
    }

    public List<MachineType> getAllWorkingType(){
        return machineService.getMachineType()
                .stream()
                .map(userMachineType -> machineConverter.convertToMachineType(userMachineType))
                .collect(Collectors.toList());

    }

    public List<String> getAllBrandbyTypeId(Long typeId){

        return  machineService.getAllBrandbyTypeId(typeId);

    }

    public List<String> getAllNumbyBrandName(String name){

        return machineService.getAllNumbyBrand(name);
    }

    public MachineModel getMachineModelById(Long id) {

        return machineConverter.convertToMachineModel(machineService.getMachineModelById(id));

    }
    public MachineModel getMachineModelByBrandandNum(String brand,String num){

        return machineConverter.convertToMachineModel(machineService.getMachineModelbyBrandandName(brand,num));
    }

    public UserMachineDetail getUserMachineById(Long id) {

        cn.bangnongmang.data.combo.domain.UserMachineShow userMachineShow = userMachineService.getUserMachineShowById(id);
        if (userMachineShow == null) {
            return null;
        }
        UserMachineBasic userMachineBasic = machineConverter.convertToUserMachineBasic(userMachineShow);
        UserMachineDetail userMachineDetail = new UserMachineDetail();
        userMachineDetail.setId(id);
        userMachineDetail.setBrand(userMachineBasic.getBrand());
        userMachineDetail.setBrandNum(userMachineBasic.getBrandNum());
        userMachineDetail.setTel(userMachineBasic.getTel());
        userMachineDetail.setUsername(userMachineBasic.getUsername());
        userMachineDetail.setUid(userMachineBasic.getUid());
        userMachineDetail.setIntegrity(userMachineBasic.getIntegrity());
        userMachineDetail.setModelId(userMachineBasic.getModelId());

        userMachineDetail.setBuytime(userMachineShow.getUserMachine()
                                                    .getBuy_time());
        userMachineDetail.setReason(userMachineShow.getUserMachine()
                                                   .getFailed_reason());
        userMachineDetail.setCutnum(userMachineShow.getUserMachineModel()
                                                   .getCut_num());
        userMachineDetail.setPower(userMachineShow.getUserMachineModel()
                                                  .getModel_power());
        userMachineDetail.setBuytime(userMachineShow.getUserMachine()
                                                    .getBuy_time());

        userMachineDetail.setAuthImage(userMachineShow.getUserMachineAuthImages()
                                                      .stream()
                                                      .map(userMachineAuthImage -> {
                                                          return userMachineAuthImage.getUrl();
                                                      })
                                                      .collect(Collectors.toList()));
        userMachineDetail.setReason(userMachineShow.getUserMachine()
                                                   .getFailed_reason());
        userMachineDetail.setState(userMachineBasic.getState());


        cn.bangnongmang.server.swagger.model.MachineType machineType=new cn.bangnongmang.server.swagger.model.MachineType();
        machineType.setId(userMachineShow.getUserMachineType().getId());
        machineType.setName(userMachineShow.getUserMachineType().getType_name());
        machineType.setDescription(userMachineShow.getUserMachineType().getDescripetion());
        userMachineDetail.setMachinetype(machineType);

        List<cn.bangnongmang.data.domain.OptionCluster> propertiesList = userMachineShow.getTypeOptionClusters();
        List<OptionClusterDetailInfo> modelproperties = userMachineShow.getOptions();
        List<OptionClusterDetailInfo> machineproperties = userMachineShow.getUserOptions();
        List<MachineProperty> machineList = new ArrayList<MachineProperty>();

        Iterator it = propertiesList.iterator();
        next:

        while (it.hasNext()) {
            cn.bangnongmang.data.domain.OptionCluster optionCluster = (cn.bangnongmang.data.domain.OptionCluster) it.next();

            Iterator mit = machineproperties.iterator();

            while (mit.hasNext()) {
                OptionClusterDetailInfo optionClusterDetailInfo = (OptionClusterDetailInfo) mit.next();
                if (optionClusterDetailInfo.getId()
                                           .equals(optionCluster.getId())) {
                    MachineProperty machineProperty = new MachineProperty();

                    cn.bangnongmang.server.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.server.swagger.model.OptionCluster();
                    optionClusterM.setId(optionCluster.getId());
                    optionClusterM.setName(optionCluster.getCluster_name());
                    optionClusterM.setDescription(optionCluster.getDescription());
                    machineProperty.setOptionCluster(optionClusterM);

                    cn.bangnongmang.server.swagger.model.OptionDetail optionDetailM = new cn.bangnongmang.server.swagger.model.OptionDetail();
                    optionDetailM.setId(optionClusterDetailInfo.getOptionDetail()
                                                               .getId());
                    optionDetailM.setName(optionClusterDetailInfo.getOptionDetail()
                                                                 .getOption_name());
                    optionDetailM.setDescprition(optionClusterDetailInfo.getOptionDetail()
                                                                        .getDescription());
                    machineProperty.setOptionDetail(optionDetailM);
                    machineList.add(machineProperty);
                    continue next;
                }
            }

            mit = modelproperties.iterator();
            while (mit.hasNext()) {

                OptionClusterDetailInfo optionClusterDetailInfo = (OptionClusterDetailInfo) mit.next();

                if (optionClusterDetailInfo.getId()
                                           .equals(optionCluster.getId())) {
                    MachineProperty machineProperty = new MachineProperty();

                    cn.bangnongmang.server.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.server.swagger.model.OptionCluster();
                    optionClusterM.setId(optionCluster.getId());
                    optionClusterM.setName(optionCluster.getCluster_name());
                    optionClusterM.setDescription(optionCluster.getDescription());
                    machineProperty.setOptionCluster(optionClusterM);;
                    cn.bangnongmang.server.swagger.model.OptionDetail optionDetailM = new cn.bangnongmang.server.swagger.model.OptionDetail();
                    optionDetailM.setId(optionClusterDetailInfo.getOptionDetail()
                                                               .getId());
                    optionDetailM.setName(optionClusterDetailInfo.getOptionDetail()
                                                                 .getOption_name());
                    optionDetailM.setDescprition(optionClusterDetailInfo.getOptionDetail()
                                                                        .getDescription());
                    machineProperty.setOptionDetail(optionDetailM);
                    machineList.add(machineProperty);
                    continue next;
                }
            }

            MachineProperty machineProperty = new MachineProperty();

            cn.bangnongmang.server.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.server.swagger.model.OptionCluster();
            optionClusterM.setId(optionCluster.getId());
            optionClusterM.setName(optionCluster.getCluster_name());
            optionClusterM.setDescription(optionCluster.getDescription());
            machineProperty.setOptionCluster(optionClusterM);
            machineProperty.setOptionDetail(null);
            machineList.add(machineProperty);
        }

        MachineProperty machineProperty = new MachineProperty();
        cn.bangnongmang.server.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.server.swagger.model.OptionCluster();
        optionClusterM.setId(0L);
        optionClusterM.setName("割幅");
        //optionClusterM.setDescription("Id为0的optionCluster是假的optionCluster,不要尝试去服务器获取详细信息");
        machineProperty.setOptionCluster(optionClusterM);
        OptionDetail optionDetail=new OptionDetail();
        optionDetail.setId(0L);
        if(userMachineShow.getUserMachine().getWidth()==null){
            optionDetail.setName("");
        }else{
            optionDetail.setName(userMachineShow.getUserMachine().getWidth().toString());
        }
        //optionDetail.setDescprition("Id为0的optionDetail是假的optionDetail,不要尝试去服务器获取详细信息");
        machineProperty.setOptionDetail(optionDetail);

        machineList.add(machineProperty);

        machineProperty = new MachineProperty();
        optionClusterM = new cn.bangnongmang.server.swagger.model.OptionCluster();
        optionClusterM.setId(-1L);
        optionClusterM.setName("作物类型");
        machineProperty.setOptionCluster(optionClusterM);
        optionDetail=new OptionDetail();
        optionDetail.setId(0L);
        StringBuffer str=new StringBuffer("");

        List<OptionWorkingType> workingTypeList=machineService.getWorkingTypeByMachineModelId(userMachineDetail.getModelId());
        Iterator workintypeIt=workingTypeList.iterator();
        int i=1;
        while(workintypeIt.hasNext()){
            OptionWorkingType optionWorkingType= (OptionWorkingType) workintypeIt.next();
            str.append(i+":"+optionWorkingType.getCrop_type());
            i++;
        }
        optionDetail.setName(str.toString());

        machineProperty.setOptionDetail(optionDetail);

        machineList.add(machineProperty);

        userMachineDetail.setMachineproperties(machineList);

        MachineModel machineModel=getMachineModelById(userMachineDetail.getModelId());

        userMachineDetail.setModelproperties(machineModel.getModelproperties());

        return userMachineDetail;

    }
}
