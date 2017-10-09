package cn.bangnongmang.server.business.order;

import java.util.List;
import java.util.Map;

import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.service.middle.order.exception.OrderStateException;
import cn.bangnongmang.service.middle.order.exception.OrderStateInstanceNotFindException;
import cn.bangnongmang.service.middle.order.exception.OrderStateTransitionNotExsistException;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManager;
import cn.bangnongmang.service.middle.order.farmer.OrderFarmerStateManagerFactory;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.size.io.swagger.model.SizeCounterSetting;
import cn.bangnongmang.size.io.swagger.model.WorkingOrderStatus;
import cn.bangnongmang.size.io.swagger.model.WorkingSizeHistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Service("orderBusiness")
@Transactional(rollbackFor = {Exception.class})
public class OrderBusiness {

    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private OrderFarmerStateManagerFactory farmerStateManagerFactory;

    @Autowired
    private SizeCounterService sizeCounterService;

    @Autowired
    private UserMachineService userMachineService;

    @Autowired
    private WorkSizeService workSizeService;

    @Autowired
    private AliOssService aliOssService;

    @Autowired
    private UserService userService;

    /**
     * 农户预付定金
     *
     * @param uid
     * @param orderId
     * @throws BusinessException
     * @Title farmerPrepay
     */
    public Boolean farmerPrepay(Long uid, String orderId) throws BusinessException {

        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getUid()
                        .equals(uid)) {
            throw new BusinessException("该订单不属于你");
        }
        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.prePay();
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;
    }

    /**
     * 组队成功后的抢单逻辑
     *
     * @param orderId
     * @param teamId
     * @return
     * @throws BusinessException
     * @Title grabOrder
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    public Boolean grabOrder(String orderId, Long teamId) throws BusinessException {

        orderFarmerService.updateLeader(orderId, 0L);// 为了获得独占锁

        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("订单已被抢走");
        }

        List<SeasonOrders> seasonOrders = orderFarmerService.getCurrentSeasonOrders();

        if (!seasonOrders.stream()
                         .anyMatch(seasonOrder -> seasonOrder.getOrder_id()
                                                             .equals(orderId))) {
            throw new BusinessException("订单还不可抢");
        }

        try {
            orderFarmerStateManager.grabOrder(teamId);
        } catch (OrderStateException e) {
            if (e instanceof OrderStateTransitionNotExsistException) {
                throw new BusinessException("订单已被抢走");
            }
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;
    }

    /**
     * 获取我正在工作的订单
     *
     * @param uid
     * @return
     * @Title getMyWorkingOrder
     */

    public OrderFarmer getMyWorkingOrder(Long uid) {

        List<OrderDriver> orderDrivers = orderDriverService
                .getOrderDriverByUsernameAndStates(uid,
                        OrderDriverService.ORDER_DRIVER_STATE_GOT,
                        OrderDriverService.ORDER_DRIVER_STATE_WORKING,
                        OrderDriverService.ORDER_DRIVER_STATE_STOPPED);

        if (orderDrivers.isEmpty()) {
            return null;
        }

        for (OrderDriver orderDriver : orderDrivers) {
            OrderFarmer orderFarmer = orderFarmerService
                    .getOrderFarmerById(orderDriver.getOrder_farmer_id());
            if (orderFarmer.getState()
                           .equals(OrderFarmerStateName.FARMER_WORKING)) {
                return orderFarmer;
            }
        }
        return orderFarmerService.getOrderFarmerById(orderDrivers.get(0)
                                                                 .getOrder_farmer_id());
    }

    /**
     * 准备就绪
     *
     * @param orderId
     * @return
     * @throws BusinessException
     * @Title readyToWork
     */
    public boolean readyToWork(String orderId, Long uid) throws BusinessException {

        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getDriver_leader()
                        .equals(uid)) {
            throw new BusinessException("你不是队长无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.readyToWork();
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;

    }

    /**
     * 结束今日工作
     *
     * @param orderId
     * @param uid
     * @return
     * @throws BusinessException
     * @Title finishOneDayWork
     */
    public boolean finishOneDayWork(String orderId, Long uid) throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getDriver_leader()
                        .equals(uid)) {
            throw new BusinessException("你不是队长无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.finishTodayWork();
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }

        OrderFarmerWorkSize orderFarmerWorkSize = workSizeService
                .getOrderFarmerWorkSizeByOrderIdAndState(orderId,
                        WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE);

        sizeCounterService.getDefaultApi()
                          .workingOrderidOuterindexStatusPut(orderfarmer.getOrder_id(),
                                  String.valueOf(orderFarmerWorkSize.getId()), WorkingOrderStatus.STOP)
                          .enqueue(new Callback<List<WorkingSizeHistory>>() {

                              @Override
                              public void onResponse(Call<List<WorkingSizeHistory>> call,
                                                     Response<List<WorkingSizeHistory>> response) {
                              }

                              @Override
                              public void onFailure(Call<List<WorkingSizeHistory>> call, Throwable t) {
                                  t.printStackTrace();
                              }
                          });
        return true;
    }


    public void uploadWorkSizeAuthImage(String orderId, String imagePath, String title,
                                        Long uid) throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getDriver_leader()
                        .equals(uid)) {
            throw new BusinessException("你不是队长无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.uploadWorkSizeAuthImage(orderFarmerStateManager,
                    aliOssService.getAuthPutUrlFolderByUsername(uid) +
                            imagePath, title);
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    /**
     * 根据ID删除本日作业的凭据
     *
     * @param IDs
     * @return
     * @throws OrderStateException
     */
    public void deleteWorkSizeAuthImage(String orderId, List<Long> IDs, Long uid)
            throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getDriver_leader()
                        .equals(uid)) {
            throw new BusinessException("你不是队长无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.deleteWorkSizeAuthImage(orderFarmerStateManager, IDs);
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    /**
     * 更新今日作业亩数
     *
     * @return
     * @Title finishWork
     */


    public void updateTodayTotalSize(String orderId, Double totalSize, Long uid)
            throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getDriver_leader()
                        .equals(uid)) {
            throw new BusinessException("你不是队长无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.updateTodayTotalSize(orderFarmerStateManager, totalSize);
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    public boolean distributeWork(String orderId, Map<Long, Double> everyOneWorks,
                                  Long uid)
            throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getDriver_leader()
                        .equals(uid)) {
            throw new BusinessException("你不是队长无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.distributeSize(everyOneWorks);
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;
    }

    public boolean farmerChooseField(Long uid, String orderId) throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getUid()
                        .equals(uid)) {
            throw new BusinessException("你无权操作");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.chooseField();
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;
    }

    public boolean farmerEnsureSize(Long uid, String orderId, Double size)
            throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getUid()
                        .equals(uid)) {
            throw new BusinessException("该订单不属于你");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.ensureTodaySize(size);
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;
    }

    public boolean continueToNextDayWork(Long uid, String orderId) throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getUid()
                        .equals(uid)) {
            throw new BusinessException("该订单不属于你");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.continueNextDayWork();
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return true;
    }

    public boolean finishOrder(Long uid, String orderId) throws BusinessException {
        OrderFarmer orderfarmer = orderFarmerService.getOrderFarmerById(orderId);

        if (orderfarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!orderfarmer.getUid()
                        .equals(uid)) {
            throw new BusinessException("该订单不属于你");
        }

        OrderFarmerStateManager orderFarmerStateManager = null;
        try {
            orderFarmerStateManager = farmerStateManagerFactory
                    .createManager(orderfarmer.getState(), orderId);

        } catch (OrderStateInstanceNotFindException e) {
            throw new BusinessException("当前状态不正确");
        }

        try {
            orderFarmerStateManager.finishOrder();
        } catch (OrderStateException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        sizeCounterService.getDefaultApi()
                          .workingOrderidOuterindexStatusPut(orderfarmer.getOrder_id(), "NULL",
                                  WorkingOrderStatus.FINISH)
                          .enqueue(new Callback<List<WorkingSizeHistory>>() {

                              @Override
                              public void onResponse(Call<List<WorkingSizeHistory>> call,
                                                     Response<List<WorkingSizeHistory>> response) {
                              }

                              @Override
                              public void onFailure(Call<List<WorkingSizeHistory>> call, Throwable t) {
                                  t.printStackTrace();
                              }
                          });
        return true;
    }

    public void startWork(String orderFarmerId, Long uid, Double width)
            throws BusinessException {

        OrderDriver orderDriver = orderDriverService
                .getOrderDriverByOrderFarmerIdAndUid(orderFarmerId, uid);

        if (orderDriver == null) {
            throw new BusinessException("您的订单不存在，请联系客服");
        }
        if (orderDriver.getService_start() == null || orderDriver.getService_start() == 0) {
            orderDriverService
                    .updateStartTime(orderDriver.getOrder_id(), System.currentTimeMillis() / 1000);
        }

        if (OrderDriverService.ORDER_DRIVER_STATE_ALL_FINISHED.equals(orderDriver.getState())) {
            throw new BusinessException("订单已结束，不能开始作业");
        }

        OrderFarmer orderFarmer = orderFarmerService
                .getOrderFarmerById(orderDriver.getOrder_farmer_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!OrderFarmerStateName.FARMER_WORKING.equals(orderFarmer.getState())) {
            throw new BusinessException("订单不处于作业状态，无法开始");
        }

        orderDriverService.updateState(orderDriver.getOrder_id(),
                OrderDriverService.ORDER_DRIVER_STATE_WORKING);

        // TODO 那辆车，车宽多少？
        List<UserMachine> userMachines = userMachineService.getAllMyUserMachines(uid);
        Account account = userService.getUserInfo(uid);
        SizeCounterSetting setting = new SizeCounterSetting();
        setting.setUid(account.getUid());
        setting.setWidth(width);
        sizeCounterService.getDefaultApi()
                          .workingOrderidSettingsPost(orderFarmerId, setting)
                          .enqueue(new Callback<Void>() {
                              @Override
                              public void onResponse(Call<Void> call, Response<Void> response) {
                              }

                              @Override
                              public void onFailure(Call<Void> call, Throwable t) {
                              }
                          });
    }

    public void stopWork(String orderFarmerId, Long uid) throws BusinessException {
        OrderDriver orderDriver = orderDriverService
                .getOrderDriverByOrderFarmerIdAndUid(orderFarmerId, uid);
        if (orderDriver == null) {
            throw new BusinessException("您的订单不存在，请联系客服");
        }
        if (OrderDriverService.ORDER_DRIVER_STATE_ALL_FINISHED.equals(orderDriver.getState())) {
            throw new BusinessException("订单已结束，不能结束作业");
        }

        OrderFarmer orderFarmer = orderFarmerService
                .getOrderFarmerById(orderDriver.getOrder_farmer_id());
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }

        if (!OrderFarmerStateName.FARMER_WORKING.equals(orderFarmer.getState())) {
            throw new BusinessException("订单不处于作业状态，无法执行此操作");
        }
        orderDriverService
                .updateEndTime(orderDriver.getOrder_id(), System.currentTimeMillis() / 1000);

        orderDriverService.updateState(orderDriver.getOrder_id(),
                OrderDriverService.ORDER_DRIVER_STATE_STOPPED);

    }


    public boolean isAnyStillWoking(String orderid) {
        List<OrderDriver> orderDrivers = orderDriverService
                .getOrderDriverListByOrderFarmerId(orderid);
        for (OrderDriver orderDriver : orderDrivers) {
            if (OrderDriverService.ORDER_DRIVER_STATE_WORKING.equals(orderDriver.getState())) {
                return false;
            }
        }
        return true;
    }

}
