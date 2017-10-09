package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.combo.domain.TeamJoinRequsterInfo;
import cn.bangnongmang.service.service.TeamOrderService;
import cn.bangnongmang.server.swagger.model.Team;
import cn.bangnongmang.server.swagger.model.TeamJoinRequest;
import cn.bangnongmang.server.swagger.model.UserSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-17.
 */
@Service
public class TeamConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private OrderConverter orderConverter;

    public Team convertToTeam(TeamInfoShow teamInfoShow) {
        Team team = new Team();
        team.setDescription(teamInfoShow.getTeamInOrder().getDescription());
        team.setJoinRequests(teamInfoShow.getRequesters().stream().filter(teamJoinRequsterInfo ->
                teamJoinRequsterInfo.getTeamJoinRequest().getState().equals(TeamOrderService
                        .REQUEST_WAITTING)).map(this::convertToTeamJoinRequest).collect(
                Collectors.toList()));
        team.setLeader(
                userConverter.convertToUserSimple(teamInfoShow.getLeader(), UserSimple.class));
        team.setOrder(orderConverter.convertToOrder(teamInfoShow.getOrderFarmerInfoShow()));
        team.setTeamId(teamInfoShow.getId());
        team.setTeamMembers(teamInfoShow.getRequesters().stream().filter(teamJoinRequsterInfo ->
                teamJoinRequsterInfo.getTeamJoinRequest().getState().equals(TeamOrderService
                        .REQUEST_ACCEPTED)).map(teamJoinRequsterInfo -> userConverter
                .convertToUserSimple(teamJoinRequsterInfo.getUserBasicInfoShow(), UserSimple
                        .class)).collect(Collectors.toList()));
        return team;
    }

    public TeamJoinRequest convertToTeamJoinRequest(TeamJoinRequsterInfo teamJoinRequsterInfo) {
        TeamJoinRequest teamJoinRequest = new TeamJoinRequest();
        teamJoinRequest.setPs(teamJoinRequsterInfo.getTeamJoinRequest().getPs());
        teamJoinRequest.setUser(userConverter.convertToUserSimple(teamJoinRequsterInfo
                .getUserBasicInfoShow(), UserSimple.class));
        return teamJoinRequest;
    }
}
