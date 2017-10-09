package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.UserSimple;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 农机手每日的作业信息
 */
@ApiModel(description = "农机手每日的作业信息")

public class OrderDriverWorkInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("orderFarmerWorkInfoId")
  private Long orderFarmerWorkInfoId = null;

  @JsonProperty("size")
  private Double size = null;

  @JsonProperty("driver")
  private UserSimple driver = null;

  public OrderDriverWorkInfo orderFarmerWorkInfoId(Long orderFarmerWorkInfoId) {
    this.orderFarmerWorkInfoId = orderFarmerWorkInfoId;
    return this;
  }

   /**
   * 关联的总作业信息
   * @return orderFarmerWorkInfoId
  **/
  @ApiModelProperty(value = "关联的总作业信息")
  public Long getOrderFarmerWorkInfoId() {
    return orderFarmerWorkInfoId;
  }

  public void setOrderFarmerWorkInfoId(Long orderFarmerWorkInfoId) {
    this.orderFarmerWorkInfoId = orderFarmerWorkInfoId;
  }

  public OrderDriverWorkInfo size(Double size) {
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

  public OrderDriverWorkInfo driver(UserSimple driver) {
    this.driver = driver;
    return this;
  }

   /**
   * Get driver
   * @return driver
  **/
  @ApiModelProperty(value = "")
  public UserSimple getDriver() {
    return driver;
  }

  public void setDriver(UserSimple driver) {
    this.driver = driver;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDriverWorkInfo orderDriverWorkInfo = (OrderDriverWorkInfo) o;
    return Objects.equals(this.orderFarmerWorkInfoId, orderDriverWorkInfo.orderFarmerWorkInfoId) &&
        Objects.equals(this.size, orderDriverWorkInfo.size) &&
        Objects.equals(this.driver, orderDriverWorkInfo.driver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderFarmerWorkInfoId, size, driver);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDriverWorkInfo {\n");
    
    sb.append("    orderFarmerWorkInfoId: ").append(toIndentedString(orderFarmerWorkInfoId)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    driver: ").append(toIndentedString(driver)).append("\n");
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

