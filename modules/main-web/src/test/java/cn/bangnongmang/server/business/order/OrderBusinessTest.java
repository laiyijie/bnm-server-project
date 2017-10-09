package cn.bangnongmang.server.business.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderDriverWorkSizeMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.OrderFarmerWorkSizeMapper;
import cn.bangnongmang.data.dao.OrderInsuranceRecordMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.dao.UserWorkCalendarMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderDriverCriteria;
import cn.bangnongmang.data.domain.OrderDriverWorkSize;
import cn.bangnongmang.data.domain.OrderDriverWorkSizeCriteria;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.OrderFarmerWorkSizeCriteria;
import cn.bangnongmang.data.domain.OrderInsuranceRecordCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.data.domain.TeamJoinRequestCriteria;
import cn.bangnongmang.data.domain.UserWorkCalendarCriteria;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.TeamOrderService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.WorkSizeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderBusinessTest {

    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private TeamInOrderMapper teamInOrderMapper;
    @Autowired
    private OrderDriverMapper orderDriverMapper;
    @Autowired
    private TeamJoinRequestMapper teamJoinRequestMapper;
    @Autowired
    private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;
    @Autowired
    private OrderDriverWorkSizeMapper orderDriverWorkSizeMapper;
    @Autowired
    private PocketLogMapper pocketLogMapper;

    @Autowired
    private GrabSeasonMapper grabSeasonMapper;
    @Autowired
    private SeasonOrdersMapper seasonOrdersMapper;
    @Autowired
    private OrderInsuranceRecordMapper orderInsuranceRecordMapper;
    @Autowired
    private UserWorkCalendarMapper userWorkCalendarMapper;

    private static final Long TEST_FARMER_UID = 18801902222L;
    private static final Long TEST_DRIVER_UID = 18801902298L;

    private static final Long[] TEST_DRIVER_MEMBERS = {18801902301L, 18801902302L, 18801902303L};

    private static final String TEST_FARMER_ORDER_ID = "test_farmer_order_for_order_business";

    private static final Long TEST_TEAM_IN_ORDER_ID = 91225555000L;

    private static final String TEST_CURRENT_GRAB_SEASON_ID = "test_season_id";
    public static final Integer INSURANCE_DEFAULT_MONEY = 50000;
    public static List<Long> allUser = new ArrayList<>();

    @AfterClass
    public static void clearUserCalendar() {

    }

    @BeforeClass
    public static void init() {
        allUser.addAll(Arrays.stream(TEST_DRIVER_MEMBERS)
                             .collect(Collectors.toList()));
        allUser.add(TEST_FARMER_UID);
        allUser.add(TEST_DRIVER_UID);

    }

    @Before
    public void beforeTest() {

        Account account = MainTestDataCreator.createAccount(TEST_FARMER_UID, UserService.AUTHENTICATED_FARMER);
        AccountRealNameAuth accountRealNameAuth = MainTestDataCreator.createAccountRealNameAuth(TEST_FARMER_UID,
                UserService.REAL_NAME_AUTH_STATE_PASS, "测试农户");

        accountMapper.insert(account);
        accountRealNameAuthMapper.insert(accountRealNameAuth);

        account = MainTestDataCreator.createAccount(TEST_DRIVER_UID, UserService.AUTHENTICATED_DRIVER);
        accountRealNameAuth = MainTestDataCreator.createAccountRealNameAuth(TEST_DRIVER_UID,
                UserService.REAL_NAME_AUTH_STATE_PASS, "测试队长");
        account.setDriver_leader(UserService.DRIVER_LEADER_AUTHED);
        Pocket pocketLeader = MainTestDataCreator.createPocket(TEST_DRIVER_UID, 0);
        accountMapper.insert(account);
        accountRealNameAuthMapper.insert(accountRealNameAuth);
        pocketMapper.insert(pocketLeader);
        for (Long driverPhonNumber : TEST_DRIVER_MEMBERS) {
            account = MainTestDataCreator.createAccount(driverPhonNumber, UserService.AUTHENTICATED_DRIVER);
            accountRealNameAuth = MainTestDataCreator.createAccountRealNameAuth(driverPhonNumber,
                    UserService.REAL_NAME_AUTH_STATE_PASS, "测试机手xx");
            Pocket pocket = MainTestDataCreator.createPocket(driverPhonNumber, 0);
            pocketMapper.insert(pocket);
            accountMapper.insert(account);
            accountRealNameAuthMapper.insert(accountRealNameAuth);
        }

        grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(OrderFarmerService.GRAB_SEASON_STATE_PUBLISHED,
                TEST_CURRENT_GRAB_SEASON_ID));
        seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders(TEST_FARMER_ORDER_ID, TEST_CURRENT_GRAB_SEASON_ID));
    }

    @After
    public void afterTest() {
        for (Long user : allUser) {
            UserWorkCalendarCriteria userWorkCalendarCriteria = new UserWorkCalendarCriteria();
            userWorkCalendarCriteria.or()
                                    .andUidEqualTo(user);
            userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria);
        }
        // 删除所有账户和钱包
        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUidEqualTo(TEST_FARMER_UID);
        accountCriteria.or()
                       .andUidEqualTo(TEST_DRIVER_UID);

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(TEST_DRIVER_UID);
        pocketLogMapper.deleteByExample(pocketLogCriteria);
        pocketLogCriteria.clear();
        pocketLogCriteria.or()
                         .andUidEqualTo(TEST_FARMER_UID);
        pocketLogMapper.deleteByExample(pocketLogCriteria);

        for (Long driverPhone : TEST_DRIVER_MEMBERS) {
            accountCriteria.or()
                           .andUidEqualTo(driverPhone);
            accountRealNameAuthMapper.deleteByPrimaryKey(driverPhone);
            pocketMapper.deleteByPrimaryKey(driverPhone);
            pocketLogCriteria = new PocketLogCriteria();
            pocketLogCriteria.or()
                             .andUidEqualTo(driverPhone);
            pocketLogMapper.deleteByExample(pocketLogCriteria);
        }

        accountMapper.deleteByExample(accountCriteria);
        accountRealNameAuthMapper.deleteByPrimaryKey(TEST_FARMER_UID);

        pocketMapper.deleteByPrimaryKey(TEST_FARMER_UID);

        accountRealNameAuthMapper.deleteByPrimaryKey(TEST_DRIVER_UID);
        pocketMapper.deleteByPrimaryKey(TEST_DRIVER_UID);

        // 删除订单
        orderFarmerMapper.deleteByPrimaryKey(TEST_FARMER_ORDER_ID);
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(TEST_FARMER_ORDER_ID);
        orderDriverMapper.deleteByExample(orderDriverCriteria);

        // 删除队伍和入队请求
        teamInOrderMapper.deleteByPrimaryKey(TEST_TEAM_IN_ORDER_ID);

        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andTeam_idEqualTo(TEST_TEAM_IN_ORDER_ID);
        teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);

        // 删除worksize
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or()
                                   .andOrder_farmer_idEqualTo(TEST_FARMER_ORDER_ID);

        List<OrderFarmerWorkSize> orderFarmerWorkSizes = orderFarmerWorkSizeMapper
                .selectByExample(orderFarmerWorkSizeCriteria);
        if (!orderFarmerWorkSizes.isEmpty()) {
            OrderDriverWorkSizeCriteria orderDriverWorkSizeCriteria = new OrderDriverWorkSizeCriteria();
            for (OrderFarmerWorkSize orderFarmerWorkSize : orderFarmerWorkSizeMapper
                    .selectByExample(orderFarmerWorkSizeCriteria)) {
                orderDriverWorkSizeCriteria.or()
                                           .andOrder_farmer_work_size_idEqualTo(orderFarmerWorkSize.getId());
            }
            orderDriverWorkSizeMapper.deleteByExample(orderDriverWorkSizeCriteria);
        }

        orderFarmerWorkSizeMapper.deleteByExample(orderFarmerWorkSizeCriteria);

        grabSeasonMapper.deleteByPrimaryKey(TEST_CURRENT_GRAB_SEASON_ID);
        SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
        seasonOrdersCriteria.or()
                            .andSeason_idEqualTo(TEST_CURRENT_GRAB_SEASON_ID);
        seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);

    }

    @Test
    public void TC0101_farmerPrePay_normal() throws BusinessException {
        final Integer initMoney = 18000000;
        final Double size = 200.0;
        final Integer discount = 100;
        final Integer prePayRate = 100;
        final Integer uniPrice = 9000;

        Pocket pocket = MainTestDataCreator.createPocket(TEST_FARMER_UID, initMoney);
        pocketMapper.insert(pocket);
        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, 5, System.currentTimeMillis() / 1000,
                TEST_FARMER_ORDER_ID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_PAY);
        orderFarmer.setSize(size);
        orderFarmer.setUni_price(uniPrice);
        orderFarmer.setPre_pay_rate(prePayRate);
        orderFarmer.setDiscount(discount);

        orderFarmerMapper.insert(orderFarmer);

        try {
            orderBusiness.farmerPrepay(TEST_FARMER_UID, TEST_FARMER_ORDER_ID);

        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            e.printStackTrace();
            throw e;
        }

        OrderFarmer afterOrderfarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        Pocket afterPocket = pocketMapper.selectByPrimaryKey(TEST_FARMER_UID);
        assertEquals(OrderFarmerStateName.FARMER_WAITTING_GOT, afterOrderfarmer.getState());
        assertEquals((int) (initMoney - Math.round(size * discount / 100 * prePayRate / 100 * uniPrice)),
                afterPocket.getCurr_money()
                           .intValue());
    }

    @Test
    public void TC0201_grabOrder_onePeopleNormal() throws BusinessException {

        final Integer desireNum = 1;

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, 5, System.currentTimeMillis() / 1000,
                TEST_FARMER_ORDER_ID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_GOT);
        orderFarmer.setDesire_num(desireNum);
        orderFarmerMapper.insert(orderFarmer);

        TeamInOrder teamInOrder = MainTestDataCreator.createTeamInOrder(TEST_TEAM_IN_ORDER_ID, TEST_FARMER_ORDER_ID,
                TEST_DRIVER_UID, "碉堡了的队伍");
        teamInOrderMapper.insert(teamInOrder);

        orderBusiness.grabOrder(TEST_FARMER_ORDER_ID, TEST_TEAM_IN_ORDER_ID);

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_GOT, afterOrderFarmer.getState());
        assertEquals(TEST_DRIVER_UID, afterOrderFarmer.getDriver_leader());

        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(TEST_FARMER_ORDER_ID);
        List<OrderDriver> afterOrderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        assertEquals(desireNum.intValue(), afterOrderDrivers.size());
        for (OrderDriver orderDriver : afterOrderDrivers) {
            assertEquals(TEST_DRIVER_UID, orderDriver.getUid());
        }
    }

    @Test
    public void TC0202_grabOrder_mutiPeopleNormal() throws BusinessException {

        final Integer desireNum = TEST_DRIVER_MEMBERS.length + 1;

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, 5, System.currentTimeMillis() / 1000,
                TEST_FARMER_ORDER_ID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_GOT);
        orderFarmer.setDesire_num(desireNum);
        orderFarmerMapper.insert(orderFarmer);

        TeamInOrder teamInOrder = MainTestDataCreator.createTeamInOrder(TEST_TEAM_IN_ORDER_ID, TEST_FARMER_ORDER_ID,
                TEST_DRIVER_UID, "碉堡了的队伍");
        teamInOrderMapper.insert(teamInOrder);

        for (Long driverPhone : TEST_DRIVER_MEMBERS) {
            TeamJoinRequest teamJoinRequest = MainTestDataCreator.createTeamJoinRequest(
                    System.currentTimeMillis() * 1000 + new Random().nextInt(1000), TEST_TEAM_IN_ORDER_ID, driverPhone,
                    "救命");
            teamJoinRequest.setState(TeamOrderService.REQUEST_ACCEPTED);
            teamJoinRequestMapper.insert(teamJoinRequest);
        }

        try {
            orderBusiness.grabOrder(TEST_FARMER_ORDER_ID, TEST_TEAM_IN_ORDER_ID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            throw e;
        }

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_GOT, afterOrderFarmer.getState());
        assertEquals(TEST_DRIVER_UID, afterOrderFarmer.getDriver_leader());

        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(TEST_FARMER_ORDER_ID);
        List<OrderDriver> afterOrderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        assertEquals(desireNum.intValue(), afterOrderDrivers.size());
        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);
        for (OrderDriver orderDriver : afterOrderDrivers) {
            if (allDriver.contains(orderDriver.getUid())) {
                continue;
            }
            fail();
        }
    }

    @Test
    public void TC0301_readyToWork_oneManNormal() throws BusinessException {
        final Integer desireNum = 1;
        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_GOT);
        OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID,
                OrderDriverService.ORDER_DRIVER_STATE_GOT);
        orderDriverMapper.insert(orderDriver);
        orderFarmerMapper.insert(orderFarmer);

        try {
            orderBusiness.readyToWork(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            e.printStackTrace();
            throw e;
        }

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);

        assertEquals(OrderFarmerStateName.FARMER_WORKING, afterOrderFarmer.getState());

    }

    @Test
    public void TC0302_readyToWork_errorOtherDriverTouch() {
        final Integer desireNum = 1;
        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_GOT);
        OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID,
                OrderDriverService.ORDER_DRIVER_STATE_GOT);
        orderDriverMapper.insert(orderDriver);
        orderFarmerMapper.insert(orderFarmer);

        try {
            orderBusiness.readyToWork(TEST_FARMER_ORDER_ID, TEST_DRIVER_MEMBERS[0]);
            fail();
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            assertEquals("你不是队长无权操作", e.errorMessage);
        }

    }

    @Test
    public void TC0401_finishOneDayWork_MutiManNormal() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WORKING);
        orderFarmerMapper.insert(orderFarmer);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }

        try {
            orderBusiness.finishOneDayWork(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            e.printStackTrace();
            throw e;
        }
        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_TODAY_WORKING_STOP, afterOrderFarmer.getState());
    }

    @Test
    public void TC0501_farmerEnsureSize_MutiManNormal() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;
        Pocket pocket = MainTestDataCreator.createPocket(TEST_FARMER_UID, 1000000000);
        pocketMapper.insert(pocket);

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_SIZE_ENSURE);
        orderFarmerMapper.insert(orderFarmer);

        OrderFarmerWorkSize orderFarmerWorkSize = MainTestDataCreator.createOrderFarmerWorkSize(TEST_FARMER_ORDER_ID, size,
                WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE);
        orderFarmerWorkSizeMapper.insert(orderFarmerWorkSize);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }

        try {
            orderBusiness.farmerEnsureSize(TEST_FARMER_UID, TEST_FARMER_ORDER_ID, size);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            e.printStackTrace();
            throw e;
        }

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_WAITTING_DISTRIBUTE, afterOrderFarmer.getState());
        OrderFarmerWorkSize afterOrderFarmerWorkSize = orderFarmerWorkSizeMapper
                .selectByPrimaryKey(orderFarmerWorkSize.getId());
        assertEquals(WorkSizeService.WORK_SIZE_STATE_ENSURED, afterOrderFarmerWorkSize.getState());
    }

    @Test
    public void TC0601_distributeSize_mutiManNormal() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_DISTRIBUTE);
        orderFarmerMapper.insert(orderFarmer);

        OrderFarmerWorkSize orderFarmerWorkSize = MainTestDataCreator.createOrderFarmerWorkSize(TEST_FARMER_ORDER_ID, size,
                WorkSizeService.WORK_SIZE_STATE_ENSURED);
        orderFarmerWorkSizeMapper.insert(orderFarmerWorkSize);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }

        Map<Long, Double> everyOneWorks = new HashMap<>();

        for (Long driver : allDriver) {
            everyOneWorks.put(driver, size / desireNum);
        }
        try {
            orderBusiness.distributeWork(TEST_FARMER_ORDER_ID, everyOneWorks, TEST_DRIVER_UID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            e.printStackTrace();
            throw e;
        }

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_TODAY_WORK_FINISHED, afterOrderFarmer.getState());
        OrderFarmerWorkSize afterOrderFarmerWorkSize = orderFarmerWorkSizeMapper
                .selectByPrimaryKey(orderFarmerWorkSize.getId());
        assertEquals(WorkSizeService.WORK_SIZE_STATE_FINISH, afterOrderFarmerWorkSize.getState());

        for (Long string : TEST_DRIVER_MEMBERS) {
            Pocket pocket = pocketMapper.selectByPrimaryKey(string);
            assertEquals((int) (size / desireNum * orderFarmer.getUni_price()) * orderFarmer.getDay_money_out_rate() / 10000, pocket.getWaitting_in()
                                                                                                                                    .intValue());
            assertEquals((int) (size / desireNum * orderFarmer.getUni_price()) * (10000 - orderFarmer.getDay_money_out_rate()) / 10000, pocket
                    .getCurr_money()
                    .intValue());
            OrderDriverWorkSizeCriteria orderDriverWorkSizeCriteria = new OrderDriverWorkSizeCriteria();
            orderDriverWorkSizeCriteria.or()
                                       .andOrder_farmer_work_size_idEqualTo(orderFarmerWorkSize.getId());
            OrderDriverWorkSize orderDriverWorkSize = orderDriverWorkSizeMapper
                    .selectByExample(orderDriverWorkSizeCriteria)
                    .get(0);
            assertEquals(false, orderDriverWorkSize == null);
        }
    }

    @Test
    public void TC0602_distributeSize_notLeader() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_DISTRIBUTE);
        orderFarmerMapper.insert(orderFarmer);

        OrderFarmerWorkSize orderFarmerWorkSize = MainTestDataCreator.createOrderFarmerWorkSize(TEST_FARMER_ORDER_ID, size,
                WorkSizeService.WORK_SIZE_STATE_ENSURED);
        orderFarmerWorkSizeMapper.insert(orderFarmerWorkSize);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }
        Map<Long, Double> everyOneWorks = new HashMap<>();
        for (Long driver : allDriver) {
            everyOneWorks.put(driver, size / desireNum);
        }
        try {
            orderBusiness.distributeWork(TEST_FARMER_ORDER_ID, everyOneWorks, TEST_FARMER_UID);
            fail();
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
        }

    }

    @Test
    public void TC0603_distributeSize_sizeTotalNotSame() throws BusinessException {
        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_WAITTING_DISTRIBUTE);
        orderFarmerMapper.insert(orderFarmer);

        OrderFarmerWorkSize orderFarmerWorkSize = MainTestDataCreator.createOrderFarmerWorkSize(TEST_FARMER_ORDER_ID, size,
                WorkSizeService.WORK_SIZE_STATE_ENSURED);
        orderFarmerWorkSizeMapper.insert(orderFarmerWorkSize);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }
        Map<Long, Double> everyOneWorks = new HashMap<>();
        for (Long driver : allDriver) {
            everyOneWorks.put(driver, size / desireNum + 1);
        }
        try {
            orderBusiness.distributeWork(TEST_FARMER_ORDER_ID, everyOneWorks, TEST_DRIVER_UID);
            fail();
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
        }
    }

    @Test
    public void TC0701_continueToNextDayWork_normal() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_TODAY_WORK_FINISHED);
        orderFarmerMapper.insert(orderFarmer);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }

        try {
            orderBusiness.continueToNextDayWork(TEST_FARMER_UID, TEST_FARMER_ORDER_ID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            throw e;
        }

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_WORKING, afterOrderFarmer.getState());
    }

    @Test
    public void TC0701_finishAll_normal() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_TODAY_WORK_FINISHED);
        orderFarmerMapper.insert(orderFarmer);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
        }

        try {
            orderBusiness.finishOrder(TEST_FARMER_UID, TEST_FARMER_ORDER_ID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            throw e;
        }

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_ALL_FINISHED, afterOrderFarmer.getState());
    }

    @Test
    public void TC0701_finishAll_with_refund_normal() throws BusinessException {

        final Integer desireNum = 1 + TEST_DRIVER_MEMBERS.length;
        final Double size = 100.0;

        List<Long> allDriver = new ArrayList<>();
        Collections.addAll(allDriver, TEST_DRIVER_MEMBERS);
        allDriver.add(TEST_DRIVER_UID);

        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_TODAY_WORK_FINISHED);
        orderFarmerMapper.insert(orderFarmer);

        for (Long driver : allDriver) {
            OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, driver,
                    OrderDriverService.ORDER_DRIVER_STATE_GOT);
            orderDriverMapper.insert(orderDriver);
            orderInsuranceRecordMapper.insert(
                    MainTestDataCreator.createOrderInsuranceRecord(driver, TEST_FARMER_ORDER_ID, INSURANCE_DEFAULT_MONEY));
            Pocket pocket = new Pocket();
            pocket.setUid(driver);
            pocket.setCurr_money(0);
            pocket.setInsurance(INSURANCE_DEFAULT_MONEY);
            pocket.setWaitting_in(20000);
            pocketMapper.updateByPrimaryKeySelective(pocket);

        }

        try {
            orderBusiness.finishOrder(TEST_FARMER_UID, TEST_FARMER_ORDER_ID);
        } catch (BusinessException e) {
            System.out.println(e.errorMessage);
            throw e;
        }

        allDriver.stream()
                 .forEach(driver -> checkMoneyAfterRefundInsurance(driver, TEST_FARMER_ORDER_ID,
                         INSURANCE_DEFAULT_MONEY, 0, INSURANCE_DEFAULT_MONEY));

        OrderFarmer afterOrderFarmer = orderFarmerMapper.selectByPrimaryKey(TEST_FARMER_ORDER_ID);
        assertEquals(OrderFarmerStateName.FARMER_ALL_FINISHED, afterOrderFarmer.getState());
    }

    @Test
    public void TC0801_createTeam_normal_invite_driver() {

    }

    private void checkMoneyAfterRefundInsurance(Long uid, String orderId, Integer pocketCurrentMoney,
                                                Integer pocketInsuranceMoney, Integer orderInsuranceMoney) {

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(uid)
                         .andConnect_order_idEqualTo(orderId)
                         .andTypeEqualTo(TradeFlowService.POCKETLOG_TYPE_REFUND_INSURANCE);
        PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria)
                                             .get(0);
        assertEquals(orderInsuranceMoney, pocketLog.getMoney());
        assertEquals(TradeFlowService.POCKETLOG_STATE_DONE, (long) pocketLog.getState());
        Pocket pocket = pocketMapper.selectByPrimaryKey(uid);
        assertEquals(pocketCurrentMoney, pocket.getCurr_money());
        assertEquals(pocketInsuranceMoney, pocket.getInsurance());
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andUidEqualTo(uid)
                                    .andOrder_farmer_idEqualTo(orderId);
        assertEquals(true, orderInsuranceRecordMapper.selectByExample(orderInsuranceRecordCriteria)
                                                     .isEmpty());
    }



}
