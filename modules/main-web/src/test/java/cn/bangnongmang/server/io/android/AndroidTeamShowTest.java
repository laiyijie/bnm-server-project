package cn.bangnongmang.server.io.android;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.server.io.android.show.AndroidOrderShow;
import cn.bangnongmang.server.io.android.show.AndroidTeamShow;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })
public class AndroidTeamShowTest {

	@Autowired
	private AndroidTeamShow androidTeamShow;

	@Autowired
	private AndroidOrderShow orderShow;

	@Test
	public void test() {
		// System.out.println(JSON.toJSONString(orderShow.showOrderInfoSimpleList(),true));
	}

	@Test
	public void t(){
		Executor executor = Executors.newCachedThreadPool();
		executor.execute(()->{System.out.println(this);});
	}

}
