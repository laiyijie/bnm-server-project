package cn.bangnongmang.server.test;

import java.util.Date;
import java.util.Random;

import cn.bangnongmang.data.domain.*;


public class MainTestDataCreator {

    public static final Long DEFAULT_WORKING_TYPE_ID = Long.MAX_VALUE;

    public static OrderFarmer createOrderFarmer(Long uid, Integer num, Long startTime, String
            orderId) {
        OrderFarmer orderFarmer = new OrderFarmer();

        orderFarmer.setActual_finish_time(0L);
        orderFarmer.setActual_start_time(0L);
        orderFarmer.setUid(uid);
        orderFarmer.setCity("sh");
        orderFarmer.setCounty("xh");
        orderFarmer.setDesire_num(num);
        orderFarmer.setDesire_start_time(startTime);
        orderFarmer.setDetail("2103");
        orderFarmer.setDiscount(100);
        orderFarmer.setDriver_leader(null);
        orderFarmer.setGot_time(0L);
        orderFarmer.setName("ff");
        orderFarmer.setOrder_id(orderId);
        orderFarmer.setPre_pay_rate(100);
        orderFarmer.setProvince("sh");
        orderFarmer.setSize(200.0);
        orderFarmer.setState("FARMER_WAITTING_GOT");
        orderFarmer.setTags("[\"zz\",\"xx\"]");
        orderFarmer.setTel(String.valueOf(System.currentTimeMillis()));
        orderFarmer.setTown("tt");
        orderFarmer.setUni_price(9000);
        orderFarmer.setWorking_type_id(DEFAULT_WORKING_TYPE_ID);
        orderFarmer.setDriver_insurance(0);
        orderFarmer.setLeader_bonus(0);
        orderFarmer.setCompany_bonus(0);
        orderFarmer.setDay_money_out_rate(5000);
        return orderFarmer;
    }

    public static Friendship createFriendship(Long usera, Long userb) {
        Friendship friendship = new Friendship();
        friendship.setUsera(usera);
        friendship.setUserb(userb);
        friendship.setCreate_time(System.currentTimeMillis() / 1000);
        return friendship;
    }

    public static FriendshipSpecial createFriendshipSpecial(Long belongto, Long friendUid) {
        FriendshipSpecial friendshipSpecial = new FriendshipSpecial();
        friendshipSpecial.setBelongto(belongto);
        friendshipSpecial.setFriend_uid(friendUid);
        return friendshipSpecial;
    }

    public static AccountRealNameAuth createAccountRealNameAuth(Long uid, Integer state, String
            name) {
        AccountRealNameAuth accountRealNameAuth = new AccountRealNameAuth();
        accountRealNameAuth.setDown_side("");
        accountRealNameAuth.setFailed_reason("");
        accountRealNameAuth.setId_card_number("");
        accountRealNameAuth.setReal_name(name);
        accountRealNameAuth.setState(state);
        accountRealNameAuth.setUp_side("");
        accountRealNameAuth.setUpdate_time(System.currentTimeMillis() / 1000);
        accountRealNameAuth.setUid(uid);
        return accountRealNameAuth;
    }

    public static AccountPortrait createAccountPortrait(Long uid) {
        AccountPortrait accountPortrait = new AccountPortrait();
        accountPortrait.setPortrait_url("portrait_url");
        accountPortrait.setUid(uid);
        return accountPortrait;

    }

    public static TeamJoinRequest createTeamJoinRequest(Long id, Long teamId, Long requester, String
            msg) {
        TeamJoinRequest teamJoinRequest = new TeamJoinRequest();

        teamJoinRequest.setId(id);
        teamJoinRequest.setTeam_id(teamId);
        teamJoinRequest.setUid(requester);
        teamJoinRequest.setState(200);
        teamJoinRequest.setCreate_time(new Date().getTime());
        teamJoinRequest.setPs(msg);

        return teamJoinRequest;
    }

