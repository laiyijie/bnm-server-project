package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.StarEvaluations;
import cn.bangnongmang.data.domain.StarUser;

public interface StarService {

	Double INIT_MEMBER_STAR = 4.0;
	Double INIT_LEADER_STAR = 4.0;
	String STAR_LEADER = "STAR_LEADER";
	String STAR_MEMBER = "STAR_MEMBER";
	String FARMER_STAR_LEADER = "FARMER_STAR_LEADER";

	boolean updateUserMemberStar(Long uid, double star);
	boolean updateUserLeaderStar(Long uid, double star);
	StarUser getUserStar(Long uid);
	int createEvaluation(Long valutor, Long valuted, double star, long time, String orderId,
						 String type);
	
	List<StarEvaluations> getStarEvaluationsByValutorAndOrderId(Long uid,String orderId);
	
	List<StarEvaluations> getStarEvaluationByValutorAndValutedAndOrderId(Long valutor,Long
			valuted,String orderId);

}
