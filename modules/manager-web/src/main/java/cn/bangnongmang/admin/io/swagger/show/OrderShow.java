package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.io.swagger.converter.OrderConverter;
import cn.bangnongmang.admin.io.swagger.converter.UserConverter;
import cn.bangnongmang.admin.swagger.model.OrderBasic;
import cn.bangnongmang.admin.swagger.model.OrderDetail;
import cn.bangnongmang.admin.swagger.model.OrderMultiInfo;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.dao.OrderInviteMapper;
import cn.bangnongmang.data.dao.OrderMultiInfoMapper;
import cn.bangnongmang.data.domain.OrderInvite;
import cn.bangnongmang.data.domain.OrderInviteCriteria;
import cn.bangnongmang.data.domain.OrderMultiInfoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-05-16.
 */
@Service
public class OrderShow {

    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private OrderMultiInfoMapper orderMultiInfoMapper;
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;

    @Autowired
    private OrderInviteMapper orderInviteMapper;

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    public List<OrderBasic> getAllOrderBasic() {
        return orderFarmerInfoShowMapper.selectOrderFarmerInfoShow()
                                        .stream()
                                        .map(orderFarmerInfoShow -> orderConverter.convertToOrderBasic
                                                (orderFarmerInfoShow, OrderBasic.class))
                                        .collect(Collectors.toList());
    }

    public List<OrderMultiInfo> getOrderMultiInfoByOrderId(String orderId) {
        OrderMultiInfoCriteria orderMultiInfoCriteria = new OrderMultiInfoCriteria();
        orderMultiInfoCriteria.or()
                              .andOrder_idEqualTo(orderId);
        return orderMultiInfoMapper.selectByExample(orderMultiInfoCriteria)
                                   .stream()
                                   .map(info -> orderConverter.convertToOrderMultiInfo(info))
                                   .collect(Collectors.toList());
    }

    public OrderDetail getOrderDetail(String orderId) {
        OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId);
        if (orderFarmerInfoShow == null)
            return null;
        UserBasic belongto = userConverter.convertToUserBasic(userBasicInfoShowMapper.selectByUid(orderFarmerInfoShow.getOrderFarmer()
                                                                                                                     .getUid()),
                UserBasic.class);
        OrderInviteCriteria orderInviteCriteria = new OrderInviteCriteria();
        orderInviteCriteria.or()
                           .andFarmer_order_idEqualTo(orderId);
        List<OrderInvite> orderInvites = orderInviteMapper.selectByExample(orderInviteCriteria);
        UserBasic leader = null;
        if (!orderInvites.isEmpty()) {
            leader = userConverter.convertToUserBasic(userBasicInfoShowMapper.selectByUid(orderInvites.get(0)
                                                                                                      .getUid()), UserBasic.class);
        }
        return orderConverter.convertToOrderDetail(orderFarmerInfoShow, leader, belongto);


    }

}
