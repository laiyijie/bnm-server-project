package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * LoginTextResult
 */

public class LoginTextResult  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("nextTime")
  private Long nextTime = null;

  @JsonProperty("registered")
  private Boolean registered = null;

  @JsonProperty("driver")
  private Boolean driver = null;

  public LoginTextResult nextTime(Long nextTime) {
    this.nextTime = nextTime;
    return this;
  }

   /**
   * Get nextTime
   * @return nextTime
  **/
  @ApiModelProperty(value = "")
  public Long getNextTime() {
    return nextTime;
  }

  public void setNextTime(Long nextTime) {
    this.nextTime = nextTime;
  }

  public LoginTextResult registered(Boolean registered) {
    this.registered = registered;
    return this;
  }

   /**
   * 是否是注册用户
   * @return registered
  **/
  @ApiModelProperty(value = "是否是注册用户")
  public Boolean getRegistered() {
    return registered;
  }

  public void setRegistered(Boolean registered) {
    this.registered = registered;
  }

  public LoginTextResult driver(Boolean driver) {
    this.driver = driver;
    return this;
  }

   /**
   * 是否是机手
   * @return driver
  **/
  @ApiModelProperty(value = "是否是机手")
  public Boolean getDriver() {
    return driver;
  }

  public void setDriver(Boolean driver) {
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
    LoginTextResult loginTextResult = (LoginTextResult) o;
    return Objects.equals(this.nextTime, loginTextResult.nextTime) &&
        Objects.equals(this.registered, loginTextResult.registered) &&
        Objects.equals(this.driver, loginTextResult.driver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nextTime, registered, driver);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginTextResult {\n");
    
    sb.append("    nextTime: ").append(toIndentedString(nextTime)).append("\n");
    sb.append("    registered: ").append(toIndentedString(registered)).append("\n");
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

