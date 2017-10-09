package cn.bangnongmang.server.io.android.show;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.OrderDriverWorkSizeInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.StarEvaluations;
import cn.bangnongmang.driverapp.models.OrderStar;
import cn.bangnongmang.driverapp.models.StarUserSimple;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.StarService;

@Service
public class AndroidStarShow {

    @Autowired
    private OrderDriverWorkSizeInfoShowMapper orderDriverWorkSizeInfoShowMapper;

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private OrderFarmerService orderFarmerService;
    @Autowired
    private StarService starService;
    @Autowired
    private AndroidUserShow androidUserShow;

    public OrderStar checkHasUnStarOrder(Long uid) {
        // 找到最新完成订单
        OrderDriver orderDriver = null;
        try {
            orderDriver = orderDriverService
                    .getOrderDriverByUsernameAndStates(uid,
                            OrderDriverService.ORDER_DRIVER_STATE_ALL_FINISHED)
                    .stream().max((a, b) -> a.getOrder_id().compareTo(b.getOrder_id())).get();
        } catch (NoSuchElementException e) {
            return null;
        }
        String orderId = orderDriver.getOrder_farmer_id();

        // 拿到父单
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderId);

        // 拿到所有评价
        List<StarEvaluations> starEvaluations = starService
                .getStarEvaluationsByValutorAndOrderId(uid, orderId);

        // 拿到所有评价过的人的id
        List<Long> valutedIds = starEvaluations.stream().map((d) -> d.getValuted())
                .collect(Collectors.toList());

        Long leader = orderFarmer.getDriver_leader();
        UserBasicInfoShow lead = userBasicInfoShowMapper.selectByUid(orderFarmer.getDriver_leader
                ());
        OrderStar orderStar = new OrderStar();
        orderStar.setOrderID(orderId);
        orderStar.setLeaderID(lead.getAccount().getUsername());
        if (leader.equals(uid)) { // 队长

            // 拿到所有队员 除了队长
            List<StarUserSimple> temp = orderDriverService
                    .getOrderDriverListByOrderFarmerId(orderFarmer.getOrder_id())
                    .stream().filter((d) -> !d.getUid().equals(uid)).map((d) -> {
                        StarUserSimple starUserSimple = new StarUserSimple();
                        starUserSimple.setUserInfoSimple(
                                androidUserShow.convertUserBasicInfoShowToUserInfoSimple(
                                        userBasicInfoShowMapper
                                                .selectByUid(d.getUid())));
                        starUserSimple.setStared(valutedIds.contains(d.getUid()));
                        return starUserSimple;
                    }).collect(Collectors.toList());

            orderStar.setStarUserSimples(temp);

        } else { // 队员
            StarUserSimple starUserSimple = new StarUserSimple();
            starUserSimple.setUserInfoSimple(androidUserShow
                    .convertUserBasicInfoShowToUserInfoSimple(
                            userBasicInfoShowMapper.selectByUid(leader)));
            starUserSimple.setStared(valutedIds.contains(leader));
            List<StarUserSimple> temp = new ArrayList<>();
            temp.add(starUserSimple);
            orderStar.setStarUserSimples(temp);
        }
        return orderStar;
    }

}
