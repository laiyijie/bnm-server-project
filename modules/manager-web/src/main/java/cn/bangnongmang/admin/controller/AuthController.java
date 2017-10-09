package cn.bangnongmang.admin.controller;

import static cn.bangnongmang.admin.enums.ServerSessionAttr.USER;

import cn.bangnongmang.admin.service.UserService;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.outerservice.impl.AliOssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.enums.RealNameEnum;
import cn.bangnongmang.admin.enums.UserModelStateEnum;
import cn.bangnongmang.admin.io.wechat.model.UserInfo;
import cn.bangnongmang.admin.io.wechat.model.UserMachineInfoSimple;
import cn.bangnongmang.admin.service.impl.MachineAuthService;
import cn.bangnongmang.admin.service.impl.RealNameAuthService;

import cn.bangnongmang.admin.service.show.OutputAdapter;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineCriteria;

/**
 * 实名认证和机器认证的接口
 *
 * @author laiyijie
 * @ClassName AuthController
 * @date 2016年12月24日 下午3:31:26
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RealNameAuthService realNameAuthService;

    @Autowired
    private MachineAuthService machineAuthService;

    @Autowired
    private UserMachineMapper userMachineMapper;

    @Autowired
    private AliOssService aliOssService;

    @Autowired
    private OutputAdapter outputAdapter;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    /**
     * 获取实名认证人的列表
     *
     * @param currentPage 需要查询的页码 默认 1
     * @param pageSize    每页显示的数量 默认 20
     * @param type        需要查询的类型 waittingAuth:等待认证 authed：已认证 denied：被拒绝 pageSize：每页的数量
     *                    默认 waittingAuth
     * @return
     * @throws BusinessException
     * @Title getRealNameAuthUserList
     */
    @RequestMapping("/getRealNameAuthUserList")
    public PageResult<UserBasicInfoShow> getRealNameAuthUserList(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "type", defaultValue = "waittingAuth") String type) throws BusinessException {

        Integer state = 0;
        if ("denied".equals(type)) {
            state = RealNameEnum.REAL_NAME_AUTH_STATE_INIT.getCurrencyCode();
        } else if ("waittingAuth".equals(type)) {
            state = RealNameEnum.REAL_NAME_AUTH_STATE_WAITTING_AUTH.getCurrencyCode();
        } else if ("authed".equals(type)) {
            state = RealNameEnum.REAL_NAME_AUTH_STATE_PASS.getCurrencyCode();
        }
        if (state == 0) {
            throw new BusinessException("state错误");
        }

        PageHelper.startPage(currentPage, pageSize);

        Page<UserBasicInfoShow> realNameAuths = (Page<UserBasicInfoShow>) userBasicInfoShowMapper
                .selectRealNameAuthListByType(state);

        return new PageResult<UserBasicInfoShow>(realNameAuths);
    }

    /**
     * 通过名字或手机号搜索实名认证用户(需要完全匹配)
     *
     * @param key
     * @param currentPage
     * @param pageSize
     * @return
     * @Title searchRealNameAuthUserList
     */
    @RequestMapping("/searchRealNameAuthUserList")
    public PageResult<UserBasicInfoShow> searchRealNameAuthUserList(@RequestParam("key") String key,
                                                                    @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(currentPage, pageSize);

        Page<UserBasicInfoShow> pages = (Page<UserBasicInfoShow>) userBasicInfoShowMapper
                .searchRealNameAuthListByNameOrPhone(key);

        return new PageResult<UserBasicInfoShow>(pages);

    }

    /**
     * 获取机器认证列表
     *
     * @param currentPage 需要查询的页码 默认 1
     * @param pageSize    每页显示的数量 默认 20
     * @param type        需要查询的类型 waittingAuth:等待认证 authed：已认证 denied：被拒绝 pageSize：每页的数量
     *                    默认 waittingAuth
     * @return
     * @Title getMachineAuthUserList
     */
    @RequestMapping("/getMachineAuthUserList")
    public PageResult<UserMachineInfoSimple> getMachineAuthUserList(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "type", defaultValue = "waittingAuth") String type) {

        UserMachineCriteria userMachineCriteria = new UserMachineCriteria();

        if ("denied".equals(type)) {
            userMachineCriteria.or()
                               .andStateEqualTo(
                                       UserModelStateEnum.USER_MODEL_STATE_INIT.getCurrencyCode());
        } else if ("waittingAuth".equals(type)) {
            userMachineCriteria.or()
                               .andStateEqualTo(
                                       UserModelStateEnum.USER_MODEL_STATE_WAITING_AUTH.getCurrencyCode());

        } else if ("authed".equals(type)) {
            userMachineCriteria.or()
                               .andStateEqualTo(
                                       UserModelStateEnum.USER_MODEL_STATE_AUTHED.getCurrencyCode());
        }

        userMachineCriteria.setOrderByClause("id desc");
        PageHelper.startPage(currentPage, pageSize);
        Page<UserMachine> userMachines = (Page<UserMachine>) userMachineMapper.selectByExample(
                userMachineCriteria);
        return new PageResult<UserMachineInfoSimple>(userMachines,
                outputAdapter.convertToUserMachineInfoSimple(userMachines));
    }

    /**
     * 搜索机器（只能通过手机号查询）
     *
     * @param key
     * @param currentPage
     * @param pageSize
     * @return
     * @Title searchMachineAuthUserList
     */
    @RequestMapping("/searchMachineAuthUserList")
    public PageResult<UserMachine> searchMachineAuthUserList(@RequestParam("key") String key,
                                                             @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        Account account = userService.getAccountByUsername(key);
        UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
        userMachineCriteria.or()
                           .andUidEqualTo(account.getUid());
        userMachineCriteria.setOrderByClause("id desc");
        PageHelper.startPage(currentPage, pageSize);
        Page<UserMachine> userMachines = (Page<UserMachine>) userMachineMapper.selectByExample(
                userMachineCriteria);
        return new PageResult<UserMachine>(userMachines);
    }

    /**
     * 通过实名认证
     *
     * @param authUsername
     * @param operator     操作员（无需上传）
     * @return
     * @throws BusinessException
     * @Title authRealNameInfo
     */
    @RequestMapping("/authRealNameInfo")
    public String authRealNameInfo(@RequestParam("authUsername") String authUsername,
                                   @SessionAttribute(USER) String operator) throws BusinessException {

        if (realNameAuthService.authRealName(authUsername, operator)) {
            return "success";
        }

        throw new BusinessException();
    }

    /**
     * 拒绝提交的实名认证
     *
     * @param authUsername
     * @param reason
     * @param operator（操作员，无需上传)
     * @return
     * @throws BusinessException
     * @Title denyRealNameInfo
     */
    @RequestMapping("/denyRealNameInfo")
    public String denyRealNameInfo(@RequestParam("authUsername") String authUsername,
                                   @RequestParam("reason") String reason,
                                   @SessionAttribute(USER) String operator) throws BusinessException {
        if (realNameAuthService.denyRealName(authUsername, operator, reason)) {
            return "success";
        }

        throw new BusinessException();
    }

    /**
     * 认证机器，如果机器的品牌无效则不可认证，需要先进行品牌处理
     *
     * @param userMachineId
     * @param operator      无需上传
     * @return
     * @throws BusinessException
     * @Title authMachine
     */
    @RequestMapping("/authMachine")
    public String authMachine(@RequestParam("userMachineId") Long userMachineId,
                              @SessionAttribute(USER) String operator) throws BusinessException {

        if (machineAuthService.authMachine(userMachineId, operator)) {

            return "success";
        }

        throw new BusinessException();

    }

    /**
     * 驳回机器认证，需要驳回的理由s
     *
     * @param userMachineId
     * @param reason
     * @param operator      无需上传
     * @return
     * @throws BusinessException
     * @Title denyMachine
     */
    @RequestMapping("/denyMachine")
    public String denyMachine(@RequestParam("userMachineId") Long userMachineId,
                              @RequestParam("reason") String reason,
                              @SessionAttribute(USER) String operator) throws BusinessException {

        if (machineAuthService.denyMachine(userMachineId, operator, reason)) {
            return "success";
        }

        throw new BusinessException();
    }

    /**
     * 认证通过新的model
     *
     * @param userMachineModelId
     * @param operator           无需上传
     * @return
     * @throws BusinessException
     * @Title authModel
     */
    @RequestMapping("/authModel")
    public String authModel(@RequestParam("userMachineModelId") Long userMachineModelId,
                            @SessionAttribute(USER) String operator) throws BusinessException {

        if (machineAuthService.authUserMachineModel(userMachineModelId, operator)) {
            return "success";
        }

        throw new BusinessException();
    }

    /**
     * 修改model
     *
     * @param id
     * @param type
     * @param brand
     * @param number
     * @param power
     * @param width
     * @param moreInfoUrl
     * @param specailInfo
     * @param operator    操作者
     * @return
     * @throws BusinessException
     * @Title modifyModel
     */
    @RequestMapping("/modifyModel")
    public String modifyModel(@RequestParam("id") Long id, @RequestParam("type") String type,
                              @RequestParam("brand") String brand,
                              @RequestParam("number") String number,
                              @RequestParam(value = "power", required = false) Double power,
                              @RequestParam(value = "width", required = false) Double width,
                              @RequestParam(value = "moreInfoUrl", required = false) String moreInfoUrl,
                              @RequestParam(value = "specialInfo", required = false) String specailInfo,
                              @SessionAttribute(USER) String operator) throws BusinessException {

        if (machineAuthService.modifyModelById(id, type, brand, number, power, width, moreInfoUrl,
                specailInfo,
                operator)) {
            return "success";
        }

        throw new BusinessException();
    }

    /**
     * 将当前model合并至另一个model
     *
     * @param currentModelId
     * @param dstModelId
     * @param operator
     * @return
     * @throws BusinessException
     * @Title combineToExistModel
     */
    @RequestMapping("/combineToExistModel")
    public String combineToExistModel(@RequestParam("currentModelId") Long currentModelId,
                                      @RequestParam("dstModelId") Long dstModelId,
                                      @SessionAttribute(USER) String operator)
            throws BusinessException {

        if (machineAuthService.combineModel(currentModelId, dstModelId, operator)) {
            return "success";
        }

        throw new BusinessException();
    }

    /**
     * 拒绝并删除一个model
     *
     * @param currentModelId
     * @param operator
     * @return
     * @throws BusinessException
     * @Title denyAndDeleteModel
     */
    @RequestMapping("/denyAndDeleteModel")
    public String denyAndDeleteModel(@RequestParam("currentModelId") Long currentModelId,
                                     @SessionAttribute(USER) String operator) throws BusinessException {

        boolean flag = machineAuthService.denyAndDeleteMachineModel(currentModelId, operator);
        if (flag) {
            return "sucess";
        }
        throw new BusinessException();
    }

    /**
     * 获取ali的bucket读取权限
     *
     * @return
     * @Title getAliOssCred
     */
    @RequestMapping("/getAliOssCred")
    public Credentials getAliOssCred() {

        AliOssServiceImpl.PolicyBuilder policyBuilder = new AliOssServiceImpl.PolicyBuilder();

        policyBuilder.addAllowGet(AliOssService.IMG_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.HEAD_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.RECORD_BUCKET_NAME, "");

        policyBuilder.addAllowPut(AliOssService.RECORD_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.WORK_SIZE_AUTH_BUCKET_NAME, "");

        policyBuilder.addAllowGet(AliOssService.ORDER_IMG_BUCKET_NAME, "");

        policyBuilder.addAllowPut(AliOssService.ORDER_IMG_BUCKET_NAME, "");

        return aliOssService.getCridentialsByPolicy(policyBuilder.build(), "manager");
    }

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     * @throws BusinessException
     * @Title getUserinfo
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(
            @RequestParam("username") String username) throws BusinessException {
        return outputAdapter.getUserInfo(username);
    }

}
