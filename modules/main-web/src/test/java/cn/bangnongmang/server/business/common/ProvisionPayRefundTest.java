package cn.bangnongmang.server.business.common;

import static org.junit.Assert.assertEquals;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.OrderGeoMapper;
import cn.bangnongmang.data.dao.OrderInsuranceRecordMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.dao.RegionGeoMapper;
import cn.bangnongmang.data.dao.RegionMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserWorkCalendarMapper;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.server.business.order.OrderBusiness;
import cn.bangnongmang.server.business.order.OrderCreateBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional

public class ProvisionPayRefundTest {

    @Autowired
    private OrderCreateBusiness orderCreateBusiness;
    @Autowired
    private AccountMapper accountmapper;
    @Autowired
    private RegionGeoMapper regionGeoMapper;
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private OrderGeoMapper orderGeoMapper;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;
    @Autowired
    private TeamOrderBusiness teamOrderBusiness;
    @Autowired
    private SeasonOrdersMapper seasonOrdersMapper;
    @Autowired
    private GrabSeasonMapper grabSeasonMapper;
    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;
    @Autowired
    private UserMachineMapper userMachineMapper;
    @Autowired
    private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
    @Autowired
    private UserMachineModelMapper userMachineModelMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private OrderInsuranceRecordMapper orderInsuranceRecordMapper;
    @Autowired
    private PocketLogMapper pocketLogMapper;
    @Autowired
    private TeamInOrderMapper teamInOrderMapper;
    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private TeamJoinRequestMapper teamJoinRequestMapper;
    @Autowired
    private OrderDriverMapper orderDriverMapper;
    @Autowired
    private UserWorkCalendarMapper userWorkCalendarMapper;
    private Long FARMER_UID = 111111111L;
    private Long DRIVERLEADER1 = 11111111111L;
    private Long DRIVER1 = 3333333L;
    private Long DRIVER2 = 4444444L;
    private Long DRIVERLEADER2 = 22222222222L;

    private Integer AUTHENTICATED_FARMER = 14;
    private String regionId = "111";
    private Long working_type_id = 111L;
    private int desireNum = 2;
    private double Size = 200.00;
    private Long startTime = 0L;
    private OrderFarmer orderFarmer;
    private Integer DRIVER_LEADER_AUTHED = 200;
    private Integer DRIVER = 20;
    private Integer GRAB_SEASON_STATE_PUBLISHED = 200;
    private String SEASONID = "1";
    private Long machineModelId = 123L;
    private Long UserMachineTypeId = 123L;

