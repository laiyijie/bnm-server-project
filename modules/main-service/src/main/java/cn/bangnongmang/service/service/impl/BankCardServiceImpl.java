package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.BankCardMapper;
import cn.bangnongmang.data.domain.BankCard;
import cn.bangnongmang.data.domain.BankCardCriteria;
import cn.bangnongmang.service.service.BankCardService;

@Service("S_BankCardService")
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public int deleteBankCard(Long id) {
        BankCard bankCard = new BankCard();
        bankCard.setStatus(CARD_UNAVALIABLE);
        bankCard.setId(id);
        return bankCardMapper.updateByPrimaryKeySelective(bankCard);
    }

    @Override
    public BankCard getBankCard(Long id) {
        return bankCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addBankCard(Long uid, String phone, String cardNumber, String type, String bank) {
        BankCard bankCard = new BankCard();
        bankCard.setBank(bank);
        bankCard.setCard_number(cardNumber);
        bankCard.setUid(uid);
        bankCard.setType(type);
        bankCard.setPhone(phone);
        bankCard.setStatus(CARD_AVALIABLE);
        return bankCardMapper.insertSelective(bankCard);
    }

    @Override
    public List<BankCard> getBankCardByNumber(String cardNumber) {
        BankCardCriteria bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or().andCard_numberEqualTo(cardNumber);
        return bankCardMapper.selectByExample(bankCardCriteria);
    }

    @Override
    public BankCard getBankCardByUidAndCardNumber(Long uid, String cardNumber) {
        BankCardCriteria bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or().andCard_numberEqualTo(cardNumber).andUidEqualTo(uid);
        List<BankCard> bankCards = bankCardMapper.selectByExample(bankCardCriteria);
        if (bankCards.isEmpty()) {
            return null;
        }
        return bankCards.get(0);
    }
}
