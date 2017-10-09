package cn.bangnongmang.server.notify;

import cn.bangnongmang.server.notify.bo.HookInviteFriendParams;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.server.notify.bo.HookFriendParams;

@Aspect
@Component
@Order(3)
public class FriendNotify {

    private NotifyUtil notifyUtil;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    private static String FRIEND_FRIEND_REQUEST_SEND = "FRIEND_FRIEND_REQUEST_SEND";
    private static String FRIEND_FRIEND_REQUEST_ACCEPT = "FRIEND_FRIEND_REQUEST_ACCEPT";
    private static String FRIEND_TEXTMESSAGE_INVITE ="FRIEND_TEXTMESSAGE_INVITE";

    @Autowired
    public FriendNotify(NotifyUtil notifyUtil) {

        this.notifyUtil = notifyUtil;
        HookFriendParams params = new HookFriendParams();
        params.setFriendName("雷妈");
        params.setFriendUsername("18801902298");
        params.setMyName("我");
        params.setMyUsername("13817350813");
        params.setPs("我是赖毅杰，请求加你为好友");

        HookInviteFriendParams hookInviteFriendParams=new HookInviteFriendParams();
        hookInviteFriendParams.setRealName("雷妈");
        hookInviteFriendParams.setPhone("18888888888");
        hookInviteFriendParams.setDistrict("江西");

        notifyUtil.registerHook(FRIEND_FRIEND_REQUEST_SEND, "好友 - 收到别人的好友请求 - 给被申请的人", params);
        notifyUtil.registerHook(FRIEND_FRIEND_REQUEST_ACCEPT, "好友 - 你的好友請求已通過 - 给申请的人", params);
        notifyUtil.registerHook(FRIEND_TEXTMESSAGE_INVITE,"好友 - 通过手机号邀请好友",hookInviteFriendParams);

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.FriendBusiness.sendFriendRequest(..)) && args(sender,receiver,ps)")
    public void sendFriendRequest(Long sender, Long receiver, String ps) {

        notifyUtil.sendNotifyBusinessMessage(receiver, FRIEND_FRIEND_REQUEST_SEND, createParams(receiver, sender, ps));

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.FriendBusiness.acceptFriendRequest(..)) && args(sender,receiver)")
    public void acceptFriendRequest(Long sender, Long receiver) {
        notifyUtil.sendNotifyBusinessMessage(sender, FRIEND_FRIEND_REQUEST_ACCEPT, createParams(sender, receiver, ""));
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.InviteFriendBusiness.inviteFriend(..)) && args(uid,friendPhone)")
    public void sendInviteFriendRequest(Long uid,String friendPhone){
        notifyUtil.sendNotifyBusinessMessage(uid,FRIEND_TEXTMESSAGE_INVITE,createParams(uid),friendPhone);

    }

    private HookFriendParams createParams(Long uid, Long friendUid, String ps) {

        UserBasicInfoShow myUbis = userBasicInfoShowMapper.selectByUid(uid);
        UserBasicInfoShow friendUbis = userBasicInfoShowMapper.selectByUid(friendUid);

        HookFriendParams params = new HookFriendParams();

        params.setFriendName(friendUbis.getAccountRealNameAuth() != null
                ? friendUbis.getAccountRealNameAuth()
                            .getReal_name() : "未认证用户");
        params.setMyName(
                friendUbis.getAccountRealNameAuth() != null ? myUbis.getAccountRealNameAuth()
                                                                    .getReal_name() : "未认证用户");
        params.setMyUsername(myUbis.getAccount()
                                   .getUsername());
        params.setFriendUsername(friendUbis.getAccount()
                                           .getUsername());
        params.setPs(ps);
        return params;

    }

    private HookInviteFriendParams createParams(Long uid) {

        UserBasicInfoShow myUbis = userBasicInfoShowMapper.selectByUid(uid);
        HookInviteFriendParams hookInviteFriendParams=new HookInviteFriendParams();
        hookInviteFriendParams.setRealName(myUbis.getAccountRealNameAuth().getReal_name());
        hookInviteFriendParams.setDistrict(myUbis.getAccountGeo().getProvince());
        hookInviteFriendParams.setPhone("");
        return hookInviteFriendParams;

    }



}
