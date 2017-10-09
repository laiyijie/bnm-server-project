package cn.bangnongmang.server.controller.wechat;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.wechat.WxOutputException;
import cn.bangnongmang.service.outerservice.WxOcService;
import cn.bangnongmang.server.util.ParamsCheck;

@Controller("wxAccLoginController")
@RequestMapping("/wx/account")
public class WxAccountLoginController {

    public static final String WX_UNION_ID_KEY = "unionid";
    public static final String WX_OPEN_ID_KEY = "openid";
    public static final String WX_ACCESS_TOKEN_KEY = "access_token";
    public static final String WX_CODE_KEY = "code";
    public static final String WX_STATE_KEY = "state";

    private final String REGISTER_STATE_KEY = "register";

    @Value("${front.baseUrlSegment}")
    private String frontBaseUrlSegment;
    @Value("${front.indexUrlSegment}")
    private String frontIndexUrlSegment;
    @Value("${front.loginUrlSegment}")
    private String frontLoginUrlSegment;
    @Value("${front.rewardRecommendUrlSegment}")
    private String frontRecommendUrlSegment;
    @Value("${front.newUserRewardUrlSegment}")
    private String frontUserRewardUrlSegment;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WxOcService wxOcService;
    @Autowired
    private AccountBusiness commonAccountBusiness;

    private final Logger logger = LogManager.getLogger(WxAccountLoginController.class);


