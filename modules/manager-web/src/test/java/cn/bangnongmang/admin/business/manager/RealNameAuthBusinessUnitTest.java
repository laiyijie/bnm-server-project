package cn.bangnongmang.admin.business.manager;

import cn.bangnongmang.admin.swagger.model.RealNameAuthDetail;
import cn.bangnongmang.admin.swagger.model.UserBasic;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.dao.*;

import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.server.test.MainTestDataCreator;
import cn.bangnongmang.service.service.UserService;
import com.alibaba.fastjson.JSON;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.soap.SOAPBinding;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wenxx on 2017/5/23.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional
public class RealNameAuthBusinessUnitTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private RealNameAuthBusiness testBusiness;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    @Autowired
    private AccountGeoMapper accountGeoMapper;
    @Autowired
    private StarUserMapper starUserMapper;
    @Autowired
    private AccountPortraitMapper accountPortraitMapper;
    private Long USERA = 18334741666L;
    private MockMvc mockMvc;
    @Before
    public void Before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        accountMapper.insert(MainTestDataCreator.createAccount(USERA,40));
        accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(USERA,300,"JJY"));
        accountGeoMapper.insert(MainTestDataCreator.createAccountGeo(USERA));
        starUserMapper.insert(MainTestDataCreator.createStarUserMapper(USERA));
        accountPortraitMapper.insert(MainTestDataCreator.createAccountPortrait(USERA));
    }

    @Test
    public void realNameAuthsGetTestNormal() throws Exception{
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/realNameAuths").param("type" ,"WAITING_AUTH").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        List<UserBasic> userBasics = JSON.parseArray( mvcResult.getResponse().getContentAsString(),UserBasic.class);

        assertEquals("183****1666",userBasics.get(0).getPhoneStar());
        assertEquals(UserBasic.RoleEnum.DRIVER,userBasics.get(0).getRole());
        assertEquals(UserBasic.AuthedEnum.WAITING_AUTH,userBasics.get(0).getAuthed());
    }

    @Test
    public void realNameAuthsUidGetTestNormal() throws Exception{
        MvcResult mvcResult=mockMvc.perform(get("/api/v1/realNameAuths/18334741666").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        RealNameAuthDetail realNameAuthDetail=JSON.parseObject(mvcResult.getResponse().getContentAsString(),RealNameAuthDetail.class);
        assertEquals("18334741666",realNameAuthDetail.getPhone());
        assertEquals("183****1666",realNameAuthDetail.getPhoneStar());
        assertEquals("未知",realNameAuthDetail.getSex());
    }

    @Test
    public void acceptRealNameAuthTest ()throws Exception{
        mockMvc.perform(post("/api/v1/realNameAuths/18334741666/status/accept").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        AccountRealNameAuth accountRealNameAuth=accountRealNameAuthMapper.selectByPrimaryKey(18334741666L);
        assertEquals(UserService.REAL_NAME_AUTH_STATE_PASS,accountRealNameAuth.getState());
    }

    @Test
    public void acceptRealNameAuthNullTest()throws Exception{
        mockMvc.perform(post("/api/v1/realNameAuths/18334741661/status/accept").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed())
                .andDo(print());
    }

    @Test
    public void denyRealNameAuthTest ()throws Exception{
        mockMvc.perform(post("/api/v1/realNameAuths/18334741666/status/deny").param("reason","太丑").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        AccountRealNameAuth accountRealNameAuth=accountRealNameAuthMapper.selectByPrimaryKey(18334741666L);
        assertEquals(UserService.REAL_NAME_AUTH_STATE_INIT,accountRealNameAuth.getState());
        assertEquals("太丑",accountRealNameAuth.getFailed_reason());
    }

    @Test
    public void denyRealNameAuthNullTest ()throws Exception{
        mockMvc.perform(post("/api/v1/realNameAuths/18334741661/status/deny").param("reason","太丑").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed())
                .andDo(print());
    }
}
