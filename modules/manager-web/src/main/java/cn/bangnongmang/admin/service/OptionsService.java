package cn.bangnongmang.admin.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;

/**
 * 
 * Description: 管理端—service层 筛选项管理
 * 
 * @author luchangquan DateTime 2017年4月6日 上午10:26:56
 * @company bangnongmang
 * @email luchangquan@bangnongmang.cn
 * @version 1.0
 */
public interface OptionsService {

	/**
	 * 
	 * Description:获取所有筛选项
	 * 
	 * @param
	 * @return PageResult<OptionCluster>
	 * @author luchangquan DateTime 2017年4月6日 上午11:09:18
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public PageResult<OptionCluster> getOptionClusterlList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;

	/**
	 * 
	 * Description:更新筛选项
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 上午10:31:25
	 * @param id
	 * @param clusterName
	 * @param description
	 * @throws BusinessException
	 */
	public boolean updateOptionCluster(long id, String clusterName, String description) throws BusinessException;

	/**
	 * 
	 * Description: 插入筛选
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 上午11:06:48
	 * @param clusterName
	 * @param description
	 * @return
	 * @throws BusinessException
	 */
	public boolean insertOptionCluster(String clusterName, String description) throws BusinessException;

	/**
	 * 
	 * Description:每个类型下面的所有筛选项
	 * 
	 * @param
	 * @return PageResult<OptionDetail>
	 * @author luchangquan DateTime 2017年4月6日 下午1:41:19
	 * @param currentPage
	 * @param pageSize
	 * @param clusterId
	 * @return
	 * @throws BusinessException
	 */
	public PageResult<OptionDetail> getOptionDetaillList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "clusterId") long clusterId) throws BusinessException;

	/**
	 * 
	 * Description:根据id更新筛选项
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:41:57
	 * @param id
	 * @param optionName
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateOptionDetail(long id, String optionName) throws BusinessException;

	/**
	 * 
	 * Description:插入筛选项类型
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:42:16
	 * @param optionName
	 * @param clusterId
	 * @return
	 * @throws BusinessException
	 */
	public boolean insertOptionDetail(String optionName, long clusterId) throws BusinessException;

	/**
	 * 
	 * Description:根据clusterId查询 working_type 和 筛选项类型的关联映射
	 * 
	 * @param
	 * @return PageResult<OptionWorkingType>
	 * @author luchangquan DateTime 2017年4月6日 下午1:42:35
	 * @param currentPage
	 * @param pageSize
	 * @param clusterId
	 * @return
	 * @throws BusinessException
	 */
	public PageResult<OptionWorkingType> getOptionWorkingTypeList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "clusterId") long clusterId) throws BusinessException;

	/**
	 * 
	 * Description:根据workingTypeId更新OptionWorkingType
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:43:10
	 * @param id
	 * @param croptype
	 * @param workingType
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateOptionWorkingType(long id, String croptype, String workingType) throws BusinessException;

	/**
	 * 
	 * Description:插入OptionWorkingType
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:43:28
	 * @param cropType
	 * @param workingType
	 * @return
	 * @throws BusinessException
	 */
	public boolean insertOptionWorkingType(String cropType, String workingType) throws BusinessException;

	@RequestMapping("/getOptionClusterls")
	/**
	 * 
	 * Description:根据workingTypeId获有筛选项类型
	 * 
	 * @param
	 * @return PageResult<OptionCluster>
	 * @author luchangquan DateTime 2017年4月6日 下午1:43:58
	 * @param currentPage
	 * @param pageSize
	 * @param workingTypeId
	 * @return
	 * @throws BusinessException
	 */
	public PageResult<OptionCluster> getOptionClusterls(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "workingTypeId") long workingTypeId) throws BusinessException;

	/**
	 * 
	 * Description:增加工作种类对应的筛选项类型
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:44:17
	 * @param clusterId
	 * @param workingTypeId
	 * @return
	 * @throws BusinessException
	 */
	public boolean addOptionClusterWorkingType(long clusterId, long workingTypeId) throws BusinessException;

	/**
	 * 
	 * Description:删除工作种类对应的筛选项类型
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:44:56
	 * @param clusterId
	 * @param workingTypeId
	 * @return
	 * @throws BusinessException
	 */
	public boolean delOptionClusterWorkingType(long clusterId, long workingTypeId) throws BusinessException;

	/**
	 * 
	 * Description:根据id删除筛选项类型
	 * 
	 * @param
	 * @return boolean
	 * @author luchangquan DateTime 2017年4月6日 下午1:45:12
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public boolean deleteOptionCluster(long id) throws BusinessException;

	/**
	 *
	 * id删除筛选项
	 *
	 * @param id
	 * @return
	 * @throws BusinessException
	 */

	public boolean deleteOptionDetail(long id) throws BusinessException;

	/**
	 *
	 * 根据删除OptionWorkingType
	 *
	 * @param clusterid
	 * @return
	 * @throws BusinessException
	 */

	public boolean deleteOptionWorkingType(long id) throws BusinessException;


	/**
	 * 
	 *  Description:获取所有workTyId类型
	 *  @param 
	 *  @return PageResult<OptionWorkingType>
	 *  @author luchangquan  DateTime 2017年4月6日 下午1:46:36
	 *  @param currentPage
	 *  @param pageSize
	 *  @return
	 *  @throws BusinessException
	 */
	public PageResult<OptionWorkingType> getWorkingTypeList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;

}
