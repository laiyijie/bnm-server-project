package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 子订单，接单以后每个农机手对应一个子订单
 */
@ApiModel(description = "子订单，接单以后每个农机手对应一个子订单")

public class SubOrder  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("startTime")
  private Long startTime = null;

  @JsonProperty("endTime")
  private Long endTime = null;

  @JsonProperty("actualSize")
  private Double actualSize = null;

  @JsonProperty("actualMoney")
  private Integer actualMoney = null;

  /**
   * 当前的状态
   */
  public enum StateEnum {
    GOT("GOT"),
    
    WORKING("WORKING"),
    
    STOPPED("STOPPED"),
    
    FINISHED("FINISHED");

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

  public SubOrder orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * 对应的订单ID
   * @return orderId
  **/
  @ApiModelProperty(value = "对应的订单ID")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public SubOrder id(String id) {
    this.id = id;
    return this;
  }

   /**
   * 唯一的标识
   * @return id
  **/
  @ApiModelProperty(value = "唯一的标识")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SubOrder uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * 子订单的所有者
   * @return uid
  **/
  @ApiModelProperty(value = "子订单的所有者")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public SubOrder startTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * 作业开始的时间
   * @return startTime
  **/
  @ApiModelProperty(value = "作业开始的时间")
  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public SubOrder endTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * 作业结束的时间
   * @return endTime
  **/
  @ApiModelProperty(value = "作业结束的时间")
  public Long getEndTime() {
    return endTime;
  }

  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  public SubOrder actualSize(Double actualSize) {
    this.actualSize = actualSize;
    return this;
  }

   /**
   * 总共的作业亩数
   * @return actualSize
  **/
  @ApiModelProperty(value = "总共的作业亩数")
  public Double getActualSize() {
    return actualSize;
  }

  public void setActualSize(Double actualSize) {
    this.actualSize = actualSize;
  }

  public SubOrder actualMoney(Integer actualMoney) {
    this.actualMoney = actualMoney;
    return this;
  }

   /**
   * 总共获取的作业款
   * @return actualMoney
  **/
  @ApiModelProperty(value = "总共获取的作业款")
  public Integer getActualMoney() {
    return actualMoney;
  }

  public void setActualMoney(Integer actualMoney) {
    this.actualMoney = actualMoney;
  }

  public SubOrder state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * 当前的状态
   * @return state
  **/
  @ApiModelProperty(value = "当前的状态")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubOrder subOrder = (SubOrder) o;
    return Objects.equals(this.orderId, subOrder.orderId) &&
        Objects.equals(this.id, subOrder.id) &&
        Objects.equals(this.uid, subOrder.uid) &&
        Objects.equals(this.startTime, subOrder.startTime) &&
        Objects.equals(this.endTime, subOrder.endTime) &&
        Objects.equals(this.actualSize, subOrder.actualSize) &&
        Objects.equals(this.actualMoney, subOrder.actualMoney) &&
        Objects.equals(this.state, subOrder.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, id, uid, startTime, endTime, actualSize, actualMoney, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubOrder {\n");
    
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    actualSize: ").append(toIndentedString(actualSize)).append("\n");
    sb.append("    actualMoney: ").append(toIndentedString(actualMoney)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

