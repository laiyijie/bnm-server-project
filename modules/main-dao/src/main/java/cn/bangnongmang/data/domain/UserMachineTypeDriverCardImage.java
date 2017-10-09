package cn.bangnongmang.data.domain;

public class UserMachineTypeDriverCardImage {
    private Long id;

    private Long user_machine_type_id;

    private String username;

    private String image_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_machine_type_id() {
        return user_machine_type_id;
    }

    public void setUser_machine_type_id(Long user_machine_type_id) {
        this.user_machine_type_id = user_machine_type_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}