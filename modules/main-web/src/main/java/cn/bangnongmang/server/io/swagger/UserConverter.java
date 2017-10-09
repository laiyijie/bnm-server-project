package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.data.combo.domain.*;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.server.io.swagger.util.NumberUtil;
import cn.bangnongmang.service.outerservice.IDCardExplainService;
import cn.bangnongmang.service.service.FriendshipService;
import cn.bangnongmang.service.service.StarService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.server.swagger.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-12.
 */
@Service
public class UserConverter {
    public static final String UNAUTHED_USER_NAME = "未认证用户";

    @Autowired
    private MachineConverter machineConverter;

    @Autowired
    private IDCardExplainService idCardExplainService;

    public FriendRequest convertToFriendRequest(FriendRequestInfoShow requestInfoShow) {
        if (requestInfoShow == null || requestInfoShow.getUserBasicInfoShow() == null)
            return null;
        FriendRequest friendRequest = convertToUserSimple(requestInfoShow.getUserBasicInfoShow(),
                FriendRequest.class);
        friendRequest.setAccepted(requestInfoShow.getFriendshipRequest()
                                                 .getState()
                                                 .equals
                                                         (FriendshipService.STATE_ACCEPTED));
        friendRequest.setPs(requestInfoShow.getFriendshipRequest()
                                           .getPs());
        friendRequest.setRequestTime(requestInfoShow.getFriendshipRequest()
                                                    .getCreate_time() *
                1000);
        friendRequest.setUid(requestInfoShow.getUserBasicInfoShow()
                                            .getAccount()
                                            .getUid());
        return friendRequest;
    }

    public FriendSimple convertToFriendSimple(FriendInfoShow friendInfoShow) {
        if (friendInfoShow == null || friendInfoShow.getUserBasicInfoShow() == null) {
            return null;
        }
        FriendSimple friendSimple = convertToUserSimple(friendInfoShow
                .getUserBasicInfoShow(), FriendSimple.class);
        friendSimple.setSpecail(friendInfoShow.getFriendshipSpecial() != null);
        friendSimple.setLastLocation(convertToUserGeo(friendInfoShow
                .getUserBasicInfoShow()
                .getAccountGeo()));
        friendSimple.setPhone(friendInfoShow.getUserBasicInfoShow()
                                            .getAccount()
                                            .getUsername());
        return friendSimple;
    }

    public FriendDetail convertToFriendDetail(FriendInfoShow friendInfoShow,
                                              FriendDetail.StateEnum state) {
        FriendDetail friendDetail = convertToUserDetail(FriendDetail.class, friendInfoShow
                .getUserBasicInfoShow(), true);
        friendDetail.setState(state);
        friendDetail.setLastLocation(
                convertToUserGeo(friendInfoShow.getUserBasicInfoShow()
                                               .getAccountGeo()));
        friendDetail.setSpecail(friendInfoShow.getFriendshipSpecial() != null);
        friendDetail.setPhone(friendInfoShow.getUserBasicInfoShow()
                                            .getAccount()
                                            .getUsername());
        return friendDetail;
    }


    public <T extends UserDetail> T convertToUserDetail(Class<T> dst, UserBasicInfoShow ubis,

                                                        boolean isFriend) {
        T userDetail = convertToUserSimple(ubis, dst);
        userDetail.setFriend(isFriend);
        if (ubis.getAccountRealNameAuth() != null) {
            IDCardExplainService.IDCardInfo info = idCardExplainService
                    .explainIDCardNumber(ubis.getAccountRealNameAuth()
                                             .getId_card_number());
            if (info == null) {
                info = new IDCardExplainService.IDCardInfo();
            }
            userDetail.setRegion(info.getDistrict());
            userDetail.setAge(info.getAge());
            userDetail.setSex(info.getGender());
        }

        return userDetail;
    }

