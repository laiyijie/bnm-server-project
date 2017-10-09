package cn.bangnongmang.admin.controller;

import static cn.bangnongmang.admin.enums.ServerSessionAttr.USER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.model.SimpleInnerUser;
import cn.bangnongmang.admin.service.impl.UserService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.ClassTransUtil;
import cn.bangnongmang.admin.util.PageResult;

/**
 * 用户查看用户列表和修改权限的接口
 * 
 * @author Yunfei
 *
 */
@RestController
@RequestMapping(value = "/innerUser")
public class InnerUserController {

	@Autowired
	UserService userService;

	/**
	 * 用于获取用户列表<br>
	 * 
	 * PS：目前的策略为<br>
	 * 
	 * 管理员可以看到并修改所有人的信息<br>
	 * 
	 * 客服可以看到所有人的信息，但不能修改<br>
	 * 
	 * Leader可以看到所有手下人的信息，并修改其范围<br>
	 * 
	 * 市场人员无权查看信息<br>
	 * 
	 * @param currentPage
	 *            当前页面，默认1
	 * @param pageSize
	 *            页面容量，默认20
	 * @param level
	 *            需要过滤的等级
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getInnerUserList")
	public PageResult<SimpleInnerUser> getInnerUserList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "level", required = false) Integer level, @SessionAttribute(USER) String opUsername)
			throws BusinessException {
		PageResult<AdminAccount> result = userService.getInnerUserList(opUsername, currentPage, pageSize, level);
		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}
		return new ClassTransUtil<SimpleInnerUser>().transToTarget(SimpleInnerUser.class, result);
	}

	/**
	 * 获取用户详细信息
	 * 
	 * @param username
	 *            用户名
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getUserDetail")
	public SimpleInnerUser getUserDetail(@RequestParam(value = "username") String username,
			@SessionAttribute(USER) String opUsername) throws BusinessException {
		SimpleInnerUser result = userService.getUserDetail(opUsername, username);
		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}
		return result;
	}

	/**
	 * 用于获取Leader列表，在选择上级时使用
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getLeaderList")
	public List<SimpleInnerUser> getLeaderList(@SessionAttribute(USER) String opUsername) throws BusinessException {
		List<SimpleInnerUser> result = userService.getLeaderList(opUsername);
		if (result == null) {
			throw new BusinessException("对不起，没有权限");
		}
		return result;
	}

	/**
	 * 修改用户信息，不包括用户区域
	 * 
	 * @param username
	 *            用户名
	 * @param name
	 *            用户姓名
	 * @param phone
	 *            手机号
	 * @param level
	 *            用户等级
	 * @param superior
	 *            他的上级
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/updateUserProfile")
	public String updateUserProfile(@RequestParam(value = "username") String username,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "level", required = false) Integer level,
			@RequestParam(value = "superior", required = false) String superior,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		boolean result = userService.updateUserProfile(opUsername, username, name, phone, level, superior);
		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}
		return "success";
	}

	/**
	 * 添加一个用户的区域
	 * 
	 * @param username
	 *            用户名
	 * @param province
	 *            省
	 * @param city
	 *            市
	 * @param county
	 *            县
	 * @param town
	 *            镇
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/addAccountArea")
	public String addAccountArea(@RequestParam(value = "username") String username,
			@RequestParam(value = "province") String province, @RequestParam(value = "city") String city,
			@RequestParam(value = "county") String county, @RequestParam(value = "town") String town,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		boolean result = userService.addAccountArea(opUsername, username, province, city, county, town);
		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	/**
	 * 删除用户的一个区块
	 * 
	 * @param username
	 *            用户名
	 * @param areaId
	 *            位置ID
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/deleteAccountArea")
	public String deleteAccountArea(@RequestParam(value = "username") String username,
			@RequestParam(value = "areaId") Integer areaId, @SessionAttribute(USER) String opUsername)
			throws BusinessException {

		boolean result = userService.deleteAccountArea(opUsername, username, areaId);
		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	/**
	 * 搜索用户名、姓名或者手机号（全匹配）
	 * 
	 * @param key
	 *            需要搜索的关键词
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/searchAccount")
	public List<SimpleInnerUser> searchAccount(@RequestParam(value = "key") String key,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		List<SimpleInnerUser> innerUsers = userService.searchAccount(opUsername, key);
		if (innerUsers == null) {
			throw new BusinessException("对不起，没有权限");
		}

		return innerUsers;
	}
	
}
