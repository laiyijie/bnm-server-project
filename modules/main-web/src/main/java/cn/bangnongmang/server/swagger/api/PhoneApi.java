package cn.bangnongmang.server.swagger.api;

import java.util.List;
import cn.bangnongmang.server.swagger.model.PhoneFriend;

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

@Api(value = "phone", description = "the phone API")
public interface PhoneApi {

    @ApiOperation(value = "邀请好友", notes = "", response = Void.class, tags={ "Phone", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/phone/invite",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> phoneInvitePost(@ApiParam(value = "手机号码", required=true) @RequestParam(value="phonenum", required=true)  String phonenum, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取通讯录好友状态", notes = "", response = PhoneFriend.class, responseContainer = "List", tags={ "Phone", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = PhoneFriend.class) })
    
    @RequestMapping(value = "/phone",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<List<PhoneFriend>> phonePost(@ApiParam(value = "通讯录号码列表" ,required=true )  @Valid @RequestBody List<String> phoneList, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