    public UserGeo convertToUserGeo(AccountGeo accountGeo) {

        if (accountGeo == null) {
            return null;
        }
        UserGeo userGeo = new UserGeo();

        userGeo.setAddress(accountGeo.getAddress());
        userGeo.setCity(accountGeo.getCity());
        userGeo.setDistrict(accountGeo.getDistrict());
        userGeo.setLatitude(accountGeo.getLatitude());
        userGeo.setLongitude(accountGeo.getLongitude());
        userGeo.setProvince(accountGeo.getProvince());
        userGeo.setStreetNumber(accountGeo.getStreet_number());
        userGeo.setStreet(accountGeo.getStreet());
        userGeo.setUpdateTime(NumberUtil.convertToMillisTime(accountGeo.getUpdate_time()));
        return userGeo;
    }


    public <T extends UserSimple> T convertToUserSimple(UserBasicInfoShow ubis,
                                                        Class<T> dst) {
        if (ubis == null) {
            return null;
        }
        try {
            T userSimple = dst.newInstance();

            if (ubis.getStarUser() != null) {
                userSimple.setLeaderStar(ubis.getStarUser()
                                             .getCaptain_star());
                userSimple.setMemberStar(ubis.getStarUser()
                                             .getMember_star());
            } else {
                userSimple.setLeaderStar(StarService.INIT_LEADER_STAR);
                userSimple.setMemberStar(StarService.INIT_MEMBER_STAR);
            }
            userSimple.setName(ubis.getAccountRealNameAuth() == null ? UNAUTHED_USER_NAME : ubis
                    .getAccountRealNameAuth()
                    .getReal_name());
            userSimple.setUid(ubis.getAccount()
                                  .getUid());
            userSimple.setPortraitUrl(ubis.getAccountPortrait() == null ? null : ubis
                    .getAccountPortrait()
                    .getPortrait_url());
            userSimple.setRole(convertToRoleEnum(ubis.getAccount()
                                                     .getLevel(), ubis.getAccount()
                                                                      .getDriver_leader()));
            userSimple.setAuthed(
                    ubis.getAccountRealNameAuth() != null && ubis.getAccountRealNameAuth
                            ()
                                                                 .getState()
                                                                 .equals(UserService.REAL_NAME_AUTH_STATE_PASS));
            return userSimple;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }




    public UserSimple.RoleEnum convertToRoleEnum(Integer level, Integer driverLeaderState) {
        if (level >= UserService.DRIVER && UserService.DRIVER_LEADER_AUTHED.equals
                (driverLeaderState)) {
            return UserSimple.RoleEnum.DRIVER_LEADER;
        }

        if (level >= UserService.DRIVER) {
            return UserSimple.RoleEnum.DRIVER;
        }
        return UserSimple.RoleEnum.FARMER;
    }

    public AccountAuth convertToAccountAuth(AccountRealNameAuth accountRealNameAuth){


        if(accountRealNameAuth==null){
            return null;
        }

        AccountAuth accountAuth=new AccountAuth();

        if(accountRealNameAuth.getState().equals(UserService.REAL_NAME_AUTH_STATE_PASS)){
             accountAuth.setState(AccountAuth.StateEnum.PASS);
        }else if(accountRealNameAuth.getState().equals(UserService.REAL_NAME_AUTH_STATE_WAITTING_AUTH)){
             accountAuth.setState(AccountAuth.StateEnum.WAITTING_AUTH);
        }else{
            accountAuth.setState(AccountAuth.StateEnum.INIT);
        }
        accountAuth.setIdcCardNumber(accountRealNameAuth.getId_card_number());
        accountAuth.setDownSide(accountRealNameAuth.getDown_side());
        accountAuth.setUpSide(accountRealNameAuth.getUp_side());
        accountAuth.setFailedReason(accountRealNameAuth.getFailed_reason());
        accountAuth.setRealName(accountRealNameAuth.getReal_name());

        return accountAuth;
    }
}
