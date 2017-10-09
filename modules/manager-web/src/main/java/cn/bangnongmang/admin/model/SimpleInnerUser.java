package cn.bangnongmang.admin.model;

import java.util.List;

import cn.bangnongmang.admin.data.domain.AreaDict;

public class SimpleInnerUser {

	private String username;
	private String superior;
	private String name;
	private String phone;
	private Integer level;
	private SimpleInnerUser superiorDetail;
	private List<AreaDict> areaList;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public SimpleInnerUser getSuperiorDetail() {
		return superiorDetail;
	}

	public void setSuperiorDetail(SimpleInnerUser superiorDetail) {
		this.superiorDetail = superiorDetail;
	}

	public List<AreaDict> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaDict> areaList) {
		this.areaList = areaList;
	}

}
