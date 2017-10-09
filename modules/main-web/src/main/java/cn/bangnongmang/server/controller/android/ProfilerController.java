package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.SESSION_UID;
import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.SESSION_USERNAME;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.driverapp.models.GeoInfo;
import cn.bangnongmang.driverapp.models.MyProfile;
import cn.bangnongmang.driverapp.models.MyUserInfo;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidOrderShow;
import cn.bangnongmang.server.io.android.show.AndroidUserShow;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;

import com.fasterxml.jackson.core.type.TypeReference;

@Controller
@RequestMapping("/center")
public class ProfilerController {

	@Autowired
	private AndroidUserShow androidUserShow;


	/**
	 * 
	 * 获取个人中心信息
	 * 
	 * @Title getMyprofile
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping("/getMyProfile")
	@ResponseBody
	public MyProfile getMyprofile(@SessionAttribute(SESSION_UID) Long uid) {

		return androidUserShow.getMyProfile(uid);

	}

}
