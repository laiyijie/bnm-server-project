package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.Resc;
import cn.bangnongmang.admin.data.domain.RescCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RescMapper {
    long countByExample(RescCriteria example);

    int deleteByExample(RescCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Resc record);

    int insertSelective(Resc record);

    List<Resc> selectByExample(RescCriteria example);

    Resc selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Resc record, @Param("example") RescCriteria example);

    int updateByExample(@Param("record") Resc record, @Param("example") RescCriteria example);

    int updateByPrimaryKeySelective(Resc record);

    int updateByPrimaryKey(Resc record);
}