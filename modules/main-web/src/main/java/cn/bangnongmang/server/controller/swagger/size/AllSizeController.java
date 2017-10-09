package cn.bangnongmang.server.controller.swagger.size;

import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.server.business.common.AccountBusiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.controller.swagger.size.api.FieldsApi;
import cn.bangnongmang.server.controller.swagger.size.api.MarketApi;
import cn.bangnongmang.server.controller.swagger.size.api.WorkingApi;
import cn.bangnongmang.size.io.swagger.model.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 2017-05-16.
 */
@RestController
@RequestMapping(ApiBaseConfig.BASE_URL)
public class AllSizeController implements FieldsApi, MarketApi, WorkingApi {
    @Autowired
    private AccountBusiness accountBusiness;


    @Override
    public ResponseEntity<Field> fieldsFieldidFieldIdGet(
            @ApiParam(value = "", required = true) @PathVariable("fieldId") String fieldId) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> fieldsIdDelete(@PathVariable("id") Integer id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Field> fieldsIdGet(@PathVariable("id") Integer id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> fieldsIdPut(@PathVariable("id") Integer id, @RequestBody Field field) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<FieldIdWrapper> fieldsPost(@RequestBody UpdateField data) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<Field>> fieldsViewPost(@RequestBody Polygon viewPolygon) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderMarker>> marketOrderidAreaidMarksGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
            @ApiParam(value = "", required = true) @PathVariable("areaid") Integer areaid) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> marketOrderidAreaidMarksPost(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                             @ApiParam(value = "", required = true) @PathVariable("areaid") Integer areaid,
                                                             @ApiParam(value = "", required = true) @RequestBody String fieldId) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> marketOrderidAreaidMarksPut(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                            @ApiParam(value = "", required = true) @PathVariable("areaid") Integer areaid,
                                                            @ApiParam(value = "", required = true) @RequestBody OrderMarker orderMarker) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderArea>> marketOrderidAreasGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> marketOrderidAreasPost(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                       @ApiParam(value = "", required = true) @RequestBody Polygon data) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> marketOrderidAreasPut(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                      @ApiParam(value = "", required = true) @RequestBody OrderArea data) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderMarker>> marketOrderidMarksGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> marketOrderidOrderFieldFieldIdDelete(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                                     @ApiParam(value = "", required = true) @PathVariable("fieldId") String fieldId) throws Exception {
        return null;
    }


    @Override
    public ResponseEntity<List<OrderField>> marketOrderidOrderFieldGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> marketOrderidOrderFieldPost(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                            @ApiParam(value = "", required = true) @RequestBody String fieldId) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<WorkingSizeHistory>> workingHistoryCurrentOrderidGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<WorkingSizeHistory>> workingHistoryOrderidOrderidGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<WorkingSizeHistory>> workingHistoryOuterindexOuterindexGet(
            @ApiParam(value = "", required = true) @PathVariable("outerindex") String outerindex) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<WorkingSizeHistory>> workingOrderidOuterindexStatusPut(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
            @ApiParam(value = "", required = true) @PathVariable("outerindex") String outerindex,
            @ApiParam(value = "修改订单状态，当完成单日作业时，为stop，完成全部作业之后为finish ", required = true) @RequestBody WorkingOrderStatus status) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<WorkingSizeHistory>> workingOrderidPointsPost(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
            @ApiParam(value = "") @RequestBody List<UserPoint> point) throws Exception {
        Optional<UserPoint> p = point.stream()
                                     .max(Comparator.comparingLong(UserPoint::getTimestamp));
        if (p.isPresent()) {
            UserPoint userGeo = p.get();
            AccountGeo accountGeo = new AccountGeo();
            accountGeo.setLatitude(userGeo.getLatitude());
            accountGeo.setLongitude(userGeo.getLongitude());
            accountGeo.setUid(userGeo.getUid());
            accountBusiness.updateAccountGeoInfo(accountGeo);
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> workingOrderidSettingsPost(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                           @ApiParam(value = "", required = true) @RequestBody SizeCounterSetting settings) throws Exception {
        return null;
    }
}
