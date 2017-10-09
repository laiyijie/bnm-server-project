package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.InviteFriend;
import cn.bangnongmang.data.domain.InviteFriendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InviteFriendMapper {
    long countByExample(InviteFriendCriteria example);

    int deleteByExample(InviteFriendCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(InviteFriend record);

    int insertSelective(InviteFriend record);

    List<InviteFriend> selectByExample(InviteFriendCriteria example);

    InviteFriend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InviteFriend record, @Param("example") InviteFriendCriteria example);

    int updateByExample(@Param("record") InviteFriend record, @Param("example") InviteFriendCriteria example);

    int updateByPrimaryKeySelective(InviteFriend record);

    int updateByPrimaryKey(InviteFriend record);
}