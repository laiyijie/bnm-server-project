package cn.bangnongmang.data.combo.domain;

import cn.bangnongmang.data.domain.FriendshipRequest;

public class FriendRequestInfoShow {
	private Long id;
	private FriendshipRequest friendshipRequest;
	private UserBasicInfoShow userBasicInfoShow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FriendshipRequest getFriendshipRequest() {
		return friendshipRequest;
	}

	public void setFriendshipRequest(FriendshipRequest friendshipRequest) {
		this.friendshipRequest = friendshipRequest;
	}

	public UserBasicInfoShow getUserBasicInfoShow() {
		return userBasicInfoShow;
	}

	public void setUserBasicInfoShow(UserBasicInfoShow userBasicInfoShow) {
		this.userBasicInfoShow = userBasicInfoShow;
	}

}
