package cn.bangnongmang.admin.notify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.bangnongmang.admin.service.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.bangnongmang.admin.enums.NotifyRealNameEnum;
import cn.bangnongmang.admin.notify.model.NotifyMachineAuthParam;
import cn.bangnongmang.admin.notify.model.NotifyRealNameAuthParam;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.notify.bo.NotifyBusinessMessage;
import cn.bangnongmang.notify.client.NotifyClient;
import cn.bangnongmang.notify.server.data.domain.NotifyHook;

@Aspect
@Component
public class NotifyConnector {

    private NotifyClient notifyClient;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;

    @Autowired
    private UserMachineTypeMapper userMachineTypeMapper;
    @Autowired
    private UserMachineModelMapper userMachineModelMapper;
    @Autowired
    private UserMachineMapper userMachineMapper;

    @Autowired
    private UserService userService;

    @Autowired
    public NotifyConnector(NotifyClient notifyClient) {
        this.notifyClient = notifyClient;

        NotifyRealNameAuthParam notifyRealNameAuthParam = new NotifyRealNameAuthParam();
        notifyRealNameAuthParam.setAuthTime("2017年1月17日 16:50");
        notifyRealNameAuthParam.setFailedReason("身份证号输入错误");
        notifyRealNameAuthParam.setPhone("13817342351");
        notifyRealNameAuthParam.setRealName("黄鑫河");

        NotifyMachineAuthParam notifyMachineAuthParam = new NotifyMachineAuthParam();
        notifyMachineAuthParam.setAuthTime("2017年1月17日 16:50");
        notifyMachineAuthParam.setFailedReason("驾驶证不清晰");
        notifyMachineAuthParam.setPhone("13817342351");
        notifyMachineAuthParam.setRealName("黄鑫河");
        notifyMachineAuthParam.setMachineBrand("沃德");
        notifyMachineAuthParam.setMachineNumber("瑞龙");
        notifyMachineAuthParam.setMachineType("收割机");

        registerHook(NotifyRealNameEnum.NOTIFY_REAL_NAME_AUTH_SUCCESS.getCurrencyCode(), "实名认证成功后通知",
                (Map<String, Object>) JSON.toJSON(notifyRealNameAuthParam));
        registerHook(NotifyRealNameEnum.NOTIFY_REAL_NAME_AUTH_FAILED.getCurrencyCode(), "实名认证失败后通知",
                (Map<String, Object>) JSON.toJSON(notifyRealNameAuthParam));
        registerHook(NotifyRealNameEnum.NOTIFY_MACHINE_AUTH_SUCCESS.getCurrencyCode(), "机器认证成功后通知",
                (Map<String, Object>) JSON.toJSON(notifyMachineAuthParam));
        registerHook(NotifyRealNameEnum.NOTIFY_MACHINE_AUTH_FAILED.getCurrencyCode(), "机器认证失败后通知",
                (Map<String, Object>) JSON.toJSON(notifyMachineAuthParam));
    }

    private void registerHook(String hook_name, String description, Map<String, Object> parameters) {
        NotifyHook notifyHook = new NotifyHook();
        notifyHook.setHook_name(hook_name);
        notifyHook.setDescription(description);
        notifyHook.setParameters(JSON.toJSONString(parameters));
        notifyClient.registerHook(notifyHook);
    }

