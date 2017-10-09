package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.admin.swagger.model.UserDetail;

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

@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "获取所有的机手", notes = "", response = UserBasic.class, responseContainer = "List", tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserBasic.class) })
    
    @RequestMapping(value = "/users/drivers",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserBasic>> usersDriversGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的认证农户", notes = "", response = UserBasic.class, responseContainer = "List", tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserBasic.class) })
    
    @RequestMapping(value = "/users/farmers",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserBasic>> usersFarmersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取用户的详细信息", notes = "", response = UserDetail.class, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserDetail.class) })
    
    @RequestMapping(value = "/users/{uid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserDetail> usersUidGet(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "取消其队长资格", notes = "", response = Void.class, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/users/{uid}/roles/driverLeader",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> usersUidRolesDriverLeaderDelete(@ApiParam(value = "",required=true ) @PathVariable("uid") String uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "提升队长", notes = "", response = Void.class, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/users/{uid}/roles/driverLeader",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> usersUidRolesDriverLeaderPost(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
