package cn.bangnongmang.admin.business;

import cn.bangnongmang.admin.testutil.AdminTestDataCreator;
import cn.bangnongmang.admin.testutil.ServiceConfigUtil;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.test.MainTestDataCreator;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.service.service.UserService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by admin on 2017-05-05.
 */

public class WorkTeamManagerBusinessTest extends ServiceConfigUtil {


    @Autowired
    private WorkTeamManageBusiness business;

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
        Pocket pocketLeader = MainTestDataCreator.createPocket(TEST_DRIVER_UID, INSURANCE_DEFAULT_MONEY);
        accountMapper.insert(account);
        accountRealNameAuthMapper.insert(accountRealNameAuth);
        pocketMapper.insert(pocketLeader);
        for (Long driverPhonNumber : TEST_DRIVER_MEMBERS) {
            account = MainTestDataCreator.createAccount(driverPhonNumber, UserService.AUTHENTICATED_DRIVER);
            accountRealNameAuth = MainTestDataCreator.createAccountRealNameAuth(driverPhonNumber,
                    UserService.REAL_NAME_AUTH_STATE_PASS, "测试机手xx");
            Pocket pocket = MainTestDataCreator.createPocket(driverPhonNumber, INSURANCE_DEFAULT_MONEY);
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
        TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
        teamInOrderCriteria.or()
                           .andOrder_idEqualTo(TEST_FARMER_ORDER_ID);
        teamInOrderMapper.deleteByExample(teamInOrderCriteria);
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
        rmAllOrderInsuranceRecord();
    }


    private void rmAllOrderInsuranceRecord() {
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
        allUser.forEach(user -> orderInsuranceRecordCriteria.or()
                                                            .andUidEqualTo(user));
        orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);
    }

    @Test
    public void TC0101_addMemberTest() throws BusinessException {
        final Integer desireNum = 1;
        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_GOT);
        orderFarmer.setDriver_insurance(INSURANCE_DEFAULT_MONEY);
        OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID,
                OrderDriverService.ORDER_DRIVER_STATE_GOT);
        orderDriverMapper.insert(orderDriver);
        orderFarmerMapper.insert(orderFarmer);

        teamInOrderMapper.insert(MainTestDataCreator.createTeamInOrder(TEST_TEAM_IN_ORDER_ID, TEST_FARMER_ORDER_ID, TEST_DRIVER_UID, "zuizuizui"));


        for (Long member : TEST_DRIVER_MEMBERS) {
            business.addMember(member, TEST_FARMER_ORDER_ID, true);
            Pocket pocket = pocketMapper.selectByPrimaryKey(member);
            assertEquals(0, pocket.getCurr_money()
                                  .intValue());
            assertEquals(INSURANCE_DEFAULT_MONEY, pocket.getInsurance());
        }
    }

    @Test
    public void TC0201_deleteMemberTest() throws BusinessException {
        final Integer desireNum = 1;
        final Integer leftMoney = 10000;
        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_GOT);
        orderFarmer.setDriver_insurance(INSURANCE_DEFAULT_MONEY);
        OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID,
                OrderDriverService.ORDER_DRIVER_STATE_GOT);
        orderDriverMapper.insert(orderDriver);
        orderFarmerMapper.insert(orderFarmer);

        teamInOrderMapper.insert(MainTestDataCreator.createTeamInOrder(TEST_TEAM_IN_ORDER_ID, TEST_FARMER_ORDER_ID, TEST_DRIVER_UID, "zuizuizui"));


        for (Long member : TEST_DRIVER_MEMBERS) {
            business.addMember(member, TEST_FARMER_ORDER_ID, true);
            business.removeMember(member, TEST_FARMER_ORDER_ID, INSURANCE_DEFAULT_MONEY - leftMoney,true);
            Pocket pocket = pocketMapper.selectByPrimaryKey(member);
            assertEquals(leftMoney, pocket.getCurr_money());
            assertEquals(0, pocket.getInsurance()
                                  .intValue());
        }

    }

    @Test
    public void TC0301_changeLeaderTest() throws BusinessException {
        final Integer desireNum = 1;
        OrderFarmer orderFarmer = MainTestDataCreator.createOrderFarmer(TEST_FARMER_UID, desireNum,
                System.currentTimeMillis() / 1000, TEST_FARMER_ORDER_ID);
        orderFarmer.setDriver_leader(TEST_DRIVER_UID);
        orderFarmer.setState(OrderFarmerStateName.FARMER_GOT);
        orderFarmer.setDriver_insurance(INSURANCE_DEFAULT_MONEY);
        OrderDriver orderDriver = MainTestDataCreator.createOrderDriver(TEST_FARMER_ORDER_ID, TEST_DRIVER_UID,
                OrderDriverService.ORDER_DRIVER_STATE_GOT);
        orderDriverMapper.insert(orderDriver);
        orderFarmerMapper.insert(orderFarmer);

        teamInOrderMapper.insert(MainTestDataCreator.createTeamInOrder(TEST_TEAM_IN_ORDER_ID, TEST_FARMER_ORDER_ID, TEST_DRIVER_UID, "zuizuizui"));


        for (Long member : TEST_DRIVER_MEMBERS) {
            business.addMember(member, TEST_FARMER_ORDER_ID, true);

        }
        business.changeLeader(TEST_DRIVER_MEMBERS[0], TEST_FARMER_ORDER_ID);
        TeamInOrder teamInOrder = teamInOrderMapper.selectByPrimaryKey(TEST_TEAM_IN_ORDER_ID);
        assertEquals(TEST_DRIVER_MEMBERS[0], teamInOrder.getUid());
    }
}
