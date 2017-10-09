package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.UserSimple;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 好友请求信息
 */
@ApiModel(description = "好友请求信息")

public class FriendRequest extends UserSimple implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("accepted")
  private Boolean accepted = null;

  @JsonProperty("ps")
  private String ps = null;

  @JsonProperty("requestTime")
  private Long requestTime = null;

  public FriendRequest accepted(Boolean accepted) {
    this.accepted = accepted;
    return this;
  }

   /**
   * 是否被接受
   * @return accepted
  **/
  @ApiModelProperty(value = "是否被接受")
  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }

  public FriendRequest ps(String ps) {
    this.ps = ps;
    return this;
  }

   /**
   * 请求带的附言
   * @return ps
  **/
  @ApiModelProperty(value = "请求带的附言")
  public String getPs() {
    return ps;
  }

  public void setPs(String ps) {
    this.ps = ps;
  }

  public FriendRequest requestTime(Long requestTime) {
    this.requestTime = requestTime;
    return this;
  }

   /**
   * 请求发送的时间（单位：ms）
   * @return requestTime
  **/
  @ApiModelProperty(value = "请求发送的时间（单位：ms）")
  public Long getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Long requestTime) {
    this.requestTime = requestTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendRequest friendRequest = (FriendRequest) o;
    return Objects.equals(this.accepted, friendRequest.accepted) &&
        Objects.equals(this.ps, friendRequest.ps) &&
        Objects.equals(this.requestTime, friendRequest.requestTime) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accepted, ps, requestTime, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendRequest {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    accepted: ").append(toIndentedString(accepted)).append("\n");
    sb.append("    ps: ").append(toIndentedString(ps)).append("\n");
    sb.append("    requestTime: ").append(toIndentedString(requestTime)).append("\n");
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

