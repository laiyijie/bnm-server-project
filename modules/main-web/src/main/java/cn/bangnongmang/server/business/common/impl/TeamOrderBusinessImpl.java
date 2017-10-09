package cn.bangnongmang.server.business.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderInvite;
import cn.bangnongmang.data.domain.SeasonOrders;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.server.business.common.TeamOrderBusiness;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.service.middle.order.farmer.state.OrderFarmerStateWaittingGot;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.FollowOrderService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.middle.pocket.OrderInsuranceService;
import cn.bangnongmang.service.service.TeamOrderService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.UserWorkCalendarService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
@Transactional(rollbackFor = {Exception.class})
public class TeamOrderBusinessImpl implements TeamOrderBusiness {

    @Autowired
    private FollowOrderService followOrderService;
    @Autowired
    private TradeFlowService tradeFlowService;

    @Autowired
    private OrderInsuranceService orderInsuranceService;
    @Autowired
    private UserService userService;

    @Autowired
    private TeamOrderService teamOrderService;

    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    @Autowired
    private UserMachineShowMapper userMachineShowMapper;
    @Autowired
    private UserWorkCalendarService userWorkCalendarService;

    @Override
    public boolean followOrder(Long uid, String orderid) throws BusinessException {

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderid);
        if (orderFarmer == null) {
            throw new BusinessException("此订单不存在");
        }

        if (!OrderFarmerStateName.FARMER_WAITTING_GOT.equals(orderFarmer.getState())) {
            throw new BusinessException("当前状态无法进行关注");
        }

