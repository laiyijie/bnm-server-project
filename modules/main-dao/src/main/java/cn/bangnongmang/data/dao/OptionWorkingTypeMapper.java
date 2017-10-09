package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OptionWorkingTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionWorkingTypeMapper {
    long countByExample(OptionWorkingTypeCriteria example);

    int deleteByExample(OptionWorkingTypeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OptionWorkingType record);

    int insertSelective(OptionWorkingType record);

    List<OptionWorkingType> selectByExample(OptionWorkingTypeCriteria example);

    OptionWorkingType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OptionWorkingType record, @Param("example") OptionWorkingTypeCriteria example);

    int updateByExample(@Param("record") OptionWorkingType record, @Param("example") OptionWorkingTypeCriteria example);

    int updateByPrimaryKeySelective(OptionWorkingType record);

    int updateByPrimaryKey(OptionWorkingType record);
}