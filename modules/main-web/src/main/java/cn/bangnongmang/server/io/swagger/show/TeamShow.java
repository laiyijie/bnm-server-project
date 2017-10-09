package cn.bangnongmang.server.io.swagger.show;

import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.server.io.swagger.TeamConverter;
import cn.bangnongmang.server.io.swagger.UserConverter;
import cn.bangnongmang.service.service.TeamOrderService;
import cn.bangnongmang.server.swagger.model.Team;
import cn.bangnongmang.server.swagger.model.TeamJoinRequest;
import cn.bangnongmang.server.swagger.model.UserSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-17.
 */
@Service
public class TeamShow {

    @Autowired
    private TeamConverter teamConverter;
    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private UserConverter userConverter;

    public List<Team> getMyTeams(Long uid) {
        List<TeamInfoShow> myTeams = new ArrayList<>();
        myTeams.addAll(teamInfoShowMapper.selectTeamInfoShowByLeader(uid));
        myTeams.addAll(teamInfoShowMapper.selectTeamInfoShowByRequester(uid));
        return myTeams.stream()
                      .map(teamInfoShow -> teamConverter.convertToTeam(teamInfoShow))
                      .collect(
                              Collectors.toList());
    }

    public Team getTeamById(Long teamId) {
        return teamConverter.convertToTeam(teamInfoShowMapper.selectTeamInfoShowByTeamId(teamId));
    }

    public List<TeamJoinRequest> getTeamJoinRequestByTeamId(Long teamId) {
        TeamInfoShow teamInfoShow = teamInfoShowMapper.selectTeamInfoShowByTeamId(teamId);
        if (teamInfoShow == null) {
            return new ArrayList<>();
        }
        return teamInfoShow.getRequesters()
                           .stream()
                           .filter(teamJoinRequsterInfo -> teamJoinRequsterInfo
                                   .getTeamJoinRequest()
                                   .getState()
                                   .equals(TeamOrderService.REQUEST_WAITTING))
                           .map
                                   (teamJoinRequsterInfo -> teamConverter.convertToTeamJoinRequest
                                           (teamJoinRequsterInfo))
                           .collect(Collectors.toList());
    }

    public List<UserSimple> getTeamMemebers(Long teamId) {
        TeamInfoShow teamInfoShow = teamInfoShowMapper.selectTeamInfoShowByTeamId(teamId);
        if (teamInfoShow == null) return new ArrayList<>();

        return teamInfoShow.getRequesters()
                           .stream()
                           .filter(teamJoinRequsterInfo ->
                                   teamJoinRequsterInfo.getTeamJoinRequest()
                                                       .getState()
                                                       .equals(TeamOrderService
                                                               .REQUEST_ACCEPTED))
                           .map(teamJoinRequsterInfo -> userConverter
                                   .convertToUserSimple(
                                           teamJoinRequsterInfo.getUserBasicInfoShow(),
                                           UserSimple.class))
                           .collect(Collectors.toList());
    }
}
