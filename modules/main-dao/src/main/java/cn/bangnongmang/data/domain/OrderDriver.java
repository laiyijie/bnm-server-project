package cn.bangnongmang.data.domain;

public class OrderDriver {
    private String order_id;

    private String order_farmer_id;

    private Long uid;

    private Long service_start;

    private Long service_end;

    private Double actual_size;

    private Integer actual_money;

    private String state;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_farmer_id() {
        return order_farmer_id;
    }

    public void setOrder_farmer_id(String order_farmer_id) {
        this.order_farmer_id = order_farmer_id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getService_start() {
        return service_start;
    }

    public void setService_start(Long service_start) {
        this.service_start = service_start;
    }

    public Long getService_end() {
        return service_end;
    }

    public void setService_end(Long service_end) {
        this.service_end = service_end;
    }

    public Double getActual_size() {
        return actual_size;
    }

    public void setActual_size(Double actual_size) {
        this.actual_size = actual_size;
    }

    public Integer getActual_money() {
        return actual_money;
    }

    public void setActual_money(Integer actual_money) {
        this.actual_money = actual_money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}