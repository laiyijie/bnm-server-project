package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.OrderDriver;

public interface OrderDriverService {

	String ORDER_DRIVER_STATE_GOT = "DRIVER_STATE_GOT";
	String ORDER_DRIVER_STATE_WORKING = "ORDER_DRIVER_STATE_WORKING";
	String ORDER_DRIVER_STATE_STOPPED = "ORDER_DRIVER_STATE_STOPPED";
	String ORDER_DRIVER_STATE_ALL_FINISHED = "DRIVER_ALL_FINISHED";

	boolean createOrderDriver(OrderDriver orderDriver);

	List<OrderDriver> getOrderDriverByUsernameAndStates(Long uid, String... states);

	List<OrderDriver> getOrderDriverListByOrderFarmerId(String orderId);

	boolean updateState(String order_id, String state);

	OrderDriver getOrderDriverById(String orderDriverId);

	OrderDriver getOrderDriverByOrderFarmerIdAndUid(String orderFarmerId, Long uid);

	boolean updateStartTime(String order_id, long time);

	boolean updateEndTime(String order_id, long time);

	boolean deleteOrderDriver(String orderDriverId);

}
