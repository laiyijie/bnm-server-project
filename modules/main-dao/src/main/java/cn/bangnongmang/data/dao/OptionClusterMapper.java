package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionClusterCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionClusterMapper {
    long countByExample(OptionClusterCriteria example);

    int deleteByExample(OptionClusterCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OptionCluster record);

    int insertSelective(OptionCluster record);

    List<OptionCluster> selectByExample(OptionClusterCriteria example);

    OptionCluster selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OptionCluster record, @Param("example") OptionClusterCriteria example);

    int updateByExample(@Param("record") OptionCluster record, @Param("example") OptionClusterCriteria example);

    int updateByPrimaryKeySelective(OptionCluster record);

    int updateByPrimaryKey(OptionCluster record);
}