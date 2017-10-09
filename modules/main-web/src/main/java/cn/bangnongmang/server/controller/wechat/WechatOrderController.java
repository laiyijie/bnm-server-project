package cn.bangnongmang.server.controller.wechat;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.business.common.AccountBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.dao.OrderFarmerWorkSizeMapper;
import cn.bangnongmang.server.business.order.OrderBusiness;
import cn.bangnongmang.server.business.order.OrderCreateBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.wechat.WxMessageConverter;
import cn.bangnongmang.server.io.wechat.data.OrderFarmerInfo;
import cn.bangnongmang.server.io.wechat.data.OrderFarmerWorkSizeInfo;
import cn.bangnongmang.server.io.wechat.show.WxOrderShow;

/**
 * 新的微信订单接口
 *
 * @author laiyijie
 * @ClassName WechatOrderController
 * @date 2017年2月14日 下午5:20:23
 */

@Controller
@RequestMapping("/wx/farmer/order")
public class WechatOrderController {

    @Autowired
    private OrderCreateBusiness orderCreateBusness;

    @Autowired
    private WxOrderShow wxOrderShow;

    @Autowired
    private OrderBusiness orderBusiness;

    @Autowired
    private AccountBusiness accountBusiness;

    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;

    /**
     * 根据regionId 获取当前区域的价格
     *
     * @param regionId 不一定需求，如果没有的话就显示统一的价格为9000
     * @return
     * @throws BusinessException
     * @Title getUniPrice
     */
    @ResponseBody
    @RequestMapping("/getUniPrice")
    public AreaPrice getUniPrice(
            @RequestParam(value = "regionId", required = false) String regionId)
            throws BusinessException {
        return orderCreateBusness.getUniPrice(regionId);
    }

    /**
     * 获取所有的作物和工作类型的列表
     *
     * @return
     * @Title getCropAndWorkTypeList
     */
    @ResponseBody
    @RequestMapping("/getCropAndWorkTypeList")
    public List<OptionWorkingType> getCropAndWorkTypeList() {
        return wxOrderShow.getAllOptionWorkingTypes();
    }

    /**
     * 根据选取的工种ID来获取所有选项
     *
     * @param workingTypeId
     * @return
     * @Title getOptionClusterByWorkingType
     */
    @ResponseBody
    @RequestMapping("/getOptionClusterByWorkingType")
    public List<OptionCluster> getOptionClusterByWorkingType(
            @RequestParam("workingTypeId") Long workingTypeId) {
        return wxOrderShow.getClustersByWorkingTypeId(workingTypeId);
    }

    /**
     * 根据选项的第一个聚类ID获取详细的选项
     *
     * @param clusterId
     * @return
     * @Title getOptionDetailByCluster
     */
    @ResponseBody
    @RequestMapping("/getOptionDetailByCluster")
    public List<OptionDetail> getOptionDetailByCluster(@RequestParam("clusterId") Long clusterId) {
        return wxOrderShow.getOptionDetailByClusterId(clusterId);
    }

    @ResponseBody
    @RequestMapping("/createOrder")
    public OrderFarmer createOrder(@SessionAttribute(SESSION_UID) Long uid,
                                   @RequestParam("regionid") String regionId,
                                   @RequestParam("cropWorkTypeId") Long cropWorkTypeId,
                                   @RequestParam("num") Integer num,
                                   @RequestParam("size") Double size,
                                   @RequestParam("startTime") String startTime,
                                   @RequestParam(value = "invite", required = false) String invite,
                                   @RequestParam(value = "tagsStr", required = false) String tagsStr,
                                   @RequestParam("optionsStr") String optionsStr)
            throws BusinessException {

        List<String> tags = null;
        if (tagsStr == null || "".equals(tagsStr)) {
            tags = null;
        } else {
            tags = JSON.parseArray(tagsStr, String.class);
        }

        Set<Long> options = null;
        if (optionsStr == null || "".equals(optionsStr)) {
            options = null;
        } else {
            options = new HashSet<>(JSON.parseArray(optionsStr, Long.class));
        }

        long startTimeMillis = WxMessageConverter.jsDateToTimeStamp(startTime);

        return orderCreateBusness
                .createOrder(uid, regionId, cropWorkTypeId, num, size, startTimeMillis,
                        accountBusiness.getUserInfoByUsername(invite) == null ? null : accountBusiness.getUserInfoByUsername(invite)
                                                                                                      .getUid(),
                        options, tags);

    }

    /**
     * 获取我的全部订单
     *
     * @param uid
     * @return
     * @Title getAllMyOrders
     */
    @ResponseBody
    @RequestMapping("/getAllMyOrders")
    public List<OrderFarmerInfo> getAllMyOrders(@SessionAttribute(SESSION_UID) Long uid) {
        return wxOrderShow.getAllMyOrders(uid);
    }

