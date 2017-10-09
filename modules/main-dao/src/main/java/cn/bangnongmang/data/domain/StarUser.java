package cn.bangnongmang.data.domain;

public class StarUser {
    private Long uid;

    private Double captain_star;

    private Double member_star;

    private Integer captain_evaluations;

    private Integer member_evaluations;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getCaptain_star() {
        return captain_star;
    }

    public void setCaptain_star(Double captain_star) {
        this.captain_star = captain_star;
    }

    public Double getMember_star() {
        return member_star;
    }

    public void setMember_star(Double member_star) {
        this.member_star = member_star;
    }

    public Integer getCaptain_evaluations() {
        return captain_evaluations;
    }

    public void setCaptain_evaluations(Integer captain_evaluations) {
        this.captain_evaluations = captain_evaluations;
    }

    public Integer getMember_evaluations() {
        return member_evaluations;
    }

    public void setMember_evaluations(Integer member_evaluations) {
        this.member_evaluations = member_evaluations;
    }
}