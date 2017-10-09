package cn.bangnongmang.service.middle.pocket.impl;

import cn.bangnongmang.data.dao.OrderInsuranceRecordMapper;
import cn.bangnongmang.data.domain.OrderInsuranceRecord;
import cn.bangnongmang.data.domain.OrderInsuranceRecordCriteria;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.service.middle.pocket.OrderInsuranceService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017-04-08.
 */
@Service("S_OrderInsuranceService")
public class OrderInsuranceServiceImpl implements OrderInsuranceService {

    @Autowired
    private TradeFlowService tradeFlowService;

    @Autowired
    private OrderInsuranceRecordMapper orderInsuranceRecordMapper;

    @Override
    public void refundInsurance(Long uid, String order_id) throws ServiceLayerExeption {
        OrderInsuranceRecord orderInsuranceRecord = getOrderInsurance
                (uid, order_id);
        if (orderInsuranceRecord == null) {
            return;
        }
        PocketLog refundLog = null;
        refundLog = tradeFlowService.createTradeFlow(uid, TradeFlowService
                        .POCKETLOG_TYPE_REFUND_INSURANCE, orderInsuranceRecord.getMoney(),
                TradeFlowService
                        .POCKETLOG_METHOD_DEFAULT, "退还保证金", "", order_id);
        tradeFlowService.doneTradeFlow(refundLog.getPocket_log_id());

        deleteOrderInsurance(uid, order_id);
    }

    @Override
    public void punishInsurance(Long uid, String order_id, Integer punish) throws ServiceLayerExeption {

        if (punish != 0) {
            OrderInsuranceRecord orderInsuranceRecord = getOrderInsurance(uid, order_id);
            if (orderInsuranceRecord == null) throw new ServiceLayerExeption("此人没交这单的保证金");
            if (orderInsuranceRecord.getMoney() < punish)
                throw new ServiceLayerExeption("惩罚大于缴纳的订单保证金");
            PocketLog punishLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_PUNISH_INSURANCE, punish, TradeFlowService
                    .POCKETLOG_METHOD_DEFAULT, "保证金惩罚", "", order_id);
            tradeFlowService.doneTradeFlow(punishLog.getPocket_log_id());

            updateOrderInsuranceMoney(uid, order_id, orderInsuranceRecord.getMoney() - punish);
        }
    }

    @Override
    public void payOrderInsurance(Long uid, String orderId, Integer money) throws
            ServiceLayerExeption {
        OrderInsuranceRecord orderInsuranceRecord = getOrderInsurance
                (uid, orderId);
        if (orderInsuranceRecord == null) {
            PocketLog payLog = tradeFlowService.createTradeFlow(uid, TradeFlowService
                            .POCKETLOG_TYPE_PAY_INSURANCE, money,
                    TradeFlowService.POCKETLOG_METHOD_DEFAULT, "支付保证金", "", orderId);
            tradeFlowService.doneTradeFlow(payLog.getPocket_log_id());
            addOrderInsurance(uid, orderId, money);

        }
    }


    private OrderInsuranceRecord getOrderInsurance(Long uid, String orderId) {
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new
                OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andUidEqualTo(uid)
                                    .andOrder_farmer_idEqualTo
                                            (orderId);
        List<OrderInsuranceRecord> orderInsuranceRecords = orderInsuranceRecordMapper
                .selectByExample(orderInsuranceRecordCriteria);
        if (orderInsuranceRecords.isEmpty()) {
            return null;
        }

        return orderInsuranceRecords.get(0);
    }

    private void addOrderInsurance(Long uid, String orderId, Integer money) {
        OrderInsuranceRecord orderInsuranceRecord = new OrderInsuranceRecord();
        orderInsuranceRecord.setMoney(money);
        orderInsuranceRecord.setOrder_farmer_id(orderId);
        orderInsuranceRecord.setUid(uid);
        orderInsuranceRecordMapper.insert(orderInsuranceRecord);
    }


    private void deleteOrderInsurance(Long uid, String orderId) {
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new
                OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andUidEqualTo(uid)
                                    .andOrder_farmer_idEqualTo
                                            (orderId);
        orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);
    }

    private void updateOrderInsuranceMoney(Long uid, String orderId, Integer money) {
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new
                OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andUidEqualTo(uid)
                                    .andOrder_farmer_idEqualTo
                                            (orderId);
        OrderInsuranceRecord orderInsuranceRecord = new OrderInsuranceRecord();
        orderInsuranceRecord.setMoney(money);
        orderInsuranceRecordMapper.updateByExampleSelective(orderInsuranceRecord, orderInsuranceRecordCriteria);
    }

}