        if (followOrderService.followOrder(uid, orderid)) {
            return true;
        }
        throw new BusinessException("无法关注此单");

    }

    @Override
    public boolean unfollowOrder(Long uid, String orderid) throws BusinessException {

        if (followOrderService.unfollowOrder(uid, orderid)) {
            return true;
        }

        throw new BusinessException("无法取消关注此单");
    }

    @Override
    public boolean isUserFollowOrder(Long uid, String orderid) {
        return followOrderService.getFollowOrder(uid, orderid) == null ? false : true;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.REPEATABLE_READ)
    public Long createOrderTeam(String orderid, Long uid, String msg) throws
            BusinessException {

        Account account = userService.getUserInfo(uid);
        OrderInvite orderInvite = orderFarmerService.getOrderInvite(orderid);

        if (orderFarmerService.getOrderFarmerById(orderid).getTel().equals(uid)) {
            throw new BusinessException("机手不能创建订单");
        }

        if (account == null) {
            throw new BusinessException("该用户不存在");
        }

        if (!isLeader(account)) {
            throw new BusinessException("只有队长才可以创建队伍");
        }

        if (orderInvite != null) {
            if (!orderInvite.getUid().equals(uid)) {
                throw new BusinessException("该订单农户已指定队长接单，您无法再创建队伍");
            }
        }

        OrderFarmer farmerOrder = orderFarmerService.getOrderFarmerById(orderid);

        if (farmerOrder == null) {
            throw new BusinessException("订单不存在");
        }
        List<SeasonOrders> seasonOrders = orderFarmerService.getCurrentSeasonOrders();

        if (!seasonOrders.stream()
                .anyMatch(seasonOrder -> seasonOrder.getOrder_id().equals(orderid))) {
            throw new BusinessException("订单还不可抢");
        }

        if (!farmerOrder.getState().equals(OrderFarmerStateWaittingGot.STATE)) {
            throw new BusinessException("订单还不可抢");
        }

        if (isInATeam(uid)) {
            throw new BusinessException("只能创建一个队伍");
        }

        if (!isMachineMatchTheOrder(uid, orderid)) {
            throw new BusinessException("您的车不符合订单要求，无法建队");
        }

        if (!userWorkCalendarService.isTimeAvailable(uid, farmerOrder.getDesire_start_time(),
                farmerOrder
                        .getDesire_start_time() + UserWorkCalendarService.DEFAULT_WORKING_TIME)) {
            throw new BusinessException("此订单的时间与您已接到的订单的时间间隔过短");
        }

        try {
            orderInsuranceService
                    .payOrderInsurance(uid, orderid, farmerOrder.getDriver_insurance());
        } catch (ServiceLayerExeption serviceLayerExeption) {
            throw new BusinessException(serviceLayerExeption.getErrorCode(),
                    serviceLayerExeption.getErrorMessage());
        }

        Long teamid = teamOrderService.createOrderTeam(orderid, uid, msg);

        if (teamid == null) {
            throw new BusinessException("创建队伍失败");
        }

        teamOrderService.deleteRequestByNameAndState(uid, TeamOrderService.REQUEST_WAITTING);
        return teamid;
    }

    @Override
    public Long sendJoinOrderTeamRequest(Long teamid, Long uid, String msg)
            throws BusinessException {
        // TODO 判断是否可以入队
        if (isInATeam(uid)) {
            throw new BusinessException("您已经在一个队伍中");
        }

        TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamid);
        if (teamInOrder == null) {
            throw new BusinessException("队伍不存在");
        }

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(teamInOrder.getOrder_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        if (!orderFarmer.getState().equals(OrderFarmerStateName.FARMER_WAITTING_GOT)) {
            throw new BusinessException("订单已抢完");
        }

        TeamJoinRequest teamJoinRequest = teamOrderService.getTeanJoinRequest(teamid, uid);
        if (teamJoinRequest != null) {
            throw new BusinessException("您已经申请加入该队伍");
        }

        if (!isMachineMatchTheOrder(uid, orderFarmer.getOrder_id())) {
            throw new BusinessException("您的车不符合订单要求，无法建队");
        }

        if (!userWorkCalendarService.isTimeAvailable(uid, orderFarmer.getDesire_start_time(),
                orderFarmer
                        .getDesire_start_time() + UserWorkCalendarService.DEFAULT_WORKING_TIME)) {
            throw new BusinessException("此订单的时间与您已接到的订单的时间间隔过短");
        }
        try {
            orderInsuranceService.payOrderInsurance(uid, orderFarmer.getOrder_id(),
                    orderFarmer.getDriver_insurance());
        } catch (ServiceLayerExeption serviceLayerExeption) {
            throw new BusinessException(serviceLayerExeption.getErrorCode(),
                    serviceLayerExeption.getErrorMessage());
        }
        Long requestId = teamOrderService.createJoinOrderTeamRequest(teamid, uid, msg);

        if (requestId == null) {
            throw new BusinessException("提交申请失败");
        }

        return requestId;
    }

    @Override
    public boolean acceptTeamRequest(Long teamid, Long uid, Long operator)
            throws BusinessException {

        TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamid);
        if (teamInOrder == null) {
            throw new BusinessException("队伍不存在");
        }

        if (!teamInOrder.getUid().equals(operator)) {
            throw new BusinessException("您不是队长无权操作");
        }

        TeamJoinRequest teamJoinRequest = teamOrderService.getTeanJoinRequest(teamid, uid);

        if (teamJoinRequest == null) {
            throw new BusinessException("机手已经取消请求");
        } else if (teamJoinRequest.getState() == TeamOrderService.REQUEST_ACCEPTED) {
            throw new BusinessException("该请求已被接受");
        } else if (teamJoinRequest.getState() != TeamOrderService.REQUEST_WAITTING) {
            throw new BusinessException("接受入队请求失败");
        }

        if (isInATeam(uid)) {
            throw new BusinessException("该用户已经加入其它队伍");
        }

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(teamInOrder.getOrder_id());

        if (orderFarmer == null) {
            throw new BusinessException("该订单不存在");
        }
        if (!orderFarmer.getState().equals(OrderFarmerStateName.FARMER_WAITTING_GOT)) {
            throw new BusinessException("订单已抢完");
        }

        if (orderFarmer.getDesire_num() <= teamOrderService
                .countTeamJoinRequestsByTeamIdAndState(teamid,
                        TeamOrderService.REQUEST_ACCEPTED) + 1) {
            throw new BusinessException("当前人数已够，组人失败");
        }

        Boolean result = teamOrderService.changeTeamRequestStateById(teamJoinRequest.getId(),
                TeamOrderService.REQUEST_ACCEPTED);

        if (result == null || !result) {
            throw new BusinessException("接受入队请求失败");
        }

        result = teamOrderService
                .deleteRequestByNameAndState(uid, TeamOrderService.REQUEST_WAITTING);

        if (result == null || !result) {
            throw new BusinessException("接受入队请求失败");
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    @Override
    public boolean denyTeamRequest(Long teamid, Long uid, Long operator)
            throws BusinessException {

        TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamid);
        if (teamInOrder == null) {
            throw new BusinessException("队伍不存在");
        }

        if (!teamInOrder.getUid().equals(operator)) {
            throw new BusinessException("您不是队长无权操作");
        }

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(teamInOrder.getOrder_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        if (!orderFarmer.getState().equals(OrderFarmerStateName.FARMER_WAITTING_GOT)) {
            throw new BusinessException("订单已抢完");
        }

        TeamJoinRequest teamJoinRequest = teamOrderService.getTeanJoinRequest(teamid, uid);

        if (teamJoinRequest == null) {
            throw new BusinessException("机手已经取消请求");
        } else if (teamJoinRequest.getState() == TeamOrderService.REQUEST_ACCEPTED) {
            throw new BusinessException("该请求已被接受");
        }

        Boolean result = teamOrderService.deleteTeamRequestById(teamJoinRequest.getId());

        if (result == null || !result) {
            throw new BusinessException("拒绝入队请求失败");
        }
        if (isLeaveAllTheTeamInThisOrder(uid, orderFarmer.getOrder_id())) {
            try {
                orderInsuranceService.refundInsurance(uid, orderFarmer.getOrder_id());
            } catch (ServiceLayerExeption serviceLayerExeption) {
                throw new BusinessException(serviceLayerExeption.getErrorCode(),
                        serviceLayerExeption.getErrorMessage());
            }
        }
        return true;
    }

    private boolean isLeaveAllTheTeamInThisOrder(Long username, String orderId) {
        List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrderByOrderId(orderId);
        if (teamInOrders.stream().anyMatch(a -> a.getUid().equals(username))) {
            return false;
        }
        List<TeamJoinRequest> teamJoinRequests = teamOrderService
                .getTeamJoinRequestsByUsername(username);
        Long count = teamJoinRequests.stream()
                .filter(tjr -> teamInOrders.stream()
                        .anyMatch(tio -> tio.getId().equals(tjr.getTeam_id()))).count();
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean cancelTeamRequest(Long teamid, Long uid) throws BusinessException {

        TeamJoinRequest teamJoinRequest = teamOrderService.getTeanJoinRequest(teamid, uid);
        TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamid);
        if (teamInOrder == null) {
            throw new BusinessException("队伍不存在");
        }
        if (teamJoinRequest == null) {
            throw new BusinessException("您的请求已经取消或被拒绝");
        }
        if (TeamOrderService.REQUEST_WAITTING != teamJoinRequest.getState()){
            throw new BusinessException("入队请求状态不一致，请刷新后重试");
        }
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(teamInOrder.getOrder_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        Boolean result = teamOrderService.deleteTeamRequestById(teamJoinRequest.getId());

        if (result == null || !result) {
            throw new BusinessException("取消入队请求失败");
        }
        if (isLeaveAllTheTeamInThisOrder(uid, orderFarmer.getOrder_id())) {
            try {
                orderInsuranceService.refundInsurance(uid, orderFarmer.getOrder_id());
            } catch (ServiceLayerExeption serviceLayerExeption) {
                throw new BusinessException(serviceLayerExeption.getErrorCode(),
                        serviceLayerExeption.getErrorMessage());
            }
        }
        return true;
    }

    @Override
    public boolean deleteTeamRequest(Long teamid, Long uid, String ps, Long operator)
            throws BusinessException {

        TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamid);
        if (teamInOrder == null) {
            throw new BusinessException("队伍不存在");
        }

        if (!teamInOrder.getUid().equals(operator)) {
            throw new BusinessException("您不是队长无权操作");
        }
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(teamInOrder.getOrder_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        if (!orderFarmer.getState().equals(OrderFarmerStateName.FARMER_WAITTING_GOT)) {
            throw new BusinessException("订单已抢完");
        }
        TeamJoinRequest teamJoinRequest = teamOrderService.getTeanJoinRequest(teamid, uid);

        if (teamJoinRequest == null) {
            throw new BusinessException("删除入队请求失败");
        }

        Boolean result = teamOrderService.deleteTeamRequestById(teamJoinRequest.getId());

        if (result == null || !result) {
            throw new BusinessException("删除入队请求失败");
        }
        if (isLeaveAllTheTeamInThisOrder(uid, orderFarmer.getOrder_id())) {
            try {
                orderInsuranceService.refundInsurance(uid, orderFarmer.getOrder_id());
            } catch (ServiceLayerExeption serviceLayerExeption) {
                throw new BusinessException(serviceLayerExeption.getErrorCode(),
                        serviceLayerExeption.getErrorMessage());
            }
        }
        return true;

    }

    @Override
    public boolean deleteOrderTeam(Long teamId, Long operator) throws BusinessException {

        TeamInOrder teamInOrder = teamOrderService.getTeamInOrderByTeamId(teamId);
        if (teamInOrder == null) {
            throw new BusinessException("无此队伍");
        }

        if (!teamInOrder.getUid().equals(operator)) {
            throw new BusinessException("不是队长无权操作");
        }

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(teamInOrder.getOrder_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        if (!orderFarmer.getState().equals(OrderFarmerStateName.FARMER_WAITTING_GOT)) {
            throw new BusinessException("订单已抢完");
        }
        List<Long> allTheMember = new ArrayList<>();
        allTheMember.add(operator);
        List<TeamJoinRequest> teamJoinRequests = teamOrderService
                .getTeamJoinRequestsByTeamId(teamId);
        allTheMember.addAll(teamJoinRequests.stream().map(a -> a.getUid())
                .collect(Collectors.toList()));
        if (teamOrderService.deleteTeamRequestByTeamId(teamId).equals(true)
                && teamOrderService.deleteTeamByTeamId(teamId).equals(true)) {
            for (Long memberUid : allTheMember) {
                if (isLeaveAllTheTeamInThisOrder(memberUid, orderFarmer.getOrder_id())) {
                    try {
                        orderInsuranceService
                                .refundInsurance(memberUid, orderFarmer.getOrder_id());
                    } catch (ServiceLayerExeption serviceLayerExeption) {
                        throw new BusinessException(serviceLayerExeption.getErrorCode(),
                                serviceLayerExeption.getErrorMessage());
                    }
                }
            }
            return true;
        }

        throw new BusinessException("删除失败");
    }

    private boolean isInATeam(Long uid) {
        List<TeamInfoShow> teamInfoShows = teamInfoShowMapper.selectTeamInfoShowByLeader(uid);
        teamInfoShows.addAll(
                teamInfoShowMapper.selectTeamInfoShowByRequesterAndState(uid,
                        TeamOrderService.REQUEST_ACCEPTED));
        if (teamInfoShows.stream().anyMatch(a -> OrderFarmerStateName.FARMER_WAITTING_GOT
                .equals(a.getOrderFarmerInfoShow().getOrderFarmer().getState()))) {
            return true;
        }
        return false;
    }

    private boolean isLeader(Account account) {
        if (UserService.DRIVER_LEADER_AUTHED.equals(account.getDriver_leader())) {
            return true;
        }
        return false;
    }

    // 判断一个人的机器是否满足抢单的要求；
    // 1、机器的要关联指定的workingtype
    // 2.机器的option要满足order的option
    private boolean isMachineMatchTheOrder(Long uid, String orderId) {
        OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByOrderId(orderId);
        List<UserMachineShow> userMachineShows = userMachineShowMapper.selectByUid(uid);
        if (orderFarmerInfoShow == null)
            return false;
        if (userMachineShows.isEmpty())
            return false;

        List<UserMachineShow> workingTypeCorrectMachines = userMachineShows.stream()
                .filter(um -> um.getOptionWorkingTypes().stream()
                        .anyMatch(owt -> owt.getId()
                                .equals(orderFarmerInfoShow.getOptionWorkingType().getId())))
                .collect(Collectors.toList());

        if (orderFarmerInfoShow.getOptions().isEmpty() && !workingTypeCorrectMachines.isEmpty()) {
            return true;
        }

        Map<Long, Set<Long>> orderClusterIdDetailIdSetMap = new HashMap<>();
        for (OptionDetail optionDetail : orderFarmerInfoShow.getOptions().stream()
                .map(option -> option.getOptionDetail()).collect(Collectors.toList())) {
            if (!orderClusterIdDetailIdSetMap.containsKey(optionDetail.getCluster_id())) {
                orderClusterIdDetailIdSetMap.put(optionDetail.getCluster_id(), new HashSet<>());
            }
            Set<Long> optionDetailSet = orderClusterIdDetailIdSetMap
                    .get(optionDetail.getCluster_id());
            optionDetailSet.add(optionDetail.getId());
        }
        for (UserMachineShow machine : workingTypeCorrectMachines) {
            Set<Long> machineOptionDetailIdSet = machine.getOptions().stream()
                    .map(op -> op.getOptionDetail().getId())
                    .collect(Collectors.toSet());
            boolean isThisMachineOk = true;

            for (Map.Entry<Long, Set<Long>> entry : orderClusterIdDetailIdSetMap.entrySet()) {
                Set<Long> temp = new HashSet<>();
                temp.addAll(entry.getValue());
                temp.retainAll(machineOptionDetailIdSet);
                if (temp.isEmpty()) {
                    isThisMachineOk = false;
                    break;
                }
            }
            if (isThisMachineOk)
                return true;
        }
        return false;
    }

}