    @RequestMapping("/wechatEnter")
    public void wechatEnter(@RequestParam(value = WX_CODE_KEY) String code,
                            @RequestParam(value = WX_STATE_KEY) String state,
                            HttpSession httpSession, HttpServletResponse response,
                            HttpServletRequest request) throws WxOutputException, IOException {

        logger.debug("request get :" + request.getParameter(WX_CODE_KEY));
        logger.debug("actual:" + code);
        JSONObject wechatBase = wxOcService.oAuthGetTokenAndOpenid(code);
        if (wechatBase == null) {
            throw new WxOutputException("请从微信登录");
        }
        JSONObject wechatUserInfo = wxOcService
                .getUserWechatInfo(wechatBase.getString(WX_ACCESS_TOKEN_KEY),
                        wechatBase.getString(WX_OPEN_ID_KEY));

        if (wechatUserInfo == null || wechatUserInfo.getString(WX_UNION_ID_KEY) == null) {
            throw new WxOutputException("从微信获取用户信息失败");
        }

        Account account = commonAccountBusiness
                .getUserInfoByUnionId(wechatUserInfo.getString(WX_UNION_ID_KEY));

        if (account == null) {
            // 未注册的逻辑

            httpSession.setAttribute(ServerSessionAttr.SESSION_UNIONID,
                    wechatBase.getString(WX_UNION_ID_KEY));
            httpSession.setAttribute(ServerSessionAttr.SESSION_OPENID,
                    wechatBase.getString(WX_OPEN_ID_KEY));
            httpSession.setAttribute(ServerSessionAttr.SESSION_ACCESS_TOKEN,
                    wechatBase.getString(WX_ACCESS_TOKEN_KEY));

            response.sendRedirect(getRedirectUrl(state));

        } else {

            httpSession
                    .setAttribute(ServerSessionAttr.SESSION_UNIONID, account.getWechat_union_id());
            httpSession.setAttribute(ServerSessionAttr.SESSION_OPENID, account.getWechat_open_id());
            httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, account.getUsername());
            httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());
            if (REGISTER_STATE_KEY.equals(state)) {
                state = frontIndexUrlSegment;

            }
            response.sendRedirect(getRedirectUrl(state));
        }
    }

    @RequestMapping("/inviteEnter")
    public void inviteEnter(@RequestParam(value = WX_CODE_KEY) String code,
                            @RequestParam(value = WX_STATE_KEY) String state,
                            HttpSession httpSession, HttpServletResponse response,
                            HttpServletRequest request) throws WxOutputException, IOException {

        JSONObject wechatBase = wxOcService.oAuthGetTokenAndOpenid(code);
        if (wechatBase == null) {
            throw new WxOutputException("请从微信登录");
        }
        JSONObject wechatUserInfo = wxOcService
                .getUserWechatInfo(wechatBase.getString(WX_ACCESS_TOKEN_KEY),
                        wechatBase.getString(WX_OPEN_ID_KEY));

        if (wechatUserInfo == null || wechatUserInfo.getString(WX_UNION_ID_KEY) == null) {
            throw new WxOutputException("从微信获取用户信息失败");
        }

        Account account = commonAccountBusiness
                .getUserInfoByUnionId(wechatUserInfo.getString(WX_UNION_ID_KEY));

        if (account == null) {
            // 未注册的逻辑

            httpSession.setAttribute(ServerSessionAttr.SESSION_UNIONID,
                    wechatBase.getString(WX_UNION_ID_KEY));
            httpSession.setAttribute(ServerSessionAttr.SESSION_OPENID,
                    wechatBase.getString(WX_OPEN_ID_KEY));
            httpSession.setAttribute(ServerSessionAttr.SESSION_ACCESS_TOKEN,
                    wechatBase.getString(WX_ACCESS_TOKEN_KEY));

            response.sendRedirect(getRedirectUrl(frontUserRewardUrlSegment));

        } else {
            httpSession
                    .setAttribute(ServerSessionAttr.SESSION_UNIONID, account.getWechat_union_id());
            httpSession.setAttribute(ServerSessionAttr.SESSION_OPENID, account.getWechat_open_id());
            httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, account.getUsername());
            httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());

            response.sendRedirect(getRedirectUrl(frontRecommendUrlSegment));
        }
    }

    private String getRedirectUrl(String state) {
//		if (order == "farmer") {
//			return frontBaseUrlSegment + frontIndexUrlSegment + "?datetime=" + System.currentTimeMillis() + "&level=1";
//		}
//		
//		if (order == "driver") {
//			return frontBaseUrlSegment + frontIndexUrlSegment + "?datetime=" + System.currentTimeMillis() + "&level=2";
//		}
        return frontBaseUrlSegment + state + "?datetime=" + System.currentTimeMillis();
    }

    @RequestMapping("/getVerifyCode")
    @ResponseBody
    public Object getVerifyCode(@RequestParam("username") String phoneNumber,
                                HttpSession httpSession)
            throws BusinessException, WxOutputException {

        ParamsCheck.checkPhoneNumber(phoneNumber);

        Account account = commonAccountBusiness.getUserInfoByUsername(phoneNumber);

        if (account != null) {
            throw new WxOutputException("这个手机号已注册，请去登录页登录");
        }
        String unionid = (String) httpSession.getAttribute(ServerSessionAttr.SESSION_UNIONID);
        if (unionid == null) {
            throw new WxOutputException("请从微信登录");
        }

        String code = commonAccountBusiness.getCommonVerifyCode(phoneNumber);

        if (code == null) {
            throw new WxOutputException("获取验证码失败，请稍后重试");
        }

        return "already_send";
    }

    @RequestMapping("/getLoginVerifyCode")
    @ResponseBody
    public Object getLoginVerifyCode(@RequestParam("username") String phoneNumber,
                                     HttpSession httpSession)
            throws BusinessException, WxOutputException {

        ParamsCheck.checkPhoneNumber(phoneNumber);

        Account account = commonAccountBusiness.getUserInfoByUsername(phoneNumber);

        if (account == null) {
            throw new WxOutputException("该手机号未注册，请先注册");
        }

        String code = commonAccountBusiness.getCommonVerifyCode(phoneNumber);

        if (code == null) {
            throw new WxOutputException("获取验证码失败，请稍后重试");
        }

        return "already_send";
    }

    @RequestMapping("/register")
    @ResponseBody
    public Object register(@RequestParam("username") String username,
                           @RequestParam("verifycode") String authCode,
                           @RequestParam("id") String id, HttpSession httpSession)
            throws BusinessException, WxOutputException {

        ParamsCheck.checkPhoneNumber(username);

        if (httpSession.getAttribute(ServerSessionAttr.SESSION_UID) != null) {
            throw new WxOutputException("您已登录，该微信号无法重复注册");
        }

        String accessToken = (String) httpSession
                .getAttribute(ServerSessionAttr.SESSION_ACCESS_TOKEN);
        String unionid = (String) httpSession.getAttribute(ServerSessionAttr.SESSION_UNIONID);
        String openid = (String) httpSession.getAttribute(ServerSessionAttr.SESSION_OPENID);

        if (accessToken == null || unionid == null || openid == null) {
            throw new WxOutputException("请从微信登录注册");
        }

        Account account = commonAccountBusiness
                .commonRegister(username, authCode, id, unionid, openid);

        if (account != null) {
            // TODO 需要增加微信事件通知

            httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, account.getUsername());
            httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());

            try {
                return objectMapper.writeValueAsString(account);
            } catch (JsonProcessingException e) {
                return "";
            }
        } else {

            throw new WxOutputException("注册失败");
        }
    }

    @RequestMapping("/tologin")
    @ResponseBody
    public Object login(@RequestParam("username") String username,
                        @RequestParam("verifycode") String authCode,
                        HttpSession httpSession) throws BusinessException, WxOutputException {

        ParamsCheck.checkPhoneNumber(username);

        if (httpSession.getAttribute(ServerSessionAttr.SESSION_UID) != null) {
            throw new WxOutputException("您已登录，请勿重复登录");
        }

        String unionid = (String) httpSession.getAttribute(ServerSessionAttr.SESSION_UNIONID);
        String openid = (String) httpSession.getAttribute(ServerSessionAttr.SESSION_OPENID);

        Account account = commonAccountBusiness.commonLogin(username, authCode);

        if (account != null) {

            httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME, account.getUsername());
            httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());
            if (unionid != null) {

                if (account.getWechat_union_id() == null || ""
                        .equals(account.getWechat_union_id())) {

                    account.setWechat_union_id(unionid);

                    account.setWechat_open_id(openid);

                    commonAccountBusiness.updateWechatInfo(account.getUid(), unionid, openid);
                } else {

                    if (!account.getWechat_union_id().equals(unionid)) {
                        throw new WxOutputException("此微信已绑定其他手机号");
                    }

                }

            } else {

                if (account.getWechat_union_id() == null || ""
                        .equals(account.getWechat_union_id())) {
                    throw new WxOutputException("未关联微信，无法登录");
                } else {
                    httpSession.setAttribute(ServerSessionAttr.SESSION_UNIONID,
                            account.getWechat_union_id());
                    httpSession.setAttribute(ServerSessionAttr.SESSION_OPENID,
                            account.getWechat_open_id());
                    httpSession.setAttribute(ServerSessionAttr.SESSION_USERNAME,
                            account.getUsername());
                    httpSession.setAttribute(ServerSessionAttr.SESSION_UID, account.getUid());
                }
            }

            try {
                account = commonAccountBusiness.getUserInfoByUsername(username);
                return objectMapper.writeValueAsString(account);
            } catch (JsonProcessingException e) {
                return "";
            }

        } else {

            throw new WxOutputException("登录失败");
        }
    }

}
