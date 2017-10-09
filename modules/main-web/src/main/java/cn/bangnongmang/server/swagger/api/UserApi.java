package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.AccountAuth;
import cn.bangnongmang.server.swagger.model.AliCredentials;
import cn.bangnongmang.server.swagger.model.AliOssBaseUrl;
import cn.bangnongmang.server.swagger.model.BankCard;
import cn.bangnongmang.server.swagger.model.BankCardValidateInfo;
import cn.bangnongmang.server.swagger.model.ChargeInfo;
import cn.bangnongmang.server.swagger.model.NextTimeResult;
import cn.bangnongmang.server.swagger.model.Order;
import cn.bangnongmang.server.swagger.model.StarInfo;
import cn.bangnongmang.server.swagger.model.StringResponse;
import cn.bangnongmang.server.swagger.model.SubOrder;
import cn.bangnongmang.server.swagger.model.Team;
import cn.bangnongmang.server.swagger.model.UserDetail;
import cn.bangnongmang.server.swagger.model.UserGeo;
import cn.bangnongmang.server.swagger.model.UserMachine;
import cn.bangnongmang.server.swagger.model.UserMachineDetail;
import cn.bangnongmang.server.swagger.model.Wallet;
import cn.bangnongmang.server.swagger.model.WalletLogItem;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "获取阿里oss的凭证", notes = "", response = AliCredentials.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = AliCredentials.class) })
    
    @RequestMapping(value = "/user/aliOssAuthCridential",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<AliCredentials> userAliOssAuthCridentialGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我的所有订单信息", notes = "", response = Order.class, responseContainer = "List", tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Order.class) })
    
    @RequestMapping(value = "/user/favorite/orders",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> userFavoriteOrdersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "取消关注", notes = "", response = Void.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/favorite/orders/{orderId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> userFavoriteOrdersOrderIdDelete(@ApiParam(value = "需要关注订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查看是否关注了这个订单", notes = "", response = Void.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "已关注", response = Void.class),
        @ApiResponse(code = 404, message = "未关注", response = Void.class) })
    
    @RequestMapping(value = "/user/favorite/orders/{orderId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Void> userFavoriteOrdersOrderIdGet(@ApiParam(value = "关注的订单ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "关注一个订单", notes = "", response = Void.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/favorite/orders/{orderId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userFavoriteOrdersOrderIdPost(@ApiParam(value = "需要关注订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我自己的个人信息 ", notes = "", response = UserDetail.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = UserDetail.class) })
    
    @RequestMapping(value = "/user",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<UserDetail> userGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取身份证的baseurl，用于上传到阿里云", notes = "", response = AliOssBaseUrl.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = AliOssBaseUrl.class) })
    
    @RequestMapping(value = "/user/idCardImage/baseUrl",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<AliOssBaseUrl> userIdCardImageBaseUrlGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我的机器列表", notes = "", response = UserMachine.class, responseContainer = "List", tags={ "User","Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserMachine.class) })
    
    @RequestMapping(value = "/user/machines",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<UserMachine>> userMachinesGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除某台机器", notes = "", response = Void.class, tags={ "User","Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/machines/{machineID}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> userMachinesMachineIDDelete(@ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取某台机器", notes = "", response = UserMachineDetail.class, tags={ "User","Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserMachineDetail.class) })
    
    @RequestMapping(value = "/user/machines/{machineID}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<UserMachineDetail> userMachinesMachineIDGet(@ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "更新用户车辆信息", notes = "", response = Void.class, tags={ "User","Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/machines/{machineID}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userMachinesMachineIDPost(@ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID,@ApiParam(value = "车辆的详细信息" ,required=true )  @Valid @RequestBody UserMachineDetail userMachineDetail, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "更新用户车辆信息", notes = "", response = Void.class, tags={ "User","Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/machines/{machineID}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> userMachinesMachineIDPut(@ApiParam(value = "",required=true ) @PathVariable("machineID") Long machineID,@ApiParam(value = "车辆的详细信息" ,required=true )  @Valid @RequestBody UserMachineDetail userMachineDetail, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "添加一个车辆", notes = "", response = Void.class, tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/machines",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userMachinesPost(@ApiParam(value = "车辆的详细信息" ,required=true )  @Valid @RequestBody UserMachineDetail userMachineDetial, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取增加machine的webview url", notes = "", response = StringResponse.class, tags={ "User","Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = StringResponse.class) })
    
    @RequestMapping(value = "/user/machines/webViewUrl",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<StringResponse> userMachinesWebViewUrlGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查看我的订单", notes = "", response = Order.class, responseContainer = "List", tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Order.class) })
    
    @RequestMapping(value = "/user/orders",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> userOrdersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查看是不是我的订单", notes = "", response = Void.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "是我的订单", response = Void.class),
        @ApiResponse(code = 404, message = "不是我的订单", response = Void.class) })
    
    @RequestMapping(value = "/user/orders/{orderId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Void> userOrdersOrderIdGet(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查询这个订单下我评价的情况（评价过哪些人）,作为队长应该评价队员，作为队员应该评价队长", notes = "", response = StarInfo.class, responseContainer = "List", tags={ "User","Star", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = StarInfo.class) })
    
    @RequestMapping(value = "/user/orders/{orderId}/stars",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<StarInfo>> userOrdersOrderIdStarsGet(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "", notes = "", response = Void.class, tags={ "User","Star", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/orders/{orderId}/stars",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userOrdersOrderIdStarsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody StarInfo starInfo,@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取指定订单下的子订单", notes = "", response = SubOrder.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = SubOrder.class) })
    
    @RequestMapping(value = "/user/orders/{orderId}/subOrder",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<SubOrder> userOrdersOrderIdSubOrderGet(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "开始自己的作业", notes = "", response = Void.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/orders/{orderId}/subOrder/status/startWork",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> userOrdersOrderIdSubOrderStatusStartWorkPut(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "结束自己的作业", notes = "", response = Void.class, tags={ "User","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/orders/{orderId}/subOrder/status/stopWork",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> userOrdersOrderIdSubOrderStatusStopWorkPut(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取头像的baseurl，用于上传到阿里云", notes = "", response = AliOssBaseUrl.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = AliOssBaseUrl.class) })
    
    @RequestMapping(value = "/user/portrait/baseUrl",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<AliOssBaseUrl> userPortraitBaseUrlGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "设置自己的头像", notes = "", response = Void.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/portrait",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userPortraitPost(@ApiParam(value = "上传到阿里云的文件名", required=true) @RequestParam(value="portraitFileName", required=true)  String portraitFileName, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取实名认证信息", notes = "", response = AccountAuth.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = AccountAuth.class) })
    
    @RequestMapping(value = "/user/realNameAuthInfo",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<AccountAuth> userRealNameAuthInfoGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "实名认证", notes = "", response = Void.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/realNameAuthInfo",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userRealNameAuthInfoPost(@ApiParam(value = "真实姓名", required=true) @RequestParam(value="realName", required=true)  String realName,@ApiParam(value = "身份证号", required=true) @RequestParam(value="idNumber", required=true)  String idNumber,@ApiParam(value = "身份证正面URL", required=true) @RequestParam(value="upSideUrl", required=true)  String upSideUrl,@ApiParam(value = "身份证反面URL", required=true) @RequestParam(value="downSideUrl", required=true)  String downSideUrl, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查看自己是否有没评价的订单", notes = "", response = Order.class, responseContainer = "List", tags={ "User","Star", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Order.class) })
    
    @RequestMapping(value = "/user/stars/status/unstar/orders",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> userStarsStatusUnstarOrdersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我当前所在的队伍列表", notes = "", response = Team.class, responseContainer = "List", tags={ "User","Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Team.class) })
    
    @RequestMapping(value = "/user/teams",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<Team>> userTeamsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "更新自己的地理位置", notes = "", response = Void.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/userGeo",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> userUserGeoPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserGeo userGeo, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除这张银行卡", notes = "", response = Void.class, tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/wallet/bankCards/{bankCardId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> userWalletBankCardsBankCardIdDelete(@ApiParam(value = "银行卡的ID，唯一标识",required=true ) @PathVariable("bankCardId") Long bankCardId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "使用银行卡提现", notes = "", response = Void.class, tags={ "Wallet","User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/wallet/bankCards/{bankCardNum}/withDraws",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userWalletBankCardsBankCardNumWithDrawsPost(@ApiParam(value = "开户银行", required=true) @RequestParam(value="openBank", required=true)  String openBank,@ApiParam(value = "充值的金额，单位为分", required=true) @RequestParam(value="money", required=true)  Integer money,@ApiParam(value = "卡号",required=true ) @PathVariable("bankCardNum") String bankCardNum, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查看自己的银行卡信息", notes = "", response = BankCard.class, responseContainer = "List", tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = BankCard.class) })
    
    @RequestMapping(value = "/user/wallet/bankCards",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<BankCard>> userWalletBankCardsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查询银行卡号是否正确", notes = "", response = BankCardValidateInfo.class, tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = BankCardValidateInfo.class) })
    
    @RequestMapping(value = "/user/wallet/bankCards/validations/{cardNum}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<BankCardValidateInfo> userWalletBankCardsValidationsCardNumGet(@ApiParam(value = "银行卡号",required=true ) @PathVariable("cardNum") String cardNum, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "验证几个要素", notes = "", response = NextTimeResult.class, tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = NextTimeResult.class) })
    
    @RequestMapping(value = "/user/wallet/bankCards/validations/realNameAndPhone",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<NextTimeResult> userWalletBankCardsValidationsRealNameAndPhonePost(@ApiParam(value = "预留手机号", required=true) @RequestParam(value="phone", required=true)  String phone,@ApiParam(value = "银行卡号", required=true) @RequestParam(value="cardNum", required=true)  String cardNum, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "验证码是否正确", notes = "", response = Void.class, tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/user/wallet/bankCards/validations/verifyCode",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> userWalletBankCardsValidationsVerifyCodePost(@ApiParam(value = "预留手机号", required=true) @RequestParam(value="phone", required=true)  String phone,@ApiParam(value = "银行卡号", required=true) @RequestParam(value="cardNum", required=true)  String cardNum,@ApiParam(value = "验证码", required=true) @RequestParam(value="authCode", required=true)  String authCode, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我的钱包信息", notes = "", response = Wallet.class, tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Wallet.class) })
    
    @RequestMapping(value = "/user/wallet",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Wallet> userWalletGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我的钱包日志", notes = "", response = WalletLogItem.class, responseContainer = "List", tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = WalletLogItem.class) })
    
    @RequestMapping(value = "/user/walletLogs",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<WalletLogItem>> userWalletLogsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取我的单个钱包日志", notes = "", response = WalletLogItem.class, tags={ "User","Wallet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = WalletLogItem.class) })
    
    @RequestMapping(value = "/user/walletLogs/{logId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<WalletLogItem> userWalletLogsLogIdGet(@ApiParam(value = "",required=true ) @PathVariable("logId") String logId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "发起充值", notes = "", response = ChargeInfo.class, tags={ "Wallet","User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = ChargeInfo.class) })
    
    @RequestMapping(value = "/user/wallet/recharges",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ChargeInfo> userWalletRechargesPost(@ApiParam(value = "渠道", required=true, allowableValues="upacp, wx_app, wx_pub") @RequestParam(value="channelType", required=true)  String channelType,@ApiParam(value = "充值的金额，单位为分", required=true) @RequestParam(value="money", required=true)  Integer money, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
