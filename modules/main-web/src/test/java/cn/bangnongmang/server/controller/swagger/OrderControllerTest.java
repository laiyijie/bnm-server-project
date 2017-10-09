package cn.bangnongmang.server.controller.swagger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import cn.bangnongmang.data.dao.AccountGeoMapper;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountPortraitMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.OrderFarmerWorkSizeMapper;
import cn.bangnongmang.data.dao.OrderGeoMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.dao.StarUserMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.TeamJoinRequest;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional
public class OrderControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private GrabSeasonMapper grabSeasonMapper;
	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private OrderGeoMapper orderGeoMapper;
	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;
	@Autowired
	private OrderDriverMapper orderDriverMapper;
	@Autowired
	private AccountRealNameAuthMapper accountRealNameAuthMapper;
	@Autowired
	private AccountMapper accountmapper;
	@Autowired
	private AccountGeoMapper accountGeoMapper;
	@Autowired
	private AccountPortraitMapper accountPortraitMapper;
	@Autowired
	private StarUserMapper starUserMapper;
	@Autowired
	private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;
	@Autowired
	private TeamJoinRequestMapper teamJoinRequestMapper;
	@Autowired
	private TeamInOrderMapper teamInOrderMapper;
	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;
	private MockMvc mockMvc;
	private Long USERNAMEORDERFARMER = 13073537158L;
	private Long DRIVERUSERNAME = 18334741666L;
	private Long TEAMMEMBER = 1333333333L;
	private String ORDERID = "111";
	private Long TEAMID = 111L;
	private String SEASONID = "1";
	private OrderFarmer orderFarmer;
	private OrderFarmerWorkSize orderFamerWorkSize;

	@Before
	public void Before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(200, SEASONID));
		seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders(ORDERID, SEASONID));
		orderFarmer = MainTestDataCreator.createOrderFarmer(USERNAMEORDERFARMER, 2, 0L, ORDERID);
		orderFarmer.setDriver_leader(DRIVERUSERNAME);
		orderFarmer.setState("FARMER_WAITTING_DISTRIBUTE");
		orderFarmerMapper.insert(orderFarmer);
		orderGeoMapper.insert(MainTestDataCreator.createOrderGeo(ORDERID));
		optionWorkingTypeMapper.insert(MainTestDataCreator.createOptionWorkingType());

		accountmapper.insert(MainTestDataCreator.createAccount(DRIVERUSERNAME, 40));
		accountGeoMapper.insert(MainTestDataCreator.createAccountGeo(DRIVERUSERNAME));
		accountPortraitMapper.insert(MainTestDataCreator.createAccountPortrait(DRIVERUSERNAME));
		accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(DRIVERUSERNAME, 400, "bobo"));
		starUserMapper.insert(MainTestDataCreator.createStarUserMapper(DRIVERUSERNAME));

		orderFamerWorkSize = MainTestDataCreator.createOrderFarmerWorkSize(ORDERID, 200.00, 300);
		orderFarmerWorkSizeMapper.insert(orderFamerWorkSize);
	}

	@Test
	public void ordersGetNormal() throws Exception {

		mockMvc.perform(get("/api/v1/orders").accept(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666")
											 .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void ordersOrderIdGetNormal() throws Exception {

		mockMvc.perform(get("/api/v1/orders/111").accept(MediaType.APPLICATION_JSON)
												 .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());

	}

	@Test
	public void ordersOrderIdMembersGetNormal() throws Exception {
		orderDriverMapper.insert(MainTestDataCreator.createOrderDriver(ORDERID, DRIVERUSERNAME, "DRIVER_STATE_GOT"));

		mockMvc.perform(get("/api/v1/orders/111/members").accept(MediaType.APPLICATION_JSON)
														 .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
	}

	@Test
	public void ordersOrderIdStatusDistributeWorkPutNormal() throws Exception {

		mockMvc.perform(post("/api/v1/orders/111/status/distributeWork").accept(MediaType.APPLICATION_JSON)
																	   .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666")
																	   .content("[{\"uid\":123,\"size\":\"100\"},{\"uid\":348," +
																			   "\"size\":\"100\"}]")
																	   .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void ordersOrderIdStatusFinishOneDayWorkPutNormal() throws Exception {

		orderFamerWorkSize.setState(200);
		orderFarmerWorkSizeMapper.updateByPrimaryKey(orderFamerWorkSize);
		orderFarmer.setState("FARMER_WORKING");
		orderFarmerMapper.updateByPrimaryKey(orderFarmer);
		mockMvc.perform(post("/api/v1/orders/111/status/finishOneDayWork").accept(MediaType.APPLICATION_JSON)
																		 .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").param("totalSize", "200")
																		 .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void ordersOrderIdStatusGotPutNormal() throws Exception {

		TeamJoinRequest teamJoinRequest = MainTestDataCreator.createTeamJoinRequest(1L, TEAMID, 1L, "张三");
		teamJoinRequest.setState(300);
		teamJoinRequestMapper.insert(teamJoinRequest);
		teamInOrderMapper.insert(MainTestDataCreator.createTeamInOrder(TEAMID, ORDERID, DRIVERUSERNAME, "圈圈最强队伍"));
		orderFamerWorkSize.setState(200);
		orderFarmerWorkSizeMapper.updateByPrimaryKey(orderFamerWorkSize);
		orderFarmer.setState("FARMER_WAITTING_GOT");
		orderFarmerMapper.updateByPrimaryKey(orderFarmer);
		mockMvc.perform(post("/api/v1/orders/111/status/got").accept(MediaType.APPLICATION_JSON)
															.sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").param("teamId", "111").contentType(MediaType.APPLICATION_JSON))
			   .andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void ordersOrderIdStatusReadyToWorkPutNormal() throws Exception {
		orderFarmer.setState("FARMER_GOT");
		orderFarmerMapper.updateByPrimaryKey(orderFarmer);
		mockMvc.perform(post("/api/v1/orders/111/status/readyToWork").accept(MediaType.APPLICATION_JSON)
																	.sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
	}

	@Test
	public void ordersOrderIdSubOrdersStatusNoOneWorkingGetNormal() throws Exception {
		orderDriverMapper.insert(MainTestDataCreator.createOrderDriver("111", DRIVERUSERNAME, "ORDER_DRIVER_STATE_STOPPED"));
		mockMvc.perform(get("/api/v1/orders/111/subOrders/status/noOneWorking").accept(MediaType.APPLICATION_JSON)
																			   .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
	}

	@Test
	public void ordersOrderIdTeamsGetNormal() throws Exception {
		orderFarmer.setState("FARMER_WAITTING_GOT");
		orderFarmerMapper.updateByPrimaryKey(orderFarmer);
		teamInOrderMapper.insert(MainTestDataCreator.createTeamInOrder(TEAMID, ORDERID, DRIVERUSERNAME, "圈圈最强队伍"));
		TeamJoinRequest teamJoinRequest = MainTestDataCreator.createTeamJoinRequest(1L, TEAMID, TEAMMEMBER, "张三");
		teamJoinRequest.setState(300);
		teamJoinRequestMapper.insert(teamJoinRequest);

		accountmapper.insert(MainTestDataCreator.createAccount(TEAMMEMBER, 40));
		accountGeoMapper.insert(MainTestDataCreator.createAccountGeo(TEAMMEMBER));
		accountPortraitMapper.insert(MainTestDataCreator.createAccountPortrait(TEAMMEMBER));
		accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(TEAMMEMBER, 400, "DADA"));
		starUserMapper.insert(MainTestDataCreator.createStarUserMapper(TEAMMEMBER));

		mockMvc.perform(get("/api/v1/orders/111/teams").accept(MediaType.APPLICATION_JSON)
													   .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").contentType(MediaType.APPLICATION_JSON)).andDo(print())
			   .andExpect(status().isOk());
	}

	@Test
	public void ordersOrderIdWorkSizesGetNormal() throws Exception {
		mockMvc.perform(get("/api/v1/orders/111/workSizes").accept(MediaType.APPLICATION_JSON)
														   .sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666").contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
	}
}
