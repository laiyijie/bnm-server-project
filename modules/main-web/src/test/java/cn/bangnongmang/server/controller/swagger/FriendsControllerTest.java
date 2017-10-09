package cn.bangnongmang.server.controller.swagger;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import cn.bangnongmang.data.dao.FriendshipMapper;
import cn.bangnongmang.data.dao.FriendshipRequestMapper;
import cn.bangnongmang.data.dao.FriendshipSpecialMapper;
import cn.bangnongmang.data.dao.StarUserMapper;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional

public class FriendsControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private FriendshipMapper friendshipMapper;
	@Autowired
	private FriendshipSpecialMapper friendshipSpecialMapper;
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
	private FriendshipRequestMapper friendshipRequestMapper;

	private Long USERA = 18334741666L;

	private Long USERB = 15738397703L;
	private MockMvc mockMvc;

	@Before
	public void before() {

		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		friendshipSpecialMapper.insert(MainTestDataCreator.createFriendshipSpecial(USERA, USERB));

		accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(USERB, 400, "quanquan"));
		accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(USERA, 400, "bobo"));

		accountmapper.insert(MainTestDataCreator.createAccount(USERB, 40));
		accountGeoMapper.insert(MainTestDataCreator.createAccountGeo(USERB));
		accountPortraitMapper.insert(MainTestDataCreator.createAccountPortrait(USERB));
		starUserMapper.insert(MainTestDataCreator.createStarUserMapper(USERB));

		accountmapper.insert(MainTestDataCreator.createAccount(USERA, 40));
		accountGeoMapper.insert(MainTestDataCreator.createAccountGeo(USERA));
		accountPortraitMapper.insert(MainTestDataCreator.createAccountPortrait(USERA));
		starUserMapper.insert(MainTestDataCreator.createStarUserMapper(USERA));

	}

	@Test
	public void friendsGetTestNormal() throws Exception {
		friendshipMapper.insert(MainTestDataCreator.createFriendship(USERA, USERB));
		mockMvc.perform(get("/api/v1/friends").accept(MediaType.APPLICATION_JSON)
											  .contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("quanquan")))
			   .andExpect(jsonPath("$[0].phone", is("15738397703"))).andDo(print());

	}

	@Test
	public void friendsRequestsGetNormal() throws Exception {
		friendshipRequestMapper.insert(MainTestDataCreator.creatFriendShipRequest(USERB, USERA));
		mockMvc.perform(get("/api/v1/friends/requests").accept(MediaType.APPLICATION_JSON)
													   .contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("quanquan"))).andDo(print())
			   .andExpect(jsonPath("$[0].uid", is(15738397703L))).andExpect(jsonPath("$[0].accepted", is(false)));

	}

	@Test
	public void friendsRequestsUsernameDeleteNormal() throws Exception {
		friendshipRequestMapper.insert(MainTestDataCreator.creatFriendShipRequest(USERB, USERA));
		mockMvc.perform(delete("/api/v1/friends/requests/15738397703").accept(MediaType.APPLICATION_JSON)
																	  .contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666")).andDo(print())
			   .andExpect(status().isOk());
	}

	@Test
	public void friendsRequestsPostNormal() throws Exception {

		mockMvc.perform(post("/api/v1/friends/requests").param("uid","15738397703") .accept(MediaType.APPLICATION_JSON)
																	.contentType(MediaType.APPLICATION_JSON).param("ps", "好友请求").sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk());
	}

	@Test
	public void friendsRequestsUsernamePostNormal() throws Exception {

		friendshipRequestMapper.insert(MainTestDataCreator.creatFriendShipRequest(USERB, USERA));
		mockMvc.perform(post("/api/v1/friends/requests/15738397703").accept(MediaType.APPLICATION_JSON)
																   .contentType(MediaType.APPLICATION_JSON).param("agree", "true").sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk());

	}

	@Test
	public void friendsRequestsUsernamePutRejected() throws Exception {

		friendshipRequestMapper.insert(MainTestDataCreator.creatFriendShipRequest(USERB, USERA));
		mockMvc.perform(post("/api/v1/friends/requests/15738397703").accept(MediaType.APPLICATION_JSON)
																   .contentType(MediaType.APPLICATION_JSON).param("agree", "false").sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isBadRequest());

	}

	@Test
	public void friendsSpecialsUsernameDeleteNormal() throws Exception {
		mockMvc.perform(delete("/api/v1/friends/specials/15738397703").accept(MediaType.APPLICATION_JSON)
																	  .contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk());
	}

	@Test
	public void friendsSpecialsUsernamePostNormal() throws Exception {
		mockMvc.perform(post("/api/v1/friends/specials/15738397703").accept(MediaType.APPLICATION_JSON)
																	.contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk());
	}

	@Test
	public void friendsUsernameDeleteNormal() throws Exception {
		friendshipMapper.insert(MainTestDataCreator.createFriendship(USERA, USERB));
		friendshipMapper.insert(MainTestDataCreator.createFriendship(USERB, USERA));
		mockMvc.perform(delete("/api/v1/friends/15738397703").accept(MediaType.APPLICATION_JSON)
															 .contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isOk());
	}

	@Test
	public void friendsUsernameGetNormal() throws Exception {
		mockMvc.perform(get("/api/v1/friends/15738397703").accept(MediaType.APPLICATION_JSON)
														  .contentType(MediaType.APPLICATION_JSON).sessionAttr("uid",18334741666L).sessionAttr("username", "18334741666"))
			   .andExpect(status().isNotFound()).andDo(print());
	}

}
