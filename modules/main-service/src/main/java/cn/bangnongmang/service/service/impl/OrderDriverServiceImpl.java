package cn.bangnongmang.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.OrderDriverMapper;
import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderDriverCriteria;
import cn.bangnongmang.service.service.OrderDriverService;

@Service("S_OrderDriverService")
public class OrderDriverServiceImpl implements OrderDriverService {

    @Autowired
    private OrderDriverMapper orderDriverMapper;

    @Override
    public boolean createOrderDriver(OrderDriver orderDriver) {

        if (orderDriverMapper.insert(orderDriver) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<OrderDriver> getOrderDriverByUsernameAndStates(Long uid, String... states) {
        if (states.length == 0) {
            return new ArrayList<>();
        }

        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        for (String string : states) {
            orderDriverCriteria.or()
                               .andUidEqualTo(uid)
                               .andStateEqualTo(string);
        }
        return orderDriverMapper.selectByExample(orderDriverCriteria);
    }

    @Override
    public List<OrderDriver> getOrderDriverListByOrderFarmerId(String orderId) {
        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderId);
        return orderDriverMapper.selectByExample(orderDriverCriteria);
    }

    @Override
    public boolean updateState(String order_id, String state) {
        OrderDriver orderDriver = new OrderDriver();
        orderDriver.setOrder_id(order_id);
        orderDriver.setState(state);
        return orderDriverMapper.updateByPrimaryKeySelective(orderDriver) > 0 ? true : false;
    }

    @Override
    public OrderDriver getOrderDriverById(String orderDriverId) {

        return orderDriverMapper.selectByPrimaryKey(orderDriverId);
    }

    @Override
    public OrderDriver getOrderDriverByOrderFarmerIdAndUid(String orderFarmerId, Long uid) {

        OrderDriverCriteria orderDriverCriteria = new OrderDriverCriteria();
        orderDriverCriteria.or()
                           .andOrder_farmer_idEqualTo(orderFarmerId)
                           .andUidEqualTo(uid);
        List<OrderDriver> orderDrivers = orderDriverMapper.selectByExample(orderDriverCriteria);
        return orderDrivers.isEmpty() ? null : orderDrivers.get(0);
    }

    @Override
    public boolean updateStartTime(String order_id, long time) {
        OrderDriver orderDriver = new OrderDriver();
        orderDriver.setOrder_id(order_id);
        orderDriver.setService_start(time);
        return orderDriverMapper.updateByPrimaryKeySelective(orderDriver) > 0;
    }

    @Override
    public boolean updateEndTime(String order_id, long time) {
        OrderDriver orderDriver = new OrderDriver();
        orderDriver.setOrder_id(order_id);
        orderDriver.setService_end(time);
        return orderDriverMapper.updateByPrimaryKeySelective(orderDriver) > 0;
    }

    @Override
    public boolean deleteOrderDriver(String orderDriverId) {
        return orderDriverMapper.deleteByPrimaryKey(orderDriverId) > 0;
    }

}
