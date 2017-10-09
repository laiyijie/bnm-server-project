package cn.bangnongmang.server.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.service.service.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.server.notify.bo.HookAccountParams;

@Aspect
@Component
@Order(3)
public class AccountNotify {

    private NotifyUtil notifyUtil;

    private static String ACCOUNT_REGISTER_SUCCESS = "ACCOUNT_REGISTER_SUCCESS";
    private static String ACCOUNT_POST_REAL_NAME_AUTH_SUCCESS = "ACCOUNT_POST_REAL_NAME_AUTH_SUCCESS";

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    @Autowired
    private UserService userService;

    @Autowired
    public AccountNotify(NotifyUtil notifyUtil) {
        this.notifyUtil = notifyUtil;
        HookAccountParams hookAccountParams = new HookAccountParams();
        hookAccountParams.setRole("农机手");
        hookAccountParams.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        hookAccountParams.setUsername("18801902298");

        notifyUtil.registerHook(ACCOUNT_REGISTER_SUCCESS, "注册成功后通知", hookAccountParams);
        notifyUtil.registerHook(ACCOUNT_POST_REAL_NAME_AUTH_SUCCESS, "提交实名认证成功后通知", hookAccountParams);
    }

    /**
     * android 注册
     *
     * @param username
     * @Title register
     */
    @AfterReturning("execution(* cn.bangnongmang.server.business.common.DriverAccountBusiness.register(..)) && args(username,..)")
    public void register(String username) {

        Account account = userService.getUserInfoByUsername(username);

        notifyUtil.sendNotifyBusinessMessage(account.getUid(), ACCOUNT_REGISTER_SUCCESS, createHookParams(account.getUid()));
    }

    /**
     * 微信注册
     *
     * @param username
     * @Title commonRegister
     */
    @AfterReturning("execution(* cn.bangnongmang.server.business.common.AccountBusiness.commonRegister(..)) && args(username,..)")
    public void commonRegister(String username) {
        Account account = userService.getUserInfoByUsername(username);
        notifyUtil.sendNotifyBusinessMessage(account.getUid(), ACCOUNT_REGISTER_SUCCESS, createHookParams(account.getUid()));
    }

    /**
     * 提交实名认证成功
     *
     * @param uid
     * @Title realNameCertificate
     */

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.AccountBusiness.realNameCertificate(..)) && args(uid,..)")
    public void realNameCertificate(Long uid) {
        notifyUtil.sendNotifyBusinessMessage(uid, ACCOUNT_POST_REAL_NAME_AUTH_SUCCESS, createHookParams(uid));
    }

    /**
     * 添加机器
     *
     * @param uid
     * @Title addMachine
     */
    @AfterReturning("execution(* cn.bangnongmang.server.business.common.DriverAccountBusiness.addMachineAuthRequest(..)) && args(uid,..)")
    public void addMachine(Long uid) {

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.DriverAccountBusiness.addElseMachineRequest(..)) && args(uid,..)")
    public void addMachineElse(Long uid) {

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.DriverAccountBusiness.modifyMachineAuthRequest(..)) && args(id,username,..)")
    public void modifyMachine(Long id, String username) {

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.DriverAccountBusiness.modifyElseMachineRequest(..)) && args(id,username,..)")
    public void modifyMachineElse(Long id, String username) {

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.DriverAccountBusiness.removeUserMachine(..)) && args(id,uid,..)")
    public void removeMachine(Long id, Long uid) {

    }

    private HookAccountParams createHookParams(Long uid) {
        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(uid);

        HookAccountParams hookAccountParams = new HookAccountParams();
        hookAccountParams.setRole(userBasicInfoShow.getAccount()
                                                   .getLevel() < 20 ? "农户" : "农机手");
        hookAccountParams.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        hookAccountParams.setUsername(userBasicInfoShow.getAccount()
                                                       .getUsername());
        return hookAccountParams;
    }

}
