package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipMapper {
    long countByExample(FriendshipCriteria example);

    int deleteByExample(FriendshipCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Friendship record);

    int insertSelective(Friendship record);

    List<Friendship> selectByExample(FriendshipCriteria example);

    Friendship selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Friendship record, @Param("example") FriendshipCriteria example);

    int updateByExample(@Param("record") Friendship record, @Param("example") FriendshipCriteria example);

    int updateByPrimaryKeySelective(Friendship record);

    int updateByPrimaryKey(Friendship record);
}