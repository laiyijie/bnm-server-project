package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.UserDetail;
import cn.bangnongmang.admin.swagger.model.UserGeo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * RealNameAuthDetail
 */

public class RealNameAuthDetail extends UserDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("idCardNum")
  private String idCardNum = null;

  @JsonProperty("upSideImage")
  private String upSideImage = null;

  @JsonProperty("downSideImage")
  private String downSideImage = null;

  @JsonProperty("updateTime")
  private Long updateTime = null;

  @JsonProperty("failedReason")
  private String failedReason = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    WAITING_AUTH("WAITING_AUTH"),
    
    AUTHED("AUTHED"),
    
    DENIED("DENIED");

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

  public RealNameAuthDetail uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * Get uid
   * @return uid
  **/
  @ApiModelProperty(value = "")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public RealNameAuthDetail idCardNum(String idCardNum) {
    this.idCardNum = idCardNum;
    return this;
  }

   /**
   * 身份证号
   * @return idCardNum
  **/
  @ApiModelProperty(value = "身份证号")
  public String getIdCardNum() {
    return idCardNum;
  }

  public void setIdCardNum(String idCardNum) {
    this.idCardNum = idCardNum;
  }

  public RealNameAuthDetail upSideImage(String upSideImage) {
    this.upSideImage = upSideImage;
    return this;
  }

   /**
   * 正面
   * @return upSideImage
  **/
  @ApiModelProperty(value = "正面")
  public String getUpSideImage() {
    return upSideImage;
  }

  public void setUpSideImage(String upSideImage) {
    this.upSideImage = upSideImage;
  }

  public RealNameAuthDetail downSideImage(String downSideImage) {
    this.downSideImage = downSideImage;
    return this;
  }

   /**
   * 反面
   * @return downSideImage
  **/
  @ApiModelProperty(value = "反面")
  public String getDownSideImage() {
    return downSideImage;
  }

  public void setDownSideImage(String downSideImage) {
    this.downSideImage = downSideImage;
  }

  public RealNameAuthDetail updateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

   /**
   * Get updateTime
   * @return updateTime
  **/
  @ApiModelProperty(value = "")
  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  public RealNameAuthDetail failedReason(String failedReason) {
    this.failedReason = failedReason;
    return this;
  }

   /**
   * Get failedReason
   * @return failedReason
  **/
  @ApiModelProperty(value = "")
  public String getFailedReason() {
    return failedReason;
  }

  public void setFailedReason(String failedReason) {
    this.failedReason = failedReason;
  }

  public RealNameAuthDetail state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")
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
    RealNameAuthDetail realNameAuthDetail = (RealNameAuthDetail) o;
    return Objects.equals(this.uid, realNameAuthDetail.uid) &&
        Objects.equals(this.idCardNum, realNameAuthDetail.idCardNum) &&
        Objects.equals(this.upSideImage, realNameAuthDetail.upSideImage) &&
        Objects.equals(this.downSideImage, realNameAuthDetail.downSideImage) &&
        Objects.equals(this.updateTime, realNameAuthDetail.updateTime) &&
        Objects.equals(this.failedReason, realNameAuthDetail.failedReason) &&
        Objects.equals(this.state, realNameAuthDetail.state) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, idCardNum, upSideImage, downSideImage, updateTime, failedReason, state, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RealNameAuthDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    idCardNum: ").append(toIndentedString(idCardNum)).append("\n");
    sb.append("    upSideImage: ").append(toIndentedString(upSideImage)).append("\n");
    sb.append("    downSideImage: ").append(toIndentedString(downSideImage)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    failedReason: ").append(toIndentedString(failedReason)).append("\n");
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

