package cn.bangnongmang.server.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.server.business.common.DriverAccountBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.wechat.DomainToWxOutputAdapter;
import cn.bangnongmang.server.io.wechat.WxOutputException;
import cn.bangnongmang.server.io.wechat.data.UserMachineAuthInfo;
import cn.bangnongmang.server.io.wechat.data.UserMachineInfo;
import cn.bangnongmang.server.io.wechat.show.WxMachineShow;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineType;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户相关接口 "/wx/account/*"
 *
 * @author laiyijie
 * @ClassName AccountController
 * @date 2016年12月21日 上午10:40:56
 */

@Controller("wxAccountController")
@RequestMapping("/wx/account")
public class AccountController {

    @Autowired
    private AccountBusiness commonAccountBusiness;
    @Autowired
    private DriverAccountBusiness driverAccountBusiness;
    @Autowired
    private DomainToWxOutputAdapter adapter;

    @Autowired
    private WxMachineShow wxMachineShow;

    /**
     * 获取用户的信息
     *
     * @param uid
     * @return 成功则返回Account，后续需要修改。
     * @Title getUserinfo
     */

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Account getUserinfo(@SessionAttribute(value = SESSION_UID) Long uid) {
        Account account = commonAccountBusiness.getUserInfo(uid);
        return account;
    }

    /**
     * 获取用户机器信息,更改接口，旧版废弃不可用
     *
     * @param uid
     * @return
     * @Title getUserMachineInfo
     */
    @RequestMapping("/getUserMachineInfo")
    @ResponseBody
    public List<UserMachineInfo> getUserMachineInfo(@SessionAttribute(SESSION_UID) Long uid) {

        return adapter.getUserMachineInfo(uid);
    }

    /**
     * 新增接口，用来认证农机
     *
     * @param machineType
     * @param machineBrand
     * @param machineNumber
     * @param machinePhotoKey
     * @param uid
     * @return
     * @throws BusinessException
     * @Title driverAuthMachine
     */

    @RequestMapping("/driverAuthMachine")
    @ResponseBody
    public String driverAuthMachine(@RequestParam("machineType") String machineType,
                                    @RequestParam("machineBrand") String machineBrand,
                                    @RequestParam("machineNumber") String machineNumber,
                                    @RequestParam("machinePhotoKey") String machinePhotoKey,
                                    @SessionAttribute(SESSION_UID) Long uid,
                                    @RequestParam(value = "id", required =
                                            false)
                                            Long id)
            throws BusinessException {

        if (id == null) {
            driverAccountBusiness
                    .addMachineAuthRequest(uid, machineType, machineBrand, machineNumber,
                            machinePhotoKey);
        } else {
            driverAccountBusiness
                    .modifyMachineAuthRequest(id, uid, machineType, machineBrand, machineNumber,
                            machinePhotoKey);
        }

        return "success";

    }

    /**
     * 如果在机器品牌或者机器型号选择了其他并额外填写，请使用这个接口进行上传
     *
     * @param machineType
     * @param machineBrand
     * @param machineNumber
     * @param machinePhotoKey
     * @param uid
     * @return
     * @throws BusinessException
     * @Title driverAuthElseMachine
     */
    @RequestMapping("/driverAuthElseMachine")
    @ResponseBody
    public String driverAuthElseMachine(@RequestParam("machineType") String machineType,
                                        @RequestParam("machineBrand") String machineBrand,
                                        @RequestParam("machineNumber") String machineNumber,
                                        @RequestParam("machinePhotoKey") String machinePhotoKey,
                                        @SessionAttribute(SESSION_UID) Long uid,
                                        @RequestParam(value = "id", required = false) Long id)
            throws BusinessException {
        if (id == null) {
            driverAccountBusiness
                    .addElseMachineRequest(uid, machineType, machineBrand, machineNumber,
                            machinePhotoKey);
        } else {
            driverAccountBusiness.modifyElseMachineRequest(id, uid, machineType, machineBrand,
                    machineNumber,
                    machinePhotoKey);
        }

        return "success";

    }

    /**
     * 获取Oss认证信息
     *
     * @param uid
     * @return private String securityToken; private String accessKeySecret;
     * private String accessKeyId; private String expiration;
     * @Title getOssAuthKey
     */

    @RequestMapping("/getOssAuthKey")
    @ResponseBody
    public Credentials getOssAuthKey(@SessionAttribute(SESSION_UID) Long uid) {

        return commonAccountBusiness.getAliOssCredentials(uid, "wechat");
    }

