package cn.bangnongmang.server.controller.android;

import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.service.service.StarService;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.driverapp.models.OrderStar;
import cn.bangnongmang.server.business.common.StarBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.server.io.android.show.AndroidStarShow;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/star")
public class StarController {

	@Autowired
	private StarBusiness commonStarBuisness;

	@Autowired
	private AndroidStarShow androidStarShow;

	@Autowired
	private AccountBusiness accountBusiness;

	//TODO: javadoc
	@ResponseBody
	@RequestMapping("/starLeader")
	public String starLeader(@RequestBody AndroidRequest androidRequest,
							 @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		double star = (double) in.get("star");
		String orderId = (String) in.get("orderId");
		String leaderId = (String) in.get("leaderId");
		commonStarBuisness.star(uid, accountBusiness.getUserInfoByUsername(leaderId).getUid(),
				star,
				orderId,
				StarService.STAR_LEADER);

		return "success";
	}

	@ResponseBody
	@RequestMapping("/starMember")
	public String starMember(@RequestBody AndroidRequest androidRequest,
							 @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

		Map<String, Object> in = androidRequest.resolveJsonParams();
		double star = (double) in.get("star");
		String orderId = (String) in.get("orderId");
		String memberId = (String) in.get("memberId");
		commonStarBuisness.star(uid, accountBusiness.getUserInfoByUsername(memberId).getUid(), star,
				orderId, StarService.STAR_MEMBER);

		return "success";
	}

	@ResponseBody
	@RequestMapping("/checkHasUnStarOrder")
	public OrderStar checkHasUnStarOrder(@RequestBody AndroidRequest androidRequest,
										 @SessionAttribute(SESSION_UID) Long uid) throws AndroidOutputException, BusinessException {

		return androidStarShow.checkHasUnStarOrder(uid);
	}


	// TODO: 农户评价队长
}
