package cn.bangnongmang.admin.io.swagger.converter;

import cn.bangnongmang.admin.swagger.model.TeamBasic;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import org.springframework.stereotype.Component;

/**
 * Created by lucq on 2017/6/27.
 */
@Component("teamShowConverter")
public class TeamShowConverter {

	public TeamBasic convertTeamBasic(TeamInfoShow teamInfoShow){
		if(teamInfoShow==null){
			return null;
		}
		TeamBasic teamBasic=new TeamBasic();
		teamBasic.setLeaderUid(teamInfoShow.getLeader().getId());
		teamBasic.setLeaderName(teamInfoShow.getLeader().getAccountRealNameAuth().getReal_name());
		teamBasic.setLeaderTel(teamInfoShow.getLeader().getAccount().getTel());
		teamBasic.setOrderId(teamInfoShow.getTeamInOrder().getOrder_id());
		teamBasic.setTeamId(teamInfoShow.getTeamInOrder().getId());
		teamBasic.setTeamDescription(teamInfoShow.getTeamInOrder().getDescription());
		return teamBasic;
	}
}
