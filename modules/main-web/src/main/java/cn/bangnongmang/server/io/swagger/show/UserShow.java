package cn.bangnongmang.server.io.swagger.show;

import java.util.List;
import java.util.stream.Collectors;

import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.server.swagger.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.FriendInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.FriendInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.server.io.swagger.UserConverter;
import cn.bangnongmang.service.service.FriendshipService;
import cn.bangnongmang.service.service.TeamOrderService;

/**
 * Created by admin on 2017-04-12.
 */
@Service
public class UserShow {

	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserBasicInfoShowMapper userBasicInfoShowMapper;
	@Autowired
	private FriendInfoShowMapper friendInfoShowMapper;
	@Autowired
	private UserMachineShowMapper userMachineShowMapper;
	@Autowired
	private FriendshipService friendshipService;
	@Autowired
	private TeamOrderService teamOrderService;
	@Autowired
	private AccountRealNameAuthMapper accountRealNameAuthMapper;

	public UserDetail getMyUserInfo(Long uid) {
		return userConverter.convertToUserDetail(UserDetail.class, userBasicInfoShowMapper.selectByUid(uid),
				false);
	}

	public List<UserSimple> searchUserByUsernameOrRealName(String key) {
		List<UserBasicInfoShow> userBasicInfoShows = userBasicInfoShowMapper.searchRealNameAuthListByNameOrPhone(key);
		userBasicInfoShows.removeIf(a -> a.getAccount().getLevel() < 20);
		return userBasicInfoShows.stream().map(ubis -> userConverter.convertToUserSimple(ubis, UserSimple.class))
				.collect(Collectors.toList());
	}

	public UserDetail getUserDetail(Long uid, Long operator) {
		UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(uid);
		Friendship friendship = friendshipService.getFriendship(operator, uid);
		return userConverter.convertToUserDetail(UserDetail.class, userBasicInfoShowMapper.selectByUid(uid),
				friendship != null);
	}

	public List<FriendSimple> getMyFriendList(Long uid) {
		return friendInfoShowMapper.selectFriendListByUsera(uid).stream()
				.filter(friendInfoShow -> friendInfoShow.getUserBasicInfoShow() != null)
				.map(friendInfoShow -> userConverter.convertToFriendSimple(friendInfoShow))
				.collect(Collectors.toList());
	}

	public List<FriendRequest> getMyFriendRequestList(Long uid) {
		return friendInfoShowMapper.selectFriendRequestListByResponser(uid).stream()
				.filter(friendRequestInfoShow -> friendRequestInfoShow != null)
				.map(friendRequestInfoShow -> userConverter.convertToFriendRequest(friendRequestInfoShow))
				.collect(Collectors.toList());
	}

	public FriendDetail getMyFriendDetail(Long uid, Long friendUid) {
		FriendInfoShow friendInfoShow = friendInfoShowMapper.selectFriendInfoShowByUseraAndUserb(uid,
				friendUid);
		if (friendInfoShow == null || friendInfoShow.getUserBasicInfoShow() == null) {
			return null;
		}
		List<TeamJoinRequest> teamJoinRequests = teamOrderService.getTeamJoinRequestsByUsername(friendUid);
		List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrdersByLeaderUsername(friendUid);
		teamJoinRequests.removeIf(tjr -> !tjr.getState().equals(TeamOrderService.REQUEST_ACCEPTED));
		FriendDetail.StateEnum state = FriendDetail.StateEnum.IDLE;
		if (teamInOrders.isEmpty() && teamJoinRequests.isEmpty()) {
			state = FriendDetail.StateEnum.IDLE;
		} else {
			// TODO 组队成功以后的状态需要增加，在组队成功之后
			state = FriendDetail.StateEnum.IN_TEAM;
		}

		return userConverter.convertToFriendDetail(friendInfoShow, state);

	}

	public AccountAuth getAccountAuth(Long uid){
		AccountRealNameAuth accountRealNameAuth=accountRealNameAuthMapper.selectByPrimaryKey(uid);
		return userConverter.convertToAccountAuth(accountRealNameAuth);
	}

}
