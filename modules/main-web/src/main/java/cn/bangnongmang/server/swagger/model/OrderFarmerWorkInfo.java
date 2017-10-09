package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.OrderDriverWorkInfo;
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
 * 总计的每日作业信息
 */
@ApiModel(description = "总计的每日作业信息")

public class OrderFarmerWorkInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("time")
  private Long time = null;

  @JsonProperty("totalSize")
  private Double totalSize = null;

  /**
   * 作业的确认情况
   */
  public enum StateEnum {
    WAITING_ENSURE("WAITING_ENSURE"),
    
    ENSURED("ENSURED"),
    
    DENIED("DENIED"),
    
    FINISH("FINISH");

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

  @JsonProperty("orderDriverWorkInfos")
  private List<OrderDriverWorkInfo> orderDriverWorkInfos = null;

  public OrderFarmerWorkInfo id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * 唯一标识
   * @return id
  **/
  @ApiModelProperty(value = "唯一标识")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderFarmerWorkInfo orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * 关联订单的ID
   * @return orderId
  **/
  @ApiModelProperty(value = "关联订单的ID")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public OrderFarmerWorkInfo time(Long time) {
    this.time = time;
    return this;
  }

   /**
   * 记录的时间
   * @return time
  **/
  @ApiModelProperty(value = "记录的时间")
  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public OrderFarmerWorkInfo totalSize(Double totalSize) {
    this.totalSize = totalSize;
    return this;
  }

   /**
   * 当天作业的总亩数
   * @return totalSize
  **/
  @ApiModelProperty(value = "当天作业的总亩数")
  public Double getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(Double totalSize) {
    this.totalSize = totalSize;
  }

  public OrderFarmerWorkInfo state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * 作业的确认情况
   * @return state
  **/
  @ApiModelProperty(value = "作业的确认情况")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public OrderFarmerWorkInfo orderDriverWorkInfos(List<OrderDriverWorkInfo> orderDriverWorkInfos) {
    this.orderDriverWorkInfos = orderDriverWorkInfos;
    return this;
  }

  public OrderFarmerWorkInfo addOrderDriverWorkInfosItem(OrderDriverWorkInfo orderDriverWorkInfosItem) {
    if (this.orderDriverWorkInfos == null) {
      this.orderDriverWorkInfos = new ArrayList<OrderDriverWorkInfo>();
    }
    this.orderDriverWorkInfos.add(orderDriverWorkInfosItem);
    return this;
  }

   /**
   * 具体的每一个人的作业情况，只有在分配作业量以后才有
   * @return orderDriverWorkInfos
  **/
  @ApiModelProperty(value = "具体的每一个人的作业情况，只有在分配作业量以后才有")
  public List<OrderDriverWorkInfo> getOrderDriverWorkInfos() {
    return orderDriverWorkInfos;
  }

  public void setOrderDriverWorkInfos(List<OrderDriverWorkInfo> orderDriverWorkInfos) {
    this.orderDriverWorkInfos = orderDriverWorkInfos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderFarmerWorkInfo orderFarmerWorkInfo = (OrderFarmerWorkInfo) o;
    return Objects.equals(this.id, orderFarmerWorkInfo.id) &&
        Objects.equals(this.orderId, orderFarmerWorkInfo.orderId) &&
        Objects.equals(this.time, orderFarmerWorkInfo.time) &&
        Objects.equals(this.totalSize, orderFarmerWorkInfo.totalSize) &&
        Objects.equals(this.state, orderFarmerWorkInfo.state) &&
        Objects.equals(this.orderDriverWorkInfos, orderFarmerWorkInfo.orderDriverWorkInfos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderId, time, totalSize, state, orderDriverWorkInfos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderFarmerWorkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    totalSize: ").append(toIndentedString(totalSize)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    orderDriverWorkInfos: ").append(toIndentedString(orderDriverWorkInfos)).append("\n");
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

