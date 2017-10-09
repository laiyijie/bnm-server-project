package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.data.domain.InviteFriend;
import cn.bangnongmang.server.business.common.InviteFriendBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.swagger.show.PhoneFriendShow;
import cn.bangnongmang.server.swagger.api.PhoneApi;
import cn.bangnongmang.server.swagger.model.PhoneFriend;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by lucq on 2017/7/4.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class PhoneFriendController implements PhoneApi {
	@Autowired
	private PhoneFriendShow phoneFriendShow;
	@Autowired
	private InviteFriendBusiness inviteFriendBusiness;
	@Override
	public ResponseEntity<Void> phoneInvitePost(
			@ApiParam(value = "手机号码", required=true) @RequestParam(value="phonenum", required=true) String phonenum,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		inviteFriendBusiness.inviteFriend(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID),phonenum);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PhoneFriend>> phonePost(
			@ApiParam(value = "通讯录号码列表" ,required=true )  @Valid @RequestBody List<String> phoneList,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ResponseEntity<List<PhoneFriend>>(phoneFriendShow.getPhoneFriend(
				BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr
						.SESSION_UID),phoneList), HttpStatus.OK);
	}
}
