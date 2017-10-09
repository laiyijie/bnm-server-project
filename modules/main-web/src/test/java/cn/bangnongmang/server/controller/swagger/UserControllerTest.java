package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.service.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    AccountRealNameAuthMapper accountRealNameAuthMapper;
    private MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        AccountRealNameAuth accountRealNameAuth = new AccountRealNameAuth();
        accountRealNameAuth.setState(UserService.REAL_NAME_AUTH_STATE_INIT);
        accountRealNameAuth.setDown_side("url/downSide");
        accountRealNameAuth.setUp_side("url/upSide");
        accountRealNameAuth.setFailed_reason("test");
        accountRealNameAuth.setReal_name("hanchunyu");
        accountRealNameAuth.setId_card_number("1234567890");
        accountRealNameAuth.setUpdate_time(1L);
        accountRealNameAuth.setUid(1L);
        accountRealNameAuthMapper.insert(accountRealNameAuth);
    }
    @Test
    public void authsAutoUsernameGetTestNormal() throws Exception {

        mockMvc.perform(get("/api/v1//user/realNameAuthInfo").accept(MediaType.APPLICATION_JSON)
                .sessionAttr("uid",1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

}
