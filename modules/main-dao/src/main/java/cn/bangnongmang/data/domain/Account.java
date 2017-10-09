package cn.bangnongmang.data.domain;

public class Account {
    private Long uid;

    private String username;

    private String password;

    private String salt_string;

    private String temp_password;

    private Long create_time;

    private Integer state;

    private Long temp_available_time;

    private String onetime_temp_password;

    private Long onetime_available_time;

    private Integer level;

    private String name;

    private String wechat_union_id;

    private String wechat_open_id;

    private String nickname;

    private String idcard_id;

    private String tel;

    private Integer driver_leader;

    private String wechat_info;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

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

    public String getSalt_string() {
        return salt_string;
    }

    public void setSalt_string(String salt_string) {
        this.salt_string = salt_string;
    }

    public String getTemp_password() {
        return temp_password;
    }

    public void setTemp_password(String temp_password) {
        this.temp_password = temp_password;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTemp_available_time() {
        return temp_available_time;
    }

    public void setTemp_available_time(Long temp_available_time) {
        this.temp_available_time = temp_available_time;
    }

    public String getOnetime_temp_password() {
        return onetime_temp_password;
    }

    public void setOnetime_temp_password(String onetime_temp_password) {
        this.onetime_temp_password = onetime_temp_password;
    }

    public Long getOnetime_available_time() {
        return onetime_available_time;
    }

    public void setOnetime_available_time(Long onetime_available_time) {
        this.onetime_available_time = onetime_available_time;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWechat_union_id() {
        return wechat_union_id;
    }

    public void setWechat_union_id(String wechat_union_id) {
        this.wechat_union_id = wechat_union_id;
    }

    public String getWechat_open_id() {
        return wechat_open_id;
    }

    public void setWechat_open_id(String wechat_open_id) {
        this.wechat_open_id = wechat_open_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard_id() {
        return idcard_id;
    }

    public void setIdcard_id(String idcard_id) {
        this.idcard_id = idcard_id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDriver_leader() {
        return driver_leader;
    }

    public void setDriver_leader(Integer driver_leader) {
        this.driver_leader = driver_leader;
    }

    public String getWechat_info() {
        return wechat_info;
    }

    public void setWechat_info(String wechat_info) {
        this.wechat_info = wechat_info;
    }
}