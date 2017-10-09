package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionDetailMapper {
    long countByExample(OptionDetailCriteria example);

    int deleteByExample(OptionDetailCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OptionDetail record);

    int insertSelective(OptionDetail record);

    List<OptionDetail> selectByExample(OptionDetailCriteria example);

    OptionDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OptionDetail record, @Param("example") OptionDetailCriteria example);

    int updateByExample(@Param("record") OptionDetail record, @Param("example") OptionDetailCriteria example);

    int updateByPrimaryKeySelective(OptionDetail record);

    int updateByPrimaryKey(OptionDetail record);
}