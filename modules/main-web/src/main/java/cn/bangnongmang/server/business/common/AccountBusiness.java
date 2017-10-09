package cn.bangnongmang.server.business.common;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.server.io.BusinessException;

/**
 * 基本的用户账户控制逻辑
 *
 * @author laiyijie
 * @ClassName CommonAccountBusiness
 * @date 2016年12月17日 下午4:39:11
 */
public interface AccountBusiness {

    /**
     * 根据用户名返回用户的信息
     *
     * @param username 用户名
     * @return
     * @Title getUserinfo
     * @see Account
     */
    Account getUserInfoByUsername(String username);


    /**
     * 根据用户ID返回用户的信息
     *
     * @param uid 用户id
     * @return
     * @Title getUserinfo
     * @see Account
     */
    Account getUserInfo(Long uid);


    /**
     * 根据unionid来获取用户
     *
     * @param unionid
     * @return
     * @Title getUserInfoByUnionId
     */
    Account getUserInfoByUnionId(String unionid);

    /**
     * 获取验证码
     *
     * @param phone
     * @return
     * @throws BusinessException
     * @Title getCommonVerifyCode
     */
     String getCommonVerifyCode(String phone) throws BusinessException;

    /**
     * 注册，需要传入是农户还是农机手
     *
     * @param username
     * @param authCode
     * @param type
     * @param unionid
     * @param openid
     * @return
     * @throws BusinessException
     * @Title commonRegister
     */
     Account commonRegister(String username, String authCode, String type, String unionid,
                                  String openid)
            throws BusinessException;

    /**
     * 根域手机号和用户名登录
     *
     * @param username
     * @param authCode
     * @return
     * @throws BusinessException
     * @Title commonLogin
     */
     Account commonLogin(String username, String authCode) throws BusinessException;

    /**
     * 绑定微信的信息
     *
     * @param uid
     * @param unionid
     * @param openid
     * @return
     * @Title updateWechatInfo
     */

     boolean updateWechatInfo(Long uid, String unionid, String openid);

    /**
     * 设置头像的链接，会删掉原来的头像图片
     *
     * @param uid
     * @param filename
     * @return
     * @Title setPortraitUrl
     */
     boolean setPortraitUrl(Long uid, String filename);

    /**
     * 获取基本的权限，权限包括： bnm-head的getobject权限，bnm-image和bnm-head的个人文件夹上传权限
     *
     * @param uid
     * @param sessionName
     * @return
     * @Title getAliOssCredentials
     */
     Credentials getAliOssCredentials(Long uid, String sessionName);

    /**
     * 获取alioss头像权限的前缀
     *
     * @param uid
     * @return "xxx/xxx/xx/"
     * @Title getPortaitPutUrl
     */
     String getPortaitPutUrl(Long uid);

    /**
     * 更新用户的地理位置信息
     *
     * @param accountGeo
     * @return
     * @Title updateAccountGeoInfo
     */
     boolean updateAccountGeoInfo(AccountGeo accountGeo);

    /**
     * 获取alioss上传图像权限的前缀
     *
     * @param uid
     * @return
     * @Title getImagePutUrl
     */
     String getImagePutUrl(Long uid);

    /**
     * 实名认证
     *
     * @param uid
     * @param name
     * @param idNumber
     * @param upSide
     * @param downSide
     * @return
     * @throws BusinessException
     * @Title realNameCertificate
     */
     boolean realNameCertificate(Long uid, String name, String idNumber, String upSide,
                                       String downSide)
            throws BusinessException;

    /**
     * 获取实名认证信息
     *
     * @param uid
     * @return
     * @Title getRealNameAuthInfo
     */
     AccountRealNameAuth getRealNameAuthInfo(Long uid);
}
