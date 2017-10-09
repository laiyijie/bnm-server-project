package cn.bangnongmang.admin.controller;

import static cn.bangnongmang.admin.enums.ServerSessionAttr.USER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.admin.model.NotifyDetail;
import cn.bangnongmang.admin.service.impl.NotifyEditService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.notify.server.data.domain.NotifyHook;
import cn.bangnongmang.notify.server.data.domain.NotifySendPattern;
import cn.bangnongmang.notify.server.data.domain.NotifyType;

/**
 * 通知相关的接口
 * 
 * @author Yunfei
 *
 */
@RestController
@RequestMapping(value = "notify")
public class NotifyEditController {

	@Autowired
	NotifyEditService notifyEditService;

	/**
	 * 获取通知类别列表
	 * 
	 * @param opUsername
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getNotifyTypes")
	public List<NotifyType> getNotifyTypes(@SessionAttribute(USER) String opUsername) throws BusinessException {
		List<NotifyType> result = notifyEditService.getNotifyTypeList(opUsername);

		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}

		return result;
	}

	/**
	 * 获取特定通知类型的所有触发列表
	 * 
	 * @param notifyType
	 *            通知类型
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getNotifyHookList")
	public List<NotifyHook> getNotifyHookList(@RequestParam(value = "notifyType") String notifyType,
			@SessionAttribute(USER) String opUsername) throws BusinessException {
		List<NotifyHook> result = notifyEditService.getNotifyHookList(opUsername, notifyType);

		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}

		return result;
	}

	@RequestMapping(value = "getHookList")
	public List<NotifyHook> getHookList(@SessionAttribute(USER) String opUsername) throws BusinessException {
		List<NotifyHook> result = notifyEditService.getHookList(opUsername);

		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}

		return result;
	}

	@RequestMapping(value = "disableNotifyTemplate")
	public String disableNotifyTemplate(@RequestParam(value = "notifyType") String notifyType,
			@RequestParam(value = "notifyHook") String notifyHook, @SessionAttribute(USER) String opUsername)
			throws BusinessException {
		boolean result = notifyEditService.disableNotifyPattern(opUsername, notifyHook, notifyType);

		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	@RequestMapping(value = "enableHookPattern")
	public NotifySendPattern enableHookPattern(@RequestParam(value = "notifyType") String notifyType,
			@RequestParam(value = "notifyHook") String notifyHook,
			@RequestParam(value = "notifyPattern") String notifyPattern, @SessionAttribute(USER) String opUsername)
			throws BusinessException {
		NotifySendPattern p = notifyEditService.enableNotifyPattern(notifyHook, notifyType, notifyPattern);
		if (p == null) {
			throw new BusinessException("对不起，没有权限");
		}
		return p;
	}

	/**
	 * 获取一个通知的详细信息
	 * 
	 * @param notifyType
	 *            通知类型
	 * @param hookName
	 *            触发时机名称
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getNotifyDetail")
	public NotifyDetail getNotifyDetail(@RequestParam(value = "notifyType") String notifyType,
			@RequestParam(value = "hookName") String hookName, @SessionAttribute(USER) String opUsername)
			throws BusinessException {
		NotifyDetail result = notifyEditService.getNotifyDetail(opUsername, notifyType, hookName);

		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}

		return result;
	}

	@RequestMapping(value = "testNotifyPattern")
	public String testNotifyPattern(@RequestParam(value = "notifyType") String notifyType,
			@RequestParam(value = "hookName") String hookName, @RequestParam(value = "pattern") String pattern,
			@RequestParam(value = "username") String username, @SessionAttribute(USER) String opUsername)
			throws BusinessException {

		boolean result = notifyEditService.testNotifyPattern(opUsername, notifyType, hookName, pattern, username);

		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	/**
	 * 提交一个通知模板
	 * 
	 * @param notifyType
	 *            通知类型
	 * @param hookName
	 *            触发时机名称
	 * @param pattern
	 *            模板内容
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "postNotifyType")
	public String postNotifyType(@RequestParam(value = "notifyType") String notifyType,
			@RequestParam(value = "hookName") String hookName, @RequestParam(value = "pattern") String pattern,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		boolean result = notifyEditService.postNotifyType(opUsername, notifyType, hookName, pattern);

		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

}
