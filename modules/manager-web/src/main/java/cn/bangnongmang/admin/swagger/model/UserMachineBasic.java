package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * UserMachineBasic
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = UserMachineDetail.class, name = "UserMachineDetail"),
})

public class UserMachineBasic  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("tel")
  private String tel = null;

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

  @JsonProperty("modelId")
  private Long modelId = null;

  @JsonProperty("brand")
  private String brand = null;

  @JsonProperty("brand_num")
  private String brandNum = null;

  @JsonProperty("integrity")
  private Integer integrity = null;

  public UserMachineBasic id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserMachineBasic uid(Long uid) {
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

  public UserMachineBasic username(String username) {
    this.username = username;
    return this;
  }

   /**
   * 用户名
   * @return username
  **/
  @ApiModelProperty(value = "用户名")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserMachineBasic tel(String tel) {
    this.tel = tel;
    return this;
  }

   /**
   * 用户电话
   * @return tel
  **/
  @ApiModelProperty(value = "用户电话")
  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public UserMachineBasic state(StateEnum state) {
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

  public UserMachineBasic modelId(Long modelId) {
    this.modelId = modelId;
    return this;
  }

   /**
   * 车辆模型id
   * @return modelId
  **/
  @ApiModelProperty(value = "车辆模型id")
  public Long getModelId() {
    return modelId;
  }

  public void setModelId(Long modelId) {
    this.modelId = modelId;
  }

  public UserMachineBasic brand(String brand) {
    this.brand = brand;
    return this;
  }

   /**
   * 车辆品牌
   * @return brand
  **/
  @ApiModelProperty(value = "车辆品牌")
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public UserMachineBasic brandNum(String brandNum) {
    this.brandNum = brandNum;
    return this;
  }

   /**
   * 车辆型号
   * @return brandNum
  **/
  @ApiModelProperty(value = "车辆型号")
  public String getBrandNum() {
    return brandNum;
  }

  public void setBrandNum(String brandNum) {
    this.brandNum = brandNum;
  }

  public UserMachineBasic integrity(Integer integrity) {
    this.integrity = integrity;
    return this;
  }

   /**
   * 信息是否完整
   * @return integrity
  **/
  @ApiModelProperty(value = "信息是否完整")
  public Integer getIntegrity() {
    return integrity;
  }

  public void setIntegrity(Integer integrity) {
    this.integrity = integrity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserMachineBasic userMachineBasic = (UserMachineBasic) o;
    return Objects.equals(this.id, userMachineBasic.id) &&
        Objects.equals(this.uid, userMachineBasic.uid) &&
        Objects.equals(this.username, userMachineBasic.username) &&
        Objects.equals(this.tel, userMachineBasic.tel) &&
        Objects.equals(this.state, userMachineBasic.state) &&
        Objects.equals(this.modelId, userMachineBasic.modelId) &&
        Objects.equals(this.brand, userMachineBasic.brand) &&
        Objects.equals(this.brandNum, userMachineBasic.brandNum) &&
        Objects.equals(this.integrity, userMachineBasic.integrity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uid, username, tel, state, modelId, brand, brandNum, integrity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserMachineBasic {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    tel: ").append(toIndentedString(tel)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    modelId: ").append(toIndentedString(modelId)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    brandNum: ").append(toIndentedString(brandNum)).append("\n");
    sb.append("    integrity: ").append(toIndentedString(integrity)).append("\n");
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

