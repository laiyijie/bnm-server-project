package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.OrderBusiness;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.service.service.OptionService;
import cn.bangnongmang.service.service.OrderFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

/**
 * Created by lucq on 2017/6/19.
 */
@Component("orderBusiness")
public class OrderBusinessImp implements OrderBusiness{

    @Autowired
	private OrderFarmerService orderFarmerService;
    @Autowired
	private OptionService optionService;

	@Override
	public Boolean modifyOrderFarmer(OrderFarmer orderFarmer) {

		if(!orderFarmerService.updateOrderFarmerById(orderFarmer)){
			throw  new BusinessException("更新失败，请稍候再试");
		}
		return true;
	}

	@Override
	public Boolean saveOrderAndOptionRealtion(String orderId, Long optionId) {
		if(orderFarmerService.isExistOrderAndOptionReation(orderId,optionId)){
			throw new BusinessException("该关系已存在无需保存");
		}
		if(orderFarmerService.getOrderFarmerById(orderId)==null){
			throw new BusinessException("订单不存在");
		}
		if(optionService.getOptionDetailById(optionId)==null){
			throw new BusinessException("选项不存在");
		}
		if(orderFarmerService.saveOrderAndOptionRelation(orderId,optionId)){
			return  true;
		}
		return false;
	}

	@Override
	public Boolean deleteOrderAndOptionRealtion(String orderId, Long optionId) {

		if(!orderFarmerService.deleteOrderAndOptionRealtion(orderId,optionId)){
			throw new BusinessException("该关系不存在，无需删除");
		}
		return true;
	}
}
