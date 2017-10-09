package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.server.business.common.TeamOrderBusiness;
import cn.bangnongmang.server.business.order.OrderBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.swagger.show.OrderShow;
import cn.bangnongmang.server.io.swagger.show.TeamShow;
import cn.bangnongmang.server.swagger.api.OrdersApi;
import cn.bangnongmang.server.swagger.model.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017-04-17.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class OrdersController implements OrdersApi {
    @Autowired
    private OrderShow orderShow;
    @Autowired
    private TeamShow teamShow;
    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private TeamOrderBusiness teamOrderBusiness;

    @Override
    public ResponseEntity<List<Order>> ordersGet(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<Order>>(orderShow.getShowOrders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> ordersOrderIdGet(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Order order = orderShow.getOrderByOrderId(orderId);
        if (order == null) return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserSimple>> ordersOrderIdMembersGet(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<UserSimple>>(orderShow.getMembersInOrder(orderId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderMultiInfo>> ordersOrderIdMultiInfosGet(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(orderShow.getOrderMultiInfoByOrderId(orderId));
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdStatusDistributeWorkPost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "每个队员的作业面积分配") @Valid @RequestBody List<MemberDayWorkInfo> totalSize,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Long, Double> maps = new HashMap<>();
        totalSize.forEach(memberDayWorkInfo -> maps
                .put(memberDayWorkInfo.getUid(), memberDayWorkInfo.getSize()));
        orderBusiness.distributeWork(orderId, maps, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdStatusFinishOneDayWorkPost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (orderBusiness.finishOneDayWork(orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID))) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("结束本日作业失败");
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdStatusGotPost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "抢单的队伍", required = true) @RequestParam(value = "teamId", required = true) Long teamId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Boolean flag = orderBusiness.grabOrder(orderId, teamId);

        if (flag) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("抢单失败");
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdStatusReadyToWorkPost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (orderBusiness.readyToWork(orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID))) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("操作失败");
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdStatusUpdateTodayTotalSizePost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "本日作业面积", required = true) @RequestParam(value = "totalSize", required = true) Double totalSize,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        orderBusiness.updateTodayTotalSize(orderId,totalSize,BnmSwaggerControllerUtil
                .getSessionForLong(request,ServerSessionAttr.SESSION_UID));

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<SubOrder>> ordersOrderIdSubOrdersGet(
            @ApiParam(value = "订单的Id", required = true) @PathVariable("orderId") String orderId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(orderShow.getSubOrderByOrderId(orderId));
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdSubOrdersStatusNoOneWorkingGet(
            @ApiParam(value = "订单的Id", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!orderBusiness.isAnyStillWoking(orderId)) {
            throw new BusinessException("还有队员正在作业，无法结束");
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Team>> ordersOrderIdTeamsGet(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<Team>>(orderShow.getTeamsInOrder(orderId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Team> ordersOrderIdTeamsPost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @RequestParam("message") String message,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long teamId = teamOrderBusiness.createOrderTeam(orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID),
                message);
        Team team = teamShow.getTeamById(teamId);
        if (team == null) return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderFarmerWorkInfo>> ordersOrderIdWorkSizesGet(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<OrderFarmerWorkInfo>>(orderShow.getOrderFarmerWorkInfos
                (orderId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<WorkSizeAuthImage>> ordersOrderIdWorkSizesWorkSizeIdAuthImagesGet(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "orderFarmerWorkInfoId", required = true) @PathVariable("workSizeId") Long workSizeId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(orderShow.getWorkSizeAuthImageByWorkSizeId(workSizeId));
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdWorkSizesWorkSizeIdAuthImagesImageIdDelete(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "orderFarmerWorkInfoId", required = true) @PathVariable("workSizeId") Long workSizeId,
            @ApiParam(value = "imageId", required = true) @PathVariable("imageId") Long imageId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {


        orderBusiness.deleteWorkSizeAuthImage(orderId, Arrays.asList(imageId), BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr
                .SESSION_UID));
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdWorkSizesWorkSizeIdAuthImagesPost(
            @ApiParam(value = "订单的ID", required = true) @PathVariable("orderId") String orderId,
            @ApiParam(value = "orderFarmerWorkInfoId", required = true) @PathVariable("workSizeId") Long workSizeId,
            @ApiParam(value = "name of this images", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "the image url", required = true) @RequestParam(value = "imageUrl", required = true) String imageUrl,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        orderBusiness.uploadWorkSizeAuthImage(orderId, imageUrl, title,
                BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return ResponseEntity.ok(null);
    }
}
