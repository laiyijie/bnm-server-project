package cn.bangnongmang.data.combo.domain;

import java.io.Serializable;
import java.util.List;

import cn.bangnongmang.data.domain.*;

public class UserMachineShow implements Serializable {
	private Long id;
	private UserMachine userMachine;
	private UserMachineType userMachineType;
	private UserMachineModel userMachineModel;
	private List<UserMachineAuthImage> userMachineAuthImages;
	private List<OptionClusterDetailInfo> options;
	private List<OptionClusterDetailInfo> userOptions;
	private List<OptionCluster> typeOptionClusters;
	private List<OptionWorkingType> optionWorkingTypes;

	public List<OptionWorkingType> getOptionWorkingTypes() {
		return optionWorkingTypes;
	}

	public void setOptionWorkingTypes(List<OptionWorkingType> optionWorkingTypes) {
		this.optionWorkingTypes = optionWorkingTypes;
	}

	public UserMachine getUserMachine() {
		return userMachine;
	}

	public void setUserMachine(UserMachine userMachine) {
		this.userMachine = userMachine;
	}

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

	@Override
	public String toString() {
		return "UserMachineShow [id=" + id + ", userMachine=" + userMachine + ", userMachineType=" + userMachineType
				+ ", userMachineModel=" + userMachineModel + ", userMachineAuthImages=" + userMachineAuthImages + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UserMachineAuthImage> getUserMachineAuthImages() {
		return userMachineAuthImages;
	}

	public void setUserMachineAuthImages(List<UserMachineAuthImage> userMachineAuthImages) {
		this.userMachineAuthImages = userMachineAuthImages;
	}

	public List<OptionClusterDetailInfo> getOptions() {
		return options;
	}

	public void setOptions(List<OptionClusterDetailInfo> options) {
		this.options = options;
	}

	public List<OptionClusterDetailInfo> getUserOptions() {
		return userOptions;
	}

	public void setUserOptions(List<OptionClusterDetailInfo> userOptions) {
		this.userOptions = userOptions;
	}

	public List<OptionCluster> getTypeOptionClusters() {
		return typeOptionClusters;
	}

	public void setTypeOptionClusters(List<OptionCluster> typeOptionClusters) {
		this.typeOptionClusters = typeOptionClusters;
	}
}
