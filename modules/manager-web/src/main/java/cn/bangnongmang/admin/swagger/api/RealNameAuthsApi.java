package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.RealNameAuthDetail;
import cn.bangnongmang.admin.swagger.model.UserBasic;

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

@Api(value = "realNameAuths", description = "the realNameAuths API")
public interface RealNameAuthsApi {

    @ApiOperation(value = "获取实名认证列表", notes = "", response = UserBasic.class, responseContainer = "List", tags={ "RealNameAuths", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserBasic.class) })
    
    @RequestMapping(value = "/realNameAuths",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserBasic>> realNameAuthsGet( @NotNull @ApiParam(value = "", required = true, allowableValues = "WAITING_AUTH, AUTHED, DENIED") @RequestParam(value = "type", required = true) String type, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取某个人的实名认证详细信息", notes = "", response = RealNameAuthDetail.class, tags={ "RealNameAuths", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = RealNameAuthDetail.class) })
    
    @RequestMapping(value = "/realNameAuths/{uid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<RealNameAuthDetail> realNameAuthsUidGet(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "通过实名认证", notes = "", response = Void.class, tags={ "RealNameAuths", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/realNameAuths/{uid}/status/accept",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> realNameAuthsUidStatusAcceptPost(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "拒绝实名认证", notes = "", response = Void.class, tags={ "RealNameAuths", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/realNameAuths/{uid}/status/deny",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> realNameAuthsUidStatusDenyPost(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid,@ApiParam(value = "拒绝的原因", required=true) @RequestParam(value="reason", required=true)  String reason, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
