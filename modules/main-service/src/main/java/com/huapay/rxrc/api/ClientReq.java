package com.huapay.rxrc.api;

/**
 * 在请求报文基础上追加附加信息
 * Created by flasfr on 15/11/22.
 */
public final class ClientReq {

    /** 机构号 */
    private String orgNo;

    /** 角色编号 */
    private String roleNo;

    /** 用户号 */
    private String userNo;

    /** 调用的功能编号 */
    private String funcNo;

    /** 请求的唯一标识 */
    private String seqToken;

    /** 协议版本号 */
    private String version = "1.1.0";
    /**
     * 请求的时间
     * 格式:yyyyMMddHHmmss
     */
    private long seqTime;

    /**
     * 请求报文保留域
     * 改域由请求者自定义,响应报文中原样返回
     */
    private String reserved;

    /**
     * 请求报文扩展域
     * 用于在以后的接口扩展中使用
     */
    private String ext;

    /** 请求的json序列化数据 */
    private String reqJson;

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getFuncNo() {
        return funcNo;
    }

    public void setFuncNo(String funcNo) {
        this.funcNo = funcNo;
    }

    public String getSeqToken() {
        return seqToken;
    }

    public void setSeqToken(String seqToken) {
        this.seqToken = seqToken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getSeqTime() {
        return seqTime;
    }

    public void setSeqTime(long seqTime) {
        this.seqTime = seqTime;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getReqJson() {
        return reqJson;
    }

    public void setReqJson(String reqJson) {
        this.reqJson = reqJson;
    }

	@Override
	public String toString() {
		return "ClientReq [orgNo=" + orgNo + ", roleNo=" + roleNo + ", userNo=" + userNo + ", funcNo=" + funcNo
				+ ", seqToken=" + seqToken + ", version=" + version + ", seqTime=" + seqTime + ", reserved=" + reserved
				+ ", ext=" + ext + ", reqJson=" + reqJson + "]";
	}
}
