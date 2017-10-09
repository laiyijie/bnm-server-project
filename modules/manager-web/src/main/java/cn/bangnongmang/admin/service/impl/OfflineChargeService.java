package cn.bangnongmang.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.admin.data.dao.OfflineChargeMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.admin.data.domain.OfflineCharge;
import cn.bangnongmang.admin.data.domain.OfflineChargeCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketCriteria;
import cn.bangnongmang.data.domain.PocketLog;

@Transactional
public class OfflineChargeService {

    public static final Integer STATE_INIT = 100;
    public static final Integer STATE_AUTHED = 200;

    @Autowired
    private OfflineChargeMapper offlineChargeMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private PocketLogMapper pocketLogMapper;
    private String responser;

    @Autowired
    private AccountMapper accountMapper;

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public String getResponser() {
        return this.responser;
    }

    public OfflineCharge createOfflineCharge(String requester, String target, int num) {

        if (requester.equals(responser)) {
            return null;
        }

        OfflineCharge newCharge = new OfflineCharge();

        newCharge.setId(createID());
        newCharge.setNum(num);
        newCharge.setRequest_time(new Date().getTime());
        newCharge.setTarget(target);
        newCharge.setRequester(requester);
        newCharge.setResponser(responser);
        newCharge.setResponse_time(0L);
        newCharge.setState(STATE_INIT);
        newCharge.setPs("");

        offlineChargeMapper.insert(newCharge);

        return newCharge;
    }

    public OfflineCharge ensureOfflineCharge(String responser, String id) {

        OfflineCharge offlineCharge = offlineChargeMapper.selectByPrimaryKey(id);

        Pocket pocket;

        if (offlineCharge != null && responser.equals(this.responser)) {

            AccountCriteria accountCriteria = new AccountCriteria();

            accountCriteria.or()
                           .andUsernameEqualTo(offlineCharge.getTarget());

            List<Account> accounts = accountMapper.selectByExample(accountCriteria);

            if (accounts.isEmpty()) {
                return null;
            }
            Long targetUid = accounts.get(0)
                                     .getUid();
            PocketCriteria pocketCriteria = new PocketCriteria();
            pocketCriteria.or()
                          .andUidEqualTo(targetUid);

            List<Pocket> pockets = pocketMapper.selectByExample(pocketCriteria);
            if (pockets.isEmpty()) {

                pocket = new Pocket();

                pocket.setUid(targetUid);

                pocket.setArrears(0);
                pocket.setCredit(0);
                pocket.setCurr_money(0);
                pocket.setInsurance(0);
                pocket.setProvisions(0);
                pocket.setWaitting_in(0);

                pocketMapper.insert(pocket);
            } else {

                pocket = pockets.get(0);
            }

            offlineCharge.setState(STATE_AUTHED);
            offlineCharge.setResponse_time(new Date().getTime());
            offlineChargeMapper.updateByPrimaryKey(offlineCharge);
            pocket.setCurr_money(pocket.getCurr_money() + offlineCharge.getNum());
            pocketMapper.updateByPrimaryKey(pocket);
            PocketLog pocketLog = new PocketLog();
            pocketLog.setUid(targetUid);
            pocketLog.setConnect_order_id("");
            pocketLog.setDetail("线下充值");
            pocketLog.setMethod(200);
            pocketLog.setMoney(offlineCharge.getNum());
            pocketLog.setPocket_log_id(createID());
            pocketLog.setState(200);
            pocketLog.setTime(new Date().getTime() / 1000);
            pocketLog.setTrade_order_id(offlineCharge.getId());
            pocketLog.setType(100);
            pocketLog.setWechat_pay_info("");
            pocketLogMapper.insert(pocketLog);
            return offlineCharge;

        }
        return null;
    }

    private String createID() {

        return String.valueOf(new Date().getTime());

    }

}
