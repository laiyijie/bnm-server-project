package cn.bangnongmang.server.business.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.data.domain.TeamJoinRequestCriteria;
import cn.bangnongmang.server.business.common.TeamOrderBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.GrabSeasonService;
import cn.bangnongmang.service.service.TeamOrderService;
import cn.bangnongmang.service.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverTeamOrderBusinessTest {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private TeamInOrderMapper teamInOrderMapper;
	@Autowired
	private TeamJoinRequestMapper teamJoinRequestMapper;

	@Autowired
	private TeamOrderBusiness driverTeamOrderBusiness;
	@Autowired
	private GrabSeasonMapper grabSeasonMapper;
	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;

	private static Long ID_GENERATOR = 100L;

	private Long farmerPhone = 18801902299L;
	private Long leaderPhone = 18801902230L;
	private Long memberPhone = 18801902231L;
	private String testFatherOrderID = "testst11111";
	private String testMsg = "testMessage";
	private String seasonId = "test_seasonId";

	@Before
	public void create() {
		accountMapper.insert(MainTestDataCreator.createAccount(farmerPhone, UserService.FARMER));
		Account leader = MainTestDataCreator.createAccount(leaderPhone, UserService.AUTHENTICATED_DRIVER);
		leader.setDriver_leader(UserService.DRIVER_LEADER_AUTHED);
		accountMapper.insert(leader);
		accountMapper.insert(MainTestDataCreator.createAccount(memberPhone, UserService.AUTHENTICATED_DRIVER));
		grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(GrabSeasonService.STATE_PUBLISHED, seasonId));
		seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders(testFatherOrderID, seasonId));
		OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(farmerPhone, 10, 0L, testFatherOrderID);
		orderFarmerMapper.insert(orderFarmer);
	}

	@After
	public void destroy() {
		orderFarmerMapper.deleteByPrimaryKey(testFatherOrderID);

		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(farmerPhone);
		accountCriteria.or().andUidEqualTo(leaderPhone);
		accountCriteria.or().andUidEqualTo(memberPhone);

		accountMapper.deleteByExample(accountCriteria);

		grabSeasonMapper.deleteByPrimaryKey(seasonId);
		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
		seasonOrdersCriteria.or().andSeason_idEqualTo(seasonId);
		seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);
		TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
		teamJoinRequestCriteria.or().andUidEqualTo(leaderPhone);
		teamJoinRequestCriteria.or().andUidEqualTo(farmerPhone);
		teamJoinRequestCriteria.or().andUidEqualTo(memberPhone);
		teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);
	}



	public Long createTeam() {
		Long teamId = ID_GENERATOR++;
		teamInOrderMapper.insert(MainTestDataCreator.createTeamInOrder(teamId, testFatherOrderID, leaderPhone, testMsg));
		return teamId;
	}

	public void deleteTeam(Long teamId) {
		teamInOrderMapper.deleteByPrimaryKey(teamId);
	}



	public Long createRequest(Long teamId) {
		Long requestId = ID_GENERATOR++;
		teamJoinRequestMapper.insert(MainTestDataCreator.createTeamJoinRequest(requestId, teamId, memberPhone, testMsg));
		return requestId;
	}

	public void deleteRequset(Long requestId) {
		teamJoinRequestMapper.deleteByPrimaryKey(requestId);
	}

	@Test
	public void TC103_acceptJoinOrderTeamRequest() throws BusinessException {

		Long teamId1 = createTeam();
		Long teamId2 = createTeam();
		Long requestId1 = createRequest(teamId1);
		Long requestId2 = createRequest(teamId2);

		try {

			driverTeamOrderBusiness.acceptTeamRequest(teamId1, memberPhone, leaderPhone);

			TeamJoinRequest teamJoinRequest1 = teamJoinRequestMapper.selectByPrimaryKey(requestId1);
			TeamJoinRequest teamJoinRequest2 = teamJoinRequestMapper.selectByPrimaryKey(requestId2);

			assertTrue(TeamOrderService.REQUEST_ACCEPTED == teamJoinRequest1.getState());
			assertTrue(null == teamJoinRequest2);

		} finally {
			deleteTeam(teamId1);
			deleteTeam(teamId2);
			deleteRequset(requestId1);
			deleteRequset(requestId2);
		}

	}

	@Test
	public void TC104_denyJoinOrderTeamRequest() throws BusinessException {

		Long teamId = createTeam();
		Long requestId = createRequest(teamId);

		try {

			driverTeamOrderBusiness.denyTeamRequest(teamId, memberPhone, leaderPhone);

			TeamJoinRequest teamJoinRequest1 = teamJoinRequestMapper.selectByPrimaryKey(requestId);

			assertTrue(null == teamJoinRequest1);

		} finally {
			deleteTeam(teamId);
			deleteRequset(requestId);
		}

	}

	@Test
	public void TC105_cancelJoinOrderTeamRequest() throws BusinessException {

		Long teamId = createTeam();
		Long requestId = createRequest(teamId);

		try {

			driverTeamOrderBusiness.cancelTeamRequest(teamId, memberPhone);

			TeamJoinRequest teamJoinRequest1 = teamJoinRequestMapper.selectByPrimaryKey(requestId);

			assertTrue(null == teamJoinRequest1);

		} finally {
			deleteTeam(teamId);
			deleteRequset(requestId);
		}

	}

	@Test
	public void TC106_delateJoinOrderTeamRequest() throws BusinessException {

		Long teamId = createTeam();
		Long requestId = createRequest(teamId);

		try {

			driverTeamOrderBusiness.deleteTeamRequest(teamId, memberPhone, "fuck", leaderPhone);

			TeamJoinRequest teamJoinRequest1 = teamJoinRequestMapper.selectByPrimaryKey(requestId);

			assertTrue(null == teamJoinRequest1);

		} finally {
			deleteTeam(teamId);
			deleteRequset(requestId);
		}

	}

}
