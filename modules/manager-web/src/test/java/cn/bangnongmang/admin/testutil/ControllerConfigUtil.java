package cn.bangnongmang.admin.testutil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 *  Description: 
 *  Controller层测试        测试类可以继承此类。
 *  @author luchangquan  DateTime 2017年4月7日 下午1:25:17 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 *  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })

public class ControllerConfigUtil{
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private MockHttpSession session;
	private MockMvc mockMvc;

	public MockHttpSession getSession() {
		return getInstanceSession();
	}

	public void setSession(MockHttpSession session) {
		this.session = session;
	}

	public MockMvc getMockMvc() {
		return getInstanceMockMvc();
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	public MockHttpSession getInstanceSession() {

		synchronized (MockHttpSession.class) {
			if (session == null) {
				session = new MockHttpSession();
			}
		}
		return session;
	}

	public MockMvc getInstanceMockMvc() {

		synchronized (MockMvc.class) {
			if (mockMvc == null) {
				mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
			}
		}
		return mockMvc;
	}

	@Before
	public void before() {
		
		try {
			getMockMvc().perform(post("/login.do").session(getSession()).param("username", "shixiaoxue")
					.param("password", "1076f90dbb9f4e9dbe30c541710d1f1f1eee5e24")).andExpect(status().isOk())
					.andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test(){
		
	}
//	private static volatile ControllerConfigUtil instance = null;
//
//	public static ControllerConfigUtil getInstance() {
//		synchronized (ControllerConfigUtil.class) {
//			if (instance == null) {
//				instance = new ControllerConfigUtil();
//			}
//		}
//		return instance;
//	}
//
//	public ControllerConfigUtil() {
//	}



}
