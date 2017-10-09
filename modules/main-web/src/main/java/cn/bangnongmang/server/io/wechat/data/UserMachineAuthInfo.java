package cn.bangnongmang.server.io.wechat.data;

import java.util.List;

import cn.bangnongmang.data.domain.UserMachineAuthImage;

public class UserMachineAuthInfo {

	private Long id;
	private String type;
	private String brand;
	private String number;
	private Integer state;
	private String failedReason;
	public String getFailedReason() {
		return failedReason;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	private List<UserMachineAuthImage> imageUrls;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<UserMachineAuthImage> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<UserMachineAuthImage> imageUrls) {
		this.imageUrls = imageUrls;
	}

	@Override
	public String toString() {
		return "UserMachineAuthInfo [id=" + id + ", type=" + type + ", brand=" + brand + ", number=" + number
				+ ", state=" + state + ", failedReason=" + failedReason + ", imageUrls=" + imageUrls + "]";
	}



}
