package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.OptionBussiness;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.service.service.impl.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lucq on 2017/5/22.
 */
@Service("optionBussiness")
public class OptionBussinessImpl implements OptionBussiness {

	@Autowired
	private OptionServiceImpl optionService;

	@Override
	public Boolean deleteOtpionDetailById(Long id) {

		if (optionService.optionDetailHasOrderOrMachine(id)) {
			throw new BusinessException("该选项正在被使用，删除失败");
		}

		if (!optionService.deleteOptionDetailById(id)) {
			throw new BusinessException("找不到该选项，删除失败");
		}
		return true;
	}

	@Override
	public Boolean modifyOptionDetail(OptionDetail optionDetail) {

		if (optionDetail == null || optionDetail.getOption_name() == null || optionDetail.getId() == null) {
			throw new BusinessException("信息有误，更新失败");
		}

		if (!optionService.modifyOptionDetail(optionDetail)) {

			throw new BusinessException("找不到该选项，删除失败");
		}

		return true;
	}

	@Override
	public Boolean saveOptionDetail(OptionDetail optionDetail) {


		if (optionDetail == null || optionDetail.getId() != null || optionDetail.getOption_name() == null) {
			throw new BusinessException("数据有误，添加失败");
		}

		if (optionService.getOptionClusterById(optionDetail.getCluster_id()) == null) {
			throw new BusinessException("选项集不存在，添加失败");
		}

		if (!optionService.saveOptionDetail(optionDetail)) {
			throw new BusinessException("服务器暂时有点问题，请稍后重试");
		}

		return true;

	}

	@Override
	public Boolean deleteOptionCkusterById(Long id) {

		if (!optionService.getOptionDetailsByClusterId(id)
						  .isEmpty()) {
			throw new BusinessException("该选项集有子选项，删除失败");
		}

		if (!optionService.getWorkingTypeByOptionClusterId(id)
						  .isEmpty()) {

			throw new BusinessException("该选项集正在被使用，删除失败");
		}

		if (!optionService.deleteOptionClusterById(id)) {
			throw new BusinessException("该选项集不存在，删除失败");
		}
		return true;
	}

	@Override
	public Boolean modifyOptionCluster(OptionCluster optionCluster) {

		if (optionCluster == null || optionCluster.getId() == null || optionCluster.getCluster_name() == null) {
			throw new BusinessException("数据有误，更新失败");
		}

		if (!optionService.modifyOptionCluster(optionCluster)) {
			throw new BusinessException("选项集不存在，更新失败");
		}

		return true;

	}

	@Override
	public Boolean saveOptionCluster(OptionCluster optionCluster) {

		if (optionCluster == null || optionCluster.getId() != null || optionCluster.getCluster_name() == null) {
			throw new BusinessException("数据有误，添加失败");
		}

		if (!optionService.saveOptionCluster(optionCluster)) {
			throw new BusinessException("服务器暂时有点问题，请稍后重试");
		}

		return true;
	}

	@Override
	public Boolean deleteOptionWorkingTypeById(Long id) {

		if (!optionService.getOptionClusterByWorkingTypeId(id)
						  .isEmpty() || optionService.workingTypeHasOrderOrMachineModel(id)) {
			throw new BusinessException("该工作类型正在被使用，删除失败");
		}

		if (!optionService.deleteOptionWorkingType(id)) {
			throw new BusinessException("找不到工作类型，删除失败");
		}
		return true;
	}

	@Override
	public Boolean modifyOptionWorkingType(OptionWorkingType optionWorkingType) {

		if (optionWorkingType == null || optionWorkingType.getId() == null || optionWorkingType.getCrop_type() == null || optionWorkingType.getWorking_type()
				== null) {
			throw new BusinessException("信息有误，更新失败");
		}

		if (!optionService.modifyOptionWorkingType(optionWorkingType)) {
			throw new BusinessException("找不到该工作类型，更新失败");
		}
		return true;
	}

	@Override
	public Boolean saveOptionWorkingType(OptionWorkingType optionWorkingType) {

		if (optionWorkingType == null || optionWorkingType.getId() != null || optionWorkingType.getCrop_type() == null || optionWorkingType
				.getWorking_type()
				== null) {
			throw new BusinessException("信息有误，保存失败");
		}
		if (!optionService.saveOptionWorkingType(optionWorkingType)) {
			throw new BusinessException("服务器暂时有点问题，请稍后重试");
		}

		return true;
	}

	@Override
	public Boolean deleteWorkingTypeOptionClusterRelation(Long workingTypeId, Long optionClusterId) {

		if(optionService.isWorkingTypeOptionClusterRelationUsedOnOrderMachine(workingTypeId, optionClusterId)){
			throw new BusinessException("该关系正在被使用，删除失败");
		}

		if (!optionService.deleteWorkingTypeOptionClusterRelation(workingTypeId, optionClusterId)) {
			throw new BusinessException("该关系不存在，删除失败");
		}

		return true;
	}

	@Override
	public Boolean saveWorkingTypeOptionClusterRelation(OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey) {

		if (optionService.getOptionClusterById(optionClusterWorkingTypeMappingKey.getCluster_id())==null){
			throw new BusinessException("选项集不存在，添加失败");
		}
		if(optionService.getWorkingTypeById(optionClusterWorkingTypeMappingKey.getWorking_type_id())==null){
			throw new BusinessException("工作类型不存在，添加失败");
		}
		if(optionService.isExistWorkingTypeOptionClusterRelation(optionClusterWorkingTypeMappingKey)){
			throw new BusinessException("该关系已经存在，无需添加");
		}

		if (!optionService.saveWorkingTypeOptionClusterRelation(optionClusterWorkingTypeMappingKey)) {
			throw new BusinessException("服务器暂时有点问题，请稍候重试");
		}

		return true;
	}

}
