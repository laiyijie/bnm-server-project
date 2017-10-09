package cn.bangnongmang.server.controller.swagger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import cn.bangnongmang.data.domain.PhoneVerifyCriteria;
import org.apache.commons.codec.digest.DigestUtils;
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

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.PhoneVerifyMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.PhoneVerify;
import cn.bangnongmang.server.test.MainTestDataCreator;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional

public class AuthsControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PhoneVerifyMapper phoneVerifyMapper;

    private MockMvc mockMvc;

    private Long USERNAME = 18334741666L;

    private String innerAuthCode;

    @Before
    public void before() {
        PhoneVerifyCriteria phoneVerifyCriteria = new PhoneVerifyCriteria();
        phoneVerifyCriteria.or()
                           .andPhonenumberEqualTo(String.valueOf(USERNAME));
        phoneVerifyMapper.deleteByExample(phoneVerifyCriteria);
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                                 .build();

        PhoneVerify phoneVerify = new PhoneVerify();
        phoneVerify.setPhonenumber(String.valueOf(USERNAME));
        phoneVerify.setSend_time(System.currentTimeMillis() / 1000);
        phoneVerify.setVerify_code("0000");
        phoneVerifyMapper.insert(phoneVerify);

    }

    @Test
    public void authsAutoUsernameGetTestNormal() throws Exception {
        Account account = MainTestDataCreator.createAccount(USERNAME, 20);
        account.setSalt_string("1111");
        innerAuthCode = DigestUtils.sha1Hex(account.getSalt_string() + account.getPassword());
        accountMapper.insert(account);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(get("/api/v1/auths/auto/18334741666").headers(httpHeaders)
                                                             .param("authData", innerAuthCode)
                                                             .param("machineId", "1111"))
               .andExpect(status().isOk())
               .andDo(print());

    }

    @Test
    public void authsAutoUsernameGetTestForbidden() throws Exception {
        Account account = MainTestDataCreator.createAccount(USERNAME, 20);
        account.setSalt_string("1111");
        innerAuthCode = DigestUtils.sha1Hex(account.getSalt_string() + account.getPassword());
        accountMapper.insert(account);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(get("/api/v1/auths/auto/18334741666").headers(httpHeaders)
                                                             .param("authData", "0000")
                                                             .param("machineId", "1111"))
               .andExpect(status().is(417))
               .andDo(print());

    }

    @Test
    public void authsDriversUsernamePostNormal() throws Exception {

        mockMvc.perform(post("/api/v1/auths/drivers/18334741666").accept(MediaType.APPLICATION_JSON)
                                                                 .contentType(
                                                                         MediaType.APPLICATION_JSON)
                                                                 .param("authCode", "0000")
                                                                 .param("machineId", "1111"))
               .andExpect(status().isOk())
               .andDo(print());
    }

	/*
     * @Test public void authsLoginTextsUsernamePostNormal() throws Exception {
	 * Account account = MainTestDataCreator.createAccount(USERNAME, 20);
	 * account.setSalt_string("1111"); innerAuthCode =
	 * DigestUtils.sha1Hex(account.getSalt_string() + account.getPassword());
	 * accountMapper.insert(account);
	 * 
	 * mockMvc.perform(post("/api/v1/auths/loginTexts/18334741666").accept(
	 * MediaType.APPLICATION_JSON)
	 * .contentType(MediaType.APPLICATION_JSON).param("client",
	 * "driverapp")).andExpect(status().isOk()) .andDo(print()); }
	 */

    @Test
    public void authsUsernameGetNormal() throws Exception {
        Account account = MainTestDataCreator.createAccount(USERNAME, 20);
        account.setSalt_string("1111");
        innerAuthCode = DigestUtils.sha1Hex(account.getSalt_string() + account.getPassword());
        accountMapper.insert(account);

        mockMvc.perform(get("/api/v1/auths/18334741666").accept(MediaType.APPLICATION_JSON)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .param("authCode", "0000")
                                                        .param("machineId", "1111"))
               .andExpect(status().isOk())
               .andDo(print());
    }

}
