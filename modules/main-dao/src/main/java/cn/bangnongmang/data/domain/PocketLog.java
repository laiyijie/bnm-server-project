package cn.bangnongmang.data.domain;

public class PocketLog {
    private String pocket_log_id;

    private Long uid;

    private Integer type;

    private Long time;

    private Integer money;

    private Integer state;

    private String connect_order_id;

    private String trade_order_id;

    private Integer method;

    private String wechat_pay_info;

    private String detail;

    public String getPocket_log_id() {
        return pocket_log_id;
    }

    public void setPocket_log_id(String pocket_log_id) {
        this.pocket_log_id = pocket_log_id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getConnect_order_id() {
        return connect_order_id;
    }

    public void setConnect_order_id(String connect_order_id) {
        this.connect_order_id = connect_order_id;
    }

    public String getTrade_order_id() {
        return trade_order_id;
    }

    public void setTrade_order_id(String trade_order_id) {
        this.trade_order_id = trade_order_id;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getWechat_pay_info() {
        return wechat_pay_info;
    }

    public void setWechat_pay_info(String wechat_pay_info) {
        this.wechat_pay_info = wechat_pay_info;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}