package cn.bangnongmang.server.io.android.show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.bangnongmang.data.combo.domain.*;
import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.driverapp.models.*;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.service.service.WorkSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.GrabSeasonInfoShowMapper;
import cn.bangnongmang.data.combo.dao.OrderDriverWorkSizeInfoShowMapper;
import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.driverapp.models.OrderFarmerWorkSizeInfo.OrderDriverWorkSizeInfo;
import cn.bangnongmang.service.service.OrderFarmerService;

@Service
public class AndroidOrderShow {

    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;

    @Autowired
    private OrderDriverMapper orderDriverMapper;

    @Autowired
    private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;
    @Autowired
    private OrderDriverWorkSizeInfoShowMapper orderDriverWorkSizeInfoShowMapper;

    @Autowired
    private AndroidUserShow androidUserShow;
    @Autowired
    private AndroidMachineShow androidMachineShow;

    @Autowired
    private GrabSeasonMapper grabSeasonMapper;
    @Autowired
    private SeasonOrdersMapper seasonOrdersMapper;

    @Autowired
    private OrderFarmerService orderFarmerService;

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    @Autowired
    private GrabSeasonInfoShowMapper grabSeasonInfoShowMapper;

    @Autowired
    private OrderWorkSizeImageMapper orderWorkSizeImageMapper;

    @Autowired
    private WorkSizeService workSizeService;

    public List<WorkSizeAuthImage> getOrderWorkSizeImages(String orderId) {
        OrderFarmerWorkSize orderFarmerWorkSize = workSizeService
                .getOrderFarmerWorkSizeByOrderIdAndState(orderId, WorkSizeService
                        .WORK_SIZE_STATE_WAITTING_ENSURE);
        if (orderFarmerWorkSize == null)
            return new ArrayList<>();
        OrderWorkSizeImageCriteria orderWorkSizeImageCriteria = new OrderWorkSizeImageCriteria();
        orderWorkSizeImageCriteria.or()
                .andOrder_farmer_work_size_idEqualTo(orderFarmerWorkSize.getId());
        return orderWorkSizeImageMapper.selectByExample(orderWorkSizeImageCriteria)
                .stream()
                .map(this::convertToWorkSizeAuthImage)
                .collect(
                        Collectors.toList());

    }

    public WorkSizeAuthImage convertToWorkSizeAuthImage(OrderWorkSizeImage orderWorkSizeImage) {
        WorkSizeAuthImage workSizeAuthImage = new WorkSizeAuthImage();
        workSizeAuthImage.setId(orderWorkSizeImage.getId());
        workSizeAuthImage.setImageUrl(orderWorkSizeImage.getImage_path());
        workSizeAuthImage
                .setOrderFarmerWorkInfoId(orderWorkSizeImage.getOrder_farmer_work_size_id());
        workSizeAuthImage.setTitle(orderWorkSizeImage.getTitile());
        return workSizeAuthImage;
    }

    public List<OrderInfoSimple> getMyFavoriteOrderList(Long uid) {

        return orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByUserFavorite(uid)
                .stream()
                .map(orderFarmerInfoShow -> convertOrderFarmerInfoShowToOrderInfoSimple
                        (OrderInfoSimple.class, orderFarmerInfoShow,
                                grabSeasonInfoShowMapper
                                        .selectGrabSeasonInfoShowByMinEndTime(
                                                System.currentTimeMillis())))
                .collect(Collectors.toList());
    }

    public List<UserInfoSimple> getMemberInOrder(String orderId) {

        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);

        List<UserInfoSimple> userInfoSimples = new ArrayList<>();

