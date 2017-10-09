package cn.bangnongmang.service.service;

/**
 * Created by lucq on 2017/7/5.
 */
public interface InviteFriendService {

	public Boolean sendMessage(String friendphone, String message);
	public Boolean insertInvite(Long uid, String firendPhone);
	public Boolean SendTooFast(Long uid, String friendPhone, Long period);
}
