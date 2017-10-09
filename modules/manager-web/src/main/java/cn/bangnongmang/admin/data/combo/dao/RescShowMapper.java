package cn.bangnongmang.admin.data.combo.dao;

import cn.bangnongmang.admin.data.combo.domain.RescAndMethod;
import cn.bangnongmang.admin.data.domain.Resc;

import java.util.List;

/**
 * Created by admin on 2017-05-10.
 */
public interface RescShowMapper {
    List<Resc> selectRescByUserName(String username);
    List<RescAndMethod> selectRescAndMethodByUsername(String username);
}
