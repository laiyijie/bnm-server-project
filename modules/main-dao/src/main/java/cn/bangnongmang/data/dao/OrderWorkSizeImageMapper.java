package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderWorkSizeImage;
import cn.bangnongmang.data.domain.OrderWorkSizeImageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderWorkSizeImageMapper {
    long countByExample(OrderWorkSizeImageCriteria example);

    int deleteByExample(OrderWorkSizeImageCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderWorkSizeImage record);

    int insertSelective(OrderWorkSizeImage record);

    List<OrderWorkSizeImage> selectByExample(OrderWorkSizeImageCriteria example);

    OrderWorkSizeImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderWorkSizeImage record, @Param("example") OrderWorkSizeImageCriteria example);

    int updateByExample(@Param("record") OrderWorkSizeImage record, @Param("example") OrderWorkSizeImageCriteria example);

    int updateByPrimaryKeySelective(OrderWorkSizeImage record);

    int updateByPrimaryKey(OrderWorkSizeImage record);
}