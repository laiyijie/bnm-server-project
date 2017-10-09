package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.data.combo.domain.GrabSeasonInfoShow;
import cn.bangnongmang.data.combo.domain.OrderDriverWorkSizeInfoShow;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.io.swagger.util.NumberUtil;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.server.swagger.model.OrderMultiInfo;
import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.service.service.GrabSeasonService;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderMultiInfoService;
import cn.bangnongmang.service.service.WorkSizeService;
import cn.bangnongmang.server.swagger.model.*;
import cn.bangnongmang.server.swagger.model.GrabSeason;
import cn.bangnongmang.server.swagger.model.OrderGeo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-14.
 */
@Service
public class OrderConverter {
    @Autowired
    private MachineConverter machineConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;

    @Autowired
    private SizeCounterService sizeCounterService;

    public OrderMultiInfo convertToOrderMultiInfo(cn.bangnongmang.data.domain.OrderMultiInfo info) {
        if (info == null) return null;
        OrderMultiInfo orderMultiInfo = new OrderMultiInfo();
        orderMultiInfo.setContent(info.getContent());
        if (OrderMultiInfoService.TYPE_FIELD.equals(info.getType())) {
//            Double size = null;
//            try {
//                size = sizeCounterService.getDefaultApi().marketOrderidOrderFieldsTotalSizeGet(info.getOrder_id()).execute().body();
//            } catch (Exception e) {
//                BLog.errorJsonLogBuilder()
//                    .addErrorMessage("get_order_field_total_size_failed")
//                    .put("order_id",info.getOrder_id())
//                    .log();
//            }
//            if (size != null)
//                orderMultiInfo.setContent("订单总亩数：" + size + "亩");
//            else
//                orderMultiInfo.setContent("订单总亩数：" + 0.0 + "亩");
           OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(info.getOrder_id());
            orderMultiInfo.setContent("订单总亩数：" + orderFarmer.getSize() + "亩");
        }

        orderMultiInfo.setUpdateTime(info.getUpdate_time() * 1000);
        orderMultiInfo.setType(
                OrderMultiInfoService.TYPE_PHOTO.equals(info.getType()) ? OrderMultiInfo.TypeEnum.PHOTO : OrderMultiInfo.TypeEnum.SIZE);
        orderMultiInfo.setOrderId(info.getOrder_id());
        orderMultiInfo.setId(info.getId());
        orderMultiInfo.setExtraInfo(info.getExtra_info());
        orderMultiInfo.setCreateTime(info.getCreate_time() * 1000);
        return orderMultiInfo;
    }

    public WorkSizeAuthImage convertToWorkSizeAuthImage(OrderWorkSizeImage orderWorkSizeImage) {
        if (orderWorkSizeImage == null) return null;
        WorkSizeAuthImage workSizeAuthImage = new WorkSizeAuthImage();
        workSizeAuthImage.setImageUrl(orderWorkSizeImage.getImage_path());
        workSizeAuthImage.setId(orderWorkSizeImage.getId());
        workSizeAuthImage.setTitle(orderWorkSizeImage.getTitile());
        return workSizeAuthImage;
    }

    public SubOrder convertToSubOrder(OrderDriver orderDriver) {
        if (orderDriver == null) return null;
        SubOrder subOrder = new SubOrder();
        switch (orderDriver.getState()) {
            case OrderDriverService.ORDER_DRIVER_STATE_ALL_FINISHED:
                subOrder.setState(SubOrder.StateEnum.FINISHED);
                break;
            case OrderDriverService.ORDER_DRIVER_STATE_GOT:
                subOrder.setState(SubOrder.StateEnum.GOT);
                break;
            case OrderDriverService.ORDER_DRIVER_STATE_STOPPED:
                subOrder.setState(SubOrder.StateEnum.STOPPED);
                break;
            case OrderDriverService.ORDER_DRIVER_STATE_WORKING:
                subOrder.setState(SubOrder.StateEnum.WORKING);
                break;
        }
        subOrder.setActualMoney(orderDriver.getActual_money());
        subOrder.setActualSize(orderDriver.getActual_size());
        subOrder.setEndTime(NumberUtil.convertToMillisTime(orderDriver.getService_end()));
        subOrder.setId(orderDriver.getOrder_id());
        subOrder.setUid(orderDriver.getUid());
        subOrder.setStartTime(NumberUtil.convertToMillisTime(orderDriver.getService_start()));
        subOrder.setOrderId(orderDriver.getOrder_farmer_id());
        return subOrder;
    }

