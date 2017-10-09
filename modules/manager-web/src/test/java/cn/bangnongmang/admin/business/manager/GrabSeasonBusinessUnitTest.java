package cn.bangnongmang.admin.business.manager;

import cn.bangnongmang.admin.io.swagger.show.GrabSeasonsShow;
import cn.bangnongmang.admin.swagger.model.GrabSeason;
import cn.bangnongmang.admin.swagger.model.OrderIdWrapper;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.domain.SeasonOrders;
import cn.bangnongmang.server.test.MainTestDataCreator;
import cn.bangnongmang.service.service.GrabSeasonService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wenxx on 2017/6/2.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional
public class GrabSeasonBusinessUnitTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private GrabSeasonMapper grabSeasonMapper;
    @Autowired
    private SeasonOrdersMapper seasonOrdersMapper;
    @Autowired
    private GrabSeasonBusiness grabSeasonBusiness;
    @Autowired
    private GrabSeasonsShow grabSeasonsShow;
    private MockMvc mockMvc;

    @Before
    public void Before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                                 .build();
        grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(100, "12345"));
        grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(200, "54321"));
        seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders("100", "12345"));
        seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders("101", "12345"));
        seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders("102", "12345"));
    }

    @Test
    public void grabSeasonsGetTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/grabSeasons").accept(MediaType.APPLICATION_JSON)
                                                                        .contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andDo(print())
                                     .andReturn();
        List<GrabSeason> grabSeasons = JSON.parseArray(mvcResult.getResponse()
                                                                .getContentAsString(), GrabSeason.class);
        assertEquals(GrabSeason.StateEnum.DRAFT, grabSeasons.get(0)
                                                            .getState());
        assertEquals(GrabSeason.StateEnum.PUBLISHED, grabSeasons.get(1)
                                                                .getState());
    }

    @Test
    public void grabSeasonsPostTest() throws Exception {
        mockMvc.perform(post("/api/v1/grabSeasons").param("startTime", "1000000")
                                                   .param("endTime", "1000001")
                                                   .param("ps", "abcdefg")
                                                   .accept(MediaType.APPLICATION_JSON)
                                                   .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
        List<GrabSeason> grabSeasons = grabSeasonsShow.getGrabSeasons();
        for (GrabSeason i : grabSeasons) {
            if (!i.getSeasonId()
                  .equals("12345") && !i.getSeasonId()
                                        .equals("54321")) {
                assertEquals(1000000L, (long) i.getStartTime());
                assertEquals(1000001L, (long) i.getEndTime());
                assertEquals("abcdefg", i.getPs());
            }
        }
    }

    @Test
    public void grabSeasonsSeasonIdDeleteTest() throws Exception {
        mockMvc.perform(delete("/api/v1/grabSeasons/12345").accept(MediaType.APPLICATION_JSON)
                                                           .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
        mockMvc.perform(delete("/api/v1/grabSeasons/1234567").accept(MediaType.APPLICATION_JSON)
                                                             .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isExpectationFailed())
               .andDo(print());
    }

    @Test
    public void grabSeasonsSeasonIdGetTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/grabSeasons/12345").accept(MediaType.APPLICATION_JSON)
                                                                              .contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andDo(print())
                                     .andReturn();
        GrabSeason grabSeason = JSON.parseObject(mvcResult.getResponse()
                                                          .getContentAsString(), GrabSeason.class);
        assertEquals(GrabSeason.StateEnum.DRAFT, grabSeason.getState());
        assertEquals("12345", grabSeason.getSeasonId());
        mockMvc.perform(get("/api/v1/grabSeasons/1234567").accept(MediaType.APPLICATION_JSON)
                                                          .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isExpectationFailed())
               .andDo(print());
    }

    @Test
    public void grabSeasonsSeasonIdPostTest() throws Exception {
        mockMvc.perform(post("/api/v1/grabSeasons/12345").param("startTime", "11111")
                                                         .param("endTime", "22222")
                                                         .param("ps", "DK")
                                                         .accept(MediaType.APPLICATION_JSON)
                                                         .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
        GrabSeason grabSeason = grabSeasonsShow.getGrabSeason("12345");
        assertEquals(11111L, (long) grabSeason.getStartTime());
        assertEquals(22222L, (long) grabSeason.getEndTime());
        assertEquals("DK", grabSeason.getPs());
        mockMvc.perform(post("/api/v1/grabSeasons/1234567").param("startTime", "11111")
                                                           .param("endTime", "22222")
                                                           .param("ps", "DK")
                                                           .accept(MediaType.APPLICATION_JSON)
                                                           .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isExpectationFailed())
               .andDo(print());
    }

    @Test
    public void grabSeasonsSeasonIdSeasonOrdersGetTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/grabSeasons/12345/seasonOrders").accept(MediaType.APPLICATION_JSON)
                                                                                           .contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andDo(print())
                                     .andReturn();
        List<String> orders = JSON.parseArray(mvcResult.getResponse()
                                                       .getContentAsString(), OrderIdWrapper.class)
                                  .stream()
                                  .map(orderIdWrapper ->
                                          orderIdWrapper.getOrderId())
                                  .collect(Collectors.toList());
        assertEquals("100", orders.get(0));
        assertEquals("101", orders.get(1));
        assertEquals("102", orders.get(2));
        mockMvc.perform(get("/api/v1/grabSeasons/1234567/seasonOrders").accept(MediaType.APPLICATION_JSON)
                                                                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isExpectationFailed())
               .andDo(print());
        List<OrderIdWrapper> result = grabSeasonsShow.getGrabSeasonOrders("12345");
        System.out.println(result);
        List<SeasonOrders> result1 = seasonOrdersMapper.selectByExample(null);
        for (SeasonOrders i : result1) {
            System.out.println(i);
        }
    }

    //TODO
    @Test
    public void grabSeasonsSeasonIdSeasonOrdersPostTest() throws Exception {

        List<OrderIdWrapper> orders = new ArrayList<>();
        OrderIdWrapper oiw = new OrderIdWrapper();
        oiw.setOrderId("102");
        orders.add(oiw);
        OrderIdWrapper oiw2 = new OrderIdWrapper();
        oiw2.setOrderId("103");
        orders.add(oiw2);
        mockMvc.perform(post("/api/v1/grabSeasons/12345/seasonOrders").content(JSON.toJSONString(orders))
                                                                      .accept(MediaType.APPLICATION_JSON)
                                                                      .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
        List<OrderIdWrapper> result = grabSeasonsShow.getGrabSeasonOrders("12345");
        System.out.println(result);
        List<SeasonOrders> result1 = seasonOrdersMapper.selectByExample(null);
        for (SeasonOrders i : result1) {
            System.out.println(i);
        }
        assertEquals("102", result.get(0)
                                  .getOrderId());
        assertEquals("103", result.get(1)
                                  .getOrderId());


    }

    @Test
    public void grabSeasonsSeasonIdStatusDraftPostTest() throws Exception {
        mockMvc.perform(post("/api/v1/grabSeasons/54321/status/draft").accept(MediaType.APPLICATION_JSON)
                                                                      .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
        GrabSeason grabSeasons = grabSeasonsShow.getGrabSeason("54321");
        assertEquals(GrabSeason.StateEnum.DRAFT, grabSeasons.getState());
        mockMvc.perform(post("/api/v1/grabSeasons/7654321/status/draft").accept(MediaType.APPLICATION_JSON)
                                                                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isExpectationFailed())
               .andDo(print());
    }

    @Test
    public void grabSeasonsSeasonIdStatusPublishPostTest() throws Exception {
        mockMvc.perform(post("/api/v1/grabSeasons/12345/status/publish").accept(MediaType.APPLICATION_JSON)
                                                                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());
        GrabSeason grabSeasons = grabSeasonsShow.getGrabSeason("12345");
        assertEquals(GrabSeason.StateEnum.PUBLISHED, grabSeasons.getState());
        mockMvc.perform(post("/api/v1/grabSeasons/1234567/status/draft").accept(MediaType.APPLICATION_JSON)
                                                                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isExpectationFailed())
               .andDo(print());
    }


}
