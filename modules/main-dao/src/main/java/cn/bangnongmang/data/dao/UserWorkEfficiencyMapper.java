package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserWorkEfficiency;
import cn.bangnongmang.data.domain.UserWorkEfficiencyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWorkEfficiencyMapper {
    long countByExample(UserWorkEfficiencyCriteria example);

    int deleteByExample(UserWorkEfficiencyCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserWorkEfficiency record);

    int insertSelective(UserWorkEfficiency record);

    List<UserWorkEfficiency> selectByExample(UserWorkEfficiencyCriteria example);

    UserWorkEfficiency selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserWorkEfficiency record, @Param("example") UserWorkEfficiencyCriteria example);

    int updateByExample(@Param("record") UserWorkEfficiency record, @Param("example") UserWorkEfficiencyCriteria example);

    int updateByPrimaryKeySelective(UserWorkEfficiency record);

    int updateByPrimaryKey(UserWorkEfficiency record);
}