package cn.bangnongmang.data.domain;

public class UserMachineType {
    private Long id;

    private String type_name;

    private String descripetion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDescripetion() {
        return descripetion;
    }

    public void setDescripetion(String descripetion) {
        this.descripetion = descripetion;
    }
}