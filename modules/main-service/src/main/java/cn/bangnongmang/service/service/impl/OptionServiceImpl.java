package cn.bangnongmang.service.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.service.service.OptionService;

@Service("S_OptionService")
public class OptionServiceImpl implements OptionService {


	@Autowired
	private OptionDetailMapper optionDetailMapper;

	@Autowired
	private OptionClusterMapper optionClusterMapper;

	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Autowired
	private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;

	@Autowired
	private OptionOrderMappingMapper optionOrderMappingMapper;

	@Autowired
	private OptionMachineModelMappingMapper optionMachineModelMappingMapper;

	@Autowired
	private OrderFarmerMapper orderFarmerMapper;

	@Autowired
	private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;

	@Autowired

	private MachineTypeOptionClusterMappingMapper machineTypeOptionClusterMappingMapper;


	@Override
	public boolean addOptionOrderMapping(String orderId, Long optionDetailId) {
		OptionOrderMappingKey key = new OptionOrderMappingKey();
		key.setOption_id(optionDetailId);
		key.setOrder_id(orderId);
		if (optionOrderMappingMapper.insert(key) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public OptionOrderMappingKey getOptionOrderMapping(String orderId, Long optionId) {

		OptionOrderMappingCriteria optionOrderMappingCriteria = new OptionOrderMappingCriteria();
		optionOrderMappingCriteria.or()
								  .andOption_idEqualTo(optionId)
								  .andOrder_idEqualTo(orderId);

		List<OptionOrderMappingKey> optionOrderMappingKeys = optionOrderMappingMapper
				.selectByExample(optionOrderMappingCriteria);

		if (optionOrderMappingKeys.isEmpty()) {
			return null;
		}
		return optionOrderMappingKeys.get(0);

	}

	@Override
	public OptionDetail getOptionDetailById(Long id) {

		return optionDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean deleteOptionDetailById(Long id) {


//		OptionOrderMappingCriteria optionOrderMappingCriteria = new OptionOrderMappingCriteria();
//		optionOrderMappingCriteria.or().andOption_idEqualTo(id);
//
//		OptionMachineModelMappingCriteria optionMachineModelMappingCriteria = new OptionMachineModelMappingCriteria();
//		optionMachineModelMappingCriteria.or().andOption_idEqualTo(id);
//
//		if (optionOrderMappingMapper.countByExample(optionOrderMappingCriteria) > 0) {
//			throw new ServiceLayerExeption("有订单使用该选项，删除失败");
//		}
//
//		if (optionMachineModelMappingMapper.countByExample(optionMachineModelMappingCriteria) > 0) {
//			throw new ServiceLayerExeption("有农机使用该选项，删除失败");
//		}


		if (optionDetailMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}

		return false;

	}

	@Override
	public Boolean modifyOptionDetail(OptionDetail optionDetail) {

//		if (optionDetail == null || optionDetail.getOption_name() == null || optionDetail.getId() == null)
//			throw new ServiceLayerExeption("数据有误，更新失败");

		if (optionDetailMapper.updateByPrimaryKey(optionDetail) > 0) {
			return true;
		}

		return false;

	}

	@Override
	public Boolean saveOptionDetail(OptionDetail optionDetail) {

//		if (optionDetail == null || optionDetail.getId() != null || optionDetail.getOption_name() == null) {
//			throw new ServiceLayerExeption("数据有误，添加失败");
//		}
//
//		OptionCluster optionCluster = optionClusterMapper.selectByPrimaryKey(optionDetail.getCluster_id());
//		if (optionCluster == null){
//			throw new ServiceLayerExeption("选项集不存在，添加失败");
//		}

		if (optionDetailMapper.insert(optionDetail) > 0) {

			return true;
		}

		return false;
	}

	@Override
	public OptionCluster getOptionClusterById(Long id) {

		return optionClusterMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<OptionCluster> getOptionCluster() {

		return optionClusterMapper.selectByExample(null);

	}

	@Override
	public List<OptionDetail> getOptionDetailsByClusterId(Long clusterId) {

		OptionDetailCriteria opCriteria = new OptionDetailCriteria();
		opCriteria.or()
				  .andCluster_idEqualTo(clusterId);

		return optionDetailMapper.selectByExample(opCriteria);

	}

	@Override
	public Boolean modifyOptionCluster(OptionCluster optionCluster) {

//		if (optionCluster == null || optionCluster.getId() == null || optionCluster.getCluster_name() == null) {
//			return false;
//		}

		if (optionClusterMapper.updateByPrimaryKey(optionCluster) > 0) {
			return true;
		}

		return false;
	}


	@Override
	public Boolean saveOptionCluster(OptionCluster optionCluster) {

//		if (optionCluster == null || optionCluster.getId() != null || optionCluster.getCluster_name() == null) {
//			return false;
//		}

		if (optionClusterMapper.insert(optionCluster) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean deleteOptionClusterById(Long id) {

//		OptionClusterWorkingTypeMappingCriteria optionClusterWorkingTypeMappingCriteria=new OptionClusterWorkingTypeMappingCriteria();
//		if(optionClusterWorkingTypeMappingMapper.countByExample(optionClusterWorkingTypeMappingCriteria)>0){
//			return false;
//		}

		if (optionClusterMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public OptionWorkingType getWorkingTypeById(Long id) {

		return optionWorkingTypeMapper.selectByPrimaryKey(id);

	}

	@Override
	public List<OptionWorkingType> getWorkingType() {

		return optionWorkingTypeMapper.selectByExample(null);

	}

	@Override
	public List<OptionWorkingType> getWorkingTypeByOptionClusterId(Long optionClusterId) {

		OptionClusterWorkingTypeMappingCriteria optionClusterWorkingTypeMappingCriteria = new OptionClusterWorkingTypeMappingCriteria();
		optionClusterWorkingTypeMappingCriteria.or()
											   .andCluster_idEqualTo(optionClusterId);
		return optionClusterWorkingTypeMappingMapper.selectByExample(optionClusterWorkingTypeMappingCriteria)
													.stream()
													.map(optionClusterWorkingTypeMappingKey -> optionWorkingTypeMapper.selectByPrimaryKey
															(optionClusterWorkingTypeMappingKey.getCluster_id()))
													.collect(Collectors.toList());
	}

	@Override
	public Boolean modifyOptionWorkingType(OptionWorkingType optionWorkingType) {

//		if (optionWorkingType == null || optionWorkingType.getId() == null || optionWorkingType.getCrop_type() == null || optionWorkingType.getWorking_type()
//				== null) {
//			return false;
//		}

		if (optionWorkingTypeMapper.updateByPrimaryKey(optionWorkingType) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean saveOptionWorkingType(OptionWorkingType optionWorkingType) {
//		if (optionWorkingType == null || optionWorkingType.getId() != null || optionWorkingType.getCrop_type() == null || optionWorkingType.getWorking_type()
//				== null) {
//			return false;
//		}
//
		if (optionWorkingTypeMapper.insert(optionWorkingType) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean deleteOptionWorkingType(Long id) {

		if (optionWorkingTypeMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}
		return false;

	}

	@Override
	public List<OptionCluster> getOptionClusterByWorkingTypeId(Long workingTypeId) {

		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
		criteria.or()
				.andWorking_type_idEqualTo(workingTypeId);

		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingTypeMappingMapper.selectByExample(criteria);

		return list.stream()
				   .map(optionClusterWorkingTypeMappingKey -> optionClusterMapper.selectByPrimaryKey(
						   optionClusterWorkingTypeMappingKey.getCluster_id()))
				   .collect(Collectors.toList());
	}

	@Override
	public Boolean saveWorkingTypeOptionClusterRelation(OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey) {

//		if (optionClusterWorkingTypeMappingKey.getWorking_type_id() == null || optionClusterWorkingTypeMappingKey.getCluster_id() == null) {
//			return false;
//		}
//		if (optionClusterMapper.selectByPrimaryKey(optionClusterWorkingTypeMappingKey.getCluster_id()) == null) {
//			return false;
//		}
//		if (optionWorkingTypeMapper.selectByPrimaryKey(optionClusterWorkingTypeMappingKey.getWorking_type_id()) == null) {
//			return false;
//		}
//
//		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
//		criteria.or()
//				.andWorking_type_idEqualTo(optionClusterWorkingTypeMappingKey.getWorking_type_id())
//				.andCluster_idEqualTo(optionClusterWorkingTypeMappingKey.getCluster_id());
//		if (optionClusterWorkingTypeMappingMapper.countByExample(criteria)==1) {
//
//			optionClusterWorkingTypeMappingMapper.insert(optionClusterWorkingTypeMappingKey);
//
//		}

		if (optionClusterWorkingTypeMappingMapper.insert(optionClusterWorkingTypeMappingKey) > 0) {
			return true;
		}

		return false;

	}

	@Override
	public Boolean deleteWorkingTypeOptionClusterRelation(Long workingtypeId, Long optionClusterId) {

//		if (workingtypeId == null || optionClusterId == null) {
//			return false;
//		}

		OptionClusterWorkingTypeMappingKey ocwtmk = new OptionClusterWorkingTypeMappingKey();

		ocwtmk.setCluster_id(optionClusterId);
		ocwtmk.setWorking_type_id(workingtypeId);

		if (optionClusterWorkingTypeMappingMapper.deleteByPrimaryKey(ocwtmk) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean optionDetailHasOrderOrMachine(Long optionDetialId) {

		OptionOrderMappingCriteria optionOrderMappingCriteria = new OptionOrderMappingCriteria();
		optionOrderMappingCriteria.or()
								  .andOption_idEqualTo(optionDetialId);

		if (optionOrderMappingMapper.countByExample(optionOrderMappingCriteria) > 0) {
			return true;
		}

		OptionMachineModelMappingCriteria optionMachineModelMappingCriteria = new OptionMachineModelMappingCriteria();
		optionMachineModelMappingCriteria.or()
								  .andOption_idEqualTo(optionDetialId);

		if (optionMachineModelMappingMapper.countByExample(optionMachineModelMappingCriteria) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean workingTypeHasOrderOrMachineModel(Long workingTypeId) {

		OrderFarmerCriteria orderFarmerCriteria = new OrderFarmerCriteria();
		orderFarmerCriteria.or()
						   .andWorking_type_idEqualTo(workingTypeId);
		if (orderFarmerMapper.countByExample(orderFarmerCriteria) > 0) {
			return true;
		}

		OptionWorkingTypeMachineModelMappingCriteria optionWorkingTypeMachineModelMappingCriteria = new OptionWorkingTypeMachineModelMappingCriteria();
		optionWorkingTypeMachineModelMappingCriteria.or()
													.andOption_working_type_idEqualTo(workingTypeId);
		if (optionWorkingTypeMachineModelMappingMapper.countByExample(optionWorkingTypeMachineModelMappingCriteria) > 0) {
			return true;
		}

		return false;

	}

	@Override
	public Boolean isExistWorkingTypeOptionClusterRelation(OptionClusterWorkingTypeMappingKey optionWorkingTypeMachineModelMappingKey) {

		OptionClusterWorkingTypeMappingCriteria optionClusterWorkingTypeMappingCriteria = new OptionClusterWorkingTypeMappingCriteria();
		optionClusterWorkingTypeMappingCriteria.or()
											   .andWorking_type_idEqualTo(optionWorkingTypeMachineModelMappingKey.getWorking_type_id())
											   .andCluster_idEqualTo(optionWorkingTypeMachineModelMappingKey.getCluster_id());

		if (optionClusterWorkingTypeMappingMapper.selectByExample(optionClusterWorkingTypeMappingCriteria).isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public Boolean isWorkingTypeOptionClusterRelationUsedOnOrderMachine(Long workingTypeId, Long optionClusterId) {

		OptionDetailCriteria optionDetailCriteria = new OptionDetailCriteria();
		optionDetailCriteria.or()
							.andCluster_idEqualTo(optionClusterId);


		List<Long> list = optionDetailMapper.selectByExample(optionDetailCriteria)
											.stream()
											.map(optionDetail -> {
												return optionDetail.getId();
											})
											.collect(Collectors.toList());


		OptionOrderMappingCriteria optionOrderMappingCriteria = new OptionOrderMappingCriteria();
		optionOrderMappingCriteria.or()
								  .andOption_idIn(list);

		List<String> li = optionOrderMappingMapper.selectByExample(optionOrderMappingCriteria)
												  .stream()
												  .map(optionOrderMappingKey -> {
													  return optionOrderMappingKey.getOrder_id();
												  })
												  .collect(Collectors.toList());
		Set<String> orderSet = new HashSet<>();
		orderSet.addAll(li);
		li.addAll(orderSet);

		//optionOrderMappingMapper;

		OrderFarmerCriteria orderFarmerCriteria = new OrderFarmerCriteria();
		orderFarmerCriteria.or()
						   .andWorking_type_idEqualTo(workingTypeId);

		List<String> l = orderFarmerMapper.selectByExample(orderFarmerCriteria)
										  .stream()
										  .map(orderFarmer -> {
											  return orderFarmer.getOrder_id();
										  })
										  .collect(Collectors.toList());
		l.retainAll(li);

		if (!l.isEmpty()) {
			return true;
		}

		return false;
	}

	@Override
	public List<OptionCluster> getOptionClusterByMachineTypeId(Long machineTypeId) {

		MachineTypeOptionClusterMappingCriteria machineTypeOptionClusterMappingCriteria = new MachineTypeOptionClusterMappingCriteria();
		machineTypeOptionClusterMappingCriteria.or().andMachine_type_idEqualTo(machineTypeId);
		return machineTypeOptionClusterMappingMapper.selectByExample(machineTypeOptionClusterMappingCriteria)
				.stream()
				.map(machineTypeOptionClusterMappingKey -> {return optionClusterMapper.selectByPrimaryKey(machineTypeOptionClusterMappingKey
						.getOption_cluster_id());})
				.collect(Collectors.toList());

	}

	@Override
	public List<OptionDetail> getOptionDetailByModelId(Long ModelId) {
		OptionMachineModelMappingCriteria optionMachineModelMappingCriteria=new OptionMachineModelMappingCriteria();
		optionMachineModelMappingCriteria.or().andModel_idEqualTo(ModelId);


		return optionMachineModelMappingMapper.selectByExample(optionMachineModelMappingCriteria)
				.stream()
				.map(optionMachineModelMappingKey -> {return optionDetailMapper.selectByPrimaryKey(optionMachineModelMappingKey.getOption_id());})
				.collect(Collectors.toList());
	}

}
