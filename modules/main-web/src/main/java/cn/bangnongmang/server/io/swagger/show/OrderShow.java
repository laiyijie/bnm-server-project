package cn.bangnongmang.server.io.swagger.show;

import cn.bangnongmang.data.combo.dao.*;
import cn.bangnongmang.data.combo.domain.OrderDriverWorkSizeInfoShow;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.dao.OrderFarmerWorkSizeMapper;
import cn.bangnongmang.data.dao.OrderMultiInfoMapper;
import cn.bangnongmang.data.dao.OrderWorkSizeImageMapper;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.driverapp.models.OrderTeamSimple;
import cn.bangnongmang.server.swagger.model.OrderMultiInfo;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.swagger.OrderConverter;
import cn.bangnongmang.server.io.swagger.TeamConverter;
import cn.bangnongmang.server.io.swagger.UserConverter;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.server.swagger.model.*;
import cn.bangnongmang.server.swagger.model.GrabSeason;
import cn.bangnongmang.service.service.OrderMultiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-17.
 */
@Service
public class OrderShow {
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private TeamConverter teamConverter;

    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;
    @Autowired
    private OrderDriverMapper orderDriverMapper;
    @Autowired
    private OrderFarmerService orderFarmerService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    @Autowired
    private TeamInfoShowMapper teamInfoShowMapper;
    @Autowired
    private GrabSeasonInfoShowMapper grabSeasonInfoShowMapper;

    @Autowired
    private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;
    @Autowired
    private OrderDriverWorkSizeInfoShowMapper orderDriverWorkSizeInfoShowMapper;
    @Autowired
    private OrderWorkSizeImageMapper orderWorkSizeImageMapper;
    @Autowired
    private OrderMultiInfoMapper orderMultiInfoMapper;

    public List<OrderMultiInfo> getOrderMultiInfoByOrderId(String orderId) {
        OrderMultiInfoCriteria orderMultiInfoCriteria = new OrderMultiInfoCriteria();
        orderMultiInfoCriteria.or()
                              .andOrder_idEqualTo(orderId)
                              .andStateEqualTo(OrderMultiInfoService.STATE_PUBLISH);
        return orderMultiInfoMapper.selectByExample(orderMultiInfoCriteria)
                                   .stream()
                                   .map(info -> orderConverter.convertToOrderMultiInfo(info))
                                   .collect(
                                           Collectors.toList());
    }

