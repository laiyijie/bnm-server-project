package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.Team;
import cn.bangnongmang.server.swagger.model.TeamJoinRequest;
import cn.bangnongmang.server.swagger.model.UserSimple;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Api(value = "teams", description = "the teams API")
public interface TeamsApi {

    @ApiOperation(value = "解散队伍，只有队长可以", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/teams/{teamId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> teamsTeamIdDelete(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取队伍信息", notes = "", response = Team.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Team.class) })
    
    @RequestMapping(value = "/teams/{teamId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Team> teamsTeamIdGet(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取入队请求", notes = "", response = TeamJoinRequest.class, responseContainer = "List", tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = TeamJoinRequest.class) })
    
    @RequestMapping(value = "/teams/{teamId}/joinRquests",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<TeamJoinRequest>> teamsTeamIdJoinRquestsGet(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "提交入队申请", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/teams/{teamId}/joinRquests",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> teamsTeamIdJoinRquestsPost(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId,@ApiParam(value = "请求入队的附言", required=true) @RequestParam(value="ps", required=true)  String ps, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除入队申请（删除自己的，拒绝他人的都是一样）", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/teams/{teamId}/joinRquests/{requester}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> teamsTeamIdJoinRquestsRequesterDelete(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId,@ApiParam(value = "请求者的用户名",required=true ) @PathVariable("requester") Long requester, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取队伍中的队员", notes = "", response = UserSimple.class, responseContainer = "List", tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserSimple.class) })
    
    @RequestMapping(value = "/teams/{teamId}/members",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<UserSimple>> teamsTeamIdMembersGet(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "同意入队请求（增加队员）", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/teams/{teamId}/members",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> teamsTeamIdMembersPost(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId,@ApiParam(value = "加入队伍的用户id", required=true) @RequestParam(value="uid", required=true)  Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除队员", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/teams/{teamId}/members/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> teamsTeamIdMembersUidDelete(@ApiParam(value = "队伍ID",required=true ) @PathVariable("teamId") Long teamId,@ApiParam(value = "队友名称",required=true ) @PathVariable("uid") Long uid, @NotNull @ApiParam(value = "删除的理由", required = true) @RequestParam(value = "ps", required = true) String ps, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
