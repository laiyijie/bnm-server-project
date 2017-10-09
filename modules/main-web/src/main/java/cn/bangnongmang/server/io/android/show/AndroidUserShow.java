package cn.bangnongmang.server.io.android.show;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.TeamInOrder;
import cn.bangnongmang.data.domain.TeamJoinRequest;
import cn.bangnongmang.driverapp.models.FriendDetail;
import cn.bangnongmang.driverapp.models.MyProfile;
import cn.bangnongmang.driverapp.models.MyUserInfo;
import cn.bangnongmang.driverapp.models.UserGeoInfo;
import cn.bangnongmang.driverapp.models.UserInfoDetail;
import cn.bangnongmang.driverapp.models.UserInfoSimple;
import cn.bangnongmang.service.outerservice.IDCardExplainService;
import cn.bangnongmang.service.outerservice.IDCardExplainService.IDCardInfo;
import cn.bangnongmang.service.service.FriendshipService;
import cn.bangnongmang.service.service.StarService;
import cn.bangnongmang.service.service.TeamOrderService;
import cn.bangnongmang.service.service.UserService;

@Service
public class AndroidUserShow {

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private IDCardExplainService idCardExplainService;

    @Autowired
    private TeamOrderService teamOrderService;

    @Autowired
    private AndroidMachineShow androidMachineShow;

    public static final String UNAUTHED_USER_NAME = "未认证用户";

    public UserInfoDetail getUserInfoDetail(Long uid, Long operator) {

        Friendship friendship = friendshipService.getFriendship(uid, operator);

        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(uid);

        if (userBasicInfoShow == null) {
            return null;
        }

        UserInfoDetail userInfoDetail = new UserInfoDetail();

        userInfoDetail.setIsFriend(friendship == null ? false : true);
        userInfoDetail
                .setLevel(UserService.DRIVER_LEADER_AUTHED.equals(userBasicInfoShow.getAccount()
                        .getDriver_leader()) ? 50 : 0);
        userInfoDetail.setMachineInfos(androidMachineShow.getUserMachineList(uid));

        if (userBasicInfoShow.getStarUser() == null) {
            userInfoDetail.setLeaderStar(StarService.INIT_LEADER_STAR);
            userInfoDetail.setMemberStar(StarService.INIT_MEMBER_STAR);
        } else {
            userInfoDetail.setLeaderStar(userBasicInfoShow.getStarUser()
                    .getCaptain_star());
            userInfoDetail.setMemberStar(userBasicInfoShow.getStarUser()
                    .getMember_star());
        }

        if (userBasicInfoShow.getAccountRealNameAuth() != null) {
            IDCardInfo info = idCardExplainService
                    .explainIDCardNumber(userBasicInfoShow.getAccountRealNameAuth()
                            .getId_card_number());
            if (info == null) {
                info = new IDCardInfo();
            }
            userInfoDetail.setDistrict(info.getDistrict());
            userInfoDetail.setName(userBasicInfoShow.getAccountRealNameAuth()
                    .getReal_name());
        } else {
            userInfoDetail.setName(UNAUTHED_USER_NAME);
        }

        userInfoDetail.setPhone(userBasicInfoShow.getAccount()
                .getUsername());
        userInfoDetail.setPortraitUrl(userBasicInfoShow.getAccountPortrait() != null
                ? userBasicInfoShow.getAccountPortrait()
                .getPortrait_url() : "");
        userInfoDetail.setWorkEffeciencies(new ArrayList<>());

        return userInfoDetail;
    }

    public MyProfile getMyProfile(Long uid) {
        UserBasicInfoShow ubis = userBasicInfoShowMapper.selectByUid(uid);
        return convertUserBasiceInfoShowToMyUserInfo(ubis);
    }

