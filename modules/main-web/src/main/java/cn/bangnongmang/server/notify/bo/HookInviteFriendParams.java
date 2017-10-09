package cn.bangnongmang.server.notify.bo;

/**
 * Created by lucq on 2017/7/6.
 */
public class HookInviteFriendParams {

	private String district;

	private String realName;

	private String phone;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
