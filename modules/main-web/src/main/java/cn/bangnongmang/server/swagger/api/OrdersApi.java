package cn.bangnongmang.server.swagger.api;

import java.util.List;
import cn.bangnongmang.server.swagger.model.MemberDayWorkInfo;
import cn.bangnongmang.server.swagger.model.Order;
import cn.bangnongmang.server.swagger.model.OrderFarmerWorkInfo;
import cn.bangnongmang.server.swagger.model.OrderMultiInfo;
import cn.bangnongmang.server.swagger.model.SubOrder;
import cn.bangnongmang.server.swagger.model.Team;
import cn.bangnongmang.server.swagger.model.UserSimple;
import cn.bangnongmang.server.swagger.model.WorkSizeAuthImage;

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

    @ApiOperation(value = "获取当前的所有可以显示的订单", notes = "", response = Order.class, responseContainer = "List", tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Order.class) })
    
    @RequestMapping(value = "/orders",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> ordersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取这个订单的信息", notes = "", response = Order.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Order.class) })
    
    @RequestMapping(value = "/orders/{orderId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Order> ordersOrderIdGet(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取该订单的所有队员（只有接单成功以后才可以获取）", notes = "", response = UserSimple.class, responseContainer = "List", tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserSimple.class) })
    
    @RequestMapping(value = "/orders/{orderId}/members",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<UserSimple>> ordersOrderIdMembersGet(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取订单多维度信息", notes = "根据不同的type有对应不同的extra_info 其中 type 为PHOTO的话，返回值是list<string>json串，为SIZE则extra_info为空 ", response = OrderMultiInfo.class, responseContainer = "List", tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderMultiInfo.class) })
    
    @RequestMapping(value = "/orders/{orderId}/multiInfos",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<OrderMultiInfo>> ordersOrderIdMultiInfosGet(@ApiParam(value = "",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "分配本日作业的面积", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/status/distributeWork",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdStatusDistributeWorkPost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "每个队员的作业面积分配"  )  @Valid @RequestBody List<MemberDayWorkInfo> totalSize, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "完成了本日的作业", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/status/finishOneDayWork",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdStatusFinishOneDayWorkPost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "抢单", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/status/got",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdStatusGotPost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "抢单的队伍", required=true) @RequestParam(value="teamId", required=true)  Long teamId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "已经抵达位置准备好可以工作了", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/status/readyToWork",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdStatusReadyToWorkPost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "完成了本日的作业", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/status/updateTodayTotalSize",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdStatusUpdateTodayTotalSizePost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "本日作业面积", required=true) @RequestParam(value="totalSize", required=true)  Double totalSize, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "查询该订单下所有的subOrders", notes = "", response = SubOrder.class, responseContainer = "List", tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = SubOrder.class) })
    
    @RequestMapping(value = "/orders/{orderId}/subOrders",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<SubOrder>> ordersOrderIdSubOrdersGet(@ApiParam(value = "订单的Id",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "判断是否所有人都停止了作业", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "没有人正在工作", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/subOrders/status/noOneWorking",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<Void> ordersOrderIdSubOrdersStatusNoOneWorkingGet(@ApiParam(value = "订单的Id",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取该订单下的所有队伍", notes = "", response = Team.class, responseContainer = "List", tags={ "Team","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Team.class) })
    
    @RequestMapping(value = "/orders/{orderId}/teams",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<Team>> ordersOrderIdTeamsGet(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "创建队伍", notes = "", response = Team.class, tags={ "Team","Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Team.class) })
    
    @RequestMapping(value = "/orders/{orderId}/teams",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Team> ordersOrderIdTeamsPost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "队伍的简单藐视", required=true) @RequestParam(value="message", required=true)  String message, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取该订单的所有作业情况", notes = "", response = OrderFarmerWorkInfo.class, responseContainer = "List", tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderFarmerWorkInfo.class) })
    
    @RequestMapping(value = "/orders/{orderId}/workSizes",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<OrderFarmerWorkInfo>> ordersOrderIdWorkSizesGet(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "", notes = "", response = WorkSizeAuthImage.class, responseContainer = "List", tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = WorkSizeAuthImage.class) })
    
    @RequestMapping(value = "/orders/{orderId}/workSizes/{workSizeId}/authImages",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<WorkSizeAuthImage>> ordersOrderIdWorkSizesWorkSizeIdAuthImagesGet(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "orderFarmerWorkInfoId",required=true ) @PathVariable("workSizeId") Long workSizeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "delete the auth images", notes = "", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/workSizes/{workSizeId}/authImages/{imageId}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> ordersOrderIdWorkSizesWorkSizeIdAuthImagesImageIdDelete(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "orderFarmerWorkInfoId",required=true ) @PathVariable("workSizeId") Long workSizeId,@ApiParam(value = "imageId",required=true ) @PathVariable("imageId") Long imageId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "", notes = "add a new auth images", response = Void.class, tags={ "Order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/orders/{orderId}/workSizes/{workSizeId}/authImages",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersOrderIdWorkSizesWorkSizeIdAuthImagesPost(@ApiParam(value = "订单的ID",required=true ) @PathVariable("orderId") String orderId,@ApiParam(value = "orderFarmerWorkInfoId",required=true ) @PathVariable("workSizeId") Long workSizeId,@ApiParam(value = "name of this images", required=true) @RequestParam(value="title", required=true)  String title,@ApiParam(value = "the image url", required=true) @RequestParam(value="imageUrl", required=true)  String imageUrl, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
