package cn.bangnongmang.admin.service;

import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;

public interface UserMachineService {
	/**
	 * 
	 * 查询所有车辆
	 * 
	 * @Title getuserMachineList
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 *        
	 * @param state
	 *          
	 * @return
	 * @throws BusinessException
	 */
	public PageResult<UserMachineModel> getuserMachineModelList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "state",defaultValue="200") Integer state) throws BusinessException;
	
	/***
	 * 车宽更改
	 * @param id
	 * @param modelWidth
	 *@return
	 * @throws BusinessException 
	 * 
	 */

	public UserMachineModel updateMachineWidth(long id,double modelWidth) throws BusinessException;

	/**
	 * 
	 * 根据userMachineTypeId查询车辆的type，每辆车辆只有一种类型
	 * 
	 * @Title getuserMachineType
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 1
	 * @param type
	 *          
	 * @return
	 * @throws BusinessException
	 */

	public PageResult<UserMachineType> getuserMachineType(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
			@RequestParam(value = "userMachineTypeId") long userMachineTypeId) throws BusinessException;
	
	/***
	 * insert车辆类型信息
	 * 
	 * @param typeName
	 * @param descripetion
	 *@return
	 * @throws BusinessException 
	 */

	public boolean insertuserMachineType(String typeName,String descripetion) throws BusinessException;
	
	/***
	 * 更新车辆类型信息
	 * @param  userMachineTypeId
	 * @param typeName
	 * @param descripetion
	 *@return
	 * @throws BusinessException 
	 */

	public boolean updateuserMachineType(long userMachineTypeId,String typeName,String descripetion) throws BusinessException;

	/***
	 * insert车辆对应的工作类型
	 * @param  machineModelId
	 * @param optionWorkingTypeId
	 * 
	 *@return
	 * @throws BusinessException 
	 */

	public boolean insetWorkingTypeMachineModel(long machineModelId,long optionWorkingTypeId) throws BusinessException;
	
	/***
	 * del车辆对应的工作类型
	 * @param  machineModelId
	 * @param optionWorkingTypeId
	 * 
	 *@return
	 * @throws BusinessException 
	 */

	public boolean delWorkingTypeMachineModel(long machineModelId,long optionWorkingTypeId) throws BusinessException;


	/***
	 *del车辆对应的option
	 * @param  modelId
	 * @param optionId
	 * 
	 *@return
	 * @throws BusinessException 
	 */

	public boolean deloptionMachineModel(long modelId,long optionId) throws BusinessException;

	/***
	 *insert车辆对应的option
	 * @param  modelId
	 * @param optionId
	 * 
	 *@return
	 * @throws BusinessException 
	 */

	public boolean insertoptionMachineModel(long modelId,long optionId) throws BusinessException;

	/**
	 * 
	 * 根据modelId查询车辆的option
	 * 
	 * @Title modelId
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 * @param type
	 *          
	 * @return
	 * @throws BusinessException
	 */

	public PageResult<OptionDetail> getOptionDetailByModelId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "modelId") long modelId) throws BusinessException;
	/**
	 * 
	 * 根据modelId查询车辆的工作类型
	 * 
	 * @Title modelId
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 * @param type
	 *          
	 * @return
	 * @throws BusinessException
	 */

	public PageResult<OptionWorkingType> getWorkingTypeByModelId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "modelId") long modelId) throws BusinessException;
}
