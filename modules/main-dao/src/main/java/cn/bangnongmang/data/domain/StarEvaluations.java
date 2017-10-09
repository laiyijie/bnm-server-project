package cn.bangnongmang.data.domain;

public class StarEvaluations {
    private Long id;

    private Long valutor;

    private Long valuted;

    private Double star;

    private Long time;

    private String order_id;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValutor() {
        return valutor;
    }

    public void setValutor(Long valutor) {
        this.valutor = valutor;
    }

    public Long getValuted() {
        return valuted;
    }

    public void setValuted(Long valuted) {
        this.valuted = valuted;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}