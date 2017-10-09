package cn.bangnongmang.data.domain;

public class OrderWorkSizeImage {
    private Long id;

    private Long order_farmer_work_size_id;

    private String image_path;

    private String titile;

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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }
}