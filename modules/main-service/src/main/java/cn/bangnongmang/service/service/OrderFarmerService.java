package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderInvite;
import cn.bangnongmang.data.domain.SeasonOrders;

public interface OrderFarmerService {

	Integer GRAB_SEASON_STATE_DRAFT = 100;
	Integer GRAB_SEASON_STATE_PUBLISHED = 200;

	boolean createOrderFarmer(OrderFarmer orderFarmer);

	OrderFarmer getOrderFarmerById(String id);

	Boolean changeStateTo(String id, String state);

	Boolean addOrderInvite(String farmerOrderId, Long uid);

	Boolean updateOrderFarmerById(OrderFarmer orderFarmer);

	OrderInvite getOrderInvite(String orderId);

	List<SeasonOrders> getCurrentSeasonOrders();

	Boolean updateLeader(String orderId, Long leader);

	Boolean saveOrderAndOptionRelation(String orderId, Long optionId);

	Boolean deleteOrderAndOptionRealtion(String orderId, Long optionId);

	Boolean isExistOrderAndOptionReation(String orderId,Long optionId);
}
