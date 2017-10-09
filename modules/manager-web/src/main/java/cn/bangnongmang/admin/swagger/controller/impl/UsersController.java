package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.io.swagger.show.UserShow;
import cn.bangnongmang.admin.swagger.api.UsersApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.admin.swagger.model.UserDetail;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2017-06-14.
 */
@Controller
@RequestMapping(BaseConf.BASE_URL)
public class UsersController implements UsersApi {
    @Autowired
    private UserShow userShow;

    @Override
    public ResponseEntity<List<UserBasic>> usersDriversGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(userShow.getDriverList());
    }

    @Override
    public ResponseEntity<List<UserBasic>> usersFarmersGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(userShow.getFarmerList());
    }

    @Override
    public ResponseEntity<UserDetail> usersUidGet(@ApiParam(value = "", required = true) @PathVariable("uid") Long uid, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(userShow.getUserDetail(uid));
    }

    @Override
    public ResponseEntity<Void> usersUidRolesDriverLeaderDelete(@ApiParam(value = "", required = true) @PathVariable("uid") String uid,
                                                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> usersUidRolesDriverLeaderPost(@ApiParam(value = "", required = true) @PathVariable("uid") Long uid,
                                                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
