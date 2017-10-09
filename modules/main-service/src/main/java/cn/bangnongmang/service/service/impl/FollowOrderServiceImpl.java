package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.FollowOrderMapper;
import cn.bangnongmang.data.domain.FollowOrderCriteria;
import cn.bangnongmang.data.domain.FollowOrderKey;
import cn.bangnongmang.service.service.FollowOrderService;

@Service("S_FollowOrderService")
public class FollowOrderServiceImpl implements FollowOrderService {

	@Autowired
	private FollowOrderMapper followOrderMapper;

	@Override
	public boolean followOrder(Long uid, String orderId) {

		if (getFollowOrder(uid, orderId) == null) {

			FollowOrderKey followOrderKey = new FollowOrderKey();
			followOrderKey.setOrder_id(orderId);
			followOrderKey.setUid(uid);
			followOrderMapper.insert(followOrderKey);
		}

		return true;
	}

	@Override
	public boolean unfollowOrder(Long uid, String orderId) {
		if (getFollowOrder(uid, orderId) != null) {

			FollowOrderKey followOrderKey = new FollowOrderKey();
			followOrderKey.setOrder_id(orderId);
			followOrderKey.setUid(uid);
			followOrderMapper.deleteByPrimaryKey(followOrderKey);
		}
		return true;
	}

	@Override
	public FollowOrderKey getFollowOrder(Long uid, String orderId) {

		FollowOrderCriteria followOrderCriteria = new FollowOrderCriteria();
		followOrderCriteria.or().andOrder_idEqualTo(orderId).andUidEqualTo(uid);

		List<FollowOrderKey> followOrderKeys = followOrderMapper.selectByExample(followOrderCriteria);
		if (followOrderKeys.isEmpty()) {
			return null;
		}
		return followOrderKeys.get(0);
	}

	@Override
	public void unfollowOrderByOrderId(String orderId) {
		FollowOrderCriteria followOrderCriteria = new FollowOrderCriteria();
		followOrderCriteria.or().andOrder_idEqualTo(orderId);
		followOrderMapper.deleteByExample(followOrderCriteria);
	}
}
