package cn.bangnongmang.server.io.wechat.show;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.OrderDriverWorkSizeInfoShowMapper;
import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderDriverWorkSizeInfoShow;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.driverapp.models.OrderFarmerStateName;
import cn.bangnongmang.server.io.wechat.data.OrderFarmerInfo;
import cn.bangnongmang.server.io.wechat.data.OrderFarmerWorkSizeInfo;
import cn.bangnongmang.server.io.wechat.data.OrderFarmerWorkSizeInfo.OrderDriverWorkSizeInfo;
import cn.bangnongmang.service.service.WorkSizeService;

@Service
public class WxOrderShow implements OrderFarmerStateName {

    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;

    @Autowired
    private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;

    @Autowired
    private OptionClusterMapper optionClusterMapper;
    @Autowired
    private OptionDetailMapper optionDetailMapper;

    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;

    @Autowired
    private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;

    @Autowired
    private OrderDriverWorkSizeInfoShowMapper orderDriverWorkSizeInfoShowMapper;

    @Autowired
    private AccountMapper accountMapper;

    public static final String UNAUTHED_USER_REAL_NAME = "未认证用户";

    public List<OrderFarmerWorkSizeInfo> getOrderFarmerWorkSizeInfoByOrderFarmerId(String orderId) {

        List<OrderFarmerWorkSize> orderFarmerWorkSizes = getOrderFarmerWorkSizeList(orderId);
        List<OrderDriverWorkSizeInfoShow> orderDriverWorkSizeInfoShows = orderDriverWorkSizeInfoShowMapper
                .getOrderDriverWorkSizeInfoShowListByOrderFarmerId(orderId);

        List<OrderFarmerWorkSizeInfo> orderFarmerWorkSizeInfos = new ArrayList<>();

        Map<Long, List<OrderDriverWorkSizeInfo>> map = new HashMap<>();

        for (OrderDriverWorkSizeInfoShow orderDriverWorkSizeInfoShow : orderDriverWorkSizeInfoShows) {
            if (!map.containsKey(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                    .getOrder_farmer_work_size_id())) {
                map.put(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                                .getOrder_farmer_work_size_id(),
                        new ArrayList<>());
            }
            OrderDriverWorkSizeInfo orderDriverWorkSizeInfo = new OrderDriverWorkSizeInfo();
            orderDriverWorkSizeInfo
                    .setName(orderDriverWorkSizeInfoShow.getUserBasicInfoShow() != null
                            && orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountRealNameAuth() != null
                            ? orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountRealNameAuth().getReal_name()
                            : UNAUTHED_USER_REAL_NAME);
            orderDriverWorkSizeInfo
                    .setPortraitUrl(orderDriverWorkSizeInfoShow.getUserBasicInfoShow() != null
                            && orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountPortrait() != null
                            ? orderDriverWorkSizeInfoShow.getUserBasicInfoShow()
                            .getAccountPortrait().getPortrait_url()
                            : "");
            orderDriverWorkSizeInfo
                    .setRealSize(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize().getSize());
            map.get(orderDriverWorkSizeInfoShow.getOrderDriverWorkSize()
                    .getOrder_farmer_work_size_id())
                    .add(orderDriverWorkSizeInfo);
        }

        for (OrderFarmerWorkSize orderFarmerWorkSize : orderFarmerWorkSizes) {

            OrderFarmerWorkSizeInfo orderFarmerWorkSizeInfo = new OrderFarmerWorkSizeInfo();
            orderFarmerWorkSizeInfo.setOrderFarmerWorkSize(orderFarmerWorkSize);
            if (map.containsKey(orderFarmerWorkSize.getId())) {
                orderFarmerWorkSizeInfo
                        .setOrderDriverWorkSizeInfos(map.get(orderFarmerWorkSize.getId()));
            }

            orderFarmerWorkSizeInfos.add(orderFarmerWorkSizeInfo);
        }
        return orderFarmerWorkSizeInfos;

    }

    public OrderFarmerWorkSize getUnensuredWorkSizeByOrderId(String orderId) {
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or().andOrder_farmer_idEqualTo(orderId)
                .andStateEqualTo(WorkSizeService.WORK_SIZE_STATE_WAITTING_ENSURE);
        List<OrderFarmerWorkSize> orderFarmerWorkSizes = orderFarmerWorkSizeMapper
                .selectByExample(orderFarmerWorkSizeCriteria);
        if (orderFarmerWorkSizes.isEmpty()) {
            return null;
        }
        return orderFarmerWorkSizes.get(0);
    }

    public List<OrderFarmerWorkSize> getOrderFarmerWorkSizeList(String orderId) {
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or().andOrder_farmer_idEqualTo(orderId);
        return orderFarmerWorkSizeMapper.selectByExample(orderFarmerWorkSizeCriteria);
    }

    public List<OptionWorkingType> getAllOptionWorkingTypes() {
        OptionWorkingTypeCriteria criteria = new OptionWorkingTypeCriteria();
        criteria.or();
        return optionWorkingTypeMapper.selectByExample(criteria);
    }

