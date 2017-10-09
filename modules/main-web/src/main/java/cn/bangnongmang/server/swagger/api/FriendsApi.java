package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.FriendDetail;
import cn.bangnongmang.server.swagger.model.FriendRequest;
import cn.bangnongmang.server.swagger.model.FriendSimple;

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

@Api(value = "friends", description = "the friends API")
public interface FriendsApi {

    @ApiOperation(value = "获取我的好友列表 ", notes = "", response = FriendSimple.class, responseContainer = "List", tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = FriendSimple.class) })
    
    @RequestMapping(value = "/friends",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<FriendSimple>> friendsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的好友请求 ", notes = "", response = FriendRequest.class, responseContainer = "List", tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = FriendRequest.class) })
    
    @RequestMapping(value = "/friends/requests",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<FriendRequest>> friendsRequestsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "发送好友请求 ", notes = "", response = Void.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Void.class) })
    
    @RequestMapping(value = "/friends/requests",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> friendsRequestsPost(@ApiParam(value = "好友的id", required=true) @RequestParam(value="uid", required=true)  Long uid,@ApiParam(value = "请求信息", required=true) @RequestParam(value="ps", required=true)  String ps, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "", notes = "", response = Void.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Void.class) })
    
    @RequestMapping(value = "/friends/requests/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> friendsRequestsUidDelete(@ApiParam(value = "好友的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "同意或者拒绝好友请求 ", notes = "", response = Void.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Void.class) })
    
    @RequestMapping(value = "/friends/requests/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> friendsRequestsUidPost(@ApiParam(value = "好友的id",required=true ) @PathVariable("uid") Long uid,@ApiParam(value = "是否同意", required=true) @RequestParam(value="agree", required=true)  Boolean agree, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除特殊好友 ", notes = "", response = Void.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Void.class) })
    
    @RequestMapping(value = "/friends/specials/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> friendsSpecialsUidDelete(@ApiParam(value = "好友的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "添加特殊好友 ", notes = "", response = Void.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = Void.class) })
    
    @RequestMapping(value = "/friends/specials/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> friendsSpecialsUidPost(@ApiParam(value = "好友的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除好友 ", notes = "", response = Void.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/friends/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> friendsUidDelete(@ApiParam(value = "好友的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取好友的详细信息，如果不是好友，则抛错 ", notes = "", response = FriendDetail.class, tags={ "Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = FriendDetail.class) })
    
    @RequestMapping(value = "/friends/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<FriendDetail> friendsUidGet(@ApiParam(value = "好友的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
