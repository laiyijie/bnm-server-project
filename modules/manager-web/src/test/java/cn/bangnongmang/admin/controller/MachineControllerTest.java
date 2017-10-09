package cn.bangnongmang.admin.controller;


import cn.bangnongmang.admin.swagger.model.MachineModel;
import cn.bangnongmang.admin.swagger.model.MachineType;

import cn.bangnongmang.data.dao.OptionWorkingTypeMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.log.BLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.soap.SOAPBinding;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lucq on 2017/5/25.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional()
public class MachineControllerTest {

    @Autowired
    private UserMachineModelMapper userMachineModelMapper;
    @Autowired
    private UserMachineTypeMapper userMachineTypeMapper;

    @Autowired
    private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void before() {

        UserMachineModel userMachineModel = new UserMachineModel();
        userMachineModel.setId(10000L);
        userMachineModel.setUser_machine_type_id(10000L);
        userMachineModel.setModel_number("No123456");
        userMachineModel.setModel_brand("Test");
        userMachineModel.setState(400);
        userMachineModel.setUser_machine_type_id(10000L);
        userMachineModel.setModel_width(1000D);
        userMachineModel.setModel_power(2000D);
        userMachineModelMapper.insert(userMachineModel);
        userMachineModel.setModel_brand("Test2");
        userMachineModel.setId(20000L);
        userMachineModelMapper.insert(userMachineModel);
        userMachineModel.setModel_brand("Test3");
        userMachineModel.setId(30000L);
        userMachineModelMapper.insert(userMachineModel);


        UserMachineType userMachineType = new UserMachineType();
        userMachineType.setId(10000L);
        userMachineType.setType_name("Test1");
        userMachineType.setDescripetion("TEST");
        userMachineTypeMapper.insert(userMachineType);

        userMachineType.setId(20000L);
        userMachineType.setType_name("Test2");
        userMachineTypeMapper.insert(userMachineType);

        userMachineType.setId(30000L);
        userMachineType.setType_name("Test3");
        userMachineTypeMapper.insert(userMachineType);

        OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey=new OptionWorkingTypeMachineModelMappingKey();
        optionWorkingTypeMachineModelMappingKey.setMachine_model_id(10000L);
        optionWorkingTypeMachineModelMappingKey.setOption_working_type_id(20L);
        optionWorkingTypeMachineModelMappingMapper.insert(optionWorkingTypeMachineModelMappingKey);
        optionWorkingTypeMachineModelMappingKey.setMachine_model_id(10000L);
        optionWorkingTypeMachineModelMappingKey.setOption_working_type_id(10000L);
        optionWorkingTypeMachineModelMappingMapper.insert(optionWorkingTypeMachineModelMappingKey);


        OptionWorkingType optionWorkingType=new OptionWorkingType();

        optionWorkingType.setId(10000L);
        optionWorkingType.setWorking_type("Test1");
        optionWorkingType.setCrop_type("Test1");
        optionWorkingTypeMapper.insert(optionWorkingType);

        optionWorkingType.setId(20L);
        optionWorkingType.setWorking_type("Test2");
        optionWorkingType.setCrop_type("Test2");
        optionWorkingTypeMapper.insert(optionWorkingType);



        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                                 .build();
        mapper = new ObjectMapper();

    }


    @Test
    public void Test_machineMachineModelsGet() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();

//        mockMvc.perform(get("/api/v1/machine/machineModels").sessionAttr("username", "1234456789")
//                                                            .headers(httpHeaders))
//               .andDo(print())
//               .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/machine/machineModels/1").sessionAttr("username", "1234456789")
                                                              .headers(httpHeaders))
               .andDo(print())
               .andExpect(status().isNotFound());

