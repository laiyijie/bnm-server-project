package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.driverapp.models.*;
import cn.bangnongmang.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.server.business.order.OrderBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidMachineShow;
import cn.bangnongmang.server.io.android.show.AndroidOrderShow;

@RestController
@RequestMapping("/app/order")
public class AppOrderController {

    @Autowired
    private OrderBusiness orderBusiness;

    @Autowired
    private AndroidOrderShow androidOrderShow;

    @Autowired
    private AndroidMachineShow androidMachineShow;

    @Autowired
    private UserService userService;

    /**
     * 抢单
     *
     * @param androidRequest { orderId:String , teamId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title grabOrder
     */

    @ResponseBody
    @RequestMapping("grabOrder")
    public String grabOrder(@RequestBody AndroidRequest androidRequest,
                            @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        Long teamId = (Long) params.get("teamId");

        Boolean flag = orderBusiness.grabOrder(orderId, teamId);

        if (flag) {
            return "success";
        }
        throw new AndroidOutputException("抢单失败");
    }

    /**
     * 集结完毕
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title readyToWork
     */
    @ResponseBody
    @RequestMapping("readyToWork")
    public String readyToWork(@RequestBody AndroidRequest androidRequest,
                              @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        if (orderBusiness.readyToWork(orderId, uid)) {
            return "success";
        }
        throw new AndroidOutputException("操作失败");
    }

    /**
     * 当日作业完成，等待农户确认作业面积
     *
     * @param androidRequest {orderId:String , totalSize:Double}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title finishOneDayWork
     */
    @ResponseBody
    @RequestMapping("finishOneDayWork")
    public String finishOneDayWork(@RequestBody AndroidRequest androidRequest,
                                   @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        if (orderBusiness.finishOneDayWork(orderId, uid)) {
            return "success";
        }
        throw new AndroidOutputException("操作失败");
    }


    /**
     * 上传确认的图片
     *
     * @param androidRequest {orderId:String , imageUrl:String,title: String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title finishOneDayWork
     */
    @ResponseBody
    @RequestMapping("uploadWorkSizeAuthImage")
    public String uploadWorkSizeAuthImage(@RequestBody AndroidRequest androidRequest,
                                          @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        String imageUrl = (String) params.get("imageUrl");
        String title = (String) params.get("title");
        orderBusiness.uploadWorkSizeAuthImage(orderId, imageUrl, title, uid);
        return "success";
    }


    /**
     * 删除确认的图片
     *
     * @param androidRequest {orderId:String , id:Long}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title finishOneDayWork
     */
    @ResponseBody
    @RequestMapping("deleteWorkSizeAuthImage")
    public String deleteWorkSizeAuthImage(@RequestBody AndroidRequest androidRequest,
                                          @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        Long id = Long.valueOf(String.valueOf(params.get("id")));
        orderBusiness.deleteWorkSizeAuthImage(orderId, Arrays.asList(id), uid);
        return "success";

    }

    /**
     * 更新当日作业亩数
     *
     * @param androidRequest {orderId:String , totalSize:Double}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title finishOneDayWork
     */
    @ResponseBody
    @RequestMapping("updateTodayWorkSize")
    public String updateTodayWorkSize(@RequestBody AndroidRequest androidRequest,
                                      @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        Double totalSize = (Double) params.get("totalSize");
        orderBusiness.updateTodayTotalSize(orderId, totalSize, uid);

        return "success";

    }

    @ResponseBody
    @RequestMapping("getWorkSizeAuthImages")
    public List<WorkSizeAuthImage> getWorkSizeAuthImages(@RequestBody AndroidRequest androidRequest,
                                                         @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return androidOrderShow.getOrderWorkSizeImages(orderId);
    }

    /**
     * 队长分配作业量
     *
     * @param androidRequest {orderId:String , workList:List<MemberOneDayWorkSize>}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title distributeWork
     */
    @ResponseBody
    @RequestMapping("distributeWork")
    public String distributeWork(@RequestBody AndroidRequest androidRequest,
                                 @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        List<MemberOneDayWorkSize> everyOneWorkList = JSON.parseArray(JSON.toJSONString(params.get("workList")),
                MemberOneDayWorkSize.class);

        Map<Long, Double> everyOneWorks = new HashMap<>();

        for (MemberOneDayWorkSize memberOneDayWorkSize : everyOneWorkList) {
            Account account = userService.getUserInfoByUsername(memberOneDayWorkSize.getUsername());
            everyOneWorks.put(account.getUid(), memberOneDayWorkSize.getSize());
        }

        if (orderBusiness.distributeWork(orderId, everyOneWorks, uid)) {
            return "success";
        }

        throw new AndroidOutputException("操作失败");
    }

