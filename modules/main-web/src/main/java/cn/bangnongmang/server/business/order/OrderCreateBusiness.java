package cn.bangnongmang.server.business.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.AreaPriceService;
import cn.bangnongmang.service.service.GeoService;
import cn.bangnongmang.service.service.OptionService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.RegionService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@Service
@Transactional
public class OrderCreateBusiness {

    public static final String STATE_INIT = "FARMER_WAITTING_AUTH";

    @Autowired
    private UserService userService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private ServiceUtil serviceUtil;
    @Autowired
    private OrderFarmerService orderFarmerService;
    @Autowired
    private GeoService geoService;
    @Autowired
    private AreaPriceService areaPriceService;
    @Autowired
    private OptionService optionService;

    public static final Integer DEFAULT_DISCOUNT = 100;

    public static final Integer DEFAULT_PAY_RATE = 100;

    public static final Integer DEFAULT_COMPANY_BONUS = 10;

    public static final Integer DEFAULT_LEADER_BONUS = 0;

    public static final Integer DEFAULT_DRIVER_INSURANCE = 50000;

    public static final Integer DEFAULT_DAY_OUT_MONEY_RATE = 5000;

    public AreaPrice getUniPrice(String regionId) throws BusinessException {
        Region region = regionService.getRegionById(regionId);

        if (region == null) {
            return areaPriceService.getAreaPriceByPosition(null, null, null, null);
        }

        return areaPriceService.getAreaPriceByPosition(region.getProvince(), region.getCity(),
                region.getCounty(),
                region.getTown());
    }

    public OrderFarmer createOrder(Long uid, String regionId, Long working_type_id, Integer
            num, Double size,
                                   Long startTime, Long invite, Set<Long> options, List<String>
                                           tStrings) throws BusinessException {

        if (!userService.isFarmer(uid)) {

            throw new BusinessException(3001);
        }
        if (regionId == null || "".equals(regionId)) {
            throw new BusinessException(3002);
        }
        if (working_type_id == null) {
            throw new BusinessException("请选择作业种类");
        }
        if (num == null) {
            throw new BusinessException(3006);
        }
        if (size == null) {
            throw new BusinessException(3007);
        }
        if (startTime == null) {
            throw new BusinessException(3008);
        }

        if (tStrings == null) {
            tStrings = new ArrayList<>();
        }

        Region region = regionService.getRegionById(regionId);

        if (region == null) {
            throw new BusinessException("该位置不存在");
        }

        tStrings.add("平台提成10%");
        OrderFarmer orderFarmer = new OrderFarmer();
        orderFarmer.setActual_finish_time(null);
        orderFarmer.setActual_start_time(null);
        orderFarmer.setUid(uid);
        orderFarmer.setCity(region.getCity());
        orderFarmer.setCounty(region.getCounty());
        orderFarmer.setDesire_num(num);
        orderFarmer.setDesire_start_time(startTime);
        orderFarmer.setDetail(region.getDetail());
        orderFarmer.setDiscount(DEFAULT_DISCOUNT);
        orderFarmer.setDriver_leader(null);
        orderFarmer.setGot_time(null);
        orderFarmer.setName(region.getName());
        orderFarmer.setOrder_id(serviceUtil.generateId(uid, 4, 6));
        orderFarmer.setPre_pay_rate(DEFAULT_PAY_RATE);
        orderFarmer.setProvince(region.getProvince());
        orderFarmer.setSize(size);
        orderFarmer.setState(STATE_INIT);
        orderFarmer.setTags(JSON.toJSONString(tStrings));
        orderFarmer.setTel(region.getTel());
        orderFarmer.setTown(region.getTown());
        orderFarmer.setUni_price(areaPriceService
                .getAreaPriceByPosition(region.getProvince(), region.getCity(), region.getCounty(),
                        region.getTown())
                .getUniprice());
        orderFarmer.setWorking_type_id(working_type_id);
        orderFarmer.setCompany_bonus(DEFAULT_COMPANY_BONUS);
        orderFarmer.setLeader_bonus(DEFAULT_LEADER_BONUS);
        orderFarmer.setDriver_insurance(DEFAULT_DRIVER_INSURANCE);
        orderFarmer.setDay_money_out_rate(DEFAULT_DAY_OUT_MONEY_RATE);

        boolean flag = orderFarmerService.createOrderFarmer(orderFarmer);

        if (options != null && !options.isEmpty()) {
            for (Long option : options) {
                optionService.addOptionOrderMapping(orderFarmer.getOrder_id(), option);
            }
        }

        if (invite != null && !"".equals(invite)) {
            Account account = userService.getUserInfo(invite);
            if (account == null) {
                throw new BusinessException("您邀请的用户不存在");
            }
            orderFarmerService.addOrderInvite(orderFarmer.getOrder_id(), invite);
            tStrings.add("农户指定队长接单");
            orderFarmer.setTags(JSON.toJSONString(tStrings));
        }

        RegionGeo regionGeo = regionService.getRegionGeo(regionId);
        if (regionGeo == null) {
            throw new BusinessException("此地址为旧地址，请重新选择");
        }

        OrderGeo orderGeo = new OrderGeo();
        orderGeo.setLatitude(regionGeo.getLatitude());
        orderGeo.setLongtitude(regionGeo.getLongtitude());
        orderGeo.setOrder_id(orderFarmer.getOrder_id());
        orderGeo.setPs("");


        if (geoService.createOrderGeo(orderGeo) == null) {
            throw new BusinessException("创建地理位置失败");
        }

        if (flag == true) {
            return orderFarmer;
        }

        throw new BusinessException("创建订单失败");
    }

}
