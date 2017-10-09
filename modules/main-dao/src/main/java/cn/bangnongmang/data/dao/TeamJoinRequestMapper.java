package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.data.domain.TeamJoinRequestCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamJoinRequestMapper {
    long countByExample(TeamJoinRequestCriteria example);

    int deleteByExample(TeamJoinRequestCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TeamJoinRequest record);

    int insertSelective(TeamJoinRequest record);

    List<TeamJoinRequest> selectByExample(TeamJoinRequestCriteria example);

    TeamJoinRequest selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TeamJoinRequest record, @Param("example") TeamJoinRequestCriteria example);

    int updateByExample(@Param("record") TeamJoinRequest record, @Param("example") TeamJoinRequestCriteria example);

    int updateByPrimaryKeySelective(TeamJoinRequest record);

    int updateByPrimaryKey(TeamJoinRequest record);
}