package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.FriendshipSpecial;
import cn.bangnongmang.data.domain.FriendshipSpecialCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendshipSpecialMapper {
    long countByExample(FriendshipSpecialCriteria example);

    int deleteByExample(FriendshipSpecialCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(FriendshipSpecial record);

    int insertSelective(FriendshipSpecial record);

    List<FriendshipSpecial> selectByExample(FriendshipSpecialCriteria example);

    FriendshipSpecial selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FriendshipSpecial record, @Param("example") FriendshipSpecialCriteria example);

    int updateByExample(@Param("record") FriendshipSpecial record, @Param("example") FriendshipSpecialCriteria example);

    int updateByPrimaryKeySelective(FriendshipSpecial record);

    int updateByPrimaryKey(FriendshipSpecial record);
}