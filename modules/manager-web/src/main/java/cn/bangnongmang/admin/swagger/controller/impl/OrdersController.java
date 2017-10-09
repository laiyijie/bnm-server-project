package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.OrderBusiness;
import cn.bangnongmang.admin.business.OrderMultiInfoBusiness;
import cn.bangnongmang.admin.io.swagger.show.OrderShow;
import cn.bangnongmang.admin.io.swagger.show.TeamShow;
import cn.bangnongmang.admin.swagger.api.OrdersApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.service.service.OrderMultiInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-05-16.
 */
@RestController
@RequestMapping(BaseConf.BASE_URL)
public class OrdersController implements OrdersApi {

    @Autowired
    private OrderShow orderShow;
    @Autowired
    private TeamShow teamShow;

    @Autowired
    private OrderMultiInfoBusiness orderMultiInfoBusiness;

    @Autowired
    private OrderBusiness orderBusiness;

    @Override
    public ResponseEntity<List<OrderBasic>> ordersGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(orderShow.getAllOrderBasic());
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdAcceptPut(
            @ApiParam(value = "订单ID",required=true ) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        OrderFarmer orderFarmer=new OrderFarmer();
        orderFarmer.setOrder_id(orderId);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_PAY);
        orderBusiness.modifyOrderFarmer(orderFarmer);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDetail> ordersOrderIdGet(@ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
                                                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        return ResponseEntity.ok(orderShow.getOrderDetail(orderId));
    }

    @Override
    public ResponseEntity<List<UserBasic>> ordersOrderIdMembergeoGet(
            @ApiParam(value = "订单ID",required=true ) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<UserBasic>>(teamShow.getTeamUserBasicByOrderId(orderId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderMultiInfo>> ordersOrderIdMultiInfosGet(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(orderShow.getOrderMultiInfoByOrderId(orderId));
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdMultiInfosIdDelete(@ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
                                                                @ApiParam(value = "", required = true) @PathVariable("id") Long id,
                                                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        orderMultiInfoBusiness.deleteOrderMultiInfo(orderId, id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdMultiInfosIdStatusPublishPost(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "", required = true) @PathVariable("id") Long id, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        orderMultiInfoBusiness.publishOrderMultiInfo(orderId, id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdMultiInfosIdStatusUnpublishPost(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "", required = true) @PathVariable("id") Long id, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        orderMultiInfoBusiness.unPublishOrderMultiInfo(orderId, id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdMultiInfosPost(@ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
                                                            @ApiParam(value = "不需要传id", required = true) @Valid @RequestBody OrderMultiInfo multiInfo,
                                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (multiInfo.getType() == null) throw new BusinessException("type 不能为空");
        List<String> extra = JSON.parseArray(multiInfo.getExtraInfo(), String.class);
        if (multiInfo.getType()
                     .equals(OrderMultiInfo.TypeEnum.PHOTO)) {
            orderMultiInfoBusiness.addOrderMultiInfoPhotos(orderId, multiInfo.getContent(), extra);
        } else {
            orderMultiInfoBusiness.addOrderMultiInfoFields(orderId, extra);
        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdOptionOptionIdDelete(
            @ApiParam(value = "",required=true ) @PathVariable("orderId")  String orderId,
            @ApiParam(value = "",required=true ) @PathVariable("optionId") Long optionId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        orderBusiness.deleteOrderAndOptionRealtion(orderId,optionId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdOptionOptionIdPost(
            @ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,
            @ApiParam(value = "",required=true ) @PathVariable("optionId") Long optionId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        orderBusiness.saveOrderAndOptionRealtion(orderId,optionId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> ordersOrderIdPut(
            @ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,
            @ApiParam(value = "" ,required=true )  @Valid @RequestBody OrderDetail orderDetail,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        OrderFarmer orderFarmer=new OrderFarmer();
        orderFarmer.setOrder_id(orderId);
        orderFarmer.setDesire_start_time(orderDetail.getDesireTime()/1000);
        orderFarmer.setDesire_num(orderDetail.getDesireNum());
        orderFarmer.setSize(orderDetail.getSize());
        orderFarmer.setUni_price(orderDetail.getUniPrice());
        orderFarmer.setDriver_insurance(orderDetail.getDriverInsurance());
        orderFarmer.setCompany_bonus(orderDetail.getCompanyBonus());
        orderFarmer.setPre_pay_rate(orderDetail.getFarmerPrepayRate());
        if(orderDetail.getTags()!=null) {
            orderFarmer.setTags(JSON.toJSONString(orderDetail.getTags().stream()
            .map(orderTag -> {return  orderTag.getTagName();})
            .toArray()));
        }
        orderBusiness.modifyOrderFarmer(orderFarmer);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
