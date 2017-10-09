package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 入队请求状态
 *  @author luchangquan  DateTime 2017年4月7日 上午10:20:33 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public enum TeamOrderEnum {
	  REQUEST_WAITTING(200,"等待请求"),
	  REQUEST_ACCEPTED( 300,"接受请求"),
	  REQUEST_REJECTED( 400,"拒绝请求"),
	  REQUEST_CANCELED(500,"取消请求"),
	  REQUEST_DELETED( 600,"删除请求"),
	  REQUEST_REMOVED(700,"移除请求");
	
	private Integer currencyCode;
	private String  description;
	
	private TeamOrderEnum(Integer currencyCode, String  description) {
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
