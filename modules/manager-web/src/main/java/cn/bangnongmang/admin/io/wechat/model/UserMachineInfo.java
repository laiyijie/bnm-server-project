package cn.bangnongmang.admin.io.wechat.model;

import java.io.Serializable;
import java.util.List;

import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineAuthImage;
import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineType;

public class UserMachineInfo implements Serializable {
	private UserMachineType userMachineType;
	private UserMachineModel userMachineModel;
	private UserMachine userMachine;
	private List<UserMachineAuthImage> photos;

	public UserMachineType getUserMachineType() {
		return userMachineType;
	}

	public void setUserMachineType(UserMachineType userMachineType) {
		this.userMachineType = userMachineType;
	}

	public UserMachineModel getUserMachineModel() {
		return userMachineModel;
	}

	public void setUserMachineModel(UserMachineModel userMachineModel) {
		this.userMachineModel = userMachineModel;
	}

	public UserMachine getUserMachine() {
		return userMachine;
	}

	public void setUserMachine(UserMachine userMachine) {
		this.userMachine = userMachine;
	}

	public List<UserMachineAuthImage> getPhotos() {
		return photos;
	}

	public void setPhotos(List<UserMachineAuthImage> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "UserMachineInfo [userMachineType=" + userMachineType + ", userMachineModel=" + userMachineModel
				+ ", userMachine=" + userMachine + ", photos=" + photos + "]";
	}

}
