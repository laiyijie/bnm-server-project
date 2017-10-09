package cn.bangnongmang.service.service.impl;

import cn.bangnongmang.data.dao.OrderMultiInfoMapper;
import cn.bangnongmang.data.domain.OrderMultiInfo;
import cn.bangnongmang.service.service.OrderMultiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-05-25.
 */
@Service
public class OrderMultiInfoServiceImpl implements OrderMultiInfoService {
    @Autowired
    private OrderMultiInfoMapper orderMultiInfoMapper;

    @Override
    public Boolean deleteMultiInfoById(Long id) {
        return orderMultiInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public OrderMultiInfo getMultiInfoById(Long id) {
        return orderMultiInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean addMultiInfo(OrderMultiInfo multiInfo) {
        return orderMultiInfoMapper.insert(multiInfo) > 0;
    }

    @Override
    public boolean changeMultiInfoState(Long id, String state) {
        OrderMultiInfo orderMultiInfo = new OrderMultiInfo();
        orderMultiInfo.setId(id);
        orderMultiInfo.setState(state);
        return orderMultiInfoMapper.updateByPrimaryKeySelective(orderMultiInfo) > 0;
    }

}
