package cn.bangnongmang.server.business.common.impl;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.server.business.common.InviteFriendBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.notify.FriendNotify;
import cn.bangnongmang.service.service.InviteFriendService;
import cn.bangnongmang.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucq on 2017/7/5.
 */
@Component("inviteFriendBusiness")
public class InviteFriendBusinessImp implements InviteFriendBusiness {

	@Autowired
	private InviteFriendService inviteFriendService;
	@Autowired
	private UserBasicInfoShowMapper userBasicInfoShowMapper;
	@Override
	public Boolean inviteFriend(Long uid, String friendPhone) {


		UserBasicInfoShow userBasicInfoShow=userBasicInfoShowMapper.selectByUid(uid);
		AccountRealNameAuth accountRealNameAuth=userBasicInfoShow.getAccountRealNameAuth();
		if(accountRealNameAuth==null&&!accountRealNameAuth.getState().equals(UserService.REAL_NAME_AUTH_STATE_PASS)){
			throw new BusinessException("您还未实名认证，快去实名认证吧");
		}



		if(inviteFriendService.SendTooFast(uid,friendPhone,86400L)){
			throw new BusinessException("您好，您已近发送过好友请求");
		}

        //friendNotify.sendRequestByTextMessage(uid,friendPhone);

		if(inviteFriendService.insertInvite(uid,friendPhone)){
			return true;
		}

		return false;
	}
}
