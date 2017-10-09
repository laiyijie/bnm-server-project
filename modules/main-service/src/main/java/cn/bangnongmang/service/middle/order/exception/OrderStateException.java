package cn.bangnongmang.service.middle.order.exception;

public class OrderStateException extends Exception {

	private String message;
	private Integer code;

	@Override
	public String getMessage() {
		return this.message;
	}

	public OrderStateException(String message) {
		this.message = message;
		this.code = 800;
	}

	public OrderStateException(Integer code, String message) {
		this.setCode(code);
		this.message = message;
	}

	public OrderStateException(Integer code) {
		this.setCode(code);
		this.message = "状态默认错误";
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
