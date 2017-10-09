package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.SeasonOrders;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeasonOrdersMapper {
    long countByExample(SeasonOrdersCriteria example);

    int deleteByExample(SeasonOrdersCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeasonOrders record);

    int insertSelective(SeasonOrders record);

    List<SeasonOrders> selectByExample(SeasonOrdersCriteria example);

    SeasonOrders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeasonOrders record, @Param("example") SeasonOrdersCriteria example);

    int updateByExample(@Param("record") SeasonOrders record, @Param("example") SeasonOrdersCriteria example);

    int updateByPrimaryKeySelective(SeasonOrders record);

    int updateByPrimaryKey(SeasonOrders record);
}