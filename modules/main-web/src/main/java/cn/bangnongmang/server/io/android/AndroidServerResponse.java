package cn.bangnongmang.server.io.android;


/**
 * 
 * 针对安卓的返回数据 
 * @ClassName AndroidServerResponse 
 * @author laiyijie
 * @date 2016年10月17日 上午10:37:52 
 *
 */

public class AndroidServerResponse  {
	@Override
	public String toString() {
		return "AndroidServerResponse [error=" + error + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ ", result=" + result + "]";
	}
	public boolean error;
	public int errorCode;
	public String errorMessage;
	public String result;
}
