package cn.bangnongmang.service.outerservice.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

import cn.bangnongmang.service.outerservice.AliOssService;

@Service("S_AliOssService")
public class AliOssServiceImpl implements AliOssService {

    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    // 当前 STS API 版本
    public static final String STS_API_VERSION = "2015-04-01";

    public static final String SALT = "fajldfjadfja";

    private static final transient Logger logger = LogManager.getLogger(AliOssServiceImpl.class);

    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${ali.oss.roleArn}")
    private String roleArn;
    @Value("${ali.oss.portraitFolder}")
    private String portraitFolder;
    @Value("${ali.oss.imageFolder}")
    private String imageFolder;
    @Value("${ali.oss.endpoint}")
    private String ossEndpoint;

    private DefaultAcsClient client;

    private OSSClient ossClient;

    @PostConstruct
    public void init() {

        IClientProfile profile = DefaultProfile
                .getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);

        ossClient = new OSSClient(ossEndpoint, accessKeyId, accessKeySecret);
    }


    private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
                                          String roleArn,
                                          String roleSessionName, String policy,
                                          ProtocolType protocolType) throws ClientException {
        try {

            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(900L);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response;

        } catch (ClientException e) {
            throw e;
        }
    }

    @Override
    public String getPortraitPutUrlFolderByUsername(Long uid) {
        if (uid == null) {
            return portraitFolder + "/default/";
        }

        return portraitFolder + "/" + DigestUtils.md5Hex(SALT + DigestUtils.md5Hex(String
                .valueOf(uid)))
                                                 .substring(0, 16) + "/";
    }

    @Override
    public void deleteImage(String headBuketName, String url) {
        ossClient.deleteObject(headBuketName, url);
    }

    @Override
    public String getAuthPutUrlFolderByUsername(Long uid) {
        if (uid == null) {
            return imageFolder + "/default/";
        }

        return imageFolder + "/" + DigestUtils.md5Hex(SALT + DigestUtils.md5Hex(String.valueOf
                (uid))) + "/";
    }

    @Override
    public String getPutUrlFolderByString(String id) {
        if (id == null || "".equals(id))
            return imageFolder + "/default/";
        return imageFolder + "/" + DigestUtils.md5Hex(SALT + DigestUtils.md5Hex(id)).substring(0, 16) + "/";
    }

    @Override
    public Credentials getCridentialsByPolicy(String policy, String sessionName) {

        String roleSessionName = sessionName;

        ProtocolType protocolType = ProtocolType.HTTPS;
        try {
            final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn,
                    roleSessionName,
                    policy, protocolType);

            return response.getCredentials();
        } catch (ClientException e) {
            logger.error("阿里OSS出错，code:{},message:{}", e.getErrCode(), e.getErrMsg());
        }

        return null;
    }


    public static class PolicyBuilder {
        JSONArray statements;

        public PolicyBuilder() {
            this.statements = new JSONArray();

        }

        public PolicyBuilder addAllowPut(String bucketName, String folderName) {

            folderName = folderName.trim();
            if (!"".equals(folderName) && !"/".equals(folderName.substring(folderName.length() - 1))) {
                folderName = folderName + "/";
            }

            String putResource = "acs:oss:*:*:" + bucketName + "/" + folderName + "*";

            JSONObject stateMent = new JSONObject();

            stateMent.put("Effect", "Allow");
            stateMent.put("Action", "oss:PutObject");

            stateMent.put("Resource", putResource);

            this.statements.add(stateMent);
            return this;
        }

        public PolicyBuilder addAllowGet(String bucketName, String folderName) {
            String getResource = "acs:oss:*:*:" + bucketName + "/" + folderName + "*";

            JSONObject stateMent = new JSONObject();

            stateMent.put("Effect", "Allow");
            stateMent.put("Action", "oss:GetObject");

            stateMent.put("Resource", getResource);

            this.statements.add(stateMent);
            return this;

        }

        public String build() {
            JSONObject policy = new JSONObject();
            policy.put("Version", "1");
            policy.put("Statement", this.statements);
            return policy.toJSONString();
        }

    }


    @Override
    public OSSClient getCurrOssClient() {
        return this.ossClient;
    }


}
