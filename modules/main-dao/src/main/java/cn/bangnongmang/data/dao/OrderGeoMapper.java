package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderGeo;
import cn.bangnongmang.data.domain.OrderGeoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderGeoMapper {
    long countByExample(OrderGeoCriteria example);

    int deleteByExample(OrderGeoCriteria example);

    int deleteByPrimaryKey(String order_id);

    int insert(OrderGeo record);

    int insertSelective(OrderGeo record);

    List<OrderGeo> selectByExample(OrderGeoCriteria example);

    OrderGeo selectByPrimaryKey(String order_id);

    int updateByExampleSelective(@Param("record") OrderGeo record, @Param("example") OrderGeoCriteria example);

    int updateByExample(@Param("record") OrderGeo record, @Param("example") OrderGeoCriteria example);

    int updateByPrimaryKeySelective(OrderGeo record);

    int updateByPrimaryKey(OrderGeo record);
}