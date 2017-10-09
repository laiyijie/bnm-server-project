package cn.bangnongmang.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.enums.DriverFarmerEnum;
import cn.bangnongmang.admin.enums.OrderDriverEnum;
import cn.bangnongmang.admin.enums.UserMachineEnums;
import cn.bangnongmang.admin.service.TeamManageService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OptionClusterWorkingTypeMappingMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingCriteria;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionDetailCriteria;
import cn.bangnongmang.data.domain.OptionMachineModelMappingCriteria;
import cn.bangnongmang.data.domain.OptionMachineModelMappingKey;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OptionWorkingTypeCriteria;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderDriverCriteria;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamInOrderCriteria;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineCriteria;

@Service
public class TeamManageServiceImpl implements TeamManageService {

	@Autowired
	private TeamInOrderMapper teamInOrderMapper;
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private OrderDriverMapper orderDriverMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private OptionMachineModelMappingMapper optionMachineModelMappingMapper;
	@Autowired
	private OptionDetailMapper optionDetailMapper;
	@Autowired
	private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;
	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;
	@Autowired
	private UserBasicInfoShowMapper userBasicInfoShowMapper;

	@Override
	public PageResult<TeamInOrder> getTeamManageList(Integer currentPage, Integer pageSize) throws BusinessException {

		PageHelper.startPage(currentPage, pageSize);
		TeamInOrderCriteria orderCriteria = new TeamInOrderCriteria();
		Page<TeamInOrder> list = (Page<TeamInOrder>) teamInOrderMapper.selectByExample(orderCriteria);

		return new PageResult<TeamInOrder>(list);
	}

	@Override
	public String addDriver1(String orderId, Long uid) throws BusinessException {

		OrderFarmer farmer = orderFarmerMapper.selectByPrimaryKey(orderId);
		if (farmer == null) {
			throw new BusinessException("订单不存在");
		}

		OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
		orderDriverCriteria.or().andOrder_farmer_idEqualTo(orderId);
		List<OrderDriver> example = orderDriverMapper.selectByExample(orderDriverCriteria);
		if (example.isEmpty()) {
			throw new BusinessException("订单不存在");
		}

		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(uid);
		List<Account> selectByExample = accountMapper.selectByExample(accountCriteria);
		if (selectByExample.isEmpty()
				|| selectByExample.get(0).getLevel() != DriverFarmerEnum.AUTHENTICATED_DRIVER.getCurrencyCode()) {
			throw new BusinessException("机手不存在或未认证");
		}

		orderDriverCriteria.clear();
		orderDriverCriteria.or().andUidEqualTo(uid).andOrder_farmer_idEqualTo(orderId)
				.andStateEqualTo(OrderDriverEnum.ORDER_DRIVER_STATE_GOT.getCurrencyCode());
		List<OrderDriver> example1 = orderDriverMapper.selectByExample(orderDriverCriteria);
		if (!example1.isEmpty()) {
			throw new BusinessException("已存在此订单中");
		}

		List<OptionWorkingType> list = getWorkTypeByUsername(uid);

		for (OptionWorkingType key : list) {
			if (farmer.getWorking_type_id().equals(key.getId())) {
				if (example.size() == 1) {

					OrderDriver orderDriver = example.get(0);
					return setOrderDriver(orderDriver, uid, orderDriver.getOrder_id());

				} else {
					String checkbox = checkbox(example);
					return setOrderDriver(example.get(0), uid, checkbox);
				}
			} else {
				return "oter";
			}
		}
		throw new BusinessException("失败");
	}

	public String setOrderDriver(OrderDriver driver, Long uid, String id) {

		String substring = id.substring(23, 28);
		int parseInt = Integer.parseInt(substring);
		parseInt = parseInt + 1;

		String substring2 = id.substring(0, 23);
		String orderid = substring2 + parseInt;

		OrderDriver orderDriver = new OrderDriver();
		orderDriver.setOrder_id(orderid);
		orderDriver.setOrder_farmer_id(driver.getOrder_farmer_id());
		orderDriver.setActual_money(driver.getActual_money());
		orderDriver.setActual_size(driver.getActual_size());
		orderDriver.setUid(uid);
		orderDriver.setService_start(driver.getService_start());
		orderDriver.setService_end(driver.getService_end());
		orderDriver.setState(driver.getState());

		if (orderDriverMapper.insert(orderDriver) > 0) {
			return "success";
		}
		return "";
	}

	public String checkbox(List<OrderDriver> list) {

		String max = null;
		for (OrderDriver orderDriver : list) {

			if (max == null || max.compareTo(orderDriver.getOrder_id()) < 0) {
				max = orderDriver.getOrder_id();
			}

		}

		return max;

	}

