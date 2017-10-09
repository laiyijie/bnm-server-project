package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.data.domain.AdminAccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminAccountMapper {
    long countByExample(AdminAccountCriteria example);

    int deleteByExample(AdminAccountCriteria example);

    int deleteByPrimaryKey(String username);

    int insert(AdminAccount record);

    int insertSelective(AdminAccount record);

    List<AdminAccount> selectByExample(AdminAccountCriteria example);

    AdminAccount selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") AdminAccount record, @Param("example") AdminAccountCriteria example);

    int updateByExample(@Param("record") AdminAccount record, @Param("example") AdminAccountCriteria example);

    int updateByPrimaryKeySelective(AdminAccount record);

    int updateByPrimaryKey(AdminAccount record);
}