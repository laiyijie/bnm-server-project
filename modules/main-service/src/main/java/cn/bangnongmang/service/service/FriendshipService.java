package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.Account;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.data.domain.FriendshipSpecial;

@Service
public interface FriendshipService {

	Integer STATE_INIT = 1000;
	Integer STATE_ACCEPTED = 2000;

	List<Friendship> getFriendsList(Long uid);

	FriendshipRequest getFriendshipRequestBySenderAndReceiver(Long sender, Long receiver);

	boolean deleteFriendshipRequest(Long requester, Long receiver);

	boolean addFriendRequest(Long sender, Long receiver, String ps);

	boolean changeFriendshipRequestState(FriendshipRequest friendshipRequest, Integer stateAccepted);

	boolean addFriendShip(Long usera, Long userb);

	boolean deleteFriendship(Long usera, Long userb);

	List<FriendshipRequest> getFrienshipRequestList(Long uid);

	Friendship getFriendship(Long sender, Long receiver);

	boolean addFriendShipSpecial(Long uid, Long friendUid);

	FriendshipSpecial getFriendShipSpecial(Long uid, Long friendUid);

	boolean deleteFriendShipSpecial(Long uid, Long friendUid);

	List<UserBasicInfoShow> getFriendInListPhone(Long uid, List<String> phoneList);
}