    public FriendDetail getFriendDetail(Long uid, Long operator) {

        Friendship friendship = friendshipService.getFriendship(uid, operator);

        if (friendship == null) {
            return null;
        }

        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(uid);

        if (userBasicInfoShow == null) {
            return null;
        }

        FriendDetail friendDetail = new FriendDetail();

        if (userBasicInfoShow.getStarUser() == null) {
            friendDetail.setLeaderStar(StarService.INIT_LEADER_STAR);
            friendDetail.setMemberStar(StarService.INIT_MEMBER_STAR);
        } else {
            friendDetail.setLeaderStar(userBasicInfoShow.getStarUser()
                    .getCaptain_star());
            friendDetail.setMemberStar(userBasicInfoShow.getStarUser()
                    .getMember_star());
        }

        friendDetail.setMachineInfos(androidMachineShow.getUserMachineList(uid));
        if (userBasicInfoShow.getAccountRealNameAuth() != null) {
            IDCardInfo info = idCardExplainService
                    .explainIDCardNumber(userBasicInfoShow.getAccountRealNameAuth()
                            .getId_card_number());
            if (info == null) {
                info = new IDCardInfo();
            }
            friendDetail.setDistrict(info.getDistrict());
            friendDetail.setName(userBasicInfoShow.getAccountRealNameAuth()
                    .getReal_name());
        } else {
            IDCardInfo info = new IDCardInfo();
            friendDetail.setName(UNAUTHED_USER_NAME);
            friendDetail.setDistrict(info.getDistrict());
        }
        friendDetail.setPhone(userBasicInfoShow.getAccount()
                .getUsername());
        friendDetail.setPortraitUrl(userBasicInfoShow.getAccountPortrait() != null
                ? userBasicInfoShow.getAccountPortrait()
                .getPortrait_url() : "");
        friendDetail.setWorkEffeciencies(new ArrayList<>());
        List<TeamJoinRequest> teamJoinRequests = teamOrderService
                .getTeamJoinRequestsByUsername(uid);
        List<TeamInOrder> teamInOrders = teamOrderService.getTeamInOrdersByLeaderUsername(uid);
        teamJoinRequests.removeIf(tjr -> !tjr.getState()
                .equals(TeamOrderService.REQUEST_ACCEPTED));
        if (teamInOrders.isEmpty() && teamJoinRequests.isEmpty()) {
            friendDetail.setState(FriendDetail.IDLE);
        } else {
            // TODO 组队成功以后的状态需要增加，在组队成功之后
            friendDetail.setState(FriendDetail.IN_TEAM);
        }
        friendDetail
                .setLastLocation(convertAccountGeoToUserGeoInfo(userBasicInfoShow.getAccountGeo()));

        friendDetail
                .setIsSpecial(friendshipService.getFriendShipSpecial(operator, uid) != null);

        return friendDetail;
    }

    public UserInfoSimple convertUserBasicInfoShowToUserInfoSimple(UserBasicInfoShow ubis) {

        if (ubis == null) {
            return null;
        }

        UserInfoSimple userInfoSimple = new UserInfoSimple();

        if (ubis.getStarUser() == null) {
            userInfoSimple.setLeaderStar(StarService.INIT_LEADER_STAR);
            userInfoSimple.setMemberStar(StarService.INIT_MEMBER_STAR);
        } else {
            userInfoSimple.setLeaderStar(ubis.getStarUser()
                    .getCaptain_star());
            userInfoSimple.setMemberStar(ubis.getStarUser()
                    .getMember_star());
        }
        userInfoSimple.setLevel(UserService.DRIVER_LEADER_AUTHED.equals(ubis.getAccount()
                .getDriver_leader()) ? 50 : 0);
        userInfoSimple.setName(ubis.getAccountRealNameAuth() != null ? ubis.getAccountRealNameAuth()
                .getReal_name()
                : UNAUTHED_USER_NAME);

        userInfoSimple.setPhone(ubis.getAccount()
                .getUsername());
        if (ubis.getAccountPortrait() != null) {
            userInfoSimple.setPortraitURL(ubis.getAccountPortrait()
                    .getPortrait_url());
        }

        return userInfoSimple;

    }

