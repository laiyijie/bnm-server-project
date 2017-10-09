package cn.bangnongmang.admin.data.domain;

public class AdminAccount {
    private String username;

    private String password;

    private Integer login_failed_time;

    private Long last_failed_time;

    private String verify_code;

    private String superior;

    private String name;

    private String phone;

    private Integer level;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLogin_failed_time() {
        return login_failed_time;
    }

    public void setLogin_failed_time(Integer login_failed_time) {
        this.login_failed_time = login_failed_time;
    }

    public Long getLast_failed_time() {
        return last_failed_time;
    }

    public void setLast_failed_time(Long last_failed_time) {
        this.last_failed_time = last_failed_time;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}