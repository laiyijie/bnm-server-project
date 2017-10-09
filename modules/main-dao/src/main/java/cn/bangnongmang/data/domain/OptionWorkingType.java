package cn.bangnongmang.data.domain;

public class OptionWorkingType {
    private Long id;

    private String crop_type;

    private String working_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrop_type() {
        return crop_type;
    }

    public void setCrop_type(String crop_type) {
        this.crop_type = crop_type;
    }

    public String getWorking_type() {
        return working_type;
    }

    public void setWorking_type(String working_type) {
        this.working_type = working_type;
    }
}