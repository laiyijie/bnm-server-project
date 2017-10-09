package cn.bangnongmang.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.bangnongmang.data.dao.OrderWorkSizeImageMapper;
import cn.bangnongmang.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.OrderDriverWorkSizeMapper;
import cn.bangnongmang.data.dao.OrderFarmerWorkSizeMapper;
import cn.bangnongmang.data.dao.UserWorkEfficiencyMapper;
import cn.bangnongmang.service.service.WorkSizeService;

@Service("S_WorkSizeService")
public class WorkSizeServiceImpl implements WorkSizeService {

    @Autowired
    private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;

    @Autowired
    private OrderDriverWorkSizeMapper orderDriverWorkSizeMapper;

    @Autowired
    private UserWorkEfficiencyMapper userWorkEfficiencyMapper;

    @Autowired
    private OrderWorkSizeImageMapper orderWorkSizeImageMapper;

    @Override
    public Boolean createOrderFarmerWorkSize(OrderFarmerWorkSize orderFarmerWorkSize) {
        if (orderFarmerWorkSizeMapper.insert(orderFarmerWorkSize) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public OrderFarmerWorkSize getOrderFarmerWorkSizeByOrderIdAndState(String orderId,
                                                                       Integer state) {

        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or()
                .andOrder_farmer_idEqualTo(orderId)
                .andStateEqualTo(state);
        List<OrderFarmerWorkSize> orderFarmerWorkSizes = orderFarmerWorkSizeMapper
                .selectByExample(orderFarmerWorkSizeCriteria);
        if (orderFarmerWorkSizes.isEmpty()) {
            return null;
        }
        return orderFarmerWorkSizes.get(0);

    }

    @Override
    public Boolean changeOrderFarmerWorkSizeStateTo(Long id, Integer state) {

        OrderFarmerWorkSize orderFarmerWorkSize = new OrderFarmerWorkSize();
        orderFarmerWorkSize.setId(id);
        orderFarmerWorkSize.setState(state);

        if (orderFarmerWorkSizeMapper.updateByPrimaryKeySelective(orderFarmerWorkSize) > 0) {
            return true;
        }
        return false;

    }

    @Override
    public OrderDriverWorkSize createOrderDriverWorkSize(OrderDriverWorkSize orderDriverWorkSize) {
        if (orderDriverWorkSizeMapper.insert(orderDriverWorkSize) > 0) {
            return orderDriverWorkSize;
        }
        return null;
    }

    @Override
    public boolean updateOrderFarmerWorkSize(Long id, Double totalSize) {
        OrderFarmerWorkSize orderFarmerWorkSize = new OrderFarmerWorkSize();
        orderFarmerWorkSize.setId(id);
        orderFarmerWorkSize.setSize(totalSize);
        return orderFarmerWorkSizeMapper.updateByPrimaryKeySelective(orderFarmerWorkSize) > 0;
    }

    @Override
    public boolean insertOnConflictUpdateWorkEfficiency(Long uid, Long workingTypeId, Double
            addingSize,
                                                        Integer addingCount) {
        if (addingCount == 0) {
            return false;
        }
        UserWorkEfficiencyCriteria userWorkEfficiencyCriteria = new UserWorkEfficiencyCriteria();
        userWorkEfficiencyCriteria.or()
                .andUidEqualTo(uid)
                .andWorking_type_idEqualTo(workingTypeId);
        List<UserWorkEfficiency> userWorkEfficiencys = userWorkEfficiencyMapper
                .selectByExample(userWorkEfficiencyCriteria);

        UserWorkEfficiency userWorkEfficiency = new UserWorkEfficiency();
        if (userWorkEfficiencys.isEmpty()) {
            userWorkEfficiency.setCount(addingCount);
            userWorkEfficiency.setEfficiency(addingSize / addingCount);
            userWorkEfficiency.setUid(uid);
            userWorkEfficiency.setWorking_type_id(workingTypeId);
            return userWorkEfficiencyMapper.insert(userWorkEfficiency) > 0 ? true : false;
        } else {
            UserWorkEfficiency old = userWorkEfficiencys.get(0);
            userWorkEfficiency.setCount(addingCount + old.getCount());
            userWorkEfficiency.setEfficiency(
                    (addingSize + old.getEfficiency() * old.getCount()) / (userWorkEfficiency
                            .getCount()));
            return userWorkEfficiencyMapper
                    .updateByExampleSelective(userWorkEfficiency, userWorkEfficiencyCriteria) > 0
                    ? true : false;
        }
    }

    @Override
    public List<OrderDriverWorkSize> getOrderDriverWorkSizeListByOrderFarmerIdAndUsername(
            String orderId,
            Long
                    uid) {
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or()
                .andOrder_farmer_idEqualTo(orderId);
        List<OrderFarmerWorkSize> orderFarmerWorkSizes = orderFarmerWorkSizeMapper
                .selectByExample(orderFarmerWorkSizeCriteria);
        OrderDriverWorkSizeCriteria orderDriverWorkSizeCriteria = new OrderDriverWorkSizeCriteria();

        if (orderFarmerWorkSizes.isEmpty()) {
            return new ArrayList<>();
        }

        for (OrderFarmerWorkSize orderFarmerWorkSize : orderFarmerWorkSizes) {
            orderDriverWorkSizeCriteria.or()
                    .andOrder_farmer_work_size_idEqualTo(orderFarmerWorkSize.getId())
                    .andUidEqualTo(uid);
        }

        return orderDriverWorkSizeMapper.selectByExample(orderDriverWorkSizeCriteria);

    }

    @Override
    public boolean addWorkSizeAuthImage(Long orderFarmerWorkSizeId, String url, String title) {
        OrderWorkSizeImage image = new OrderWorkSizeImage();
        image.setImage_path(url);
        image.setOrder_farmer_work_size_id(orderFarmerWorkSizeId);
        image.setTitile(title);
        return orderWorkSizeImageMapper.insert(image) > 0;
    }

    @Override
    public void deleteWorkSizeAuthImage(Long id) {
        orderWorkSizeImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderWorkSizeImage getWorkSizeAuthImage(Long id) {
        return orderWorkSizeImageMapper.selectByPrimaryKey(id);
    }

}
