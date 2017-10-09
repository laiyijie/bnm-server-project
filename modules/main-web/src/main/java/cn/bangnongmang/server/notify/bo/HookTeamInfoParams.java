package cn.bangnongmang.server.notify.bo;

public class HookTeamInfoParams {
	private String orderId;
	private String teamId;
	private String leaderName;
	private String requesterName;
	private String teamMemberNum;
	private String teamRequesterNum;
	private String occuredTime;
	private String removeReson;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getTeamMemberNum() {
		return teamMemberNum;
	}

	public void setTeamMemberNum(String teamMemberNum) {
		this.teamMemberNum = teamMemberNum;
	}

	public String getOccuredTime() {
		return occuredTime;
	}

	public void setOccuredTime(String occuredTime) {
		this.occuredTime = occuredTime;
	}

	public String getTeamRequesterNum() {
		return teamRequesterNum;
	}

	public void setTeamRequesterNum(String teamRequesterNum) {
		this.teamRequesterNum = teamRequesterNum;
	}

	public String getRemoveReson() {
		return removeReson;
	}

	public void setRemoveReson(String removeReson) {
		this.removeReson = removeReson;
	}

}
