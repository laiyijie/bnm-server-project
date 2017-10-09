package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.StarEvaluationsMapper;
import cn.bangnongmang.data.dao.StarUserMapper;
import cn.bangnongmang.data.domain.StarEvaluations;
import cn.bangnongmang.data.domain.StarEvaluationsCriteria;
import cn.bangnongmang.data.domain.StarUser;
import cn.bangnongmang.service.service.StarService;

@Service("S_StarService")
public class StarServiceImpl implements StarService {

	@Autowired
	private StarUserMapper starUserMapper;
	@Autowired
	private StarEvaluationsMapper starEvaluationsMapper;

	private final static double WEIGHT = 2.8;

	@Override
	public int createEvaluation(Long valutor, Long valuted, double star, long time, String
			orderId, String type) {
		// TODO Auto-generated method stub
		StarEvaluations starEvaluations = new StarEvaluations();
		starEvaluations.setOrder_id(orderId);
		starEvaluations.setStar(star);
		starEvaluations.setTime(time);
		starEvaluations.setType(type);
		starEvaluations.setValuted(valuted);
		starEvaluations.setValutor(valutor);
		return starEvaluationsMapper.insert(starEvaluations);

	}

	@Override
	public StarUser getUserStar(Long uid) {
		StarUser starUser = starUserMapper.selectByPrimaryKey(uid);
		return starUser;
	}

	private int createUserStar(Long uid) {
		StarUser starUser = new StarUser();
		starUser.setUid(uid);
		starUser.setMember_evaluations(0);
		starUser.setCaptain_evaluations(0);
		starUser.setCaptain_star(INIT_LEADER_STAR);
		starUser.setMember_star(INIT_MEMBER_STAR);
		return starUserMapper.insertSelective(starUser);

	}

	@Override
	public boolean updateUserLeaderStar(Long uid, double star) {
		StarUser starUser = getUserStar(uid);
		if (starUser == null) {
			if (createUserStar(uid) > 0) {
				starUser = getUserStar(uid);
			}
		}
		if (starUser != null) {
			starUser.setCaptain_evaluations(starUser.getCaptain_evaluations() + 1);
			starUser.setCaptain_star((starUser.getCaptain_star() * WEIGHT + star) / (WEIGHT + 1));
			if (starUserMapper.updateByPrimaryKeySelective(starUser) > 0)
				return true;
		}
		return false;
	}

	@Override
	public boolean updateUserMemberStar(Long uid, double star) {
		StarUser starUser = getUserStar(uid);
		if (starUser == null) {
			if (createUserStar(uid) > 0) {
				starUser = getUserStar(uid);
			}
		}
		if (starUser != null) {
			starUser.setMember_evaluations(starUser.getMember_evaluations() + 1);
			starUser.setMember_star((starUser.getMember_star() * (WEIGHT) + star) / (WEIGHT + 1));
			if (starUserMapper.updateByPrimaryKeySelective(starUser) > 0)
				return true;
		}
		return false;
	}

	@Override
	public List<StarEvaluations> getStarEvaluationsByValutorAndOrderId(Long valutor, String
			orderId) {
		StarEvaluationsCriteria starEvaluationsCriteria=new StarEvaluationsCriteria();
		starEvaluationsCriteria.or().andValutorEqualTo(valutor).andOrder_idEqualTo(orderId);

		return starEvaluationsMapper.selectByExample(starEvaluationsCriteria);
	}

	@Override
	public List<StarEvaluations> getStarEvaluationByValutorAndValutedAndOrderId(Long valutor, Long
			valuted,
			String orderId) {
		StarEvaluationsCriteria starEvaluationsCriteria=new StarEvaluationsCriteria();
		starEvaluationsCriteria.or().andValutorEqualTo(valutor).andOrder_idEqualTo(orderId).andValutedEqualTo(valuted);

		return starEvaluationsMapper.selectByExample(starEvaluationsCriteria);
	}

}
