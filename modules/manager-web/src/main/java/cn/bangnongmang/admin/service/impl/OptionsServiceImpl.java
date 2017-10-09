package cn.bangnongmang.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.service.OptionsService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.OptionClusterMapper;
import cn.bangnongmang.data.dao.OptionClusterWorkingTypeMappingMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionClusterCriteria;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingCriteria;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionDetailCriteria;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OptionWorkingTypeCriteria;

@Transactional(rollbackFor = { Exception.class })
@Service
public class OptionsServiceImpl  implements OptionsService {

	@Autowired
	private OptionClusterMapper optionClusterMapper;

	@Autowired
	private OptionDetailMapper optionDetailMapper;

	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Autowired
	private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;

	@Override
	public PageResult<OptionCluster> getOptionClusterlList(Integer currentPage, Integer pageSize)
			throws BusinessException {

		PageHelper.startPage(currentPage, pageSize);

		OptionClusterCriteria clusterCriteria = new OptionClusterCriteria();
		Page<OptionCluster> optionCluster = (Page<OptionCluster>) optionClusterMapper.selectByExample(clusterCriteria);

		return new PageResult<OptionCluster>(optionCluster);
	}

	@Override
	public boolean updateOptionCluster(long id, String clusterName, String description) throws BusinessException {

		OptionCluster optionCluster = optionClusterMapper.selectByPrimaryKey(id);

		if (null == optionCluster) {
			throw new BusinessException("optionCluster不存在");
		}

		optionCluster.setCluster_name(clusterName);
		optionCluster.setDescription(description);

		if (optionClusterMapper.updateByPrimaryKey(optionCluster) > 0) {
			return true;

		} else {
			throw new BusinessException("optionCluster更新失败");
		}

	}

	@Override
	public boolean insertOptionCluster(String clusterName, String description) throws BusinessException {

		if (null == clusterName) {
			throw new BusinessException("clusterName为空");
		}

		OptionCluster optionCluster = new OptionCluster();
		optionCluster.setCluster_name(clusterName);
		optionCluster.setDescription(description);

		if (optionClusterMapper.insert(optionCluster) > 0) {
			return true;
		} else {
			throw new BusinessException("插入数据失败");
		}
	}

	@Override
	public PageResult<OptionDetail> getOptionDetaillList(Integer currentPage, Integer pageSize, long clusterId)
			throws BusinessException {
		PageHelper.startPage(currentPage, pageSize);

		OptionDetailCriteria detailCriteria = new OptionDetailCriteria();
		detailCriteria.or().andCluster_idEqualTo(clusterId);

		Page<OptionDetail> optionDetail = (Page<OptionDetail>) optionDetailMapper.selectByExample(detailCriteria);

		return new PageResult<OptionDetail>(optionDetail);
	}

	@Override
	public boolean updateOptionDetail(long id, String optionName) throws BusinessException {

		OptionDetail optionDetail = optionDetailMapper.selectByPrimaryKey(id);

		if (optionDetail == null) {
			throw new BusinessException("optionDetail为空");
		}

		optionDetail.setOption_name(optionName);

		if (optionDetailMapper.updateByPrimaryKey(optionDetail) > 0) {
			return true;
		} else {
			throw new BusinessException("更新数据失败");
		}
	}

	@Override
	public boolean insertOptionDetail(String optionName, long clusterId) throws BusinessException {

		OptionCluster optionCluster = optionClusterMapper.selectByPrimaryKey(clusterId);
		if (optionName == null) {
			optionName = "";
		}

		if (optionCluster == null) {
			throw new BusinessException("clusterid不存在");
		}

		OptionDetail detail = new OptionDetail();
		detail.setCluster_id(clusterId);
		detail.setOption_name(optionName);

		if (optionDetailMapper.insert(detail) > 0) {
			return true;
		} else {
			throw new BusinessException("更新数据失败");
		}

	}

	@Override
	public PageResult<OptionWorkingType> getOptionWorkingTypeList(Integer currentPage, Integer pageSize, long clusterId)
			throws BusinessException {

		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
		criteria.or().andCluster_idEqualTo(clusterId);
		PageHelper.startPage(currentPage, pageSize);
		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingTypeMappingMapper.selectByExample(criteria);

		OptionWorkingTypeCriteria typeCriteria = new OptionWorkingTypeCriteria();

		if (list.isEmpty()){
			typeCriteria.or().andIdEqualTo(Long.MAX_VALUE);
		}
		for (OptionClusterWorkingTypeMappingKey key:list) {
			typeCriteria.or().andIdEqualTo(key.getWorking_type_id());
		}
		PageHelper.startPage(currentPage, pageSize);
		Page<OptionWorkingType> OptionWorkingTypeList = (Page<OptionWorkingType>) optionWorkingTypeMapper
				 .selectByExample(typeCriteria);
		return new PageResult<OptionWorkingType>(OptionWorkingTypeList);
	}

