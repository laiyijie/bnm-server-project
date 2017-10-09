package cn.bangnongmang.admin.controller.api;

import java.util.List;

import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.TeamInOrder;

/**
 * 队伍管理接口
 */
@RequestMapping("teamManage")
public interface TeamManageController {

    /**
     * 获取所有队伍
     *
     * @param currentPage 需要查询的页码 默认 1
     * @param pageSize    每页显示的数量 默认 20
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getTeamManageList")
    public PageResult<TeamInOrder> getTeamManageList(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;

    /**
     * 根据orderFarmerId获取单下的所有成员
     *
     * @param orderFarmerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getCurrentTeamByOrderId")
    TeamInfoShow getCurrentTeamByOrderId(
            @RequestParam(value = "orderFarmerId") String orderFarmerId) throws BusinessException;

    /**
     * 根据orderFarmerId获取队伍
     *
     * @param currentPage   需要查询的页码 默认 1
     * @param pageSize      每页显示的数量 默认 20
     * @param orderFarmerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getTeamInOrderByorderFarmerId")
    public PageResult<TeamInOrder> getTeamInOrderByorderFarmerId(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "orderFarmerId") String orderFarmerId) throws BusinessException;

    /**
     * 根据LowLevel,highLevel获取成员
     *
     * @param LowLevel  最低用户等级，默认0
     * @param highLevel 最高用户等级，默认1000
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/selectRealNameAuthListByLevel")
    public List<UserBasicInfoShow> selectRealNameAuthListByLevel(
            @RequestParam(value = "lowLevel", defaultValue = "0") Integer lowLevel,
            @RequestParam(value = "highLevel", defaultValue = "1000") Integer highLevel) throws BusinessException;

    @RequestMapping("/addMember")
    public String addMember(@RequestParam("username") String username, @RequestParam("orderId") String orderId, @RequestParam("needInsurance")
            Boolean needInsurance)
            throws
            BusinessException;

    @RequestMapping("/removeMember")
    public String removeMember(@RequestParam("username") String username, @RequestParam("orderId") String orderId, @RequestParam("punishMoney")
            Integer
            punishMoney , @RequestParam(name="isPunishWaitInMoney",defaultValue = "true") Boolean isPunishWaitInMoney)
            throws
            BusinessException;

    @RequestMapping("/changeLeader")
    public String changeLeader(@RequestParam("newLeader") String newLeader, @RequestParam("orderId") String orderId) throws BusinessException;
}
