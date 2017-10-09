package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.TeamBusiness;
import cn.bangnongmang.admin.service.UserService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.service.service.TeamOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucq on 2017/6/27.
 */
@Component("teamBusiness")
public class TeamBusinessImp implements TeamBusiness{

	@Autowired
	private UserService userService;
	@Autowired
	private TeamOrderService teamOrderService;
	@Autowired
	private WorkTeamManageBusinessImpl workTeamManageBusiness;


	@Override
	public Boolean addTeamMember(String tel,Long teamId, Boolean isPay) {
		Account account=userService.getAccountByUsername(tel);
		if(account==null){
			throw new BusinessException("该机手不存在");
		}
		TeamInOrder teamInOrder=teamOrderService.getTeamInOrderByTeamId(teamId);
		if(teamInOrder==null){
			throw new BusinessException("该队伍不存在");
		}
		workTeamManageBusiness.addMember(account.getUid(),teamInOrder.getOrder_id(),isPay);
		return  true;
	}

	@Override
	public Boolean removeTeamMeber(String tel, Long teamId, Integer punishInsurement, Boolean isPunished) {
		Account account=userService.getAccountByUsername(tel);
		if(account==null){
			throw new BusinessException("该机手不存在");
		}
		TeamInOrder teamInOrder=teamOrderService.getTeamInOrderByTeamId(teamId);
		if(teamInOrder==null){
			throw new BusinessException("该队伍不存在");
		}
		workTeamManageBusiness.removeMember(account.getUid(),teamInOrder.getOrder_id(),punishInsurement,isPunished);
		return true;
	}

	@Override
	public Boolean changeLeader(String tel, Long teamId) {
		Account account=userService.getAccountByUsername(tel);
		if(account==null){
			throw new BusinessException("该机手不存在");
		}
		TeamInOrder teamInOrder=teamOrderService.getTeamInOrderByTeamId(teamId);
		if(teamInOrder==null){
			throw new BusinessException("该队伍不存在");
		}
		workTeamManageBusiness.changeLeader(account.getUid(),teamInOrder.getOrder_id());
		return true;
	}
}
