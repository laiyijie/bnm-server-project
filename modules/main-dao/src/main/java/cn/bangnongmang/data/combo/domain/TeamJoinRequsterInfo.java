package cn.bangnongmang.data.combo.domain;

import cn.bangnongmang.data.domain.TeamJoinRequest;

public class TeamJoinRequsterInfo {

	private Long id;
	private UserBasicInfoShow userBasicInfoShow;
	private TeamJoinRequest teamJoinRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserBasicInfoShow getUserBasicInfoShow() {
		return userBasicInfoShow;
	}

	public void setUserBasicInfoShow(UserBasicInfoShow userBasicInfoShow) {
		this.userBasicInfoShow = userBasicInfoShow;
	}

	public TeamJoinRequest getTeamJoinRequest() {
		return teamJoinRequest;
	}

	public void setTeamJoinRequest(TeamJoinRequest teamJoinRequest) {
		this.teamJoinRequest = teamJoinRequest;
	}

}
