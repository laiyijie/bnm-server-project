package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.TeamBasic;
import cn.bangnongmang.admin.swagger.model.TeamDetail;

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

@Api(value = "team", description = "the team API")
public interface TeamApi {

    @ApiOperation(value = "获得所有组队信息", notes = "", response = TeamBasic.class, responseContainer = "List", tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = TeamBasic.class) })
    
    @RequestMapping(value = "/team",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TeamBasic>> teamGet( @ApiParam(value = "") @RequestParam(value = "orderId", required = false) String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除队员", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/team/{teamId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> teamTeamIdDelete(@ApiParam(value = "",required=true ) @PathVariable("teamId") Long teamId, @NotNull @ApiParam(value = "", required = true) @RequestParam(value = "tel", required = true) String tel, @NotNull @ApiParam(value = "", required = true) @RequestParam(value = "punishInsurance", required = true) Integer punishInsurance, @NotNull @ApiParam(value = "", required = true) @RequestParam(value = "isPunished", required = true) Boolean isPunished, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获得所有指定组队信息详细", notes = "", response = TeamDetail.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = TeamDetail.class) })
    
    @RequestMapping(value = "/team/{teamId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<TeamDetail> teamTeamIdGet(@ApiParam(value = "",required=true ) @PathVariable("teamId") Long teamId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "添加队员", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/team/{teamId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> teamTeamIdPost(@ApiParam(value = "",required=true ) @PathVariable("teamId") Long teamId,@ApiParam(value = "", required=true) @RequestParam(value="tel", required=true)  String tel,@ApiParam(value = "", required=true) @RequestParam(value="isPay", required=true)  Boolean isPay, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "更换队长", notes = "", response = Void.class, tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/team/{teamId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> teamTeamIdPut(@ApiParam(value = "",required=true ) @PathVariable("teamId") Long teamId,@ApiParam(value = "", required=true) @RequestParam(value="tel", required=true)  String tel, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
