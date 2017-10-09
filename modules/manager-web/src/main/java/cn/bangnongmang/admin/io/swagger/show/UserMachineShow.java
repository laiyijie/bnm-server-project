package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.io.swagger.converter.MachineConverter;
import cn.bangnongmang.admin.io.swagger.converter.UserMachineConverter;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.data.combo.domain.OptionClusterDetailInfo;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionWorkingType;
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
 * Created by lucq on 2017/5/26.
 */
@Service("userMachineService")
public class UserMachineShow {


	@Autowired
	private UserMachineService userMachineService;

	@Autowired
	private UserMachineConverter userMachineConverter;

	@Autowired
	private MachineConverter machineConverter;

	@Autowired
	private MachineShow machineShow;

	@Autowired
	private MachineService machineService;

	public List<UserMachineBasic> getAllUserMachine() {

		return userMachineService.getAllUserMachineShow()
								 .stream()
								 .map(userMachine -> userMachineConverter.convertToUserMachineBasic(userMachine))
								 .collect(Collectors.toList());
	}
	public UserMachineDetail getUserMachineById(Long id) {

		cn.bangnongmang.data.combo.domain.UserMachineShow userMachineShow = userMachineService.getUserMachineShowById(id);
		if (userMachineShow == null) {
			return null;
		}
		UserMachineBasic userMachineBasic = userMachineConverter.convertToUserMachineBasic(userMachineShow);
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


		cn.bangnongmang.admin.swagger.model.MachineType machineType=new cn.bangnongmang.admin.swagger.model.MachineType();
		machineType.setId(userMachineShow.getUserMachineType().getId());
		machineType.setName(userMachineShow.getUserMachineType().getType_name());
		machineType.setDescription(userMachineShow.getUserMachineType().getDescripetion());
		userMachineDetail.setMachinetype(machineType);

		List<OptionCluster> propertiesList = userMachineShow.getTypeOptionClusters();
		List<OptionClusterDetailInfo> modelproperties = userMachineShow.getOptions();
		List<OptionClusterDetailInfo> machineproperties = userMachineShow.getUserOptions();
		List<MachineProperty> machineList = new ArrayList<MachineProperty>();

		Iterator it = propertiesList.iterator();
		next:

		while (it.hasNext()) {
			OptionCluster optionCluster = (OptionCluster) it.next();

			Iterator mit = machineproperties.iterator();

			while (mit.hasNext()) {
				OptionClusterDetailInfo optionClusterDetailInfo = (OptionClusterDetailInfo) mit.next();
				if (optionClusterDetailInfo.getId()
										   .equals(optionCluster.getId())) {
					MachineProperty machineProperty = new MachineProperty();

					cn.bangnongmang.admin.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.admin.swagger.model.OptionCluster();
					optionClusterM.setId(optionCluster.getId());
					optionClusterM.setName(optionCluster.getCluster_name());
					optionClusterM.setDescription(optionCluster.getDescription());
					machineProperty.setOptionCluster(optionClusterM);

					cn.bangnongmang.admin.swagger.model.OptionDetail optionDetailM = new cn.bangnongmang.admin.swagger.model.OptionDetail();
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

					cn.bangnongmang.admin.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.admin.swagger.model.OptionCluster();
					optionClusterM.setId(optionCluster.getId());
					optionClusterM.setName(optionCluster.getCluster_name());
					optionClusterM.setDescription(optionCluster.getDescription());
					machineProperty.setOptionCluster(optionClusterM);;
					cn.bangnongmang.admin.swagger.model.OptionDetail optionDetailM = new cn.bangnongmang.admin.swagger.model.OptionDetail();
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

			cn.bangnongmang.admin.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.admin.swagger.model.OptionCluster();
			optionClusterM.setId(optionCluster.getId());
			optionClusterM.setName(optionCluster.getCluster_name());
			optionClusterM.setDescription(optionCluster.getDescription());
			machineProperty.setOptionCluster(optionClusterM);
			machineProperty.setOptionDetail(null);
			machineList.add(machineProperty);
		}

		MachineProperty machineProperty = new MachineProperty();
		cn.bangnongmang.admin.swagger.model.OptionCluster optionClusterM = new cn.bangnongmang.admin.swagger.model.OptionCluster();
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

		machineProperty.setOptionDetail(optionDetail);

		machineList.add(machineProperty);

		machineProperty = new MachineProperty();
		optionClusterM = new cn.bangnongmang.admin.swagger.model.OptionCluster();
		optionClusterM.setId(-1L);
		optionClusterM.setName("作物类型");
		machineProperty.setOptionCluster(optionClusterM);
		optionDetail=new OptionDetail();
		optionDetail.setId(0L);
		StringBuffer str=new StringBuffer("");

		List<OptionWorkingType> workingTypeList=userMachineService.getWorkingTypeByModelId(userMachineDetail.getModelId());
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

		MachineModel machineModel=machineShow.getMachineModelById(userMachineDetail.getModelId());

		userMachineDetail.setModelproperties(machineModel.getModelproperties());

		return userMachineDetail;
	}


	public List<UserMachineDetail> getAllUserMachineByUid(Long uid){
		 return userMachineService.getAllMyUserMachines(uid)
						  .stream()
						  .map(userMachine -> this.getUserMachineById(userMachine.getId()))
						  .collect(Collectors.toList());
	}

}
