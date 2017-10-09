package cn.bangnongmang.admin.controller;

import java.util.List;

import cn.bangnongmang.admin.business.WorkTeamManageBusiness;
import cn.bangnongmang.admin.service.UserService;
import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.TeamOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bangnongmang.admin.controller.api.TeamManageController;
import cn.bangnongmang.admin.service.TeamManageService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.TeamInOrder;

@RestController
public class TeamManageControllerImpl implements TeamManageController {
    @Autowired
    private TeamManageService teamManageService;
    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private WorkTeamManageBusiness workTeamManageBusiness;
    @Autowired
    private UserService userService;

    @Override
    public PageResult<TeamInOrder> getTeamManageList(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException {

        return teamManageService.getTeamManageList(currentPage, pageSize);
    }


    @Override
    public TeamInfoShow getCurrentTeamByOrderId(
            @RequestParam(value = "orderFarmerId") String orderFarmerId) throws BusinessException {
        if (orderFarmerId == null) {
            throw new BusinessException("订单号为空");
        }
        List<TeamInfoShow> teamInfoShows = teamInfoShowMapper.selectTeamInfoShowByOrderId(orderFarmerId);
        if (teamInfoShows.size() == 1) {
            teamInfoShows.get(0)
                         .getRequesters()
                         .removeIf(teamJoinRequsterInfo -> !teamJoinRequsterInfo.getTeamJoinRequest()
                                                                                .getState()
                                                                                .equals(TeamOrderService
                                                                                        .REQUEST_ACCEPTED));
            return teamInfoShows.get(0);
        }
        return null;
    }

    @Override
    public List<UserBasicInfoShow> selectRealNameAuthListByLevel(
            @RequestParam(value = "lowLevel", defaultValue = "0") Integer lowLevel,
            @RequestParam(value = "highLevel", defaultValue = "1000") Integer highLevel) throws BusinessException {

        return teamManageService.selectRealNameAuthListByLevel(lowLevel, highLevel);
    }

    @Override
    public String addMember(@RequestParam("username") String username, @RequestParam("orderId") String orderId,
                            @RequestParam("needInsurance") Boolean needInsurance) throws BusinessException {
        Account account = userService.getAccountByUsername(username);
        workTeamManageBusiness.addMember(account.getUid(), orderId, needInsurance);
        return "success";
    }

    @Override
    public String removeMember(@RequestParam("username") String username, @RequestParam("orderId") String orderId,
                               @RequestParam("punishMoney") Integer punishMoney,@RequestParam(name="isPunishWaitInMoney",defaultValue = "true") Boolean isPunishWaitInMoney) throws BusinessException {
        Account account = userService.getAccountByUsername(username);
        workTeamManageBusiness.removeMember(account.getUid(), orderId, punishMoney,isPunishWaitInMoney);
        return "success";
    }

    @Override
    public String changeLeader(@RequestParam("newLeader") String newLeader, @RequestParam("orderId") String orderId) throws BusinessException {
        Account account = userService.getAccountByUsername(newLeader);
        workTeamManageBusiness.changeLeader(account.getUid(), orderId);
        return "success";
    }

    @Override
    public PageResult<TeamInOrder> getTeamInOrderByorderFarmerId(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "orderFarmerId") String orderFarmerId) throws BusinessException {

        if (orderFarmerId == null) {
            throw new BusinessException("订单号为空");
        }

        return teamManageService.getTeamInOrderByorderFarmerId(currentPage, pageSize, orderFarmerId);
    }

}