    public static TeamInOrder createTeamInOrder(Long id, String orderid, Long uid, String msg) {
        TeamInOrder teamInOrder = new TeamInOrder();

        teamInOrder.setId(id);
        teamInOrder.setOrder_id(orderid);
        teamInOrder.setUid(uid);
        teamInOrder.setDescription(msg);

        return teamInOrder;
    }

    public static GrabSeason createGrabseason(Integer state, String seasonId) {
        GrabSeason grabSeason = new GrabSeason();
        grabSeason.setEnd_time(System.currentTimeMillis() * 10);
        grabSeason.setStart_time(0L);
        grabSeason.setPs("xx");
        grabSeason.setId(seasonId);
        grabSeason.setState(state);
        grabSeason.setToatalsize(0.0);

        return grabSeason;
    }

    public static OrderGeo createOrderGeo(String order_Id) {
        OrderGeo orderGeo = new OrderGeo();
        orderGeo.setLatitude(88.0);
        orderGeo.setLongtitude(88.0);
        orderGeo.setOrder_id(order_Id);
        orderGeo.setPs("xx");
        return orderGeo;

    }

    public static SeasonOrders createSeasonOrders(String order_id, String season_id) {
        SeasonOrders seasonOrders = new SeasonOrders();
        seasonOrders.setOrder_id(order_id);
        seasonOrders.setSeason_id(season_id);
        return seasonOrders;
    }

    public static Account createAccount(Long uid, int level) {
        Account acc = new Account();
        acc.setUid(uid);
        acc.setUsername(String.valueOf(uid));

        acc.setCreate_time(System.currentTimeMillis() / 1000);

        acc.setIdcard_id("");

        acc.setLevel(level);

        acc.setName(String.valueOf(uid));

        acc.setNickname(String.valueOf(uid));

        acc.setOnetime_available_time(0L);

        acc.setOnetime_temp_password("");

        acc.setPassword("");

        acc.setSalt_string("");

        acc.setState(1);

        acc.setTel(String.valueOf(uid));

        acc.setTemp_available_time(0L);

        acc.setTemp_password("");

        acc.setWechat_info("");

        acc.setWechat_open_id("oHLNYv6cQUVxU1G4G_b31Oyb_FlY");

        acc.setWechat_union_id("");

        acc.setDriver_leader(200);

        return acc;
    }

    public static Region createRegion(String idRegion, Long belongto, String name) {

        Region region = new Region();

        region.setIdregion(idRegion);

        region.setUid(belongto);

        region.setProvince("上海");

        region.setCity("上海");

        region.setCounty("徐汇区");

        region.setTown("漕宝路");

        region.setVillage("72号");

        region.setDetail("帮农忙总部");

        region.setLevel(1);

        region.setName(name);

        region.setTel(name);

        region.setPs("");

        return region;
    }

    public static RegionGeo createRegionGeo(String id) {

        RegionGeo regionGeo = new RegionGeo();

        regionGeo.setLatitude(10.11);
        regionGeo.setLongtitude(11.11);
        regionGeo.setRegion_id(id);
        regionGeo.setPs("test");

        return regionGeo;
    }

    public static BankCard createBankCard(String card_number, Long uid, String type,
                                          String bank, String status,
                                          String phone) {
        BankCard BankCard = new BankCard();
        BankCard.setCard_number(card_number);
        BankCard.setUid(uid);
        BankCard.setType(type);
        BankCard.setBank(bank);
        BankCard.setStatus(status);
        BankCard.setPhone(phone);
        return BankCard;

    }

    public static Pocket createPocket(Long uid, Integer money) {
        Pocket pocket = new Pocket();
        pocket.setUid(uid);
        pocket.setCurr_money(money);
        pocket.setArrears(0);
        pocket.setCredit(0);
        pocket.setInsurance(0);
        pocket.setProvisions(0);
        pocket.setWaitting_in(0);
        return pocket;
    }

