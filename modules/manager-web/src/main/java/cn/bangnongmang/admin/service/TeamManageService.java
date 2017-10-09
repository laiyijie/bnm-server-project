package cn.bangnongmang.admin.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.TeamInOrder;

/**
 * 
 * 队伍管理接口
 *
 */

public interface TeamManageService {

	public PageResult<TeamInOrder> getTeamManageList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;

	public PageResult<OrderDriver> getDriverByOrderId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "orderFarmerId") String orderFarmerId) throws BusinessException;

	public PageResult<TeamInOrder> getTeamInOrderByorderFarmerId(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "orderFarmerId") String orderFarmerId) throws BusinessException;

	public List<UserBasicInfoShow> selectRealNameAuthListByLevel(
			@RequestParam(value = "lowLevel", defaultValue = "0") Integer lowLevel,
			@RequestParam(value = "highLevel", defaultValue = "1000") Integer highLevel) throws BusinessException;

	public String addDriver1(String orderId, Long uid) throws BusinessException;

	public String addDriver2(String orderId, Long uid) throws BusinessException;

	public String delDriverToOrder(String orderId, Long uid) throws BusinessException;
}
