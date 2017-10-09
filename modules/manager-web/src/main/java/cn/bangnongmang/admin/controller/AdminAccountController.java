package cn.bangnongmang.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.JsonResult;
import cn.bangnongmang.admin.util.JsonResultFactory;
import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.service.impl.UserService;

@RestController
public class AdminAccountController {
	@Autowired
	private UserService admin;
	@Autowired
	private JsonResultFactory jsonResultFactory;
	@Autowired
	private AdminAccountMapper mapper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public JsonResult loginPage() throws BusinessException {

		throw new BusinessException("not_loging");
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {

		if (admin.login(username, password)) {

			session.setAttribute("user", username);
			return "success";
		}
		return "failed";
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public String logout(HttpSession session) {

		session.invalidate();
		return "done";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, HttpSession session) throws BusinessException {

		String creName = (String) session.getAttribute("user");

		if (admin.changePassword(creName, oldPassword, newPassword)) {
			return "done";
		}

		throw new BusinessException("old password not right");
	}

	@RequestMapping(value = "/getAdminList")
	public List<String> getAdminList(HttpSession session) throws BusinessException {

		String username = (String) session.getAttribute("user");
		List<String> list = admin.getAdminAccountList(username);
		if (list != null) {
			return list;
		} else {
			throw new BusinessException("Permission Deny");
		}
	}

	@RequestMapping(value = "/modifyPassword")
	public String modifyPassword(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) throws BusinessException {

		String opUsername = (String) session.getAttribute("user");
		AdminAccount a = admin.changeUserPassword(opUsername, username, password);
		if (a != null) {
			return "done";
		}
		throw new BusinessException("something wrong");

	}

	@ResponseBody
	@RequestMapping(value = "/createAccount")
	public JsonResult createAccount(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) throws BusinessException {

		AdminAccount account = admin.createUser((String) session.getAttribute("user"), username, password);
		if (account != null) {
			return jsonResultFactory.makeJsonResult("done", "Done");
		}
		throw new BusinessException();

	}

	@ResponseBody
	@RequestMapping(value = "/getMyinfo")
	public Map<String, Object> getMyinfo(HttpSession session) {
		AdminAccount account = mapper.selectByPrimaryKey((String) session.getAttribute("user"));

		Map<String, Object> re = new HashMap<String, Object>();

		re.put("username", account.getUsername());
		re.put("level", account.getLevel());

		return re;

	}
	@ResponseBody
	@RequestMapping(value = "/deleteAccount")
	public String deleteAccount(@RequestParam("username") String username) throws BusinessException {
		if (username == null) {
			throw new BusinessException("用户不能为空");
		}
		AdminAccount account = mapper.selectByPrimaryKey(username);
		if (null == account) {
			throw new BusinessException("用户不存在");
		}
		if (admin.deleteUser(username)) {
			return "success";
		}
		throw new BusinessException("删除失败");

	}
}
