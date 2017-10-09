package cn.bangnongmang.server.notify;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.FollowOrderMapper;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.FollowOrderCriteria;
import cn.bangnongmang.data.domain.FollowOrderKey;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderDriverCriteria;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.server.notify.bo.HookOrderInfoParams;

@Aspect
@Component
@Order(2)
public class OrderBusinessNotify {

    private NotifyUtil notifyUtil;

    @Autowired
    private OrderDriverMapper orderDriverMapper;
    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private FollowOrderMapper followOrderMapper;

    public static final String ORDER_GRAB_SUCCESS = "ORDER_GRAB_SUCCESS";

    public static final String ORDER_GRAB_BY_OTHER = "ORDER_GRAB_BY_OTHER";

    public static final String FOLLOW_ORDER_BE_GRABED = "FOLLOW_ORDER_BE_GRABED";

    public static final String ORDER_DRIVER_READY_TO_WORK_TO_MEMBER = "ORDER_DRIVER_READY_TO_WORK_TO_MEMBER";

    public static final String ORDER_DRIVER_READY_TO_WORK_TO_FARMER = "ORDER_DRIVER_READY_TO_WORK_TO_FARMER";

    public static final String ORDER_DRIVER_FINISH_ONE_DAY_WORK_TO_FARMER = "ORDER_DRIVER_FINISH_ONE_DAY_WORK_TO_FARMER";

    public static final String ORDER_DRIVER_FINISH_DISTRIBUTE_TO_MEMBER = "ORDER_DRIVER_FINISH_DISTRIBUTE_TO_MEMBER";

    public static final String ORDER_DRIVER_FINISH_DISTRIBUTE_TO_FARMER = "ORDER_DRIVER_FINISH_DISTRIBUTE_TO_FARMER";

    public static final String ORDER_FARMER_FINISH_ENSURE_SIZE_TO_LEADER = "ORDER_FARMER_FINISH_ENSURE_SIZE_TO_LEADER";

    public static final String ORDER_FARMER_CONTINUE_TO_WORK_TO_MEMBER = "ORDER_FARMER_CONTINUE_TO_WORK_TO_MEMBER";

    public static final String ORDER_FARMER_FINISH_ORDER_TO_MEMBER = "ORDER_FARMER_FINISH_ORDER_TO_MEMBER";

    public static final String ORDER_FARMER_CREATE_ORDER_SUCCESS = "ORDER_FARMER_CREATE_ORDER_SUCCESS";

    @Autowired
    public OrderBusinessNotify(NotifyUtil notifyUtil) {
        this.notifyUtil = notifyUtil;

        HookOrderInfoParams params = new HookOrderInfoParams();
        params.setCity("盐城");
        params.setDetail("黄海农场");
        params.setCounty("xx县");
        params.setFarmerName("黄鑫河");
        params.setFieldSize("500.0");
        params.setLeaderName("贺云飞");
        params.setOrderId("2016050211301150216");
        params.setProvince("江苏省");
        params.setStartTime("2016-05-02");
        params.setTown("xx镇");
        params.setUniPrice("90.0");

        notifyUtil.registerHook(ORDER_GRAB_SUCCESS, "订单 - 机手抢单成功", params);
        notifyUtil.registerHook(ORDER_GRAB_BY_OTHER, "订单 - 已被别的队伍抢，所在的队伍已解散", params);
        notifyUtil.registerHook(FOLLOW_ORDER_BE_GRABED, "订单 - 关注的订单已被抢", params);

        notifyUtil.registerHook(ORDER_DRIVER_READY_TO_WORK_TO_MEMBER, "订单 - 队长点击准备就绪 - 给机手的通知", params);
        notifyUtil.registerHook(ORDER_DRIVER_READY_TO_WORK_TO_FARMER, "订单 - 队长点击准备就绪 - 给农户的通知", params);
        notifyUtil.registerHook(ORDER_DRIVER_FINISH_ONE_DAY_WORK_TO_FARMER, "订单 - 完成今日作业 - 给农户的通知", params);
        notifyUtil.registerHook(ORDER_DRIVER_FINISH_DISTRIBUTE_TO_MEMBER, "订单 - 完成分配亩数 - 给机手的通知", params);
        notifyUtil.registerHook(ORDER_DRIVER_FINISH_DISTRIBUTE_TO_FARMER, "订单 - 完成分配亩数 - 给农户的通知", params);
        notifyUtil.registerHook(ORDER_FARMER_FINISH_ENSURE_SIZE_TO_LEADER, "订单 - 农户确认今日作业亩数完毕 - 给队长通知", params);
        notifyUtil.registerHook(ORDER_FARMER_CONTINUE_TO_WORK_TO_MEMBER, "订单 - 农户要求继续第二天作业 - 给机手通知", params);
        notifyUtil.registerHook(ORDER_FARMER_FINISH_ORDER_TO_MEMBER, "订单 - 农户结束整个订单 - 给机手通知", params);
        notifyUtil.registerHook(ORDER_FARMER_CREATE_ORDER_SUCCESS, "订单 - 农户创建订单成功后通知 - 给农户", params);
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.farmerPrepay(..)) && args(uid,orderId)")
    public void farmerPrepay(Long uid, String orderId) {

    }

