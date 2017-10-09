package cn.bangnongmang.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.bangnongmang.admin.service.UserService;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.data.dao.OfflineChargeMapper;
import cn.bangnongmang.admin.data.domain.OfflineCharge;
import cn.bangnongmang.admin.data.domain.OfflineChargeCriteria;
import cn.bangnongmang.admin.service.impl.OfflineChargeService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.JsonResult;
import cn.bangnongmang.admin.util.JsonResultFactory;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketCriteria;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;

@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private PocketLogMapper plogMapper;

    @Autowired
    private OfflineChargeMapper offlineChargeMapper;

    @Autowired
    private JsonResultFactory jsonResultFactory;

    @Autowired
    private OfflineChargeService offlineChargeService;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    @Autowired
    private UserMachineShowMapper userMachineShowMapper;

    @Autowired
    private UserService userService;

    public static Integer DRIVER_LEADER_AUTHED = 200;
    public static Integer DRIVER_LEADER_UN_AUTHED = 0;
    // private transient final Log logger=
    // LogFactory.getLog(AccountController.class);

    /**
     * @return
     * @Title getUserlist
     * @deprecated TODO(这里用一句话描述这个方法的作用)
     */

    @RequestMapping(value = "/getAccountList")
    public List<Account> getUserlist() {
        AccountCriteria cr = new AccountCriteria();
        cr.or();
        // PageHelper.startPage(1, 20);
        List<Account> re = accountMapper.selectByExample(cr);
        return re;
    }

    /**
     * TODO(获取用户列表)
     *
     * @throws IOException
     * @Title getAccountDetail
     */
    @RequestMapping("/getAccountListByParams")
    public List<Account> getAccountListByParams() {

        return null;

    }

    @RequestMapping(value = "/getAccountDetail")
    public Map<String, Object> getAccountDetail(@RequestParam String username) throws IOException {
        Account account = userService.getAccountByUsername(username);

        Map<String, Object> re = new HashMap<String, Object>();
        PocketCriteria pCr = new PocketCriteria();
        pCr.or()
           .andUidEqualTo(account.getUid());
        List<Pocket> pocket = pocketMapper.selectByExample(pCr);

        re.put("pocket", pocket);
        AccountCriteria aCr = new AccountCriteria();
        aCr.or()
           .andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(aCr);
        re.put("account", accounts);

        return re;
    }

    @RequestMapping(value = "/getPocketLog")
    public List<PocketLog> getPocketLog(@RequestParam String username) {
        Account account = userService.getAccountByUsername(username);
        PocketLogCriteria pCr = new PocketLogCriteria();
        pCr.or()
           .andUidEqualTo(account.getUid());
        List<PocketLog> pLogs = plogMapper.selectByExample(pCr);
        return pLogs;
    }

    @RequestMapping(value = "/createRecharge")
    public JsonResult createRecharge(@RequestParam("target") String target, @RequestParam("num") int num,
                                     HttpSession session) throws BusinessException {
        String requester = (String) session.getAttribute("user");
        OfflineCharge offlineCharge = offlineChargeService.createOfflineCharge(requester, target, num);
        if (offlineCharge == null) {

            throw new BusinessException("something wriong");
        }

        return jsonResultFactory.makeJsonResult("done", "done");
    }

    @RequestMapping(value = "/ensureRecharge")
    public String ensureRecharge(@RequestParam("id") String id, HttpSession session)
            throws JsonProcessingException, BusinessException {
        String responser = (String) session.getAttribute("user");
        OfflineCharge offlineCharge = offlineChargeService.ensureOfflineCharge(responser, id);
        if (offlineCharge == null) {
            throw new BusinessException("something wrong");
        }
        Account account = userService.getAccountByUsername(offlineCharge.getTarget());
        PocketCriteria pocketCriteria = new PocketCriteria();

        pocketCriteria.or()
                      .andUidEqualTo(account.getUid());

        List<Pocket> aList = pocketMapper.selectByExample(pocketCriteria);

        if (aList.isEmpty()) {

        }

        Pocket pocket = aList.get(0);

        ArrayList<String> list = new ArrayList<String>();
        list.add("sendMoneyChangeModalMessage");
        list.add("Pocket");
        list.add(offlineCharge.getTarget());
        list.add(String.valueOf(System.currentTimeMillis() / 1000));
        list.add(String.format("%.2f", offlineCharge.getNum() / 100.0));
        list.add(String.format("%.2f", pocket.getCurr_money() / 100.0));
        list.add("线下充值");

        return "done";
    }

    /**
     * 获取充值列表
     *
     * @param session
     * @param currentPage 需要查询的页码 默认 1
     * @param pageSize    每页显示的数量 默认 20
     * @param type        需要查询的类型 waittingAuth:等待认证 authed：已认证 默认 waittingAuth
     * @return
     * @Title getMyOfflineCharge
     */
    @RequestMapping(value = "/getMyOfflineCharge")
    public PageResult<OfflineCharge> getMyOfflineCharge(HttpSession session,
                                                        @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                        @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                        @RequestParam(value = "type", defaultValue = "waittingAuth") String type) {
        String username = (String) session.getAttribute("user");

        OfflineChargeCriteria offlineChargeCriteria = new OfflineChargeCriteria();
        if ("authed".equals(type)) {
            offlineChargeCriteria.or()
                                 .andRequesterEqualTo(username)
                                 .andStateEqualTo(OfflineChargeService.STATE_AUTHED);

        } else {
            offlineChargeCriteria.or()
                                 .andRequesterEqualTo(username)
                                 .andStateEqualTo(OfflineChargeService.STATE_INIT);
        }

        PageHelper.startPage(currentPage, pageSize);
        Page<OfflineCharge> list = (Page<OfflineCharge>) offlineChargeMapper.selectByExample(offlineChargeCriteria);

        return new PageResult<>(list);
    }

    /**
     * 获取认证的充值列表
     *
     * @param session
     * @param currentPage 需要查询的页码 默认 1
     * @param pageSize    每页显示的数量 默认 20
     * @param type        需要查询的类型 waittingAuth:等待认证 authed：已认证 默认 waittingAuth
     * @return
     * @Title getMyAuthOfflineCharge
     */
    @RequestMapping(value = "/getMyAuthOfflineCharge")
    public PageResult<OfflineCharge> getMyAuthOfflineCharge(HttpSession session,
                                                            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                            @RequestParam(value = "type", defaultValue = "waittingAuth") String type) {
        String username = (String) session.getAttribute("user");
        OfflineChargeCriteria offlineChargeCriteria = new OfflineChargeCriteria();
        if ("authed".equals(type)) {
            offlineChargeCriteria.or()
                                 .andResponserEqualTo(username)
                                 .andStateEqualTo(OfflineChargeService.STATE_AUTHED);

        } else {
            offlineChargeCriteria.or()
                                 .andResponserEqualTo(username)
                                 .andStateEqualTo(OfflineChargeService.STATE_INIT);
        }

        PageHelper.startPage(currentPage, pageSize);
        Page<OfflineCharge> list = (Page<OfflineCharge>) offlineChargeMapper.selectByExample(offlineChargeCriteria);

        return new PageResult<>(list);
    }

    /**
     * 搜索我的充值记录
     *
     * @param key
     * @param currentPage
     * @param pageSize
     * @param session
     * @return
     * @Title searchMyOfflineCharge
     */
    @RequestMapping("/searchMyOfflineCharge")
    public PageResult<OfflineCharge> searchMyOfflineCharge(@RequestParam("key") String key,
                                                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                           @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                           HttpSession session) {
        String username = (String) session.getAttribute("user");
        OfflineChargeCriteria offlineChargeCriteria = new OfflineChargeCriteria();

        offlineChargeCriteria.or()
                             .andRequesterEqualTo(username)
                             .andTargetEqualTo(key);
        offlineChargeCriteria.setOrderByClause("id desc");

        PageHelper.startPage(currentPage, pageSize);
        Page<OfflineCharge> list = (Page<OfflineCharge>) offlineChargeMapper.selectByExample(offlineChargeCriteria);

        return new PageResult<>(list);
    }

    /**
     * 搜索我认证的的充值记录
     *
     * @param key
     * @param currentPage
     * @param pageSize
     * @param session
     * @return
     * @Title searchMyAuthOfflineCharge
     */
    @RequestMapping("/searchMyAuthOfflineCharge")
    public PageResult<OfflineCharge> searchMyAuthOfflineCharge(@RequestParam("key") String key,
                                                               @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                               HttpSession session) {
        String username = (String) session.getAttribute("user");
        OfflineChargeCriteria offlineChargeCriteria = new OfflineChargeCriteria();

        offlineChargeCriteria.or()
                             .andResponserEqualTo(username)
                             .andTargetEqualTo(key);
        offlineChargeCriteria.setOrderByClause("id desc");

        PageHelper.startPage(currentPage, pageSize);
        Page<OfflineCharge> list = (Page<OfflineCharge>) offlineChargeMapper.selectByExample(offlineChargeCriteria);

        return new PageResult<>(list);
    }

    /**
     * 提升队长
     *
     * @param username
     * @return
     * @throws BusinessException
     * @Title uptoLeader
     */
    @RequestMapping("uptoLeader")
    public String uptoLeader(String username) throws BusinessException {

        UserBasicInfoShow userBasicInfoShows = userBasicInfoShowMapper.selectByUserName(username);


        if (userBasicInfoShows == null) {
            throw new BusinessException("此用户不存在");
        }
        List<UserMachineShow> userMachineShows = userMachineShowMapper.selectByUid(userBasicInfoShows.getAccount()
                                                                                                     .getUid());
        if (userBasicInfoShows.getAccountRealNameAuth() == null) {
            throw new BusinessException("此用户未认证");
        }
        if (userBasicInfoShows.getAccount()
                              .getLevel() < 20) {
            throw new BusinessException("农户不能成为队长");
        }
        if (userMachineShows.stream()
                            .noneMatch(userMachineShow -> UserMachineService.STATE_AUTH_PASSED.equals(userMachineShow.getUserMachine()
                                                                                                                     .getState()))) {
            throw new BusinessException("非认证农机手不能提升");
        }
        Account account = new Account();
        account.setUid(userBasicInfoShows.getAccount()
                                         .getUid());
        account.setDriver_leader(DRIVER_LEADER_AUTHED);
        if (accountMapper.updateByPrimaryKeySelective(account) > 0) {
            return "success";
        }

        throw new BusinessException("操作失败");
    }

    /**
     * 取消隊長
     *
     * @param username
     * @return
     * @throws BusinessException
     * @Title cancelLeader
     */
    @RequestMapping("cancelLeader")
    public String cancelLeader(String username) throws BusinessException {

        UserBasicInfoShow userBasicInfoShows = userBasicInfoShowMapper.selectByUserName(username);

        if (userBasicInfoShows == null) {
            throw new BusinessException("此用户不存在");
        }

        Account account = new Account();
        account.setUid(userBasicInfoShows.getAccount()
                                         .getUid());
        account.setDriver_leader(DRIVER_LEADER_UN_AUTHED);
        if (accountMapper.updateByPrimaryKeySelective(account) > 0) {
            return "success";
        }

        throw new BusinessException("操作失败");
    }


}
