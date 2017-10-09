package cn.bangnongmang.data.domain;

public class OrderFarmerWorkSize {
    private Long id;

    private Long time;

    private String order_farmer_id;

    private Double size;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getOrder_farmer_id() {
        return order_farmer_id;
    }

    public void setOrder_farmer_id(String order_farmer_id) {
        this.order_farmer_id = order_farmer_id;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}