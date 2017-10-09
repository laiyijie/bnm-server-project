package cn.bangnongmang.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import cn.bangnongmang.data.combo.dao.FriendInfoShowMapper;
import cn.bangnongmang.data.combo.domain.FriendInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.FriendshipMapper;
import cn.bangnongmang.data.dao.FriendshipRequestMapper;
import cn.bangnongmang.data.dao.FriendshipSpecialMapper;
import cn.bangnongmang.service.service.FriendshipService;

@Service("S_FriendshipService")
public class FriendshipServiceImpl implements FriendshipService {

	@Autowired
	private FriendshipMapper friendshipMapper;
	@Autowired
	private FriendshipRequestMapper requestMapper;
	@Autowired
	private FriendshipSpecialMapper specialMapper;
	@Autowired
	private FriendInfoShowMapper friendInfoShowMapper;

	@Override
	public List<Friendship> getFriendsList(Long uid) {

		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();

		friendshipCriteria.or()
						  .andUseraEqualTo(uid);

		return friendshipMapper.selectByExample(friendshipCriteria);
	}

	@Override
	public FriendshipRequest getFriendshipRequestBySenderAndReceiver(Long sender, Long receiver) {

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();

		friendshipRequestCriteria.or()
								 .andRequesterEqualTo(sender)
								 .andResponserEqualTo(receiver);

		List<FriendshipRequest> friendshipRequests = requestMapper
				.selectByExample(friendshipRequestCriteria);

		if (friendshipRequests.isEmpty()) {

			return null;
		} else {

			return friendshipRequests.get(0);
		}
	}

	@Override
	public boolean deleteFriendshipRequest(Long requester, Long receiver) {
		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();

		friendshipRequestCriteria.or()
								 .andRequesterEqualTo(requester)
								 .andResponserEqualTo(receiver);

		requestMapper.deleteByExample(friendshipRequestCriteria);

		return true;
	}

	@Override
	public boolean addFriendRequest(Long sender, Long receiver, String ps) {

		FriendshipRequest friendshipRequest = new FriendshipRequest();

		friendshipRequest.setRequester(sender);
		friendshipRequest.setResponser(receiver);
		friendshipRequest.setState(FriendshipService.STATE_INIT);
		friendshipRequest.setPs(ps);
		friendshipRequest.setCreate_time(System.currentTimeMillis());

		requestMapper.insert(friendshipRequest);

		return true;
	}

	@Override
	public boolean changeFriendshipRequestState(FriendshipRequest friendshipRequest,
												Integer state) {

		Long id = friendshipRequest.getId();

		FriendshipRequest request = requestMapper.selectByPrimaryKey(id);

		if (request == null) {
			return false;
		}

		request.setState(state);

		requestMapper.updateByPrimaryKey(request);

		return true;
	}

	@Override
	public boolean addFriendShip(Long usera, Long userb) {

		Friendship friendship = new Friendship();

		friendship.setUsera(usera);
		friendship.setUserb(userb);

		friendship.setCreate_time(System.currentTimeMillis());

		int flag = friendshipMapper.insert(friendship);

		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteFriendship(Long usera, Long userb) {

		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();

		friendshipCriteria.or()
						  .andUseraEqualTo(usera)
						  .andUserbEqualTo(userb);
		friendshipCriteria.or()
						  .andUseraEqualTo(userb)
						  .andUserbEqualTo(usera);

		int flag = friendshipMapper.deleteByExample(friendshipCriteria);

		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<FriendshipRequest> getFrienshipRequestList(Long uid) {

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();

		friendshipRequestCriteria.or()
								 .andResponserEqualTo(uid);

		return requestMapper.selectByExample(friendshipRequestCriteria);
	}

	@Override
	public Friendship getFriendship(Long sender, Long receiver) {
		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();

		friendshipCriteria.or()
						  .andUseraEqualTo(sender)
						  .andUserbEqualTo(receiver);

		List<Friendship> requests = friendshipMapper.selectByExample(friendshipCriteria);

		if (requests.isEmpty()) {
			return null;
		} else {
			return requests.get(0);
		}
	}

	@Override
	public boolean addFriendShipSpecial(Long uid, Long friendUid) {

		FriendshipSpecial friendshipSpecial = new FriendshipSpecial();
		friendshipSpecial.setBelongto(uid);
		friendshipSpecial.setFriend_uid(friendUid);
		specialMapper.insert(friendshipSpecial);
		return true;
	}

	@Override
	public FriendshipSpecial getFriendShipSpecial(Long uid, Long friendUid) {

		FriendshipSpecialCriteria friendshipSpecialCriteria = new FriendshipSpecialCriteria();
		friendshipSpecialCriteria.or()
								 .andBelongtoEqualTo(uid)
								 .andFriend_uidEqualTo(friendUid);

		List<FriendshipSpecial> friendshipSpecials = specialMapper
				.selectByExample(friendshipSpecialCriteria);

		if (friendshipSpecials.isEmpty()) {
			return null;
		}

		return friendshipSpecials.get(0);
	}

	@Override
	public boolean deleteFriendShipSpecial(Long uid
			, Long friendUid) {

		FriendshipSpecialCriteria friendshipSpecialCriteria = new FriendshipSpecialCriteria();
		friendshipSpecialCriteria.or()
								 .andBelongtoEqualTo(uid)
								 .andFriend_uidEqualTo(friendUid);

		specialMapper.deleteByExample(friendshipSpecialCriteria);

		return true;
	}

	@Override
	public List<UserBasicInfoShow> getFriendInListPhone(Long uid, List<String> phoneList) {

		HashMap param= new HashMap();
		param.put("usera",uid);
		param.put("phoneList",phoneList);
		return friendInfoShowMapper.selectFriendListByUseraAndUserbList(param)
								   .stream()
				                   .map(friendRequestInfoShow -> {return  friendRequestInfoShow.getUserBasicInfoShow();})
				                   .collect(Collectors.toList());
	}


}
