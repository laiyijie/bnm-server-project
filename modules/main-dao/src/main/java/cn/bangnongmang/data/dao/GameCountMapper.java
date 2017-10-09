package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.GameCount;
import cn.bangnongmang.data.domain.GameCountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameCountMapper {
    long countByExample(GameCountCriteria example);

    int deleteByExample(GameCountCriteria example);

    int deleteByPrimaryKey(String openid);

    int insert(GameCount record);

    int insertSelective(GameCount record);

    List<GameCount> selectByExample(GameCountCriteria example);

    GameCount selectByPrimaryKey(String openid);

    int updateByExampleSelective(@Param("record") GameCount record, @Param("example") GameCountCriteria example);

    int updateByExample(@Param("record") GameCount record, @Param("example") GameCountCriteria example);

    int updateByPrimaryKeySelective(GameCount record);

    int updateByPrimaryKey(GameCount record);
}