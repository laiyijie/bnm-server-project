package cn.bangnongmang.admin.business;

/**
 * Created by lucq on 2017/6/27.
 */
public interface TeamBusiness {

	public Boolean addTeamMember(String tel,Long teamId,Boolean isPay);
	public Boolean removeTeamMeber(String tel,Long teamId,Integer punishInsurement, Boolean isPunished);
	public Boolean changeLeader(String tel,Long teamId);


}
