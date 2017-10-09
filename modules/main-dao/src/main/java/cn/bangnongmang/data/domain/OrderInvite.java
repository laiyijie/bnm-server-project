package cn.bangnongmang.data.domain;

public class OrderInvite {
    private Long id;

    private String farmer_order_id;

    private Long uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmer_order_id() {
        return farmer_order_id;
    }

    public void setFarmer_order_id(String farmer_order_id) {
        this.farmer_order_id = farmer_order_id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}