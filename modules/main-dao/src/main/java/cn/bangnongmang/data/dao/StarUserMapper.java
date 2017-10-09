package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.StarUser;
import cn.bangnongmang.data.domain.StarUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StarUserMapper {
    long countByExample(StarUserCriteria example);

    int deleteByExample(StarUserCriteria example);

    int deleteByPrimaryKey(Long uid);

    int insert(StarUser record);

    int insertSelective(StarUser record);

    List<StarUser> selectByExample(StarUserCriteria example);

    StarUser selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StarUser record, @Param("example") StarUserCriteria example);

    int updateByExample(@Param("record") StarUser record, @Param("example") StarUserCriteria example);

    int updateByPrimaryKeySelective(StarUser record);

    int updateByPrimaryKey(StarUser record);
}