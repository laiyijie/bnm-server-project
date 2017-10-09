package cn.bangnongmang.server.business.common.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.bangnongmang.data.domain.OrderDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.server.business.common.StarBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.StarService;
import cn.bangnongmang.service.service.UserService;

@Transactional
@Service
public class StarBusinessImpl implements StarBusiness {

    @Autowired
    private StarService starService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private OrderFarmerService orderFarmerService;


    private void throwException(String s) throws BusinessException {
        throw new BusinessException(s);
    }

    @Override
    public void star(Long valutor, Long valuted, double star, String orderId, String type)
            throws BusinessException {
        long time = System.currentTimeMillis() / 1000;

        if (valuted.equals(valutor))
            throwException("不能评价自己");

        Long leader = orderFarmerService.getOrderFarmerById(orderId)
                                        .getDriver_leader();
        List<Long> driverIDs = orderDriverService.getOrderDriverListByOrderFarmerId(orderId)
                                                 .stream()
                                                 .map(OrderDriver::getUid)
                                                 .collect(Collectors.toList());

        if (type.equals(StarService.STAR_LEADER)) { // 评价队长

            if (!(valuted.equals(leader) && (driverIDs.stream()
                                                      .anyMatch(l -> l.equals(valutor)))))
                throwException("评价失败");

            if (!starService.updateUserLeaderStar(valuted, star))
                throwException("评价失败");
        } else if (type.equals(StarService.STAR_MEMBER)) {// 评价队员

            if (!(valutor.equals(leader) && (driverIDs.stream()
                                                      .anyMatch(l -> l.equals(valuted)))))
                throwException("评价失败");

            if (!starService.updateUserMemberStar(valuted, star))
                throwException("评价失败");
        }

        // TODO: 农户评价队长
        if (starService.getStarEvaluationByValutorAndValutedAndOrderId(valutor, valuted, orderId)
                       .size() > 0)
            throwException("您已评价过此人");
        if (starService.createEvaluation(valutor, valuted, star, time, orderId, type) <= 0)
            throwException("评价失败");
    }


}
