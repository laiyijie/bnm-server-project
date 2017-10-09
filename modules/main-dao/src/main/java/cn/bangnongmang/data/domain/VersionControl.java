package cn.bangnongmang.data.domain;

public class VersionControl {
    private Integer id;

    private Integer main_version;

    private Integer sub_version;

    private Integer bugfix_version;

    private Integer force_update;

    private String update_items;

    private String update_url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMain_version() {
        return main_version;
    }

    public void setMain_version(Integer main_version) {
        this.main_version = main_version;
    }

    public Integer getSub_version() {
        return sub_version;
    }

    public void setSub_version(Integer sub_version) {
        this.sub_version = sub_version;
    }

    public Integer getBugfix_version() {
        return bugfix_version;
    }

    public void setBugfix_version(Integer bugfix_version) {
        this.bugfix_version = bugfix_version;
    }

    public Integer getForce_update() {
        return force_update;
    }

    public void setForce_update(Integer force_update) {
        this.force_update = force_update;
    }

    public String getUpdate_items() {
        return update_items;
    }

    public void setUpdate_items(String update_items) {
        this.update_items = update_items;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }
}