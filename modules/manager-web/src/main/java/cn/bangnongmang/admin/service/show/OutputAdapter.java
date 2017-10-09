package cn.bangnongmang.admin.service.show;

import java.util.ArrayList;
import java.util.List;

import cn.bangnongmang.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.admin.io.wechat.model.UserInfo;
import cn.bangnongmang.admin.io.wechat.model.UserMachineInfo;
import cn.bangnongmang.admin.io.wechat.model.UserMachineInfoSimple;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountGeoMapper;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.dao.UserMachineAuthImageMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineAuthImageCriteria;
import cn.bangnongmang.data.domain.UserMachineCriteria;
import cn.bangnongmang.data.domain.UserMachineModel;

@Service
public class OutputAdapter {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private UserMachineMapper userMachineMapper;
    @Autowired
    private UserMachineModelMapper userMachineModelMapper;
    @Autowired
    private UserMachineTypeMapper userMachineTypeMapper;
    @Autowired
    private UserMachineAuthImageMapper userMachineAuthImageMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    @Autowired
    private AccountGeoMapper accountGeoMapper;

    @Autowired
    private UserService userService;

    public UserInfo getUserInfo(String username) throws BusinessException {

        UserInfo userInfo = new UserInfo();

        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountCriteria);

        if (accounts.isEmpty()) {
            throw new BusinessException("此用户不存在");
        }

        Account account = accounts.get(0);

        AccountRealNameAuth accountRealNameAuth = accountRealNameAuthMapper.selectByPrimaryKey(account.getUid());

        UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
        userMachineCriteria.or()
                           .andUidEqualTo(account.getUid());

        List<UserMachine> userMachines = userMachineMapper.selectByExample(userMachineCriteria);

        List<UserMachineInfo> userMachineInfos = new ArrayList<>();

        for (UserMachine userMachine : userMachines) {

            UserMachineInfo userMachineInfo = new UserMachineInfo();

            UserMachineAuthImageCriteria userMachineAuthImageCriteria = new UserMachineAuthImageCriteria();
            userMachineAuthImageCriteria.or()
                                        .andUser_machine_idEqualTo(userMachine.getId());
            userMachineInfo.setPhotos(userMachineAuthImageMapper.selectByExample(userMachineAuthImageCriteria));

            userMachineInfo.setUserMachine(userMachine);
            userMachineInfo
                    .setUserMachineModel(userMachineModelMapper.selectByPrimaryKey(userMachine.getMachine_model_id()));

            if (userMachineInfo.getUserMachineModel() != null) {
                userMachineInfo.setUserMachineType(userMachineTypeMapper
                        .selectByPrimaryKey(userMachineInfo.getUserMachineModel()
                                                           .getUser_machine_type_id()));
            }
            userMachineInfos.add(userMachineInfo);

        }

        Pocket pocket = pocketMapper.selectByPrimaryKey(account.getUid());

        userInfo.setUsername(username);
        userInfo.setNickname(account.getNickname());
        userInfo.setCreateTime(account.getCreate_time() * 1000);

        if (account.getLevel() >= 20) {
            userInfo.setRole("农机手");
        } else {
            userInfo.setRole("农户");
        }

        if (accountRealNameAuth != null) {
            userInfo.setAccountRealNameAuth(accountRealNameAuth);
        }

        if (pocket != null) {
            userInfo.setCurrMoney(pocket.getCurr_money());
        } else {
            userInfo.setCurrMoney(0);
        }

        userInfo.setLastAddress(accountGeoMapper.selectByPrimaryKey(account.getUid()));

        userInfo.setUserMachineInfos(userMachineInfos);

        return userInfo;
    }

    public List<UserMachineInfoSimple> convertToUserMachineInfoSimple(List<UserMachine> userMachines) {

        List<UserMachineInfoSimple> simples = new ArrayList<>();
        for (UserMachine userMachine : userMachines) {
            Account account = userService.getAccount(userMachine.getUid());
            if (account == null) continue;
            UserMachineInfoSimple userMachineInfoSimple = new UserMachineInfoSimple();
            userMachineInfoSimple.setBelongto(account.getUsername());

            UserMachineModel userMachineModel = userMachineModelMapper
                    .selectByPrimaryKey(userMachine.getMachine_model_id());
            if (userMachineModel != null) {
                userMachineInfoSimple.setBrandAndNumber(
                        userMachineModel.getModel_brand() + " " + userMachineModel.getModel_number());
            }
            userMachineInfoSimple.setId(userMachine.getId());
            userMachineInfoSimple.setState(userMachine.getState());
            simples.add(userMachineInfoSimple);
        }

        return simples;
    }

}
