package cn.bangnongmang.data.combo.domain;

/**
 * Created by lucq on 2017/6/14.
 */
public class UserMachineBasic {

	private Long uid;
	private String realName;
	private String tel;
	private Long machineId;
	private Long modelId;
	private String modelBrand;
	private String modelNumber;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(String modelBrand) {
		this.modelBrand = modelBrand;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
}
