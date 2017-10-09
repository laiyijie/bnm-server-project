package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.UserDetail;
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

@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "查找用户的信息 ", notes = "", response = UserSimple.class, responseContainer = "List", tags={ "User","Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = UserSimple.class) })
    
    @RequestMapping(value = "/users/searchs",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<List<UserSimple>> usersSearchsPost(@ApiParam(value = "用户的手机号或者真实姓名", required=true) @RequestParam(value="key", required=true)  String key, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取用户的信息 ", notes = "", response = UserDetail.class, tags={ "User","Friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = UserDetail.class) })
    
    @RequestMapping(value = "/users/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<UserDetail> usersUidGet(@ApiParam(value = "用户的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
