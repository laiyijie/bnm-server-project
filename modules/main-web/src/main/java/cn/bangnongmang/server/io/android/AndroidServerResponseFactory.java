package cn.bangnongmang.server.io.android;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 使用工厂方法创建针对安卓的响应对象（AndroidServerResponseFactory)
 *
 * @author laiyijie
 * @ClassName AndroidServerResponseFactory
 * @date 2016年10月17日 上午10:38:36
 */
@Service
public class AndroidServerResponseFactory implements ServerResponseFactory {

    private transient final Logger logger = LogManager.getLogger(AndroidServerResponseFactory.class);

    @Override
    public String showWithoutError(Object objs) {

        AndroidServerResponse serverResponse = new AndroidServerResponse();
        serverResponse.error = false;
        serverResponse.errorCode = 0;
        serverResponse.errorMessage = null;
        if (objs instanceof String) {
            serverResponse.result = (String) objs;
        } else {
            serverResponse.result = JSON.toJSONString(objs);
        }
        return JSON.toJSONString(serverResponse);
    }

    @Override
    public Object showWithoutErrorObject(Object objs) {

        AndroidServerResponse serverResponse = new AndroidServerResponse();
        serverResponse.error = false;
        serverResponse.errorCode = 0;
        serverResponse.errorMessage = null;
        if (objs instanceof String) {
            serverResponse.result = (String) objs;
        } else {
            serverResponse.result = JSON.toJSONString(objs);
        }
        return serverResponse;
    }

    @Override
    public AndroidServerResponse showErrorObject(int errorCode, String errorMessage) {
        AndroidServerResponse serverResponse = new AndroidServerResponse();
        serverResponse.error = true;
        serverResponse.errorCode = errorCode;
        serverResponse.errorMessage = errorMessage;
        serverResponse.result = null;
        return serverResponse;
    }

    @Override
    public String showError(int errorCode, String errorMessage) {
        AndroidServerResponse serverResponse = new AndroidServerResponse();
        serverResponse.error = true;
        serverResponse.errorCode = errorCode;
        serverResponse.errorMessage = errorMessage;
        serverResponse.result = null;
        String re = JSON.toJSONString(serverResponse);
        return re;
    }

    @Override
    public String showJsonError(int errorCode, String errorMessage) {
        String re = showError(errorCode, errorMessage);
        return re;
    }
}
