package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.server.business.common.TeamOrderBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.swagger.show.TeamShow;
import cn.bangnongmang.server.swagger.api.TeamsApi;
import cn.bangnongmang.server.swagger.model.Team;
import cn.bangnongmang.server.swagger.model.TeamJoinRequest;
import cn.bangnongmang.server.swagger.model.UserSimple;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by admin on 2017-04-17.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class TeamsController implements TeamsApi {
    @Autowired
    private TeamShow teamShow;

    @Autowired
    private TeamOrderBusiness teamOrderBusiness;

    @Override
    public ResponseEntity<Void> teamsTeamIdDelete(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        teamOrderBusiness.deleteOrderTeam(teamId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Team> teamsTeamIdGet(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Team team = teamShow.getTeamById(teamId);
        if (team == null)
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TeamJoinRequest>> teamsTeamIdJoinRquestsGet(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<TeamJoinRequest>>(teamShow.getTeamJoinRequestByTeamId
                (teamId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> teamsTeamIdJoinRquestsPost(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            @ApiParam(value = "请求入队的附言", required = true) @RequestParam(value = "ps", required =
                    true) String ps,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        teamOrderBusiness.sendJoinOrderTeamRequest(teamId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), ps);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> teamsTeamIdJoinRquestsRequesterDelete(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            @ApiParam(value = "请求者的用户名", required = true) @PathVariable("requester") Long requester,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long operator = BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr
                .SESSION_UID);
        if (requester.equals(operator)) {
            teamOrderBusiness.cancelTeamRequest(teamId, requester);
        } else {
            teamOrderBusiness.denyTeamRequest(teamId, requester, operator);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserSimple>> teamsTeamIdMembersGet(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<UserSimple>>(teamShow.getTeamMemebers(teamId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> teamsTeamIdMembersPost(
            @ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
            @ApiParam(value = "加入队伍的用户名", required = true) @RequestParam(value = "uid", required =
                    true) Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        teamOrderBusiness.acceptTeamRequest(teamId, uid, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> teamsTeamIdMembersUidDelete(@ApiParam(value = "队伍ID", required = true) @PathVariable("teamId") Long teamId,
                                                            @ApiParam(value = "队友名称", required = true) @PathVariable("uid") Long uid,
                                                            @NotNull @ApiParam(value = "删除的理由", required = true) @RequestParam(value = "ps", required = true) String ps,
                                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        teamOrderBusiness.deleteTeamRequest(teamId, uid, ps,
                BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
