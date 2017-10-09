package cn.bangnongmang.admin.swagger.size;

import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.size.api.FieldsApi;
import cn.bangnongmang.admin.swagger.size.api.MarketApi;
import cn.bangnongmang.size.io.swagger.model.*;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2017-05-16.
 */
@RestController
@RequestMapping(BaseConf.BASE_URL)
public class AllSizeController implements FieldsApi,MarketApi{
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
}
