package cn.bangnongmang.server.business.common.impl;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountPortrait;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.outerservice.impl.AliOssServiceImpl;
import cn.bangnongmang.service.outerservice.impl.AliOssServiceImpl.PolicyBuilder;
import cn.bangnongmang.service.service.GeoService;
import cn.bangnongmang.service.service.PushEventService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.VerifyCodeService;

import java.util.Objects;

/**
 * 基本的用户账户控制逻辑
 *
 * @author laiyijie
 * @ClassName CommonAccountBusiness
 * @date 2016年12月17日 下午4:39:11
 */
@Service("cCommonAccountBusiness")
@Transactional(rollbackFor = {Exception.class})
public class AccountBusinessImpl implements AccountBusiness {
    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private PushEventService pushEventService;

    @Autowired
    private AliOssService aliOssService;
    @Autowired
    private GeoService geoService;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;

    @Override
    public Account getUserInfoByUsername(String username) {
        if (username == null || "".equals(username))
            return null;
        return userService.getUserInfoByUsername(username);
    }

    @Override
    public Account getUserInfo(Long uid) {
        return userService.getUserInfo(uid);
    }

    @Override
    public Account getUserInfoByUnionId(String unionid) {

        return userService.getUserInfoByUnionId(unionid);
    }

    @Override
    public String getCommonVerifyCode(String username) throws BusinessException {

        try {
            return verifyCodeService.sendVerifyCode(username);
        } catch (ServiceLayerExeption serviceLayerExeption) {
            throw new BusinessException(serviceLayerExeption.getErrorCode(), serviceLayerExeption.getErrorMessage());
        }
    }

    @Override
    public Account commonRegister(String username, String authCode, String type, String unionid,
                                  String openid)
            throws BusinessException {

        if (!verifyCodeService.checkVerifyCode(username, authCode)) {

            throw new BusinessException(1015);
        }

        boolean flag = false;
        try {
            if ("driver".equals(type)) {
                flag = userService.createUser(username, UserService.DRIVER);
                Account user = userService.getUserInfoByUsername(username);
                PushEvent event = pushEventService
                        .createPushEvent(user.getUid(), PushEventService.TYPE_REGISTER);

                pushEventService.enablePushEvent(event);

            } else if ("farmer".equals(type)) {
                flag = userService.createUser(username, UserService.FARMER);
            }
        } catch (Exception e) {
            throw new BusinessException(1016);
        }

        if (flag != true) {

            throw new BusinessException(1016);
        }
        Account user = userService.getUserInfoByUsername(username);
        userService.updateWechatInfo(user.getUid(), unionid, openid);


        return user;

    }

    @Override
    public Account commonLogin(String username, String authCode) throws BusinessException {

        Account account = userService.getUserInfoByUsername(username);

        if (account == null) {
            throw new BusinessException(1002);
        }

        boolean flag = verifyCodeService.checkVerifyCode(username, authCode);

        if (flag) {

            return account;
        }

        throw new BusinessException(1001);
    }

    @Override
    public boolean updateWechatInfo(Long uid, String unionid, String openid) {

        return userService.updateWechatInfo(uid, unionid, openid);
    }

    @Override
    public boolean setPortraitUrl(Long uid, String filename) {

        if (filename == null || "".equals(filename)) {
            return false;
        }
        String url = aliOssService.getPortraitPutUrlFolderByUsername(uid) + filename;
        AccountPortrait portrait = userService.getPortraitUrl(uid);

        if (portrait != null) {
            if (!portrait.getPortrait_url()
                         .equals(url)) {
                aliOssService
                        .deleteImage(AliOssService.HEAD_BUCKET_NAME, portrait.getPortrait_url());
            }
        }

        return userService.setPortraitUrl(uid, url);
    }

    @Override
    public Credentials getAliOssCredentials(Long uid, String sessionName) {
        Account account = userService.getUserInfo(uid);
        PolicyBuilder policyBuilder = new AliOssServiceImpl.PolicyBuilder();
        policyBuilder.addAllowGet(AliOssService.HEAD_BUCKET_NAME, "");
        policyBuilder.addAllowPut(AliOssService.HEAD_BUCKET_NAME,
                aliOssService.getPortraitPutUrlFolderByUsername(uid));
        policyBuilder.addAllowPut(AliOssService.IMG_BUCKET_NAME,
                aliOssService.getAuthPutUrlFolderByUsername(uid));
        policyBuilder.addAllowGet(AliOssService.IMG_BUCKET_NAME,
                aliOssService.getAuthPutUrlFolderByUsername(uid));
        if (account!=null){
            policyBuilder.addAllowGet(AliOssService.IMG_BUCKET_NAME,
                    aliOssService.getPutUrlFolderByString(account.getUsername()));
        }
        policyBuilder.addAllowGet(AliOssService.WORK_SIZE_AUTH_BUCKET_NAME, "");
        policyBuilder.addAllowPut(AliOssService.WORK_SIZE_AUTH_BUCKET_NAME,
                aliOssService.getAuthPutUrlFolderByUsername(uid));
        policyBuilder.addAllowGet(AliOssService.ORDER_IMG_BUCKET_NAME, "");
        return aliOssService.getCridentialsByPolicy(policyBuilder.build(), sessionName);
    }

    @Override
    public String getPortaitPutUrl(Long uid) {

        return aliOssService.getPortraitPutUrlFolderByUsername(uid);
    }

    @Override
    public boolean updateAccountGeoInfo(AccountGeo accountGeo) {
        return geoService.updateAccountGeoInfo(accountGeo);
    }

    @Override
    public String getImagePutUrl(Long uid) {

        return aliOssService.getAuthPutUrlFolderByUsername(uid);
    }

    @Override
    public boolean realNameCertificate(Long uid, String name, String idNumber, String upSide,
                                       String downSide)
            throws BusinessException {

        Account account = userService.getUserInfo(uid);
        if (account == null) {
            throw new BusinessException("用户不存在");
        }

        AccountRealNameAuth accountRealNameAuth = userService.getRealNameAuth(uid);

        if (accountRealNameAuth != null) {
            if (Objects
                    .equals(accountRealNameAuth.getState(),
                            UserService.REAL_NAME_AUTH_STATE_PASS)) {
                throw new BusinessException("您实名认证已经成功，无法重复提交");
            }

        }

        if (!userService.addRealNameAuth(uid, name, idNumber,
                aliOssService.getAuthPutUrlFolderByUsername(uid) + upSide,
                aliOssService.getAuthPutUrlFolderByUsername(uid) + downSide)) {
            return false;
        }
        boolean flag = true;
        if (Objects.equals(account.getLevel(), UserService.FARMER)) {
            flag = userService.changeAccountState(uid, UserService.FARMER_WAITTING_AUTH);
        } else if (Objects.equals(account.getLevel(), UserService.DRIVER)) {
            flag = userService.changeAccountState(uid, UserService.DRIVER_WAITTING_AUTH);
        }

        if (!flag) {
            throw new BusinessException("提交认证失败，请重试");
        }

        userService.changeAccountName(uid, name);

        return true;
    }

    @Override
    public AccountRealNameAuth getRealNameAuthInfo(Long uid) {
        return accountRealNameAuthMapper.selectByPrimaryKey(uid);
    }

}
