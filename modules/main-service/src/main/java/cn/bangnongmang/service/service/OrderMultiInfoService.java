package cn.bangnongmang.service.service;

import cn.bangnongmang.data.domain.OrderMultiInfo;

/**
 * Created by admin on 2017-05-23.
 */
public interface OrderMultiInfoService {

    String STATE_UN_PUBLISH = "UN_PUBLISH";
    String STATE_PUBLISH = "PUBLISH";
    String TYPE_PHOTO = "PHOTO";
    String TYPE_FIELD = "FIELD";

    Boolean deleteMultiInfoById(Long id);

    OrderMultiInfo getMultiInfoById(Long id);

    Boolean addMultiInfo(OrderMultiInfo multiInfo);

    boolean changeMultiInfoState(Long id, String state);

}
