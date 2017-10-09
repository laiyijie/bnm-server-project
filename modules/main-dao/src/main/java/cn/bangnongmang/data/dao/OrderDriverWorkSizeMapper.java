package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderDriverWorkSize;
import cn.bangnongmang.data.domain.OrderDriverWorkSizeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDriverWorkSizeMapper {
    long countByExample(OrderDriverWorkSizeCriteria example);

    int deleteByExample(OrderDriverWorkSizeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDriverWorkSize record);

    int insertSelective(OrderDriverWorkSize record);

    List<OrderDriverWorkSize> selectByExample(OrderDriverWorkSizeCriteria example);

    OrderDriverWorkSize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDriverWorkSize record, @Param("example") OrderDriverWorkSizeCriteria example);

    int updateByExample(@Param("record") OrderDriverWorkSize record, @Param("example") OrderDriverWorkSizeCriteria example);

    int updateByPrimaryKeySelective(OrderDriverWorkSize record);

    int updateByPrimaryKey(OrderDriverWorkSize record);
}