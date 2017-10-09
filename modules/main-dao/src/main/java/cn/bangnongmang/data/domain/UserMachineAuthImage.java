package cn.bangnongmang.data.domain;

public class UserMachineAuthImage {
    private Long id;

    private Long user_machine_id;

    private String type;

    private String url;

    private Long update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_machine_id() {
        return user_machine_id;
    }

    public void setUser_machine_id(Long user_machine_id) {
        this.user_machine_id = user_machine_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }
}