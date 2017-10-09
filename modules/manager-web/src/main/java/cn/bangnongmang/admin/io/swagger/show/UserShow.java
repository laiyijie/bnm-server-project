package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.swagger.model.RealNameAuthDetail;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.admin.swagger.model.UserDetail;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.bangnongmang.admin.io.swagger.converter.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wenxx on 2017/5/22.
 */
@Service
public class UserShow {
    @Autowired
    private UserConverter realNameAuthsConvert;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    public List<UserBasic> getRealNameAuthsByType(String type) {
        Integer correspondingtype;
        if (type.equals("WAITING_AUTH")) {
            correspondingtype = UserService.REAL_NAME_AUTH_STATE_WAITTING_AUTH;
        } else if (type.equals("AUTHED")) {
            correspondingtype = UserService.REAL_NAME_AUTH_STATE_PASS;
        } else if (type.equals("DENIED")){
            correspondingtype = UserService.REAL_NAME_AUTH_STATE_INIT;
        }else {
            correspondingtype=null;
        }
        return userBasicInfoShowMapper.selectRealNameAuthListByType(correspondingtype)
                                      .stream()
                                      .map(userBasicInfoShow -> realNameAuthsConvert.convertToUserBasic
                                              (userBasicInfoShow, UserBasic.class))
                                      .collect(Collectors.toList());
    }

    public RealNameAuthDetail getRealNameAuthsByUid(long uid) {
        return realNameAuthsConvert.convetToRealNameAuthDetail(userBasicInfoShowMapper.selectByUid(uid), RealNameAuthDetail.class);
    }

    public UserDetail getUserDetail(Long uid) {
        return realNameAuthsConvert.convertToUserDetail(userBasicInfoShowMapper.selectByUid(uid), UserDetail.class);
    }

    public List<UserBasic> getDriverList() {
        return userBasicInfoShowMapper.selectRealNameAuthListByLevel(20, 100)
                                      .stream()
                                      .map(ubis -> realNameAuthsConvert.convertToUserBasic(ubis,
                                              UserBasic.class))
                                      .collect(Collectors.toList());
    }

    public List<UserBasic> getFarmerList() {
        return userBasicInfoShowMapper.selectRealNameAuthListByLevel(10, 19)
                                      .stream()
                                      .map(ubis -> realNameAuthsConvert.convertToUserBasic(ubis,
                                              UserBasic.class))
                                      .collect(Collectors.toList());
    }


}
