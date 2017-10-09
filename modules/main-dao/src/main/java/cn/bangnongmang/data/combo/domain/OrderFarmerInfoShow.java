package cn.bangnongmang.data.combo.domain;

import java.util.List;

import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderGeo;

public class OrderFarmerInfoShow {

	private String id;
	private OrderFarmer orderFarmer;
	private OrderGeo orderGeo;
	private OptionWorkingType optionWorkingType;
	private List<OptionClusterDetailInfo> options;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public OrderFarmer getOrderFarmer() {
		return orderFarmer;
	}
	public void setOrderFarmer(OrderFarmer orderFarmer) {
		this.orderFarmer = orderFarmer;
	}
	public OrderGeo getOrderGeo() {
		return orderGeo;
	}
	public void setOrderGeo(OrderGeo orderGeo) {
		this.orderGeo = orderGeo;
	}
	public OptionWorkingType getOptionWorkingType() {
		return optionWorkingType;
	}
	public void setOptionWorkingType(OptionWorkingType optionWorkingType) {
		this.optionWorkingType = optionWorkingType;
	}
	public List<OptionClusterDetailInfo> getOptions() {
		return options;
	}
	public void setOptions(List<OptionClusterDetailInfo> options) {
		this.options = options;
	}
	
	
}