    private <T> void registerHook(String hook_name, String description, Class<T> clazz) {
        NotifyHook notifyHook = new NotifyHook();
        notifyHook.setHook_name(hook_name);
        notifyHook.setDescription(description);
        try {
            T obj = clazz.newInstance();
            notifyHook.setParameters(JSON.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteMapNullValue));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            notifyHook.setParameters("");
        }
        // System.out.println(notifyClient);
        notifyClient.registerHook(notifyHook);
    }

    @AfterReturning(value = "execution(* cn.bangnongmang.admin.controller.AuthController.authRealNameInfo(..)) && args(authUsername,operator)")
    public void authRealNameInfo(String authUsername, String operator) {
        Account account = userService.getAccountByUsername(authUsername);
        AccountRealNameAuth accountRealNameAuth = userService.getAccountRealNameAuth(account.getUid());

        if (account == null || accountRealNameAuth == null) {
            return;
        }
        NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
        notifyBusinessMessage.setHookName(NotifyRealNameEnum.NOTIFY_REAL_NAME_AUTH_SUCCESS.getCurrencyCode());
        notifyBusinessMessage.setOpenid(account.getWechat_open_id());
        notifyBusinessMessage.setPhoneNumber(account.getUsername());
        NotifyRealNameAuthParam param = new NotifyRealNameAuthParam();
        param.setAuthTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        param.setPhone(account.getUsername());
        param.setRealName(accountRealNameAuth.getReal_name());
        notifyBusinessMessage.setParams((Map<String, Object>) JSON.toJSON(param));

        notifyClient.sendBusinessMessage(notifyBusinessMessage);

    }

    @AfterReturning(value = "execution(* cn.bangnongmang.admin.controller.AuthController.denyRealNameInfo(..)) && args(authUsername,operator,reason)")
    public void denyRealNameInfo(String authUsername, String operator, String reason) {
        Account account = userService.getAccountByUsername(authUsername);
        AccountRealNameAuth accountRealNameAuth = userService.getAccountRealNameAuth(account.getUid());

        if (account == null || accountRealNameAuth == null) {
            return;
        }
        NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
        notifyBusinessMessage.setHookName(NotifyRealNameEnum.NOTIFY_REAL_NAME_AUTH_FAILED.getCurrencyCode());
        notifyBusinessMessage.setOpenid(account.getWechat_open_id());
        notifyBusinessMessage.setPhoneNumber(account.getUsername());
        NotifyRealNameAuthParam param = new NotifyRealNameAuthParam();
        param.setAuthTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        param.setPhone(account.getUsername());
        param.setRealName(accountRealNameAuth.getReal_name());
        param.setFailedReason(accountRealNameAuth.getFailed_reason());

        notifyBusinessMessage.setParams((Map<String, Object>) JSON.toJSON(param));

        notifyClient.sendBusinessMessage(notifyBusinessMessage);
    }

    @AfterReturning(value = "execution(* cn.bangnongmang.admin.controller.AuthController.authMachine(..)) && args(userMachineId,operator)")
    public void authMachine(Long userMachineId, String operator) {

        UserMachine userMachine = userMachineMapper.selectByPrimaryKey(userMachineId);
        if (userMachine == null) {
            return;
        }
        UserMachineModel userMachineModel = userMachineModelMapper
                .selectByPrimaryKey(userMachine.getMachine_model_id());
        if (userMachineModel == null) {
            return;
        }

        UserMachineType userMachineType = userMachineTypeMapper
                .selectByPrimaryKey(userMachineModel.getUser_machine_type_id());

        if (userMachineType == null) {
            return;
        }

        Account account = userService.getAccount(userMachine.getUid());
        AccountRealNameAuth accountRealNameAuth = userService.getAccountRealNameAuth(userMachine.getUid());

        if (account == null || accountRealNameAuth == null) {
            return;
        }
        NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
        notifyBusinessMessage.setHookName(NotifyRealNameEnum.NOTIFY_MACHINE_AUTH_SUCCESS.getCurrencyCode());
        notifyBusinessMessage.setOpenid(account.getWechat_open_id());
        notifyBusinessMessage.setPhoneNumber(account.getUsername());
        NotifyMachineAuthParam param = new NotifyMachineAuthParam();
        param.setAuthTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        param.setMachineBrand(userMachineModel.getModel_brand());
        param.setMachineNumber(userMachineModel.getModel_number());
        param.setMachineType(userMachineType.getType_name());
        param.setPhone(account.getUsername());
        param.setRealName(accountRealNameAuth.getReal_name());

        notifyBusinessMessage.setParams((Map<String, Object>) JSON.toJSON(param));

        notifyClient.sendBusinessMessage(notifyBusinessMessage);
    }

    @AfterReturning(value = "execution(* cn.bangnongmang.admin.controller.AuthController.denyMachine(..)) && args(userMachineId,operator,reason)")
    public void denyMachine(Long userMachineId, String operator, String reason) {

        UserMachine userMachine = userMachineMapper.selectByPrimaryKey(userMachineId);
        if (userMachine == null) {
            return;
        }
        UserMachineModel userMachineModel = userMachineModelMapper
                .selectByPrimaryKey(userMachine.getMachine_model_id());
        if (userMachineModel == null) {
            return;
        }

        UserMachineType userMachineType = userMachineTypeMapper
                .selectByPrimaryKey(userMachineModel.getUser_machine_type_id());

        if (userMachineType == null) {
            return;
        }

        Account account = userService.getAccount(userMachine.getUid());
        AccountRealNameAuth accountRealNameAuth = userService.getAccountRealNameAuth(userMachine.getUid());

        if (account == null || accountRealNameAuth == null) {
            return;
        }
        NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
        notifyBusinessMessage.setHookName(NotifyRealNameEnum.NOTIFY_MACHINE_AUTH_FAILED.getCurrencyCode());
        notifyBusinessMessage.setOpenid(account.getWechat_open_id());
        notifyBusinessMessage.setPhoneNumber(account.getUsername());
        NotifyMachineAuthParam param = new NotifyMachineAuthParam();
        param.setAuthTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
        param.setMachineBrand(userMachineModel.getModel_brand());
        param.setMachineNumber(userMachineModel.getModel_number());
        param.setMachineType(userMachineType.getType_name());
        param.setPhone(account.getUsername());
        param.setRealName(accountRealNameAuth.getReal_name());
        param.setFailedReason(userMachine.getFailed_reason());

        notifyBusinessMessage.setParams((Map<String, Object>) JSON.toJSON(param));

        notifyClient.sendBusinessMessage(notifyBusinessMessage);
    }

    @AfterReturning(value = "execution(* cn.bangnongmang.admin.controller.InfoController.testWechatModal(..))")
    public void testWechat() {
        NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
        notifyBusinessMessage.setHookName("test");
        notifyBusinessMessage.setOpenid("oHLNYv83dLCfvQsh0QKLK6sLc6FE");
        notifyBusinessMessage.setParams(null);
        notifyBusinessMessage.setPhoneNumber("18801902298");

        notifyClient.sendBusinessMessage(notifyBusinessMessage);
    }


}
