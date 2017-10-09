package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.FollowOrderCriteria;
import cn.bangnongmang.data.domain.FollowOrderKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FollowOrderMapper {
    long countByExample(FollowOrderCriteria example);

    int deleteByExample(FollowOrderCriteria example);

    int deleteByPrimaryKey(FollowOrderKey key);

    int insert(FollowOrderKey record);

    int insertSelective(FollowOrderKey record);

    List<FollowOrderKey> selectByExample(FollowOrderCriteria example);

    int updateByExampleSelective(@Param("record") FollowOrderKey record, @Param("example") FollowOrderCriteria example);

    int updateByExample(@Param("record") FollowOrderKey record, @Param("example") FollowOrderCriteria example);
}