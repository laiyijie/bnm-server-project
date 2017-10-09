package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;
import java.util.Map;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.server.business.common.AccountBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.driverapp.models.FriendDetail;
import cn.bangnongmang.driverapp.models.MyOrderInfoDetail;
import cn.bangnongmang.driverapp.models.OrderInfoDetail;
import cn.bangnongmang.driverapp.models.OrderInfoSimple;
import cn.bangnongmang.driverapp.models.OrderTeam;
import cn.bangnongmang.driverapp.models.OrderTeamSimple;
import cn.bangnongmang.driverapp.models.UserInfoDetail;
import cn.bangnongmang.server.business.common.TeamOrderBusiness;
import cn.bangnongmang.server.business.order.OrderBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidOrderShow;
import cn.bangnongmang.server.io.android.show.AndroidTeamShow;
import cn.bangnongmang.server.io.android.show.AndroidUserShow;
import cn.bangnongmang.server.io.wechat.data.UserMachineInfo;

/**
 * 组队列表，代替原来的抢单界面
 *
 * @author laiyijie
 * @ClassName TeamOrderController
 * @date 2016年12月20日 下午5:33:54
 */

@Controller
@RequestMapping("/team")
public class TeamOrderController {

    @Autowired
    private TeamOrderBusiness driverTeamOrderBusiness;

    @Autowired
    private AndroidOrderShow show;

    @Autowired
    private AndroidTeamShow teamShow;

    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private AccountBusiness accountBusiness;

    /**
     * 获取自己的组队请求(未入队)
     *
     * @param uid
     * @return
     * @Title getMyTeamJoinRequestList
     */
    @ResponseBody
    @RequestMapping("/getMyTeamJoinRequests")
    public List<OrderTeamSimple> getMyTeamJoinRequestList(@SessionAttribute(SESSION_UID) Long uid) {

        return teamShow.getUserRequestOrderTeamList(uid);

    }

    /**
     * 获取正在里面的队伍（已入队）
     *
     * @param uid
     * @return
     * @Title getMyNowInTeam
     */

    @ResponseBody
    @RequestMapping("/getMyNowInTeam")
    public OrderTeamSimple getMyNowInTeam(@SessionAttribute(SESSION_UID) Long uid) {

        return teamShow.getUserInOrderTeam(uid);

    }

    /**
     * 获取当前正在作业订单
     *
     * @param uid
     * @return
     * @Title getMyWorkingOrder
     */
    @ResponseBody
    @RequestMapping("/getMyWorkingOrder")
    public MyOrderInfoDetail getMyWorkingOrder(@SessionAttribute(SESSION_UID) Long uid) {

        OrderFarmer orderFarmer = orderBusiness.getMyWorkingOrder(uid);

        if (orderFarmer == null) {
            return null;
        }

        return show.getMyOrderInfoDetailByOrderId(orderFarmer.getOrder_id());

    }

    /**
     * 获取所有订单信息
     *
     * @return
     * @Title getOrderList
     */
    @ResponseBody
    @RequestMapping("/getOrderList")
    public List<OrderInfoSimple> getOrderList() {

        return show.showOrderInfoSimpleList();
    }

    /**
     * 获取订单详细信息
     *
     * @param androidRequest {orderId:String}
     * @return
     * @throws AndroidOutputException
     * @Title getOrderInfoDetail
     */

