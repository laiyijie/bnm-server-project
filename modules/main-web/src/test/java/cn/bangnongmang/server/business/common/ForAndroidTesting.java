package cn.bangnongmang.server.business.common;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.service.service.FriendshipService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForAndroidTesting {

	public static final String namesInString = "[\"南风\",\"醉山\",\"初彤\",\"凝海\",\"紫文\",\"凌晴\",\"香卉\",\"雅琴\",\"傲安\",\"傲之\",\"初蝶\",\"寻桃\",\"代芹\",\"诗霜\",\"春柏\",\"绿夏\",\"碧灵\",\"诗柳\",\"夏柳\",\"采白\",\"慕梅\",\"乐安\",\"冬菱\",\"紫安\",\"宛凝\",\"雨雪\",\"易真\",\"安荷\",\"静竹\",\"代柔\",\"丹秋\",\"绮梅\",\"依白\",\"凝荷\",\"幼珊\",\"忆彤\",\"凌青\",\"之桃\",\"芷荷\",\"听荷\",\"代玉\",\"念珍\",\"梦菲\",\"夜春\",\"千秋\",\"白秋\",\"谷菱\",\"飞松\",\"初瑶\",\"惜灵\",\"恨瑶\",\"梦易\",\"新瑶\",\"曼梅\",\"碧曼\",\"友瑶\",\"雨兰\",\"夜柳\",\"香蝶\",\"盼巧\",\"芷珍\",\"香卉\",\"含芙\",\"夜云\",\"依萱\",\"凝雁\",\"以莲\",\"易容\",\"元柳\",\"安南\",\"幼晴\",\"尔琴\",\"飞阳\",\"白凡\",\"沛萍\",\"雪瑶\",\"向卉\",\"采文\",\"乐珍\",\"寒荷\",\"觅双\",\"白桃\",\"安卉\",\"迎曼\",\"盼雁\",\"乐松\",\"涵山\",\"恨寒\",\"问枫\",\"以柳\",\"含海\",\"秋春\",\"翠曼\",\"忆梅\",\"涵柳\",\"梦香\",\"海蓝\",\"晓曼\",\"代珊\",\"春冬\",\"恨荷\",\"忆丹\",\"静芙\",\"绮兰\",\"梦安\",\"紫丝\",\"千雁\",\"凝珍\",\"香萱\",\"梦容\",\"冷雁\",\"飞柏\",\"天真\",\"翠琴\",\"寄真\",\"秋荷\",\"代珊\",\"初雪\",\"雅柏\",\"怜容\",\"如风\",\"南露\",\"紫易\",\"冰凡\",\"海雪\",\"语蓉\",\"碧玉\",\"翠岚\",\"语风\",\"盼丹\",\"痴旋\",\"凝梦\",\"从雪\",\"白枫\",\"傲云\",\"白梅\",\"念露\",\"慕凝\",\"雅柔\",\"盼柳\",\"半青\",\"从霜\",\"怀柔\",\"怜晴\",\"夜蓉\",\"代双\",\"以南\",\"若菱\",\"芷文\",\"寄春\",\"南晴\",\"恨之\",\"梦寒\",\"初翠\",\"灵波\",\"巧春\",\"问夏\",\"凌春 \",\"惜海\"]";

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private FriendshipService friendshipService;

	public static final String TESTPHONE = "18321783963";

	@Test
	public void test() {
//		List<String> array = JSON.parseArray(namesInString, String.class);
//
//		List<Account> accounts = accountMapper.selectByExample(new AccountCriteria());
//
//		Integer half = accounts.size() / 2;
//
//		for (int i = 0; i < accounts.size(); i++) {
//
//			if (i < half) {
//				friendshipService.addFriendShip(TESTPHONE, accounts.get(i).getUsername());
//				friendshipService.addFriendShip(accounts.get(i).getUsername(), TESTPHONE);
//			} else {
//				friendshipService.addFriendRequest(accounts.get(i).getUsername(), TESTPHONE,
//						array.get(i % array.size()));
//			}
//		}

	}

	
	@Test
	public void ttt(){
//		System.out.println(String.format("%04", 2));
	}
}
