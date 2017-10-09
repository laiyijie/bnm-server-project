package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 实名认证状态
 *  @author luchangquan  DateTime 2017年4月7日 上午10:21:14 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public enum RealNameEnum {
	REAL_NAME_AUTH_STATE_INIT(200,"实名认证初始化"),
	REAL_NAME_AUTH_STATE_WAITTING_AUTH (300,"等待实名认证"),
	REAL_NAME_AUTH_STATE_PASS (400,"实名认证通过");
	
	private Integer currencyCode;
	private String  description;
	
	private RealNameEnum(Integer currencyCode, String  description) {
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
