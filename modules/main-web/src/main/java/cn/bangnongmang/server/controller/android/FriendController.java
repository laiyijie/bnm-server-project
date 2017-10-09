package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;
import java.util.Map;

import cn.bangnongmang.server.business.common.AccountBusiness;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.driverapp.models.FriendDetail;
import cn.bangnongmang.driverapp.models.FriendRequest;
import cn.bangnongmang.driverapp.models.FriendSimple;
import cn.bangnongmang.driverapp.models.UserInfoDetail;
import cn.bangnongmang.driverapp.models.UserInfoSimple;
import cn.bangnongmang.server.business.common.FriendBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidFriendShow;
import cn.bangnongmang.server.io.android.show.AndroidUserShow;

/**
 * 机手好友接口 /friends/*
 *
 * @author laiyijie
 * @ClassName FriendController
 * @date 2016年12月20日 上午10:51:13
 */

@Controller("driverFriendController")
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendBusiness commonFriendBusiness;

    @Autowired
    private AndroidUserShow userShow;
    @Autowired
    private AndroidFriendShow androidFriendShow;

    @Autowired
    private AccountBusiness accountBusiness;

    private final transient Logger logger = LogManager.getLogger(FriendController.class);

    /**
     * TODO 获取还有列表，还需要增加车辆的信息
     *
     * @param uid
     * @return
     * @Title getFriendsList
     */
    @ResponseBody
    @RequestMapping("/getFriendsList")
    public List<FriendSimple> getFriendsList(@SessionAttribute(SESSION_UID) Long uid) {

        return androidFriendShow.getFriendListByUid(uid);
    }

    /**
     * 发送好友请求
     *
     * @param androidRequest 入参为 {phone:String,ps:String}分别代表需要添加的好友的手机号和请求信息
     * @param uid
     * @return 成功返回 success否则抛错
     * @throws BusinessException
     * @throws AndroidOutputException
     * @Title sendFriendRequest
     */
    @ResponseBody
    @RequestMapping("/sendFriendRequest")
    public String sendFriendRequest(@RequestBody AndroidRequest androidRequest,
                                    @SessionAttribute(SESSION_UID) Long uid)
            throws BusinessException, AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String toUser = (String) params.get("phone");

        String ps = (String) params.get("ps");

        FriendshipRequest request = commonFriendBusiness.sendFriendRequest(uid, accountBusiness
                        .getUserInfoByUsername(toUser).getUid(),
                ps);

        if (request == null) {
            throw new AndroidOutputException("发送好友请求失败，请重试");
        }

        return "success";

    }

    /**
     * 同意好友申请接口
     *
     * @param androidRequest 入参为{phone:String} 你同意的向你申请的好友的username（手机号）
     * @param uid
     * @return
     * @throws BusinessException
     * @throws AndroidOutputException
     * @Title acceptFriendRequest
     */
    @ResponseBody
    @RequestMapping("/acceptFriendRequest")
    public String acceptFriendRequest(@RequestBody AndroidRequest androidRequest,
                                      @SessionAttribute(SESSION_UID) Long uid)
            throws BusinessException, AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String phone = (String) params.get("phone");

        Friendship friendship = commonFriendBusiness.acceptFriendRequest(accountBusiness
                .getUserInfoByUsername(phone).getUid(), uid);

        if (friendship == null) {
            throw new AndroidOutputException("添加好友失败，请重试");
        }

        return "success";

    }

    /**
     * 删除好友请求，将对方的好友请求删除，需要重新申请才行
     *
     * @param androidRequest 入参为{phone:String} 你同意的向你申请的好友的username（手机号）
     * @param uid
     * @return 成功返回 success 否则抛错
     * @throws BusinessException
     * @throws AndroidOutputException
     * @Title deleteFriendRequest
     */

    @ResponseBody
    @RequestMapping("/deleteFriendRequest")
    public String deleteFriendRequest(@RequestBody AndroidRequest androidRequest,
                                      @SessionAttribute(SESSION_UID) Long uid)
            throws BusinessException, AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String phone = (String) params.get("phone");

        boolean flag = commonFriendBusiness.deleteFrienshipRequest(accountBusiness
                .getUserInfoByUsername(phone).getUid(), uid);

        if (!flag) {
            throw new AndroidOutputException("删除好友请求失败，请重试");
        }

        return "success";

    }

    /**
     * 删除好友
     *
     * @param androidRequest 入参为{phone:String} 你同意的向你申请的好友的username（手机号）
     * @param uid
     * @return
     * @throws BusinessException
     * @throws AndroidOutputException
     * @Title deleteFriend
     */
    @ResponseBody
    @RequestMapping("/deleteFriend")
    public Object deleteFriend(@RequestBody AndroidRequest androidRequest,
                               @SessionAttribute(SESSION_UID) Long uid)
            throws BusinessException, AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String phone = (String) params.get("phone");

        boolean flag = commonFriendBusiness.deleteFriend(uid, accountBusiness
                .getUserInfoByUsername(phone).getUid());

        if (!flag) {
            throw new AndroidOutputException("删除好友失败，请重试");
        }

        return "success";
    }

    /**
     * 获取好友申请列表
     *
     * @param uid
     * @return 返回列表
     * @throws BusinessException
     * @throws AndroidOutputException
     * @Title getRequestList
     */

    @ResponseBody
    @RequestMapping("/getRequestList")
    public List<FriendRequest> getRequestList(@SessionAttribute(SESSION_UID) Long uid)
            throws BusinessException, AndroidOutputException {

        return androidFriendShow.getResponserFriendRequestList(uid);
    }

    /**
     * 查找用户，需要完全匹配，根据名字或手机号查找
     *
     * @param androidRequest
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title searchFriend
     */

    @ResponseBody
    @RequestMapping("/searchUser")
    public List<UserInfoSimple> searchFriend(@RequestBody AndroidRequest androidRequest,
                                             @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String key = (String) params.get("key");

        return androidFriendShow.searchFriendByUsernameOrRealName(key);
    }

    /**
     * 获取用户基本信息，非好友也可以获取
     *
     * @param androidRequest {username:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @Title getUserInfoDetail
     */

    @ResponseBody
    @RequestMapping("/getUserInfoDetail")
    public UserInfoDetail getUserInfoDetail(@RequestBody AndroidRequest androidRequest,
                                            @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String username = (String) params.get("username");

        return userShow.getUserInfoDetail(accountBusiness
                .getUserInfoByUsername(username).getUid(), operator);

    }

    /**
     * TODO(这里用一句话描述这个方法的作用)
     *
     * @param androidRequest {username:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @Title getFriendDetail
     */
    @ResponseBody
    @RequestMapping("/getFriendDetail")
    public FriendDetail getFriendDetail(@RequestBody AndroidRequest androidRequest,
                                        @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String username = (String) params.get("username");

        return userShow.getFriendDetail(accountBusiness
                .getUserInfoByUsername(username).getUid(), operator);

    }

    /**
     * TODO(这里用一句话描述这个方法的作用)
     *
     * @param androidRequest {friendUsername:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title addSpecialFriend
     */

    @ResponseBody
    @RequestMapping("/addSpecialFriend")
    public String addSpecialFriend(@RequestBody AndroidRequest androidRequest,
                                   @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException, BusinessException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String friendUsername = (String) params.get("friendUsername");

        commonFriendBusiness.addSpecialFriend(operator, accountBusiness
                .getUserInfoByUsername(friendUsername).getUid());

        return "success";
    }

    /**
     * TODO(这里用一句话描述这个方法的作用)
     *
     * @param androidRequest {friendUsername:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @Title deleteSpecialFriend
     */

    @ResponseBody
    @RequestMapping("/deleteSpecialFriend")
    public String deleteSpecialFriend(@RequestBody AndroidRequest androidRequest,
                                      @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String friendUsername = (String) params.get("friendUsername");

        commonFriendBusiness.deleteSpecialFriend(operator, accountBusiness
                .getUserInfoByUsername(friendUsername).getUid());

        return "success";
    }

}
