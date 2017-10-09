package cn.bangnongmang.data.domain;

public class Friendship {
    private Long id;

    private Long usera;

    private Long userb;

    private Long create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsera() {
        return usera;
    }

    public void setUsera(Long usera) {
        this.usera = usera;
    }

    public Long getUserb() {
        return userb;
    }

    public void setUserb(Long userb) {
        this.userb = userb;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}