	@Override
	public boolean updateOptionWorkingType(long id, String croptype, String workingType) throws BusinessException {

		OptionWorkingType type = optionWorkingTypeMapper.selectByPrimaryKey(id);
		if (type == null) {
			throw new BusinessException("工作类型不存在");
		}

		type.setCrop_type(croptype);
		type.setWorking_type(workingType);

		if (optionWorkingTypeMapper.updateByPrimaryKey(type) > 0) {
			return true;
		} else {
			throw new BusinessException("工作类型update失败");
		}

	}

	@Override
	public boolean insertOptionWorkingType(String cropType, String workingType) throws BusinessException {

		if (cropType == null) {
			throw new BusinessException("cropType为null");
		}

		if (workingType == null) {
			throw new BusinessException("workingType为null");
		}

		OptionWorkingType type = new OptionWorkingType();
		type.setCrop_type(cropType);
		type.setWorking_type(workingType);

		if (optionWorkingTypeMapper.insert(type) > 0) {
			return true;
		} else {
			throw new BusinessException("工作类型insert失败");
		}

	}

	@Override
	public PageResult<OptionCluster> getOptionClusterls(Integer currentPage, Integer pageSize, long workingTypeId)
			throws BusinessException {

		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
		criteria.or().andWorking_type_idEqualTo(workingTypeId);
		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingTypeMappingMapper.selectByExample(criteria);

		if (list.size() <= 0) {
			throw new BusinessException("暂无设置此选项的工作类型");
		}

		Page<OptionCluster> optionClusterList = null;

		OptionClusterCriteria optionClusterCriteria = new OptionClusterCriteria();

		int flag = 0;
		for (OptionClusterWorkingTypeMappingKey key : list) {

			optionClusterCriteria.or().andIdEqualTo(key.getCluster_id());

			flag++;
			if (flag == list.size()) {
				PageHelper.startPage(currentPage, pageSize);
				optionClusterList = (Page<OptionCluster>) optionClusterMapper.selectByExample(optionClusterCriteria);
			}
		}
		return new PageResult<OptionCluster>(optionClusterList);
	}

	@Override
	public boolean addOptionClusterWorkingType(long clusterId, long workingTypeId) throws BusinessException {

		OptionCluster optionCluster = optionClusterMapper.selectByPrimaryKey(clusterId);
		if (optionCluster == null) {
			throw new BusinessException("optionCluster不存在");
		}

		OptionWorkingType workingType = optionWorkingTypeMapper.selectByPrimaryKey(workingTypeId);
		if (workingType == null) {
			throw new BusinessException("OptionWorkingType不存在");
		}

		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingType(clusterId, workingTypeId);

		if (list.size() > 0) {

			throw new BusinessException("已经存在");
		}

		OptionClusterWorkingTypeMappingKey key = new OptionClusterWorkingTypeMappingKey();
		key.setCluster_id(clusterId);
		key.setWorking_type_id(workingTypeId);

		if (optionClusterWorkingTypeMappingMapper.insert(key) > 0) {
			return true;
		} else {
			throw new BusinessException("updateOptionClusterWorkingTypeMappingKey失败");
		}

	}

	@Override
	public boolean delOptionClusterWorkingType(long clusterId, long workingTypeId) throws BusinessException {

		OptionCluster optionCluster = optionClusterMapper.selectByPrimaryKey(clusterId);
		if (optionCluster == null) {
			throw new BusinessException("optionCluster不存在");
		}

		OptionWorkingType workingType = optionWorkingTypeMapper.selectByPrimaryKey(workingTypeId);
		if (workingType == null) {
			throw new BusinessException("OptionWorkingType不存在");
		}

		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingType(clusterId, workingTypeId);

		OptionClusterWorkingTypeMappingKey mappingKey = list.get(0);

		if (list.size() <= 0) {
			throw new BusinessException("要删除已不存在");
		}

		if (optionClusterWorkingTypeMappingMapper.deleteByPrimaryKey(mappingKey) > 0) {
			return true;
		} else {
			throw new BusinessException("要删除已不存在");
		}
	}

	@Override
	public boolean deleteOptionCluster(long id) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOptionDetail(long id) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOptionWorkingType(long id) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageResult<OptionWorkingType> getWorkingTypeList(Integer currentPage, Integer pageSize)
			throws BusinessException {

		PageHelper.startPage(currentPage, pageSize);

		OptionWorkingTypeCriteria criteria = new OptionWorkingTypeCriteria();
		Page<OptionWorkingType> optionWorkingType = (Page<OptionWorkingType>) optionWorkingTypeMapper
				.selectByExample(criteria);

		return new PageResult<OptionWorkingType>(optionWorkingType);
	}

	public List<OptionClusterWorkingTypeMappingKey> optionClusterWorkingType(long clusterId, long workingTypeId) {

		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
		criteria.or().andWorking_type_idEqualTo(workingTypeId).andCluster_idEqualTo(clusterId);

		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingTypeMappingMapper.selectByExample(criteria);

		return list;

	}

}
