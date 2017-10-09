package cn.bangnongmang.admin.io.wechat.model;

import java.io.Serializable;
import java.util.List;

import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountRealNameAuth;

public class UserInfo implements Serializable {
	private String username;
	private String nickname;
	private String role;
	private AccountGeo lastAddress;
	private Long createTime;
	private AccountRealNameAuth accountRealNameAuth;

	private Integer currMoney;

	private List<UserMachineInfo> userMachineInfos;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public AccountGeo getLastAddress() {
		return lastAddress;
	}

	public void setLastAddress(AccountGeo lastAddress) {
		this.lastAddress = lastAddress;
	}

	public AccountRealNameAuth getAccountRealNameAuth() {
		return accountRealNameAuth;
	}

	public void setAccountRealNameAuth(AccountRealNameAuth accountRealNameAuth) {
		this.accountRealNameAuth = accountRealNameAuth;
	}

	public Integer getCurrMoney() {
		return currMoney;
	}

	public void setCurrMoney(Integer currMoney) {
		this.currMoney = currMoney;
	}

	public List<UserMachineInfo> getUserMachineInfos() {
		return userMachineInfos;
	}

	public void setUserMachineInfos(List<UserMachineInfo> userMachineInfos) {
		this.userMachineInfos = userMachineInfos;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", nickname=" + nickname + ", role=" + role + ", lastAddress="
				+ lastAddress + ", createTime=" + createTime + ", accountRealNameAuth=" + accountRealNameAuth
				+ ", currMoney=" + currMoney + ", userMachineInfos=" + userMachineInfos + "]";
	}

}
