package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.data.domain.FriendshipRequestCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipRequestMapper {
    long countByExample(FriendshipRequestCriteria example);

    int deleteByExample(FriendshipRequestCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(FriendshipRequest record);

    int insertSelective(FriendshipRequest record);

    List<FriendshipRequest> selectByExample(FriendshipRequestCriteria example);

    FriendshipRequest selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FriendshipRequest record, @Param("example") FriendshipRequestCriteria example);

    int updateByExample(@Param("record") FriendshipRequest record, @Param("example") FriendshipRequestCriteria example);

    int updateByPrimaryKeySelective(FriendshipRequest record);

    int updateByPrimaryKey(FriendshipRequest record);
}