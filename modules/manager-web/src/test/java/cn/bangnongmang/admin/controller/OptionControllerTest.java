package cn.bangnongmang.admin.controller;

/**
 * Created by lucq on 2017/5/22.
 */

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.data.dao.OptionClusterMapper;
import cn.bangnongmang.data.dao.OptionClusterWorkingTypeMappingMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.server.log.BLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.Block;
import jdk.net.SocketFlow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.HttpHeaders;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional()
public class OptionControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private OptionDetailMapper optionDetailMapper;

    @Autowired
    private OptionClusterMapper optionClusterMapper;

    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;

    @Autowired
    private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;

    private MockMvc mockMvc;


    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)

                .build();

        OptionCluster optionCluster=new OptionCluster();

        optionCluster.setId(10000L);
        optionCluster.setCluster_name("OptionCluster1");
        optionCluster.setDescription("TestCluster");
        optionClusterMapper.insert(optionCluster);

        optionCluster.setId(20000L);
        optionCluster.setCluster_name("OptionCluster2");
        optionCluster.setDescription("TestCluster");
        optionClusterMapper.insert(optionCluster);


        OptionDetail optionDetail=new OptionDetail();

        optionDetail.setId(10000L);
        optionDetail.setCluster_id(10000L);
        optionDetail.setOption_name("OptionDetail1");
        optionDetail.setDescription("Test");
        optionDetailMapper.insert(optionDetail);

        optionDetail.setId(20000L);
        optionDetail.setCluster_id(10000L);
        optionDetail.setOption_name("OptionDetail2");
        optionDetail.setDescription("Test");
        optionDetailMapper.insert(optionDetail);


        OptionWorkingType optionWorkingType=new OptionWorkingType();

        optionWorkingType.setId(10000L);
        optionWorkingType.setWorking_type("Test1");
        optionWorkingType.setCrop_type("Test1");
        optionWorkingTypeMapper.insert(optionWorkingType);

        optionWorkingType.setId(20000L);
        optionWorkingType.setWorking_type("Test2");
        optionWorkingType.setCrop_type("Test2");
        optionWorkingTypeMapper.insert(optionWorkingType);



    }

    @Test
    public void Test_optionOptionClustersClusterIdOptionDetailsDetailIdDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/option/optionClusters/10000/optionDetails/10")
                .sessionAttr("username","1234456789"))
                .andExpect(status().is(417));

        mockMvc.perform(delete("/api/v1/option/optionClusters/10000/optionDetails/10000")
                .sessionAttr("username","1234456789"));
