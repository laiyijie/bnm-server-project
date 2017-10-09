package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.PushEventMapper;
import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.data.domain.PushEventCriteria;
import cn.bangnongmang.service.service.PushEventService;
import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@Service("S_PushEventService")
public class PushEventServiceImpl implements PushEventService{

	@Autowired
	private PushEventMapper pushEventMapper;
	
	@Autowired
	private ServiceUtil util;
	
	@Override
	public PushEvent createPushEvent(Long uid, Integer type, Long startTime, Long endTime,
									 String content,
			String ps) {
		PushEvent pushEvent = new PushEvent();
		
		pushEvent.setId(createId(uid));
		pushEvent.setContent(content);
		pushEvent.setEnd_time(endTime);
		pushEvent.setPs(ps);
		pushEvent.setStart_time(startTime);
		pushEvent.setState(STATE_INIT);
		pushEvent.setType(type);
		pushEvent.setUid(uid);
		
		pushEventMapper.insert(pushEvent);
		
		return pushEvent;
		
	}

	@Override
	public PushEvent createPushEvent(Long uid, Integer type, Long startTime, Long endTime) {
		
		return createPushEvent(uid, type, startTime, endTime, "", "");
	}

	@Override
	public PushEvent createPushEvent(Long uid, Integer type) {
		return createPushEvent(uid, type,0L,0L,"","");
	}

	
	private String createId(Long uid){

		return util.generateId(uid, 4, 6);
	}

	@Override
	public List<PushEvent> getPushEventsByUsernameAndType(Long uid, Integer type) {
		
		PushEventCriteria pushEventCriteria = new PushEventCriteria();
		pushEventCriteria.or().andUidEqualTo(uid)
		.andTypeEqualTo(type);
		return pushEventMapper.selectByExample(pushEventCriteria);
	}

	@Override
	public boolean enablePushEvent(PushEvent pushEvent) {
		
		PushEvent pushEvent2 = pushEventMapper.selectByPrimaryKey(pushEvent.getId());
		
		if (pushEvent2==null) {
			return false;
		}
		
		pushEvent2.setState(STATE_AVAILABLE);
		
		pushEventMapper.updateByPrimaryKey(pushEvent2);
		return true;
	}

	@Override
	public boolean donePushEvent(PushEvent pushEvent) {
		PushEvent pushEvent2 = pushEventMapper.selectByPrimaryKey(pushEvent.getId());
		
		if (pushEvent2==null) {
			return false;
		}
		
		pushEvent2.setState(STATE_FINISHED);
		
		pushEventMapper.updateByPrimaryKey(pushEvent2);
		return true;
	}

	@Override
	public List<PushEvent> getAvailableEventsByUid(Long uid) {
		PushEventCriteria pushEventCriteria = new PushEventCriteria();
		pushEventCriteria.or().andUidEqualTo(uid)
		.andStateEqualTo(STATE_AVAILABLE);
		return pushEventMapper.selectByExample(pushEventCriteria);
	}

	@Override
	public PushEvent getPushEventById(String id) {
		return pushEventMapper.selectByPrimaryKey(id);
	}
	
}
