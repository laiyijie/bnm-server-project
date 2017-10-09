package cn.bangnongmang.admin.io.swagger.converter;

import cn.bangnongmang.admin.swagger.model.RealNameAuthDetail;
import cn.bangnongmang.admin.swagger.model.UserDetail;
import cn.bangnongmang.admin.swagger.model.UserGeo;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.service.outerservice.IDCardExplainService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Created by wenxx on 2017/5/22.
 */
@Service
public class UserConverter {
    @Autowired
    private IDCardExplainService idCardExplainService;
    private final double STAR_DEFAULT = 4.0;

    public <T extends UserBasic> T convertToUserBasic(UserBasicInfoShow userBasicInfoShow, Class<T> dst) {
        if (userBasicInfoShow == null) return null;
        try {
            T newUserBasic = dst.newInstance();
            AccountRealNameAuth accountRealNameAuth=userBasicInfoShow.getAccountRealNameAuth();
            if(accountRealNameAuth==null){
                newUserBasic.setName("未知");
            }else{
                newUserBasic.setName(userBasicInfoShow.getAccountRealNameAuth()
                                                      .getReal_name());
            }

            newUserBasic.setCreateTime(userBasicInfoShow.getAccount()
                                                        .getCreate_time());
            String phoneNum = userBasicInfoShow.getAccount()
                                               .getTel();
            if (UserConverter.checkPhoneNum(phoneNum)) {
                newUserBasic.setPhoneStar(phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11));
            } else {
                newUserBasic.setPhoneStar(phoneNum);
            }
            newUserBasic.setUid(userBasicInfoShow.getAccount()
                                                 .getUid());
            if (userBasicInfoShow.getStarUser()!=null){
                newUserBasic.setLeaderStar(userBasicInfoShow.getStarUser()
                                                            .getCaptain_star());

                newUserBasic.setMemberStar(userBasicInfoShow.getStarUser()
                                                            .getMember_star());
            }else{
                newUserBasic.setLeaderStar(STAR_DEFAULT);
                newUserBasic.setMemberStar(STAR_DEFAULT);
            }
            if (userBasicInfoShow.getAccountPortrait() != null){
                newUserBasic.setPortraitUrl(userBasicInfoShow.getAccountPortrait()
                                                             .getPortrait_url());
            }

            UserGeo newUseGeo = new UserGeo();
            if (userBasicInfoShow.getAccountGeo() != null) {
                newUseGeo.setAddress(userBasicInfoShow.getAccountGeo()
                                                      .getAddress());
                newUseGeo.setCity(userBasicInfoShow.getAccountGeo()
                                                   .getCity());
                newUseGeo.setDistrict(userBasicInfoShow.getAccountGeo()
                                                       .getDistrict());
                newUseGeo.setLatitude(userBasicInfoShow.getAccountGeo()
                                                       .getLatitude());
                newUseGeo.setLongitude(userBasicInfoShow.getAccountGeo()
                                                        .getLongitude());
                newUseGeo.setProvince(userBasicInfoShow.getAccountGeo()
                                                       .getProvince());
                newUseGeo.setStreet(userBasicInfoShow.getAccountGeo()
                                                     .getStreet());
                newUseGeo.setStreetNumber(userBasicInfoShow.getAccountGeo()
                                                           .getStreet_number());
                newUseGeo.setUid(userBasicInfoShow.getAccountGeo()
                                                  .getUid());
                newUseGeo.setUpdateTime(userBasicInfoShow.getAccountGeo()
                                                         .getUpdate_time());
            }
            newUserBasic.setGeoInfo(newUseGeo);

            if(accountRealNameAuth!=null){
                Integer userAuthState=accountRealNameAuth.getState();
                if (userAuthState != null) {
                    if (userAuthState.equals(UserService.REAL_NAME_AUTH_STATE_WAITTING_AUTH)) {
                        newUserBasic.setAuthed(UserBasic.AuthedEnum.fromValue("WAITING_AUTH"));
                    } else if (userAuthState.equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
                        newUserBasic.setAuthed(UserBasic.AuthedEnum.fromValue("AUTHED"));
                    } else if (userAuthState.equals(UserService.REAL_NAME_AUTH_STATE_INIT)) {
                        newUserBasic.setAuthed(UserBasic.AuthedEnum.fromValue("DENIED"));
                    }
                }
            }
            Integer userType = userBasicInfoShow.getAccount()
                                                .getLevel();
            if (userType != null) {
                if (userType < UserService.DRIVER) {
                    newUserBasic.setRole(UserBasic.RoleEnum.fromValue("FARMER"));
                    newUserBasic.setLeader(false);
                } else {
                    if (userType.equals(UserService.DRIVER_LEADER_AUTHED)) {
                        newUserBasic.setRole(UserBasic.RoleEnum.fromValue("DRIVER_LEADER"));
                        newUserBasic.setLeader(true);
                    } else {
                        newUserBasic.setRole(UserBasic.RoleEnum.fromValue("DRIVER"));
                        newUserBasic.setLeader(false);
                    }
                }
            }
            return newUserBasic;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends UserDetail> T convertToUserDetail(UserBasicInfoShow ubis, Class<T> dst) {
        T userDetail = convertToUserBasic(ubis, dst);
        userDetail.setPhone(ubis.getAccount()
                                .getUsername());
        if (ubis.getAccountRealNameAuth() != null) {
            IDCardExplainService.IDCardInfo idCardInfo = idCardExplainService.explainIDCardNumber(ubis.getAccountRealNameAuth()
                                                                                                      .getId_card_number());
            if (idCardInfo != null) {
                userDetail.setAge(idCardInfo.getAge());
                userDetail.setRegion(idCardInfo.getDistrict());
                userDetail.setSex(idCardInfo.getGender());
            }
        } else {
            userDetail.setAge("未知");
            userDetail.setRegion("未知");
            userDetail.setSex("未知");
        }
        return userDetail;
    }

    public <T extends RealNameAuthDetail> T convetToRealNameAuthDetail(UserBasicInfoShow userBasicInfoShow, Class<T> dst) {
        T newRealNameAuthDetail = convertToUserBasic(userBasicInfoShow, dst);
        newRealNameAuthDetail.setPhone(userBasicInfoShow.getAccount()
                                                        .getTel());
        newRealNameAuthDetail.setUpSideImage(userBasicInfoShow.getAccountRealNameAuth()
                                                              .getUp_side());
        newRealNameAuthDetail.setDownSideImage(userBasicInfoShow.getAccountRealNameAuth()
                                                                .getDown_side());
        newRealNameAuthDetail.setIdCardNum(userBasicInfoShow.getAccountRealNameAuth()
                                                            .getId_card_number());
        newRealNameAuthDetail.setUpdateTime(userBasicInfoShow.getAccountRealNameAuth()
                                                             .getUpdate_time());
        newRealNameAuthDetail.setName(userBasicInfoShow.getAccountRealNameAuth()
                                                       .getReal_name());
        IDCardExplainService.IDCardInfo idCardInfo = idCardExplainService.explainIDCardNumber(userBasicInfoShow.getAccountRealNameAuth()
                                                                                                               .getId_card_number());
        if (idCardInfo != null) {
            newRealNameAuthDetail.setAge(idCardInfo.getAge());
            newRealNameAuthDetail.setRegion(idCardInfo.getDistrict());
            newRealNameAuthDetail.setSex(idCardInfo.getGender());
        } else {
            newRealNameAuthDetail.setAge("未知");
            newRealNameAuthDetail.setRegion("未知");
            newRealNameAuthDetail.setSex("未知");
        }
        return newRealNameAuthDetail;

    }


    public static boolean checkPhoneNum(String phoneNum) {
        Pattern pattern = Pattern.compile("^[1][0-9]{10}$");
        return pattern.matcher(phoneNum)
                      .matches();
    }
}
