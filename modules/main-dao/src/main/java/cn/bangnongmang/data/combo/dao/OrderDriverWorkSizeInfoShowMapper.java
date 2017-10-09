package cn.bangnongmang.data.combo.dao;

import java.util.List;

import cn.bangnongmang.data.combo.domain.OrderDriverWorkSizeInfoShow;

public interface OrderDriverWorkSizeInfoShowMapper {
	List<OrderDriverWorkSizeInfoShow> getOrderDriverWorkSizeInfoShowListByOrderFarmerId(String orderId);
}
