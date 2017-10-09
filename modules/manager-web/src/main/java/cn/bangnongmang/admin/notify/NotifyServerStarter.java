package cn.bangnongmang.admin.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bangnongmang.notify.server.NotifyServer;

@Component
public class NotifyServerStarter {

	@Autowired
	public NotifyServerStarter(NotifyServer notifyServer) {
		notifyServer.start();
	}

}
