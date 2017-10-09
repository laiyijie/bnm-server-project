package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.AliCredentials;

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

@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "获取阿里oss的凭证", notes = "", response = AliCredentials.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = AliCredentials.class) })
    
    @RequestMapping(value = "/user/aliOssAuthCridential",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<AliCredentials> userAliOssAuthCridentialGet( HttpServletRequest request, HttpServletResponse response) throws Exception;

}
