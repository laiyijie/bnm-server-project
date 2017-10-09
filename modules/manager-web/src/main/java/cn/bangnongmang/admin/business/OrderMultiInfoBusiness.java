package cn.bangnongmang.admin.business;

import java.util.List;

/**
 * Created by admin on 2017-05-24.
 */
public interface OrderMultiInfoBusiness {

    void deleteOrderMultiInfo(String orderId, Long id);

    void addOrderMultiInfoPhotos(String orderId, String content, List<String> extraInfo);

    void addOrderMultiInfoFields(String orderId, List<String> extraInfo);

    void publishOrderMultiInfo(String orderId, Long id);

    void unPublishOrderMultiInfo(String orderId, Long id);

}
