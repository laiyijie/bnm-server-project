package cn.bangnongmang.service.outerservice.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huapay.rxrc.api.AesUtil;
import com.huapay.rxrc.api.ClientReq;
import com.huapay.rxrc.api.ClientRsp;
import com.huapay.rxrc.api.HttpUtil;
import com.huapay.rxrc.api.RsaUtil;

import cn.bangnongmang.service.outerservice.OuterBankCardService;

public class RxBankAuth implements OuterBankCardService {

    private Resource selfPrivateKeyPath;
    private Resource serverPublicKeyPath;
    private String serverUrl;

    private String funcNo;
    private String orgNo;
    private String prodNo;
    private String version;
    private boolean liveMode;
    private final static String SUCCESS_CODE = "0021000";
    private final transient Logger logger = LogManager.getLogger();

    public boolean auth(String pan, String certNo, String name, String tel) throws ServiceLayerExeption {
        if (!liveMode) {
            return true;
        }

        long currTime = System.currentTimeMillis();
        String date = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date(currTime));
        ClientReq req = new ClientReq();
        req.setFuncNo(getFuncNo());
        req.setOrgNo(getOrgNo());
        req.setSeqToken(String.valueOf(currTime));
        req.setSeqTime(Long.parseLong(date));
        req.setVersion(version);
        JSONObject js = new JSONObject();
        js.put("prodNo", prodNo);
        Map<String, String> map = new HashMap<>();
        map.put("pan", pan);
        map.put("certType", "01");
        map.put("certNo", certNo);
        map.put("name", name);
        map.put("cel", tel);
        js.put("prodParams", map);
        req.setReqJson(js.toJSONString());
        ClientRsp rsp = auth(req);
        if (SUCCESS_CODE.equals(rsp.getRspCode())) {
            return true;
        } else {
            BLog.businessJsonLogBuilder("BANK_CARD_SERVICE")
                .addAction("AUTH")
                .put("RSP_CODE", rsp.getRspCode())
                .put("RSP_DESC", rsp.getRspDesc())
                .log();
            throw new ServiceLayerExeption(rsp.getRspDesc());
        }

    }

    public ClientRsp auth(ClientReq req) {
        try {
            String json = JSON.toJSONString(req);// Transfer数据对象转为json字符串
            String clientId = req.getOrgNo(); // TODO 客户端的clientId
            String selfPriKey = RsaUtil.readKey(selfPrivateKeyPath);//
            // 客户端私钥
            String srvPubKey = RsaUtil.readKey(serverPublicKeyPath);//
            // 服务端公钥
            // 准备原始数据
            String reqData = clientId + "|" + json;
            // 生成AES KEY
            String key = AesUtil.createNewKey(AesUtil.AesLevel.LEVEL_128);
            // 生成密文

            String encData = encrpyt(reqData.getBytes("utf-8"), key, selfPriKey, srvPubKey); // 加密
            // 发送和接收
            System.out.println(reqData);
            System.out.println(getServerUrl());
            String encRsp = HttpUtil.post(getServerUrl(), encData); // TODO
            // 设置post地址
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
    private String encrpyt(byte[] data, String aesKey, String selfPriKey, String srvPubKey) throws Exception {
        byte[] bytes = Base64.getDecoder()
                             .decode(aesKey);
        String encKey = RsaUtil.encrpyt(srvPubKey, bytes);
        String encData = AesUtil.createInstance(bytes)
                                .encrypt(data);
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
    private byte[] decrypt(String data, String aesKey, String srvPubKey) throws Exception {
        String[] enc_data_seg = data.split("\\|");
        byte[] bytes = Base64.getDecoder()
                             .decode(aesKey);
        byte[] resData = AesUtil.createInstance(bytes)
                                .decrypt(enc_data_seg[0]);
        boolean isOk = RsaUtil.verify(srvPubKey, resData, enc_data_seg[1]);
        if (isOk)
            return resData;
        else
            return null;
    }

    public Resource getSelfPrivateKeyPath() {
        return selfPrivateKeyPath;
    }

    public void setSelfPrivateKeyPath(Resource selfPrivateKeyPath) {
        this.selfPrivateKeyPath = selfPrivateKeyPath;
    }

    public Resource getServerPublicKeyPath() {
        return serverPublicKeyPath;
    }

    public void setServerPublicKeyPath(Resource serverPublicKeyPath) {
        this.serverPublicKeyPath = serverPublicKeyPath;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getFuncNo() {
        return funcNo;
    }

    public void setFuncNo(String funcNo) {
        this.funcNo = funcNo;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isLiveMode() {
        return liveMode;
    }

    public void setLiveMode(boolean liveMode) {
        this.liveMode = liveMode;
    }
}
