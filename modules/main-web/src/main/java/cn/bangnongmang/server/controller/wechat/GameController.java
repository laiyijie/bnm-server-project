package cn.bangnongmang.server.controller.wechat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.leonoss.wechat.apppay.client.dto.WechatJsApiConfigParams;

import cn.bangnongmang.data.dao.GameCountMapper;
import cn.bangnongmang.data.domain.GameCount;
import cn.bangnongmang.data.domain.GameCountCriteria;
import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.business.common.WxPubBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.wechat.WxOutputException;
import cn.bangnongmang.service.outerservice.WxOcService;

import static cn.bangnongmang.server.controller.wechat.WxAccountLoginController.*;
import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/wx/game")
public class GameController {

	@Autowired
	private WxOcService wxOcService;
	@Autowired
	private WxPubBusiness wechatBusiness;

	@Resource(name = "commonPayment")
	private PaymentBusiness paymentBusiness;

	@Autowired
	private GameCountMapper gameCountMapper;

	private static final Integer MAX_DIED = 10;

	private transient final static Logger logger = LogManager.getLogger(GameController.class);

	@RequestMapping("/isFromWechat")
	public String isFromWechat(HttpSession session) throws BusinessException {

		String gameer = (String) session.getAttribute(SESSION_OPENID);

		if (gameer == null) {
			throw new BusinessException(90001, "请从微信登录");
		}

		return "success";
	}

	@RequestMapping("/gameEnter")
	public void wechatEnter(@RequestParam(value = WX_CODE_KEY) String code,
			@RequestParam(value = WX_STATE_KEY) String state, HttpSession httpSession, HttpServletResponse response,
			HttpServletRequest request) throws WxOutputException, IOException, BusinessException {

		JSONObject wechatBase = wxOcService.oAuthGetTokenAndOpenid(code);
		if (wechatBase == null) {
			throw new WxOutputException("请从微信登录");
		}

		// if
		// (!"oHLNYv83dLCfvQsh0QKLK6sLc6FE".equals(wechatBase.getString(WX_OPEN_ID_KEY)))
		// {
		// throw new WxOutputException("维护中请重试");
		// }

		String openId = wechatBase.getString(WX_OPEN_ID_KEY);

		JSONObject userInfo = wxOcService.getUserWechatInfo(openId);
		if (userInfo == null) {
			throw new BusinessException("获取用户信息错误");
		}
		JSONObject userLoginInfo = wxOcService.getUserWechatInfo(wechatBase.getString(WX_ACCESS_TOKEN_KEY), openId);
		String nickname = userLoginInfo.getString("nickname");
		httpSession.setAttribute("nickname", nickname);

		if (userInfo.getInteger("subscribe") == 0) {
			httpSession.setAttribute(SESSION_GAME, false);
		} else {
			httpSession.setAttribute(SESSION_GAME, false);
		}
		httpSession.setAttribute(ServerSessionAttr.SESSION_OPENID, wechatBase.getString(WX_OPEN_ID_KEY));
		response.sendRedirect("/game");

	}

	@RequestMapping("/getWxPayParam")
	public WechatJsApiConfigParams getWxPayParam(@RequestParam(required = false) String name) {
		if (name != null) {
			return wechatBusiness.getWechatJsapiParams("http://bnm.agribiotech.cn/game/" + name);
		}
		return wechatBusiness.getWechatJsapiParams("http://bnm.agribiotech.cn/game/");
	}

	@RequestMapping("/restart")
	public Object restart(@SessionAttribute(SESSION_OPENID) String openId, HttpSession httpSession)
			throws BusinessException {
		GameCount gameCount = gameCountMapper.selectByPrimaryKey(openId);
		Boolean isSubscripe = (Boolean) httpSession.getAttribute(SESSION_GAME);
		String username = (String) httpSession.getAttribute(SESSION_USERNAME);
		if (isSubscripe == null) {
			if (username != null) {
				isSubscripe = true;
			}
			throw new BusinessException(90001, "请从微信登录");
		}
		// 无论是否关注都为false 1.14 新增
		isSubscripe = false;
		if (isSubscripe) {
			return "success";
		} else {
			if (gameCount == null) {
				return "success";
			}
			if (gameCount.getCount() >= MAX_DIED + gameCount.getRecharge_time() * MAX_DIED) {
				return "need_recharge";
			}
			return "success";
		}
	}

	@RequestMapping("/rechargeFinished")
	public void rechargeFinished(@SessionAttribute(SESSION_OPENID) String openid) throws BusinessException {

		GameCount gameCount = gameCountMapper.selectByPrimaryKey(openid);
		if (gameCount == null) {
			throw new BusinessException("error");
		}

		gameCount.setRecharge_time(gameCount.getRecharge_time() + 1);
		gameCountMapper.updateByPrimaryKey(gameCount);
	}

