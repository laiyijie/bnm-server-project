package cn.bangnongmang.service.service.impl;

import cn.bangnongmang.data.dao.InviteFriendMapper;
import cn.bangnongmang.data.domain.InviteFriend;
import cn.bangnongmang.data.domain.InviteFriendCriteria;

import cn.bangnongmang.service.outerservice.util.ParamsCheck;
import cn.bangnongmang.service.service.InviteFriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by lucq on 2017/7/5.
 */
@Component("inviteFriendService")
public class InviteFriendServiceImp implements InviteFriendService {

	@Autowired
	private InviteFriendMapper inviteFriendMapper;
	@Override
	public Boolean sendMessage(String friendphone, String message)  {
		ParamsCheck.checkPhoneNumber(friendphone);
		return true;
	}

	@Override
	public Boolean insertInvite(Long uid, String firendPhone) {
		InviteFriend inviteFriend=new InviteFriend();
		inviteFriend.setUid(uid);
		inviteFriend.setFriend_phone(firendPhone);
		inviteFriend.setCreate_time(System.currentTimeMillis()/1000);

		if(inviteFriendMapper.insert(inviteFriend)>0){
			return  true;
		}else{
			return  false;
		}
	}

	@Override
	public Boolean SendTooFast(Long uid, String friendPhone, Long period) {

		InviteFriendCriteria inviteFriendCriteria = new InviteFriendCriteria();
		inviteFriendCriteria.or()
							.andUidEqualTo(uid)
							.andFriend_phoneEqualTo(friendPhone);
		inviteFriendCriteria.setOrderByClause("create_time desc");
		List<InviteFriend> inviteList = inviteFriendMapper.selectByExample(inviteFriendCriteria);
		InviteFriend inviteFriend = inviteList.isEmpty() ? null : inviteList.get(0);

		if (inviteFriend == null) {
			return false;
		} else if (System.currentTimeMillis() / 1000 - inviteFriend.getCreate_time() > period) {
			return false;
		} else {
			return true;
		}
	}
}
