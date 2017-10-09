package cn.bangnongmang.admin.controller;

import cn.bangnongmang.admin.service.UserService;
import cn.bangnongmang.data.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.bangnongmang.admin.controller.api.PocketCreditControllerApi;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.domain.Pocket;

@RestController
public class PocketCreditControllerImpl implements PocketCreditControllerApi {

    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private UserService userService;

    @Override
    public boolean pocketCredit(String id, Integer credit) throws BusinessException {
        Account account = userService.getAccountByUsername(id);
        Pocket pocket = pocketMapper.selectByPrimaryKey(account.getUid());

        if (pocket == null) {
            throw new BusinessException("钱包不存在");
        }

        pocket.setCredit(credit);

        if (pocketMapper.updateByPrimaryKey(pocket) > 0) {
            return true;
        } else {
            throw new BusinessException("信用额度更新失败");

        }

    }

}
