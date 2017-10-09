package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.WorkTeamManageBusiness;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.service.middle.pocket.OrderInsuranceService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.*;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-05-04.
 */
@Service
@Transactional
public class WorkTeamManageBusinessImpl implements WorkTeamManageBusiness, OrderFarmerStateName {

    @Autowired
    private TradeFlowService tradeFlowService;

    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private TeamOrderService teamOrderService;

    @Autowired
    private UserWorkCalendarService userWorkCalendarService;

    @Autowired
    private OrderInsuranceService orderInsuranceService;

    private List<String> CAN_CHANGE_LEADER_STATES = Collections.singletonList(FARMER_GOT);

    @Override
    public void changeLeader(Long newLeaderUid, String orderId) throws BusinessException {
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderId);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        if (CAN_CHANGE_LEADER_STATES.stream()
                                    .noneMatch(state -> state.equals(orderFarmer.getState()))) {
            throw new BusinessException("只有在未开始作业的时候才可以修改队长");
        }
        Long oldLeaderUid = orderFarmer.getDriver_leader();
        List<OrderDriver> orderDrivers = orderDriverService.getOrderDriverListByOrderFarmerId(orderId);
        if (orderDrivers.stream()
                        .noneMatch(orderDriver -> orderDriver.getUid()
                                                             .equals(newLeaderUid))) {
            throw new BusinessException("请先将新队长拉入队伍后再切换队长");
        }
        List<String> orderDriverIds = orderDrivers.stream()
                                                  .filter(orderDriver -> orderDriver.getUid()
                                                                                    .equals(oldLeaderUid))
                                                  .map(OrderDriver::getOrder_id)
                                                  .collect(Collectors.toList());
        if (orderDriverIds.isEmpty()) {
            throw new BusinessException("订单有误，请联系管理员");
        }

        List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrderByOrderId(orderId);
        //修改team中的信息
        if (teamInOrders.size() != 1)
            throw new BusinessException("队伍数量不对");
        Long teamId = teamInOrders.get(0)
                                  .getId();
        List<TeamJoinRequest> members = teamOrderService.getTeamJoinRequestsByTeamIdAndState(teamId, TeamOrderService.REQUEST_ACCEPTED);
        members.stream()
               .filter(member -> member.getUid()
                                       .equals(newLeaderUid))
               .map(TeamJoinRequest::getId)
               .forEach(requestId -> teamOrderService.deleteTeamRequestById(requestId));

        teamOrderService.updateTeamLeader(teamId, newLeaderUid);
        Long newRequestId = teamOrderService.createJoinOrderTeamRequest(teamId, oldLeaderUid, "切换为队员");
        teamOrderService.changeTeamRequestStateById(newRequestId, TeamOrderService.REQUEST_ACCEPTED);
        //更新队长
        orderFarmerService.updateLeader(orderId, newLeaderUid);
    }

    @Override
    public void removeMember(Long uid, String orderId, Integer punishInsurance, Boolean isPunishWaitingIn) throws BusinessException {
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderId);
        List<OrderDriver> orderDrivers = orderDriverService.getOrderDriverListByOrderFarmerId(orderId);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        if (orderDrivers.stream()
                        .noneMatch(orderDriver -> orderDriver.getUid()
                                                             .equals(uid))) {
            throw new BusinessException("该队员不在队伍中");
        }
        try {
            orderInsuranceService.punishInsurance(uid, orderId, punishInsurance);
            orderInsuranceService.refundInsurance(uid, orderId);
            if (isPunishWaitingIn) {
                Integer leftWaitingInMoney = tradeFlowService.countMyUnfreezeOrderWaitInMoneyByOrderId(uid, orderId);
                PocketLog pocketLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_INCOME_PUNISH, leftWaitingInMoney,
                        TradeFlowService.POCKETLOG_METHOD_DEFAULT, "离队扣除作业款", "", orderId);
                tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
            }

        } catch (ServiceLayerExeption serviceLayerExeption) {
            throw new BusinessException(serviceLayerExeption.getErrorCode(), serviceLayerExeption.getErrorMessage());
        }
        orderDrivers.stream()
                    .filter(orderDriver -> orderDriver.getUid()
                                                      .equals(uid))
                    .forEach(orderDriver -> orderDriverService.deleteOrderDriver(orderDriver.getOrder_id()));
        List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrderByOrderId(orderId);
        //修改team中的信息
        if (teamInOrders.size() != 1)
            throw new BusinessException("队伍数量不对");
        Long teamId = teamInOrders.get(0)
                                  .getId();
        List<TeamJoinRequest> teamJoinRequests = teamOrderService.getTeamJoinRequestsByTeamIdAndState(teamId, TeamOrderService.REQUEST_ACCEPTED);
        teamJoinRequests.stream()
                        .filter(teamJoinRequest -> teamJoinRequest.getUid()
                                                                  .equals(uid))
                        .forEach(tjr -> teamOrderService.deleteTeamRequestById
                                (tjr.getId()));
        userWorkCalendarService.deleteCalendarByOrderIdAndUid(orderId,uid);
    }

    @Override
    public void addMember(Long uid, String orderId, Boolean needInsurance) throws BusinessException {
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderId);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        List<OrderDriver> orderDrivers = orderDriverService.getOrderDriverListByOrderFarmerId(orderId);
        if (orderDrivers.isEmpty())
            throw new BusinessException("订单状态不正确");
        if (orderDrivers.stream()
                        .anyMatch(orderDriver -> orderDriver.getUid()
                                                            .equals(uid))) {
            throw new BusinessException("该队员已经在队伍中，不能重复添加");
        }
        try {
            if (needInsurance) {
                orderInsuranceService.payOrderInsurance(uid, orderId, orderFarmer.getDriver_insurance());
            } else {
                orderInsuranceService.payOrderInsurance(uid, orderId, 0);
            }

        } catch (ServiceLayerExeption serviceLayerExeption) {
            throw new BusinessException(serviceLayerExeption.getErrorCode(), serviceLayerExeption.getErrorMessage());
        }

        List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrderByOrderId(orderId);
        //修改team中的信息
        if (teamInOrders.size() != 1)
            throw new BusinessException("队伍数量不对");
        Long teamId = teamInOrders.get(0)
                                  .getId();
        Long newRequestId = teamOrderService.createJoinOrderTeamRequest(teamId, uid, "新增队员");
        teamOrderService.changeTeamRequestStateById(newRequestId, TeamOrderService.REQUEST_ACCEPTED);
        Integer last = 0;
        List<Integer> tails = orderDrivers.stream()
                                          .map(OrderDriver::getOrder_id)
                                          .map(id -> id.substring(id.length() - 4))
                                          .map(Integer::valueOf)
                                          .collect(Collectors.toList());
        for (Integer tail : tails) {
            if (tail > last)
                last = tail;
        }
        last++;
        String tail = "0000" + last;
        OrderDriver orderDriver = new OrderDriver();
        orderDriver.setUid(uid);
        orderDriver.setActual_money(0);
        orderDriver.setActual_size(0.0);
        orderDriver.setOrder_farmer_id(orderId);
        orderDriver.setOrder_id(orderId + tail.substring(tail.length() - 4));
        orderDriver.setState(OrderDriverService.ORDER_DRIVER_STATE_GOT);
        orderDriver.setService_start(0L);
        orderDriver.setService_end(0L);
        orderDriverService.createOrderDriver(orderDriver);
    }
}
