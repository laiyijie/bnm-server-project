package cn.bangnongmang.server.io.android;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用于解析与安卓对接的请求数据，POJO
 *
 * @author laiyijie
 * @ClassName AndroidRequest
 * @date 2016年10月17日 上午10:36:43
 */
public class AndroidRequest {

    public String params;

    private transient final Logger logger = LogManager.getLogger(AndroidRequest.class);

    public Map<String, Object> resolveJsonParams() throws AndroidOutputException {


        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> re;
        try {
            re = mapper.readValue(params, Map.class);

            return re;
        } catch (IOException e) {
            throw new AndroidOutputException(400, "入参错误");
        }
    }

    public <T> Object resolveJsonParams(TypeReference<T> type) throws AndroidOutputException {

        ObjectMapper mapper = new ObjectMapper();
        Object re;
        try {
            re = mapper.readValue(params, type);

            return re;
        } catch (IOException e) {
            throw new AndroidOutputException(400, "入参错误");
        }
    }

    public <T> T resolveJsonParams(Class<T> clazz) {

        return JSON.parseObject(params, clazz);
    }
}
