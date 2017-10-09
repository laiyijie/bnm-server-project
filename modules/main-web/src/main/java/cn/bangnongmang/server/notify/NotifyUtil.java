package cn.bangnongmang.server.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.notify.bo.NotifyBusinessMessage;
import cn.bangnongmang.notify.client.NotifyClient;
import cn.bangnongmang.notify.server.data.domain.NotifyHook;
import cn.bangnongmang.service.service.UserService;

@Service
public class NotifyUtil {

	@Autowired
	private NotifyClient notifyClient;
	@Autowired
	private UserService userService;

	public void registerHook(String hook_name, String description, Object parameters) {
		NotifyHook notifyHook = new NotifyHook();
		notifyHook.setHook_name(hook_name);
		notifyHook.setDescription(description);
		notifyHook.setParameters(JSON.toJSONString(parameters));
		notifyClient.registerHook(notifyHook);
	}

	public void sendNotifyBusinessMessage(Long uid, String hookName, Object params) {
		Account account = userService.getUserInfo(uid);
		NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
		notifyBusinessMessage.setHookName(hookName);
		if (account == null) {
			return;
		}
		notifyBusinessMessage.setOpenid(account.getWechat_open_id());
		notifyBusinessMessage.setParams(JSON.parseObject(JSON.toJSONString(params)));
		notifyBusinessMessage.setPhoneNumber(account.getUsername());
		notifyClient.sendBusinessMessage(notifyBusinessMessage);
	}
	public void sendNotifyBusinessMessage(Long uid, String hookName, Object params ,String friendPhone ) {
		Account account = userService.getUserInfo(uid);
		NotifyBusinessMessage notifyBusinessMessage = new NotifyBusinessMessage();
		notifyBusinessMessage.setHookName(hookName);
		if (account == null) {
			return;
		}
		notifyBusinessMessage.setOpenid(account.getWechat_open_id());
		notifyBusinessMessage.setParams(JSON.parseObject(JSON.toJSONString(params)));
		notifyBusinessMessage.setPhoneNumber(friendPhone);
		notifyClient.sendBusinessMessage(notifyBusinessMessage);
	}

	public String convertTimemillisToString(Long time) {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
	}
}
