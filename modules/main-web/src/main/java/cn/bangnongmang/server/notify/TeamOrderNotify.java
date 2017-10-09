package cn.bangnongmang.server.notify;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.server.notify.bo.HookTeamInfoParams;
import cn.bangnongmang.service.service.TeamOrderService;

@Aspect
@Component
@Order(3)
public class TeamOrderNotify {

    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    private NotifyUtil notifyUtil;
    @Autowired
    private TeamInOrderMapper teamInOrderMapper;
    public static final String TEAM_NEW_REQUESTER_TO_LEADER = "TEAM_NEW_REQUESTER_TO_LEADER";

    public static final String TEAM_ACCEPT_REQUEST_TO_MEMBER = "TEAM_ACCEPT_REQUEST_TO_MEMBER";

    public static final String TEAM_DENY_REQUEST_TO_MEMBER = "TEAM_DENY_REQUEST_TO_MEMBER";

    public static final String TEAM_REMOVE_MEMBER_TO_MEMBER = "TEAM_REMOVE_MEMBER_TO_MEMBER";

    public static final String TEAM_DELETE_TO_MEMBER = "TEAM_DELETE_TO_MEMBER";

    @Autowired
    public TeamOrderNotify(NotifyUtil notifyUtil) {
        this.notifyUtil = notifyUtil;

        HookTeamInfoParams params = new HookTeamInfoParams();
        params.setRequesterName("辣鸡鑫河");
        params.setLeaderName("辣鸡大白");
        params.setOccuredTime(notifyUtil.convertTimemillisToString(System.currentTimeMillis()));
        params.setOrderId("201612131555123522");
        params.setTeamId(String.valueOf(1235512315331L));
        params.setTeamMemberNum(String.valueOf(5));
        params.setTeamRequesterNum(String.valueOf(5));
        params.setRemoveReson("太傻");

        notifyUtil.registerHook(TEAM_NEW_REQUESTER_TO_LEADER, "组队 - 有新的入队请求 - 给队长", params);
        notifyUtil.registerHook(TEAM_ACCEPT_REQUEST_TO_MEMBER, "组队 - 队长接受某人入队 - 给这个人", params);
        notifyUtil.registerHook(TEAM_DENY_REQUEST_TO_MEMBER, "组队 - 队长拒绝某人入队 - 给这人", params);
        notifyUtil.registerHook(TEAM_REMOVE_MEMBER_TO_MEMBER, "组队 - 队长移除某人 - 给这个人", params);
        notifyUtil.registerHook(TEAM_DELETE_TO_MEMBER, "组队 - 队伍解散 - 给所有队员", params);

    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.TeamOrderBusiness.sendJoinOrderTeamRequest(..)) && args(teamid,uid,msg)")
    public void sendJoinOrderTeamRequest(Long teamid, Long uid, String msg) {
        TeamInOrder teamInOrder = teamInOrderMapper.selectByPrimaryKey(teamid);
        HookTeamInfoParams hookTeamInfoParams = createParams(teamid, uid);
        notifyUtil.sendNotifyBusinessMessage(teamInOrder.getUid(), TEAM_NEW_REQUESTER_TO_LEADER,
                hookTeamInfoParams);
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.TeamOrderBusiness.acceptTeamRequest(..)) && args(teamid,uid,operator)")
    public void acceptTeamRequest(Long teamid, Long uid, String operator) {
        HookTeamInfoParams hookTeamInfoParams = createParams(teamid, uid);
        notifyUtil.sendNotifyBusinessMessage(uid, TEAM_ACCEPT_REQUEST_TO_MEMBER, hookTeamInfoParams);
    }

    @AfterReturning("execution(* cn.bangnongmang.server.business.common.TeamOrderBusiness.denyTeamRequest(..)) && args(teamid,uid,operator)")
    public void denyTeamRequest(Long teamid, Long uid, String operator) {
        HookTeamInfoParams hookTeamInfoParams = createParams(teamid, uid);
        notifyUtil.sendNotifyBusinessMessage(uid, TEAM_DENY_REQUEST_TO_MEMBER, hookTeamInfoParams);
    }

