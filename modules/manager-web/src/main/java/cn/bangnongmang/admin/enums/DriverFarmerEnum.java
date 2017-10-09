package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 机手，农主
 *  @author luchangquan  DateTime 2017年4月7日 下午3:28:32 
 *  @version 1.0
 */
public enum DriverFarmerEnum {
	
	FARMER(10, "农主"),
	DRIVER(20, "机手"),
	FARMER_WAITTING_AUTH(13, "农主账户等待认证"),
	AUTHENTICATED_FARMER(14, "农主账户已认证"),
	DRIVER_WAITTING_AUTH(30, "机手账户等待认证"),
	AUTHENTICATED_DRIVER(40, "机手账户已认证"),
    	AUTH_DRIVER(50, "机手已认证");
	
	private Integer currencyCode;
	private String  description;
	
	private DriverFarmerEnum(Integer currencyCode, String  description) {
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
