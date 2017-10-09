package cn.bangnongmang.server.io.android.show;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.FriendInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.FriendInfoShow;
import cn.bangnongmang.data.combo.domain.FriendRequestInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.driverapp.models.FriendRequest;
import cn.bangnongmang.driverapp.models.FriendSimple;
import cn.bangnongmang.driverapp.models.UserInfoSimple;
import cn.bangnongmang.service.service.FriendshipService;

@Service
public class AndroidFriendShow {

	@Autowired
	private AndroidUserShow androidUserShow;
	@Autowired
	private FriendInfoShowMapper friendInfoShowMapper;
	@Autowired
	private UserBasicInfoShowMapper userBasicInfoShowMapper;

	public List<FriendSimple> getFriendListByUid(Long uid) {
		List<FriendSimple> result = new ArrayList<>();
		List<FriendInfoShow> friendInfoShows = friendInfoShowMapper.selectFriendListByUsera(uid);
		for (FriendInfoShow friendInfoShow : friendInfoShows) {
			FriendSimple friendSimple = convertFriendInfoShowToFriendSimple(friendInfoShow);
			if (friendSimple != null) {
				result.add(friendSimple);
			}
		}
		return result;
	}

	public List<FriendRequest> getResponserFriendRequestList(Long responser) {

		List<FriendRequest> requests = new ArrayList<>();
		List<FriendRequestInfoShow> friendRequestInfoShows = friendInfoShowMapper
				.selectFriendRequestListByResponser(responser);
		Collections.reverse(friendRequestInfoShows);
		for (FriendRequestInfoShow friendRequestInfoShow : friendRequestInfoShows) {
			FriendRequest friendRequest = convertFriedRequestInfoShowToFriendRequest(friendRequestInfoShow);
			if (friendRequest != null) {
				requests.add(friendRequest);
			}
		}
		return requests;
	}

	public List<UserInfoSimple> searchFriendByUsernameOrRealName(String key) {
		List<UserInfoSimple> result = new ArrayList<>();
		List<UserBasicInfoShow> userBasicInfoShows = userBasicInfoShowMapper.searchRealNameAuthListByNameOrPhone(key);
		userBasicInfoShows.removeIf(a->a.getAccount().getLevel()<20);
		for (UserBasicInfoShow userBasicInfoShow : userBasicInfoShows) {
			UserInfoSimple friendSimple = androidUserShow.convertUserBasicInfoShowToUserInfoSimple(userBasicInfoShow);
			if (friendSimple != null) {
				result.add(friendSimple);
			}
		}
		return result;
	}

	public FriendRequest convertFriedRequestInfoShowToFriendRequest(FriendRequestInfoShow fris) {
		FriendRequest fRequest = new FriendRequest();

		if (fris.getFriendshipRequest().getState().equals(FriendshipService.STATE_ACCEPTED)) {
			fRequest.setAccepted(true);
		} else {
			fRequest.setAccepted(false);
		}

		fRequest.setName(fris.getUserBasicInfoShow().getAccountRealNameAuth() != null
				? fris.getUserBasicInfoShow().getAccountRealNameAuth().getReal_name()
				: AndroidUserShow.UNAUTHED_USER_NAME);
		fRequest.setPhone(fris.getUserBasicInfoShow().getAccount().getUsername());
		fRequest.setPortraitUrl(fris.getUserBasicInfoShow().getAccountPortrait() != null
				? fris.getUserBasicInfoShow().getAccountPortrait().getPortrait_url() : "");
		fRequest.setPs(fris.getFriendshipRequest().getPs());
		return fRequest;
	}

	public FriendSimple convertFriendInfoShowToFriendSimple(FriendInfoShow fis) {

		if (fis.getUserBasicInfoShow() == null) {
			return null;
		}
		FriendSimple friendSimple = convertUserbasicInfoShowToFriendSimple(fis.getUserBasicInfoShow());
		if (fis.getFriendshipSpecial() != null) {
			friendSimple.setIsSpecial(true);
		}
		friendSimple.setIsFriend(true);

		return friendSimple;
	}

	public FriendSimple convertUserbasicInfoShowToFriendSimple(UserBasicInfoShow ubis) {

		FriendSimple friendSimple = new FriendSimple();
		friendSimple.setIsFriend(false);
		friendSimple.setIsSpecial(false);
		friendSimple.setLastLocation(androidUserShow.convertAccountGeoToUserGeoInfo(ubis.getAccountGeo()));
		friendSimple.setName(ubis.getAccountRealNameAuth() != null ? ubis.getAccountRealNameAuth().getReal_name()
				: AndroidUserShow.UNAUTHED_USER_NAME);
		friendSimple.setPhone(ubis.getAccount().getUsername());
		friendSimple
				.setPortraitUrl(ubis.getAccountPortrait() != null ? ubis.getAccountPortrait().getPortrait_url() : "");
		return friendSimple;
	}

}