    /**
     * 删除一个队员
     *
     * @param teamid
     * @param uid
     * @param operator
     * @Title deleteTeamRequest
     */
    @AfterReturning("execution(* cn.bangnongmang.server.business.common.TeamOrderBusiness.deleteTeamRequest(..)) && args(teamid,uid,ps,operator)")
    public void deleteTeamRequest(Long teamid, Long uid, String ps, String operator) {
        HookTeamInfoParams hookTeamInfoParams = createParams(teamid, uid);
        hookTeamInfoParams.setRemoveReson(ps);
        notifyUtil.sendNotifyBusinessMessage(uid, TEAM_REMOVE_MEMBER_TO_MEMBER, hookTeamInfoParams);
    }

    /**
     * 解散队伍
     *
     * @throws Throwable
     * @Title deleteOrderTeam
     */
    @Around("execution(* cn.bangnongmang.server.business.common.TeamOrderBusiness.deleteOrderTeam(..))")
    public Object deleteOrderTeam(ProceedingJoinPoint pjp) throws Throwable {
        if (pjp.getArgs().length != 2) {
            return null;
        }
        Long teamid = (Long) pjp.getArgs()[0];
        Long operator = (Long) pjp.getArgs()[1];
        HookTeamInfoParams hookTeamInfoParams = createParams(teamid, null);
        TeamInfoShow teamInfoShow = teamInfoShowMapper.selectTeamInfoShowByTeamId(teamid);
        Object result = pjp.proceed();

        List<Long> members = teamInfoShow.getRequesters()
                                         .stream()
                                         .map(a -> a.getTeamJoinRequest()
                                                    .getUid())
                                         .collect(Collectors.toList());
        members.add(teamInfoShow.getLeader()
                                .getAccount()
                                .getUid());

        members.forEach(a -> notifyUtil.sendNotifyBusinessMessage(a, TEAM_DELETE_TO_MEMBER, hookTeamInfoParams));

        return result;
    }

    private HookTeamInfoParams createParams(Long teamId, Long requester) {
        TeamInfoShow teamInfoShow = teamInfoShowMapper.selectTeamInfoShowByTeamId(teamId);
        HookTeamInfoParams params = new HookTeamInfoParams();
        params.setRequesterName("");
        if (requester != null && !"".equals(requester)) {
            UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(requester);
            if (userBasicInfoShow != null) {
                params.setRequesterName(userBasicInfoShow.getAccountRealNameAuth() != null
                        ? userBasicInfoShow.getAccountRealNameAuth()
                                           .getReal_name() : "");
            } else {
                params.setRequesterName("");
            }

        }
        params.setLeaderName(
                teamInfoShow.getLeader() != null && teamInfoShow.getLeader()
                                                                .getAccountRealNameAuth() != null
                        ? teamInfoShow.getLeader()
                                      .getAccountRealNameAuth()
                                      .getReal_name() : "未认证用户");
        params.setOccuredTime(notifyUtil.convertTimemillisToString(System.currentTimeMillis()));
        params.setOrderId(teamInfoShow.getTeamInOrder()
                                      .getOrder_id());
        params.setTeamId(String.valueOf(teamId));
        params.setTeamMemberNum(String.valueOf(teamInfoShow.getRequesters()
                                                           .stream()
                                                           .filter(a -> TeamOrderService.REQUEST_ACCEPTED == (a.getTeamJoinRequest()
                                                                                                               .getState()))
                                                           .count() + 1));
        params.setTeamRequesterNum(String.valueOf(teamInfoShow.getRequesters()
                                                              .stream()
                                                              .filter(a -> TeamOrderService.REQUEST_WAITTING == a.getTeamJoinRequest()
                                                                                                                 .getState())
                                                              .count()));
        params.setRemoveReson("");
        return params;
    }

}
