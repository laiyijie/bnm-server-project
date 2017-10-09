package cn.bangnongmang.data.domain;

public class Pocket {
    private Long uid;

    private Integer curr_money;

    private Integer provisions;

    private Integer arrears;

    private Integer credit;

    private Integer insurance;

    private Integer waitting_in;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getCurr_money() {
        return curr_money;
    }

    public void setCurr_money(Integer curr_money) {
        this.curr_money = curr_money;
    }

    public Integer getProvisions() {
        return provisions;
    }

    public void setProvisions(Integer provisions) {
        this.provisions = provisions;
    }

    public Integer getArrears() {
        return arrears;
    }

    public void setArrears(Integer arrears) {
        this.arrears = arrears;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getInsurance() {
        return insurance;
    }

    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }

    public Integer getWaitting_in() {
        return waitting_in;
    }

    public void setWaitting_in(Integer waitting_in) {
        this.waitting_in = waitting_in;
    }
}