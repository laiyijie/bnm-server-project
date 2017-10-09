package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.OrderBasic;
import cn.bangnongmang.admin.swagger.model.OrderDetail;
import cn.bangnongmang.admin.swagger.model.OrderMultiInfo;
import cn.bangnongmang.admin.swagger.model.UserBasic;

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

@Api(value = "orders", description = "the orders API")
public interface OrdersApi {

    @ApiOperation(value = "get all orders", notes = "", response = OrderBasic.class, responseContainer = "List", tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = OrderBasic.class) })
    
    @RequestMapping(value = "/orders",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderBasic>> ordersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "认证一个订单", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/accept",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> ordersOrderIdAcceptPut(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取单个订单的信息", notes = "", response = OrderDetail.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderDetail.class) })
    
    @RequestMapping(value = "/orders/{orderId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<OrderDetail> ordersOrderIdGet(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有队员的位置", notes = "", response = UserBasic.class, responseContainer = "List", tags={ "Team", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserBasic.class) })
    
    @RequestMapping(value = "/orders/{orderId}/membergeo",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserBasic>> ordersOrderIdMembergeoGet(@ApiParam(value = "订单ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取订单的多维度信息列表", notes = "", response = OrderMultiInfo.class, responseContainer = "List", tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderMultiInfo.class) })
    
    @RequestMapping(value = "/orders/{orderId}/multiInfos",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderMultiInfo>> ordersOrderIdMultiInfosGet(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个多维度展示项", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/multiInfos/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> ordersOrderIdMultiInfosIdDelete(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "",required=true ) @PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "发布一个多维度展示项", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/multiInfos/{id}/status/publish",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdMultiInfosIdStatusPublishPost(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "",required=true ) @PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "撤销发布一个多维度展示项", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/multiInfos/{id}/status/unpublish",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdMultiInfosIdStatusUnpublishPost(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "",required=true ) @PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加一个多维度展示项", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/multiInfos",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdMultiInfosPost(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "不需要传id" ,required=true )  @Valid @RequestBody OrderMultiInfo multiInfo, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除订单要求", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/option/{optionId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> ordersOrderIdOptionOptionIdDelete(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "",required=true ) @PathVariable("optionId") Long optionId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "添加订单要求", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/option/{optionId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdOptionOptionIdPost(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "",required=true ) @PathVariable("optionId") Long optionId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "更改订单信息", notes = "", response = Void.class, tags={ "Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> ordersOrderIdPut(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
