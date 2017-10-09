package cn.bangnongmang.data.combo.dao;

import java.util.List;

import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;

public interface OrderFarmerInfoShowMapper {
	List<OrderFarmerInfoShow> selectOrderFarmerInfoShowByUid(Long uid);

	List<OrderFarmerInfoShow> selectOrderFarmerInfoShowByState(String state);
	
	List<OrderFarmerInfoShow> selectOrderFarmerInfoShowByDriver(Long driverUid);

	List<OrderFarmerInfoShow> selectOrderFarmerInfoShow();
	
	OrderFarmerInfoShow selectOrderFarmerInfoShowByOrderId(String orderId);
	
	List<OrderFarmerInfoShow> selectOrderFarmerInfoShowByUserFavorite(Long uid);

}
