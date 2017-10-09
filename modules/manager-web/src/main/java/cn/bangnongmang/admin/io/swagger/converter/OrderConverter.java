package cn.bangnongmang.admin.io.swagger.converter;

import cn.bangnongmang.admin.service.OrderFarmerService;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.admin.swagger.model.OrderGeo;
import cn.bangnongmang.admin.swagger.model.OrderMultiInfo;
import cn.bangnongmang.data.combo.domain.OptionClusterDetailInfo;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.service.service.OrderMultiInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-05-16.
 */
@Service
public class OrderConverter {
    @Autowired
    private SizeCounterService sizeCounterService;


    public OrderDetail convertToOrderDetail(OrderFarmerInfoShow ofis, UserBasic inviteLeader, UserBasic belongto) {
        OrderDetail orderDetail = convertToOrderBasic(ofis, OrderDetail.class);
        orderDetail.setTags(JSON.parseArray(ofis.getOrderFarmer()
                                                .getTags(), String.class)
                                .stream()
                                .map(this::convertToOrderTag)
                                .collect(Collectors.toList()));
        orderDetail.setLeaderBonus(ofis.getOrderFarmer()
                                       .getLeader_bonus());
        orderDetail.setDesireTime(ofis.getOrderFarmer().getDesire_start_time());
        orderDetail.setInviteLeader(inviteLeader);
        orderDetail.setFarmerPrepayRate(ofis.getOrderFarmer()
                                            .getPre_pay_rate());
        orderDetail.setFarmerDiscount(ofis.getOrderFarmer()
                                          .getDiscount());
        orderDetail.setDriverInsurance(ofis.getOrderFarmer()
                                           .getDriver_insurance());
        orderDetail.setCompanyBonus(ofis.getOrderFarmer()
                                        .getCompany_bonus());
        orderDetail.setBelongto(belongto);
        return orderDetail;
    }


    public OrderMultiInfo convertToOrderMultiInfo(cn.bangnongmang.data.domain.OrderMultiInfo info) {
        if (info == null) return null;
        OrderMultiInfo orderMultiInfo = new OrderMultiInfo();
        orderMultiInfo.setContent(info.getContent());
        orderMultiInfo.setUpdateTime(info.getUpdate_time() * 1000);
        orderMultiInfo.setType(
                OrderMultiInfoService.TYPE_PHOTO.equals(info.getType()) ? OrderMultiInfo.TypeEnum.PHOTO : OrderMultiInfo.TypeEnum.SIZE);
        orderMultiInfo.setPublished(OrderMultiInfoService.STATE_PUBLISH.equals(info.getState()));
        orderMultiInfo.setOrderId(info.getOrder_id());
        orderMultiInfo.setId(info.getId());
        orderMultiInfo.setExtraInfo(info.getExtra_info());
        orderMultiInfo.setCreateTime(info.getCreate_time() * 1000);
        if (OrderMultiInfoService.TYPE_FIELD.equals(info.getType())) {
            Double size = null;
            try {
                size = sizeCounterService.getDefaultApi()
                                         .marketOrderidOrderFieldsTotalSizeGet(info.getOrder_id())
                                         .execute()
                                         .body();
            } catch (Exception e) {
                BLog.errorJsonLogBuilder()
                    .addErrorMessage("get_order_field_total_size_failed")
                    .put("order_id", info.getOrder_id())
                    .log();
            }
            if (size != null)
                orderMultiInfo.setContent("订单总亩数：" + size + "亩");
            else
                orderMultiInfo.setContent("订单总亩数：" + 0.0 + "亩");
        }
        return orderMultiInfo;
    }


