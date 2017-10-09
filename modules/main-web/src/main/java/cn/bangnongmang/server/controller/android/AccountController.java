package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.SESSION_UID;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.driverapp.models.UserGeoInfo;
import cn.bangnongmang.driverapp.models.MyUserInfo;
import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.server.business.common.DriverAccountBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidUserShow;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.service.service.impl.TextMessageSender;

/**
 * 安卓机手端控制账户相关操作的接口 "/account/*"
 *
 * @author laiyijie
 * @ClassName AccountController
 * @date 2016年12月17日 下午4:55:09
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private DriverAccountBusiness driverAccountBusiness;

    @Autowired
    private AccountBusiness accountBusiness;

    @Autowired
    private AndroidUserShow androidUserShow;

    /**
     * 获取登录验证码
     *
     * @param androidRequest 传入数据为手机号{phone:String}
     * @return 成功则返回下次的请求时间 {nextTime:Long} 否则抛异常
     * @throws AndroidOutputException
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/getLoginText")
    public Map<String, Long> getLoginText(@RequestBody AndroidRequest androidRequest)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> in = androidRequest.resolveJsonParams();

        Map<String, Long> re = new HashMap<>();

        String username = (String) in.get("phone");

        driverAccountBusiness.getDriverAppLoginOrRegisterText(username);

        re.put("nextTime", TextMessageSender.INTERVAL * 1000L);

        return re;
    }

    /**
     * 安卓端农机手注册
     *
     * @param androidRequest 输入为{phone:String,authCode:String,machineId:String}
     * @param httpSession    获取session信息（无需关心）
     * @return 成功返回用户信息，否则返回null或抛出异常
     * @throws AndroidOutputException
     * @throws BusinessException
     */

    @ResponseBody
    @RequestMapping("/userRegister")
    public MyUserInfo userRegister(@RequestBody AndroidRequest androidRequest,
                                   HttpSession httpSession)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> in = androidRequest.resolveJsonParams();

        String username = (String) in.get("phone");
        String authCode = (String) in.get("authCode");
        String machineId = (String) in.get("machineId");

        Account account = driverAccountBusiness.register(username, authCode, machineId);

        MyUserInfo userInfo = androidUserShow.getMyUserInfo(username);
        if (userInfo != null) {
            httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, username);
            httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());
            return userInfo;
        }
        return null;
    }

    /**
     * 农机手登录
     *
     * @param androidRequest {phone:String,authCode:String,machineId:String}
     * @param httpSession
     * @return 成功则返回用户信息，否则抛错
     * @throws AndroidOutputException
     */
    @ResponseBody
    @RequestMapping("/userLogin")
    public MyUserInfo userLogin(@RequestBody AndroidRequest androidRequest, HttpSession httpSession)
            throws AndroidOutputException {

        Map<String, Object> in = androidRequest.resolveJsonParams();

        String username = (String) in.get("phone");
        String authCode = (String) in.get("authCode");
        String machineId = (String) in.get("machineId");

        boolean flag;
        try {
            flag = driverAccountBusiness.androidNormalLogin(username, authCode, machineId);

            if (flag) {
                Account account = accountBusiness.getUserInfoByUsername(username);
                httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, username);
                httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());
            }

            return androidUserShow.getMyUserInfo(username);

        } catch (BusinessException e) {
            if (e.errorCode == 1002) {
                throw new AndroidOutputException(1002, "账户不存在");
            } else if (e.errorCode == 1001) {
                throw new AndroidOutputException("登录失败，请检查您的验证码是否正确");
            } else {
                throw new AndroidOutputException("登录失败");
            }
        }
    }

    /**
     * 农机手自动登录
     *
     * @param androidRequest {phone:String,authCode:String,machineId:String}
     * @param httpSession
     * @return 成功则返回UserInfo，否则返回null或抛错
     * @throws AndroidOutputException
     * @throws BusinessException
     */

    @ResponseBody
    @RequestMapping("/userAutoLogin")
    public MyUserInfo userAutoLogin(@RequestBody AndroidRequest androidRequest,
                                    HttpSession httpSession)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> in = androidRequest.resolveJsonParams();

        String username = (String) in.get("phone");
        String authCode = (String) in.get("authCode");
        String machineId = (String) in.get("machineId");

        boolean flag;
        flag = driverAccountBusiness.userAutoLogin(username, authCode, machineId);

        if (flag) {
            Account account = accountBusiness.getUserInfoByUsername(username);
            httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, username);
            httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());
            return androidUserShow.getMyUserInfo(username);
        }

        return null;

    }

    /**
     * 刷新用户信息（自己的信息）
     *
     * @param httpSession
     * @return 成功则返回UserInfo 否则抛错
     * @throws BusinessException
     * @throws AndroidOutputException
     */
    @ResponseBody
    @RequestMapping("/getMyUserInfo")
    public MyUserInfo getMyUserInfo(HttpSession httpSession)
            throws BusinessException, AndroidOutputException {

        String username = (String) httpSession.getAttribute("username");

        return androidUserShow.getMyUserInfo(username);
    }

    /**
     * 获取当前服务器系统时间戳
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTimeStamp")
    public Long getTimeStamp() {

        return System.currentTimeMillis();

    }

    /**
     * 设置头像链接
     *
     * @param androidRequest {filename:String} 只需要上传文件的名称，就是在允许的文件夹下创建的新的文件的名称，例如授权文件夹为
     *                       'xxx/xxx/xx/'上传的图片为'xxx/xxx/xx/abc.png'只需要上传'abc.png'
     * @param uid
     * @return
     * @throws AndroidOutputException
     */
    @ResponseBody
    @RequestMapping("/setPortrait")
    public Object setPortrait(@RequestBody AndroidRequest androidRequest,
                              @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();
        String filename = (String) params.get("filename");
        boolean flag = accountBusiness.setPortraitUrl(uid, filename);
        if (flag) {
            return "success";
        }
        return "failed";
    }

    /**
     * 获取阿里认证的鉴权信息（可以获取所有 bnm-head 的get权限和 bnm-head put 到自己文件夹的权限）自己的文件夹key前缀请通过
     * getAliOssAuthHeadImgPath
     *
     * @param uid (需要登录，根据登录的用户名来获取权限)
     * @return
     */

    @ResponseBody
    @RequestMapping("/getAliOssAuthenticationInfo")
    public Credentials getAliOssAuthenticationInfo(
            @SessionAttribute(SESSION_UID) Long uid) {

        return accountBusiness.getAliOssCredentials(uid, "android");
    }

    /**
     * 获取具有put权限的图片前缀，用于头像上传
     *
     * @param uid
     * @return 返回String 例如： "xxxx/xxxx/xxxx/"
     */

    @ResponseBody
    @RequestMapping("/getAliOssAuthHeadImgPath")
    public String getAliOssAuthHeadImgPath(@SessionAttribute(SESSION_UID) Long uid) {

        return accountBusiness.getPortaitPutUrl(uid);
    }

    /**
     * 获取具有put权限的图片前缀，用于idCard图片上传
     *
     * @param uid
     * @return 返回String 例如： "xxxx/xxxx/xxxx/"
     * @Title getAliOssAuthIdCardImagePath
     */
    @ResponseBody
    @RequestMapping("/getAliOssAuthIdCardImagePath")
    public String getAliOssAuthIdCardImagePath(
            @SessionAttribute(SESSION_UID) Long uid) {

        return accountBusiness.getImagePutUrl(uid);
    }

    /**
     * 更新用户的位置坐标
     *
     * @param uid
     * @param request  入参请参阅
     * @return 成功返回true 失败返回false
     * @see cn.bangnongmang.driverapp.models.UserGeoInfo
     */
    @ResponseBody
    @RequestMapping("/updateUserGeo")
    public String updateUserGeo(@SessionAttribute(SESSION_UID) Long uid,
                                @RequestBody AndroidRequest request) {
        Account account = accountBusiness.getUserInfo(uid);
        UserGeoInfo userGeo = request.resolveJsonParams(UserGeoInfo.class);

        AccountGeo accountGeo = new AccountGeo();

        accountGeo.setAddress(userGeo.getAddress());
        accountGeo.setCity(userGeo.getCity());
        accountGeo.setDistrict(userGeo.getDistrict());
        accountGeo.setLatitude(userGeo.getLatitude());
        accountGeo.setLongitude(userGeo.getLongitude());
        accountGeo.setProvince(userGeo.getProvince());
        accountGeo.setStreet(userGeo.getStreet());
        accountGeo.setStreet_number(userGeo.getStreetNumber());
        accountGeo.setUid(account.getUid());

        if (accountBusiness.updateAccountGeoInfo(accountGeo)) {
            return "success";
        }
        return "failed";
    }

    /**
     * 实名认证
     *
     * @param uid
     * @param request  {name:String,idNumber:String,upSideUrl:String,downSideUrl:
     *                 String}
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title realNameAuth
     */
    @ResponseBody
    @RequestMapping("/realNameAuth")
    public String realNameAuth(@SessionAttribute(SESSION_UID) Long uid,
                               @RequestBody AndroidRequest request)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = request.resolveJsonParams();

        String name = (String) params.get("name");
        String idNumber = (String) params.get("idNumber");
        String upSideUrl = (String) params.get("upSideUrl");
        String downSideUrl = (String) params.get("downSideUrl");

        boolean flag = accountBusiness
                .realNameCertificate(uid, name, idNumber, upSideUrl, downSideUrl);

        if (flag) {
            return "success";
        }
        throw new AndroidOutputException("提交认证失败");
    }

}