    @Around("execution(* cn.bangnongmang.server.business.order.OrderBusiness.grabOrder(..))")
    public Boolean grabOrder(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        if (args.length < 2) {
            return false;
        }
        String orderId = (String) args[0];
        List<TeamInfoShow> teamInfoShows = teamInfoShowMapper.selectTeamInfoShowByOrderId(orderId);

        FollowOrderCriteria followOrderCriteria = new FollowOrderCriteria();
        followOrderCriteria.or()
                           .andOrder_idEqualTo(orderId);
        List<FollowOrderKey> followOrderKeys = followOrderMapper.selectByExample(followOrderCriteria);

        Boolean result = (Boolean) pjp.proceed();

        if (!result) {
            return result;
        }

        // 发送通知给已经抢到单的人
        HookOrderInfoParams params = createHookOrderInfoParams(orderId);
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        Set<Long> driverUsernamesThatGotTheOrder = orderDrivers.stream()
                                                               .map(a -> a.getUid())
                                                               .collect(Collectors.toSet());
        driverUsernamesThatGotTheOrder.forEach(driverUsername -> notifyUtil.sendNotifyBusinessMessage(driverUsername, ORDER_GRAB_SUCCESS, params));

        // 发送通知给还在队伍中但是没有抢到订单的人
        Set<Long> allOtherTeamers = new HashSet<>();
        allOtherTeamers.addAll(
                teamInfoShows.stream()
                             .map(a -> a.getLeader()
                                        .getAccount()
                                        .getUid())
                             .collect(Collectors.toSet()));
        teamInfoShows.forEach(item -> allOtherTeamers.addAll(item.getRequesters()
                                                                 .stream()
                                                                 .map(a -> a.getTeamJoinRequest()
                                                                            .getUid())
                                                                 .collect(Collectors.toSet())));
        allOtherTeamers.removeAll(driverUsernamesThatGotTheOrder);
        allOtherTeamers.forEach(item -> notifyUtil.sendNotifyBusinessMessage(item, ORDER_GRAB_BY_OTHER, params));

        // 发送通知给关注了订单但是没有抢到单的人
        Set<Long> driverUsernamesThatFollowTheOrder = followOrderKeys.stream()
                                                                     .map(a -> a.getUid())
                                                                     .collect(Collectors.toSet());
        driverUsernamesThatFollowTheOrder.removeAll(driverUsernamesThatGotTheOrder);
        driverUsernamesThatFollowTheOrder.forEach(username -> notifyUtil.sendNotifyBusinessMessage(username, FOLLOW_ORDER_BE_GRABED, params));

        return result;
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.readyToWork(..)) && args(orderId,..)")
    public void readyToWork(String orderId) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderId);
        orderDrivers.forEach(item -> notifyUtil.sendNotifyBusinessMessage(item.getUid(),
                ORDER_DRIVER_READY_TO_WORK_TO_MEMBER, hookOrderInfoParams));

        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        notifyUtil.sendNotifyBusinessMessage(orderFarmer.getUid(), ORDER_DRIVER_READY_TO_WORK_TO_FARMER,
                hookOrderInfoParams);
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.finishOneDayWork(..)) && args(orderId,totalSize,..)")
    public void finishOneDayWork(String orderId, Double totalSize) {
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderId);
        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        notifyUtil.sendNotifyBusinessMessage(orderFarmer.getUid(), ORDER_DRIVER_FINISH_ONE_DAY_WORK_TO_FARMER,
                hookOrderInfoParams);
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.distributeWork(..)) && args(orderId,..)")
    public void distributeWork(String orderId) {
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderId);
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        orderDrivers.forEach(item -> notifyUtil.sendNotifyBusinessMessage(item.getUid(),
                ORDER_DRIVER_FINISH_DISTRIBUTE_TO_MEMBER, hookOrderInfoParams));

        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        notifyUtil.sendNotifyBusinessMessage(orderFarmer.getUid(), ORDER_DRIVER_FINISH_DISTRIBUTE_TO_FARMER,
                hookOrderInfoParams);

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.farmerEnsureSize(..)) && args(username,orderId,..)")
    public void farmerEnsureSize(String username, String orderId) {
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderId);
        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        notifyUtil.sendNotifyBusinessMessage(orderFarmer.getDriver_leader(), ORDER_FARMER_FINISH_ENSURE_SIZE_TO_LEADER,
                hookOrderInfoParams);
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.continueToNextDayWork(..)) && args(uid,orderId,..)")
    public void continueToNextDayWork(Long uid, String orderId) {
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderId);
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        orderDrivers.forEach(item -> notifyUtil.sendNotifyBusinessMessage(item.getUid(),
                ORDER_FARMER_CONTINUE_TO_WORK_TO_MEMBER, hookOrderInfoParams));
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.order.OrderBusiness.finishOrder(..)) && args(uid,orderId,..)")
    public void finishOrder(Long uid, String orderId) {
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderId);
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        orderDrivers.forEach(item -> notifyUtil.sendNotifyBusinessMessage(item.getUid(),
                ORDER_FARMER_FINISH_ORDER_TO_MEMBER, hookOrderInfoParams));
    }

    @AfterReturning(value = "execution(* cn.bangnongmang.server.business.order.OrderCreateBusiness.createOrder(..)) ", returning = "orderFarmer")
    public void createOrder(OrderFarmer orderFarmer) {
        HookOrderInfoParams hookOrderInfoParams = createHookOrderInfoParams(orderFarmer.getOrder_id());
        notifyUtil.sendNotifyBusinessMessage(orderFarmer.getUid(), ORDER_FARMER_CREATE_ORDER_SUCCESS,
                hookOrderInfoParams);
    }

    private HookOrderInfoParams createHookOrderInfoParams(String orderId) {

        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        if (orderFarmer == null) {
            return null;
        }
        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(orderFarmer.getDriver_leader());

        HookOrderInfoParams params = new HookOrderInfoParams();
        params.setCity(orderFarmer.getCity());
        params.setDetail(orderFarmer.getDetail());
        params.setCounty(orderFarmer.getCounty());
        params.setFarmerName(orderFarmer.getName());
        params.setFieldSize(String.valueOf(orderFarmer.getSize()));
        params.setLeaderName(userBasicInfoShow != null && userBasicInfoShow.getAccountRealNameAuth() != null
                ? userBasicInfoShow.getAccountRealNameAuth()
                                   .getReal_name() : "未认证用户");
        params.setOrderId(orderId);
        params.setProvince(orderFarmer.getProvince());
        params.setStartTime(notifyUtil.convertTimemillisToString(orderFarmer.getDesire_start_time() * 1000));
        params.setTown(orderFarmer.getTown());
        params.setUniPrice(String.valueOf(orderFarmer.getUni_price() / 100.0));
        return params;
    }
}
