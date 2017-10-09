package cn.bangnongmang.data.combo.domain;

import cn.bangnongmang.data.domain.OrderDriverWorkSize;

public class OrderDriverWorkSizeInfoShow {
	private Long id;
	private OrderDriverWorkSize orderDriverWorkSize;
	private UserBasicInfoShow userBasicInfoShow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderDriverWorkSize getOrderDriverWorkSize() {
		return orderDriverWorkSize;
	}

	public void setOrderDriverWorkSize(OrderDriverWorkSize orderDriverWorkSize) {
		this.orderDriverWorkSize = orderDriverWorkSize;
	}

	public UserBasicInfoShow getUserBasicInfoShow() {
		return userBasicInfoShow;
	}

	public void setUserBasicInfoShow(UserBasicInfoShow userBasicInfoShow) {
		this.userBasicInfoShow = userBasicInfoShow;
	}

}
