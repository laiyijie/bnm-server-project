package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.LoginTextResult;

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

@Api(value = "auths", description = "the auths API")
public interface AuthsApi {

    @ApiOperation(value = "自动登录 ", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class),
        @ApiResponse(code = 403, message = "登陆失败", response = Void.class) })
    
    @RequestMapping(value = "/auths/auto/{phone}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Void> authsAutoPhoneGet(@ApiParam(value = "用户的手机号",required=true ) @PathVariable("phone") String phone, @NotNull @ApiParam(value = "本地存储的验证信息", required = true) @RequestParam(value = "authData", required = true) String authData, @NotNull @ApiParam(value = "用户的machineId", required = true) @RequestParam(value = "machineId", required = true) String machineId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "注册农机手 ", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/drivers/{phone}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> authsDriversPhonePost(@ApiParam(value = "用户的手机号",required=true ) @PathVariable("phone") String phone,@ApiParam(value = "手机验证码", required=true) @RequestParam(value="authCode", required=true)  String authCode,@ApiParam(value = "用户的machineId", required=true) @RequestParam(value="machineId", required=true)  String machineId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取手机验证码 ", notes = "", response = LoginTextResult.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = LoginTextResult.class) })
    
    @RequestMapping(value = "/auths/loginTexts/{phone}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<LoginTextResult> authsLoginTextsPhonePost(@ApiParam(value = "用户的手机号",required=true ) @PathVariable("phone") String phone,@ApiParam(value = "是安卓还是什么", required=true, allowableValues="DRIVER_APP, WECHAT") @RequestParam(value="client", required=true)  String client, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "登录 ", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class),
        @ApiResponse(code = 403, message = "登陆失败", response = Void.class) })
    
    @RequestMapping(value = "/auths/{phone}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Void> authsPhoneGet(@ApiParam(value = "用户的手机号",required=true ) @PathVariable("phone") String phone, @NotNull @ApiParam(value = "手机验证码", required = true) @RequestParam(value = "authCode", required = true) String authCode, @NotNull @ApiParam(value = "用户的machineId", required = true) @RequestParam(value = "machineId", required = true) String machineId, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
