package cn.bangnongmang.admin.enums;

public enum UserModelStateEnum {
	
	 USER_MODEL_STATE_INIT(100,"初始化"),
	 USER_MODEL_STATE_WAITING_AUTH(200,"等待认证"),
	 USER_MODEL_STATE_FAILED(300,"车辆认证失败"),
	 USER_MODEL_STATE_AUTHED(400,"认证成功");
	
	private Integer currencyCode;
	private String  description;
	
	private UserModelStateEnum(Integer currencyCode, String  description) {
		this.currencyCode = currencyCode;
		this.description=description;
				
	}
	
	public Integer getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(Integer currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
