package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.Option;
import cn.bangnongmang.server.swagger.model.OrderGeo;
import cn.bangnongmang.server.swagger.model.OrderTag;
import cn.bangnongmang.server.swagger.model.WorkingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 订单信息
 */
@ApiModel(description = "订单信息")

public class Order  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("farmerName")
  private String farmerName = null;

  @JsonProperty("farmerTel")
  private String farmerTel = null;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("size")
  private Double size = null;

  @JsonProperty("desiredMachineNum")
  private Integer desiredMachineNum = null;

  @JsonProperty("uniPrice")
  private Integer uniPrice = null;

  @JsonProperty("geoInfo")
  private OrderGeo geoInfo = null;

  @JsonProperty("workingType")
  private WorkingType workingType = null;

  @JsonProperty("startTime")
  private Long startTime = null;

  @JsonProperty("gotTime")
  private Long gotTime = null;

  @JsonProperty("actualStartTime")
  private Long actualStartTime = null;

  @JsonProperty("actualEndTime")
  private Long actualEndTime = null;

  @JsonProperty("driverLeader")
  private Long driverLeader = null;

  @JsonProperty("farmerDiscount")
  private Integer farmerDiscount = null;

  @JsonProperty("farmerPrepayRate")
  private Integer farmerPrepayRate = null;

  @JsonProperty("driverInsurance")
  private Integer driverInsurance = null;

  @JsonProperty("leaderBonus")
  private Integer leaderBonus = null;

  @JsonProperty("companyBonus")
  private Integer companyBonus = null;

  /**
   * OrderStates:   * FARMER_WAITTING_AUTH - 等待认证   * FARMER_WAITTING_PAY - 等待农户支付   * FARMER_WATTING_CHOOSE_FIELD - 等待农户选择地块   * FARMER_WAITTING_GOT - 等待机手接单   * FARMER_GOT - 已被接单   * FARMER_WORKING - 机手正在工作   * FARMER_TODAY_WORKING_STOP - 机手作业完成，尚未上传亩数   * FARMER_WAITTING_SIZE_ENSURE - 等待确认当日亩数   * FARMER_WAITTING_DISTRIBUTE - 等待分配作业面积   * FARMER_TODAY_WORK_FINISHED - 今日作业完成   * FARMER_ALL_FINISHED - 订单全部完成   * FARMER_CANCELED - 订单已经取消   * FARMER_AUTH_FAILED - 订单认证失败 
   */
  public enum StateEnum {
    WAITTING_AUTH("FARMER_WAITTING_AUTH"),
    
    WAITTING_PAY("FARMER_WAITTING_PAY"),
    
    WATTING_CHOOSE_FIELD("FARMER_WATTING_CHOOSE_FIELD"),
    
    WAITTING_GOT("FARMER_WAITTING_GOT"),
    
    GOT("FARMER_GOT"),
    
    WORKING("FARMER_WORKING"),
    
    TODAY_WORKING_STOP("FARMER_TODAY_WORKING_STOP"),
    
    WAITTING_SIZE_ENSURE("FARMER_WAITTING_SIZE_ENSURE"),
    
    WAITTING_DISTRIBUTE("FARMER_WAITTING_DISTRIBUTE"),
    
    TODAY_WORK_FINISHED("FARMER_TODAY_WORK_FINISHED"),
    
    ALL_FINISHED("FARMER_ALL_FINISHED"),
    
    CANCELED("FARMER_CANCELED"),
    
    AUTH_FAILED("FARMER_AUTH_FAILED");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("state")
  private StateEnum state = null;

  @JsonProperty("options")
  private List<Option> options = null;

  @JsonProperty("tags")
  private List<OrderTag> tags = null;

  public Order farmerName(String farmerName) {
    this.farmerName = farmerName;
    return this;
  }

   /**
   * 农户的信息
   * @return farmerName
  **/
  @ApiModelProperty(value = "农户的信息")
  public String getFarmerName() {
    return farmerName;
  }

  public void setFarmerName(String farmerName) {
    this.farmerName = farmerName;
  }

  public Order farmerTel(String farmerTel) {
    this.farmerTel = farmerTel;
    return this;
  }

   /**
   * 农户的电话
   * @return farmerTel
  **/
  @ApiModelProperty(value = "农户的电话")
  public String getFarmerTel() {
    return farmerTel;
  }

  public void setFarmerTel(String farmerTel) {
    this.farmerTel = farmerTel;
  }

  public Order uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * 下单的用户id
   * @return uid
  **/
  @ApiModelProperty(value = "下单的用户id")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Order orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * 订单的ID
   * @return orderId
  **/
  @ApiModelProperty(value = "订单的ID")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Order size(Double size) {
    this.size = size;
    return this;
  }

   /**
   * 作业亩数
   * @return size
  **/
  @ApiModelProperty(value = "作业亩数")
  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public Order desiredMachineNum(Integer desiredMachineNum) {
    this.desiredMachineNum = desiredMachineNum;
    return this;
  }

   /**
   * 需求的农机数量
   * @return desiredMachineNum
  **/
  @ApiModelProperty(value = "需求的农机数量")
  public Integer getDesiredMachineNum() {
    return desiredMachineNum;
  }

  public void setDesiredMachineNum(Integer desiredMachineNum) {
    this.desiredMachineNum = desiredMachineNum;
  }

  public Order uniPrice(Integer uniPrice) {
    this.uniPrice = uniPrice;
    return this;
  }

   /**
   * 一亩的价格（单位：分）
   * @return uniPrice
  **/
  @ApiModelProperty(value = "一亩的价格（单位：分）")
  public Integer getUniPrice() {
    return uniPrice;
  }

  public void setUniPrice(Integer uniPrice) {
    this.uniPrice = uniPrice;
  }

  public Order geoInfo(OrderGeo geoInfo) {
    this.geoInfo = geoInfo;
    return this;
  }

   /**
   * Get geoInfo
   * @return geoInfo
  **/
  @ApiModelProperty(value = "")
  public OrderGeo getGeoInfo() {
    return geoInfo;
  }

  public void setGeoInfo(OrderGeo geoInfo) {
    this.geoInfo = geoInfo;
  }

  public Order workingType(WorkingType workingType) {
    this.workingType = workingType;
    return this;
  }

   /**
   * Get workingType
   * @return workingType
  **/
  @ApiModelProperty(value = "")
  public WorkingType getWorkingType() {
    return workingType;
  }

  public void setWorkingType(WorkingType workingType) {
    this.workingType = workingType;
  }

  public Order startTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * 农户希望的开始作业时间（ms）
   * @return startTime
  **/
  @ApiModelProperty(value = "农户希望的开始作业时间（ms）")
  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public Order gotTime(Long gotTime) {
    this.gotTime = gotTime;
    return this;
  }

   /**
   * 抢到单的时间，只有在抢到单以后有效
   * @return gotTime
  **/
  @ApiModelProperty(value = "抢到单的时间，只有在抢到单以后有效")
  public Long getGotTime() {
    return gotTime;
  }

  public void setGotTime(Long gotTime) {
    this.gotTime = gotTime;
  }

  public Order actualStartTime(Long actualStartTime) {
    this.actualStartTime = actualStartTime;
    return this;
  }

   /**
   * 实际开始时间
   * @return actualStartTime
  **/
  @ApiModelProperty(value = "实际开始时间")
  public Long getActualStartTime() {
    return actualStartTime;
  }

  public void setActualStartTime(Long actualStartTime) {
    this.actualStartTime = actualStartTime;
  }

  public Order actualEndTime(Long actualEndTime) {
    this.actualEndTime = actualEndTime;
    return this;
  }

   /**
   * 实际结束时间
   * @return actualEndTime
  **/
  @ApiModelProperty(value = "实际结束时间")
  public Long getActualEndTime() {
    return actualEndTime;
  }

  public void setActualEndTime(Long actualEndTime) {
    this.actualEndTime = actualEndTime;
  }

  public Order driverLeader(Long driverLeader) {
    this.driverLeader = driverLeader;
    return this;
  }

   /**
   * 抢到单以后的队长用户uid
   * @return driverLeader
  **/
  @ApiModelProperty(value = "抢到单以后的队长用户uid")
  public Long getDriverLeader() {
    return driverLeader;
  }

  public void setDriverLeader(Long driverLeader) {
    this.driverLeader = driverLeader;
  }

  public Order farmerDiscount(Integer farmerDiscount) {
    this.farmerDiscount = farmerDiscount;
    return this;
  }

   /**
   * 对农户的打折
   * @return farmerDiscount
  **/
  @ApiModelProperty(value = "对农户的打折")
  public Integer getFarmerDiscount() {
    return farmerDiscount;
  }

  public void setFarmerDiscount(Integer farmerDiscount) {
    this.farmerDiscount = farmerDiscount;
  }

  public Order farmerPrepayRate(Integer farmerPrepayRate) {
    this.farmerPrepayRate = farmerPrepayRate;
    return this;
  }

   /**
   * 农户的预付比例（1-100）
   * @return farmerPrepayRate
  **/
  @ApiModelProperty(value = "农户的预付比例（1-100）")
  public Integer getFarmerPrepayRate() {
    return farmerPrepayRate;
  }

  public void setFarmerPrepayRate(Integer farmerPrepayRate) {
    this.farmerPrepayRate = farmerPrepayRate;
  }

  public Order driverInsurance(Integer driverInsurance) {
    this.driverInsurance = driverInsurance;
    return this;
  }

   /**
   * 接单的农机手需要交的保证金（单位：分）
   * @return driverInsurance
  **/
  @ApiModelProperty(value = "接单的农机手需要交的保证金（单位：分）")
  public Integer getDriverInsurance() {
    return driverInsurance;
  }

  public void setDriverInsurance(Integer driverInsurance) {
    this.driverInsurance = driverInsurance;
  }

  public Order leaderBonus(Integer leaderBonus) {
    this.leaderBonus = leaderBonus;
    return this;
  }

   /**
   * 队长每亩的作业提成,取值范围（0-10000）意味着万分之几
   * @return leaderBonus
  **/
  @ApiModelProperty(value = "队长每亩的作业提成,取值范围（0-10000）意味着万分之几")
  public Integer getLeaderBonus() {
    return leaderBonus;
  }

  public void setLeaderBonus(Integer leaderBonus) {
    this.leaderBonus = leaderBonus;
  }

  public Order companyBonus(Integer companyBonus) {
    this.companyBonus = companyBonus;
    return this;
  }

   /**
   * 公司每亩作业的提成,取值范围（0-10000）意味着万分之几
   * @return companyBonus
  **/
  @ApiModelProperty(value = "公司每亩作业的提成,取值范围（0-10000）意味着万分之几")
  public Integer getCompanyBonus() {
    return companyBonus;
  }

  public void setCompanyBonus(Integer companyBonus) {
    this.companyBonus = companyBonus;
  }

  public Order state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * OrderStates:   * FARMER_WAITTING_AUTH - 等待认证   * FARMER_WAITTING_PAY - 等待农户支付   * FARMER_WATTING_CHOOSE_FIELD - 等待农户选择地块   * FARMER_WAITTING_GOT - 等待机手接单   * FARMER_GOT - 已被接单   * FARMER_WORKING - 机手正在工作   * FARMER_TODAY_WORKING_STOP - 机手作业完成，尚未上传亩数   * FARMER_WAITTING_SIZE_ENSURE - 等待确认当日亩数   * FARMER_WAITTING_DISTRIBUTE - 等待分配作业面积   * FARMER_TODAY_WORK_FINISHED - 今日作业完成   * FARMER_ALL_FINISHED - 订单全部完成   * FARMER_CANCELED - 订单已经取消   * FARMER_AUTH_FAILED - 订单认证失败 
   * @return state
  **/
  @ApiModelProperty(value = "OrderStates:   * FARMER_WAITTING_AUTH - 等待认证   * FARMER_WAITTING_PAY - 等待农户支付   * FARMER_WATTING_CHOOSE_FIELD - 等待农户选择地块   * FARMER_WAITTING_GOT - 等待机手接单   * FARMER_GOT - 已被接单   * FARMER_WORKING - 机手正在工作   * FARMER_TODAY_WORKING_STOP - 机手作业完成，尚未上传亩数   * FARMER_WAITTING_SIZE_ENSURE - 等待确认当日亩数   * FARMER_WAITTING_DISTRIBUTE - 等待分配作业面积   * FARMER_TODAY_WORK_FINISHED - 今日作业完成   * FARMER_ALL_FINISHED - 订单全部完成   * FARMER_CANCELED - 订单已经取消   * FARMER_AUTH_FAILED - 订单认证失败 ")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public Order options(List<Option> options) {
    this.options = options;
    return this;
  }

  public Order addOptionsItem(Option optionsItem) {
    if (this.options == null) {
      this.options = new ArrayList<Option>();
    }
    this.options.add(optionsItem);
    return this;
  }

   /**
   * Get options
   * @return options
  **/
  @ApiModelProperty(value = "")
  public List<Option> getOptions() {
    return options;
  }

  public void setOptions(List<Option> options) {
    this.options = options;
  }

  public Order tags(List<OrderTag> tags) {
    this.tags = tags;
    return this;
  }

  public Order addTagsItem(OrderTag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<OrderTag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * tags
   * @return tags
  **/
  @ApiModelProperty(value = "tags")
  public List<OrderTag> getTags() {
    return tags;
  }

  public void setTags(List<OrderTag> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.farmerName, order.farmerName) &&
        Objects.equals(this.farmerTel, order.farmerTel) &&
        Objects.equals(this.uid, order.uid) &&
        Objects.equals(this.orderId, order.orderId) &&
        Objects.equals(this.size, order.size) &&
        Objects.equals(this.desiredMachineNum, order.desiredMachineNum) &&
        Objects.equals(this.uniPrice, order.uniPrice) &&
        Objects.equals(this.geoInfo, order.geoInfo) &&
        Objects.equals(this.workingType, order.workingType) &&
        Objects.equals(this.startTime, order.startTime) &&
        Objects.equals(this.gotTime, order.gotTime) &&
        Objects.equals(this.actualStartTime, order.actualStartTime) &&
        Objects.equals(this.actualEndTime, order.actualEndTime) &&
        Objects.equals(this.driverLeader, order.driverLeader) &&
        Objects.equals(this.farmerDiscount, order.farmerDiscount) &&
        Objects.equals(this.farmerPrepayRate, order.farmerPrepayRate) &&
        Objects.equals(this.driverInsurance, order.driverInsurance) &&
        Objects.equals(this.leaderBonus, order.leaderBonus) &&
        Objects.equals(this.companyBonus, order.companyBonus) &&
        Objects.equals(this.state, order.state) &&
        Objects.equals(this.options, order.options) &&
        Objects.equals(this.tags, order.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(farmerName, farmerTel, uid, orderId, size, desiredMachineNum, uniPrice, geoInfo, workingType, startTime, gotTime, actualStartTime, actualEndTime, driverLeader, farmerDiscount, farmerPrepayRate, driverInsurance, leaderBonus, companyBonus, state, options, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    farmerName: ").append(toIndentedString(farmerName)).append("\n");
    sb.append("    farmerTel: ").append(toIndentedString(farmerTel)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    desiredMachineNum: ").append(toIndentedString(desiredMachineNum)).append("\n");
    sb.append("    uniPrice: ").append(toIndentedString(uniPrice)).append("\n");
    sb.append("    geoInfo: ").append(toIndentedString(geoInfo)).append("\n");
    sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    gotTime: ").append(toIndentedString(gotTime)).append("\n");
    sb.append("    actualStartTime: ").append(toIndentedString(actualStartTime)).append("\n");
    sb.append("    actualEndTime: ").append(toIndentedString(actualEndTime)).append("\n");
    sb.append("    driverLeader: ").append(toIndentedString(driverLeader)).append("\n");
    sb.append("    farmerDiscount: ").append(toIndentedString(farmerDiscount)).append("\n");
    sb.append("    farmerPrepayRate: ").append(toIndentedString(farmerPrepayRate)).append("\n");
    sb.append("    driverInsurance: ").append(toIndentedString(driverInsurance)).append("\n");
    sb.append("    leaderBonus: ").append(toIndentedString(leaderBonus)).append("\n");
    sb.append("    companyBonus: ").append(toIndentedString(companyBonus)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

