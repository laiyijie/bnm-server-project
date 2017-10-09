package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.server.business.common.DriverAccountBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.service.service.impl.TextMessageSender;
import cn.bangnongmang.server.swagger.api.AuthsApi;
import cn.bangnongmang.server.swagger.model.LoginTextResult;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * Created by admin on 2017-04-12.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class AuthsController implements AuthsApi {
    @Autowired
    private DriverAccountBusiness driverAccountBusiness;
    @Autowired
    private AccountBusiness commonAccountBusiness;

    @Override
    public ResponseEntity<Void> authsAutoPhoneGet(
            @ApiParam(value = "用户的手机号", required = true) @PathVariable("phone") String phone,
            @NotNull @ApiParam(value = "本地存储的验证信息", required = true) @RequestParam(value = "authData", required = true) String authData,
            @NotNull @ApiParam(value = "用户的machineId", required = true) @RequestParam(value = "machineId", required = true) String machineId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (driverAccountBusiness.userAutoLogin(phone, authData, machineId)) {

            BnmSwaggerControllerUtil.setSession(request, ServerSessionAttr.SESSION_USERNAME,
                    phone);
            Account account = commonAccountBusiness.getUserInfoByUsername(phone);
            BnmSwaggerControllerUtil.setSession(request, ServerSessionAttr.SESSION_UID,
                    account.getUid());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Void> authsDriversPhonePost(
            @ApiParam(value = "用户的手机号", required = true) @PathVariable("phone") String phone,
            @ApiParam(value = "手机验证码", required = true) @RequestParam(value = "authCode", required = true) String authCode,
            @ApiParam(value = "用户的machineId", required = true) @RequestParam(value = "machineId", required = true) String machineId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Account account = driverAccountBusiness.register(phone, authCode, machineId);
        if (account != null) {
            BnmSwaggerControllerUtil.setSession(request, ServerSessionAttr.SESSION_USERNAME,
                    phone);
            BnmSwaggerControllerUtil.setSession(request, ServerSessionAttr.SESSION_UID,
                    account.getUid());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<LoginTextResult> authsLoginTextsPhonePost(
            @ApiParam(value = "用户的手机号", required = true) @PathVariable("phone") String phone,
            @ApiParam(value = "是安卓还是什么", required = true, allowableValues = "DRIVERAPP, WECHAT", defaultValue = "driverapp") @RequestParam(value = "client", required = true) String client,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (client.equals("driverapp")) {
            driverAccountBusiness.getDriverAppLoginOrRegisterText(phone);
            LoginTextResult loginTextResult = new LoginTextResult();
            loginTextResult.setDriver(true);
            loginTextResult.setRegistered(false);
            loginTextResult.setNextTime(TextMessageSender.INTERVAL * 1000L);
            return new ResponseEntity<LoginTextResult>(loginTextResult, HttpStatus.OK);
        } else {
            //TODO 完善这个逻辑，用于微信端等
            return new ResponseEntity<LoginTextResult>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> authsPhoneGet(
            @ApiParam(value = "用户的手机号", required = true) @PathVariable("phone") String phone,
            @ApiParam(value = "手机验证码", required = true) @RequestParam(value = "authCode", required = true) String authCode,
            @ApiParam(value = "用户的machineId", required = true) @RequestParam(value = "machineId", required = true) String machineId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean flag;
        try {
            flag = driverAccountBusiness.androidNormalLogin(phone, authCode, machineId);

            if (flag) {
                BnmSwaggerControllerUtil.setSession(request, ServerSessionAttr.SESSION_USERNAME,
                        phone);
                Account account = commonAccountBusiness.getUserInfoByUsername(phone);
                BnmSwaggerControllerUtil.setSession(request, ServerSessionAttr.SESSION_UID,
                        account.getUid());
            }

            return new ResponseEntity<Void>(HttpStatus.OK);

        } catch (BusinessException e) {
            if (e.errorCode == 1002) {
                throw new BusinessException(1002, "账户不存在");
            } else if (e.errorCode == 1001) {
                throw new BusinessException("登录失败，请检查您的验证码是否正确");
            } else {
                throw new BusinessException("登录失败");
            }
        }
    }
}