    public GrabSeason convertToGrabSeason(GrabSeasonInfoShow grabSeasonInfoShow) {
        GrabSeason grabSeason = new GrabSeason();
        grabSeason.setState(convertToGrabSeasonState(grabSeasonInfoShow.getGrabSeason()
                                                                       .getState
                                                                               ()));
        grabSeason.setSeasonId(grabSeasonInfoShow.getId());
        grabSeason.setOrders(grabSeasonInfoShow.getSeasonOrders()
                                               .stream()
                                               .map(seasonOrders ->
                                                       seasonOrders.getOrder_id())
                                               .collect(Collectors.toList()));
        grabSeason.setEndTime(NumberUtil.convertToMillisTime(grabSeasonInfoShow.getGrabSeason()
                                                                               .getEnd_time()));
        grabSeason.setStartTime(NumberUtil.convertToMillisTime(grabSeasonInfoShow.getGrabSeason()
                                                                                 .getStart_time()));
        return grabSeason;

    }

    public GrabSeason.StateEnum convertToGrabSeasonState(Integer state) {
        if (GrabSeasonService.STATE_DRAFT.equals(state)) {
            return GrabSeason.StateEnum.DRAFT;
        } else if (GrabSeasonService.STATE_PUBLISHED.equals(state)) {
            return GrabSeason.StateEnum.PUBLISHED;
        } else {
            return GrabSeason.StateEnum.DRAFT;
        }
    }

    public Order convertToOrder(OrderFarmerInfoShow orderFarmerInfoShow) {
        if (orderFarmerInfoShow == null) {
            return null;
        }
        Order order = new Order();
        order.setActualEndTime(NumberUtil.convertToMillisTime(orderFarmerInfoShow.getOrderFarmer()
                                                                                 .getActual_finish_time()));
        order.setActualStartTime(NumberUtil.convertToMillisTime(orderFarmerInfoShow.getOrderFarmer()
                                                                                   .getActual_start_time()));
        order.setUid(orderFarmerInfoShow.getOrderFarmer()
                                        .getUid());
        order.setCompanyBonus(orderFarmerInfoShow.getOrderFarmer()
                                                 .getCompany_bonus());
        order.setDesiredMachineNum(orderFarmerInfoShow.getOrderFarmer()
                                                      .getDesire_num());
        order.setDriverInsurance(orderFarmerInfoShow.getOrderFarmer()
                                                    .getDriver_insurance());
        order.setDriverLeader(orderFarmerInfoShow.getOrderFarmer()
                                                 .getDriver_leader());
        order.setFarmerDiscount(orderFarmerInfoShow.getOrderFarmer()
                                                   .getDiscount());
        order.setFarmerName(orderFarmerInfoShow.getOrderFarmer()
                                               .getName());
        order.setWorkingType(
                machineConverter.convertToWorkingType(orderFarmerInfoShow.getOptionWorkingType()));
        order.setUniPrice(orderFarmerInfoShow.getOrderFarmer()
                                             .getUni_price());
        order.setTags(JSON.parseArray(orderFarmerInfoShow.getOrderFarmer()
                                                         .getTags(), String.class) == null ? new ArrayList<OrderTag>() : JSON.parseArray(
                orderFarmerInfoShow.getOrderFarmer()
                                   .getTags(), String.class)
                                                                                                                             .stream()
                                                                                                                             .map(tagName -> convertToOrderTag(
                                                                                                                                     tagName))
                                                                                                                             .collect(
                                                                                                                                     Collectors.toList()));
        order.setState(Order.StateEnum.fromValue(orderFarmerInfoShow.getOrderFarmer()
                                                                    .getState()));
        order.setStartTime(NumberUtil.convertToMillisTime(orderFarmerInfoShow.getOrderFarmer()
                                                                             .getDesire_start_time()));
        order.setSize(orderFarmerInfoShow.getOrderFarmer()
                                         .getSize());
        order.setOrderId(orderFarmerInfoShow.getOrderFarmer()
                                            .getOrder_id());
        order.setOptions(orderFarmerInfoShow.getOptions()
                                            .stream()
                                            .map(optionClusterDetailInfo ->
                                                    machineConverter.convertToOption(
                                                            optionClusterDetailInfo))
                                            .collect(
                                                    Collectors.toList()));
        order.setLeaderBonus(orderFarmerInfoShow.getOrderFarmer()
                                                .getLeader_bonus());
        order.setGotTime(NumberUtil.convertToMillisTime(orderFarmerInfoShow.getOrderFarmer()
                                                                           .getGot_time()));
        order.setGeoInfo(convertToOrderGeo(orderFarmerInfoShow));
        order.setFarmerTel(orderFarmerInfoShow.getOrderFarmer()
                                              .getTel());

        return order;
    }

