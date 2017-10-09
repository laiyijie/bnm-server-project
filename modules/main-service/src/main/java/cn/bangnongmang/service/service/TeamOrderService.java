package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.combo.domain.TeamInfoShow;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamJoinRequest;

public interface TeamOrderService {

	final int REQUEST_WAITTING = 200;
	final int REQUEST_ACCEPTED = 300;
	// final int REQUEST_REJECTED = 400;
	// final int REQUEST_CANCELED = 500;
	// final int REQUEST_DELETED = 600;
	// final int REQUEST_REMOVED = 700;
	final int TEAM_IN_ORDER_STATE_GRABED = 200;

	Long createOrderTeam(String orderid, Long uid, String msg);

	Long createJoinOrderTeamRequest(Long teamid, Long uid, String msg);

	TeamJoinRequest getTeanJoinRequest(Long teamid, Long uid);

	Boolean deleteTeamRequestById(Long id);

	Boolean deleteRequestByNameAndState(Long uid, Integer state);

	Boolean deleteTeamNotAcceptMemberByTeamId(Long teamId);


	List<TeamJoinRequest> getTeamJoinRequestsByTeamId(Long teamid);

	List<TeamJoinRequest> getTeamJoinRequestsByTeamIdAndState(Long teamid, Integer state);

	List<TeamJoinRequest> getTeamJoinRequestsByUsername(Long uid);

	Long countTeamJoinRequestsByTeamIdAndState(Long teamid, Integer state);

	List<TeamInOrder> getTeamInOrdersByLeaderUsername(Long uid);

	TeamInOrder getTeamInOrderByLeaderUsernameAndOrderId(Long uid, String orderid);

	TeamInOrder getTeamInOrderByTeamId(Long teamid);

	List<TeamInOrder> getTeamInOrderByOrderId(String orderId);

	Boolean changeTeamRequestStateById(Long id, Integer state);

	Boolean deleteTeamsAndRequestsByOrderId(String orderId, Long exceptFor);

	Boolean deleteTeamRequestByTeamId(Long teamId);

	Boolean deleteTeamByTeamId(Long teamId);

	void deleteTeamRequestByTeamIdAndState(Long teamInOrderId, int requestWaitting);

	void updateTeamLeader(Long teamId,Long uid);


	List<TeamInfoShow> getTeamInfoShowByOrderId(String orderId);
	TeamInfoShow getTeamInfoShowByTeamId(Long teamId);

}
