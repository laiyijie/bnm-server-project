package cn.bangnongmang.server.io.wechat.data;

public class UserMachineInfo {
	
	private String type;
	private String brand;
	private String number;

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

	@Override
	public String toString() {
		return "UserMachineInfo [type=" + type + ", brand=" + brand + ", number=" + number + "]";
	}

}
