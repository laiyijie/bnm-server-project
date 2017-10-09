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
 * AccountAuth
 */

public class AccountAuth  implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 认证状态
   */
  public enum StateEnum {
    INIT("REAL_NAME_AUTH_STATE_INIT"),
    
    WAITTING_AUTH("REAL_NAME_AUTH_STATE_WAITTING_AUTH"),
    
    PASS("REAL_NAME_AUTH_STATE_PASS");

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

  @JsonProperty("realName")
  private String realName = null;

  @JsonProperty("idcCardNumber")
  private String idcCardNumber = null;

  @JsonProperty("failedReason")
  private String failedReason = null;

  @JsonProperty("downSide")
  private String downSide = null;

  @JsonProperty("upSide")
  private String upSide = null;

  public AccountAuth state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * 认证状态
   * @return state
  **/
  @ApiModelProperty(value = "认证状态")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public AccountAuth realName(String realName) {
    this.realName = realName;
    return this;
  }

   /**
   * 真实姓名
   * @return realName
  **/
  @ApiModelProperty(value = "真实姓名")
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public AccountAuth idcCardNumber(String idcCardNumber) {
    this.idcCardNumber = idcCardNumber;
    return this;
  }

   /**
   * 身份证号
   * @return idcCardNumber
  **/
  @ApiModelProperty(value = "身份证号")
  public String getIdcCardNumber() {
    return idcCardNumber;
  }

  public void setIdcCardNumber(String idcCardNumber) {
    this.idcCardNumber = idcCardNumber;
  }

  public AccountAuth failedReason(String failedReason) {
    this.failedReason = failedReason;
    return this;
  }

   /**
   * 未通过原因
   * @return failedReason
  **/
  @ApiModelProperty(value = "未通过原因")
  public String getFailedReason() {
    return failedReason;
  }

  public void setFailedReason(String failedReason) {
    this.failedReason = failedReason;
  }

  public AccountAuth downSide(String downSide) {
    this.downSide = downSide;
    return this;
  }

   /**
   * 身份证背面
   * @return downSide
  **/
  @ApiModelProperty(value = "身份证背面")
  public String getDownSide() {
    return downSide;
  }

  public void setDownSide(String downSide) {
    this.downSide = downSide;
  }

  public AccountAuth upSide(String upSide) {
    this.upSide = upSide;
    return this;
  }

   /**
   * 身份证前面
   * @return upSide
  **/
  @ApiModelProperty(value = "身份证前面")
  public String getUpSide() {
    return upSide;
  }

  public void setUpSide(String upSide) {
    this.upSide = upSide;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountAuth accountAuth = (AccountAuth) o;
    return Objects.equals(this.state, accountAuth.state) &&
        Objects.equals(this.realName, accountAuth.realName) &&
        Objects.equals(this.idcCardNumber, accountAuth.idcCardNumber) &&
        Objects.equals(this.failedReason, accountAuth.failedReason) &&
        Objects.equals(this.downSide, accountAuth.downSide) &&
        Objects.equals(this.upSide, accountAuth.upSide);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, realName, idcCardNumber, failedReason, downSide, upSide);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountAuth {\n");
    
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    realName: ").append(toIndentedString(realName)).append("\n");
    sb.append("    idcCardNumber: ").append(toIndentedString(idcCardNumber)).append("\n");
    sb.append("    failedReason: ").append(toIndentedString(failedReason)).append("\n");
    sb.append("    downSide: ").append(toIndentedString(downSide)).append("\n");
    sb.append("    upSide: ").append(toIndentedString(upSide)).append("\n");
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

