package cn.bangnongmang.admin.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;

/**
 * 
 *  Description: 管理端—Controller层 筛选项管理
 *  @author luchangquan  DateTime 2017年4月6日 下午2:50:34 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
@RequestMapping("/options")
public interface OptionsControllerApi {

	/**
	 * 
	 * 获取所有筛选项类型
	 * 
	 * @Title getOptionClusterlList
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 * @param type 所有
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getOptionClusterlList")
	public PageResult<OptionCluster> getOptionClusterlList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;

	/**
	 * 
	 * 根据id更新筛选项类型
	 * 
	 * @param id
	 * @param clusterName
	 * @param description
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/updateOptionCluster")
	public boolean updateOptionCluster(long id,String clusterName, String description) throws BusinessException;

	/**
	 * 
	 * 插入筛选项类型
	 * 
	 * @param clusterName
	 * @param description
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/insertOptionCluster")
	public boolean insertOptionCluster(String clusterName, String description) throws BusinessException;

	/**
	 * 
	 * 每个类型下面的所有筛选项
	 * 
	 * @Title getOptionDetaillList
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 * @param clusterid
	 * 
	 * @return
	 * @throws BusinessException
	 */

	@RequestMapping("/getOptionDetaillList")
	public PageResult<OptionDetail> getOptionDetaillList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "clusterId") long clusterId) throws BusinessException;

	/**
	 * 
	 * 根据id更新筛选项
	 * 
	 * @param id
	 * @param optionName
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/updateOptionDetail")
	public boolean updateOptionDetail(long id,String optionName) throws BusinessException;

	/**
	 * 
	 * 插入筛选项类型
	 * 
	 * @param optionName
	 * @param clusterid
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/insertOptionDetail")
	public boolean insertOptionDetail(String optionName, long clusterId) throws BusinessException;

	/**
	 * 
	 * 根据clusterId查询 working_type 和 筛选项类型的关联映射
	 * 
	 * @Title getOptionWorkingTypeList
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 * @param clusterid
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getOptionWorkingTypeList")
	public PageResult<OptionWorkingType> getOptionWorkingTypeList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "clusterId") long clusterId) throws BusinessException;

	/**
	 * 
	 * 根据workingTypeId更新OptionWorkingType
	 * 
	 * @param id
	 * @param croptype
	 * @param workingtype
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/updateOptionWorkingType")
	public boolean updateOptionWorkingType(long id,String croptype, String workingType) throws BusinessException;

	/**
	 * 
	 * 插入OptionWorkingType
	 * 
	 * @param cropType
	 * @param workingType
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/insertOptionWorkingType")
	public boolean insertOptionWorkingType(String cropType, String workingType) throws BusinessException;
	

	/**
	 * 
	 * 根据workingTypeId获有筛选项类型
	 * 
	 * @Title getOptionClusterls
	 * 
	 * @param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 * @param workingTypeId
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getOptionClusterls")
	public PageResult<OptionCluster> getOptionClusterls(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "workingTypeId") long workingTypeId) throws BusinessException;

	/**
	 * 
	 * 增加工作种类对应的筛选项类型
	 * 
	 * @param clusterId
	 * @param workingTypeId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/addOptionClusterWorkingType")
	public boolean addOptionClusterWorkingType( long clusterId,long workingTypeId) throws BusinessException;
	/**
	 * 
	 * 删除工作种类对应的筛选项类型
	 * 
	 * @param clusterId
	 * @param workingTypeId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/delOptionClusterWorkingType")
	public boolean delOptionClusterWorkingType( long clusterId,long workingTypeId) throws BusinessException;
	
	 /**
	 *
	 * 根据id删除筛选项类型
	 *
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	 @RequestMapping("/deleteOptionCluster")
	 public boolean deleteOptionCluster(long id) throws BusinessException;
	 /**
	 *
	 * id删除筛选项
	 *
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	 @RequestMapping("/deleteOptionDetail")
	 public boolean deleteOptionDetail(long id) throws BusinessException;


	 /**
	 *
	 * 根据删除OptionWorkingType
	 *
	 * @param clusterid
	 * @return
	 * @throws BusinessException
	 */
	 @RequestMapping("/deleteOptionWorkingType")
	 public boolean deleteOptionWorkingType(long id) throws BusinessException;
	 
		/**
		 * 
		 * 获取所有workTyId类型
		 * 
		 * @Title getWorkingTypeList
		 * 
		 * @param currentPage
		 *            需要查询的页码 默认 1
		 * @param pageSize
		 *            每页显示的数量 默认 20
		 * @param type 所有
		 * 
		 * @return
		 * @throws BusinessException
		 */
		@RequestMapping("/getWorkingTypeList")
		public PageResult<OptionWorkingType> getWorkingTypeList(
				@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
				@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;
		
		/**
		 * 
		 *  Description:获取所以optiondetail
		 *  @param 
		 *  @return List<OptionDetail>
		 *  @author luchangquan  DateTime 2017年4月7日 下午5:27:56
		 *  @return
		 *  @throws BusinessException
		 */
		@RequestMapping("/getOptionDetails")
		public PageResult<OptionDetail> getOptionDetails(
				@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
				@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;
	 
}
