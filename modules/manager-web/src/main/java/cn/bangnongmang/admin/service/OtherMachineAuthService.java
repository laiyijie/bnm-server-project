package cn.bangnongmang.admin.service;

import cn.bangnongmang.admin.util.BusinessException;

public interface OtherMachineAuthService {

	/**
	 * 
	 * 根据id, userMachineId对其他车辆进行成功认证
	 * 
	 * @Title otherMachineAuth
	 * 
	 * @param id
	 * 
	 * @param UserMachineId
	 * @return
	 */

	public boolean otherMachineAuth(long id,long userMachineId)throws BusinessException;
	/**
	 * 
	 *  Description:通过userMachineTypeId获取userMachineId
	 *  @param userMachineTypeId
	 *  @return long
	 *  @author luchangquan  DateTime 2017年4月5日 下午6:22:17
	 *  @return
	 */
	

	public long getUserMachineId(long userMachineTypeId );
	
	
	/**
	 * 
	 *  Description:拒绝认证
	 *  @param 
	 *  @return void
	 *  @author luchangquan  DateTime 2017年4月7日 下午12:21:46
	 *  @param id
	 *  @param userMachineId
	 *  @throws BusinessException
	 */

	public boolean refuseOtherMachineAuth(long id, long userMachineId)throws BusinessException;
}
