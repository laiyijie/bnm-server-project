package cn.bangnongmang.data.domain;

public class UserWorkEfficiency {
    private Long id;

    private Long uid;

    private Long working_type_id;

    private Double efficiency;

    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getWorking_type_id() {
        return working_type_id;
    }

    public void setWorking_type_id(Long working_type_id) {
        this.working_type_id = working_type_id;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}