//        mockMvc.perform(get("/api/v1/machine/machineModels/1482804954324706").sessionAttr("username", "1234456789")
//                                                                  .headers(httpHeaders))
//               .andDo(print())
//               .andExpect(status().isOk());


    }

    //@Test
    public void Test_machineMachineModelsModelIdDelete() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(delete("/api/v1/machine/machineModels/10000").sessionAttr("username", "1234456789")
                                                                     .headers(httpHeaders))
               .andDo(print())
               .andExpect(status().is(417));
    }

    //@Test
    public void Test_machineMachineModelsModelIdPut() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MachineModel machineModel = new MachineModel();

        //machineModel.setWidth(null);
        machineModel.setPower(10000D);
        machineModel.setBrand("TED");
        //machineModel.setModel("TED");
        //machineModel.setState(400);


        MachineType machineType = new MachineType();
        machineType.setId(10000L);
        machineType.setName("10000");

        machineModel.setMachineType(machineType);
        mockMvc.perform(put("/api/v1/machine/machineModels/1000").sessionAttr("username", "1234456789")
                                                                 .headers(httpHeaders)
                                                                 .content(mapper
                                                                         .writeValueAsString(machineModel)))
               .andDo(print())
               .andExpect(status().is(417));

        mockMvc.perform(put("/api/v1/machine/machineModels/10000").sessionAttr("username", "1234456789")
                                                                  .headers(httpHeaders)
                                                                  .content(mapper
                                                                          .writeValueAsString(machineModel)))
               .andDo(print())
               .andExpect(status().isOk());

        UserMachineModel userMachineModel = userMachineModelMapper.selectByPrimaryKey(10000L);
        BLog.businessJsonLogBuilder("yyyyy")
            .put("t", userMachineModel.getModel_brand());
        assert (userMachineModel.getModel_brand()
                                .equals("TED"));

    }

    //@Test
    public void Test_machineMachineModelsModelIdPost() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MachineModel machineModel = new MachineModel();
        ;

        //machineModel.setWidth(null);
        machineModel.setPower(10000D);
        machineModel.setBrand("TED");
        //machineModel.setModel("TED");
        //machineModel.setState(400);


        MachineType machineType = new MachineType();
        machineType.setId(10000L);
        machineType.setName("10000");
        machineModel.setMachineType(machineType);

        mockMvc.perform(post("/api/v1/machine/machineModels").sessionAttr("username", "1234456789")
                                                             .headers(httpHeaders)
                                                             .content(mapper
                                                                     .writeValueAsString(machineModel)))
               .andDo(print())
               .andExpect(status().isOk());

        UserMachineModelCriteria ummc = new UserMachineModelCriteria();
        ummc.or()
            .andModel_brandEqualTo("TED");
        List<UserMachineModel> list = userMachineModelMapper.selectByExample(ummc);
        assert (list.size() == 1);

    }

    //@Test
    public void Test_machineMachineTypesGet() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(get("/api/v1/machine/machineTypes").sessionAttr("username", "1234456789")
                                                           .headers(httpHeaders))
               .andDo(print())
               .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/machine/machineTypes/1000").sessionAttr("username", "1234456789")
                                                                .headers(httpHeaders))
               .andDo(print())
               .andExpect(status().isNotFound());
        mockMvc.perform(get("/api/v1/machine/machineTypes/10000").sessionAttr("username", "1234456789")
                                                                 .headers(httpHeaders))
               .andDo(print())
               .andExpect(status().isOk());

    }

    //@Test
    public void Test_machineMachineTypesPut() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MachineType machineType = new MachineType();

        machineType.setId(10000L);
        machineType.setName("TED");
        machineType.description("TEDTEST");




        mockMvc.perform(put("/api/v1/machine/machineTypes/1000").sessionAttr("username", "1234456789")
                                                                 .headers(httpHeaders)
                                                                 .content(mapper
                                                                         .writeValueAsString(machineType)))
               .andDo(print())
               .andExpect(status().is(417));

        mockMvc.perform(put("/api/v1/machine/machineTypes/10000").sessionAttr("username", "1234456789")
                                                                  .headers(httpHeaders)
                                                                  .content(mapper
                                                                          .writeValueAsString(machineType)))
               .andDo(print())
               .andExpect(status().isOk());

        UserMachineType userMachineType = userMachineTypeMapper.selectByPrimaryKey(10000L);
        assert (userMachineType.getType_name().equals("TED"));
    }
    //@Test
    public void Test_machineMachineTypesPost() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MachineType machineType = new MachineType();

        machineType.setName("TED");
        machineType.description("TEDTEST");

        mockMvc.perform(post("/api/v1/machine/machineTypes").sessionAttr("username", "1234456789")
                                                                .headers(httpHeaders)
                                                                .content(mapper
                                                                        .writeValueAsString(machineType)))
               .andDo(print())
               .andExpect(status().isOk());

        UserMachineTypeCriteria umtc=new UserMachineTypeCriteria();
        umtc.or().andType_nameEqualTo("TED");
        List<UserMachineType> list = userMachineTypeMapper.selectByExample(umtc);
        assert (list.size()==1);
    }

    //@Test
    public void Test_machineMachineTypeTypeIdDelete() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(delete("/api/v1/machine/machineTypes/10000").sessionAttr("username", "1234456789").headers(httpHeaders))
               .andDo(print())
               .andExpect(status().is(417));
    }

   // @Test
    public  void Test_machineMachineModelsMachineModelIdWorkingTypesGet() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(get("/api/v1/machine/machineModels/10000/workingTypes").sessionAttr("username", "1234456789").headers(httpHeaders))
               .andDo(print())
               .andExpect(status().is(200));
    }

    //@Test
    public  void Test_machineMachineModelsMachineModelIdWorkingTypesDelete() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(delete("/api/v1/machine/machineModels/10000/workingTypes/10000").sessionAttr("username", "1234456789").headers(httpHeaders))
               .andDo(print())
               .andExpect(status().is(417));
    }

    //@Test
    public  void Test_machineMachineModelsMachineModelIdWorkingTypesPost() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(post("/api/v1/machine/machineModels/10000/workingTypes/10").sessionAttr("username", "1234456789").headers
                (httpHeaders))
               .andDo(print())
               .andExpect(status().is(417));

        int num=optionWorkingTypeMachineModelMappingMapper.selectByExample(null).size();
        mockMvc.perform(post("/api/v1/machine/machineModels/20000/workingTypes/10000").sessionAttr("username", "1234456789").headers
                (httpHeaders))
               .andDo(print())
               .andExpect(status().is(200));
        assert (optionWorkingTypeMachineModelMappingMapper.selectByExample(null).size()==num+1);

        mockMvc.perform(get("/api/v1/machine/machineModels/20000/workingTypes").sessionAttr("username", "1234456789").headers(httpHeaders))
               .andDo(print())
               .andExpect(status().is(200));
    }


    @Test
    public void test(){

    }

}
