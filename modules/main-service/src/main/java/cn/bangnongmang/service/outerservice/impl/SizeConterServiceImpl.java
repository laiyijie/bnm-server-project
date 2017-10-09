package cn.bangnongmang.service.outerservice.impl;

import cn.bangnongmang.server.log.BLog;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.size.io.swagger.ApiClient;
import cn.bangnongmang.size.io.swagger.api.DefaultApi;

import java.io.IOException;

@Service("S_SizeCounterService")
public class SizeConterServiceImpl implements SizeCounterService {

    private DefaultApi api;

    @Value("${size.baseUrl}")
    private String baseUrl;

    @Override
    public DefaultApi getDefaultApi() {
        if (api == null) {
            if (!baseUrl.endsWith("/"))
                baseUrl = baseUrl + "/";
            ApiClient client = new ApiClient();
            client.getAdapterBuilder()
                  .baseUrl(baseUrl);
            client.getOkBuilder()
                  .addInterceptor(new Interceptor() {
                      @Override
                      public Response intercept(Chain chain) throws IOException {
                          BLog.businessJsonLogBuilder("SIZE")
                              .addAction("DEFAULT_API")
                              .put("_URL_", chain.request()
                                                 .url().encodedPath())
                              .put("_HOST_",chain.request().url().host())
                              .log();

                          return chain.proceed(chain.request());
                      }
                  });
            api = client.createService(DefaultApi.class);
            return api;
        }
        return api;
    }

}
