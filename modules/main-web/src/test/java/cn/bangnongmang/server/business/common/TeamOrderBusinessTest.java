package cn.bangnongmang.server.business.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.OrderGeoMapper;
import cn.bangnongmang.data.dao.OrderInsuranceRecordMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderInsuranceRecordCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketCriteria;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import cn.bangnongmang.data.domain.TeamInOrderCriteria;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.data.domain.TeamJoinRequestCriteria;
import cn.bangnongmang.data.domain.UserMachineCriteria;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamOrderBusinessTest {

	@Autowired
	private TeamOrderBusiness teamOrderBusiness;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	OrderFarmerMapper orderFarmerMapper;
	@Autowired
	TeamInOrderMapper teamInOrderMapper;
	@Autowired
	TeamJoinRequestMapper teamJoinRequestMapper;
	@Autowired
	private GrabSeasonMapper grabSeasonMapper;
	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;
	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;
	@Autowired
	private UserMachineModelMapper userMachineModelMapper;
	@Autowired
	private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;
	@Autowired
	private OrderGeoMapper orderGeoMapper;
	@Autowired
	private OrderInsuranceRecordMapper orderInsuranceRecordMapper;
	@Autowired
	private PocketMapper pocketMapper;
	@Autowired
	private PocketLogMapper pocketLogMapper;

	private Long USERNAME_LEADER = 18334741666L;
	private String ORDERID = "1111111111111";
	private String SEASONID = "2222222222222";
	private Integer DESIR_NUM = 2;
	private Integer GRAB_SEASON_STATE_PUBLISHED = 200;
	private Long USERNAME_LEADER_ORDERFARMER = 13073537158L;
	private Long USERNAME_DRIVER = 18888888888L;
	private int AUTHENTICATED_DRIVER = 20;
	private int REQUEST_ACCEPTED = 300;
	private int POCKETLOG_TYPE_REFUND_INSURANCE = 70;
	private int REQUEST_WAITTING = 200;

	public static final Long USER_MACHINE_TYPE_ID = Long.MAX_VALUE;
	public static final Long USER_MACHINE_MODEL_ID = Long.MAX_VALUE;

	public static List<Long> allUser = new ArrayList<>();

	@Before
	public void Create() {

		userMachineTypeMapper.insert(MainTestDataCreator.createUserMachineType(USER_MACHINE_TYPE_ID, "收割机"));
		userMachineModelMapper.insert(MainTestDataCreator.createUserMachineModel(USER_MACHINE_MODEL_ID, USER_MACHINE_TYPE_ID));

		optionWorkingTypeMapper.insert(MainTestDataCreator.createOptionWorkingType());
		optionWorkingTypeMachineModelMappingMapper.insert(MainTestDataCreator.createOptionWorkingTypeMachineModelMapping(
				USER_MACHINE_MODEL_ID, MainTestDataCreator.DEFAULT_WORKING_TYPE_ID));
		allUser.add(USERNAME_LEADER);
		allUser.add(USERNAME_DRIVER);
		for (Long USERNAME : allUser) {
			userMachineMapper.insert(MainTestDataCreator.createUserMachine(USERNAME, USER_MACHINE_MODEL_ID));
		}

		OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(USERNAME_LEADER_ORDERFARMER, DESIR_NUM,
				System.currentTimeMillis(), ORDERID);
		orderFarmer.setDriver_insurance(1000);
		orderFarmerMapper.insert(orderFarmer);
		accountMapper.insert(MainTestDataCreator.createAccount(USERNAME_LEADER, UserService.DRIVER_LEADER_AUTHED));

		seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders(ORDERID, SEASONID));
		grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(GRAB_SEASON_STATE_PUBLISHED, SEASONID));

		orderGeoMapper.insert(MainTestDataCreator.createOrderGeo(ORDERID));

		accountMapper.insert(MainTestDataCreator.createAccount(USERNAME_DRIVER, AUTHENTICATED_DRIVER));

	}

	@After
	public void Destroy() {

		userMachineTypeMapper.deleteByPrimaryKey(USER_MACHINE_TYPE_ID);
		userMachineModelMapper.deleteByPrimaryKey(USER_MACHINE_MODEL_ID);
		UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
		userMachineCriteria.or().andUidEqualTo(USERNAME_LEADER);
		userMachineMapper.deleteByExample(userMachineCriteria);
		UserMachineCriteria userMachineCriteria2 = new UserMachineCriteria();
		userMachineCriteria2.or().andUidEqualTo(USERNAME_DRIVER);
		userMachineMapper.deleteByExample(userMachineCriteria2);

		optionWorkingTypeMapper.deleteByPrimaryKey(MainTestDataCreator.DEFAULT_WORKING_TYPE_ID);
		optionWorkingTypeMachineModelMappingMapper
				.deleteByPrimaryKey(MainTestDataCreator.createOptionWorkingTypeMachineModelMapping(USER_MACHINE_MODEL_ID,
						MainTestDataCreator.DEFAULT_WORKING_TYPE_ID));

		orderFarmerMapper.deleteByPrimaryKey(ORDERID);

		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(USERNAME_LEADER);
		accountMapper.deleteByExample(accountCriteria);
		AccountCriteria accountCriteria2 = new AccountCriteria();
		accountCriteria2.or().andUidEqualTo(USERNAME_DRIVER);
		accountMapper.deleteByExample(accountCriteria2);

		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
		seasonOrdersCriteria.or().andOrder_idEqualTo(ORDERID);
		seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);
		grabSeasonMapper.deleteByPrimaryKey(SEASONID);

		orderGeoMapper.deleteByPrimaryKey(ORDERID);

		TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
		teamInOrderCriteria.or().andOrder_idEqualTo(ORDERID);
		teamInOrderMapper.deleteByExample(teamInOrderCriteria);

		TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
		teamJoinRequestCriteria.or().andUidEqualTo(USERNAME_DRIVER);
		teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);

		OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
		orderInsuranceRecordCriteria.or().andUidEqualTo(USERNAME_DRIVER);
		orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);

		OrderInsuranceRecordCriteria orderInsuranceRecordCriteria2 = new OrderInsuranceRecordCriteria();
		orderInsuranceRecordCriteria2.or().andUidEqualTo(USERNAME_LEADER);
		orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria2);

		pocketMapper.deleteByPrimaryKey(USERNAME_LEADER);
		pocketMapper.deleteByPrimaryKey(USERNAME_DRIVER);

		PocketLogCriteria pocketlogCriteria = new PocketLogCriteria();
		pocketlogCriteria.or().andUidEqualTo(USERNAME_LEADER);
		pocketLogMapper.deleteByExample(pocketlogCriteria);
		pocketlogCriteria.clear();
		pocketlogCriteria.or().andUidEqualTo(USERNAME_DRIVER);
		pocketLogMapper.deleteByExample(pocketlogCriteria);

	}

	@Test
	public void followOrderMultiTimes() throws BusinessException {

		teamOrderBusiness.followOrder(USERNAME_LEADER, ORDERID);
		teamOrderBusiness.followOrder(USERNAME_LEADER, ORDERID);
		teamOrderBusiness.followOrder(USERNAME_LEADER, ORDERID);

	}

	@Test
	public void unfollowOrderMultiTimes() throws BusinessException {

		teamOrderBusiness.followOrder(USERNAME_LEADER, ORDERID);

		teamOrderBusiness.unfollowOrder(USERNAME_LEADER, ORDERID);
		teamOrderBusiness.unfollowOrder(USERNAME_LEADER, ORDERID);
		teamOrderBusiness.unfollowOrder(USERNAME_LEADER, ORDERID);
	}

	@Test
	public void createTeam_NotEnoughInsuranceLeader() {

		try {
			teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		} catch (BusinessException e) {
			assertEquals("余额不足", e.errorMessage);
		}

	}

	@Test
	public void createTeam_Normal() throws BusinessException {

		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(USERNAME_LEADER);
		Pocket pocket2 = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals(1000, (int) pocket2.getInsurance());
		assertEquals(500, (int) pocket2.getCurr_money());

		TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
		teamInOrderCriteria.or().andOrder_idEqualTo(ORDERID).andUidEqualTo(USERNAME_LEADER);
		assertEquals(1, teamInOrderMapper.selectByExample(teamInOrderCriteria).size());
	}

	@Test
	public void sendJoinOrderTeamRequest_DriverInsuranceNotEnough() {

		try {
			Long teamid;
			Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
			pocketMapper.insert(pocket);
			teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
			teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
		} catch (BusinessException e) {
			assertEquals("余额不足", e.errorMessage);
		}
	}

	@Test
	public void sendJoinOrderTeam_normal() throws BusinessException {
		Long teamid;
		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
		pocketMapper.insert(pocket2);
		teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
		TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
		teamJoinRequestCriteria.or().andUidEqualTo(USERNAME_DRIVER).andStateEqualTo(REQUEST_WAITTING);
		assertEquals(1, teamJoinRequestMapper.selectByExample(teamJoinRequestCriteria).size());
	}

	@Test
	public void sendJoinOrderTeamRequest_multiTimes() {
		try {
			Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
			pocketMapper.insert(pocket);
			Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
			pocketMapper.insert(pocket2);
			Long teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
			teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
			teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
			fail();
		} catch (BusinessException e) {
			assertEquals("您已经申请加入该队伍", e.errorMessage);
		}

	}

	@Test
	public void acceptTeamRequest_normal() throws BusinessException {
		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
		pocketMapper.insert(pocket2);
		Long teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
		teamOrderBusiness.acceptTeamRequest(teamid, USERNAME_DRIVER, USERNAME_LEADER);
		TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
		teamJoinRequestCriteria.or().andUidEqualTo(USERNAME_DRIVER).andStateEqualTo(REQUEST_ACCEPTED);
		List<TeamJoinRequest> list = new ArrayList<>();
		list = teamJoinRequestMapper.selectByExample(teamJoinRequestCriteria);
		assertEquals(1, list.size());
	}

	@Test
	public void acceptTeamRequest_InATeam() {
		try {
			Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
			pocketMapper.insert(pocket);
			Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
			pocketMapper.insert(pocket2);
			Long teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
			teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
			teamOrderBusiness.acceptTeamRequest(teamid, USERNAME_DRIVER, USERNAME_LEADER);
			teamOrderBusiness.acceptTeamRequest(teamid, USERNAME_DRIVER, USERNAME_LEADER);
		} catch (BusinessException e) {
			assertEquals("该请求已被接受", e.errorMessage);
		}

	}

	@Test
	public void denyTeamRequest_normal() throws BusinessException {
		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
		pocketMapper.insert(pocket2);
		Long teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
		teamOrderBusiness.denyTeamRequest(teamid, USERNAME_DRIVER, USERNAME_LEADER);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(USERNAME_DRIVER).andTypeEqualTo(POCKETLOG_TYPE_REFUND_INSURANCE);
		List<PocketLog> list = new ArrayList<>();
		list = pocketLogMapper.selectByExample(pocketLogCriteria);
		assertEquals(1, list.size());
	}

	@Test
	public void cancelTeamRequest_normal() throws BusinessException {
		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
		pocketMapper.insert(pocket2);
		Long teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
		teamOrderBusiness.cancelTeamRequest(teamid, USERNAME_DRIVER);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(USERNAME_DRIVER).andTypeEqualTo(POCKETLOG_TYPE_REFUND_INSURANCE);
		List<PocketLog> list = new ArrayList<>();
		list = pocketLogMapper.selectByExample(pocketLogCriteria);
		assertEquals(1, (int) list.size());
	}

	@Test
	public void denyAndcancel() {
		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
		pocketMapper.insert(pocket2);
		Long teamid;
		try {
			teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
			teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
			teamOrderBusiness.denyTeamRequest(teamid, USERNAME_DRIVER, USERNAME_LEADER);
			teamOrderBusiness.cancelTeamRequest(teamid, USERNAME_DRIVER);
		} catch (BusinessException e) {
			assertEquals("您的请求已经取消或被拒绝", e.errorMessage);
		}

	}

	@Test
	public void deleteOrderTeam_normal() throws BusinessException {

		Pocket pocket = MainTestDataCreator.createPocket(USERNAME_LEADER, 1500);
		pocketMapper.insert(pocket);
		Pocket pocket2 = MainTestDataCreator.createPocket(USERNAME_DRIVER, 1500);
		pocketMapper.insert(pocket2);

		Long teamid = teamOrderBusiness.createOrderTeam(ORDERID, USERNAME_LEADER, "圈圈最强队伍");
		teamOrderBusiness.sendJoinOrderTeamRequest(teamid, USERNAME_DRIVER, "请求入队");
		teamOrderBusiness.acceptTeamRequest(teamid, USERNAME_DRIVER, USERNAME_LEADER);
		teamOrderBusiness.deleteOrderTeam(teamid, USERNAME_LEADER);

		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(USERNAME_LEADER).andTypeEqualTo(POCKETLOG_TYPE_REFUND_INSURANCE);
		List<PocketLog> list = new ArrayList<>();
		list = pocketLogMapper.selectByExample(pocketLogCriteria);
		assertEquals(1, list.size());

		pocketLogCriteria.clear();
		pocketLogCriteria.or().andUidEqualTo(USERNAME_DRIVER).andTypeEqualTo(POCKETLOG_TYPE_REFUND_INSURANCE);
		List<PocketLog> listForDriver = new ArrayList<>();
		listForDriver = pocketLogMapper.selectByExample(pocketLogCriteria);
		assertEquals(1, list.size());

	}
}
