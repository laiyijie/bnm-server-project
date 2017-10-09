package cn.bangnongmang.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.model.NotifyDetail;
import cn.bangnongmang.admin.util.AccountUtils;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.notify.bo.NotifyBusinessMessage;
import cn.bangnongmang.notify.client.NotifyClient;
import cn.bangnongmang.notify.server.data.domain.NotifyHook;
import cn.bangnongmang.notify.server.data.domain.NotifyHookCriteria;
import cn.bangnongmang.notify.server.data.domain.NotifySendPattern;
import cn.bangnongmang.notify.server.data.domain.NotifySendPatternCriteria;
import cn.bangnongmang.notify.server.data.domain.NotifySendPatternKey;
import cn.bangnongmang.notify.server.data.domain.NotifyType;
import cn.bangnongmang.notify.server.data.domain.NotifyTypeCriteria;
import cn.bangnongmang.notify.server.data.mapper.NotifyHookMapper;
import cn.bangnongmang.notify.server.data.mapper.NotifySendPatternMapper;
import cn.bangnongmang.notify.server.data.mapper.NotifyTypeMapper;

@Transactional
@Service
public class NotifyEditService {

	@Autowired
	private AdminAccountMapper adminAccMapper;
	@Autowired
	private NotifyTypeMapper notifyTypeMapper;
	@Autowired
	private NotifyHookMapper notifyHookMapper;
	@Autowired
	private NotifySendPatternMapper notifySendPatternMapper;
	@Autowired
	private NotifyClient notifyClient;
	@Autowired
	private AccountMapper accountMapper;

	public static final String NOTIFY_PATTERN_STATE_AVAILABLE = "AVAILABLE";
	public static final String NOTIFY_PATTERN_STATE_DISABLE = "DISABLE";

	public boolean disableNotifyPattern(String opUsername, String hook, String type) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			NotifySendPattern notifySendPattern = new NotifySendPattern();
			notifySendPattern.setHook_name(hook);
			notifySendPattern.setType_name(type);
			notifySendPattern.setState(NOTIFY_PATTERN_STATE_DISABLE);
			notifySendPatternMapper.updateByPrimaryKeySelective(notifySendPattern);

			notifySendPattern = new NotifySendPattern();
			notifySendPattern.setHook_name("TEST" + hook);
			notifySendPattern.setType_name(type);
			notifySendPattern.setState(NOTIFY_PATTERN_STATE_DISABLE);
			notifySendPatternMapper.updateByPrimaryKeySelective(notifySendPattern);