    public List<OptionCluster> getClustersByWorkingTypeId(Long id) {

        OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
        criteria.or().andWorking_type_idEqualTo(id);

        List<OptionClusterWorkingTypeMappingKey> keys = optionClusterWorkingTypeMappingMapper
                .selectByExample(criteria);

        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        OptionClusterCriteria optionClusterCriteria = new OptionClusterCriteria();
        for (OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey : keys) {
            optionClusterCriteria.or()
                    .andIdEqualTo(optionClusterWorkingTypeMappingKey.getCluster_id());
        }

        return optionClusterMapper.selectByExample(optionClusterCriteria);
    }

    public List<OptionDetail> getOptionDetailByClusterId(Long id) {

        OptionDetailCriteria optionDetailCriteria = new OptionDetailCriteria();
        optionDetailCriteria.or().andCluster_idEqualTo(id);

        return optionDetailMapper.selectByExample(optionDetailCriteria);

    }

    public List<OrderFarmerInfo> getAllMyOrders(Long uid) {
        List<OrderFarmerInfoShow> shows = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByUid(uid);
        List<OrderFarmerInfo> orderFarmerInfos = new ArrayList<>();
        for (OrderFarmerInfoShow orderFarmerInfoShow : shows) {
            orderFarmerInfos.add(convertOrderFarmerInfoShowToOrderFarmerInfo(orderFarmerInfoShow));
        }

        return orderFarmerInfos;
    }

    public List<OrderFarmerInfo> getAllMyActivateOrders(Long uid) {
        List<OrderFarmerInfoShow> orders = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByUid(uid);
        List<OrderFarmerInfo> activate = new ArrayList<>();
        List<String> needOperateState = Arrays
                .asList(FARMER_TODAY_WORK_FINISHED, FARMER_WAITTING_CHOOSE_FIELD,
                        FARMER_WAITTING_PAY, FARMER_WAITTING_SIZE_ENSURE);
        for (OrderFarmerInfoShow orderFarmerInfoShow : orders) {
            String state = orderFarmerInfoShow.getOrderFarmer().getState();
            if (needOperateState.stream().anyMatch(s -> s.equals(state))) {
                activate.add(convertOrderFarmerInfoShowToOrderFarmerInfo(orderFarmerInfoShow));
            }
        }
        return activate;
    }

    public OrderFarmerInfo getOrderFarmerInfoShowById(String id) {

        OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper
                .selectOrderFarmerInfoShowByOrderId(id);

        return convertOrderFarmerInfoShowToOrderFarmerInfo(orderFarmerInfoShow);
    }

    public OrderFarmerInfo convertOrderFarmerInfoShowToOrderFarmerInfo(
            OrderFarmerInfoShow orderFarmerInfoShow) {
        OrderFarmer o = orderFarmerInfoShow.getOrderFarmer();
        Account account = accountMapper.selectByPrimaryKey(o.getUid());
        OrderFarmerInfo orderFarmerInfo = new OrderFarmerInfo();
        orderFarmerInfo.setActual_money(null);
        orderFarmerInfo.setActual_size(null);
        orderFarmerInfo.setAuthentication(null);
        orderFarmerInfo.setBelongto(account.getUsername());
        orderFarmerInfo.setCity(o.getCity());
        orderFarmerInfo.setCounty(o.getCounty());
        orderFarmerInfo.setCrop_type(orderFarmerInfoShow.getOptionWorkingType().getCrop_type());
        orderFarmerInfo.setDetail(o.getDetail());
        orderFarmerInfo.setDiscount(o.getDiscount());
        orderFarmerInfo.setEnd_time(null);
        orderFarmerInfo.setFarmer_pay_state(null);
        orderFarmerInfo.setMachine_type(null);
        orderFarmerInfo.setName(o.getName());
        orderFarmerInfo.setNum(o.getDesire_num());
        orderFarmerInfo.setOrder_id(o.getOrder_id());
        orderFarmerInfo.setPre_money(null);
        orderFarmerInfo.setPre_pay(null);
        orderFarmerInfo.setPre_pay_rate(o.getPre_pay_rate());
        orderFarmerInfo.setProvince(o.getProvince());
        orderFarmerInfo.setPs(null);
        orderFarmerInfo
                .setService_type(orderFarmerInfoShow.getOptionWorkingType().getWorking_type());
        orderFarmerInfo.setSize(o.getSize());
        orderFarmerInfo.setStart_time(o.getDesire_start_time());
        orderFarmerInfo.setState(o.getState());
        orderFarmerInfo.setTags(o.getTags());
        orderFarmerInfo.setTel(o.getTel());
        orderFarmerInfo.setTown(o.getTown());
        orderFarmerInfo.setUni_price(o.getUni_price());
        orderFarmerInfo.setVillage(null);
        orderFarmerInfo.setWechat_pay_info(null);
        orderFarmerInfo.setOptions(orderFarmerInfoShow.getOptions());
        return orderFarmerInfo;

    }

}
