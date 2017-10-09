package cn.bangnongmang.data.combo.dao;

import java.util.List;

import cn.bangnongmang.data.combo.domain.UserMachineShow;

public interface UserMachineShowMapper {
	List<UserMachineShow> selectByUid(Long uid);
	UserMachineShow selectByUserMachineId(Long id);
	List<UserMachineShow> selectByUserMachineId();
}
