package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.Option;
import cn.bangnongmang.server.swagger.model.WorkingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * UserMachine
 */

public class UserMachine  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("brand")
  private String brand = null;

  @JsonProperty("number")
  private String number = null;

  @JsonProperty("buyTime")
  private Long buyTime = null;

  @JsonProperty("state")
  private Integer state = null;

  @JsonProperty("failReason")
  private String failReason = null;

  @JsonProperty("supportWorkingTypes")
  private List<WorkingType> supportWorkingTypes = null;

  @JsonProperty("options")
  private List<Option> options = null;

  public UserMachine id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * 车辆的唯一ID
   * @return id
  **/
  @ApiModelProperty(value = "车辆的唯一ID")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserMachine type(String type) {
    this.type = type;
    return this;
  }

   /**
   * 车辆的类型（如，收割机等）
   * @return type
  **/
  @ApiModelProperty(value = "车辆的类型（如，收割机等）")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UserMachine brand(String brand) {
    this.brand = brand;
    return this;
  }

   /**
   * 车辆的品牌（如：雷沃）
   * @return brand
  **/
  @ApiModelProperty(value = "车辆的品牌（如：雷沃）")
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public UserMachine number(String number) {
    this.number = number;
    return this;
  }

   /**
   * 车辆的型号（如：988）
   * @return number
  **/
  @ApiModelProperty(value = "车辆的型号（如：988）")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public UserMachine buyTime(Long buyTime) {
    this.buyTime = buyTime;
    return this;
  }

   /**
   * 购买时间
   * @return buyTime
  **/
  @ApiModelProperty(value = "购买时间")
  public Long getBuyTime() {
    return buyTime;
  }

  public void setBuyTime(Long buyTime) {
    this.buyTime = buyTime;
  }

  public UserMachine state(Integer state) {
    this.state = state;
    return this;
  }

   /**
   * 认证的状态
   * @return state
  **/
  @ApiModelProperty(value = "认证的状态")
  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public UserMachine failReason(String failReason) {
    this.failReason = failReason;
    return this;
  }

   /**
   * 认证失败的原因
   * @return failReason
  **/
  @ApiModelProperty(value = "认证失败的原因")
  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }

  public UserMachine supportWorkingTypes(List<WorkingType> supportWorkingTypes) {
    this.supportWorkingTypes = supportWorkingTypes;
    return this;
  }

  public UserMachine addSupportWorkingTypesItem(WorkingType supportWorkingTypesItem) {
    if (this.supportWorkingTypes == null) {
      this.supportWorkingTypes = new ArrayList<WorkingType>();
    }
    this.supportWorkingTypes.add(supportWorkingTypesItem);
    return this;
  }

   /**
   * 支持的作物类型和工种
   * @return supportWorkingTypes
  **/
  @ApiModelProperty(value = "支持的作物类型和工种")
  public List<WorkingType> getSupportWorkingTypes() {
    return supportWorkingTypes;
  }

  public void setSupportWorkingTypes(List<WorkingType> supportWorkingTypes) {
    this.supportWorkingTypes = supportWorkingTypes;
  }

  public UserMachine options(List<Option> options) {
    this.options = options;
    return this;
  }

  public UserMachine addOptionsItem(Option optionsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserMachine userMachine = (UserMachine) o;
    return Objects.equals(this.id, userMachine.id) &&
        Objects.equals(this.type, userMachine.type) &&
        Objects.equals(this.brand, userMachine.brand) &&
        Objects.equals(this.number, userMachine.number) &&
        Objects.equals(this.buyTime, userMachine.buyTime) &&
        Objects.equals(this.state, userMachine.state) &&
        Objects.equals(this.failReason, userMachine.failReason) &&
        Objects.equals(this.supportWorkingTypes, userMachine.supportWorkingTypes) &&
        Objects.equals(this.options, userMachine.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, brand, number, buyTime, state, failReason, supportWorkingTypes, options);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserMachine {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    buyTime: ").append(toIndentedString(buyTime)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    failReason: ").append(toIndentedString(failReason)).append("\n");
    sb.append("    supportWorkingTypes: ").append(toIndentedString(supportWorkingTypes)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

