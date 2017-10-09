package cn.bangnongmang.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })
public class AccountControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvcc;

	private MockMvc mockMvc;


	@Before
	public void before() {

		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)

				.build();


	}

	// @Test
	// public void testDecrypt() throws UnsupportedEncodingException, Exception{
	//
	// tunnel.setSecretKeyFromString("rO0ABXNyAB9qYXZheC5jcnlwdG8uc3BlYy5TZWNyZXRLZXlTcGVjW0cLZuIwYU0CAAJMAAlhbGdvcml0aG10ABJMamF2YS9sYW5nL1N0cmluZztbAANrZXl0AAJbQnhwdAADQUVTdXIAAltCrPMX+AYIVOACAAB4cAAAABAxjxBLBo+doz7kHUi8Ydi4");
	// String s = tunnel.getSecretKeyToString();
	// System.out.println(s);
	// String string =
	// mockMvc.perform(post("/account/getLoginText").contentType(MediaType.APPLICATION_JSON).content("{\"params\":\""+
	// tunnel.putIn("123456")+"\"}"))
	// .andExpect(status().isOk())
	// .andReturn()
	// .getResponse()
	// .getContentAsString();
	//
	// System.out.println(string);
	//
	// }

	@Test
	public void testRegister() throws Exception {

		mockMvc.perform(get("/info/test").header("User-Agent", "driverapp"));

	}

	@Test
	public void testUser() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		mockMvc.perform(post("/api/v1/auths/loginTexts/18801902298").headers(httpHeaders)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print());
	}

}
