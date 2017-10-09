package cn.bangnongmang.service.middle.pocket;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

/**
 * Created by admin on 2017-04-08.
 */
public interface OrderInsuranceService {


    void refundInsurance(Long uid, String order_id) throws ServiceLayerExeption;

    void punishInsurance(Long uid, String order_id, Integer punish) throws ServiceLayerExeption;

    void payOrderInsurance(Long uid, String orderId, Integer money) throws
            ServiceLayerExeption;
}
