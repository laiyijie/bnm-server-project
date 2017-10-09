package cn.bangnongmang.server.io.android;

import cn.bangnongmang.server.io.android.show.AndroidOrderShow;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by admin on 2017-04-06.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
        @ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })
public class AndroidOrderShowTest {

    @Autowired
    private AndroidOrderShow androidOrderShow;

    @Test
    public void testOrderSimpleInfoShow(){
        System.out.println(JSON.toJSONString(androidOrderShow.getMyOrderInfoDetailByOrderId
                ("201703271144549522622637"),true)) ;
    }

}