    /**
     * 获取认证的上传图片path
     *
     * @param uid
     * @return "xxx/x/xx/"
     * @Title getOssAuthPath
     */
    @RequestMapping("/getOssAuthPath")
    @ResponseBody
    public String getOssAuthPath(@SessionAttribute(SESSION_UID) Long uid) {

        return commonAccountBusiness.getImagePutUrl(uid);
    }

    /**
     * 在没有成功通过之前可以修改，用同一个接口
     *
     * @param name        真实姓名
     * @param id_number   身份证号
     * @param upsideUrl   身份证正面url
     * @param downSideUrl 身份证反面url
     * @param uid    -- 无需传入
     * @return
     * @throws WxOutputException
     * @throws BusinessException
     * @Title realNameAuth
     */
    @RequestMapping("/realNameAuth")
    @ResponseBody
    public String realNameAuth(@RequestParam("name") String name,
                               @RequestParam("id_number") String id_number,
                               @RequestParam("upsideUrl") String upsideUrl,
                               @RequestParam("downSideUrl") String downSideUrl,
                               @SessionAttribute(SESSION_UID) Long uid)
            throws WxOutputException, BusinessException {

        if (commonAccountBusiness
                .realNameCertificate(uid, name, id_number, upsideUrl, downSideUrl)) {
            return "success";
        }

        throw new WxOutputException("提交实名认证失败");
    }

    /**
     * 获取所有机器类型
     *
     * @return
     * @Title getMachineType
     */

    @RequestMapping("/getMachineType")
    @ResponseBody
    public List<String> getMachineType() {
        List<UserMachineType> types = driverAccountBusiness.getMachineTypes();
        List<String> result = new ArrayList<>();
        for (UserMachineType type : types) {
            result.add(type.getType_name());
        }
        return result;
    }

    /**
     * 根据用户选的机器类型来获取品牌信息
     *
     * @param machineType
     * @return
     * @Title getBrandByType
     */

    @RequestMapping("/getBrandByType")
    @ResponseBody
    public List<String> getBrandByType(@RequestParam("machineType") String machineType) {

        return driverAccountBusiness.getMachineBrandList(machineType);
    }

    /**
     * 根据用户选择的机器类型和品牌来获取型号信息
     *
     * @param machineType
     * @param brand
     * @return
     * @Title getMachineNumberByTypeAndBrand
     */
    @RequestMapping("/getMachineNumberByTypeAndBrand")
    @ResponseBody
    public List<String> getMachineNumberByTypeAndBrand(
            @RequestParam("machineType") String machineType,
            @RequestParam("brand") String brand) {

        return driverAccountBusiness.getMachineNumberList(machineType, brand);
    }

    /**
     * 获取实名认证的信息
     *
     * @param uid
     * @return
     * @Title getRealNameAuthInfo
     */
    @RequestMapping("/getRealNameAuthInfo")
    @ResponseBody
    public AccountRealNameAuth getRealNameAuthInfo(
            @SessionAttribute(SESSION_UID) Long uid) {

        return commonAccountBusiness.getRealNameAuthInfo(uid);
    }

    /**
     * 获取用户的机器认证信息
     *
     * @param uid
     * @return
     * @Title getMachineAuthInfos
     */
    @RequestMapping("/getMachineAuthInfos")
    @ResponseBody
    public List<UserMachineAuthInfo> getMachineAuthInfos(
            @SessionAttribute(SESSION_UID) Long uid) {

        List<UserMachine> userMachines = driverAccountBusiness.getUserMachines(uid);

        return adapter.convertUserMachinesToUserMachineInfos(userMachines);
    }

    /**
     * 根据用户的机器ID获取认证信息
     *
     * @param uid
     * @param machineId
     * @return
     * @throws BusinessException
     * @Title getMachineAuthInfoById
     */
    @RequestMapping("/getMachineAuthInfoById")
    @ResponseBody
    public UserMachineAuthInfo getMachineAuthInfoById(
            @SessionAttribute(SESSION_UID) Long uid,
            @RequestParam("machineId") Long machineId) throws BusinessException {

        UserMachineAuthInfo userMachineAuthInfo = wxMachineShow
                .getUserMachineAuthInfoById(uid, machineId);
        if (userMachineAuthInfo != null) {
            return userMachineAuthInfo;
        }

        throw new BusinessException("你没有这辆车");

    }
}
