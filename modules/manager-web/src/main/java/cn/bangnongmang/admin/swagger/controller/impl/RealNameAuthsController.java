package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.manager.RealNameAuthBusiness;
import cn.bangnongmang.admin.io.swagger.show.UserShow;
import cn.bangnongmang.admin.swagger.api.RealNameAuthsApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.RealNameAuthDetail;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wenxx on 2017/5/22.
 */
@RestController
@RequestMapping(BaseConf.BASE_URL)
public class RealNameAuthsController implements RealNameAuthsApi{
    @Autowired
    private UserShow realNameAuthsShow;
    @Autowired
    private RealNameAuthBusiness realNameAuthBusiness;

    @Override
    public ResponseEntity<List<UserBasic>> realNameAuthsGet(@NotNull @ApiParam(value = "", required = true, allowableValues = "WAITING_AUTH, AUTHED, DENIED") @RequestParam(value = "type", required = true) String type, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return new ResponseEntity<List<UserBasic>>(realNameAuthsShow.getRealNameAuthsByType(type), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RealNameAuthDetail> realNameAuthsUidGet(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return new ResponseEntity<RealNameAuthDetail>(realNameAuthsShow.getRealNameAuthsByUid(uid),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> realNameAuthsUidStatusAcceptPost(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        realNameAuthBusiness.acceptRealNameAuth(uid);
        return ResponseEntity.ok(null);
    }

    @Override
    public  ResponseEntity<Void> realNameAuthsUidStatusDenyPost(@ApiParam(value = "",required=true ) @PathVariable("uid") Long uid,@ApiParam(value = "拒绝的原因", required=true) @RequestParam(value="reason", required=true)  String reason, HttpServletRequest request, HttpServletResponse response) throws Exception {
        realNameAuthBusiness.denyRealNameAuth(uid,reason);
        return ResponseEntity.ok(null);
    }
}
