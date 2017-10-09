package cn.bangnongmang.data.domain;

public class PhoneVerify {
    private Integer id_phoneverify;

    private String phonenumber;

    private Long send_time;

    private String verify_code;

    public Integer getId_phoneverify() {
        return id_phoneverify;
    }

    public void setId_phoneverify(Integer id_phoneverify) {
        this.id_phoneverify = id_phoneverify;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Long getSend_time() {
        return send_time;
    }

    public void setSend_time(Long send_time) {
        this.send_time = send_time;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }
}