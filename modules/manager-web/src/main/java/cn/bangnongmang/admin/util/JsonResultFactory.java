package cn.bangnongmang.admin.util;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class JsonResultFactory {


	
	public JsonResult makeJsonResult(String status,Object message){
		return new JsonResult(status, message);
	}
	
	public JsonResult showResultObject(Object message){
		return makeJsonResult("done", message);
	}
	
	public String showResultString(Object message){
		return JSON.toJSONString(makeJsonResult("done", message));
	}
	
	public JsonResult showErrorObject(Object message){
		return new JsonResult("error", message);
	}
	
	public String showErrorString(Object message){
		return JSON.toJSONString(new JsonResult("error", message));
	}
}
