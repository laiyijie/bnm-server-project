package cn.bangnongmang.data.domain;

public class FriendshipRequest {
    private Long id;

    private Long requester;

    private Long responser;

    private Integer state;

    private Long create_time;

    private String ps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequester() {
        return requester;
    }

    public void setRequester(Long requester) {
        this.requester = requester;
    }

    public Long getResponser() {
        return responser;
    }

    public void setResponser(Long responser) {
        this.responser = responser;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}