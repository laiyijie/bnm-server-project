package cn.bangnongmang.data.domain;

public class UserMachineModel {
    private Long id;

    private Integer state;

    private Long user_machine_type_id;

    private String model_brand;

    private String model_number;

    private Double model_width;

    private Double model_power;

    private String special_info;

    private String more_info_url;

    private String width;

    private Double cut_num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUser_machine_type_id() {
        return user_machine_type_id;
    }

    public void setUser_machine_type_id(Long user_machine_type_id) {
        this.user_machine_type_id = user_machine_type_id;
    }

    public String getModel_brand() {
        return model_brand;
    }

    public void setModel_brand(String model_brand) {
        this.model_brand = model_brand;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public Double getModel_width() {
        return model_width;
    }

    public void setModel_width(Double model_width) {
        this.model_width = model_width;
    }

    public Double getModel_power() {
        return model_power;
    }

    public void setModel_power(Double model_power) {
        this.model_power = model_power;
    }

    public String getSpecial_info() {
        return special_info;
    }

    public void setSpecial_info(String special_info) {
        this.special_info = special_info;
    }

    public String getMore_info_url() {
        return more_info_url;
    }

    public void setMore_info_url(String more_info_url) {
        this.more_info_url = more_info_url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Double getCut_num() {
        return cut_num;
    }

    public void setCut_num(Double cut_num) {
        this.cut_num = cut_num;
    }
}