    public OrderTag convertToOrderTag(String tag) {
        OrderTag orderTag = new OrderTag();
        orderTag.setTagName(tag);
        return orderTag;
    }

    public OrderGeo convertToOrderGeo(OrderFarmerInfoShow orderFarmerInfoShow) {

        if (orderFarmerInfoShow == null) {
            return null;
        }

        OrderGeo orderGeo = new OrderGeo();

        orderGeo.setCity(orderFarmerInfoShow.getOrderFarmer()
                                            .getCity());
        orderGeo.setDetail(orderFarmerInfoShow.getOrderFarmer()
                                              .getDetail());
        orderGeo.setCounty(orderFarmerInfoShow.getOrderFarmer()
                                              .getCounty());
        orderGeo.setTown(orderFarmerInfoShow.getOrderFarmer()
                                            .getTown());
        orderGeo.setLatitude(orderFarmerInfoShow.getOrderGeo()
                                                .getLatitude());
        orderGeo.setLongitude(orderFarmerInfoShow.getOrderGeo()
                                                 .getLongtitude());
        orderGeo.setProvince(orderFarmerInfoShow.getOrderFarmer()
                                                .getProvince());
        return orderGeo;
    }

    public OrderFarmerWorkInfo convertToOrderFarmerWorkInfo(OrderFarmerWorkSize
                                                                    orderFarmerWorkSize,
                                                            List<OrderDriverWorkSizeInfoShow> orderDriverWorkSizeInfoShows
    ) {
        OrderFarmerWorkInfo orderFarmerWorkInfo = new OrderFarmerWorkInfo();
        orderFarmerWorkInfo.setId(orderFarmerWorkSize.getId());
        orderFarmerWorkInfo.setOrderId(orderFarmerWorkSize.getOrder_farmer_id());
        orderFarmerWorkInfo.setState(convertToOrderFarmerWorkInfoState(orderFarmerWorkSize
                .getState()));
        orderFarmerWorkInfo.setTime(orderFarmerWorkSize.getTime());
        orderFarmerWorkInfo.setTotalSize(orderFarmerWorkSize.getSize());
        orderFarmerWorkInfo.setOrderDriverWorkInfos(orderDriverWorkSizeInfoShows.stream()
                                                                                .map
                                                                                        (this::convertToOrderDriverWorkInfo)
                                                                                .collect(
                                                                                        Collectors.toList()));
        return orderFarmerWorkInfo;
    }

    public OrderFarmerWorkInfo.StateEnum convertToOrderFarmerWorkInfoState(Integer state) {
        if (WorkSizeService.WORK_SIZE_STATE_DENIED.equals(state)) {
            return OrderFarmerWorkInfo.StateEnum.DENIED;
        } else if (WorkSizeService.WORK_SIZE_STATE_ENSURED.equals(state)) {
            return OrderFarmerWorkInfo.StateEnum.ENSURED;
        } else if (WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE.equals(state)) {
            return OrderFarmerWorkInfo.StateEnum.WAITING_ENSURE;
        } else if (WorkSizeService.WORK_SIZE_STATE_FINISH.equals(state)) {
            return OrderFarmerWorkInfo.StateEnum.FINISH;
        } else {
            return OrderFarmerWorkInfo.StateEnum.DENIED;
        }
    }

    public OrderDriverWorkInfo convertToOrderDriverWorkInfo(OrderDriverWorkSizeInfoShow
                                                                    orderDriverWorkSizeInfoShow) {
        OrderDriverWorkInfo orderDriverWorkInfo = new OrderDriverWorkInfo();
        orderDriverWorkInfo.setDriver(userConverter.convertToUserSimple
                (orderDriverWorkSizeInfoShow.getUserBasicInfoShow(), UserSimple.class));
        orderDriverWorkInfo.setOrderFarmerWorkInfoId(orderDriverWorkSizeInfoShow
                .getOrderDriverWorkSize()
                .getOrder_farmer_work_size_id());
        orderDriverWorkInfo.setSize(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                                                               .getSize());
        return orderDriverWorkInfo;
    }

}
