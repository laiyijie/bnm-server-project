package com.huapay.rxrc.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Date;

/**
 * httpclent 工具类
 * User: flasfr
 * Date: 13-6-6
 * Time: 下午3:42
 */
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * http post 工具类，超时时间都是5秒
     *
     * @param url  post 地址
     * @param data post 数据
     * @return 响应信息
     * @throws Exception
     */
    public static String post(String url, String data) throws Exception {
        return post(url, data, 60000, 60000, 60000);
    }

    /**
     * httppost请求
     *
     * @param url          请求地址
     * @param data         请求数据
     * @param so_timeout   socket超时时间
     * @param req_timeout  连接请求超时时间
     * @param conn_timeout 响应等待超时时间
     * @return 响应信息
     * @throws Exception
     */
    public static String post(String url, String data, int so_timeout, int req_timeout, int conn_timeout) throws Exception {
        HttpPost post = new HttpPost(url);
        StringEntity reqEntity = new StringEntity(data, "utf-8");
        post.setEntity(reqEntity);
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig reqCfg = RequestConfig.custom().setSocketTimeout(so_timeout).setConnectionRequestTimeout(req_timeout).setConnectTimeout(conn_timeout).build();
        post.setConfig(reqCfg);
        long start = new Date().getTime();
        HttpResponse resp = client.execute(post);
        long end = new Date().getTime();
        System.out.println("接近耗时:" + (end - start));
        try {
            String respStr = EntityUtils.toString(resp.getEntity());
            return respStr;
        } finally {
            post.releaseConnection();
            client.close();
        }
    }
}
