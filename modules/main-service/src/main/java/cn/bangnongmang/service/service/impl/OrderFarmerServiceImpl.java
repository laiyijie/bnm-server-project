package cn.bangnongmang.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.service.service.OrderFarmerService;

@Service("S_OrderFarmerService")
public class OrderFarmerServiceImpl implements OrderFarmerService {

	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private OrderInviteMapper orderInviteMapper;
	@Autowired
	private GrabSeasonMapper grabSeasonMapper;
	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;
	@Autowired
	private OptionOrderMappingMapper optionOrderMappingMapper;

	@Override
	public boolean createOrderFarmer(OrderFarmer orderFarmer) {

		if (orderFarmerMapper.insert(orderFarmer) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public OrderFarmer getOrderFarmerById(String id) {
		return orderFarmerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean changeStateTo(String id, String state) {
		OrderFarmer orderFarmer = new OrderFarmer();
		orderFarmer.setOrder_id(id);
		orderFarmer.setState(state);
		int flag = orderFarmerMapper.updateByPrimaryKeySelective(orderFarmer);
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean addOrderInvite(String farmerOrderId, Long uid) {
		OrderInvite orderInvite = new OrderInvite();
		orderInvite.setFarmer_order_id(farmerOrderId);
		orderInvite.setUid(uid);
		if (orderInviteMapper.insert(orderInvite) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOrderFarmerById(OrderFarmer orderFarmer) {
		if (orderFarmerMapper.updateByPrimaryKeySelective(orderFarmer) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public OrderInvite getOrderInvite(String orderId) {
		OrderInviteCriteria orderInviteCriteria = new OrderInviteCriteria();
		orderInviteCriteria.or()
						   .andFarmer_order_idEqualTo(orderId);
		List<OrderInvite> orderInvites = orderInviteMapper.selectByExample(orderInviteCriteria);
		if (orderInvites.isEmpty()) {
			return null;
		}
		return orderInvites.get(0);
	}

	@Override
	public List<SeasonOrders> getCurrentSeasonOrders() {
		GrabSeasonCriteria grabSeasonCriteria = new GrabSeasonCriteria();
		grabSeasonCriteria.or()
						  .andStart_timeLessThan(System.currentTimeMillis() / 1000)
						  .andEnd_timeGreaterThan(System.currentTimeMillis() / 1000)
						  .andStateEqualTo(GRAB_SEASON_STATE_PUBLISHED);
		List<GrabSeason> grabSeasons = grabSeasonMapper.selectByExample(grabSeasonCriteria);

		List<SeasonOrders> seasonOrders = null;
		if (grabSeasons.isEmpty()) {
			seasonOrders = new ArrayList<>();
		} else {
			SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
			grabSeasons.stream()
					   .forEach(grabSeason -> seasonOrdersCriteria.or()
																  .andSeason_idEqualTo(grabSeason.getId()));
			seasonOrders = seasonOrdersMapper.selectByExample(seasonOrdersCriteria);
		}
		return seasonOrders;
	}

	@Override
	public Boolean updateLeader(String orderId, Long leader) {

		OrderFarmer orderFarmer = new OrderFarmer();
		orderFarmer.setOrder_id(orderId);
		orderFarmer.setDriver_leader(leader);

		orderFarmerMapper.updateByPrimaryKeySelective(orderFarmer);

		return true;

	}

	@Override
	public Boolean saveOrderAndOptionRelation(String orderId, Long optionId) {

		OptionOrderMappingKey optionOrderMappingKey = new OptionOrderMappingKey();
		optionOrderMappingKey.setOrder_id(orderId);
		optionOrderMappingKey.setOption_id(optionId);
		if (optionOrderMappingMapper.insert(optionOrderMappingKey) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteOrderAndOptionRealtion(String orderId, Long optionId) {
		OptionOrderMappingKey optionOrderMappingKey = new OptionOrderMappingKey();
		optionOrderMappingKey.setOrder_id(orderId);
		optionOrderMappingKey.setOption_id(optionId);
		if (optionOrderMappingMapper.deleteByPrimaryKey(optionOrderMappingKey) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isExistOrderAndOptionReation(String orderId, Long optionId) {
		OptionOrderMappingCriteria optionOrderMappingCriteria=new OptionOrderMappingCriteria();
		optionOrderMappingCriteria.or().andOption_idEqualTo(optionId).andOrder_idEqualTo(orderId);
		if(optionOrderMappingMapper.countByExample(optionOrderMappingCriteria)>0){
	        return  true;
		}
		return false;
	}

}