    public MyProfile convertUserBasiceInfoShowToMyUserInfo(UserBasicInfoShow ubis) {

        if (ubis == null) {
            return null;
        }

        MyProfile myProfile = new MyProfile();

        if (ubis.getStarUser() != null) {
            myProfile.setLeaderStar(ubis.getStarUser()
                    .getCaptain_star());
            myProfile.setMemberStar(ubis.getStarUser()
                    .getMember_star());
        } else {
            myProfile.setMemberStar(StarService.INIT_MEMBER_STAR);
            myProfile.setLeaderStar(StarService.INIT_LEADER_STAR);
        }
        IDCardInfo idCardInfo = null;
        if (ubis.getAccountRealNameAuth() != null) {
            idCardInfo = idCardExplainService.explainIDCardNumber(ubis.getAccountRealNameAuth()
                    .getId_card_number());
            myProfile.setName(ubis.getAccountRealNameAuth()
                    .getReal_name());
        } else {
            idCardInfo = new IDCardInfo();
            myProfile.setName(UNAUTHED_USER_NAME);
        }
        myProfile.setAge(idCardInfo.getAge());
        myProfile.setDistrict(idCardInfo.getDistrict());

        myProfile.setPortalUrl(ubis.getAccountPortrait() != null ? ubis.getAccountPortrait()
                .getPortrait_url() : "");
        myProfile.setSex(idCardInfo.getGender());
        myProfile.setTel(ubis.getAccount()
                .getTel());
        myProfile.setUsername(ubis.getAccount()
                .getUsername());
        return myProfile;
    }

    public UserGeoInfo convertAccountGeoToUserGeoInfo(AccountGeo accountGeo) {

        if (accountGeo == null) {
            return null;
        }
        UserGeoInfo userGeoInfo = new UserGeoInfo();

        userGeoInfo.setAddress(accountGeo.getAddress());
        userGeoInfo.setCity(accountGeo.getCity());
        userGeoInfo.setDistrict(accountGeo.getDistrict());
        userGeoInfo.setLatitude(accountGeo.getLatitude());
        userGeoInfo.setLongitude(accountGeo.getLongitude());
        userGeoInfo.setProvince(accountGeo.getProvince());
        userGeoInfo.setStreetNumber(accountGeo.getStreet_number());
        userGeoInfo.setStreet(accountGeo.getStreet());
        userGeoInfo.setUpdateTime(accountGeo.getUpdate_time() * 1000);

        return userGeoInfo;
    }

    public MyUserInfo getMyUserInfo(String username) {
        UserBasicInfoShow ubis = userBasicInfoShowMapper.selectByUserName(username);
        if (ubis == null) {
            return null;
        }
        return convertUserBasicInfoShowToMyUserInfo(ubis);
    }

    public MyUserInfo convertUserBasicInfoShowToMyUserInfo(UserBasicInfoShow ubis) {

        if (ubis == null) {
            return null;
        }

        MyUserInfo myUserInfo = new MyUserInfo();

        IDCardInfo idCardInfo = null;
        if (ubis.getAccountRealNameAuth() != null) {
            myUserInfo.setName(ubis.getAccountRealNameAuth()
                    .getReal_name());
        } else {
            idCardInfo = new IDCardInfo();
            myUserInfo.setName(UNAUTHED_USER_NAME);
        }
        myUserInfo.setAuthed(ubis.getAccountRealNameAuth() != null
                && ubis.getAccountRealNameAuth()
                .getState()
                .equals(UserService.REAL_NAME_AUTH_STATE_PASS) ? true
                : false);
        myUserInfo.setLevel(UserService.DRIVER_LEADER_AUTHED.equals(ubis.getAccount()
                .getDriver_leader()) ? 50 : 0);
        myUserInfo.setPortraitURL(ubis.getAccountPortrait() != null ? ubis.getAccountPortrait()
                .getPortrait_url() : "");
        myUserInfo.setPhone(ubis.getAccount()
                .getUsername());
        return myUserInfo;

    }

//	public WorkEffeciency convertUserWorkEfficiencyToWorkEffeciency(UserWorkEfficiency userWorkEfficiency) {
//		WorkEffeciency workEffeciency = new WorkEffeciency();
//		if (userWorkEfficiency == null) {
//			return null;
//		}
//		workEffeciency.setCropType();
//		workEffeciency.setDays(days);
//		workEffeciency.setRate(rate);
//		workEffeciency.setWorkingType(workingType);
//		workEffeciency.setWorkingTypeId(workingTypeId);
//		
//		return workEffeciency;
//	}
}
