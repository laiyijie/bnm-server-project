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
 * 入队请求
 */
@ApiModel(description = "入队请求")

public class TeamJoinRequest  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("user")
  private UserSimple user = null;

  @JsonProperty("ps")
  private String ps = null;

  public TeamJoinRequest user(UserSimple user) {
    this.user = user;
    return this;
  }

   /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")
  public UserSimple getUser() {
    return user;
  }

  public void setUser(UserSimple user) {
    this.user = user;
  }

  public TeamJoinRequest ps(String ps) {
    this.ps = ps;
    return this;
  }

   /**
   * 请求的信息
   * @return ps
  **/
  @ApiModelProperty(value = "请求的信息")
  public String getPs() {
    return ps;
  }

  public void setPs(String ps) {
    this.ps = ps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamJoinRequest teamJoinRequest = (TeamJoinRequest) o;
    return Objects.equals(this.user, teamJoinRequest.user) &&
        Objects.equals(this.ps, teamJoinRequest.ps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, ps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamJoinRequest {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    ps: ").append(toIndentedString(ps)).append("\n");
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

