package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.UserGeo;
import cn.bangnongmang.server.swagger.model.UserSimple;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 好友的简单信息
 */
@ApiModel(description = "好友的简单信息")

public class FriendSimple extends UserSimple implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("lastLocation")
  private UserGeo lastLocation = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("specail")
  private Boolean specail = null;

  public FriendSimple lastLocation(UserGeo lastLocation) {
    this.lastLocation = lastLocation;
    return this;
  }

   /**
   * Get lastLocation
   * @return lastLocation
  **/
  @ApiModelProperty(value = "")
  public UserGeo getLastLocation() {
    return lastLocation;
  }

  public void setLastLocation(UserGeo lastLocation) {
    this.lastLocation = lastLocation;
  }

  public FriendSimple phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * 用户的手机号，也是用户名
   * @return phone
  **/
  @ApiModelProperty(value = "用户的手机号，也是用户名")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public FriendSimple specail(Boolean specail) {
    this.specail = specail;
    return this;
  }

   /**
   * 是否是特殊好友
   * @return specail
  **/
  @ApiModelProperty(value = "是否是特殊好友")
  public Boolean getSpecail() {
    return specail;
  }

  public void setSpecail(Boolean specail) {
    this.specail = specail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendSimple friendSimple = (FriendSimple) o;
    return Objects.equals(this.lastLocation, friendSimple.lastLocation) &&
        Objects.equals(this.phone, friendSimple.phone) &&
        Objects.equals(this.specail, friendSimple.specail) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastLocation, phone, specail, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendSimple {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    lastLocation: ").append(toIndentedString(lastLocation)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    specail: ").append(toIndentedString(specail)).append("\n");
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

