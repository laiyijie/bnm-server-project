package cn.bangnongmang.service.middle.order.farmer;

import cn.bangnongmang.service.middle.order.exception.OrderStateException;

import java.util.List;
import java.util.Map;


/**
 * 订单状态控制机
 *
 * @author laiyijie
 * @ClassName OrderFarmerState
 * @date 2017年1月10日 下午7:54:25
 */
public interface OrderFarmerState {

    String getStateValue();

    /**
     * 客服认证订单动作
     *
     * @return
     * @Title authPass
     */
    boolean authPass(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException;

    /**
     * 农户支付完成
     *
     * @return
     * @Title prePay
     */
    boolean prePay(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException;

    /**
     * 农户选择田块
     *
     * @return
     * @Title chooseField
     */
    boolean chooseField(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException;

    /**
     * 组队完成并成功抢到订单
     *
     * @return
     * @Title finish
     */
    boolean grabOrder(OrderFarmerStateManager orderFarmerStateManager, Long teamInOrderId) throws OrderStateException;

    /**
     * 已准备好工作
     *
     * @return
     * @Title startWork
     */
    boolean readyToWork(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException;

    /**
     * 结束当日作业
     *
     * @return
     * @Title finishWork
     */
    boolean finishTodayWork(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException;


    /**
     * 上传本日作业的亩数的凭据
     *
     * @param orderFarmerStateManager
     * @return
     * @throws OrderStateException
     */

    boolean uploadWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, String
            imagePath,String title) throws OrderStateException;

    /**
     * 根据ID删除本日作业的凭据
     *
     * @param orderFarmerStateManager
     * @param IDs
     * @return
     * @throws OrderStateException
     */

    boolean deleteWorkSizeAuthImage(OrderFarmerStateManager orderFarmerStateManager, List<Long> IDs) throws OrderStateException;

    /**
     * 更新今日作业亩数
     *
     * @return
     * @Title finishWork
     */

    boolean updateTodayTotalSize(OrderFarmerStateManager orderFarmerStateManager, Double totalSize) throws OrderStateException;

    /**
     * 确认当日作业完毕
     *
     * @return
     * @Title ensureSize
     */
    boolean ensureTodaySize(OrderFarmerStateManager orderFarmerStateManager, Double todaySize)
            throws OrderStateException;

    /**
     * 结算今日款项
     *
     * @return
     * @Title payTodayMoney
     */
    boolean distributeSize(OrderFarmerStateManager orderFarmerStateManager, Map<Long, Double>
            memberWorkSize) throws OrderStateException;

    /**
     * 继续下一天的作业
     *
     * @return
     * @Title finish
     */
    boolean continueNextDayWork(OrderFarmerStateManager orderFarmerStateManagers) throws OrderStateException;

    /**
     * 农户结束整个订单，不需要再继续作业
     *
     * @return
     * @Title finish
     */
    boolean finishOrder(OrderFarmerStateManager orderFarmerStateManagers) throws OrderStateException;

    /**
     * 取消订单
     *
     * @return
     * @Title cancel
     */
    boolean cancel(OrderFarmerStateManager orderFarmerStateManager) throws OrderStateException;

}
