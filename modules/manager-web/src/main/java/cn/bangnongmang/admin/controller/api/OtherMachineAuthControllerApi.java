package cn.bangnongmang.admin.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.bangnongmang.admin.util.BusinessException;

@RequestMapping("otherMachineAuth")
public interface OtherMachineAuthControllerApi {
	
	
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
	@RequestMapping("otherMachineAuth")
	public void otherMachineAuth(long id,long userMachineModelId)throws BusinessException;
	/**
	 * 
	 *  Description:通过userMachineTypeId获取userMachineId
	 *  @param userMachineTypeId
	 *  @return long
	 *  @author luchangquan  DateTime 2017年4月5日 下午6:22:17
	 *  @return
	 */
	
	@RequestMapping("getUserMachineId")
	public long getUserMachineId(long userMachineModelId );
	
	
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
	@RequestMapping("refuseOtherMachineAuth")
	public void refuseOtherMachineAuth(long id, long userMachineModelId)throws BusinessException;
	
	

}
