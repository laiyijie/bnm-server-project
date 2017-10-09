package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.OrderMultiInfoBusiness;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OrderMultiInfo;
import cn.bangnongmang.service.service.OrderMultiInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017-05-25.
 */
@Service
public class OrderMultiInfoBusinessImpl implements OrderMultiInfoBusiness {

    private static final String DEFAULT_FIELD_CONTENT = "测亩总面积：";

    @Autowired
    private OrderMultiInfoService orderMultiInfoService;

    @Override
    public void deleteOrderMultiInfo(String orderId, Long id) {

        OrderMultiInfo orderMultiInfo = orderMultiInfoService.getMultiInfoById(id);
        if (orderMultiInfo == null) throw new BusinessException("该记录不存在");
        if (!orderMultiInfo.getOrder_id()
                           .equals(orderId)) throw new BusinessException("参数错误");

        if (orderMultiInfoService.deleteMultiInfoById(id))
            return;
        throw new BusinessException("删除失败");

    }

    @Override
    public void addOrderMultiInfoPhotos(String orderId, String content, List<String> extraInfo) {
        OrderMultiInfo orderMultiInfo = new OrderMultiInfo();
        orderMultiInfo.setUpdate_time(System.currentTimeMillis() / 1000);
        orderMultiInfo.setType(OrderMultiInfoService.TYPE_PHOTO);
        orderMultiInfo.setState(OrderMultiInfoService.STATE_UN_PUBLISH);
        orderMultiInfo.setOrder_id(orderId);
        orderMultiInfo.setExtra_info(JSON.toJSONString(extraInfo));
        orderMultiInfo.setCreate_time(System.currentTimeMillis() / 1000);
        orderMultiInfo.setContent(content);
        orderMultiInfoService.addMultiInfo(orderMultiInfo);
    }


    @Override
    public void addOrderMultiInfoFields(String orderId, List<String> extraInfo) {
        OrderMultiInfo orderMultiInfo = new OrderMultiInfo();
        orderMultiInfo.setUpdate_time(System.currentTimeMillis() / 1000);
        orderMultiInfo.setType(OrderMultiInfoService.TYPE_FIELD);
        orderMultiInfo.setState(OrderMultiInfoService.STATE_UN_PUBLISH);
        orderMultiInfo.setOrder_id(orderId);
        orderMultiInfo.setExtra_info(JSON.toJSONString(extraInfo));
        orderMultiInfo.setCreate_time(System.currentTimeMillis() / 1000);
        orderMultiInfo.setContent(DEFAULT_FIELD_CONTENT);
        orderMultiInfoService.addMultiInfo(orderMultiInfo);
    }

    @Override
    public void publishOrderMultiInfo(String orderId, Long id) {

        OrderMultiInfo orderMultiInfo = orderMultiInfoService.getMultiInfoById(id);
        if (orderMultiInfo == null) throw new BusinessException("该记录不存在");
        if (!orderMultiInfo.getOrder_id()
                           .equals(orderId)) throw new BusinessException("参数错误");

        if (orderMultiInfoService.changeMultiInfoState(id, OrderMultiInfoService.STATE_PUBLISH))
            return;
        throw new BusinessException("操作失败");
    }

    @Override
    public void unPublishOrderMultiInfo(String orderId, Long id) {

        OrderMultiInfo orderMultiInfo = orderMultiInfoService.getMultiInfoById(id);
        if (orderMultiInfo == null) throw new BusinessException("该记录不存在");
        if (!orderMultiInfo.getOrder_id()
                           .equals(orderId)) throw new BusinessException("参数错误");

        if (orderMultiInfoService.changeMultiInfoState(id, OrderMultiInfoService.STATE_UN_PUBLISH))
            return;
        throw new BusinessException("操作失败");
    }
}
