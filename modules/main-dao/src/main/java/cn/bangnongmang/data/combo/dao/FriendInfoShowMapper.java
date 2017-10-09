package cn.bangnongmang.data.combo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.bangnongmang.data.combo.domain.FriendInfoShow;
import cn.bangnongmang.data.combo.domain.FriendRequestInfoShow;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;

public interface FriendInfoShowMapper {
	List<FriendRequestInfoShow> selectFriendRequestListByResponser(Long responser);
	List<FriendInfoShow> selectFriendListByUsera(Long usera);
	FriendInfoShow selectFriendInfoShowByUseraAndUserb(@Param("usera")Long usera,@Param("userb")Long userb);
	List<FriendInfoShow> selectFriendListByUseraAndUserbList(Map param);
}
