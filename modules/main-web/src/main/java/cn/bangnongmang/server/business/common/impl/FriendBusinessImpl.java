package cn.bangnongmang.server.business.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.data.domain.FriendshipSpecial;
import cn.bangnongmang.server.business.common.FriendBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.FriendshipService;
import cn.bangnongmang.service.service.UserService;

@Service
@Transactional(rollbackFor = {Exception.class})
public class FriendBusinessImpl implements FriendBusiness {

    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private UserService userService;

    @Override
    public List<Friendship> getFriendsList(Long uid) throws BusinessException {

        if (uid == null)
            throw new BusinessException("请先登录");

        return friendshipService.getFriendsList(uid);
    }

    @Override
    public List<FriendshipRequest> getRequestList(Long uid) throws BusinessException {

        if (uid == null)
            throw new BusinessException("请先登录");

        return friendshipService.getFrienshipRequestList(uid);
    }

    @Override
    public FriendshipRequest sendFriendRequest(Long sender, Long receiver, String ps) throws
            BusinessException {

        Friendship friendship = friendshipService.getFriendship(sender, receiver);

        AccountRealNameAuth senderAuth = userService.getRealNameAuth(sender);
        AccountRealNameAuth receiverAuth = userService.getRealNameAuth(receiver);

        if (senderAuth == null || !senderAuth.getState()
                .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("您尚未认证，无法添加好友");
        }

        if (sender.equals(receiver)) {
            throw new BusinessException("不能添加自己为好友");
        }

        if (receiverAuth == null || !receiverAuth.getState()
                .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("该用户尚未认证，无法添加好友");
        }

        if (friendship != null) {

            throw new BusinessException("该用户已经是您的好友");
        }

        if (friendshipService.getFriendshipRequestBySenderAndReceiver(sender, receiver) != null) {
            friendshipService.deleteFriendshipRequest(sender, receiver);
        }

        friendshipService.addFriendRequest(sender, receiver, ps);

        return friendshipService.getFriendshipRequestBySenderAndReceiver(sender, receiver);
    }

    @Override
    public Friendship acceptFriendRequest(Long sender, Long receiver) throws BusinessException {

        FriendshipRequest friendshipRequest = friendshipService
                .getFriendshipRequestBySenderAndReceiver(sender,
                        receiver);

        if (friendshipRequest == null) {
            throw new BusinessException("未发送请求");
        }
        AccountRealNameAuth senderAuth = userService.getRealNameAuth(sender);
        AccountRealNameAuth receiverAuth = userService.getRealNameAuth(receiver);

        if (senderAuth == null || !senderAuth.getState()
                .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("您尚未认证，无法添加好友");
        }

        if (receiverAuth == null || !receiverAuth.getState()
                .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("该用户尚未认证，无法添加好友");
        }

        if (sender.equals(receiver)) {
            throw new BusinessException("不能添加自己为好友");
        }

        boolean result = friendshipService.changeFriendshipRequestState(friendshipRequest,
                FriendshipService.STATE_ACCEPTED);

        if (result == false) {
            return null;
        }
        FriendshipRequest relateRequest = friendshipService
                .getFriendshipRequestBySenderAndReceiver(receiver, sender);

        if (relateRequest != null) {
            friendshipService
                    .changeFriendshipRequestState(relateRequest, FriendshipService.STATE_ACCEPTED);
        }
        Friendship friendship = friendshipService.getFriendship(sender, receiver);

        if (friendship != null) {
            return friendship;
        }

        boolean flag = (friendshipService.addFriendShip(sender, receiver)
                && friendshipService.addFriendShip(receiver, sender));

        if (flag) {
            return friendshipService.getFriendship(sender, receiver);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteFrienshipRequest(Long sender, Long receiver) throws BusinessException {

        FriendshipRequest friendshipRequest = friendshipService
                .getFriendshipRequestBySenderAndReceiver(sender,
                        receiver);
        if (friendshipRequest == null)
            throw new BusinessException("没有此好友请求");
        return friendshipService.deleteFriendshipRequest(sender, receiver);

    }

    @Override
    public boolean deleteFriend(Long deletor, Long userb) throws BusinessException {

        Friendship friendship = friendshipService.getFriendship(deletor, userb);

        if (friendship == null) {
            throw new BusinessException("你与该用户不是好友关系，无法删除");
        }

        friendshipService.deleteFriendshipRequest(deletor, userb);
        friendshipService.deleteFriendshipRequest(userb, deletor);

        return friendshipService.deleteFriendship(deletor, userb);
    }

    @Override
    public boolean addSpecialFriend(Long uid, Long friendUid) throws BusinessException {

        if (uid.equals(friendUid))
            throw new BusinessException("不能添加自己为特殊好友");
        FriendshipSpecial friendshipSpecial = friendshipService
                .getFriendShipSpecial(uid, friendUid);

        if (friendshipSpecial == null) {
            friendshipService.addFriendShipSpecial(uid, friendUid);
        }

        return true;
    }

    @Override
    public boolean deleteSpecialFriend(Long uid, Long friendUid) {

        FriendshipSpecial friendshipSpecial = friendshipService
                .getFriendShipSpecial(uid, friendUid);

        if (friendshipSpecial != null) {
            friendshipService.deleteFriendShipSpecial(uid, friendUid);
        }

        return true;
    }

    @Override
    public boolean isSpecialFriend(Long uid, Long friendUid) {

        FriendshipSpecial friendshipSpecial = friendshipService
                .getFriendShipSpecial(uid, friendUid);

        if (friendshipSpecial != null) {
            return true;
        }

        return false;
    }

}
