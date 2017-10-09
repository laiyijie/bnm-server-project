package cn.bangnongmang.data.domain;

public class OrderDriverWorkSize {
    private Long id;

    private Long order_farmer_work_size_id;

    private Long uid;

    private Double size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_farmer_work_size_id() {
        return order_farmer_work_size_id;
    }

    public void setOrder_farmer_work_size_id(Long order_farmer_work_size_id) {
        this.order_farmer_work_size_id = order_farmer_work_size_id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}