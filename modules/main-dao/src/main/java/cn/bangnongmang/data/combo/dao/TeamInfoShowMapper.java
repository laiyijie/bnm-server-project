package cn.bangnongmang.data.combo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bangnongmang.data.combo.domain.TeamInfoShow;

public interface TeamInfoShowMapper {
	List<TeamInfoShow> selectTeamInfoShowByOrderId(String orderId);

	List<TeamInfoShow> selectTeamInfoShowByRequester(Long uid);

	List<TeamInfoShow> selectTeamInfoShowByRequesterAndState(@Param("uid") Long uid,
			@Param("state") Integer state);

	List<TeamInfoShow> selectTeamInfoShowByLeader(Long leader);

	TeamInfoShow selectTeamInfoShowByTeamId(Long teamId);

}