    public static OrderDriver createOrderDriver(String order_farmer_id, Long server_uid,
                                                String state) {

        OrderDriver orderDriver = new OrderDriver();
        orderDriver.setActual_money(0);
        orderDriver.setActual_size(0.0);
        orderDriver.setOrder_farmer_id(order_farmer_id);
        orderDriver.setOrder_id(
                String.valueOf(System.currentTimeMillis() * 100 + new Random().nextInt(100)));
        orderDriver.setUid(server_uid);
        orderDriver.setService_end(0L);
        orderDriver.setService_start(0L);
        orderDriver.setState(state);

        return orderDriver;

    }

    public static OrderFarmerWorkSize createOrderFarmerWorkSize(String order_farmer_id, Double size,
                                                                Integer state) {

        OrderFarmerWorkSize orderFarmerWorkSize = new OrderFarmerWorkSize();
        orderFarmerWorkSize.setId(System.currentTimeMillis() * 100 + new Random().nextInt(100));
        orderFarmerWorkSize.setOrder_farmer_id(order_farmer_id);
        orderFarmerWorkSize.setSize(size);
        orderFarmerWorkSize.setState(state);
        orderFarmerWorkSize.setTime(System.currentTimeMillis() / 1000);

        return orderFarmerWorkSize;

    }

    public static OrderDriverWorkSize createOrderDriverWorkSize(Long order_farmer_work_size_id,
                                                                Double size,
                                                                Long uid) {
        OrderDriverWorkSize orderDriverWorkSize = new OrderDriverWorkSize();
        orderDriverWorkSize.setId(System.currentTimeMillis() * 100 + new Random().nextInt(100));
        orderDriverWorkSize.setOrder_farmer_work_size_id(order_farmer_work_size_id);
        orderDriverWorkSize.setSize(size);
        orderDriverWorkSize.setUid(uid);

        return orderDriverWorkSize;
    }

    public static PhoneVerify createPhoneVerify(Integer id_phoneverify, String phoneNumber,
                                                String verify_code) {

        PhoneVerify phoneVerify = new PhoneVerify();
        phoneVerify.setId_phoneverify(id_phoneverify);
        phoneVerify.setPhonenumber(phoneNumber);
        phoneVerify.setVerify_code(verify_code);
        phoneVerify.setSend_time((long) 1988975283);
        return phoneVerify;
    }

    public static AccountGeo createAccountGeo(Long uid) {

        AccountGeo accountGeo = new AccountGeo();
        accountGeo.setUid(uid);
        accountGeo.setLatitude(14.00);
        accountGeo.setLongitude(14.00);
        accountGeo.setProvince("山西");
        accountGeo.setCity("太原");
        accountGeo.setDistrict("小店区");
        accountGeo.setStreet("龙山大");
        accountGeo.setStreet_number("20");
        accountGeo.setAddress("杀啥哈");
        accountGeo.setUpdate_time((long) 23400);
        return accountGeo;
    }

    public static StarUser createStarUserMapper(Long uid) {
        StarUser starUser = new StarUser();
        starUser.setUid(uid);
        starUser.setCaptain_star(4.0);
        starUser.setCaptain_evaluations(2);
        starUser.setMember_evaluations(0);
        starUser.setMember_star(1.0);
        return starUser;
    }

    public static FriendshipRequest creatFriendShipRequest(Long usera, Long userb) {
        FriendshipRequest friendShipRequest = new FriendshipRequest();
        friendShipRequest.setRequester(usera);
        friendShipRequest.setResponser(userb);
        friendShipRequest.setPs("一个请求");
        friendShipRequest.setCreate_time(System.currentTimeMillis());
        friendShipRequest.setState(1000);
        return friendShipRequest;
    }

    public static OrderInvite createOrderInvite(Long uid, String orderId) {
        OrderInvite orderInvite = new OrderInvite();
        orderInvite.setUid(uid);
        orderInvite.setFarmer_order_id(orderId);
        return orderInvite;
    }

