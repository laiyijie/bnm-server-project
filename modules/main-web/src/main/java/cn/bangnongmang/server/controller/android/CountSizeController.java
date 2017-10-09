package cn.bangnongmang.server.controller.android;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.SESSION_USERNAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidRequest;
import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.size.io.swagger.model.Field;
import cn.bangnongmang.size.io.swagger.model.Polygon;
import cn.bangnongmang.size.io.swagger.model.UserPoint;
import cn.bangnongmang.size.io.swagger.model.WorkingSizeHistory;

@RestController
@RequestMapping("countsize")
public class CountSizeController {

    @Autowired
    private SizeCounterService sizeCounterService;
    @Autowired
    private UserService userService;

    /**
     * 获取订单的作业记录
     *
     * @param androidRequest {orderId:String}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title getOrderCountSize
     */

    @RequestMapping("getOrderCountSize")
    public List<WorkingSizeHistory> getOrderCountSize(@RequestBody AndroidRequest androidRequest,
                                                      @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        try {
            return sizeCounterService.getDefaultApi()
                                     .workingHistoryOrderidOrderidGet(orderId)
                                     .execute()
                                     .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 获取某天的记录
     *
     * @param androidRequest {orderFarmerWorkSizeId:Long}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title getOrderDayCountSize
     */
    @RequestMapping("getOrderDayCountSize")
    public List<WorkingSizeHistory> getOrderDayCountSize(@RequestBody AndroidRequest androidRequest,
                                                         @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        Long orderFarmerWorkSizeId = (Long) params.get("orderFarmerWorkSizeId");

        try {
            return sizeCounterService.getDefaultApi()
                                     .workingHistoryOuterindexOuterindexGet(String.valueOf(orderFarmerWorkSizeId))
                                     .execute()
                                     .body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    /**
     * 上传自己的位置
     *
     * @param androidRequest {orderId:string, pointsJsonString:String (List<UserPoint>)}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title updatePosition
     */
    @RequestMapping("updatePosition")
    public List<WorkingSizeHistory> updatePosition(@RequestBody AndroidRequest androidRequest,
                                                   @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();
        Account account = userService.getUserInfoByUsername(username);
        String orderId = (String) params.get("orderId");

        List<UserPoint> points = JSON.parseArray((String) params.get("pointsJsonString"), UserPoint.class);

        points.stream()
              .forEach(a -> a.setUid(account.getUid()));

        try {
            return sizeCounterService.getDefaultApi()
                                     .workingOrderidPointsPost(orderId, points)
                                     .execute()
                                       .body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * 查询田块信息
     *
     * @param androidRequest {id:int}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title getFieldInfo
     */
    @RequestMapping("getFieldInfoById")
    public Field getFieldInfoById(@RequestBody AndroidRequest androidRequest,
                                  @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        int id = (int) params.get("id");

        try {
            return sizeCounterService.getDefaultApi()
                                     .fieldsIdGet(id)
                                     .execute()
                                     .body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前countersize
     *
     * @param androidRequest {orderId:string}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title getCurrentCountsize
     */
    @RequestMapping("getCurrentCountsize")
    public List<WorkingSizeHistory> getCurrentCountsize(@RequestBody AndroidRequest androidRequest,
                                                        @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {
        Map<String, Object> params = androidRequest.resolveJsonParams();

        String orderId = (String) params.get("orderId");

        try {
            return sizeCounterService.getDefaultApi()
                                     .workingHistoryCurrentOrderidGet(orderId)
                                     .execute()
                                     .body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前视野的田信息
     *
     * @param androidRequest {polygonJsonStr:string}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title getFieldByView
     */
    @RequestMapping("getFieldByView")
    public List<Field> getFieldByView(@RequestBody AndroidRequest androidRequest,
                                      @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String polygonJsonStr = (String) params.get("polygonJsonStr");

        Polygon viewPolygon = JSON.parseObject(polygonJsonStr, Polygon.class);

        try {
            return sizeCounterService.getDefaultApi()
                                     .fieldsViewPost(viewPolygon)
                                     .execute()
                                     .body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前视野的田信息
     *
     * @param androidRequest {fieldId:string}
     * @param username
     * @return
     * @throws AndroidOutputException
     * @Title getCurrentCountsize
     */
    @RequestMapping("getFieldByFieldId")
    public Field getFieldByFieldId(@RequestBody AndroidRequest androidRequest,
                                   @SessionAttribute(SESSION_USERNAME) String username) throws AndroidOutputException {

        Map<String, Object> params = androidRequest.resolveJsonParams();

        String fieldId = (String) params.get("fieldId");

        try {
            return sizeCounterService.getDefaultApi()
                                     .fieldsFieldidFieldIdGet(fieldId)
                                     .execute()
                                     .body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
