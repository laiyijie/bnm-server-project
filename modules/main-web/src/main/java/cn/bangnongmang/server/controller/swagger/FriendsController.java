package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.server.business.common.FriendBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.swagger.show.UserShow;
import cn.bangnongmang.server.swagger.api.FriendsApi;
import cn.bangnongmang.server.swagger.model.FriendDetail;
import cn.bangnongmang.server.swagger.model.FriendRequest;
import cn.bangnongmang.server.swagger.model.FriendSimple;
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
import java.util.List;

/**
 * Created by admin on 2017-04-12.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class FriendsController implements FriendsApi {
    @Autowired
    private UserShow userShow;
    @Autowired
    private FriendBusiness friendBusiness;

    @Override
    public ResponseEntity<List<FriendSimple>> friendsGet(HttpServletRequest request,
                                                         HttpServletResponse response)
            throws Exception {
        return new ResponseEntity<>(
                userShow.getMyFriendList(BnmSwaggerControllerUtil.getSessionForLong(request,
                        ServerSessionAttr.SESSION_UID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FriendRequest>> friendsRequestsGet(HttpServletRequest request,
                                                                  HttpServletResponse response)
            throws Exception {

        return new ResponseEntity<List<FriendRequest>>(
                userShow.getMyFriendRequestList(BnmSwaggerControllerUtil.getSessionForLong(request,
                        ServerSessionAttr.SESSION_UID)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> friendsRequestsUidDelete(
            @ApiParam(value = "请求的好友用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean flag = friendBusiness.deleteFrienshipRequest(uid, BnmSwaggerControllerUtil
                .getSessionForLong(request,
                        ServerSessionAttr.SESSION_UID));
        if (flag) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("删除好友请求失败");
    }

    @Override
    public ResponseEntity<Void> friendsRequestsPost(
            @ApiParam(value = "请求的好友用户名", required = true) @RequestParam("uid") Long uid,
            @ApiParam(value = "请求信息", required = true) @RequestParam(value = "ps", required = true) String ps,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        FriendshipRequest r = friendBusiness.sendFriendRequest(BnmSwaggerControllerUtil
                        .getSessionForLong(request, ServerSessionAttr.SESSION_UID),
                uid, ps);

        if (r != null) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("发送好友请求失败，请重试");


    }

    @Override
    public ResponseEntity<Void> friendsRequestsUidPost(
            @ApiParam(value = "请求的好友用户名", required = true) @PathVariable("uid") Long uid,
            @ApiParam(value = "是否同意", required = true) @RequestParam(value = "agree", required = true) Boolean agree,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (agree) {
            friendBusiness.acceptFriendRequest(uid, BnmSwaggerControllerUtil
                    .getSessionForLong(request,
                            ServerSessionAttr.SESSION_UID));
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> friendsSpecialsUidDelete(
            @ApiParam(value = "特殊好友的用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        friendBusiness.deleteSpecialFriend(BnmSwaggerControllerUtil
                        .getSessionForLong(request,
                                ServerSessionAttr.SESSION_UID),
                uid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> friendsSpecialsUidPost(
            @ApiParam(value = "特殊好友的用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        friendBusiness.addSpecialFriend(BnmSwaggerControllerUtil
                .getSessionForLong(request,
                        ServerSessionAttr.SESSION_UID), uid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> friendsUidDelete(
            @ApiParam(value = "好友的用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        friendBusiness.deleteFriend(BnmSwaggerControllerUtil
                .getSessionForLong(request, ServerSessionAttr.SESSION_UID), uid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FriendDetail> friendsUidGet(
            @ApiParam(value = "好友的用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        FriendDetail friendDetail = userShow.getMyFriendDetail
                (BnmSwaggerControllerUtil
                        .getSessionForLong(request, ServerSessionAttr.SESSION_UID), uid);
        if (friendDetail == null) return new ResponseEntity<FriendDetail>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<FriendDetail>(friendDetail,
                HttpStatus.OK);

    }
}