    /**
     * 开始记亩
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title startWork
     */
    @ResponseBody
    @RequestMapping("startWork")
    public String startWork(@RequestBody AndroidRequest androidRequest,
                            @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        orderBusiness.startWork(orderId, uid, androidMachineShow.getUserAvailableMachineWidth(orderId, uid));
        return "success";
    }

    /**
     * 结束记亩
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title finishWork
     */
    @ResponseBody
    @RequestMapping("finishWork")
    public String finishWork(@RequestBody AndroidRequest androidRequest,
                             @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        orderBusiness.stopWork(orderId, uid);

        return "success";
    }

    /**
     * 检查订单是不是我抢到的订单
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title checkWhetherMyOrder
     */
    @ResponseBody
    @RequestMapping("checkWhetherMyOrder")
    public Boolean checkWhetherMyOrder(@RequestBody AndroidRequest androidRequest,
                                       @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return androidOrderShow.checkWhetherIGotTheOrder(orderId, uid);
    }

    /**
     * 获取我的订单详情
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title getMyOrderInfoDetail
     */
    @ResponseBody
    @RequestMapping("getMyOrderInfoDetail")
    public MyOrderInfoDetail getMyOrderInfoDetail(@RequestBody AndroidRequest androidRequest,
                                                  @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return androidOrderShow.getMyOrderInfoDetailByOrderId(orderId);
    }

    /**
     * 获取所有我作业过的订单
     *
     * @param uid
     * @return
     * @Title getMyOrderList
     */
    @ResponseBody
    @RequestMapping("getMyOrderList")
    public List<MyOrderInfoDetail> getMyOrderList(@SessionAttribute(SESSION_UID) Long uid) {

        return androidOrderShow.showMyGotOrderInfoSimple(uid);
    }

    /**
     * 获取详细的工作信息
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title getWorkInfo
     */

    @ResponseBody
    @RequestMapping("getWorkInfo")
    public List<OrderFarmerWorkSizeInfo> getWorkInfo(@RequestBody AndroidRequest androidRequest,
                                                     @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return androidOrderShow.getOrderFarmerWorkSizeInfo(orderId);

    }

    /**
     * 获取自己的子单信息和状态
     *
     * @param androidRequest {orderId:String - orderfarmerid}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title getMySubOrderInfo
     */
    @ResponseBody
    @RequestMapping("getMySubOrderInfo")
    public MySubOrderInfo getMySubOrderInfo(@RequestBody AndroidRequest androidRequest,
                                            @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");
        return androidOrderShow.getMySubOrderInfo(orderId, uid);
    }

    /**
     * 获取所有的队员信息
     *
     * @param androidRequest {orderId: string}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title getAllMemberInOrder
     */
    @ResponseBody
    @RequestMapping("getAllMemberInOrder")
    public List<UserInfoSimple> getAllMemberInOrder(@RequestBody AndroidRequest androidRequest,
                                                    @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return androidOrderShow.getMemberInOrder(orderId);
    }

    /**
     * 检查是否有人还在作业
     *
     * @param androidRequest {orderId:string}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title isAnyStillWoking
     */
    @ResponseBody
    @RequestMapping("isNoOneWorking")
    public boolean isNoOneWorking(@RequestBody AndroidRequest androidRequest,
                                  @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        if (!orderBusiness.isAnyStillWoking(orderId)) {
            throw new AndroidOutputException("还有队员正在作业，无法结束");
        }
        return true;

    }

    @ResponseBody
    @RequestMapping("getMyFavouriteOrderList")
    public List<OrderInfoSimple> getMyFavouriteOrderList(@SessionAttribute(SESSION_UID) Long uid) {

        return androidOrderShow.getMyFavoriteOrderList(uid);
    }


}
