package cn.bangnongmang.service.service;

import cn.bangnongmang.data.domain.FollowOrderKey;

public interface FollowOrderService {

	FollowOrderKey getFollowOrder(Long uid, String orderId);

	boolean unfollowOrder(Long uid , String orderId);

	boolean followOrder(Long uid, String orderId);

	void unfollowOrderByOrderId(String orderId);

}
