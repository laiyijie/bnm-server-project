package cn.bangnongmang.server.io.wechat.data;

import java.util.List;

import cn.bangnongmang.data.domain.OrderFarmerWorkSize;

public class OrderFarmerWorkSizeInfo {

	private OrderFarmerWorkSize orderFarmerWorkSize;
	private List<OrderDriverWorkSizeInfo> orderDriverWorkSizeInfos;

	public OrderFarmerWorkSize getOrderFarmerWorkSize() {
		return orderFarmerWorkSize;
	}

	public void setOrderFarmerWorkSize(OrderFarmerWorkSize orderFarmerWorkSize) {
		this.orderFarmerWorkSize = orderFarmerWorkSize;
	}

	public List<OrderDriverWorkSizeInfo> getOrderDriverWorkSizeInfos() {
		return orderDriverWorkSizeInfos;
	}

	public void setOrderDriverWorkSizeInfos(List<OrderDriverWorkSizeInfo> orderDriverWorkSizeInfos) {
		this.orderDriverWorkSizeInfos = orderDriverWorkSizeInfos;
	}

	public static class OrderDriverWorkSizeInfo {

		private String name;
		private Double realSize;
		private String portraitUrl;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getRealSize() {
			return realSize;
		}

		public void setRealSize(Double realSize) {
			this.realSize = realSize;
		}

		public String getPortraitUrl() {
			return portraitUrl;
		}

		public void setPortraitUrl(String portraitUrl) {
			this.portraitUrl = portraitUrl;
		}

	}

}
