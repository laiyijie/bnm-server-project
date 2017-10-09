package cn.bangnongmang.admin.io.wechat.model;

public class UserMachineInfoSimple {

	private Long id;
	private Integer state;
	private String belongto;
	private String brandAndNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getBrandAndNumber() {
		return brandAndNumber;
	}

	public void setBrandAndNumber(String brandAndNumber) {
		this.brandAndNumber = brandAndNumber;
	}

	@Override
	public String toString() {
		return "UserMachineInfoSimple [id=" + id + ", state=" + state + ", belongto=" + belongto + ", brandAndNumber="
				+ brandAndNumber + "]";
	}

}
