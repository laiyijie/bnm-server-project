package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.io.swagger.show.OrderShow;
import cn.bangnongmang.server.swagger.api.GrabseasonsApi;
import cn.bangnongmang.server.swagger.model.GrabSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2017-04-19.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class GrabSeasonsController implements GrabseasonsApi {

    @Autowired
    private OrderShow orderShow;

    @Override
    public ResponseEntity<List<GrabSeason>> grabseasonsStatusAvailableGet(
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<GrabSeason>>(orderShow.getNextAvailableGrabSeasons(),
                HttpStatus.OK);
    }
}
