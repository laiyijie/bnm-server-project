package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.MachineProperty;
import cn.bangnongmang.server.swagger.model.MachineType;
import cn.bangnongmang.server.swagger.model.ModelProperty;
import cn.bangnongmang.server.swagger.model.UserMachineBasic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * UserMachineDetail
 */

public class UserMachineDetail extends UserMachineBasic implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("machinetype")
  private MachineType machinetype = null;

  @JsonProperty("power")
  private Double power = null;

  @JsonProperty("cutnum")
  private Double cutnum = null;

  @JsonProperty("reason")
  private String reason = null;

  @JsonProperty("buytime")
  private Long buytime = null;

  @JsonProperty("authImage")
  private List<String> authImage = null;

  @JsonProperty("machineproperties")
  private List<MachineProperty> machineproperties = null;

  @JsonProperty("modelproperties")
  private List<ModelProperty> modelproperties = null;

  public UserMachineDetail machinetype(MachineType machinetype) {
    this.machinetype = machinetype;
    return this;
  }

   /**
   * Get machinetype
   * @return machinetype
  **/
  @ApiModelProperty(value = "")
  public MachineType getMachinetype() {
    return machinetype;
  }

  public void setMachinetype(MachineType machinetype) {
    this.machinetype = machinetype;
  }

  public UserMachineDetail power(Double power) {
    this.power = power;
    return this;
  }

   /**
   * 马力
   * @return power
  **/
  @ApiModelProperty(value = "马力")
  public Double getPower() {
    return power;
  }

  public void setPower(Double power) {
    this.power = power;
  }

  public UserMachineDetail cutnum(Double cutnum) {
    this.cutnum = cutnum;
    return this;
  }

   /**
   * 喂入量
   * @return cutnum
  **/
  @ApiModelProperty(value = "喂入量")
  public Double getCutnum() {
    return cutnum;
  }

  public void setCutnum(Double cutnum) {
    this.cutnum = cutnum;
  }

  public UserMachineDetail reason(String reason) {
    this.reason = reason;
    return this;
  }

   /**
   * 理由
   * @return reason
  **/
  @ApiModelProperty(value = "理由")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public UserMachineDetail buytime(Long buytime) {
    this.buytime = buytime;
    return this;
  }

   /**
   * 购买时间
   * @return buytime
  **/
  @ApiModelProperty(value = "购买时间")
  public Long getBuytime() {
    return buytime;
  }

  public void setBuytime(Long buytime) {
    this.buytime = buytime;
  }

  public UserMachineDetail authImage(List<String> authImage) {
    this.authImage = authImage;
    return this;
  }

  public UserMachineDetail addAuthImageItem(String authImageItem) {
    if (this.authImage == null) {
      this.authImage = new ArrayList<String>();
    }
    this.authImage.add(authImageItem);
    return this;
  }

   /**
   * 车辆认证的图片url
   * @return authImage
  **/
  @ApiModelProperty(value = "车辆认证的图片url")
  public List<String> getAuthImage() {
    return authImage;
  }

  public void setAuthImage(List<String> authImage) {
    this.authImage = authImage;
  }

  public UserMachineDetail machineproperties(List<MachineProperty> machineproperties) {
    this.machineproperties = machineproperties;
    return this;
  }

  public UserMachineDetail addMachinepropertiesItem(MachineProperty machinepropertiesItem) {
    if (this.machineproperties == null) {
      this.machineproperties = new ArrayList<MachineProperty>();
    }
    this.machineproperties.add(machinepropertiesItem);
    return this;
  }

   /**
   * Get machineproperties
   * @return machineproperties
  **/
  @ApiModelProperty(value = "")
  public List<MachineProperty> getMachineproperties() {
    return machineproperties;
  }

  public void setMachineproperties(List<MachineProperty> machineproperties) {
    this.machineproperties = machineproperties;
  }

  public UserMachineDetail modelproperties(List<ModelProperty> modelproperties) {
    this.modelproperties = modelproperties;
    return this;
  }

  public UserMachineDetail addModelpropertiesItem(ModelProperty modelpropertiesItem) {
    if (this.modelproperties == null) {
      this.modelproperties = new ArrayList<ModelProperty>();
    }
    this.modelproperties.add(modelpropertiesItem);
    return this;
  }

   /**
   * Get modelproperties
   * @return modelproperties
  **/
  @ApiModelProperty(value = "")
  public List<ModelProperty> getModelproperties() {
    return modelproperties;
  }

  public void setModelproperties(List<ModelProperty> modelproperties) {
    this.modelproperties = modelproperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserMachineDetail userMachineDetail = (UserMachineDetail) o;
    return Objects.equals(this.machinetype, userMachineDetail.machinetype) &&
        Objects.equals(this.power, userMachineDetail.power) &&
        Objects.equals(this.cutnum, userMachineDetail.cutnum) &&
        Objects.equals(this.reason, userMachineDetail.reason) &&
        Objects.equals(this.buytime, userMachineDetail.buytime) &&
        Objects.equals(this.authImage, userMachineDetail.authImage) &&
        Objects.equals(this.machineproperties, userMachineDetail.machineproperties) &&
        Objects.equals(this.modelproperties, userMachineDetail.modelproperties) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(machinetype, power, cutnum, reason, buytime, authImage, machineproperties, modelproperties, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserMachineDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    machinetype: ").append(toIndentedString(machinetype)).append("\n");
    sb.append("    power: ").append(toIndentedString(power)).append("\n");
    sb.append("    cutnum: ").append(toIndentedString(cutnum)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    buytime: ").append(toIndentedString(buytime)).append("\n");
    sb.append("    authImage: ").append(toIndentedString(authImage)).append("\n");
    sb.append("    machineproperties: ").append(toIndentedString(machineproperties)).append("\n");
    sb.append("    modelproperties: ").append(toIndentedString(modelproperties)).append("\n");
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

