package cn.bangnongmang.data.domain;

public class UserMachine {
    private Long id;

    private Long uid;

    private Long machine_model_id;

    private Long buy_time;

    private Integer state;

    private String failed_reason;

    private Double width;

    private Integer integrity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getMachine_model_id() {
        return machine_model_id;
    }

    public void setMachine_model_id(Long machine_model_id) {
        this.machine_model_id = machine_model_id;
    }

    public Long getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(Long buy_time) {
        this.buy_time = buy_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFailed_reason() {
        return failed_reason;
    }

    public void setFailed_reason(String failed_reason) {
        this.failed_reason = failed_reason;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Integer getIntegrity() {
        return integrity;
    }

    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }
}