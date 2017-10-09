package cn.bangnongmang.server.io.android.show;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.bangnongmang.data.dao.BankCardMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.domain.BankCard;
import cn.bangnongmang.data.domain.BankCardCriteria;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.driverapp.models.BankCardInfo;
import cn.bangnongmang.driverapp.models.BankCardValidateInfo;
import cn.bangnongmang.driverapp.models.WalletCheck;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.service.service.BankCardService;

@Service
public class AndroidBankCardShow {

	@Autowired
	private BankCardMapper bankCardMapper;

	@Autowired
	private AlipayService alipayService;

	@Autowired
	private PocketLogMapper pocketLogMapper;

	public List<BankCardInfo> getBankCardList(Long uid) {
		List<BankCardInfo> result = new ArrayList<>();

		BankCardCriteria bankCardCriteria = new BankCardCriteria();
		bankCardCriteria.or().andUidEqualTo(uid);
		bankCardCriteria.setOrderByClause("id");
		List<BankCard> bankCards = bankCardMapper.selectByExample(bankCardCriteria);
		for (BankCard bankCard : bankCards) {
			if (BankCardService.CARD_AVALIABLE.equals(bankCard.getStatus()))
				result.add(convertFromBankCardToBankCardInfo(bankCard));
		}

		return result;

	}

	public List<WalletCheck> getMyWalletCheckList(Long uid) {

		List<WalletCheck> walletChecks = new ArrayList<>();

		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(uid);
		List<PocketLog> pocketLogs = pocketLogMapper.selectByExample(pocketLogCriteria);
		for (PocketLog pocketLog : pocketLogs) {
			walletChecks.add(convertPocketLogToWalletCheck(pocketLog));
		}
		return walletChecks;
	}

	public WalletCheck convertPocketLogToWalletCheck(PocketLog pocketLog) {
		if (pocketLog == null) {
			return null;
		}
		WalletCheck walletCheck = new WalletCheck();
		walletCheck.setConnectOrder(pocketLog.getConnect_order_id());
		walletCheck.setDetail(pocketLog.getDetail());
		walletCheck.setMoney(pocketLog.getMoney());
		walletCheck.setState(pocketLog.getState());
		walletCheck.setTime(pocketLog.getTime() * 1000);
		walletCheck.setType(pocketLog.getType());

		return walletCheck;
	}

	public BankCardInfo convertFromBankCardToBankCardInfo(BankCard bankCard) {
		BankCardInfo bankCardInfo = new BankCardInfo();
		bankCardInfo.setBank(alipayService.convertBankNameToChinese(bankCard.getBank()));
		bankCardInfo.setCardNum(bankCard.getCard_number());
		bankCardInfo.setPhone(bankCard.getPhone());
		bankCardInfo.setId(bankCard.getId());
		bankCardInfo.setType(alipayService.convertCardType(bankCard.getType()));
		return bankCardInfo;

	}

	public BankCardValidateInfo validateCard(String cardNumber) throws AndroidOutputException {
		JSONObject jsonObject = alipayService.validateCard(cardNumber);
		if (jsonObject.getBoolean("validated")) {
			BankCardValidateInfo bankCardValidateInfo = new BankCardValidateInfo();
			bankCardValidateInfo.setBank(alipayService.convertBankNameToChinese(jsonObject.getString("bank")));
			bankCardValidateInfo.setCardNum(cardNumber);
			bankCardValidateInfo.setType(alipayService.convertCardType(jsonObject.getString("cardType")));
			return bankCardValidateInfo;
		}
		throw new AndroidOutputException("暂不支持该银行卡");

	}

}
