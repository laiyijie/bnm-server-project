package cn.bangnongmang.service.middle.pocket.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

/**
 * 新的支付系统，修改支付的流程
 *
 * @author laiyijie
 * @ClassName TradeFlowService
 * @date 2017年2月22日 下午6:10:32
 */
@Service("S_TradeFlowService")
public class TradeFlowServiceImpl implements TradeFlowService {

    @Autowired
    private PocketMapper pocketMapper;

    @Autowired
    private PocketLogMapper pocketLogMapper;

    @Override
    public Pocket getPocketInfo(Long belongTo) {

        return pocketMapper.selectByPrimaryKey(belongTo);
    }

    @Override
    public Boolean updateTradeOrderIdByLogId(String logID, String tradeOrderId) {
        PocketLog pocketLog = pocketLogMapper.selectByPrimaryKey(logID);
        if (pocketLog == null) {
            return false;
        }
        pocketLog.setTrade_order_id(tradeOrderId);
        if (pocketLogMapper.updateByPrimaryKey(pocketLog) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PocketLog getPocketLogByTradeOrderId(String tradeOrderId) {

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andTrade_order_idEqualTo(tradeOrderId);

        List<PocketLog> pocketLogs = pocketLogMapper.selectByExample(pocketLogCriteria);
        if (!pocketLogs.isEmpty()) {
            return pocketLogs.get(0);
        }

        return null;

    }

    /**
     * 创建支付流，不同的支付流类型对应不同的支付方式
     *
     * @param belongto
     * @param type
     * @param money
     * @param method
     * @param detail
     * @param trade_order_id
     * @param connect_order_id
     * @return
     * @throws ServiceLayerExeption
     * @Title createTradeFlow
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PocketLog createTradeFlow(Long belongto, Integer type, Integer money, Integer method,
                                     String detail,
                                     String trade_order_id, String connect_order_id) throws ServiceLayerExeption {
        PocketLog pocketLog = new PocketLog();
        if (money < 0) throw new ServiceLayerExeption("金额必须大于零");
        if (connect_order_id == null) {
            connect_order_id = "";
        }
        if (trade_order_id == null) {
            trade_order_id = "";
        }

        if (method == null) {
            method = POCKETLOG_METHOD_DEFAULT;
        }

        pocketLog.setUid(belongto);
        pocketLog.setConnect_order_id(connect_order_id);
        pocketLog.setDetail(detail);
        pocketLog.setMethod(method);
        pocketLog.setMoney(money);
        pocketLog.setPocket_log_id(createLogId());
        pocketLog.setState(POCKETLOG_STATE_DRAFT);
        pocketLog.setTime(System.currentTimeMillis() / 1000);
        pocketLog.setTrade_order_id(trade_order_id);
        pocketLog.setType(type);
        pocketLog.setWechat_pay_info("");
        int flag = pocketLogMapper.insert(pocketLog);

        Pocket pocket = pocketMapper.selectByPrimaryKey(belongto);

        if (pocket == null) {
            pocket = createPocket(belongto);
        }

        // TODO 需要关联订单ID的一些操作，需要具体下来
        switch (pocketLog.getType()) {
            case POCKETLOG_TYPE_INCOME:
                if ("".equals(connect_order_id)) {
                    throw new ServiceLayerExeption(8000, "未设置订单号");
                }
                break;
            case POCKETLOG_TYPE_REFUND:

                break;
            case POCKETLOG_TYPE_REFUND_INSURANCE:
                if ("".equals(connect_order_id)) {
                    throw new ServiceLayerExeption(8000, "未设置订单号");
                }
                break;
            case POCKETLOG_TYPE_RECHARGE:

                break;
            case POCKETLOG_TYPE_PAY:

                break;
            case POCKETLOG_TYPE_PROVISIONS_PAY:

                break;
            case POCKETLOG_TYPE_PROVISIONS:

                break;
            case POCKETLOG_TYPE_PAY_INSURANCE:
                if ("".equals(connect_order_id)) {
                    throw new ServiceLayerExeption(8000, "未设置订单号");
                }
                break;
            case POCKETLOG_TYPE_WITHDRAW:
                if (pocket.getCurr_money() < money) {
                    throw new ServiceLayerExeption(1000, "余额不足");
                }
                pocket.setCurr_money(pocket.getCurr_money() - money);
                pocketMapper.updateByPrimaryKey(pocket);
                break;

            case POCKETLOG_TYPE_INCOME_UN_FREEZE:
                if ("".equals(connect_order_id)) {
                    throw new ServiceLayerExeption(8000, "未设置订单号");
                }
                break;
            case POCKETLOG_TYPE_PUNISH_INSURANCE:
                break;
            case POCKETLOG_TYPE_INCOME_PUNISH:
                break;
            default:
                throw new ServiceLayerExeption("没有这种交易类型");
        }
        if (flag > 0)

        {
            return pocketLog;
        }
        return null;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean doneTradeFlow(String logID) throws ServiceLayerExeption {

        PocketLog pocketLog = pocketLogMapper.selectByPrimaryKey(logID);
        if (pocketLog == null) {
            throw new ServiceLayerExeption("不存在这条流水");
        }
        Pocket pocket = pocketMapper.selectByPrimaryKey(pocketLog.getUid());

        if (pocket == null) {
            pocket = createPocket(pocketLog.getUid());
        }
        switch (pocketLog.getType()) {
            case POCKETLOG_TYPE_INCOME:
                pocket.setWaitting_in(pocket.getWaitting_in() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_REFUND:
                pocket.setCurr_money(pocket.getCurr_money() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_REFUND_INSURANCE:
                if (pocket.getInsurance() < pocketLog.getMoney()) {
                    throw new ServiceLayerExeption(8100, "保证金不足");
                }
                pocket.setInsurance(pocket.getInsurance() - pocketLog.getMoney());
                pocket.setCurr_money(pocket.getCurr_money() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_RECHARGE:
                pocket.setCurr_money(pocket.getCurr_money() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_PAY:
                if (pocket.getCurr_money() < pocketLog.getMoney()) {
                    throw new ServiceLayerExeption(1000, "余额不足");
                }
                pocket.setCurr_money(pocket.getCurr_money() - pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_PROVISIONS_PAY:
                int tempMoney = pocketLog.getMoney();

                if (pocket.getProvisions() >= tempMoney) {
                    pocket.setProvisions(pocket.getProvisions() - tempMoney);
                    pocketMapper.updateByPrimaryKey(pocket);
                    break;
                }

                tempMoney = tempMoney - pocket.getProvisions();
                pocket.setProvisions(0);

                if (pocket.getCredit() - pocket.getArrears() >= tempMoney) {
                    pocket.setArrears(pocket.getArrears() + tempMoney);
                    pocketMapper.updateByPrimaryKey(pocket);
                    break;
                }

                tempMoney = tempMoney - (pocket.getCredit() - pocket.getArrears());
                pocket.setArrears(pocket.getCredit());

                if (pocket.getCurr_money() >= tempMoney) {
                    pocket.setCurr_money(pocket.getCurr_money() - tempMoney);
                    pocketMapper.updateByPrimaryKey(pocket);
                    break;
                }
                throw new ServiceLayerExeption(1000, "余额不足");
            case POCKETLOG_TYPE_PROVISIONS:
                if (pocket.getCurr_money() < pocketLog.getMoney()) {
                    throw new ServiceLayerExeption(1000, "余额不足");
                }
                pocket.setCurr_money(pocket.getCurr_money() - pocketLog.getMoney());
                pocket.setProvisions(pocket.getProvisions() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_PAY_INSURANCE:
                if (pocket.getCurr_money() < pocketLog.getMoney()) {
                    throw new ServiceLayerExeption(1000, "余额不足");
                }
                pocket.setCurr_money(pocket.getCurr_money() - pocketLog.getMoney());
                pocket.setInsurance(pocket.getInsurance() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_WITHDRAW:

                break;
            case POCKETLOG_TYPE_INCOME_UN_FREEZE:
                if (pocket.getWaitting_in() < pocketLog.getMoney()) {
                    throw new ServiceLayerExeption(8000, "支付错误");
                }
                pocket.setWaitting_in(pocket.getWaitting_in() - pocketLog.getMoney());
                pocket.setCurr_money(pocket.getCurr_money() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_PUNISH_INSURANCE:
                if (pocket.getInsurance() < pocketLog.getMoney())
                    throw new ServiceLayerExeption("保证金余额不足");
                pocket.setInsurance(pocket.getInsurance() - pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_INCOME_PUNISH:
                if (pocket.getWaitting_in() < pocketLog.getMoney())
                    throw new ServiceLayerExeption("可扣除的金额不足");
                pocket.setWaitting_in(pocket.getWaitting_in() - pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            default:
                throw new ServiceLayerExeption("没有这种交易类型");
        }

        pocketLog.setState(POCKETLOG_STATE_DONE);
        if (pocketLogMapper.updateByPrimaryKey(pocketLog) > 0) {
            return true;
        }
        throw new ServiceLayerExeption("交易失败");
    }

    @Override
    public Boolean cancelTradeFlow(String logID) throws ServiceLayerExeption {
        PocketLog pocketLog = pocketLogMapper.selectByPrimaryKey(logID);
        if (pocketLog == null) {
            return false;
        }

        if (POCKETLOG_STATE_DRAFT != pocketLog.getState()) {
            throw new ServiceLayerExeption("状态不正确");
        }
        Pocket pocket = pocketMapper.selectByPrimaryKey(pocketLog.getUid());
        if (pocket == null) {
            pocket = createPocket(pocketLog.getUid());
        }
        switch (pocketLog.getType()) {
            case POCKETLOG_TYPE_INCOME:

                break;
            case POCKETLOG_TYPE_REFUND:

                break;
            case POCKETLOG_TYPE_REFUND_INSURANCE:

                break;
            case POCKETLOG_TYPE_RECHARGE:

                break;
            case POCKETLOG_TYPE_PAY:

                break;
            case POCKETLOG_TYPE_PROVISIONS_PAY:

                break;
            case POCKETLOG_TYPE_PROVISIONS:

                break;
            case POCKETLOG_TYPE_PAY_INSURANCE:

                break;
            case POCKETLOG_TYPE_WITHDRAW:
                // 提现扣款要前置，失败后要恢复
                pocket.setCurr_money(pocket.getCurr_money() + pocketLog.getMoney());
                pocketMapper.updateByPrimaryKey(pocket);
                break;
            case POCKETLOG_TYPE_INCOME_UN_FREEZE:
                break;
            case POCKETLOG_TYPE_PUNISH_INSURANCE:
                break;
            case POCKETLOG_TYPE_INCOME_PUNISH:
                break;
            default:
                throw new ServiceLayerExeption("没有这种交易类型");
        }
        pocketLog.setState(POCKETLOG_STATE_CANCEL);
        if (pocketLogMapper.updateByPrimaryKey(pocketLog) > 0) {
            return true;
        }
        return false;
    }

    private String createLogId() {
        Random random = new Random();
        int x = random.nextInt(8999);
        x = x + 1000;
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(x);
    }

    @Override
    public List<PocketLog> getPocketLog(Long uid) {
        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(uid);
        return pocketLogMapper.selectByExample(pocketLogCriteria);
    }

    @Override
    public List<PocketLog> getAllUnensuredRechargeLogByMethod(int method) {

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();

        pocketLogCriteria.or()
                         .andStateEqualTo(POCKETLOG_STATE_DRAFT)
                         .andMethodEqualTo(method)
                         .andTypeEqualTo(POCKETLOG_TYPE_RECHARGE);

        List<PocketLog> list = pocketLogMapper.selectByExample(pocketLogCriteria);
        return list;
    }

    @Override
    public PocketLog getAllUnensuredRechargeLogByMethodAndOutTradeOrderId(String outTradeOrderId, int method) {

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();

        pocketLogCriteria.or()
                         .andStateEqualTo(POCKETLOG_STATE_DRAFT)
                         .andMethodEqualTo(method)
                         .andTypeEqualTo(POCKETLOG_TYPE_RECHARGE)
                         .andTrade_order_idEqualTo(outTradeOrderId);

        List<PocketLog> list = pocketLogMapper.selectByExample(pocketLogCriteria);

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public Pocket createPocket(Long belongTo) {
        Pocket pocket = new Pocket();
        pocket.setUid(belongTo);
        pocket.setCurr_money(0);
        pocket.setArrears(0);
        pocket.setCredit(0);
        pocket.setInsurance(0);
        pocket.setProvisions(0);
        pocket.setWaitting_in(0);
        pocketMapper.insert(pocket);
        return pocket;
    }

    @Override
    public List<PocketLog> getPocketLogByUidAndConnectOrderIdAndTypeAndState(Long uid,
                                                                             String connectOrderId, int type, Integer state) {
        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(uid)
                         .andTypeEqualTo(type)
                         .andStateEqualTo(state)
                         .andConnect_order_idEqualTo(connectOrderId);
        return pocketLogMapper.selectByExample(pocketLogCriteria);
    }

    @Override
    public Integer countMyUnfreezeOrderWaitInMoneyByOrderId(Long uid, String connectOrderId) {

        Integer totalMoney = 0;
        List<PocketLog> incomePocketLogs = this
                .getPocketLogByUidAndConnectOrderIdAndTypeAndState(
                        uid, connectOrderId,
                        TradeFlowService.POCKETLOG_TYPE_INCOME, TradeFlowService.POCKETLOG_STATE_DONE);
        List<PocketLog> unfreezePocketLogs = this
                .getPocketLogByUidAndConnectOrderIdAndTypeAndState(
                        uid, connectOrderId,
                        TradeFlowService.POCKETLOG_TYPE_INCOME_UN_FREEZE, TradeFlowService.POCKETLOG_STATE_DONE);
        List<PocketLog> punishPocketLogs = this
                .getPocketLogByUidAndConnectOrderIdAndTypeAndState(
                        uid, connectOrderId,
                        TradeFlowService.POCKETLOG_TYPE_INCOME_PUNISH, TradeFlowService.POCKETLOG_STATE_DONE);

        for (PocketLog pocketLog : incomePocketLogs) {
            totalMoney += pocketLog.getMoney();
        }
        for (PocketLog pocketLog : unfreezePocketLogs) {
            totalMoney -= pocketLog.getMoney();
        }
        for (PocketLog pocketLog : punishPocketLogs) {
            totalMoney -= pocketLog.getMoney();
        }

        return totalMoney;
    }
}
