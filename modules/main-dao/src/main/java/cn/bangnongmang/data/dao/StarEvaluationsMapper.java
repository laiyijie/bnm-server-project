package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.StarEvaluations;
import cn.bangnongmang.data.domain.StarEvaluationsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StarEvaluationsMapper {
    long countByExample(StarEvaluationsCriteria example);

    int deleteByExample(StarEvaluationsCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(StarEvaluations record);

    int insertSelective(StarEvaluations record);

    List<StarEvaluations> selectByExample(StarEvaluationsCriteria example);

    StarEvaluations selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StarEvaluations record, @Param("example") StarEvaluationsCriteria example);

    int updateByExample(@Param("record") StarEvaluations record, @Param("example") StarEvaluationsCriteria example);

    int updateByPrimaryKeySelective(StarEvaluations record);

    int updateByPrimaryKey(StarEvaluations record);
}