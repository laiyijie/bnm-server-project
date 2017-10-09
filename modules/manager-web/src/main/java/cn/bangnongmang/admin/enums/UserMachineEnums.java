package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 用户车辆认证状态
 *  @author luchangquan  DateTime 2017年4月7日 下午12:11:05 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public enum UserMachineEnums {
	 STATE_AUTH_INIT(100,"初始化"),
	 STATE_WAITTING_AUTH(200,"车辆等待认证"),
	 STATE_AUTH_FAILED(300,"车辆认证失败"),
	 STATE_AUTH_PASSED(400,"车辆认证通过");
	
	
	private Integer currencyCode;
	private String  description;
	
	private UserMachineEnums(Integer currencyCode, String  description) {
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
