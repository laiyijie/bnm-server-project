package cn.bangnongmang.data.combo.domain;

import java.util.List;

import cn.bangnongmang.data.domain.TeamInOrder;

public class TeamInfoShow {
	private Long id;
	private TeamInOrder teamInOrder;
	private UserBasicInfoShow leader;
	private OrderFarmerInfoShow orderFarmerInfoShow;
	private List<TeamJoinRequsterInfo> requesters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeamInOrder getTeamInOrder() {
		return teamInOrder;
	}

	public void setTeamInOrder(TeamInOrder teamInOrder) {
		this.teamInOrder = teamInOrder;
	}

	public UserBasicInfoShow getLeader() {
		return leader;
	}

	public void setLeader(UserBasicInfoShow leader) {
		this.leader = leader;
	}

	public List<TeamJoinRequsterInfo> getRequesters() {
		return requesters;
	}

	public void setRequesters(List<TeamJoinRequsterInfo> requesters) {
		this.requesters = requesters;
	}

	public OrderFarmerInfoShow getOrderFarmerInfoShow() {
		return orderFarmerInfoShow;
	}

	public void setOrderFarmerInfoShow(OrderFarmerInfoShow orderFarmerInfoShow) {
		this.orderFarmerInfoShow = orderFarmerInfoShow;
	}

}
