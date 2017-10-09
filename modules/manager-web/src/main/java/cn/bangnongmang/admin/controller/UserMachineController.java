package cn.bangnongmang.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bangnongmang.admin.controller.api.UserMachineControllerApi;
import cn.bangnongmang.admin.service.UserMachineService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;

@RestController
public class UserMachineController implements UserMachineControllerApi {

	@Autowired
	private UserMachineModelMapper userMachineModelMapper;

	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;

	
	@Autowired
	private UserMachineService userMachineService;
	
	@Override
	public PageResult<UserMachineModel> getuserMachineModelList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "state", defaultValue="200") Integer state) throws BusinessException {
		
	return userMachineService.getuserMachineModelList(currentPage, pageSize, state);
	}

	
	
	@Override
	public boolean updateMachineWidth(long id, double modelWidth) throws BusinessException {
		
		UserMachineModel userMachineModel = userMachineService.updateMachineWidth(id, modelWidth);
		
		if (userMachineModelMapper.updateByPrimaryKey(userMachineModel) > 0) {
			return true;
		} else {
			throw new BusinessException("车宽更新失败");
		}
	}

	@Override
	public boolean deluserMachineModel(long id) throws BusinessException {
		
		if (userMachineModelMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		} else {
			throw new BusinessException("删除车辆出错");
		}

	}

	@Override
	public PageResult<UserMachineType> getuserMachineType(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
			@RequestParam(value = "userMachineTypeId") long userMachineTypeId) throws BusinessException {

		return userMachineService.getuserMachineType(currentPage, pageSize, userMachineTypeId);
	}

	@Override
	public boolean updateuserMachineType(long userMachineTypeId, String typeName, String descripetion)
			throws BusinessException {

		return userMachineService.updateuserMachineType(userMachineTypeId, typeName, descripetion);
		
	}

	@Override
	public boolean deluserMachineType(long userMachineTypeId) throws BusinessException {

		if (userMachineTypeMapper.deleteByPrimaryKey(userMachineTypeId) > 0) {
			return true;

		} else {
			throw new BusinessException("删除车辆类型出错");
		}
	}

	@Override
	public boolean insertuserMachineType(String typeName, String descripetion) throws BusinessException {

		if (typeName == null) {
			throw new BusinessException("typeName不许为空");
		}

		return userMachineService.insertuserMachineType(typeName, descripetion);
	}

	@Override
	public boolean deloptionMachineModel(long modelId, long optionId) throws BusinessException {
		
		return userMachineService.deloptionMachineModel(modelId, optionId);

	}

	@Override
	public boolean insertoptionMachineModel(long modelId, long optionId) throws BusinessException {

		return userMachineService.insertoptionMachineModel(modelId, optionId);
	}

	@Override
	public boolean insetWorkingTypeMachineModel(long modelId, long optionWorkingTypeId)
			throws BusinessException {
		return userMachineService.insetWorkingTypeMachineModel(modelId, optionWorkingTypeId);
	}

	@Override
	public boolean delWorkingTypeMachineModel(long modelId, long optionWorkingTypeId) throws BusinessException {
		return userMachineService.delWorkingTypeMachineModel(modelId, optionWorkingTypeId);
	}

	@Override
	public PageResult<OptionDetail> getOptionDetailByModelId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "modelId") long modelId) throws BusinessException {


		 return userMachineService.getOptionDetailByModelId(currentPage, pageSize, modelId);

	}

	@Override
	public PageResult<OptionWorkingType> getWorkingTypeByModelId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "modelId") long modelId) throws BusinessException {
		
		return userMachineService.getWorkingTypeByModelId(currentPage, pageSize, modelId);
	}


}
