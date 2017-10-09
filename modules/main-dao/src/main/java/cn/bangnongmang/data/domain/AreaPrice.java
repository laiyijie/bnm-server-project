package cn.bangnongmang.data.domain;

public class AreaPrice {
    private Integer id;

    private String province;

    private String city;

    private String county;

    private String town;

    private Integer uniprice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getUniprice() {
        return uniprice;
    }

    public void setUniprice(Integer uniprice) {
        this.uniprice = uniprice;
    }
}