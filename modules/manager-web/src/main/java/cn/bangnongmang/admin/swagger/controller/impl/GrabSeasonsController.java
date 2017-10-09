package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.manager.GrabSeasonBusiness;
import cn.bangnongmang.admin.io.swagger.show.GrabSeasonsShow;
import cn.bangnongmang.admin.swagger.api.GrabSeasonsApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.GrabSeason;
import cn.bangnongmang.admin.swagger.model.OrderIdWrapper;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wenxx on 2017/5/27.
 */

@RestController
@RequestMapping(BaseConf.BASE_URL)
public class GrabSeasonsController implements GrabSeasonsApi {
    @Autowired
    private GrabSeasonsShow grabSeasonsShow;
    @Autowired
    private GrabSeasonBusiness grabSeasonBusiness;

    @Override
    public ResponseEntity<List<GrabSeason>> grabSeasonsGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<GrabSeason>>(grabSeasonsShow.getGrabSeasons(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> grabSeasonsPost(
            @ApiParam(value = "开始时间", required = true) @RequestParam(value = "startTime", required = true) Long startTime,
            @ApiParam(value = "结束时间", required = true) @RequestParam(value = "endTime", required = true) Long endTime,
            @ApiParam(value = "备注信息", required = true) @RequestParam(value = "ps", required = true) String ps, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        grabSeasonBusiness.createGrabSeason(startTime, endTime, ps);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> grabSeasonsSeasonIdDelete(@ApiParam(value = "", required = true) @PathVariable("seasonId") String seasonId,
                                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        grabSeasonBusiness.deleteGrabSeason(seasonId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<GrabSeason> grabSeasonsSeasonIdGet(@ApiParam(value = "", required = true) @PathVariable("seasonId") String seasonId,
                                                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<GrabSeason>(grabSeasonsShow.getGrabSeason(seasonId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> grabSeasonsSeasonIdPost(@ApiParam(value = "", required = true) @PathVariable("seasonId") String seasonId,
                                                        @ApiParam(value = "", required = true) @RequestParam(value = "startTime", required = true) Long startTime,
                                                        @ApiParam(value = "", required = true) @RequestParam(value = "endTime", required = true) Long endTime,
                                                        @ApiParam(value = "", required = true) @RequestParam(value = "ps", required = true) String ps,
                                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        grabSeasonBusiness.changeGrabSeasonBasicInfo(seasonId, startTime, endTime, ps);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<OrderIdWrapper>> grabSeasonsSeasonIdSeasonOrdersGet(
            @ApiParam(value = "", required = true) @PathVariable("seasonId") String
                    seasonId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<OrderIdWrapper>>(grabSeasonsShow.getGrabSeasonOrders(seasonId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> grabSeasonsSeasonIdSeasonOrdersPost(@ApiParam(value = "", required = true) @PathVariable("seasonId") String seasonId,
                                                                    @ApiParam(value = "", required = true) @Valid @RequestBody List<OrderIdWrapper> orders,
                                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("orders : " +JSON.toJSONString( orders));
        grabSeasonBusiness.changeGrabSeasonOrders(seasonId, orders.stream()
                                                                  .map(OrderIdWrapper::getOrderId)
                                                                  .collect(Collectors.toList()));
        return ResponseEntity.ok(null);
    }


    @Override
    public ResponseEntity<Void> grabSeasonsSeasonIdStatusDraftPost(@ApiParam(value = "", required = true) @PathVariable("seasonId") String seasonId,
                                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        grabSeasonBusiness.setGrabSeasonStateAsDraft(seasonId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> grabSeasonsSeasonIdStatusPublishPost(@ApiParam(value = "", required = true) @PathVariable("seasonId") String seasonId,
                                                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        grabSeasonBusiness.setGrabSeasonStateAsPublished(seasonId);
        return ResponseEntity.ok(null);
    }
}
