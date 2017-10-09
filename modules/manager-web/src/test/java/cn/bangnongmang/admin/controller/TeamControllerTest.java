package cn.bangnongmang.admin.controller;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.OrderFarmer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lucq on 2017/6/23.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional()
public class TeamControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void before(){
		mockMvc=MockMvcBuilders.webAppContextSetup(this.wac)
								 .build();

	}
	//@Test
	public void Test_teamGet() throws Exception {
		HttpHeaders httpHeaders=new HttpHeaders();

		mockMvc.perform(get("/api/v1/team").headers(httpHeaders).sessionAttr("username", "1234456789").param("orderId","201704230318260823640466"))
			   .andDo(print());

	}
	@Test
	public void Test_teamTeamIdGet() throws Exception {
		HttpHeaders httpHeaders=new HttpHeaders();

		mockMvc.perform(get("/api/v1/team/1493112892493227").headers(httpHeaders).sessionAttr("username", "1234456789"))
			   .andDo(print());
	}
	public void Test() throws Exception {
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		mockMvc.perform(put("/api/v1/team/222222").headers(httpHeaders).sessionAttr("username", "1234456789").content("uid=12345712345"))
			   .andDo(print());
	}
}
