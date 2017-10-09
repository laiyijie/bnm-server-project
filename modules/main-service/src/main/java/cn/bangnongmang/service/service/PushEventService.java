package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.PushEvent;

public interface PushEventService {

	public static final Integer STATE_INIT = 100;
	public static final Integer STATE_AVAILABLE = 300;
	public static final Integer STATE_FINISHED = 500;

	public static final Integer TYPE_REDPACKET = 100;
	public static final Integer TYPE_REGISTER = 200;

	PushEvent createPushEvent(Long uid, Integer type, Long startTime, Long endTime, String
			content, String ps);

	PushEvent createPushEvent(Long uid, Integer type, Long startTime, Long endTime);

	PushEvent createPushEvent(Long uid, Integer type);

	List<PushEvent> getPushEventsByUsernameAndType(Long uid,Integer type);
	
	boolean enablePushEvent(PushEvent pushEvent);
	
	boolean donePushEvent(PushEvent pushEvent);

	List<PushEvent> getAvailableEventsByUid(Long uid);
	
	PushEvent getPushEventById(String id);
}
