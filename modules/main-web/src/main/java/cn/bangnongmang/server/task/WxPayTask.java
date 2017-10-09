package cn.bangnongmang.server.task;

import java.util.List;

import cn.bangnongmang.server.log.BLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.OuterPayService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
public class WxPayTask {

    private static final long orderCheckDelay = 60000;

    @Autowired
    private PocketLogMapper pocketLogMapper;
    @Autowired
    private OuterPayService outerPayService;

    @Autowired
    private TradeFlowService tradeFlowService;

    @Autowired
    private PaymentBusiness commonPaymentBusiness;

    @Scheduled(cron = "0 0 2 * * ?")
    public void wxpayChargeCheck() throws InterruptedException {
        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or().andStateEqualTo(TradeFlowService.POCKETLOG_STATE_DRAFT)
                .andTypeEqualTo(TradeFlowService.POCKETLOG_TYPE_RECHARGE);
        List<PocketLog> pocketLogs = pocketLogMapper.selectByExample(pocketLogCriteria);
        for (PocketLog pocketLog : pocketLogs) {
            Thread.sleep(200);
            if (pocketLog.getTrade_order_id() == null || "".equals(pocketLog.getTrade_order_id())) {
                continue;
            }
            Charge charge = null;
            try {
                charge = outerPayService.getChargeById(pocketLog.getTrade_order_id());
            } catch (ServiceLayerExeption e1) {
                BLog.errorJsonLogBuilder()
                        .addErrorMessage(e1.getErrorMessage())
                        .put(e1)
                        .log();
                continue;
            }
            System.out.println(charge.toString());
            if (charge == null || !charge.getObject().equals("charge")) {
                continue;
            }
            if (charge.getPaid().equals(true)) {
                try {
                    commonPaymentBusiness
                            .doneTradeFlowByTradeOrderId(pocketLog.getTrade_order_id());
                } catch (BusinessException e) {
                    BLog.errorJsonLogBuilder()
                            .addErrorMessage(e.errorMessage)
                            .put(e)
                            .log();
                }
            } else {
                if (charge.getTimeExpire() < (System.currentTimeMillis() / 1000 + 60)) {
                    try {
                        commonPaymentBusiness
                                .cancelTradeFlowByTradeOrderId(pocketLog.getTrade_order_id());
                    } catch (BusinessException e) {
                        BLog.errorJsonLogBuilder()
                                .addErrorMessage(e.errorMessage)
                                .put(e)
                                .log();
                    }
                }
            }
        }

    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void wxpayWithDrawCheck() throws InterruptedException {
        Thread.sleep(200);
        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or().andStateEqualTo(TradeFlowService.POCKETLOG_STATE_DRAFT)
                .andTypeEqualTo(TradeFlowService.POCKETLOG_TYPE_WITHDRAW);
        List<PocketLog> pocketLogs = pocketLogMapper.selectByExample(pocketLogCriteria);
        for (PocketLog pocketLog : pocketLogs) {
            if (pocketLog.getTrade_order_id() == null || "".equals(pocketLog.getTrade_order_id())) {
                continue;
            }
            Transfer transfer = null;
            try {
                transfer = outerPayService.getTransferById(pocketLog.getTrade_order_id());
            } catch (ServiceLayerExeption e1) {
                BLog.errorJsonLogBuilder()
                        .addErrorMessage(e1.getErrorMessage())
                        .put(e1)
                        .log();
                continue;
            }
            if (transfer == null || !transfer.getObject().equals("transfer")) {
                continue;
            }
            if (transfer.getStatus().equals("paid")) {
                try {
                    commonPaymentBusiness
                            .doneTradeFlowByTradeOrderId(pocketLog.getTrade_order_id());
                } catch (BusinessException e) {
                    BLog.errorJsonLogBuilder()
                            .addErrorMessage(e.errorMessage)
                            .put(e)
                            .log();
                }
            } else if (transfer.getStatus().equals("failed")) {
                try {
                    commonPaymentBusiness
                            .cancelTradeFlowByTradeOrderId(pocketLog.getTrade_order_id());
                } catch (BusinessException e) {
                    BLog.errorJsonLogBuilder()
                            .addErrorMessage(e.errorMessage)
                            .put(e)
                            .log();
                }
            }
        }
    }

}
