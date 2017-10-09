package cn.bangnongmang.server.io.swagger.show;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.StarEvaluationsMapper;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.driverapp.models.OrderStar;
import cn.bangnongmang.driverapp.models.StarUserSimple;
import cn.bangnongmang.server.io.swagger.OrderConverter;
import cn.bangnongmang.server.io.swagger.StarConverter;
import cn.bangnongmang.server.swagger.model.Order;
import cn.bangnongmang.server.swagger.model.StarInfo;
import cn.bangnongmang.service.service.OrderDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-05-26.
 */
@Service
public class StarShow {

    @Autowired
    private StarEvaluationsMapper starEvaluationsMapper;

    @Autowired
    private StarConverter starConverter;

    @Autowired
    private OrderDriverMapper orderDriverMapper;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;

    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;


    public List<StarInfo> getStarInfoByOrderIdAndValutor(String orderId,Long uid){
        StarEvaluationsCriteria starEvaluationsCriteria = new StarEvaluationsCriteria();
        starEvaluationsCriteria.or()
                               .andOrder_idEqualTo(orderId)
                               .andValutorEqualTo(uid);
        return starEvaluationsMapper.selectByExample(starEvaluationsCriteria)
                .stream().map(starEvaluations ->starConverter.convertToStarInfo(starEvaluations) )
                .collect(Collectors.toList());
    }

    public List<Order> getUnStaredOrder(Long uid){
        List<Order> result = new ArrayList<>();
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andUidEqualTo(uid).andStateEqualTo(OrderDriverService.ORDER_DRIVER_STATE_ALL_FINISHED);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        Optional<OrderDriver> orderDriver = orderDrivers.stream().max(Comparator.comparing
                (OrderDriver::getOrder_id));
        if (!orderDriver.isPresent()){
            return  result;
        }
        OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderDriver.get().getOrder_farmer_id());
        if (orderFarmerInfoShow == null)
            return result;
        orderDriverCriteria.clear();
        orderDriverCriteria.or().andOrder_farmer_idEqualTo(orderFarmerInfoShow.getOrderFarmer().getOrder_id());
        List<OrderDriver> allMembers = orderDriverMapper.selectByExample(orderDriverCriteria);
        StarEvaluationsCriteria starEvaluationsCriteria = new StarEvaluationsCriteria();
        starEvaluationsCriteria.or().andValutorEqualTo(uid).andOrder_idEqualTo(orderFarmerInfoShow.getOrderFarmer().getOrder_id());
        Set<Long> staredUser = starEvaluationsMapper.selectByExample(starEvaluationsCriteria).stream().map(StarEvaluations::getValuted).collect(Collectors
                .toSet());

        if (orderFarmerInfoShow.getOrderFarmer().getDriver_leader().equals(uid)){
            Set<Long> members  = allMembers.stream()
                                              .filter(a->!a.getUid().equals(uid))
                                              .map(OrderDriver::getUid)
                                              .collect(Collectors.toSet());
            members.removeAll(staredUser);
            if (!members.isEmpty())
                result.add(orderConverter.convertToOrder(orderFarmerInfoShow));
            return result;
        }else {
            if (staredUser.isEmpty())
                result.add(orderConverter.convertToOrder(orderFarmerInfoShow));
            return result;
        }
    }

}
