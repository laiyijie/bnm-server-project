package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 农主订单type
 *  @author luchangquan  DateTime 2017年4月7日 上午10:43:41 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public enum OrderFarmerEnum {
	
	 FARMER_WAITTING_AUTH("FARMER_WAITTING_AUTH","等待认证"),
	 FARMER_WAITTING_PAY("FARMER_WAITTING_PAY","等待支付定金"),
	 FARMER_WAITTING_CHOOSE_FIELD("FARMER_WAITTING_CHOOSE_FIELD","等待选择田块"),
	 FARMER_WAITTING_GOT("FARMER_WAITTING_GOT","等待组队"),
	 FARMER_GOT("FARMER_GOT","已接单"),
	 FARMER_WORKING("FARMER_WORKING","正在作业"),
	 FARMER_WAITTING_SIZE_ENSURE("FARMER_WAITTING_SIZE_ENSURE","等待面积确认"),
	 FARMER_WAITTING_DISTRIBUTE("FARMER_WAITTING_DISTRIBUTE","等待分配作业亩数"),
	 FARMER_TODAY_WORK_FINISHED("FARMER_TODAY_WORK_FINISHED","当日作业完成"),
	 FARMER_ALL_FINISHED("FARMER_ALL_FINISHED","作业完成"),
	 FARMER_CANCELED("FARMER_CANCELED","订单取消"),
	 FARMER_AUTH_FAILED("FARMER_AUTH_FAILED","认证失败");
	 
	
	private String currencyCode;
	private String  description;
	
	private OrderFarmerEnum(String currencyCode, String  description) {
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