			return true;
		} else {
			return false;
		}
	}

	public boolean postNotifyType(String opUsername, String type, String hook, String sendPattern) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			NotifySendPatternKey notifySendPatternKey = new NotifySendPatternKey();
			notifySendPatternKey.setHook_name(hook);
			notifySendPatternKey.setType_name(type);
			NotifySendPattern notifySendPattern = notifySendPatternMapper.selectByPrimaryKey(notifySendPatternKey);
			if (notifySendPattern == null) {
				return false;
			}

			notifySendPattern.setPattern(sendPattern);
			notifySendPatternMapper.updateByPrimaryKey(notifySendPattern);

			return true;
		} else {
			return false;
		}
	}

	public NotifySendPattern enableNotifyPattern(String hook, String type, String pattern) {

		NotifySendPatternKey notifySendPatternKey = new NotifySendPatternKey();
		notifySendPatternKey.setHook_name(hook);
		notifySendPatternKey.setType_name(type);

		NotifySendPattern notifySendPattern = notifySendPatternMapper.selectByPrimaryKey(notifySendPatternKey);
		if (notifySendPattern != null) {
			notifySendPattern.setState(NOTIFY_PATTERN_STATE_AVAILABLE);
			notifySendPatternMapper.updateByPrimaryKeySelective(notifySendPattern);
			return notifySendPattern;
		}

		notifySendPattern = new NotifySendPattern();
		notifySendPattern.setHook_name(hook);
		notifySendPattern.setType_name(type);
		notifySendPattern.setPattern(pattern);
		notifySendPattern.setState(NOTIFY_PATTERN_STATE_AVAILABLE);

		notifySendPatternKey = new NotifySendPatternKey();
		notifySendPatternKey.setHook_name(hook);
		notifySendPatternKey.setType_name(type);
		notifySendPatternMapper.insert(notifySendPattern);
		return notifySendPattern;
	}

	private Account getUserInfo(String username) {

		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUsernameEqualTo(username);
		List<Account> accounts = accountMapper.selectByExample(accountCriteria);
		if (accounts.isEmpty()) {
			return null;
		}
		return accounts.get(0);

	}

	public boolean testNotifyPattern(String opUsername, String type, String hook, String sendPattern, String username)
			throws BusinessException {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			NotifyHook notifyHook = notifyHookMapper.selectByPrimaryKey(hook);

			NotifyHook notifyHookTest = new NotifyHook();
			notifyHookTest.setParameters(notifyHook.getParameters());
			notifyHookTest.setHook_name("TEST" + hook);
			notifyHookTest.setDescription("测试：" + notifyHook.getDescription());
			notifyClient.registerHook(notifyHookTest);

			NotifySendPatternKey notifySendPatternKey = new NotifySendPatternKey();
			notifySendPatternKey.setHook_name("TEST" + hook);
			notifySendPatternKey.setType_name(type);
			NotifySendPattern notifySendPattern = notifySendPatternMapper.selectByPrimaryKey(notifySendPatternKey);
			if (notifySendPattern == null) {
				notifySendPattern = new NotifySendPattern();
				notifySendPattern.setHook_name("TEST" + hook);
				notifySendPattern.setType_name(type);
				notifySendPattern.setPattern(sendPattern);
				notifySendPatternMapper.insert(notifySendPattern);
			} else {
				notifySendPattern.setPattern(sendPattern);
				notifySendPatternMapper.updateByPrimaryKey(notifySendPattern);
			}

			Account userAccount = getUserInfo(username);
			if (userAccount == null) {
				throw new BusinessException("测试用户未找到");
			} else if (type.equals("wechat")
					&& (userAccount.getWechat_open_id() == null || userAccount.getWechat_open_id().equals(""))) {
				throw new BusinessException("用户未绑定微信");
			}

			NotifySendPatternCriteria notifySendPatternCriteria = new NotifySendPatternCriteria();
			notifySendPatternCriteria.or().andHook_nameEqualTo("TEST" + hook);
			NotifySendPattern stateChage = new NotifySendPattern();
			stateChage.setState(NOTIFY_PATTERN_STATE_DISABLE);
			notifySendPatternMapper.updateByExampleSelective(stateChage, notifySendPatternCriteria);

			notifySendPatternCriteria.clear();
			notifySendPatternCriteria.or().andHook_nameEqualTo("TEST" + hook).andType_nameEqualTo(type);
			stateChage.setState(NOTIFY_PATTERN_STATE_AVAILABLE);
			notifySendPatternMapper.updateByExampleSelective(stateChage, notifySendPatternCriteria);

			NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
			notifyBusinessMessage.setHookName(notifyHookTest.getHook_name());
			notifyBusinessMessage.setOpenid(userAccount.getWechat_open_id());
			notifyBusinessMessage.setPhoneNumber(userAccount.getUsername());
			Map<String, Object> params = (Map<String, Object>) JSON.parse(notifyHookTest.getParameters());
			notifyBusinessMessage.setParams(params);

			notifyClient.sendBusinessMessage(notifyBusinessMessage);

			// notifySendPatternMapper.deleteByPrimaryKey(notifySendPattern);
			// notifyClient.deleteHook("TEST" + hook);
			//
			return true;
		} else {
			return false;
		}
	}

	public List<NotifyType> getNotifyTypeList(String opUsername) {

		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			NotifyTypeCriteria criteria = new NotifyTypeCriteria();
			criteria.or();

			List<NotifyType> notifyTypes = notifyTypeMapper.selectByExample(criteria);

			return notifyTypes;
		} else {
			return null;
		}

	}

	public List<NotifyHook> getHookList(String opUsername) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(account)) {
			List<NotifyHook> notifyHooks = notifyHookMapper.selectByExample(new NotifyHookCriteria());
			return notifyHooks;
		} else {
			return null;
		}
	}

	public List<NotifyHook> getNotifyHookList(String opUsername, String type) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			NotifySendPatternCriteria criteria = new NotifySendPatternCriteria();
			criteria.or().andType_nameEqualTo(type).andStateEqualTo(NOTIFY_PATTERN_STATE_AVAILABLE);

			List<NotifySendPattern> notifySendPatterns = notifySendPatternMapper.selectByExample(criteria);

			List<NotifyHook> notifyHooks = new ArrayList<>();

			for (NotifySendPattern notifySendPattern : notifySendPatterns) {
				if (!notifySendPattern.getHook_name().startsWith("TEST")) {
					NotifyHook notifyHook = notifyHookMapper.selectByPrimaryKey(notifySendPattern.getHook_name());
					if (notifyHook != null) {
						notifyHooks.add(notifyHook);
					}
				}
			}

			return notifyHooks;
		} else {
			return null;
		}
	}

	public NotifyDetail getNotifyDetail(String opUsername, String type, String hook) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			NotifyType notifyType = notifyTypeMapper.selectByPrimaryKey(type);
			NotifyHook notifyHook = notifyHookMapper.selectByPrimaryKey(hook);
			if (notifyHook == null || notifyType == null) {
				return null;
			}

			NotifySendPatternKey notifySendPatternKey = new NotifySendPatternKey();
			notifySendPatternKey.setHook_name(hook);
			notifySendPatternKey.setType_name(type);
			NotifySendPattern notifySendPattern = notifySendPatternMapper.selectByPrimaryKey(notifySendPatternKey);
			if (notifySendPattern == null) {
				return null;
			}

			NotifyDetail notifyDetail = new NotifyDetail();
			notifyDetail.setHook_name(notifyHook.getHook_name());
			notifyDetail.setType_name(notifyType.getType_name());
			notifyDetail.setParameters(notifyHook.getParameters());
			notifyDetail.setHook_description(notifyHook.getDescription());
			notifyDetail.setType_description(notifyType.getDescription());
			notifyDetail.setPattern(notifySendPattern.getPattern());

			return notifyDetail;

		} else {
			return null;
		}
	}

}
