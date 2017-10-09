package cn.bangnongmang.server.io.wechat;

public class WxOutputException extends Exception {
	
	/** 
	 * @Fields serialVersionUID TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 3889180098685085588L;
	private int errorCode;
	private String errorMessage;
	
	public static final int DEFAULT_CODE = 800;
	
	public WxOutputException(String message){
		
		this.setErrorMessage(message);
		setErrorCode(DEFAULT_CODE);
	}
	
	public WxOutputException(int code ,String message){
		this.setErrorCode(code);
		this.setErrorMessage(message);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