	@Override
	public String addDriver2(String orderId, Long uid) throws BusinessException {

		OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
		orderDriverCriteria.or().andUidEqualTo(uid).andOrder_farmer_idEqualTo(orderId);
		List<OrderDriver> example = orderDriverMapper.selectByExample(orderDriverCriteria);
		OrderDriver driver = example.get(0);

		OrderDriver orderDriver = new OrderDriver();
		orderDriver.setOrder_farmer_id(driver.getOrder_farmer_id());
		orderDriver.setActual_money(driver.getActual_money());
		orderDriver.setActual_size(driver.getActual_size());
		orderDriver.setUid(uid);
		orderDriver.setOrder_id(driver.getOrder_id());
		orderDriver.setService_start(driver.getService_start());
		orderDriver.setService_end(driver.getService_end());
		orderDriver.setState(driver.getState());

		return "success";
	}

	@Override
	public String delDriverToOrder(String orderId, Long uid) throws BusinessException {
		OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
		orderDriverCriteria.or().andUidEqualTo(uid).andOrder_farmer_idEqualTo(orderId);
		if (orderDriverMapper.deleteByExample(orderDriverCriteria) < 0) {
			throw new BusinessException("失败");
		}
		return "success";
	}

	/**
	 * 根据用户名获取 本人的机器工作类型
	 */
	public List<OptionWorkingType> getWorkTypeByUsername(Long uid) throws BusinessException {

		UserMachineCriteria machineCriteria = new UserMachineCriteria();
		machineCriteria.or().andUidEqualTo(uid);
		List<UserMachine> selectByExample = userMachineMapper.selectByExample(machineCriteria);
		if (selectByExample.isEmpty()
				|| !selectByExample.get(0).getState().equals(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode())) {
			throw new BusinessException("暂无车辆或未认证");
		}

		OptionMachineModelMappingCriteria criteria = new OptionMachineModelMappingCriteria();
		criteria.or().andModel_idEqualTo(selectByExample.get(0).getMachine_model_id());
		List<OptionMachineModelMappingKey> example = optionMachineModelMappingMapper.selectByExample(criteria);

		return getWorktype(example.get(0).getOption_id());

	}

	public List<OptionWorkingType> getWorktype(long long1) throws BusinessException {

		OptionDetailCriteria optionDetailCriteria = new OptionDetailCriteria();
		optionDetailCriteria.or().andIdEqualTo(long1);
		List<OptionDetail> selectByExample = optionDetailMapper.selectByExample(optionDetailCriteria);

		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
		criteria.or().andCluster_idEqualTo(selectByExample.get(0).getCluster_id());
		List<OptionClusterWorkingTypeMappingKey> list = optionClusterWorkingTypeMappingMapper.selectByExample(criteria);

		OptionWorkingTypeCriteria typeCriteria = new OptionWorkingTypeCriteria();
		typeCriteria.or().andIdEqualTo(list.get(0).getWorking_type_id());
		List<OptionWorkingType> selectByExample2 = optionWorkingTypeMapper.selectByExample(typeCriteria);
		if (selectByExample2.isEmpty()) {
			throw new BusinessException("暂无设置工作类型");
		}
		return selectByExample2;

	}

	@Override
	public PageResult<OrderDriver> getDriverByOrderId(Integer currentPage, Integer pageSize, String orderId)
			throws BusinessException {

		OrderFarmer farmer = orderFarmerMapper.selectByPrimaryKey(orderId);
		if (farmer == null) {
			throw new BusinessException("订单不存在");
		}

		TeamInOrderCriteria orderCriteria = new TeamInOrderCriteria();
		orderCriteria.or().andOrder_idEqualTo(orderId);
		List<TeamInOrder> selectByExample = teamInOrderMapper.selectByExample(orderCriteria);
		if (selectByExample.isEmpty()) {
			throw new BusinessException("队伍不存在");
		}

		OrderDriverCriteria driverCriteria = new OrderDriverCriteria();
		driverCriteria.or().andOrder_farmer_idEqualTo(orderId);

		PageHelper.startPage(currentPage, pageSize);
		Page<OrderDriver> list = (Page<OrderDriver>) orderDriverMapper.selectByExample(driverCriteria);

		return new PageResult<OrderDriver>(list);
	}

	@Override
	public List<UserBasicInfoShow> selectRealNameAuthListByLevel(Integer lowLevel, Integer highLevel)
			throws BusinessException {

		List<UserBasicInfoShow> list = userBasicInfoShowMapper.selectRealNameAuthListByLevel(lowLevel, highLevel);

		return list;
	}

	@Override
	public PageResult<TeamInOrder> getTeamInOrderByorderFarmerId(Integer currentPage, Integer pageSize,
			String orderFarmerId) throws BusinessException {

		OrderFarmer farmer = orderFarmerMapper.selectByPrimaryKey(orderFarmerId);
		if (farmer == null) {
			throw new BusinessException("订单不存在");
		}
		TeamInOrderCriteria orderCriteria = new TeamInOrderCriteria();
		orderCriteria.or().andOrder_idEqualTo(orderFarmerId);

		PageHelper.startPage(currentPage, pageSize);
		Page<TeamInOrder> list = (Page<TeamInOrder>) teamInOrderMapper.selectByExample(orderCriteria);

		return new PageResult<TeamInOrder>(list);
	}

}
