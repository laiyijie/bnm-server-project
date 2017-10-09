package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.GrabSeason;
import java.util.List;
import cn.bangnongmang.admin.swagger.model.OrderIdWrapper;

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

@Api(value = "grabSeasons", description = "the grabSeasons API")
public interface GrabSeasonsApi {

    @ApiOperation(value = "获取所有的抢单场次", notes = "", response = GrabSeason.class, responseContainer = "List", tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = GrabSeason.class) })
    
    @RequestMapping(value = "/grabSeasons",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<GrabSeason>> grabSeasonsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "创建抢单场次", notes = "", response = Void.class, tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/grabSeasons",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> grabSeasonsPost(@ApiParam(value = "开始时间", required=true) @RequestParam(value="startTime", required=true)  Long startTime,@ApiParam(value = "结束时间", required=true) @RequestParam(value="endTime", required=true)  Long endTime,@ApiParam(value = "备注信息", required=true) @RequestParam(value="ps", required=true)  String ps, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除当前场次", notes = "", response = Void.class, tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> grabSeasonsSeasonIdDelete(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取当前场次信息", notes = "", response = GrabSeason.class, tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = GrabSeason.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<GrabSeason> grabSeasonsSeasonIdGet(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改场次的基本信息", notes = "", response = Void.class, tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> grabSeasonsSeasonIdPost(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId,@ApiParam(value = "", required=true) @RequestParam(value="startTime", required=true)  Long startTime,@ApiParam(value = "", required=true) @RequestParam(value="endTime", required=true)  Long endTime,@ApiParam(value = "", required=true) @RequestParam(value="ps", required=true)  String ps, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取该抢单场次下的订单", notes = "", response = OrderIdWrapper.class, responseContainer = "List", tags={ "GrabSeason","Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderIdWrapper.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}/seasonOrders",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderIdWrapper>> grabSeasonsSeasonIdSeasonOrdersGet(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "设置该抢单场次下的订单", notes = "", response = Void.class, tags={ "GrabSeason","Orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}/seasonOrders",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> grabSeasonsSeasonIdSeasonOrdersPost(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody List<OrderIdWrapper> orders, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "将该抢单场次设为草稿状态", notes = "", response = Void.class, tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}/status/draft",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> grabSeasonsSeasonIdStatusDraftPost(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "发布该抢单场次", notes = "", response = Void.class, tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/grabSeasons/{seasonId}/status/publish",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> grabSeasonsSeasonIdStatusPublishPost(@ApiParam(value = "",required=true ) @PathVariable("seasonId") String seasonId, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
