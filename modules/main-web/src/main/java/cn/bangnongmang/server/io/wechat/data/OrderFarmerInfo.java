package cn.bangnongmang.server.io.wechat.data;

import java.util.List;

import cn.bangnongmang.data.combo.domain.OptionClusterDetailInfo;

public class OrderFarmerInfo {
	private String order_id;

	private String belongto;

	private String name;

	private String tel;

	private String province;

	private String city;

	private String county;

	private String town;

	private String village;

	private String detail;

	private String crop_type;

	private String service_type;

	private String machine_type;

	private Integer num;

	private Double size;

	private Long start_time;

	private Long end_time;

	private Integer uni_price;

	private Integer pre_money;

	private Integer pre_pay;

	private Integer discount;

	private Integer pre_pay_rate;

	private Integer actual_size;

	private Integer actual_money;

	private String state;

	private Integer authentication;

	private Integer farmer_pay_state;

	private String ps;

	private String wechat_pay_info;

	private String tags;

	private List<OptionClusterDetailInfo> options;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCrop_type() {
		return crop_type;
	}

	public void setCrop_type(String crop_type) {
		this.crop_type = crop_type;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getMachine_type() {
		return machine_type;
	}

	public void setMachine_type(String machine_type) {
		this.machine_type = machine_type;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
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

	public Integer getUni_price() {
		return uni_price;
	}

	public void setUni_price(Integer uni_price) {
		this.uni_price = uni_price;
	}

	public Integer getPre_money() {
		return pre_money;
	}

	public void setPre_money(Integer pre_money) {
		this.pre_money = pre_money;
	}

	public Integer getPre_pay() {
		return pre_pay;
	}

	public void setPre_pay(Integer pre_pay) {
		this.pre_pay = pre_pay;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getPre_pay_rate() {
		return pre_pay_rate;
	}

	public void setPre_pay_rate(Integer pre_pay_rate) {
		this.pre_pay_rate = pre_pay_rate;
	}

	public Integer getActual_size() {
		return actual_size;
	}

	public void setActual_size(Integer actual_size) {
		this.actual_size = actual_size;
	}

	public Integer getActual_money() {
		return actual_money;
	}

	public void setActual_money(Integer actual_money) {
		this.actual_money = actual_money;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}

	public Integer getFarmer_pay_state() {
		return farmer_pay_state;
	}

	public void setFarmer_pay_state(Integer farmer_pay_state) {
		this.farmer_pay_state = farmer_pay_state;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getWechat_pay_info() {
		return wechat_pay_info;
	}

	public void setWechat_pay_info(String wechat_pay_info) {
		this.wechat_pay_info = wechat_pay_info;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<OptionClusterDetailInfo> getOptions() {
		return options;
	}

	public void setOptions(List<OptionClusterDetailInfo> options) {
		this.options = options;
	}
}