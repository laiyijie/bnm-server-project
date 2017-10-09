package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderInvite;
import cn.bangnongmang.data.domain.OrderInviteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderInviteMapper {
    long countByExample(OrderInviteCriteria example);

    int deleteByExample(OrderInviteCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderInvite record);

    int insertSelective(OrderInvite record);

    List<OrderInvite> selectByExample(OrderInviteCriteria example);

    OrderInvite selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderInvite record, @Param("example") OrderInviteCriteria example);

    int updateByExample(@Param("record") OrderInvite record, @Param("example") OrderInviteCriteria example);

    int updateByPrimaryKeySelective(OrderInvite record);

    int updateByPrimaryKey(OrderInvite record);
}