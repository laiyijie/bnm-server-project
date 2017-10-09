package cn.bangnongmang.data.combo.domain;

import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipSpecial;

public class FriendInfoShow {

	private Long id;
	private Friendship friendship;
	private FriendshipSpecial friendshipSpecial;
	private UserBasicInfoShow userBasicInfoShow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Friendship getFriendship() {
		return friendship;
	}

	public void setFriendship(Friendship friendship) {
		this.friendship = friendship;
	}

	public FriendshipSpecial getFriendshipSpecial() {
		return friendshipSpecial;
	}

	public void setFriendshipSpecial(FriendshipSpecial friendshipSpecial) {
		this.friendshipSpecial = friendshipSpecial;
	}

	public UserBasicInfoShow getUserBasicInfoShow() {
		return userBasicInfoShow;
	}

	public void setUserBasicInfoShow(UserBasicInfoShow userBasicInfoShow) {
		this.userBasicInfoShow = userBasicInfoShow;
	}

}
