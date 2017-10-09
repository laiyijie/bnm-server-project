package cn.bangnongmang.admin.business;

import cn.bangnongmang.data.domain.OrderFarmer;

/**
 * Created by lucq on 2017/6/19.
 */
public interface OrderBusiness {

    public Boolean modifyOrderFarmer(OrderFarmer orderFarmer);
    public Boolean saveOrderAndOptionRealtion(String orderId,Long optionId);
    public Boolean deleteOrderAndOptionRealtion(String orderId,Long optionId);

}
