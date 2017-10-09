package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 200：队长，0：不是队长
 *  @author luchangquan  DateTime 2017年4月7日 上午10:24:58 

 *  @version 1.0
 */
public enum DriverLeaderEnum {
	
	DRIVER_LEADER_AUTHED(200,"队长"),
	DRIVER_LEADER_UN_AUTHED(0,"不是队长");
	
	private Integer currencyCode;
	private String  description;
	
	private DriverLeaderEnum(Integer currencyCode, String  description) {
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
