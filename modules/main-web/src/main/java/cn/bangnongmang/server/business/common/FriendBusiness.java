package cn.bangnongmang.server.business.common;

import java.util.List;

import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.server.io.BusinessException;

public interface FriendBusiness {

	List<Friendship> getFriendsList(Long uid) throws BusinessException;

	List<FriendshipRequest> getRequestList(Long uid) throws BusinessException;

	FriendshipRequest sendFriendRequest(Long sender, Long receiver, String ps) throws
			BusinessException;

	Friendship acceptFriendRequest(Long sender, Long receiver) throws BusinessException;

	boolean deleteFrienshipRequest(Long sender, Long receiver) throws BusinessException;

	boolean deleteFriend(Long deletor, Long userb) throws BusinessException;

	boolean addSpecialFriend(Long uid, Long friendUid) throws BusinessException;

	boolean deleteSpecialFriend(Long uid, Long friendUid);

	boolean isSpecialFriend(Long uid, Long friendUid);

}
