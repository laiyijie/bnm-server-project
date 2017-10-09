package cn.bangnongmang.admin.enums;

/**
 * 
 *  Description:机手抢单
 *  @author luchangquan  DateTime 2017年4月7日 上午10:28:19 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public enum OrderDriverEnum {

	ORDER_DRIVER_STATE_GOT("DRIVER_STATE_GOT","抢单成功"),
	ORDER_DRIVER_STATE_WORKING("ORDER_DRIVER_STATE_WORKING","订单WORKING"),
	ORDER_DRIVER_STATE_STOPPED("ORDER_DRIVER_STATE_STOPPED","抢单结束"),
	ORDER_DRIVER_STATE_ALL_FINISHED("DRIVER_ALL_FINISHED","所有订单已生成");
	
	private String currencyCode;
	private String  description;
	
	private OrderDriverEnum(String currencyCode, String  description) {
		this.currencyCode = currencyCode;
		this.description=description;
				
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
