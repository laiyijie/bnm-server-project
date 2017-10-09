package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional
/**
 * Created by lucq on 2017/6/9.
 */
public class MachineControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@Before
	public void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void Test_machinesTypeGet() throws Exception {
		mockMvc.perform(get("/api/v1//machines/type").accept(MediaType.APPLICATION_JSON)
															 .sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andDo(print());
	}

	//@Test
	public void Test_machinesTypeTypeIdModelBrandGet() throws Exception {
		mockMvc.perform(get("/api/v1//machines/type/3/model/brand").accept(MediaType.APPLICATION_JSON)
													 .sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andDo(print());

	}

	//@Test
	public void Test_machinesModelBrandBrandnameNumGet() throws Exception {
		mockMvc.perform(get("/api/v1//machines/model/brand/"+ URLEncoder.encode("约翰迪尔","utf-8")+"/num").accept(MediaType.APPLICATION_JSON)
																					 .sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andDo(print());
		mockMvc.perform(get("/api/v1//machines/model/brand?brandname="+ URLEncoder.encode("约翰迪尔","utf-8")).accept(MediaType
				.APPLICATION_JSON)
																									   .sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andDo(print());
	}

	//@Test
	public void Test_machinesModelBrandBrandnameNumBrandnumGet() throws Exception {
		mockMvc.perform(get("/api/v1//machines/model/brand/"+ URLEncoder.encode("东华","utf-8")+"/num/"+ URLEncoder.encode("4LZ-5.5Z",
				"utf-8"))
				.accept(MediaType.APPLICATION_JSON)
				.sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			    .andDo(print())
			    .andExpect(status().isOk());
		mockMvc.perform(get("/api/v1//machines/model/brand/num?brandname="+ URLEncoder.encode("东华","utf-8")+"&brandnum="+ URLEncoder.encode
				("4LZ-5.5Z","utf-8"))
				.accept(MediaType.APPLICATION_JSON)
				.sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			   .andDo(print())
			   .andExpect(status().isOk());
	}
	//@Test
	public void Test_userMachinesMachineIDGet() throws Exception {

		mockMvc.perform(get("/api/v1//user/machines/1490418823756258")
				.accept(MediaType.APPLICATION_JSON)
				.sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
			    .andDo(print())
			    .andExpect(status().isOk());
	}




}
