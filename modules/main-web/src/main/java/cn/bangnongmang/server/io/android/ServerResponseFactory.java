package cn.bangnongmang.server.io.android;

public interface ServerResponseFactory {

    public String showError(int errorCode, String errorMessage);

    public String showJsonError(int errorCode, String errorMessage) throws Exception;

    public String showWithoutError(Object result);

    Object showWithoutErrorObject(Object objs);

    AndroidServerResponse showErrorObject(int errorCode, String errorMessage);
}
