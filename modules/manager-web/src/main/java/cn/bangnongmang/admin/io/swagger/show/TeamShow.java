package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.io.swagger.converter.TeamShowConverter;
import cn.bangnongmang.admin.io.swagger.converter.UserConverter;
import cn.bangnongmang.admin.swagger.model.TeamBasic;
import cn.bangnongmang.admin.swagger.model.TeamDetail;
import cn.bangnongmang.admin.swagger.model.TeamMember;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.combo.domain.TeamJoinRequsterInfo;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.TeamOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lucq on 2017/6/27.
 */
@Component("teamShow")
public class TeamShow {

	@Autowired
	private TeamOrderService teamOrderService;
	@Autowired
	private TeamShowConverter teamShowConverter;
	@Autowired
	private OrderDriverService orderDriverService;
	@Autowired
	private UserBasicInfoShowMapper userBasicInfoShowMapper;
	@Autowired
	private UserConverter userConverter;

	public List<TeamBasic> getTeamBasicByOrderId(String oderId) {
		return teamOrderService.getTeamInfoShowByOrderId(oderId)
							   .stream()
							   .map(teamInfoShow -> teamShowConverter.convertTeamBasic(teamInfoShow))
							   .collect(Collectors.toList());
	}

	public TeamDetail getTeamDetailByTeamId(Long teamId){
		TeamInfoShow teamInfoShow=teamOrderService.getTeamInfoShowByTeamId(teamId);
		if(teamInfoShow==null){
			return null;
		}
		TeamDetail teamDetail= new TeamDetail();

		teamDetail.setLeaderUid(teamInfoShow.getLeader().getId());
		teamDetail.setLeaderName(teamInfoShow.getLeader().getAccountRealNameAuth().getReal_name());
		teamDetail.setLeaderTel(teamInfoShow.getLeader().getAccount().getTel());
		teamDetail.setOrderId(teamInfoShow.getTeamInOrder().getOrder_id());
		teamDetail.setTeamId(teamInfoShow.getTeamInOrder().getId());
		teamDetail.setTeamDescription(teamInfoShow.getTeamInOrder().getDescription());

		for(TeamJoinRequsterInfo teamJoinRequsterInfo:teamInfoShow.getRequesters()){
			if(teamJoinRequsterInfo.getTeamJoinRequest().getState().equals(TeamOrderService.REQUEST_ACCEPTED)){
				TeamMember teamMember = new TeamMember();
				teamMember.setUid(teamJoinRequsterInfo.getUserBasicInfoShow().getAccount().getUid());
				teamMember.setName(teamJoinRequsterInfo.getUserBasicInfoShow().getAccountRealNameAuth().getReal_name());
				teamMember.setTel(teamJoinRequsterInfo.getUserBasicInfoShow().getAccount().getTel());
				teamDetail.addTeamMemberItem(teamMember);
			}
		}

		return  teamDetail;
	}

	public List<UserBasic> getTeamUserBasicByOrderId(String orderId){
		return orderDriverService.getOrderDriverListByOrderFarmerId(orderId)
				.stream()
				.map(orderDriver ->userConverter.convertToUserBasic(userBasicInfoShowMapper.selectByUid(orderDriver.getUid()),UserBasic.class))
				.collect(Collectors.toList());

	}
}
