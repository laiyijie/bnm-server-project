package cn.bangnongmang.admin.enums;
/**
 * 
 *  Description: 
 *  @author luchangquan  DateTime 2017年4月7日 上午10:29:36 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public enum WorkSizeEnum {
	  WORK_SIZE_STATE_WAITTING_ENSURE (200,""),
	  WORK_SIZE_STATE_ENSURED(300,""),
	  WORK_SIZE_STATE_DENIED(400,""),
	  WORK_SIZE_STATE_FINISH(500,"");
	  
	  private Integer currencyCode;
	  private String  description;
		
		private WorkSizeEnum(Integer currencyCode, String  description) {
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
