package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.Option;
import cn.bangnongmang.admin.swagger.model.OrderGeo;
import cn.bangnongmang.admin.swagger.model.WorkingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * OrderBasic
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = OrderDetail.class, name = "OrderDetail"),
})

public class OrderBasic  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("farmerName")
  private String farmerName = null;

  @JsonProperty("farmerTel")
  private String farmerTel = null;

  @JsonProperty("orderGeo")
  private OrderGeo orderGeo = null;

  /**
   * Gets or Sets auth
   */
  public enum AuthEnum {
    WAITING_AUTH("WAITING_AUTH"),
    
    AUTHED("AUTHED"),
    
    DENIED("DENIED");

    private String value;

    AuthEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AuthEnum fromValue(String text) {
      for (AuthEnum b : AuthEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("auth")
  private AuthEnum auth = AuthEnum.WAITING_AUTH;

  @JsonProperty("size")
  private Double size = null;

  @JsonProperty("uniPrice")
  private Integer uniPrice = null;

  @JsonProperty("desireNum")
  private Integer desireNum = null;

  @JsonProperty("options")
  private List<Option> options = null;

  @JsonProperty("workingType")
  private WorkingType workingType = null;

  @JsonProperty("desireTime")
  private Long desireTime = null;

  /**
   * OrderStates:   * FARMER_WAITTING_AUTH - 等待认证   * FARMER_WAITTING_PAY - 等待农户支付   * FARMER_WATTING_CHOOSE_FIELD - 等待农户选择地块   * FARMER_WAITTING_GOT - 等待机手接单   * FARMER_GOT - 已被接单   * FARMER_WORKING - 机手正在工作   * FARMER_TODAY_WORKING_STOP - 等待确认当日亩数   * FARMER_WAITTING_SIZE_ENSURE - 等待确认当日亩数   * FARMER_WAITTING_DISTRIBUTE - 等待分配作业面积   * FARMER_TODAY_WORK_FINISHED - 今日作业完成   * FARMER_ALL_FINISHED - 订单全部完成   * FARMER_CANCELED - 订单已经取消   * FARMER_AUTH_FAILED - 订单认证失败 
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

  public OrderBasic orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(value = "")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public OrderBasic farmerName(String farmerName) {
    this.farmerName = farmerName;
    return this;
  }

   /**
   * order's contactor
   * @return farmerName
  **/
  @ApiModelProperty(value = "order's contactor")
  public String getFarmerName() {
    return farmerName;
  }

  public void setFarmerName(String farmerName) {
    this.farmerName = farmerName;
  }

  public OrderBasic farmerTel(String farmerTel) {
    this.farmerTel = farmerTel;
    return this;
  }

   /**
   * order's phoneNumber
   * @return farmerTel
  **/
  @ApiModelProperty(value = "order's phoneNumber")
  public String getFarmerTel() {
    return farmerTel;
  }

  public void setFarmerTel(String farmerTel) {
    this.farmerTel = farmerTel;
  }

  public OrderBasic orderGeo(OrderGeo orderGeo) {
    this.orderGeo = orderGeo;
    return this;
  }

   /**
   * Get orderGeo
   * @return orderGeo
  **/
  @ApiModelProperty(value = "")
  public OrderGeo getOrderGeo() {
    return orderGeo;
  }

  public void setOrderGeo(OrderGeo orderGeo) {
    this.orderGeo = orderGeo;
  }

  public OrderBasic auth(AuthEnum auth) {
    this.auth = auth;
    return this;
  }

   /**
   * Get auth
   * @return auth
  **/
  @ApiModelProperty(value = "")
  public AuthEnum getAuth() {
    return auth;
  }

  public void setAuth(AuthEnum auth) {
    this.auth = auth;
  }

  public OrderBasic size(Double size) {
    this.size = size;
    return this;
  }

   /**
   * Get size
   * @return size
  **/
  @ApiModelProperty(value = "")
  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public OrderBasic uniPrice(Integer uniPrice) {
    this.uniPrice = uniPrice;
    return this;
  }

   /**
   * Get uniPrice
   * @return uniPrice
  **/
  @ApiModelProperty(value = "")
  public Integer getUniPrice() {
    return uniPrice;
  }

  public void setUniPrice(Integer uniPrice) {
    this.uniPrice = uniPrice;
  }

  public OrderBasic desireNum(Integer desireNum) {
    this.desireNum = desireNum;
    return this;
  }

   /**
   * Get desireNum
   * @return desireNum
  **/
  @ApiModelProperty(value = "")
  public Integer getDesireNum() {
    return desireNum;
  }

  public void setDesireNum(Integer desireNum) {
    this.desireNum = desireNum;
  }

  public OrderBasic options(List<Option> options) {
    this.options = options;
    return this;
  }

  public OrderBasic addOptionsItem(Option optionsItem) {
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

  public OrderBasic workingType(WorkingType workingType) {
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

  public OrderBasic desireTime(Long desireTime) {
    this.desireTime = desireTime;
    return this;
  }

   /**
   * Get desireTime
   * @return desireTime
  **/
  @ApiModelProperty(value = "")
  public Long getDesireTime() {
    return desireTime;
  }

  public void setDesireTime(Long desireTime) {
    this.desireTime = desireTime;
  }

  public OrderBasic state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * OrderStates:   * FARMER_WAITTING_AUTH - 等待认证   * FARMER_WAITTING_PAY - 等待农户支付   * FARMER_WATTING_CHOOSE_FIELD - 等待农户选择地块   * FARMER_WAITTING_GOT - 等待机手接单   * FARMER_GOT - 已被接单   * FARMER_WORKING - 机手正在工作   * FARMER_TODAY_WORKING_STOP - 等待确认当日亩数   * FARMER_WAITTING_SIZE_ENSURE - 等待确认当日亩数   * FARMER_WAITTING_DISTRIBUTE - 等待分配作业面积   * FARMER_TODAY_WORK_FINISHED - 今日作业完成   * FARMER_ALL_FINISHED - 订单全部完成   * FARMER_CANCELED - 订单已经取消   * FARMER_AUTH_FAILED - 订单认证失败 
   * @return state
  **/
  @ApiModelProperty(value = "OrderStates:   * FARMER_WAITTING_AUTH - 等待认证   * FARMER_WAITTING_PAY - 等待农户支付   * FARMER_WATTING_CHOOSE_FIELD - 等待农户选择地块   * FARMER_WAITTING_GOT - 等待机手接单   * FARMER_GOT - 已被接单   * FARMER_WORKING - 机手正在工作   * FARMER_TODAY_WORKING_STOP - 等待确认当日亩数   * FARMER_WAITTING_SIZE_ENSURE - 等待确认当日亩数   * FARMER_WAITTING_DISTRIBUTE - 等待分配作业面积   * FARMER_TODAY_WORK_FINISHED - 今日作业完成   * FARMER_ALL_FINISHED - 订单全部完成   * FARMER_CANCELED - 订单已经取消   * FARMER_AUTH_FAILED - 订单认证失败 ")
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
    OrderBasic orderBasic = (OrderBasic) o;
    return Objects.equals(this.orderId, orderBasic.orderId) &&
        Objects.equals(this.farmerName, orderBasic.farmerName) &&
        Objects.equals(this.farmerTel, orderBasic.farmerTel) &&
        Objects.equals(this.orderGeo, orderBasic.orderGeo) &&
        Objects.equals(this.auth, orderBasic.auth) &&
        Objects.equals(this.size, orderBasic.size) &&
        Objects.equals(this.uniPrice, orderBasic.uniPrice) &&
        Objects.equals(this.desireNum, orderBasic.desireNum) &&
        Objects.equals(this.options, orderBasic.options) &&
        Objects.equals(this.workingType, orderBasic.workingType) &&
        Objects.equals(this.desireTime, orderBasic.desireTime) &&
        Objects.equals(this.state, orderBasic.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, farmerName, farmerTel, orderGeo, auth, size, uniPrice, desireNum, options, workingType, desireTime, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderBasic {\n");
    
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    farmerName: ").append(toIndentedString(farmerName)).append("\n");
    sb.append("    farmerTel: ").append(toIndentedString(farmerTel)).append("\n");
    sb.append("    orderGeo: ").append(toIndentedString(orderGeo)).append("\n");
    sb.append("    auth: ").append(toIndentedString(auth)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    uniPrice: ").append(toIndentedString(uniPrice)).append("\n");
    sb.append("    desireNum: ").append(toIndentedString(desireNum)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
    sb.append("    desireTime: ").append(toIndentedString(desireTime)).append("\n");
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

