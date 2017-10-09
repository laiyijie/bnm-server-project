package cn.bangnongmang.admin.enums;

public enum NotifyRealNameEnum {
	  NOTIFY_REAL_NAME_AUTH_SUCCESS("RealNameAuthSuccess","RealName成功"),
	  NOTIFY_REAL_NAME_AUTH_FAILED("RealNameAuthFailed","RealName失败"),
	  NOTIFY_MACHINE_AUTH_SUCCESS("MachineAuthSuccess","Machine成功"),
	  NOTIFY_MACHINE_AUTH_FAILED("MachineAuthFailed","Machine失败");
	  
	private String currencyCode;
	private String  description;
		
		private NotifyRealNameEnum(String currencyCode, String  description) {
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
