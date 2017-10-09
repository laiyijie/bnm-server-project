package cn.bangnongmang.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.controller.api.OptionsControllerApi;
import cn.bangnongmang.admin.service.OptionsService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.OptionClusterMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionDetailCriteria;
import cn.bangnongmang.data.domain.OptionWorkingType;

@RestController
public class OptionsControllerImpl implements OptionsControllerApi {
	
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private OptionClusterMapper optionClusterMapper;

	@Autowired
	private OptionDetailMapper optionDetailMapper;

	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Override
	public PageResult<OptionCluster> getOptionClusterlList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException {

		return optionsService.getOptionClusterlList(currentPage, pageSize);
	}

	@Override
	public boolean updateOptionCluster(long id, String clusterName, String description) throws BusinessException {
		if (clusterName==null) {
			throw new BusinessException("请填写clusterName");
		}
		return optionsService.updateOptionCluster(id, clusterName, description);

	}

	@Override
	public boolean insertOptionCluster(String clusterName, String description) throws BusinessException {
		if (clusterName==null) {
			throw new BusinessException("请填写clusterName");
		}
		return optionsService.insertOptionCluster(clusterName, description);

	}

	@Override
	public PageResult<OptionDetail> getOptionDetaillList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "clusterId") long clusterId) throws BusinessException {

		return optionsService.getOptionDetaillList(currentPage, pageSize, clusterId);
	}

	@Override
	public boolean updateOptionDetail(long id, String optionName) throws BusinessException {
		if (optionName==null) {
			throw new BusinessException("请填写optionName");
		}
		  return  optionsService.updateOptionDetail(id, optionName);

	}

	@Override
	public boolean insertOptionDetail(String optionName, long clusterId) throws BusinessException {
		if (optionName==null) {
			throw new BusinessException("请填写optionName");
		}
		return optionsService.insertOptionDetail(optionName, clusterId);
	}

	@Override
	public PageResult<OptionWorkingType> getOptionWorkingTypeList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "clusterId") long clusterId) throws BusinessException {

		return optionsService.getOptionWorkingTypeList(currentPage, pageSize, clusterId);
	}

	@Override
	public boolean updateOptionWorkingType(long id, String croptype, String workingType) throws BusinessException {
		if (croptype==null && workingType==null) {
			throw new BusinessException("请填写croptyp或workingType");
		}
		return optionsService.updateOptionWorkingType(id, croptype, workingType);

	}

	@Override
	public boolean insertOptionWorkingType(String cropType, String workingType) throws BusinessException {
		
		if (cropType==null && workingType==null) {
			throw new BusinessException("请填写croptyp或workingType");
		}
		return optionsService.insertOptionWorkingType(cropType, workingType);

	}

	@Override
	public PageResult<OptionCluster> getOptionClusterls(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "workingTypeId") long workingTypeId) throws BusinessException {
		
		return optionsService.getOptionClusterls(currentPage, pageSize, workingTypeId);
	}

	@Override
	public boolean addOptionClusterWorkingType(long clusterId, long workingTypeId) throws BusinessException {
		
		return optionsService.addOptionClusterWorkingType(clusterId, workingTypeId);

	}

	@Override
	public boolean delOptionClusterWorkingType(long clusterId, long workingTypeId) throws BusinessException {
		
		return optionsService.delOptionClusterWorkingType(clusterId, workingTypeId);
	}

	@Override
	public boolean deleteOptionCluster(long id) throws BusinessException {
	

		if (optionClusterMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteOptionDetail(long id) throws BusinessException {

		if (optionDetailMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteOptionWorkingType(long id) throws BusinessException {
	
		if (optionWorkingTypeMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}
		return false;

	}

	@Override
	public PageResult<OptionWorkingType> getWorkingTypeList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize)
			throws BusinessException {
		
		return optionsService.getWorkingTypeList(currentPage, pageSize);

	}

	@Override
	public PageResult<OptionDetail> getOptionDetails(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException {
		
		PageHelper.startPage(currentPage, pageSize);

		OptionDetailCriteria clusterCriteria = new OptionDetailCriteria();
		Page<OptionDetail> optionCluster = (Page<OptionDetail>) optionDetailMapper.selectByExample(clusterCriteria);

		return new PageResult<OptionDetail>(optionCluster);
	}

}
