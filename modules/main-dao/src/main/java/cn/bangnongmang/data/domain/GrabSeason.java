package cn.bangnongmang.data.domain;

public class GrabSeason {
    private String id;

    private Long start_time;

    private Long end_time;

    private Double toatalsize;

    private String ps;

    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public Double getToatalsize() {
        return toatalsize;
    }

    public void setToatalsize(Double toatalsize) {
        this.toatalsize = toatalsize;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}