package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.swagger.show.UserShow;
import cn.bangnongmang.server.swagger.api.UsersApi;
import cn.bangnongmang.server.swagger.model.UserDetail;
import cn.bangnongmang.server.swagger.model.UserSimple;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class UsersController implements UsersApi {

    @Autowired
    private UserShow userShow;


    @Override
    public ResponseEntity<List<UserSimple>> usersSearchsPost(
            @ApiParam(value = "用户的手机号或者真实姓名", required = true) @RequestParam(value = "key", required = true) String key,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UserSimple> userSimples = userShow.searchUserByUsernameOrRealName(key);
        return new ResponseEntity<List<UserSimple>>(userSimples, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDetail> usersUidGet(
            @ApiParam(value = "对方的用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDetail userDetail = userShow.getUserDetail(uid,
                BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);
    }
}
