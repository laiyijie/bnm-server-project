package cn.bangnongmang.data.combo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;

public interface  UserBasicInfoShowMapper {
	UserBasicInfoShow selectByUserName(String username);
	UserBasicInfoShow selectByUid(Long uid);
	List<UserBasicInfoShow> selectRealNameAuthListByLevel(@Param("lowLevel") Integer lowLevel, @Param("highLevel") Integer highLevel);
	List<UserBasicInfoShow> selectRealNameAuthListByType(Integer type);
	List<UserBasicInfoShow> searchRealNameAuthListByNameOrPhone(String key);
	List<UserBasicInfoShow> searchRealNameAuthListByPhoneList(List<String> phoneList);
}