    @Before
    public void inite() throws BusinessException {
        // 创建农户
        accountmapper.insert(MainTestDataCreator.createAccount(FARMER_UID, AUTHENTICATED_FARMER));
        // 创建2人队伍
        accountmapper.insert(MainTestDataCreator.createAccount(DRIVERLEADER1, DRIVER_LEADER_AUTHED));
        accountmapper.insert(MainTestDataCreator.createAccount(DRIVER1, DRIVER));
        accountmapper.insert(MainTestDataCreator.createAccount(DRIVER2, DRIVER));
        // 创建单人队伍
        accountmapper.insert(MainTestDataCreator.createAccount(DRIVERLEADER2, DRIVER_LEADER_AUTHED));
        regionGeoMapper.insert(MainTestDataCreator.createRegionGeo(regionId));
        regionMapper.insert(MainTestDataCreator.createRegion(regionId, FARMER_UID, String.valueOf(FARMER_UID)));
        // 创建订单
        orderFarmer = orderCreateBusiness.createOrder(FARMER_UID, regionId, working_type_id, desireNum, Size,
                startTime, null, null, null);

        // season orders
        grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(GRAB_SEASON_STATE_PUBLISHED, SEASONID));
        seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders(orderFarmer.getOrder_id(), SEASONID));

        // userMachineShow for leader1
        userMachineMapper.insert(MainTestDataCreator.createUserMachine(DRIVERLEADER1, machineModelId));
        OptionWorkingType optionWorkingType = MainTestDataCreator.createOptionWorkingType();
        optionWorkingType.setId(working_type_id);
        optionWorkingTypeMapper.insert(optionWorkingType);
        optionWorkingTypeMachineModelMappingMapper
                .insert(MainTestDataCreator.createOptionWorkingTypeMachineModelMapping(machineModelId, working_type_id));

        userMachineModelMapper.insert(MainTestDataCreator.createUserMachineModel(machineModelId, UserMachineTypeId));
        // pocketLog for leader1
        Pocket pocket1 = MainTestDataCreator.createPocket(DRIVERLEADER1, 50000);
        pocketMapper.insert(pocket1);

        // userMachineShow for leader2
        userMachineMapper.insert(MainTestDataCreator.createUserMachine(DRIVERLEADER2, machineModelId));

        // pocketLog for leader2
        Pocket pocket2 = MainTestDataCreator.createPocket(DRIVERLEADER2, 50000);
        pocketMapper.insert(pocket2);

        // userMachineShow for driver1
        userMachineMapper.insert(MainTestDataCreator.createUserMachine(DRIVER1, machineModelId));

        // pocketLog for driver1
        Pocket pocket3 = MainTestDataCreator.createPocket(DRIVER1, 50000);
        pocketMapper.insert(pocket3);
    }

    /*
     * @After public void cleanUp() { AccountCriteria accountCriteria = new
     * AccountCriteria();
     * accountCriteria.or().andUsernameEqualTo(FarmerUsername);
     * accountmapper.deleteByExample(accountCriteria); accountCriteria.clear();
     * accountCriteria.or().andUsernameEqualTo(DRIVERLEADER1);
     * accountmapper.deleteByExample(accountCriteria); accountCriteria.clear();
     * accountCriteria.or().andUsernameEqualTo(DRIVER1);
     * accountmapper.deleteByExample(accountCriteria); accountCriteria.clear();
     * accountCriteria.or().andUsernameEqualTo(DRIVER2);
     * accountmapper.deleteByExample(accountCriteria); accountCriteria.clear();
     * accountCriteria.or().andUsernameEqualTo(DRIVERLEADER2);
     * accountmapper.deleteByExample(accountCriteria);
     *
     * regionGeoMapper.deleteByPrimaryKey(regionId);
     * regionMapper.deleteByPrimaryKey(regionId);
     *
     * OrderGeoCriteria orderGeoCriteria = new OrderGeoCriteria();
     * orderGeoCriteria.or().andLatitudeEqualTo(10.11);
     * orderGeoMapper.deleteByExample(orderGeoCriteria);
     *
     * OrderFarmerCriteria orderFarmerCriteria = new OrderFarmerCriteria();
     * orderFarmerCriteria.or().andBelongtoEqualTo(FarmerUsername);
     * orderFarmerMapper.deleteByExample(orderFarmerCriteria);
     *
     * grabSeasonMapper.deleteByPrimaryKey(SEASONID);
     *
     * SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
     * seasonOrdersCriteria.or().andSeason_idEqualTo(SEASONID);
     * seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);
     *
     * UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
     * userMachineCriteria.or().andBelongtoEqualTo(DRIVERLEADER1);
     * userMachineMapper.deleteByExample(userMachineCriteria);
     * userMachineCriteria.clear();
     * userMachineCriteria.or().andBelongtoEqualTo(DRIVERLEADER2);
     * userMachineMapper.deleteByExample(userMachineCriteria);
     * userMachineCriteria.clear();
     * userMachineCriteria.or().andBelongtoEqualTo(DRIVER1);
     * userMachineMapper.deleteByExample(userMachineCriteria);
     *
     * optionWorkingTypeMapper.deleteByPrimaryKey(working_type_id);
     *
     * OptionWorkingTypeMachineModelMappingCriteria
     * optionWorkingTypeMachineModelMappingCriteria = new
     * OptionWorkingTypeMachineModelMappingCriteria();
     * optionWorkingTypeMachineModelMappingCriteria.or().
     * andOption_working_type_idEqualTo(working_type_id);
     * optionWorkingTypeMachineModelMappingMapper.deleteByExample(
     * optionWorkingTypeMachineModelMappingCriteria);
     * userMachineModelMapper.deleteByPrimaryKey(machineModelId);
     *
     * pocketMapper.deleteByPrimaryKey(DRIVERLEADER1);
     * pocketMapper.deleteByPrimaryKey(DRIVERLEADER2);
     * pocketMapper.deleteByPrimaryKey(DRIVER1); OrderInsuranceRecordCriteria
     * orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
     * orderInsuranceRecordCriteria.or().andUsernameEqualTo(DRIVERLEADER1);
     * orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);
     * orderInsuranceRecordCriteria.clear();
     * orderInsuranceRecordCriteria.or().andUsernameEqualTo(DRIVERLEADER2);
     * orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);
     * orderInsuranceRecordCriteria.clear();
     * orderInsuranceRecordCriteria.or().andUsernameEqualTo(DRIVER1);
     * orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);
     *
     * PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
     * pocketLogCriteria.or().andBelongtoEqualTo(DRIVERLEADER1);
     * pocketLogMapper.deleteByExample(pocketLogCriteria);
     * pocketLogCriteria.clear();
     * pocketLogCriteria.or().andBelongtoEqualTo(DRIVERLEADER2);
     * pocketLogMapper.deleteByExample(pocketLogCriteria);
     *
     * TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
     * teamInOrderCriteria.or().andLeader_usernameEqualTo(DRIVERLEADER1);
     * teamInOrderMapper.deleteByExample(teamInOrderCriteria);
     * teamInOrderCriteria.or().andLeader_usernameEqualTo(DRIVERLEADER2);
     * teamInOrderCriteria.or().andLeader_usernameEqualTo(DRIVERLEADER2);
     * teamInOrderMapper.deleteByExample(teamInOrderCriteria);
     *
     * TeamJoinRequestCriteria teamJoinRequestCriteria = new
     * TeamJoinRequestCriteria();
     * teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);
     *
     * orderDriverMapper.deleteByPrimaryKey(orderFarmer.getOrder_id());
     * UserWorkCalendarCriteria userWorkCalendarCriteria = new
     * UserWorkCalendarCriteria();
     * userWorkCalendarCriteria.or().andUsernameEqualTo(DRIVER1);
     * userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria);
     *
     * userWorkCalendarCriteria.clear();
     * userWorkCalendarCriteria.or().andUsernameEqualTo(DRIVERLEADER1);
     * userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria); }
     */
    @Test
    public void ProvisionPayRefundTest() throws BusinessException {

        orderFarmer.setState("FARMER_WAITTING_GOT");
        OrderFarmerCriteria orderFarmerCriteria = new OrderFarmerCriteria();
        orderFarmerCriteria.or()
                           .andUidEqualTo(FARMER_UID);
        orderFarmerMapper.updateByExampleSelective(orderFarmer, orderFarmerCriteria);
        // 创建2只队伍
        Long teamId1 = teamOrderBusiness.createOrderTeam(orderFarmer.getOrder_id(), DRIVERLEADER1, "一");
        teamOrderBusiness.sendJoinOrderTeamRequest(teamId1, DRIVER1, "入队请求");
        teamOrderBusiness.acceptTeamRequest(teamId1, DRIVER1, DRIVERLEADER1);
        Long teamId2 = teamOrderBusiness.createOrderTeam(orderFarmer.getOrder_id(), DRIVERLEADER2, "二");
        orderBusiness.grabOrder(orderFarmer.getOrder_id(), teamId1);

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(DRIVERLEADER2)
                         .andTypeEqualTo(TradeFlowService.POCKETLOG_TYPE_REFUND_INSURANCE)
                         .andConnect_order_idEqualTo(orderFarmer.getOrder_id());

        assertEquals(1, pocketLogMapper.countByExample(pocketLogCriteria));
    }
}
