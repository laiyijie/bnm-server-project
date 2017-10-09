package cn.bangnongmang.service.service.exception;

public class ServiceLayerExeption extends RuntimeException {

	private Integer errorCode;
	private String errorMessage;

	public ServiceLayerExeption(String errorMessage) {
		this.errorCode = 400;
		this.errorMessage = errorMessage;
	}

	public ServiceLayerExeption(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
