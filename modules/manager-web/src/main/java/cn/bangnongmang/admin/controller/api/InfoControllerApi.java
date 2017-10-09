package cn.bangnongmang.admin.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("info")
public interface InfoControllerApi {

	@RequestMapping("/testString")
	public String comingString(@RequestParam(value = "name", required = true) String aa);
}