    /**
     * 获取等待操作的订单
     *
     * @param uid
     * @return
     * @Title getAllMyActivateOrders
     */
    @ResponseBody
    @RequestMapping("getAllMyActivateOrders")
    public List<OrderFarmerInfo> getAllMyActivateOrders(
            @SessionAttribute(SESSION_UID) Long uid) {
        return wxOrderShow.getAllMyActivateOrders(uid);
    }

    /**
     * 根据订单ID获取订单信息
     *
     * @param uid
     * @param orderId
     * @return
     * @throws BusinessException
     * @Title getOrderInfoById
     */
    @ResponseBody
    @RequestMapping("getOrderInfoById")
    public OrderFarmerInfo getOrderInfoById(@SessionAttribute(SESSION_UID) Long uid,
                                            @RequestParam("orderId") String orderId)
            throws BusinessException {
        Account account = accountBusiness.getUserInfo(uid);
        OrderFarmerInfo orderFarmerInfo = wxOrderShow.getOrderFarmerInfoShowById(orderId);
        if (orderFarmerInfo != null && orderFarmerInfo.getBelongto()
                                                      .equals(account.getUsername())) {
            return orderFarmerInfo;
        } else {
            throw new BusinessException("订单不存在或不属于你");
        }
    }

    /**
     * 农户付保证金
     *
     * @param uid
     * @param orderId
     * @return
     * @throws BusinessException
     * @Title farmerPayForEnsurance
     */
    @ResponseBody
    @RequestMapping("farmerPayForEnsurance")
    public String farmerPayForEnsurance(@SessionAttribute(SESSION_UID) Long uid,
                                        @RequestParam("orderId") String orderId)
            throws BusinessException {

        if (orderBusiness.farmerPrepay(uid, orderId)) {
            return "success";
        }
        throw new BusinessException("操作失败");

    }

    /**
     * 农户确认面积
     *
     * @param uid
     * @param orderId
     * @param size
     * @return
     * @throws BusinessException
     * @Title farmerEnsureSize
     */
    @ResponseBody
    @RequestMapping("farmerEnsureSize")
    public String farmerEnsureSize(@SessionAttribute(SESSION_UID) Long uid,
                                   @RequestParam("orderId") String orderId,
                                   @RequestParam("size") Double size) throws BusinessException {
        if (orderBusiness.farmerEnsureSize(uid, orderId, size)) {
            return "success";
        }
        throw new BusinessException("操作失败");
    }

    /**
     * 继续第二天的作业
     *
     * @param uid
     * @param orderId
     * @return
     * @throws BusinessException
     * @Title cotinueNextWork
     */
    @ResponseBody
    @RequestMapping("continueNextWork")
    public String cotinueNextWork(@SessionAttribute(SESSION_UID) Long uid,
                                  @RequestParam("orderId") String orderId)
            throws BusinessException {
        if (orderBusiness.continueToNextDayWork(uid, orderId)) {
            return "success";
        }
        throw new BusinessException("操作失败");
    }

    /**
     * 结束今日作业
     *
     * @param uid
     * @param orderId
     * @return
     * @throws BusinessException
     * @Title finishOrder
     */
    @ResponseBody
    @RequestMapping("finishOrder")
    public String finishOrder(@SessionAttribute(SESSION_UID) Long uid,
                              @RequestParam("orderId") String orderId) throws BusinessException {
        if (orderBusiness.finishOrder(uid, orderId)) {
            return "success";
        }
        throw new BusinessException("操作失败");
    }

    /**
     * 获取作业量列表
     *
     * @param username
     * @param orderId
     * @return
     * @Title getWorkSizeList
     */
    @ResponseBody
    @RequestMapping("getWorkSizeList")
    public List<OrderFarmerWorkSizeInfo> getWorkSizeList(
            @SessionAttribute(SESSION_UID) Long username,
            @RequestParam("orderId") String orderId) {
        return wxOrderShow.getOrderFarmerWorkSizeInfoByOrderFarmerId(orderId);
    }

    /**
     * 获取未确认的亩数，如果为null则没有，或者状态不对
     *
     * @param uid
     * @param orderId
     * @return
     * @Title getUnensuredWorkSize
     */
    @ResponseBody
    @RequestMapping("getUnensuredWorkSize")
    public OrderFarmerWorkSize getUnensuredWorkSize(@SessionAttribute(SESSION_UID) Long uid,
                                                    @RequestParam("orderId") String orderId) {

        return wxOrderShow.getUnensuredWorkSizeByOrderId(orderId);

    }

}
