package cn.bangnongmang.service.service.impl;

import java.util.Date;
import java.util.List;

import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamInOrderCriteria;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.data.domain.TeamJoinRequestCriteria;
import cn.bangnongmang.service.service.TeamOrderService;

@Service("S_TeamOrderService")
public class TeamOrderServiceImpl implements TeamOrderService {

    @Autowired
    private TeamInOrderMapper teamInOrderMapper;
    @Autowired
    private TeamJoinRequestMapper teamJoinRequestMapper;
    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;

    @Override
    public Long createOrderTeam(String orderid, Long uid, String msg) {
        Long timeStamp = new Date().getTime();
        Long targetId = timeStamp * 1000 + (int) (Math.random() * 1000);

        TeamInOrder teamInOrder = new TeamInOrder();
        teamInOrder.setUid(uid);
        teamInOrder.setOrder_id(orderid);
        teamInOrder.setId(targetId);
        teamInOrder.setDescription(msg);

        teamInOrderMapper.insert(teamInOrder);

        return targetId;
    }

    @Override
    public TeamJoinRequest getTeanJoinRequest(Long teamid, Long uid) {

        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andTeam_idEqualTo(teamid)
                               .andUidEqualTo(uid);
        List<TeamJoinRequest> teamJoinRequestList = teamJoinRequestMapper.selectByExample(teamJoinRequestCriteria);

        if (teamJoinRequestList.isEmpty()) {
            return null;
        } else {
            return teamJoinRequestList.get(0);
        }

    }

    @Override
    public Long createJoinOrderTeamRequest(Long teamid, Long uid, String msg) {

        Long timeStamp = new Date().getTime();
        Long targetId = timeStamp * 1000 + (int) (Math.random() * 1000);

        TeamJoinRequest teamJoinRequest = new TeamJoinRequest();
        teamJoinRequest.setId(targetId);
        teamJoinRequest.setTeam_id(teamid);
        teamJoinRequest.setUid(uid);
        teamJoinRequest.setCreate_time(timeStamp);
        teamJoinRequest.setPs(msg);
        teamJoinRequest.setState(TeamOrderService.REQUEST_WAITTING);

        teamJoinRequestMapper.insert(teamJoinRequest);
        return targetId;
    }

    @Override
    public Boolean changeTeamRequestStateById(Long id, Integer state) {

        TeamJoinRequest teamJoinRequest = teamJoinRequestMapper.selectByPrimaryKey(id);

        if (teamJoinRequest == null) {
            return false;
        }

        teamJoinRequest.setState(state);

        teamJoinRequestMapper.updateByPrimaryKey(teamJoinRequest);

        return true;
    }

    @Override
    public Boolean deleteTeamRequestById(Long id) {

        teamJoinRequestMapper.deleteByPrimaryKey(id);

        return true;
    }

    @Override
    public Boolean deleteRequestByNameAndState(Long uid, Integer state) {

        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andUidEqualTo(uid)
                               .andStateEqualTo(state);

        teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);

        return true;
    }

    @Override
    public Boolean deleteTeamNotAcceptMemberByTeamId(Long teamId) {
        TeamJoinRequestCriteria teamJoinRequestCriteria=new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or().andTeam_idEqualTo(teamId).andStateEqualTo(TeamOrderService.REQUEST_WAITTING);
        teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);

        return true;
    }


    @Override
    public List<TeamJoinRequest> getTeamJoinRequestsByTeamId(Long teamid) {

        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andTeam_idEqualTo(teamid);

        return teamJoinRequestMapper.selectByExample(teamJoinRequestCriteria);
    }

    @Override
    public List<TeamJoinRequest> getTeamJoinRequestsByUsername(Long uid) {
        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andUidEqualTo(uid);

        return teamJoinRequestMapper.selectByExample(teamJoinRequestCriteria);
    }

    @Override
    public List<TeamInOrder> getTeamInOrdersByLeaderUsername(Long uid) {

        TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
        teamInOrderCriteria.or()
                           .andUidEqualTo(uid);

        return teamInOrderMapper.selectByExample(teamInOrderCriteria);
    }

    @Override
    public TeamInOrder getTeamInOrderByLeaderUsernameAndOrderId(Long uid, String orderid) {

        TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
        teamInOrderCriteria.or()
                           .andUidEqualTo(uid)
                           .andOrder_idEqualTo(orderid);

        List<TeamInOrder> orders = teamInOrderMapper.selectByExample(teamInOrderCriteria);

        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }

    @Override
    public TeamInOrder getTeamInOrderByTeamId(Long teamid) {

        return teamInOrderMapper.selectByPrimaryKey(teamid);
    }

    @Override
    public List<TeamInOrder> getTeamInOrderByOrderId(String orderId) {
        TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
        teamInOrderCriteria.or()
                           .andOrder_idEqualTo(orderId);
        return teamInOrderMapper.selectByExample(teamInOrderCriteria);
    }

    @Override
    public List<TeamJoinRequest> getTeamJoinRequestsByTeamIdAndState(Long teamid, Integer state) {

        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andTeam_idEqualTo(teamid)
                               .andStateEqualTo(state);

        return teamJoinRequestMapper.selectByExample(teamJoinRequestCriteria);

    }

    @Override
    public Long countTeamJoinRequestsByTeamIdAndState(Long teamid, Integer state) {
        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andTeam_idEqualTo(teamid)
                               .andStateEqualTo(state);
        return teamJoinRequestMapper.countByExample(teamJoinRequestCriteria);
    }

    @Override
    public Boolean deleteTeamsAndRequestsByOrderId(String orderId, Long exceptFor) {

        TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
        teamInOrderCriteria.or()
                           .andOrder_idEqualTo(orderId)
                           .andIdNotEqualTo(exceptFor);
        List<TeamInOrder> teamInOrders = teamInOrderMapper.selectByExample(teamInOrderCriteria);
        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        for (TeamInOrder teamInOrder : teamInOrders) {
            teamJoinRequestCriteria.or()
                                   .andTeam_idEqualTo(teamInOrder.getId());
        }

        if (!teamInOrders.isEmpty()) {
            teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);
            teamInOrderMapper.deleteByExample(teamInOrderCriteria);
            return true;
        }
        return false;

    }

    @Override
    public Boolean deleteTeamRequestByTeamId(Long teamId) {

        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andTeam_idEqualTo(teamId);

        teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);

        return true;
    }

    @Override
    public Boolean deleteTeamByTeamId(Long teamId) {

        if (teamInOrderMapper.deleteByPrimaryKey(teamId) > 0) {
            return true;
        }

        return false;
    }

    @Override
    public void deleteTeamRequestByTeamIdAndState(Long teamInOrderId, int state) {
        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andIdEqualTo(teamInOrderId)
                               .andStateEqualTo(state);
        teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);
    }

    @Override
    public void updateTeamLeader(Long teamId, Long uid) {
        TeamInOrder teamInOrder = new TeamInOrder();
        teamInOrder.setUid(uid);
        teamInOrder.setId(teamId);
        teamInOrderMapper.updateByPrimaryKeySelective(teamInOrder);
    }

    @Override
    public List<TeamInfoShow> getTeamInfoShowByOrderId(String orderId) {

        return teamInfoShowMapper.selectTeamInfoShowByOrderId(orderId);
    }

    @Override
    public TeamInfoShow getTeamInfoShowByTeamId(Long teamId) {
        return teamInfoShowMapper.selectTeamInfoShowByTeamId(teamId);
    }

}
