package cn.bangnongmang.server.business.common;

import java.util.List;

import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.server.io.BusinessException;

public interface PushEventBusiness {

	PushEvent finishSharedMoments(Long uid);

	List<PushEvent> getUserActivePushEvent(Long uid);

	PocketLog openRedPocketByPushEventId(String id) throws BusinessException;


}