	@RequestMapping("/sharedFinished")
	public void sharedFinished(@SessionAttribute(SESSION_OPENID) String openid) throws BusinessException {

		GameCount gameCount = gameCountMapper.selectByPrimaryKey(openid);
		if (gameCount == null) {
			throw new BusinessException("error");
		}

		gameCount.setRecharge_time(gameCount.getRecharge_time() + 10000);
		gameCountMapper.updateByPrimaryKey(gameCount);
	}

	@RequestMapping("/died")
	public void died(@SessionAttribute(SESSION_OPENID) String openId, @SessionAttribute("nickname") String nickname,
			@RequestParam("score") Integer score, HttpSession httpSession) throws BusinessException {

		GameCount gameCount = gameCountMapper.selectByPrimaryKey(openId);
		Boolean isSubscripe = (Boolean) httpSession.getAttribute(SESSION_GAME);
		String username = (String) httpSession.getAttribute(SESSION_USERNAME);
		if (isSubscripe == null) {
			if (username != null) {
				isSubscripe = true;
			}
			throw new BusinessException(90001, "请从微信登录");
		}

		if (gameCount == null) {
			gameCount = new GameCount();
			gameCount.setCount(1);
			gameCount.setHigest_time(System.currentTimeMillis());
			gameCount.setHight_score(score);
			gameCount.setNick_name(nickname);
			gameCount.setOpenid(openId);
			gameCount.setRecharge_time(0);
			gameCountMapper.insert(gameCount);
		} else {
			gameCount.setCount(gameCount.getCount() + 1);
			if (gameCount.getHight_score() < score) {
				gameCount.setHigest_time(System.currentTimeMillis());
				gameCount.setHight_score(score);
			}

			gameCountMapper.updateByPrimaryKey(gameCount);
		}
	}

	@RequestMapping("/getRanking")
	public List<GameCount> getRanking() {

		PageHelper.startPage(1, 6);
		GameCountCriteria gameCountCriteria = new GameCountCriteria();
		gameCountCriteria.or();
		gameCountCriteria.setOrderByClause("hight_score desc");

		return gameCountMapper.selectByExample(gameCountCriteria);
	}

	@RequestMapping("/getMyRankInfo")
	public JSONObject getMyRankInfo(@SessionAttribute(SESSION_OPENID) String openid) {
		GameCount myGameCount = gameCountMapper.selectByPrimaryKey(openid);
		GameCountCriteria gameCountCriteria = new GameCountCriteria();
		gameCountCriteria.or().andHight_scoreGreaterThanOrEqualTo(myGameCount.getHight_score());

		long num = gameCountMapper.countByExample(gameCountCriteria);
		GameCountCriteria allGameCountCriteria = new GameCountCriteria();
		allGameCountCriteria.or();
		long totalNum = gameCountMapper.countByExample(allGameCountCriteria);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("myGameCount", myGameCount);
		jsonObject.put("rankNum", num);
		jsonObject.put("totalNum", totalNum);

		return jsonObject;
	}

	@RequestMapping("/getOrderByScore")
	public JSONObject getOrderByScore(@SessionAttribute(SESSION_OPENID) String openid,
			@RequestParam("score") Integer score) {
		GameCount myGameCount = gameCountMapper.selectByPrimaryKey(openid);
		GameCountCriteria gameCountCriteria = new GameCountCriteria();
		gameCountCriteria.or().andHight_scoreGreaterThanOrEqualTo(score);

		long num = gameCountMapper.countByExample(gameCountCriteria);
		GameCountCriteria allGameCountCriteria = new GameCountCriteria();
		allGameCountCriteria.or();
		long totalNum = gameCountMapper.countByExample(allGameCountCriteria);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("myGameCount", myGameCount);
		jsonObject.put("rankNum", num);
		jsonObject.put("totalNum", totalNum);

		return jsonObject;
	}

	@RequestMapping("/getMyHighest")
	public Integer getMyHighest(@SessionAttribute(SESSION_OPENID) String openid) {
		GameCount gameCount = gameCountMapper.selectByPrimaryKey(openid);
		if (gameCount == null) {
			return 0;
		}
		return gameCount.getHight_score();
	}

	@RequestMapping("/isFirst")
	public String isFirst(@SessionAttribute(SESSION_OPENID) String openid) {
		GameCount gameCount = gameCountMapper.selectByPrimaryKey(openid);
		if (gameCount == null) {
			return "yes";
		}
		return "no";
	}

}
