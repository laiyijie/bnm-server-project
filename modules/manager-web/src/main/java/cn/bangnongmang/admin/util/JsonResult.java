package cn.bangnongmang.admin.util;

public class JsonResult {
	
	public Object message;

	public String status;
	

	
	public JsonResult(String status, Object message) {
		this.message = message;
		this.status = status;
	}



	public Object getMessage() {
		return message;
	}



	public void setMessage(Object message) {
		this.message = message;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	


}