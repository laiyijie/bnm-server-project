package cn.bangnongmang.server.business.common;

import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.dao.OptionWorkingTypeMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.driverapp.models.OrderFarmerWorkSizeInfo;
import cn.bangnongmang.server.io.android.show.AndroidOrderShow;
import cn.bangnongmang.service.outerservice.OuterBankCardService;
import cn.bangnongmang.service.outerservice.SizeCounterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class TestTest {
    @Autowired
    private TeamOrderBusiness teamOrderBusiness;

    @Autowired
    private AndroidOrderShow androidOrderShow;

    @Autowired
    private AccountBusiness accountBusiness;

    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;

    @Autowired
    private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;

    @Autowired
    private UserMachineModelMapper userMachineModelMapper;

    @Autowired
    private SizeCounterService service;
    @Autowired
    private OuterBankCardService outerBankCardService;


    @Test
    public void test() {
        List<OrderFarmerWorkSizeInfo> orderFarmerWorkSizeInfos = androidOrderShow
                .getOrderFarmerWorkSizeInfo("201703061053089522265725");

        System.out.println(JSON.toJSONString(orderFarmerWorkSizeInfos, true));
    }

    // @Test
    // public void ontime_add_option_working_type_machine_model_mapping() {
    //
    // List<UserMachineModel> userMachineModels = userMachineModelMapper
    // .selectByExample(new UserMachineModelCriteria());
    // List<OptionWorkingType> optionWorkingTypes = optionWorkingTypeMapper
    // .selectByExample(new OptionWorkingTypeCriteria());
    // Random random = new Random();
    // int size = optionWorkingTypes.size();
    //
    // for (UserMachineModel userMachineModel : userMachineModels) {
    // OptionWorkingTypeMachineModelMapping machineModelMapping = new
    // OptionWorkingTypeMachineModelMapping();
    // machineModelMapping.setMachine_model_id(userMachineModel.getId());
    // machineModelMapping.setOption_working_type_id(optionWorkingTypes.get(random.nextInt(size)).getId());
    // optionWorkingTypeMachineModelMappingMapper.insert(machineModelMapping);
    // }
    //
    // }

    //	@Test
//	public void ttest() {
//		System.out.println(JSON.toJSONString("1.0.0".split("\\.")));
//	}
//
//	@Test
//	public void testSIze() throws IOException {
//		Polygon polygon = new Polygon();
//		Point point = new Point();
//		point.setLatitude(0.0);
//		point.setLongitude(0.0);
//
//		polygon.add(point);
//		point = new Point();
//		point.setLatitude(90.0);
//		point.setLongitude(180.0);
//		System.out.println(JSON.toJSONString(service.getDefaultApi().fieldsIdGet(12312).execute().errorBody()));
//	}
    @Test
    public void testAop() {
//		testForNotify.testThrowAndRollBack();

        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+5"));
        String Stamp = df.format(new Date());
        System.out.println(Stamp);


    }

//	@Test
//	public void tet(){
//		 ClientRsp clientRsp = outerBankCardService.auth("6214830211554612",
//		 "350821199205241219",
//		 "赖毅杰", "18801902298");
//
//		System.out.printf(clientRsp.getRspCode());
//	}

    @Test
    public void tt() {
        //System.out.println(null + "2132");
        System.out.print(Double.parseDouble("100"));
//        System.out.println( FriendDetail.StateEnum.fromValue("FARMER_WAITTING_GOT"));
    }


}