//               .andExpect(status().isOk());

    }

    @Test
    public void Test_optionOptionClustersClusterIdOptionDetailsDetailIdPut() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        cn.bangnongmang.admin.swagger.model.OptionDetail inOp=new  cn.bangnongmang.admin.swagger.model.OptionDetail();
        inOp.setId(10000L);
        inOp.setName("OptionDetailModify");
        inOp.setDescprition("test2");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/api/v1/option/optionClusters/10000/optionDetails/10000").headers(httpHeaders)
                .content(mapper.writeValueAsString(inOp))
                .sessionAttr("username","1234456789"))
                .andExpect(status().isOk());

        OptionDetail op=optionDetailMapper.selectByPrimaryKey(10000L);

        assert(op.getOption_name().equals("OptionDetailModify"));
    }

    @Test
    public void Test_optionOptionClustersClusterIdOptionDetailsPost() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        cn.bangnongmang.admin.swagger.model.OptionDetail inOp=new  cn.bangnongmang.admin.swagger.model.OptionDetail();
        inOp.setName("ODAdd");
        inOp.setDescprition("test");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/option/optionClusters/10000/optionDetails").headers(httpHeaders)
                .content(mapper.writeValueAsString(inOp))
                .sessionAttr("username","1234456789"))
               .andExpect(status().isOk());

        OptionDetailCriteria odc=new OptionDetailCriteria();
        odc.or().andOption_nameEqualTo("ODAdd");
        OptionDetail od=optionDetailMapper.selectByExample(odc).get(0);

        //BLog.businessJsonLogBuilder("Test_optionOptionClustersPost").put("count",count).put("count1",count1).log();

        assert (od!=null);
        assert(od.getDescription().equals("test"));


    }



    @Test
    public void Test_optionOptionClustersPost() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        cn.bangnongmang.admin.swagger.model.OptionCluster inoc= new cn.bangnongmang.admin.swagger.model.OptionCluster();
        inoc.setName("testAddOptionCluster");
        inoc.setDescription("test");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/option/optionClusters").headers(httpHeaders)
                .content(mapper.writeValueAsString(inoc))
                .sessionAttr("username","1234456789")).andExpect(status().isOk());

        long count1=optionClusterMapper.countByExample(new OptionClusterCriteria());


        OptionClusterCriteria occ=new OptionClusterCriteria();
        occ.or().andCluster_nameEqualTo("testAddOptionCluster");

        OptionCluster oc=optionClusterMapper.selectByExample(occ).get(0);
        assert (oc!=null);
        assert (oc.getDescription().equals("test"));

    }

    @Test
    public void Test_optionOptionClustersClusterIdPut() throws Exception {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        cn.bangnongmang.admin.swagger.model.OptionCluster inoc= new cn.bangnongmang.admin.swagger.model.OptionCluster();
        inoc.setId(10000L);
        inoc.setName("Test_optionOptionClustersClusterIdPut");
        inoc.setDescription("test");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/api/v1/option/optionClusters/10000").headers(httpHeaders)
                .content(mapper.writeValueAsString(inoc))
                .sessionAttr("username","1234456789")).andExpect(status().isOk());

        OptionCluster oc=optionClusterMapper.selectByPrimaryKey(10000L);

        assert (oc.getCluster_name().equals("Test_optionOptionClustersClusterIdPut"));

    }

    @Test
    public void Test_optionOptionClustersGet() throws Exception {

        mockMvc.perform(get("/api/v1/option/optionClusters"))
               .andDo(print())
               .andExpect(status().isOk());


    }

    @Test
    public void Test_optionOptionClustersClusterIdGet() throws Exception {

        mockMvc.perform(get("/api/v1/option/optionClusters/10000"))
               .andExpect(status().isOk())
               .andDo(print());

        mockMvc.perform(get("/api/v1/option/optionClusters/10001"))
               .andExpect(status().is(404))
               .andDo(print());

    }

    @Test
    public void Test_optionOptionClustersClusterIdOptionDetailsGet() throws Exception {
        mockMvc.perform(get("/api/v1/option/optionClusters/10000/optionDetails"))
               .andExpect(status().isOk())
               .andDo(print());

        mockMvc.perform(get("/api/v1/option/optionClusters/10001/optionDetails"))
               .andExpect(status().isOk())
               .andDo(print());

        mockMvc.perform(get("/api/v1/option/optionClusters/20000/optionDetails"))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    public void Test_optionOptionClustersClusterIdOptionDetailsDetailIdGet() throws Exception {

        mockMvc.perform(get("/api/v1/option/optionClusters/10000/optionDetails/10000"))
               .andExpect(status().isOk())
               .andDo(print());

        mockMvc.perform(get("/api/v1/option/optionClusters/10001/optionDetails/1000100"))
               .andExpect(status().is(404))
               .andDo(print());
    }

    @Test
    public void Test_optionWorkingTypesGet()throws Exception{

        mockMvc.perform(get("/api/v1/option/workingTypes"))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    public void Test_optionWorkingTypesPost()throws Exception{
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        cn.bangnongmang.admin.swagger.model.WorkingType wt= new cn.bangnongmang.admin.swagger.model.WorkingType();
        wt.setWorkingType("TestSave");
        wt.setCropType("MOd");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/option/workingTypes").headers(httpHeaders)
                                                                .content(mapper.writeValueAsString(wt))
                                                                .sessionAttr("username","1234456789")).andExpect(status().isOk());

        OptionWorkingTypeCriteria owtc=new OptionWorkingTypeCriteria();

        owtc.or().andCrop_typeEqualTo("MOd");

        OptionWorkingType owt=optionWorkingTypeMapper.selectByExample(owtc).get(0);

        assert (owt!=null);


    }

    @Test
    public void Test_optionWorkingTypesWorkingTypeIdGet()throws Exception{

        List<OptionWorkingType> l=optionWorkingTypeMapper.selectByExample(new OptionWorkingTypeCriteria());
        BLog.businessJsonLogBuilder("test").put("id1",l.get(0).getId()).put("id2",l.get(1).getId()).log();
        mockMvc.perform(get("/api/v1/option/workingTypes/10001"))
               .andExpect(status().is(404))
               .andDo(print());
        mockMvc.perform(get("/api/v1/option/workingTypes/10000"))
               .andExpect(status().isOk())
               .andDo(print());



    }

    @Test
    public void Test_optionWorkingTypesWorkingTypeIdPut()throws Exception{

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        cn.bangnongmang.admin.swagger.model.WorkingType wt= new cn.bangnongmang.admin.swagger.model.WorkingType();
        wt.setId(10000L);
        wt.setWorkingType("test");
        wt.setCropType("MOd");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/api/v1/option/workingTypes/10000").headers(httpHeaders)
                                                                  .content(mapper.writeValueAsString(wt))
                                                                  .sessionAttr("username","1234456789")).andExpect(status().isOk());

        OptionWorkingType oc=optionWorkingTypeMapper.selectByPrimaryKey(10000L);

        assert (oc.getCrop_type().equals("MOd"));

    }

    @Test
    public void Test_workingTypeOptionClusterRelations() throws Exception {

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(post("/api/v1/option/optionClusters/1000/workingTypes/10000").headers(httpHeaders))
               .andExpect(status().is(417));
        mockMvc.perform(post("/api/v1/option/optionClusters/20000/workingTypes/10000").headers(httpHeaders))
               .andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/option/optionClusters/20000/workingTypes/40000").headers(httpHeaders))
               .andExpect(status().is(417));
        mockMvc.perform(get("/api/v1/option/workingTypes/10000/optionCluster").headers(httpHeaders))
               .andExpect(status().isOk())
               .andDo(print());


    }


    @After
    public void After(){

    }

}