    public List<SubOrder> getSubOrderByOrderId(String orderId) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        return orderDriverMapper.selectByExample(orderDriverCriteria)
                                .stream()
                                .map(orderDriver -> orderConverter.convertToSubOrder(orderDriver))
                                .collect(Collectors.toList());
    }

    public List<WorkSizeAuthImage> getWorkSizeAuthImageByWorkSizeId(Long workSizeId) {
        OrderWorkSizeImageCriteria orderWorkSizeImageCriteria = new OrderWorkSizeImageCriteria();
        orderWorkSizeImageCriteria.or()
                                  .andOrder_farmer_work_size_idEqualTo(workSizeId);
        return orderWorkSizeImageMapper.selectByExample(orderWorkSizeImageCriteria)
                                       .stream()
                                       .map(orderWorkSizeImage -> orderConverter
                                               .convertToWorkSizeAuthImage(orderWorkSizeImage))
                                       .collect(Collectors.toList());
    }

    public Order getOrderByOrderId(String orderId) {
        OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByOrderId(orderId);
        return orderConverter.convertToOrder(orderFarmerInfoShow);
    }

    //TODO 使用联查来解决这个问题，组队的问题也需要重新考虑，感觉现在的组队方式并不是最好的方式，应该通过可以保留的方式来实现组队的这个流程！！！
    public List<UserSimple> getMembersInOrder(String orderId) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        List<UserSimple> userSimples = new ArrayList<>();
        for (OrderDriver orderDriver : orderDrivers) {
            userSimples.add(userConverter.convertToUserSimple(userBasicInfoShowMapper
                    .selectByUid(orderDriver.getUid()), UserSimple.class));
        }
        userSimples.removeIf(Objects::isNull);
        return userSimples;
    }

    public List<Team> getTeamsInOrder(String orderId) throws BusinessException {
        OrderFarmer orderFarmer = orderFarmerService.getOrderFarmerById(orderId);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }

        List<OrderTeamSimple> result = new ArrayList<>();

        List<TeamInfoShow> tInfoShows = teamInfoShowMapper.selectTeamInfoShowByOrderId(orderId);

        return tInfoShows.stream()
                         .map(
                                 teamInfoShow -> teamConverter.convertToTeam(teamInfoShow))
                         .collect(
                                 Collectors.toList());
    }

    public List<Order> getUserFavoriteOrders(Long uid) {
        return orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByUserFavorite(uid)
                                        .stream
                                                ()
                                        .map(orderFarmerInfoShow -> orderConverter.convertToOrder(
                                                orderFarmerInfoShow))
                                        .collect(Collectors.toList());
    }

    public List<Order> getMyOrders(Long uid) {
        return orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByDriver(uid)
                                        .stream()
                                        .map(orderFarmerInfoShow -> orderConverter.convertToOrder
                                                (orderFarmerInfoShow))
                                        .collect(Collectors.toList());
    }

    public boolean isMyOrder(Long uid, String orderId) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId)
                           .andUidEqualTo(uid);
        if (orderDriverMapper.countByExample(orderDriverCriteria) != 0) {
            return true;
        }
        return false;
    }

    public SubOrder getMySubOrder(Long uid, String orderId) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId)
                           .andUidEqualTo(uid);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        if (!orderDrivers.isEmpty()) {
            return orderConverter.convertToSubOrder(orderDrivers.get(0));
        }
        return null;
    }

    public List<Order> getShowOrders() {
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
        return shows.stream()
                    .map(show -> orderConverter.convertToOrder(show))
                    .collect(Collectors
                            .toList());
    }

    public List<OrderFarmerWorkInfo> getOrderFarmerWorkInfos(String orderId) {
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or()
                                   .andOrder_farmer_idEqualTo(orderId);
        List<OrderFarmerWorkSize> orderFarmerWorkSizes = orderFarmerWorkSizeMapper
                .selectByExample(orderFarmerWorkSizeCriteria);
        List<OrderDriverWorkSizeInfoShow> orderDriverWorkSizeInfoShows =
                orderDriverWorkSizeInfoShowMapper
                        .getOrderDriverWorkSizeInfoShowListByOrderFarmerId(orderId);
        return orderFarmerWorkSizes.stream()
                                   .map(orderFarmerWorkSize -> orderConverter
                                           .convertToOrderFarmerWorkInfo(orderFarmerWorkSize,
                                                   orderDriverWorkSizeInfoShows
                                                           .stream()
                                                           .filter(driverSize -> driverSize.getOrderDriverWorkSize()
                                                                                           .getOrder_farmer_work_size_id()
                                                                                           .equals(orderFarmerWorkSize.getId
                                                                                                   ()))
                                                           .collect(Collectors.toList())))
                                   .collect(Collectors.toList());
    }

    public List<GrabSeason> getNextAvailableGrabSeasons() {
        return grabSeasonInfoShowMapper.selectGrabSeasonInfoShowByMinEndTime(System
                .currentTimeMillis()
                / 1000)
                                       .stream()
                                       .filter(grabSeasonInfoShow -> grabSeasonInfoShow
                                               .getGrabSeason()
                                               .getState()
                                               .equals
                                                       (OrderFarmerService.GRAB_SEASON_STATE_PUBLISHED))
                                       .map(grabSeasonInfoShow -> orderConverter.convertToGrabSeason
                                               (grabSeasonInfoShow))
                                       .collect(Collectors.toList());
    }

}
