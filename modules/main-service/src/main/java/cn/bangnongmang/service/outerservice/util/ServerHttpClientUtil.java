package cn.bangnongmang.service.outerservice.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class ServerHttpClientUtil {

	private OkHttpClient client;

	private Logger logger = LogManager.getLogger(ServerHttpClientUtil.class);

	public ServerHttpClientUtil() {
		client = new OkHttpClient();
	}

	public String post(String url) {
		Request.Builder rBuilder = new Request.Builder();
		RequestBody requestBody = new FormBody.Builder().build();
		Request request = rBuilder.url(url).post(requestBody).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String data = response.body().string();
				logger.debug(url);
				logger.debug(data);

				return data;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("post:" + url + ":failed");
			return null;
		}
		return null;
	}
	
	public String postJson(String url,String postData) {
		Request.Builder rBuilder = new Request.Builder();
		RequestBody requestBody = RequestBody.create(MediaType.parse("JSON"), postData);
		Request request = rBuilder.url(url).post(requestBody).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String data = response.body().string();
				logger.debug(url);
				logger.debug(data);

				return data;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("post:" + url + ":failed");
			return null;
		}
		return null;
	}

}
