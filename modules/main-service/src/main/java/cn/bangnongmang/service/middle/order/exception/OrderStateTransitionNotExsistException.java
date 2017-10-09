package cn.bangnongmang.service.middle.order.exception;

public class OrderStateTransitionNotExsistException extends OrderStateException {

	public OrderStateTransitionNotExsistException(String message) {
		super(message);
	}

	public OrderStateTransitionNotExsistException() {
		super("订单状态错误");
	}

	/**
	 * @Fields serialVersionUID TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

}
