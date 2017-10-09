package cn.bangnongmang.server.business.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.server.business.common.DriverAccountBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.service.UserMachineService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.VerifyCodeService;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author admin
 * @ClassName DriverAccountBusiness
 * @date 2016年10月27日 下午4:04:37
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class DriverAccountBusinessImpl implements DriverAccountBusiness {

    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMachineService userMachineService;
    @Autowired
    private AliOssService aliOssService;

    @Override
    public Account register(String username, String authCode, String machineId)
            throws BusinessException {

        String verifyCode = verifyCodeService.getVerifyCode(username);

        if (verifyCode == null || !verifyCode.equals(authCode)) {

            throw new BusinessException(1015);
        }

        if (userService.createUser(username, UserService.DRIVER) != true) {

            throw new BusinessException(1016);
        }

        userService.setAutoLoginPasswordAndMachineId(username, DigestUtils.sha1Hex(authCode),
                machineId);

        Account account = userService.getUserInfoByUsername(username);

        return account;
    }

    @Override
    public Account getAccountInfo(Long uid) {
        return userService.getUserInfo(uid);
    }

    /**
     * TODO(这里用一句话描述这个方法的作用)
     *
     * @param username
     * @param authCode
     * @param machineId
     * @return
     * @throws BusinessException 1002 账户不存在 1001 登录失败
     * @Title androidNormalLogin
     */
    @Override
    public boolean androidNormalLogin(String username, String authCode, String machineId)
            throws BusinessException {
        Account account = userService.getUserInfoByUsername(username);

        if (account == null) {
            throw new BusinessException(1002);
        }

        boolean flag = verifyCodeService.checkVerifyCode(username, authCode);

        if (flag) {

            userService.setAutoLoginPasswordAndMachineId(username, DigestUtils.sha1Hex(authCode),
                    machineId);

            return true;
        }

        throw new BusinessException(1001);
    }

    /**
     * 安卓自动登录
     *
     * @param username  手机号
     * @param authCode  短信验证码
     * @param machineId deviceid
     * @return
     * @throws BusinessException 1002 用户不存在 1004 设备更换 1005 登录信息过期
     * @Title userAutoLogin
     */
    @Override
    public boolean userAutoLogin(String username, String authCode, String machineId)
            throws BusinessException {

        Account account = userService.getUserInfoByUsername(username);

        if (account == null) {
            throw new BusinessException(1002);
        }

        if (machineId == null) {

            if (account.getSalt_string() != null) {

                throw new BusinessException(1004);
            }

        } else {
            if (!machineId.equals(account.getSalt_string())) {

                throw new BusinessException(1004);
            }
        }

        String innerAuthCode = DigestUtils
                .sha1Hex(account.getSalt_string() + account.getPassword());

        if (innerAuthCode.equals(authCode)) {

            return true;
        }

        throw new BusinessException(1005);
    }

    @Override
    public String getDriverAppLoginOrRegisterText(String username) throws BusinessException {

        Account account = userService.getUserInfoByUsername(username);

        if (account != null && !(userService.isDriver(account.getUid()))) {

            throw new BusinessException(1011, "您不是农机手，无法在此登录");
        }

        try {
            return verifyCodeService.sendVerifyCode(username);
        } catch (ServiceLayerExeption serviceLayerExeption) {
            throw new BusinessException(serviceLayerExeption.getErrorCode(), serviceLayerExeption.getErrorMessage());
        }
    }

    @Override
    public List<Account> getAccountInfoByName(String key) {

        return userService.getUserInfoByName(key);
    }

    @Override
    public void addMachineAuthRequest(Long uid, String machineType, String brand, String
            number, String url)
            throws BusinessException {

        Account account = userService.getUserInfo(uid);

        if (account == null) {
            throw new BusinessException("用户不存在，请尝试重新登录");
        }

        UserMachineModel userMachineModel = userMachineService
                .getAuthedUserMachineModelByThree(machineType, brand,
                        number);
        if (userMachineModel == null) {
            throw new BusinessException("您选的类型不存在");
        }
        UserMachine userMachine = userMachineService.addUserMachine(uid, userMachineModel.getId());

        boolean flag = userMachineService.addUserMachineImage(userMachine.getId(), "车照片",
                aliOssService.getAuthPutUrlFolderByUsername(uid) + url);

        if (!flag) {
            throw new BusinessException("添加用户照片错误");
        }
    }

    @Override
    public void addElseMachineRequest(Long uid, String machineType, String brand, String
            number, String url)
            throws BusinessException {
        // 添加机型model名称什么的，然后再加入进去

        UserMachineModel userMachineModel = userMachineService
                .getUserMachineModelByThree(machineType, brand, number);
        if (userMachineModel == null) {
            userMachineModel = userMachineService.addMachineModel(machineType, brand, number);
        }

        UserMachine userMachine = userMachineService.addUserMachine(uid, userMachineModel.getId());

        boolean flag = userMachineService.addUserMachineImage(userMachine.getId(), "车照片",
                aliOssService.getAuthPutUrlFolderByUsername(uid) + url);

        if (!flag) {
            throw new BusinessException("添加用户照片错误");
        }
    }

    @Override
    public List<String> getMachineBrandList(String machineType) {
        List<UserMachineModel> userMachineModels = userMachineService
                .getAuthedModelListByType(machineType);
        Set<String> uniqueBrand = new HashSet<>();
        for (UserMachineModel userMachineModel : userMachineModels) {
            uniqueBrand.add(userMachineModel.getModel_brand());
        }
        List<String> result = new ArrayList<>();

        result.addAll(uniqueBrand);

        return result;
    }

    @Override
    public List<String> getMachineNumberList(String machineType, String brand) {

        List<UserMachineModel> userMachineModels = userMachineService
                .getAuthedModelListByType(machineType);
        List<String> result = new ArrayList<>();
        for (UserMachineModel userMachineModel : userMachineModels) {
            if (userMachineModel.getModel_brand()
                                .equals(brand)) {
                result.add(userMachineModel.getModel_number());
            }
        }
        return result;
    }

    @Override
    public List<UserMachineType> getMachineTypes() {

        return userMachineService.getAllMachineType();
    }

    @Override
    public List<UserMachine> getUserMachines(Long uid) {

        return userMachineService.getAllMyUserMachines(uid);

    }

    @Override
    public void modifyMachineAuthRequest(Long id, Long uid, String machineType, String
            machineBrand,
                                         String machineNumber, String machinePhotoKey)
            throws BusinessException {
        Account account = userService.getUserInfo(uid);

        if (account == null) {
            throw new BusinessException("用户不存在，请尝试重新登录");
        }

        UserMachineModel userMachineModel = userMachineService
                .getAuthedUserMachineModelByThree(machineType,
                        machineBrand, machineNumber);
        if (userMachineModel == null) {
            throw new BusinessException("您选的类型不存在");
        }

        UserMachine userMachine = userMachineService.getUserMachineById(id);

        if (userMachine == null) {
            throw new BusinessException("没有这个农机，请重试");
        }

        if (userMachine.getState()
                       .equals(UserMachineService.STATE_AUTH_PASSED)) {
            throw new BusinessException("该认证已通过，无需修改");
        }

        userMachine.setMachine_model_id(userMachineModel.getId());
        userMachine.setState(UserMachineService.STATE_WAITTING_AUTH);
        userMachineService.modifyUserMachine(userMachine);

        userMachineService.deleteUserMachineAuthImage(userMachine.getId());

        boolean flag = userMachineService.addUserMachineImage(userMachine.getId(), "车照片",
                aliOssService.getAuthPutUrlFolderByUsername(uid) + machinePhotoKey);

        if (!flag) {
            throw new BusinessException("添加用户照片错误");
        }
    }

    @Override
    public void modifyElseMachineRequest(Long id, Long uid, String machineType, String
            machineBrand,
                                         String machineNumber, String machinePhotoKey)
            throws BusinessException {
        Account account = userService.getUserInfo(uid);

        if (account == null) {
            throw new BusinessException("用户不存在，请尝试重新登录");
        }

        UserMachineModel userMachineModel = userMachineService
                .getUserMachineModelByThree(machineType, machineBrand,
                        machineNumber);
        if (userMachineModel == null) {
            userMachineModel = userMachineService
                    .addMachineModel(machineType, machineBrand, machineNumber);
        }

        UserMachine userMachine = userMachineService.getUserMachineById(id);

        if (userMachine == null) {
            throw new BusinessException("没有这个农机，请重试");
        }

        if (userMachine.getState()
                       .equals(UserMachineService.STATE_AUTH_PASSED)) {
            throw new BusinessException("该认证已通过，无需修改");
        }
        userMachine.setMachine_model_id(userMachineModel.getId());
        userMachine.setState(UserMachineService.STATE_WAITTING_AUTH);
        userMachineService.modifyUserMachine(userMachine);

        userMachineService.deleteUserMachineAuthImage(userMachine.getId());

        boolean flag = userMachineService.addUserMachineImage(userMachine.getId(), "车照片",
                aliOssService.getAuthPutUrlFolderByUsername(uid) + machinePhotoKey);

        if (!flag) {
            throw new BusinessException("添加用户照片错误");
        }

    }

    @Override
    public void removeUserMachine(Long id, Long uid) throws BusinessException {
        UserMachine userMachine = userMachineService.getUserMachineById(id);
        if (uid.equals(userMachine.getUid())) {
            if (userMachineService.deleteUserMachine(id) == 1)
                return;
            throw new BusinessException("删除车辆失败");
        }
        throw new BusinessException("该农机不属于你");
    }

}
