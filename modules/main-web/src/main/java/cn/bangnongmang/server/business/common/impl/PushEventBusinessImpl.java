package cn.bangnongmang.server.business.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.server.business.common.PushEventBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.PushEventService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@Service
@Transactional(rollbackFor = { Exception.class })
public class PushEventBusinessImpl implements PushEventBusiness {

	@Autowired
	private UserService userService;

	@Autowired
	private PushEventService pushEventService;

	@Autowired
	private TradeFlowService tradeFlowService;

	private Integer currentBig = 0;

	private Integer currentSmall = 0;

	private int[] poolBig = { 150, 200, 125, 225, 325, 400, 75, 125, 25, 125, 150, 200, 225, 250, 275, 325, 375, 300,
			275, 250, 1650, 150, 2500, 375, 475, 900, 300, 1375, 825, 1150, 500, 275, 550, 25, 50, 75, 150, 225, 600,
			675, 225, 475, 525, 650, 600, 650, 1650, 450, 300, 800 };

	private int[] poolSmall = { 60, 80, 50, 90, 130, 160, 30, 50, 10, 50, 60, 80, 90, 100, 110, 130, 150, 120, 110, 100,
			660, 60, 1000, 150, 190, 360, 120, 550, 330, 460, 200, 110, 220, 10, 20, 30, 60, 90, 240, 270, 90, 190, 210,
			260, 240, 260, 660, 180, 120, 320 };
	
	@Override
	public PushEvent finishSharedMoments(Long uid) {

		if (userService.isFarmer(uid)) {
			return null;
		}

		List<PushEvent> events = pushEventService.getPushEventsByUsernameAndType(uid,
				PushEventService.TYPE_REDPACKET);

		if (events.isEmpty()) {
			PushEvent event = pushEventService.createPushEvent(uid, PushEventService.TYPE_REDPACKET);

			pushEventService.enablePushEvent(event);

			return event;
		} else {
			PushEvent event = events.get(0);

			if (event.getState().equals(PushEventService.STATE_FINISHED)) {
				return null;
			}

			return event;
		}

	}
	@Override
	public List<PushEvent> getUserActivePushEvent(Long uid) {

		return pushEventService.getAvailableEventsByUid(uid);

	}
	@Override
	public PocketLog openRedPocketByPushEventId(String id) throws BusinessException {

		PushEvent pushEvent = pushEventService.getPushEventById(id);

		Integer money = 10;

		if (pushEvent.getType().equals(PushEventService.TYPE_REDPACKET)
				|| pushEvent.getType().equals(PushEventService.TYPE_REGISTER)) {

			if (pushEvent.getType().equals(PushEventService.TYPE_REDPACKET)) {

				money = poolSmall[currentSmall];
				currentSmall = (currentSmall + 1) % poolSmall.length;

			} else if (pushEvent.getType().equals(PushEventService.TYPE_REGISTER)) {
				money = poolBig[currentBig];
				currentBig = (currentBig + 1) % poolBig.length;
			}

			if (pushEvent.getState().equals(PushEventService.STATE_FINISHED)) {

				throw new BusinessException("已经领过这个红包");
			}

			Boolean flag;
			PocketLog pocketLog = null;
			try {
				pocketLog = tradeFlowService.createTradeFlow(pushEvent.getUid(),
						TradeFlowService.POCKETLOG_TYPE_RECHARGE, money, TradeFlowService.POCKETLOG_METHOD_REDPACKET,
						"红包", "", "");
				flag = tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
				if (!flag) {
					throw new BusinessException("领取红包失败");
				}
			} catch (ServiceLayerExeption e) {
				throw new BusinessException("领取红包失败");
			}

			pushEventService.donePushEvent(pushEvent);

			return pocketLog;

		} else {

			return null;
		}

	}

}