    public static OrderInsuranceRecord createOrderInsuranceRecord(Long uid, String orderId,
                                                                  Integer money) {
        OrderInsuranceRecord orderInsuranceRecord = new OrderInsuranceRecord();
        orderInsuranceRecord.setMoney(money);
        orderInsuranceRecord.setUid(uid);
        orderInsuranceRecord.setOrder_farmer_id(orderId);
        return orderInsuranceRecord;
    }

    public static UserMachineType createUserMachineType(Long id, String name) {
        UserMachineType userMachineType = new UserMachineType();
        userMachineType.setDescripetion("test");
        userMachineType.setId(id);
        userMachineType.setType_name(name);
        return userMachineType;
    }

    public static UserMachineModel createUserMachineModel(Long id, Long typeId) {
        UserMachineModel userMachineModel = new UserMachineModel();
        userMachineModel.setId(id);
        userMachineModel.setModel_brand("test");
        userMachineModel.setModel_number("testnumber");
        userMachineModel.setModel_power(2.0);
        userMachineModel.setModel_width(1.5);
        userMachineModel.setMore_info_url("");
        userMachineModel.setSpecial_info("");
        userMachineModel.setState(400);
        userMachineModel.setUser_machine_type_id(typeId);
        return userMachineModel;

    }

    public static UserMachine createUserMachine(Long uid, Long machineModelId) {
        UserMachine userMachine = new UserMachine();
        userMachine.setState(400);
        userMachine.setUid(uid);
        userMachine.setBuy_time(0L);
        userMachine.setFailed_reason("");
        userMachine.setId(System.currentTimeMillis() % 1000 * 100000000 + new Random()
                .nextInt(100000000));
        userMachine.setMachine_model_id(machineModelId);
        return userMachine;
    }

    public static OptionWorkingType createOptionWorkingType() {
        OptionWorkingType optionWorkingType = new OptionWorkingType();
        optionWorkingType.setId(DEFAULT_WORKING_TYPE_ID);
        optionWorkingType.setCrop_type("test");
        optionWorkingType.setWorking_type("test working");
        return optionWorkingType;
    }

    public static OptionWorkingTypeMachineModelMappingKey createOptionWorkingTypeMachineModelMapping(
            Long machineModelId, Long workingTypeId) {
        OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey = new OptionWorkingTypeMachineModelMappingKey();
        optionWorkingTypeMachineModelMappingKey.setMachine_model_id(machineModelId);
        optionWorkingTypeMachineModelMappingKey.setOption_working_type_id(workingTypeId);
        return optionWorkingTypeMachineModelMappingKey;
    }

    public static OptionMachineModelMappingKey createOptionMachineModelMapping(long model_id, long option_id) {
        OptionMachineModelMappingKey model = new OptionMachineModelMappingKey();
        model.setModel_id(model_id);
        model.setOption_id(option_id);
        return model;
    }

    public static OptionClusterWorkingTypeMappingKey createOptionClusterWorkingTypeMappingKey(long cluster_id, long working_type_id) {
        OptionClusterWorkingTypeMappingKey key = new OptionClusterWorkingTypeMappingKey();
        key.setCluster_id(cluster_id);
        key.setWorking_type_id(working_type_id);
        return key;
    }

    public static OptionDetail createOptionDetail(long id, String Option_name, long clusterid) {
        OptionDetail detail = new OptionDetail();
        detail.setId(id);
        detail.setCluster_id(clusterid);
        detail.setOption_name(Option_name);
        detail.setDescription("xx");

        return detail;

    }


    public static OptionOrderMappingKey createOptionOrderMapping(long option_id, String order_id) {
        OptionOrderMappingKey optionOrderMappingKey = new OptionOrderMappingKey();
        optionOrderMappingKey.setOption_id(option_id);
        optionOrderMappingKey.setOrder_id(order_id);
        return optionOrderMappingKey;

    }

    public static OptionCluster createOptionCluster(long clusterid, String cluster_name, String description) {
        OptionCluster optionCluster = new OptionCluster();
        optionCluster.setId(clusterid);
        optionCluster.setCluster_name(cluster_name);
        optionCluster.setDescription(description);
        return optionCluster;

    }


}
