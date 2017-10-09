package cn.bangnongmang.server.io.android.show;

import java.util.ArrayList;
import java.util.List;

import cn.bangnongmang.data.combo.dao.GrabSeasonInfoShowMapper;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.driverapp.models.*;
import cn.bangnongmang.driverapp.models.OrderFarmerStateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.combo.domain.TeamJoinRequsterInfo;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.TeamOrderService;

@Service
public class AndroidTeamShow {

    @Autowired
    private AndroidUserShow androidUserShow;

    @Autowired
    private AndroidOrderShow androidOrderShow;
    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private GrabSeasonInfoShowMapper grabSeasonInfoShowMapper;

    public OrderTeamSimple getUserInOrderTeam(Long uid) {

        List<OrderTeamSimple> result = new ArrayList<>();

        List<TeamInfoShow> tInfoShows = teamInfoShowMapper.selectTeamInfoShowByRequesterAndState(
                uid,
                TeamOrderService.REQUEST_ACCEPTED);

        tInfoShows.addAll(teamInfoShowMapper.selectTeamInfoShowByLeader(uid));

        tInfoShows.removeIf(a -> !cn.bangnongmang.service.service.OrderFarmerStateName.FARMER_WAITTING_GOT.equals(a.getOrderFarmerInfoShow()
                                                                                                                   .getOrderFarmer()
                                                                                                                   .getState()));
        if (tInfoShows.isEmpty()) {
            return null;
        }

        return convertTeamInfoShowToOrderTeamSimple(tInfoShows.get(0));

    }

    public List<OrderTeamSimple> getUserRequestOrderTeamList(Long uid) {

        List<OrderTeamSimple> result = new ArrayList<>();

        List<TeamInfoShow> tInfoShows = teamInfoShowMapper.selectTeamInfoShowByRequesterAndState(
                uid,
                TeamOrderService.REQUEST_WAITTING);

        for (TeamInfoShow teamInfoShow : tInfoShows) {
            result.add(convertTeamInfoShowToOrderTeamSimple(teamInfoShow));
        }
        return result;
    }

    public List<OrderTeamSimple> getOrderTeamList(String orderid) throws BusinessException {

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderid);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }

        List<OrderTeamSimple> result = new ArrayList<>();

        List<TeamInfoShow> tInfoShows = teamInfoShowMapper.selectTeamInfoShowByOrderId(orderid);

        for (TeamInfoShow teamInfoShow : tInfoShows) {
            result.add(convertTeamInfoShowToOrderTeamSimple(teamInfoShow));
        }

        return result;
    }

    public List<OrderTeamSimple> getGrabOrderTeam(String orderid) throws BusinessException {

        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderid);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        List<OrderTeamSimple> result = new ArrayList<>();

        List<TeamInfoShow> tInfoShows = teamInfoShowMapper.selectTeamInfoShowByOrderId(orderid);

        for (TeamInfoShow teamInfoShow : tInfoShows) {
            result.add(convertTeamInfoShowToOrderTeamSimple(teamInfoShow));
        }

        return result;
    }

    public OrderTeam geOrderTeamSimpleById(Long id) {
        return convertTeamInfoShowToOrderTeam(teamInfoShowMapper.selectTeamInfoShowByTeamId(id));
    }

    public OrderTeam convertTeamInfoShowToOrderTeam(TeamInfoShow tis) {

        if (tis == null) {
            return null;
        }

        OrderTeam orderTeam = new OrderTeam();

        orderTeam.setLeader(
                androidUserShow.convertUserBasicInfoShowToUserInfoSimple(tis.getLeader()));
        orderTeam.setOrderInfo(
                androidOrderShow.getOrderInfoSimpleByOrderId(tis.getTeamInOrder().getOrder_id()));

        List<TeamRequsterInfo> parterner = new ArrayList<>();
        List<TeamRequsterInfo> requester = new ArrayList<>();

        for (TeamJoinRequsterInfo uis : tis.getRequesters()) {
            if (uis.getTeamJoinRequest() != null
                    && uis.getTeamJoinRequest().getState().equals(
                    TeamOrderService.REQUEST_ACCEPTED)) {
                parterner.add(convertTeamJoinRequsterInfoToTeamRequesterInfo(uis));
            } else if (uis.getTeamJoinRequest() != null
                    && uis.getTeamJoinRequest().getState().equals(
                    TeamOrderService.REQUEST_WAITTING)) {
                requester.add(convertTeamJoinRequsterInfoToTeamRequesterInfo(uis));
            }
        }

        orderTeam.setParterner(parterner);
        orderTeam.setRequester(requester);
        orderTeam.setDescription(tis.getTeamInOrder().getDescription());
        return orderTeam;
    }

    public TeamRequsterInfo convertTeamJoinRequsterInfoToTeamRequesterInfo(TeamJoinRequsterInfo tjrs) {

        if (tjrs == null) {
            return null;
        }
        TeamRequsterInfo teamRequsterInfo = new TeamRequsterInfo();

        if (tjrs.getTeamJoinRequest() != null) {
            teamRequsterInfo.setPs(tjrs.getTeamJoinRequest().getPs());
            teamRequsterInfo.setState(tjrs.getTeamJoinRequest().getState());
        }
        teamRequsterInfo.setUserInfoSimple(
                androidUserShow.convertUserBasicInfoShowToUserInfoSimple(
                        tjrs.getUserBasicInfoShow()));

        return teamRequsterInfo;
    }

    public OrderTeamSimple convertTeamInfoShowToOrderTeamSimple(TeamInfoShow tis) {

        OrderTeamSimple orderTeamSimple = new OrderTeamSimple();

        orderTeamSimple.setDescription(tis.getTeamInOrder().getDescription());
        orderTeamSimple.setLeader(
                androidUserShow.convertUserBasicInfoShowToUserInfoSimple(tis.getLeader()));
        Integer count = 1;
        for (TeamJoinRequsterInfo uis : tis.getRequesters()) {
            if (uis.getTeamJoinRequest() != null
                    && uis.getTeamJoinRequest().getState().equals(
                    TeamOrderService.REQUEST_ACCEPTED)) {
                count++;
            }
        }
        orderTeamSimple.setOrderInfoSimple(
                androidOrderShow.convertOrderFarmerInfoShowToOrderInfoSimple(OrderInfoDetail.class,
                        tis.getOrderFarmerInfoShow(),
                        grabSeasonInfoShowMapper.selectGrabSeasonInfoShowByMinEndTime(
                                System.currentTimeMillis() / 1000)));
        orderTeamSimple.setReadyNum(count);

        orderTeamSimple.setTeamId(tis.getId());

        return orderTeamSimple;
    }

}
