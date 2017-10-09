package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.server.business.common.*;
import cn.bangnongmang.server.business.order.OrderBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.base.BnmSwaggerControllerUtil;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.swagger.UserConverter;
import cn.bangnongmang.server.io.swagger.show.*;
import cn.bangnongmang.server.swagger.api.UserApi;
import cn.bangnongmang.server.swagger.model.*;
import cn.bangnongmang.service.service.StarService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017-04-12.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class UserController implements UserApi {
    @Autowired
    private UserShow userShow;
    @Autowired
    private MachineShow machineShow;
    @Autowired
    private OrderShow orderShow;
    @Autowired
    private TeamShow teamShow;
    @Autowired
    private WalletShow walletShow;

    @Autowired
    private TeamOrderBusiness teamOrderBusiness;
    @Autowired
    private BankCardBusiness bankCardBusiness;
    @Autowired
    private DriverAccountBusiness driverAccountBusiness;
    @Autowired
    private OrderBusiness orderBusiness;
    @Autowired
    private AccountBusiness accountBusiness;
    @Autowired
    private PaymentBusiness paymentBusiness;

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private StarBusiness starBusiness;

    @Autowired
    private StarShow starShow;

    @Autowired
    private MachineBussiness machineBussiness;

    @Value("${machine.add.url}")
    private String ADD_CAR_URL;

    @Override
    public ResponseEntity<AliCredentials> userAliOssAuthCridentialGet(HttpServletRequest request,
                                                                      HttpServletResponse response)
            throws Exception {

        String jsonString = JSON.toJSONString(accountBusiness.getAliOssCredentials
                (BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), "android"));
        return new ResponseEntity<AliCredentials>(JSON.parseObject(jsonString, AliCredentials
                .class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> userFavoriteOrdersGet(HttpServletRequest request,
                                                             HttpServletResponse response)
            throws Exception {
        return new ResponseEntity<List<Order>>(orderShow.getUserFavoriteOrders
                (BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)
                ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userFavoriteOrdersOrderIdDelete(
            @ApiParam(value = "需要关注订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        teamOrderBusiness.unfollowOrder(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), orderId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userFavoriteOrdersOrderIdGet(
            @ApiParam(value = "关注的订单ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (teamOrderBusiness.isUserFollowOrder(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), orderId)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> userFavoriteOrdersOrderIdPost(
            @ApiParam(value = "需要关注订单的ID", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        teamOrderBusiness.followOrder(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), orderId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDetail> userGet(HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        return new ResponseEntity<UserDetail>(
                userShow.getMyUserInfo(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AliOssBaseUrl> userIdCardImageBaseUrlGet(HttpServletRequest request,
                                                                   HttpServletResponse response)
            throws Exception {
        AliOssBaseUrl aliOssBaseUrl = new AliOssBaseUrl();
        aliOssBaseUrl.setUrl(accountBusiness.getImagePutUrl(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)));
        return new ResponseEntity<AliOssBaseUrl>(aliOssBaseUrl, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserMachine>> userMachinesGet(HttpServletRequest request,
                                                             HttpServletResponse response)
            throws Exception {
        return new ResponseEntity<List<UserMachine>>(machineShow.getUserMachines
                (BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userMachinesMachineIDDelete(
            @ApiParam(value = "", required = true) @PathVariable("machineID") Long machineID,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        driverAccountBusiness.removeUserMachine(machineID, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserMachineDetail> userMachinesMachineIDGet(
            @ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserMachineDetail userMachineDetail=machineShow.getUserMachineById(machineID);
        if(userMachineDetail!=null){
            return new ResponseEntity<UserMachineDetail>(userMachineDetail,HttpStatus.OK);
        }

        return new ResponseEntity<UserMachineDetail>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> userMachinesMachineIDPost(
            @ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID,
            @ApiParam(value = "车辆的详细信息" ,required=true )  @Valid @RequestBody UserMachineDetail userMachineDetail,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        userMachineDetail.setUid(BnmSwaggerControllerUtil.getSessionForLong(request,ServerSessionAttr.SESSION_UID));
        machineBussiness.modifyMachine(userMachineDetail);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userMachinesMachineIDPut(
            @ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID,
            @ApiParam(value = "车辆的详细信息" ,required=true )  @Valid @RequestBody UserMachineDetail userMachineDetial,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        userMachineDetial.setUid(BnmSwaggerControllerUtil.getSessionForLong(request,ServerSessionAttr.SESSION_UID));
        machineBussiness.modifyMachine(userMachineDetial);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userMachinesPost(
            @Valid @RequestBody UserMachineDetail userMachineDetial, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        userMachineDetial.setUid(BnmSwaggerControllerUtil.getSessionForLong(request,ServerSessionAttr.SESSION_UID));
        machineBussiness.saveMachine(userMachineDetial);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<StringResponse> userMachinesWebViewUrlGet(HttpServletRequest request,
                                                                    HttpServletResponse response)
            throws Exception {
        StringResponse stringResponse = new StringResponse();
        stringResponse.setUrl(ADD_CAR_URL);
        return new ResponseEntity<StringResponse>(stringResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> userOrdersGet(HttpServletRequest request,
                                                     HttpServletResponse response)
            throws Exception {
        return new ResponseEntity<List<Order>>(
                orderShow.getMyOrders(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userOrdersOrderIdGet(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (orderShow.isMyOrder(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), orderId)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<StarInfo>> userOrdersOrderIdStarsGet(@ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
                                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        return ResponseEntity.ok(
                starShow.getStarInfoByOrderIdAndValutor(orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr
                        .SESSION_UID)));

    }

    @Override
    public ResponseEntity<Void> userOrdersOrderIdStarsPost(@ApiParam(value = "", required = true) @Valid @RequestBody StarInfo starInfo,
                                                           @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
                                                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long me = BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID);
        if (StarInfo.TypeEnum.LEADER.equals(starInfo.getType())) {
            starBusiness.star(me, starInfo.getValuted(), starInfo.getStar(), orderId, StarService.STAR_LEADER);
        } else if (StarInfo.TypeEnum.MEMBER.equals(starInfo.getType())) {
            starBusiness.star(me, starInfo.getValuted(), starInfo.getStar(), orderId, StarService.STAR_MEMBER);
        } else {
            throw new BusinessException("评价类型不对");

        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<SubOrder> userOrdersOrderIdSubOrderGet(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubOrder subOrder = orderShow.getMySubOrder(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), orderId);
        if (subOrder == null) return new ResponseEntity<SubOrder>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<SubOrder>(subOrder,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userOrdersOrderIdSubOrderStatusStartWorkPut(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        orderBusiness.startWork(orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID),
                machineShow.getUserAvailableMachineWidth
                        (orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userOrdersOrderIdSubOrderStatusStopWorkPut(
            @ApiParam(value = "", required = true) @PathVariable("orderId") String orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        orderBusiness.stopWork(orderId, BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AliOssBaseUrl> userPortraitBaseUrlGet(HttpServletRequest request,
                                                                HttpServletResponse response)
            throws Exception {
        AliOssBaseUrl aliOssBaseUrl = new AliOssBaseUrl();
        aliOssBaseUrl.setUrl(accountBusiness.getPortaitPutUrl(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)));
        return new ResponseEntity<AliOssBaseUrl>(aliOssBaseUrl, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userPortraitPost(
            @ApiParam(value = "上传到阿里云的文件名", required = true) @RequestParam(value = "portraitFileName", required = true) String portraitFileName,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        accountBusiness.setPortraitUrl(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID),
                portraitFileName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountAuth> userRealNameAuthInfoGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        AccountAuth accountAuth = userShow.getAccountAuth(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        if (accountAuth == null) {
            return new ResponseEntity<AccountAuth>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountAuth>(accountAuth, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userRealNameAuthInfoPost(
            @ApiParam(value = "真实姓名", required = true) @RequestParam(value = "realName", required = true) String realName,
            @ApiParam(value = "身份证号", required = true) @RequestParam(value = "idNumber", required = true) String idNumber,
            @ApiParam(value = "身份证正面URL", required = true) @RequestParam(value = "upSideUrl", required = true) String upSideUrl,
            @ApiParam(value = "身份证反面URL", required = true) @RequestParam(value = "downSideUrl", required = true) String downSideUrl,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean flag = accountBusiness.realNameCertificate(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID),
                realName, idNumber, upSideUrl,
                downSideUrl);
        if (flag) return new ResponseEntity<Void>(HttpStatus.OK);
        throw new BusinessException("提交认证失败");
    }

    @Override
    public ResponseEntity<List<Order>> userStarsStatusUnstarOrdersGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(starShow.getUnStaredOrder(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)));
    }

    @Override
    public ResponseEntity<List<Team>> userTeamsGet(HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<Team>>(teamShow.getMyTeams(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userUserGeoPut(
            @ApiParam(value = "", required = true) @RequestBody UserGeo userGeo,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Account account = accountBusiness
                .getUserInfo(BnmSwaggerControllerUtil.getSessionForLong(request,
                        ServerSessionAttr.SESSION_UID));
        AccountGeo accountGeo = new AccountGeo();

        accountGeo.setAddress(userGeo.getAddress());
        accountGeo.setCity(userGeo.getCity());
        accountGeo.setDistrict(userGeo.getDistrict());
        accountGeo.setLatitude(userGeo.getLatitude());
        accountGeo.setLongitude(userGeo.getLongitude());
        accountGeo.setProvince(userGeo.getProvince());
        accountGeo.setStreet(userGeo.getStreet());
        accountGeo.setStreet_number(userGeo.getStreetNumber());
        accountGeo.setUid(account.getUid());
        if (accountBusiness.updateAccountGeoInfo(accountGeo)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("提交失败");
    }

    @Override
    public ResponseEntity<Void> userWalletBankCardsBankCardIdDelete(
            @ApiParam(value = "银行卡的ID，唯一标识", required = true) @PathVariable("bankCardId") Long bankCardId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        bankCardBusiness.removeCard(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), bankCardId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> userWalletBankCardsBankCardNumWithDrawsPost(
            @ApiParam(value = "开户银行", required = true) @RequestParam(value = "openBank", required = true) String openBank,
            @ApiParam(value = "充值的金额，单位为分", required = true) @RequestParam(value = "money", required = true) Integer money,
            @ApiParam(value = "卡号", required = true) @PathVariable("bankCardNum") String bankCardNum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (paymentBusiness.withdrawByUnionPay(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID),
                money, bankCardNum,
                openBank) != null) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BusinessException("提现失败");
    }

    @Override
    public ResponseEntity<List<BankCard>> userWalletBankCardsGet(HttpServletRequest request,
                                                                 HttpServletResponse response)
            throws Exception {

        return new ResponseEntity<List<BankCard>>(walletShow.getMyAvailableBankCardList
                (BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BankCardValidateInfo> userWalletBankCardsValidationsCardNumGet(
            @ApiParam(value = "银行卡号", required = true) @PathVariable("cardNum") String cardNum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<BankCardValidateInfo>(walletShow.validateCard(cardNum),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NextTimeResult> userWalletBankCardsValidationsRealNameAndPhonePost(
            @ApiParam(value = "预留手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(value = "银行卡号", required = true) @RequestParam(value = "cardNum", required = true) String cardNum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Long> result = bankCardBusiness.validateCardByPhone(
                BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), cardNum, phone);
        NextTimeResult nextTimeResult = new NextTimeResult();
        nextTimeResult.setNextTime(result.get("nextTime"));
        return new ResponseEntity<NextTimeResult>(nextTimeResult, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> userWalletBankCardsValidationsVerifyCodePost(
            @ApiParam(value = "预留手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(value = "银行卡号", required = true) @RequestParam(value = "cardNum", required = true) String cardNum,
            @ApiParam(value = "验证码", required = true) @RequestParam(value = "authCode", required = true) String authCode,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        bankCardBusiness.checkVerifyCode(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), phone, cardNum,
                authCode);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Wallet> userWalletGet(HttpServletRequest request,
                                                HttpServletResponse response) throws Exception {
        Wallet wallet = walletShow.getMyWallet(BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID));
        return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<WalletLogItem>> userWalletLogsGet(HttpServletRequest request,
                                                                 HttpServletResponse response)
            throws Exception {
        return new ResponseEntity<List<WalletLogItem>>(walletShow.getMyWalletLogs
                (BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WalletLogItem> userWalletLogsLogIdGet(@ApiParam(value = "", required = true) @PathVariable("logId") String logId,
                                                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        WalletLogItem walletLogItem = walletShow.getWalletLogItemById(logId);
        if (walletLogItem == null)
            return new ResponseEntity<WalletLogItem>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(walletLogItem);
    }

    @Override
    public ResponseEntity<ChargeInfo> userWalletRechargesPost(
            @ApiParam(value = "渠道", required = true, allowableValues = "upacp, wx_app, wx_pub") @RequestParam(value = "channelType", required = true) String channelType,
            @ApiParam(value = "充值的金额，单位为分", required = true) @RequestParam(value = "money", required = true) Integer money,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ChargeInfo chargeInfo = new ChargeInfo();
        chargeInfo.setInfoJsonString(JSON.toJSONString(paymentBusiness.charge
                (BnmSwaggerControllerUtil.getSessionForLong(request, ServerSessionAttr.SESSION_UID), channelType, money)));
        return new ResponseEntity<ChargeInfo>(chargeInfo, HttpStatus.OK);
    }


}
