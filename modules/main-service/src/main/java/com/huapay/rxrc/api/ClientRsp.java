package com.huapay.rxrc.api;

/**
 * rxrc响应对象
 * Created by flasfr on 15/11/22.
 */
public class ClientRsp {
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
    private String version = "3.1.0";
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

    /** 响应的json序列化数据 */
    private String rspJson;

    /** 响应码 */
    private String rspCode;

    /** 响应码描述 */
    private String rspDesc;

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

    public String getRspJson() {
        return rspJson;
    }

    public void setRspJson(String rspJson) {
        this.rspJson = rspJson;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspDesc() {
        return rspDesc;
    }

    public void setRspDesc(String rspDesc) {
        this.rspDesc = rspDesc;
    }

	@Override
	public String toString() {
		return "ClientRsp [orgNo=" + orgNo + ", roleNo=" + roleNo + ", userNo=" + userNo + ", funcNo=" + funcNo
				+ ", seqToken=" + seqToken + ", version=" + version + ", seqTime=" + seqTime + ", reserved=" + reserved
				+ ", ext=" + ext + ", rspJson=" + rspJson + ", rspCode=" + rspCode + ", rspDesc=" + rspDesc + "]";
	}
}
