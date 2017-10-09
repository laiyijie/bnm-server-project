package cn.bangnongmang.data.domain;

public class GameCount {
    private String openid;

    private Integer count;

    private Integer hight_score;

    private String nick_name;

    private Long higest_time;

    private Integer recharge_time;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getHight_score() {
        return hight_score;
    }

    public void setHight_score(Integer hight_score) {
        this.hight_score = hight_score;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Long getHigest_time() {
        return higest_time;
    }

    public void setHigest_time(Long higest_time) {
        this.higest_time = higest_time;
    }

    public Integer getRecharge_time() {
        return recharge_time;
    }

    public void setRecharge_time(Integer recharge_time) {
        this.recharge_time = recharge_time;
    }
}