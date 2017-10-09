package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.UserDetail;
import cn.bangnongmang.server.swagger.model.UserGeo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * FriendDetail
 */

public class FriendDetail extends UserDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("lastLocation")
  private UserGeo lastLocation = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("specail")
  private Boolean specail = null;

  /**
   * 当前的状态是什么, 空闲，正在组队
   */
  public enum StateEnum {
    IDLE("IDLE"),
    
    IN_TEAM("IN_TEAM");

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
  private StateEnum state = StateEnum.IDLE;

  public FriendDetail lastLocation(UserGeo lastLocation) {
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

  public FriendDetail phone(String phone) {
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

  public FriendDetail specail(Boolean specail) {
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

  public FriendDetail state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * 当前的状态是什么, 空闲，正在组队
   * @return state
  **/
  @ApiModelProperty(value = "当前的状态是什么, 空闲，正在组队")
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
    FriendDetail friendDetail = (FriendDetail) o;
    return Objects.equals(this.lastLocation, friendDetail.lastLocation) &&
        Objects.equals(this.phone, friendDetail.phone) &&
        Objects.equals(this.specail, friendDetail.specail) &&
        Objects.equals(this.state, friendDetail.state) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastLocation, phone, specail, state, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    lastLocation: ").append(toIndentedString(lastLocation)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    specail: ").append(toIndentedString(specail)).append("\n");
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

