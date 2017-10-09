package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.swagger.api.UserApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.AliCredentials;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.outerservice.impl.AliOssServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017-05-25.
 */
@RestController
@RequestMapping(BaseConf.BASE_URL)
public class UserController implements UserApi {
    @Autowired
    private AliOssService aliOssService;

    @Override
    public ResponseEntity<AliCredentials> userAliOssAuthCridentialGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AliOssServiceImpl.PolicyBuilder policyBuilder = new AliOssServiceImpl.PolicyBuilder();

        policyBuilder.addAllowGet(AliOssService.IMG_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.HEAD_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.RECORD_BUCKET_NAME, "");

        policyBuilder.addAllowPut(AliOssService.RECORD_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.WORK_SIZE_AUTH_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.ORDER_IMG_BUCKET_NAME, "");

        policyBuilder.addAllowPut(AliOssService.ORDER_IMG_BUCKET_NAME, "");

        return ResponseEntity.ok(JSON.parseObject(JSON.toJSONString(aliOssService.getCridentialsByPolicy(policyBuilder.build(), "manager")),
                AliCredentials.class));
    }
}
