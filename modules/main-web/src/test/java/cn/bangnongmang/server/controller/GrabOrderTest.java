package cn.bangnongmang.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.exceptions.MySQLTransactionRollbackException;

import cn.bangnongmang.driverapp.models.OrderFarmerStateName;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.TeamOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })
public class GrabOrderTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private volatile Integer count = 1;
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private TeamInOrderMapper teamInOrderMapper;
	@Autowired
	private OrderDriverMapper orderDriverMapper;
	@Autowired
	private GrabSeasonMapper grabSeasonMapper;
	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserWorkCalendarMapper userWorkCalendarMapper;

	@Autowired
	private TeamJoinRequestMapper teamJoinRequestMapper;
	private static final Long TEST_FARMER_PHONE = 18336221172L;
	private static final Long TEST_DRIVER_PHONE = 15738397703L;

	private static final Long[] TEST_DRIVER_MEMBERS = { 18801902301L, 18801902302L, 18801902303L };

	private static final String TEST_FARMER_ORDER_ID = "test_farmer_order_for_order_business";

	private static final Long TEST_TEAM_IN_ORDER_ID = 91225555000L;

	private static final String TEST_CURRENT_GRAB_SEASON_ID = "test_season_id";

	public static List<Long> allUser = new ArrayList<>();

	@BeforeClass
	public static void init() {
		allUser.addAll(Arrays.stream(TEST_DRIVER_MEMBERS).collect(Collectors.toList()));
		allUser.add(TEST_FARMER_PHONE);
		allUser.add(TEST_DRIVER_PHONE);


	}
	@Before
	public void setup() {
		try {

			accountMapper.insert(MainTestDataCreator.createAccount(TEST_DRIVER_PHONE, 20));
			OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_PHONE, 5,
					System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
			orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_GOT);
			orderFarmer.setDesire_num(3);
			orderFarmerMapper.insert(orderFarmer);

			TeamInOrder teamInOrder = MainTestDataCreator.createTeamInOrder(TEST_TEAM_IN_ORDER_ID, TEST_FARMER_ORDER_ID,
					TEST_DRIVER_PHONE, "碉堡了的队伍");
			teamInOrderMapper.insert(teamInOrder);

			TeamJoinRequest teamJoinRequest = MainTestDataCreator.createTeamJoinRequest(
					System.currentTimeMillis() * 1000 + new Random().nextInt(1000), 91225555000L, 13764688806L, "救命");
			teamJoinRequest.setState(TeamOrderService.REQUEST_ACCEPTED);
			teamJoinRequestMapper.insert(teamJoinRequest);

			teamJoinRequest = MainTestDataCreator.createTeamJoinRequest(
					System.currentTimeMillis() * 1000 + new Random().nextInt(1000), 91225555000L, 15298683730L, "救命");
			teamJoinRequest.setState(TeamOrderService.REQUEST_ACCEPTED);
			teamJoinRequestMapper.insert(teamJoinRequest);

			grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(OrderFarmerService.GRAB_SEASON_STATE_PUBLISHED,
					TEST_CURRENT_GRAB_SEASON_ID));
			seasonOrdersMapper
					.insert(MainTestDataCreator.createSeasonOrders(TEST_FARMER_ORDER_ID, TEST_CURRENT_GRAB_SEASON_ID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() {
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(TEST_DRIVER_PHONE);
		accountMapper.deleteByExample(accountCriteria);
		// 删除订单
		orderFarmerMapper.deleteByPrimaryKey(TEST_FARMER_ORDER_ID);
		OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
		orderDriverCriteria.or().andOrder_farmer_idEqualTo(TEST_FARMER_ORDER_ID);
		orderDriverMapper.deleteByExample(orderDriverCriteria);

		// 删除队伍和入队请求
		teamInOrderMapper.deleteByPrimaryKey(TEST_TEAM_IN_ORDER_ID);

		TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
		teamJoinRequestCriteria.or().andTeam_idEqualTo(TEST_TEAM_IN_ORDER_ID);
		teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);

		grabSeasonMapper.deleteByPrimaryKey(TEST_CURRENT_GRAB_SEASON_ID);
		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
		seasonOrdersCriteria.or().andSeason_idEqualTo(TEST_CURRENT_GRAB_SEASON_ID);
		seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);


		for (Long user : allUser) {
			UserWorkCalendarCriteria userWorkCalendarCriteria = new UserWorkCalendarCriteria();
			userWorkCalendarCriteria.or().andUidEqualTo(user);
			userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria);
		}
	}

	public class GrabOrderThread implements Runnable {

		private MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.alwaysExpect(status().isOk()).build();

		@Override
		public void run() {

			try {

				String requestBody = "{\"orderId\":\"test_farmer_order_for_order_business\", \"teamId\":91225555000}";
				AndroidRequest androidRequest = new AndroidRequest();
				androidRequest.params = requestBody;
				String requestJson = JSON.toJSONString(androidRequest);

				MvcResult andReturn = mockMvc.perform(
						post("/app/order/grabOrder").contentType(MediaType.APPLICATION_JSON).content(requestJson)
								.sessionAttr("username", "18321783963").header("User-agent", "driverapp"))
						.andExpect(status().isOk()).andDo(print()).andReturn();

				String forwardedUrl = andReturn.getResponse().getForwardedUrl();
				MockHttpServletResponse response = andReturn.getResponse();

				if (response.getHeaderNames().toString().equals("[Content-Type]")) {
					System.out.println(Thread.currentThread().getName() + "--->>>抢单成功----------" + count++);

				}

				if (forwardedUrl.trim().equals("/WEB-INF/views/error.jsp")) {
					System.out.println(Thread.currentThread().getName() + "--->>>抢单失败----------" + count++);

				}
				MySQLTransactionRollbackException ex=new MySQLTransactionRollbackException();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Test
	public void test_grabOrder() throws Exception {
		for (int i = 0; i < 5; i++) {
			new Thread(new GrabOrderThread()).start();
		}

		Thread.sleep(1000);
	}

}