    public <T extends OrderBasic> T convertToOrderBasic(OrderFarmerInfoShow orderFarmerInfoShow, Class<T> dst) {

        if (orderFarmerInfoShow == null) return null;

        T orderBasic = null;
        try {
            orderBasic = dst.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
        orderBasic.setAuth(convertToOrderBasicAuthEnum(orderFarmerInfoShow.getOrderFarmer()
                                                                          .getState()));
        orderBasic.setDesireNum(orderFarmerInfoShow.getOrderFarmer()
                                                   .getDesire_num());
        orderBasic.setWorkingType(convertToWorkingType(orderFarmerInfoShow.getOptionWorkingType()));
        orderBasic.setUniPrice(orderFarmerInfoShow.getOrderFarmer()
                                                  .getUni_price());
        orderBasic.setState(OrderBasic.StateEnum.fromValue(orderFarmerInfoShow.getOrderFarmer()
                                                                              .getState()));
        orderBasic.setSize(orderFarmerInfoShow.getOrderFarmer()
                                              .getSize());
        orderBasic.setOrderId(orderFarmerInfoShow.getOrderFarmer()
                                                 .getOrder_id());
        orderBasic.setOptions(orderFarmerInfoShow.getOptions() == null ? new ArrayList<Option>() : orderFarmerInfoShow.getOptions()
                                                                                                                      .stream()
                                                                                                                      .map(this::convertToOption)
                                                                                                                      .collect(Collectors.toList()));
        orderBasic.setFarmerTel(orderFarmerInfoShow.getOrderFarmer()
                                                   .getTel());
        orderBasic.setFarmerName(orderFarmerInfoShow.getOrderFarmer()
                                                    .getName());
        orderBasic.setOrderGeo(convertToOrderGeo(orderFarmerInfoShow));
        return orderBasic;
    }

    private OrderGeo convertToOrderGeo(OrderFarmerInfoShow ofis) {
        if (ofis == null) return null;
        OrderGeo orderGeo = new OrderGeo();
        orderGeo.setCity(ofis.getOrderFarmer()
                             .getCity());
        orderGeo.setCounty(ofis.getOrderFarmer()
                               .getCounty());
        orderGeo.setDetail(ofis.getOrderFarmer()
                               .getDetail());
        orderGeo.setTown(ofis.getOrderFarmer()
                             .getTown());
        orderGeo.setProvince(ofis.getOrderFarmer()
                                 .getProvince());
        if (ofis.getOrderGeo() != null) {
            orderGeo.setLatitude(ofis.getOrderGeo()
                                     .getLatitude());
            orderGeo.setLongitude(ofis.getOrderGeo()
                                      .getLongtitude());
        }
        return orderGeo;
    }

    public Option convertToOption(OptionClusterDetailInfo optionClusterDetailInfo) {
        if (optionClusterDetailInfo == null) return null;
        Option option = new Option();
        option.setClusterId(optionClusterDetailInfo.getOptionCluster()
                                                   .getId());
        option.setClusterName(optionClusterDetailInfo.getOptionCluster()
                                                     .getCluster_name());
        option.setId(optionClusterDetailInfo.getOptionDetail()
                                            .getId());
        option.setName(optionClusterDetailInfo.getOptionDetail()
                                              .getOption_name());
        return option;
    }

    public OrderBasic.AuthEnum convertToOrderBasicAuthEnum(String state) {
        if (OrderFarmerStateName.FARMER_AUTH_FAILED.equals(state))
            return OrderBasic.AuthEnum.DENIED;
        if (OrderFarmerStateName.FARMER_WAITTING_AUTH.equals(state))
            return OrderBasic.AuthEnum.WAITING_AUTH;
        return OrderBasic.AuthEnum.AUTHED;
    }

    public WorkingType convertToWorkingType(OptionWorkingType optionWorkingType) {
        if (optionWorkingType == null) return null;
        WorkingType workingType = new WorkingType();
        workingType.setId(optionWorkingType.getId());
        workingType.setCropType(optionWorkingType.getCrop_type());
        workingType.setWorkingType(optionWorkingType.getWorking_type());
        return workingType;
    }

    public OrderTag convertToOrderTag(String tagName) {
        OrderTag orderTag = new OrderTag();
        orderTag.setTagName(tagName);
        return orderTag;
    }

}
