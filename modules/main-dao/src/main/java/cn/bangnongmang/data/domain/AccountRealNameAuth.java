package cn.bangnongmang.data.domain;

public class AccountRealNameAuth {
    private Long uid;

    private Long update_time;

    private Integer state;

    private String id_card_number;

    private String real_name;

    private String failed_reason;

    private String down_side;

    private String up_side;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getFailed_reason() {
        return failed_reason;
    }

    public void setFailed_reason(String failed_reason) {
        this.failed_reason = failed_reason;
    }

    public String getDown_side() {
        return down_side;
    }

    public void setDown_side(String down_side) {
        this.down_side = down_side;
    }

    public String getUp_side() {
        return up_side;
    }

    public void setUp_side(String up_side) {
        this.up_side = up_side;
    }
}