        for (OrderDriver orderDriver : orderDrivers) {
            userInfoSimples.add(androidUserShow.convertUserBasicInfoShowToUserInfoSimple(
                    userBasicInfoShowMapper.selectByUid(orderDriver.getUid())));
        }
        return userInfoSimples;

    }

    public List<OrderFarmerWorkSizeInfo> getOrderFarmerWorkSizeInfo(String orderId) {

        List<OrderFarmerWorkSize> orderFarmerWorkSizes = getOrderFarmerWorkSizeList(orderId);
        List<OrderDriverWorkSizeInfoShow> orderDriverWorkSizeInfoShows = orderDriverWorkSizeInfoShowMapper
                .getOrderDriverWorkSizeInfoShowListByOrderFarmerId(orderId);

        List<OrderFarmerWorkSizeInfo> orderFarmerWorkSizeInfos = new ArrayList<>();

        Map<Long, List<OrderDriverWorkSizeInfo>> map = new HashMap<>();

        for (OrderDriverWorkSizeInfoShow orderDriverWorkSizeInfoShow : orderDriverWorkSizeInfoShows) {
            if (!map.containsKey(
                    orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                            .getOrder_farmer_work_size_id())) {
                map.put(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                                .getOrder_farmer_work_size_id(),
                        new ArrayList<>());
            }
            OrderDriverWorkSizeInfo orderDriverWorkSizeInfo = new OrderDriverWorkSizeInfo();
            orderDriverWorkSizeInfo.setName(
                    orderDriverWorkSizeInfoShow.getUserBasicInfoShow() != null
                            && orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountRealNameAuth() != null
                            ? orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountRealNameAuth()
                            .getReal_name()
                            : AndroidUserShow.UNAUTHED_USER_NAME);
            orderDriverWorkSizeInfo.setPortraitUrl(
                    orderDriverWorkSizeInfoShow.getUserBasicInfoShow() != null
                            && orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountPortrait() != null
                            ? orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountPortrait()
                            .getPortrait_url()
                            : "");
            orderDriverWorkSizeInfo.setRealSize(
                    orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                            .getSize());
            orderDriverWorkSizeInfo
                    .setUsername(
                            orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                                    .getAccount()
                                    .getUsername());
            map.get(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                    .getOrder_farmer_work_size_id())
                    .add(orderDriverWorkSizeInfo);
        }

        for (OrderFarmerWorkSize orderFarmerWorkSize : orderFarmerWorkSizes) {

            OrderFarmerWorkSizeInfo orderFarmerWorkSizeInfo = new OrderFarmerWorkSizeInfo();
            orderFarmerWorkSizeInfo.setId(orderFarmerWorkSize.getId());
            orderFarmerWorkSizeInfo.setOrder_farmer_id(orderFarmerWorkSize.getOrder_farmer_id());
            orderFarmerWorkSizeInfo.setSize(orderFarmerWorkSize.getSize());
            orderFarmerWorkSizeInfo.setState(orderFarmerWorkSize.getState());
            orderFarmerWorkSizeInfo.setTime(orderFarmerWorkSize.getTime());
            if (map.containsKey(orderFarmerWorkSize.getId())) {
                orderFarmerWorkSizeInfo.setOrderDriverWorkSizeInfos(
                        map.get(orderFarmerWorkSize.getId()));
            }
            orderFarmerWorkSizeInfos.add(orderFarmerWorkSizeInfo);
        }
        return orderFarmerWorkSizeInfos;
    }

    public MySubOrderInfo getMySubOrderInfo(String orderFarmerId, Long uid) {

        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                .andOrder_farmer_idEqualTo(orderFarmerId)
                .andUidEqualTo(uid);

        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);

        if (orderDrivers.isEmpty()) {
            return null;
        }
        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(uid);
        return convertOrderDriverToMySubOrderInfo(orderDrivers.get(0),
                userBasicInfoShow.getAccount());

    }

    public MySubOrderInfo convertOrderDriverToMySubOrderInfo(OrderDriver orderDriver,
                                                             Account account) {

        if (orderDriver == null) {
            return null;
        }

        MySubOrderInfo mySubOrderInfo = new MySubOrderInfo();

        mySubOrderInfo.setActualMoney(orderDriver.getActual_money());
        mySubOrderInfo.setActualSize(orderDriver.getActual_size());
        mySubOrderInfo.setEndTime(orderDriver.getService_end());
        mySubOrderInfo.setOrderFarmerId(orderDriver.getOrder_farmer_id());
        mySubOrderInfo.setOrderId(orderDriver.getOrder_id());
        mySubOrderInfo.setStartTime(orderDriver.getService_start());
        mySubOrderInfo.setState(orderDriver.getState());
        mySubOrderInfo.setUsername(account.getUsername());
        return mySubOrderInfo;

    }

    public List<OrderFarmerWorkSize> getOrderFarmerWorkSizeList(String orderId) {
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or()
                .andOrder_farmer_idEqualTo(orderId);
        return orderFarmerWorkSizeMapper.selectByExample(orderFarmerWorkSizeCriteria);
    }

    public Boolean checkWhetherIGotTheOrder(String orderid, Long uid) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                .andUidEqualTo(uid)
                .andOrder_farmer_idEqualTo(
                        orderid);
        if (orderDriverMapper.countByExample(orderDriverCriteria) > 0) {
            return true;
        }
        return false;
    }

    public List<MyOrderInfoDetail> showMyGotOrderInfoSimple(Long uid) {
        List<MyOrderInfoDetail> result = new ArrayList<>();

        List<OrderFarmerInfoShow> shows = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByDriver(uid);

        for (OrderFarmerInfoShow orderFarmerInfoShow : shows) {
            result.add(convertOrderFarmerInfoShowToMyOrderInfoDetail(orderFarmerInfoShow,
                    grabSeasonInfoShowMapper.selectGrabSeasonInfoShowByMinEndTime(
                            System.currentTimeMillis() / 1000)));
        }

        return result;

    }

    public List<OrderInfoSimple> showOrderInfoSimpleList() {
        List<OrderInfoSimple> result = new ArrayList<>();
        List<SeasonOrders> currSeasonOrders = orderFarmerService.getCurrentSeasonOrders();
        List<OrderFarmerInfoShow> shows = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByState(OrderFarmerStateName.FARMER_WAITTING_GOT);
        List<OrderFarmerInfoShow> grabedOrderShows = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByState(OrderFarmerStateName.FARMER_GOT);
        for (OrderFarmerInfoShow ofis : grabedOrderShows) {
            if (currSeasonOrders.stream()
                    .anyMatch(
                            seasonOrder -> seasonOrder.getOrder_id()
                                    .equals(ofis.getId
                                            ()))) {
                shows.add(ofis);
            }
        }

        for (OrderFarmerInfoShow orderFarmerInfoShow : shows) {
            result.add(convertOrderFarmerInfoShowToOrderInfoSimple(OrderInfoSimple.class,
                    orderFarmerInfoShow,
                    grabSeasonInfoShowMapper
                            .selectGrabSeasonInfoShowByMinEndTime(
                                    System.currentTimeMillis() / 1000)));
        }

        return result;

    }

    public OrderInfoDetail getOrderInfoDetailByOrderId(String orderId) {
        return convertOrderFarmerInfoShowToOrderInfoDetail(OrderInfoDetail.class,
                orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId),
                grabSeasonInfoShowMapper.selectGrabSeasonInfoShowByMinEndTime(
                        System.currentTimeMillis() / 1000));
    }

    public MyOrderInfoDetail getMyOrderInfoDetailByOrderId(String orderId) {
        return convertOrderFarmerInfoShowToMyOrderInfoDetail(
                orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId),
                grabSeasonInfoShowMapper.selectGrabSeasonInfoShowByMinEndTime(
                        System.currentTimeMillis() / 1000));
    }

    public OrderInfoSimple getOrderInfoSimpleByOrderId(String orderId) {

        return convertOrderFarmerInfoShowToOrderInfoSimple(OrderInfoSimple.class,
                orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId),
                grabSeasonInfoShowMapper.selectGrabSeasonInfoShowByMinEndTime(
                        System.currentTimeMillis() / 1000));

    }

    @SuppressWarnings("unchecked")
    public <T extends OrderInfoSimple> T convertOrderFarmerInfoShowToOrderInfoSimple(Class<T> dst,
                                                                                     OrderFarmerInfoShow ofis,
                                                                                     List<GrabSeasonInfoShow> gsis) {

        if (ofis == null) {
            return null;
        }
        gsis.removeIf(gs -> !gs.getGrabSeason()
                .getState()
                .equals(OrderFarmerService
                        .GRAB_SEASON_STATE_PUBLISHED));
        OrderInfoSimple orderInfoSimple;
        try {
            orderInfoSimple = dst.newInstance();

            Long minTime = Long.MAX_VALUE;
            GrabSeasonInfoShow choosen = null;
            for (GrabSeasonInfoShow grabSeasonInfoShow : gsis) {
                if (grabSeasonInfoShow.getSeasonOrders()
                        .stream()
                        .anyMatch(so -> so.getOrder_id()
                                .equals(ofis.getId()))
                        && minTime > grabSeasonInfoShow.getGrabSeason()
                        .getStart_time()) {
                    minTime = grabSeasonInfoShow.getGrabSeason()
                            .getStart_time();
                    choosen = grabSeasonInfoShow;
                }
            }
            if (choosen != null) {
                orderInfoSimple.setCanGrabTime(minTime * 1000);
                orderInfoSimple.setStopGrabTime(choosen.getGrabSeason()
                        .getEnd_time() * 1000);
                orderInfoSimple.setInGrabSeason(true);
            } else {
                orderInfoSimple.setInGrabSeason(false);
            }
            OrderGeoInfo orderGeoInfo = new OrderGeoInfo();
            orderGeoInfo.setCity(ofis.getOrderFarmer()
                    .getCity());
            orderGeoInfo.setDistrict(ofis.getOrderFarmer()
                    .getCounty());
            if (ofis.getOrderGeo() != null) {
                orderGeoInfo.setLatitude(ofis.getOrderGeo()
                        .getLatitude());
                orderGeoInfo.setLongitude(ofis.getOrderGeo()
                        .getLongtitude());
            }
            orderGeoInfo.setProvince(ofis.getOrderFarmer()
                    .getProvince());

            orderInfoSimple.setGeoInfo(orderGeoInfo);
            orderInfoSimple.setNum(ofis.getOrderFarmer()
                    .getDesire_num());
            orderInfoSimple.setOrderId(ofis.getId());
            orderInfoSimple.setSize(ofis.getOrderFarmer()
                    .getSize());
            orderInfoSimple.setUniPrice(ofis.getOrderFarmer()
                    .getUni_price());
            orderInfoSimple.setWorkTypeId(ofis.getOptionWorkingType()
                    .getId());
            orderInfoSimple.setStartTime(ofis.getOrderFarmer()
                    .getDesire_start_time() * 1000);
            orderInfoSimple.setOptionInfos(ofis.getOptions()
                    .stream()
                    .map(a -> androidMachineShow.convertOptionClusterDetailInfoToOptionInfo(
                            a))
                    .collect(Collectors.toList()));
            orderInfoSimple.setState(ofis.getOrderFarmer()
                    .getState());
            orderInfoSimple.setOrderInsurance(ofis.getOrderFarmer()
                    .getDriver_insurance());
            orderInfoSimple.setCompanyBonus(ofis.getOrderFarmer()
                    .getCompany_bonus());
            orderInfoSimple.setLeaderBonus(ofis.getOrderFarmer()
                    .getLeader_bonus());
            return (T) orderInfoSimple;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public <T extends OrderInfoDetail> T convertOrderFarmerInfoShowToOrderInfoDetail(Class<T> dst,
                                                                                     OrderFarmerInfoShow orderFarmerInfoShow,
                                                                                     List<GrabSeasonInfoShow> gsis) {

        if (orderFarmerInfoShow == null) {
            return null;
        }

        OrderInfoDetail orderInfoDetail;
        orderInfoDetail = convertOrderFarmerInfoShowToOrderInfoSimple(dst, orderFarmerInfoShow,
                gsis);

        if (orderFarmerInfoShow.getOptionWorkingType() != null) {
            orderInfoDetail.setCropType(orderFarmerInfoShow.getOptionWorkingType()
                    .getCrop_type());
            orderInfoDetail.setWorkingType(
                    orderFarmerInfoShow.getOptionWorkingType()
                            .getWorking_type());
        }
        orderInfoDetail.setGotTime(orderFarmerInfoShow.getOrderFarmer()
                .getGot_time() != null
                ? orderFarmerInfoShow.getOrderFarmer()
                .getGot_time() * 1000 : null);
        orderInfoDetail.setTags(
                JSON.parseArray(orderFarmerInfoShow.getOrderFarmer()
                        .getTags(), String.class));
        return (T) orderInfoDetail;
    }

    public MyOrderInfoDetail convertOrderFarmerInfoShowToMyOrderInfoDetail(
            OrderFarmerInfoShow orderFarmerInfoShow,
            List<GrabSeasonInfoShow> gsis) {
        if (orderFarmerInfoShow == null) {
            return null;
        }
        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper
                .selectByUid(orderFarmerInfoShow.getOrderFarmer()
                        .getDriver_leader());
        MyOrderInfoDetail myOrderInfoDetail = convertOrderFarmerInfoShowToOrderInfoDetail(
                MyOrderInfoDetail.class,
                orderFarmerInfoShow, gsis);

        MyOrderGeoInfo myOrderGeoInfo = new MyOrderGeoInfo();
        myOrderGeoInfo.setCity(orderFarmerInfoShow.getOrderFarmer()
                .getCity());
        myOrderGeoInfo.setDistrict(orderFarmerInfoShow.getOrderFarmer()
                .getCounty());
        if (orderFarmerInfoShow.getOrderGeo() != null) {
            myOrderGeoInfo.setLatitude(orderFarmerInfoShow.getOrderGeo()
                    .getLatitude());
            myOrderGeoInfo.setLongitude(orderFarmerInfoShow.getOrderGeo()
                    .getLongtitude());
        }
        myOrderGeoInfo.setProvince(orderFarmerInfoShow.getOrderFarmer()
                .getProvince());
        myOrderGeoInfo.setTown(orderFarmerInfoShow.getOrderFarmer()
                .getTown());
        myOrderGeoInfo.setDetail(orderFarmerInfoShow.getOrderFarmer()
                .getDetail());

        myOrderInfoDetail.setFarmerName(orderFarmerInfoShow.getOrderFarmer()
                .getName());
        myOrderInfoDetail.setFarmerTel(orderFarmerInfoShow.getOrderFarmer()
                .getTel());
        myOrderInfoDetail.setLeader(userBasicInfoShow.getAccount().getUsername());
        myOrderInfoDetail.setMyOrderGeoInfo(myOrderGeoInfo);
        return myOrderInfoDetail;
    }

}
