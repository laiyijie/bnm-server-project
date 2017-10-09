package com.huapay.rxrc.api;

import com.alibaba.fastjson.JSON;

import java.util.Base64;

/**
 * rxrc客户端api v3.0
 * Created by flasfr on 15/11/22.
 */
public class RxrcClient {

    public static ClientRsp action(ClientReq req) throws Exception {
        String json = JSON.toJSONString(req);// Transfer数据对象转为json字符串
        String clientId = req.getOrgNo(); // 
        String selfPriKey = RsaUtil.readKey("./res/00000037_pri.key");// 
        String srvPubKey = RsaUtil.readKey("./res/rxrc_pub.key");// 
        // 准备原始数据
        String reqData = clientId + "|" + json;
        // 生成AES KEY
        String key = AesUtil.createNewKey(AesUtil.AesLevel.LEVEL_128);
        // 生成密文
        try {
            String encData = encrpyt(reqData.getBytes("utf-8"), key, selfPriKey, srvPubKey); // 加密
            // 发送和接收
            String encRsp = HttpUtil.post("http://106.37.208.102:8096/r5", encData); // 
            // 报文解密
            byte[] rsp_bytes = decrypt(encRsp, key, srvPubKey); // 解密
            String rspJson = new String(rsp_bytes, "utf-8");
            return JSON.parseObject(rspJson, ClientRsp.class);// 反序列化
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 数据加密
     *
     * @param data       报文明文
     * @param aesKey     临时通信密钥
     * @param selfPriKey 客户端私钥证书
     * @param srvPubKey  服务端公钥证书
     * @return 报文密文
     */
    private static String encrpyt(byte[] data, String aesKey, String selfPriKey, String srvPubKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(aesKey);
        String encKey = RsaUtil.encrpyt(srvPubKey, bytes);
        String encData = AesUtil.createInstance(bytes).encrypt(data);
        String sign = RsaUtil.sign(selfPriKey, data);
        return encKey + "|" + encData + "|" + sign;
    }

    /**
     * 数据解密
     *
     * @param data      报文密文
     * @param aesKey    临时通信密钥
     * @param srvPubKey 服务端公钥证书
     * @return 报文明文
     */
    private static byte[] decrypt(String data, String aesKey, String srvPubKey) throws Exception {
        String[] enc_data_seg = data.split("\\|");
        byte[] bytes = Base64.getDecoder().decode(aesKey);
        byte[] resData = AesUtil.createInstance(bytes).decrypt(enc_data_seg[0]);
        boolean isOk = RsaUtil.verify(srvPubKey, resData, enc_data_seg[1]);
        if (isOk)
            return resData;
        else
            return null;
    }
}
