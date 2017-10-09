package cn.bangnongmang.service.outerservice;


import com.aliyun.oss.OSSClient;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;

public interface AliOssService {

    String HEAD_BUCKET_NAME = "bnm-head";
    String IMG_BUCKET_NAME = "bnm-image";
    String WORK_SIZE_AUTH_BUCKET_NAME = "work-size-auth";
    String RECORD_BUCKET_NAME = "bnm-record";
    String ORDER_IMG_BUCKET_NAME = "bnm-order-image";

    String getPortraitPutUrlFolderByUsername(Long uid);

    String getAuthPutUrlFolderByUsername(Long uid);

    String getPutUrlFolderByString(String id);

    void deleteImage(String headBuketName, String url);

    Credentials getCridentialsByPolicy(String policy, String sessionName);

    OSSClient getCurrOssClient();
}