    @ResponseBody
    @RequestMapping("/getOrderDetail")
    public OrderInfoDetail getOrderInfoDetail(@RequestBody AndroidRequest androidRequest)
            throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return show.getOrderInfoDetailByOrderId(orderId);

    }

    /**
     * 获取订单详细信息
     *
     * @param androidRequest {orderId:String}
     * @return
     * @throws AndroidOutputException
     * @Title getMyOrderInfoDetail
     */

    @ResponseBody
    @RequestMapping("/getMyOrderDetail")
    public MyOrderInfoDetail getMyOrderInfoDetail(@RequestBody AndroidRequest androidRequest)
            throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return show.getMyOrderInfoDetailByOrderId(orderId);

    }

    /**
     * 关注一个订单，当此订单开始抢单的时候会收到系统通知和推送;当订单进行时，如果有人发起组队，也会发送系统通知和推送
     *
     * @param androidRequest {orderId:String}
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title getOrderList
     */
    @ResponseBody
    @RequestMapping("/followOrder")
    public String followOrder(@RequestBody AndroidRequest androidRequest,
                              @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        driverTeamOrderBusiness.followOrder(uid, orderId);

        return "success";
    }

    /**
     * 取消关注这个订单
     *
     * @param androidRequest {orderId:String}
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title unFollowOrder
     */
    @ResponseBody
    @RequestMapping("/unFollowOrder")
    public String unFollowOrder(@RequestBody AndroidRequest androidRequest,
                                @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        driverTeamOrderBusiness.unfollowOrder(uid, orderId);

        return "success";

    }

    /**
     * 获取订单对应的队伍列表
     *
     * @param androidRequest {orderId:String}
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title getOrderTeam
     */
    @ResponseBody
    @RequestMapping("/getTeams")
    public List<OrderTeamSimple> getTeams(@RequestBody AndroidRequest androidRequest,
                                          @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return teamShow.getOrderTeamList(orderId);

    }

    /**
     * 创建一个队伍，所有关注这个单子的人，都能收到系统通知
     *
     * @param androidRequest {orderId:String,message:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title createOrderTeam
     */
    @ResponseBody
    @RequestMapping("/createOrderTeam")
    public Long createOrderTeam(@RequestBody AndroidRequest androidRequest,
                                @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");
        String message = (String) params.get("message");

        return driverTeamOrderBusiness.createOrderTeam(orderId, uid, message);

    }

    /**
     * 获取单个队伍组队详情
     *
     * @param androidRequest{teamId:Long}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title getOrderTeam
     */

    @ResponseBody
    @RequestMapping("/getOrderTeam")
    public OrderTeam getOrderTeam(@RequestBody AndroidRequest androidRequest,
                                  @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");

        OrderTeam orderTeam = teamShow.geOrderTeamSimpleById(teamId);
        if (orderTeam == null) {
            throw new BusinessException(2020);
        }
        return orderTeam;
    }

    /**
     * 判断订单是否被用户关注
     *
     * @param androidRequest {orderId:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @Title isUserFollowCurrentOrder
     */
    @ResponseBody
    @RequestMapping("/isUserFollowCurrentOrder")
    public Boolean isUserFollowCurrentOrder(@RequestBody AndroidRequest androidRequest,
                                            @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return driverTeamOrderBusiness.isUserFollowOrder(uid, orderId);

    }

    /**
     * 发送入队申请
     *
     * @param androidRequest {teamId:Long,message:String}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title joinOrderTeam
     */
    @ResponseBody
    @RequestMapping("/joinOrderTeamRequest")
    public String joinOrderTeamRequest(@RequestBody AndroidRequest androidRequest,
                                       @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");
        String message = (String) params.get("message");

        driverTeamOrderBusiness.sendJoinOrderTeamRequest(teamId, uid, message);

        return "success";
    }

    /**
     * 撤销入队申请
     *
     * @param androidRequest {teamId:Long}
     * @param uid
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title cancelOrderTeamRequest
     */
    @ResponseBody
    @RequestMapping("/cancelOrderTeamRequest")
    public String cancelOrderTeamRequest(@RequestBody AndroidRequest androidRequest,
                                         @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");

        driverTeamOrderBusiness.cancelTeamRequest(teamId, uid);

        return "success";
    }

    /**
     * 同意某某某入队,需要判断是否成单，如果成单是否抢到了单，并且要在这里撤销掉所有其他的申请队伍
     *
     * @param androidRequest {teamId:Long,username:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title acceptTeamRequest
     */
    @ResponseBody
    @RequestMapping("/acceptTeamRequest")
    public String acceptTeamRequest(@RequestBody AndroidRequest androidRequest,
                                    @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");

        String username = (String) params.get("username");

        driverTeamOrderBusiness.acceptTeamRequest(teamId, accountBusiness.getUserInfoByUsername
                        (username).getUid(),
                operator);

        return "success";
    }

    /**
     * 拒绝入队申请
     *
     * @param androidRequest {teamId:Long,username:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title denyTeamRequest
     */
    @ResponseBody
    @RequestMapping("/denyTeamRequest")
    public String denyTeamRequest(@RequestBody AndroidRequest androidRequest,
                                  @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");

        String username = (String) params.get("username");

        driverTeamOrderBusiness.denyTeamRequest(teamId, accountBusiness.getUserInfoByUsername
                        (username).getUid(),
                operator);

        return "success";
    }

    /**
     * 删除一个队友
     *
     * @param androidRequest {teamId:Long,username:String,ps:String}
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title deletePartener
     */
    @ResponseBody
    @RequestMapping("/deletePartener")
    public String deletePartener(@RequestBody AndroidRequest androidRequest,
                                 @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");

        String username = (String) params.get("username");

        String ps = (String) params.get("ps");

        // 需要发送系统通知
        driverTeamOrderBusiness.deleteTeamRequest(teamId, accountBusiness.getUserInfoByUsername
                (username).getUid(), ps, operator);

        return "success";
    }

    /**
     * 删除队伍
     *
     * @param androidRequest
     * @param operator
     * @return
     * @throws AndroidOutputException
     * @throws BusinessException
     * @Title deleteOrderTeam
     */
    @ResponseBody
    @RequestMapping("deleteOrderTeam")
    public String deleteOrderTeam(@RequestBody AndroidRequest androidRequest,
                                  @SessionAttribute(SESSION_UID) Long operator)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long teamId = (Long) params.get("teamId");

        driverTeamOrderBusiness.deleteOrderTeam(teamId, operator);

        return "success";
    }

    @ResponseBody
    @RequestMapping("/getGrabTeam")
    public List<OrderTeamSimple> getGrabTeam(@RequestBody AndroidRequest androidRequest,
                                             @SessionAttribute(SESSION_UID) Long uid)
            throws AndroidOutputException, BusinessException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        return teamShow.getGrabOrderTeam(orderId);

    }

}
