package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamInOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamInOrderMapper {
    long countByExample(TeamInOrderCriteria example);

    int deleteByExample(TeamInOrderCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TeamInOrder record);

    int insertSelective(TeamInOrder record);

    List<TeamInOrder> selectByExample(TeamInOrderCriteria example);

    TeamInOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TeamInOrder record, @Param("example") TeamInOrderCriteria example);

    int updateByExample(@Param("record") TeamInOrder record, @Param("example") TeamInOrderCriteria example);

    int updateByPrimaryKeySelective(TeamInOrder record);

    int updateByPrimaryKey(TeamInOrder record);
}