package cn.bangnongmang.admin.enums;

public enum AccountLevel {
	
	ACCOUNT_AUTHENTICATED(40,"ACCOUNT_AUTHENTICATED"),
	ACCOUNT_WATTING_AUTH (30,"ACCOUNT_WATTING_AUTH");
	
	private Integer currencyCode;
	private String  description;
	
	private AccountLevel(Integer currencyCode, String  description) {
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
