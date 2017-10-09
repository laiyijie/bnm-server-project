package cn.bangnongmang.server.io.wechat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class WxOutputResponsFactory {

    private transient final Logger logger = LogManager.getLogger(WxOutputResponsFactory.class);

    private static class WxOutputResponse {
        private boolean error;
        private int errorCode;
        private String errorMessage;
        private Object result;

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public WxOutputResponse(int errorCode, String errorMessage) {
            this.error = true;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.result = "";
        }

        public WxOutputResponse(Object result) {
            this.error = false;
            this.errorCode = 0;
            this.errorMessage = "";
            this.result = result;
        }

    }

    public String showError(String errorMessage, int errorCode) {

        WxOutputResponse wxOutputResponse = new WxOutputResponse(errorCode, errorMessage);

        return JSON.toJSONString(wxOutputResponse);
    }

    public String showJsonStringResult(Object object) {

        WxOutputResponse wxOutputResponse = new WxOutputResponse(object);

        return JSON.toJSONString(wxOutputResponse);
    }

    public Object showResult(Object object) {

        return new WxOutputResponse(object);
    }
}
