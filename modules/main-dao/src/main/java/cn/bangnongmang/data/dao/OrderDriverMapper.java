package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderDriver;
import cn.bangnongmang.data.domain.OrderDriverCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDriverMapper {
    long countByExample(OrderDriverCriteria example);

    int deleteByExample(OrderDriverCriteria example);

    int deleteByPrimaryKey(String order_id);

    int insert(OrderDriver record);

    int insertSelective(OrderDriver record);

    List<OrderDriver> selectByExample(OrderDriverCriteria example);

    OrderDriver selectByPrimaryKey(String order_id);

    int updateByExampleSelective(@Param("record") OrderDriver record, @Param("example") OrderDriverCriteria example);

    int updateByExample(@Param("record") OrderDriver record, @Param("example") OrderDriverCriteria example);

    int updateByPrimaryKeySelective(OrderDriver record);

    int updateByPrimaryKey(OrderDriver record);
}