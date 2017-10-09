package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.MachineType;
import cn.bangnongmang.admin.swagger.model.ModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * MachineModel
 */

public class MachineModel  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("machineType")
  private MachineType machineType = null;

  @JsonProperty("brand")
  private String brand = null;

  @JsonProperty("model_num")
  private String modelNum = null;

  @JsonProperty("power")
  private Double power = null;

  @JsonProperty("cut_num")
  private Double cutNum = null;

  @JsonProperty("modelproperties")
  private List<ModelProperty> modelproperties = null;

  public MachineModel id(Long id) {
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

  public MachineModel machineType(MachineType machineType) {
    this.machineType = machineType;
    return this;
  }

   /**
   * Get machineType
   * @return machineType
  **/
  @ApiModelProperty(value = "")
  public MachineType getMachineType() {
    return machineType;
  }

  public void setMachineType(MachineType machineType) {
    this.machineType = machineType;
  }

  public MachineModel brand(String brand) {
    this.brand = brand;
    return this;
  }

   /**
   * Get brand
   * @return brand
  **/
  @ApiModelProperty(value = "")
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public MachineModel modelNum(String modelNum) {
    this.modelNum = modelNum;
    return this;
  }

   /**
   * Get modelNum
   * @return modelNum
  **/
  @ApiModelProperty(value = "")
  public String getModelNum() {
    return modelNum;
  }

  public void setModelNum(String modelNum) {
    this.modelNum = modelNum;
  }

  public MachineModel power(Double power) {
    this.power = power;
    return this;
  }

   /**
   * Get power
   * @return power
  **/
  @ApiModelProperty(value = "")
  public Double getPower() {
    return power;
  }

  public void setPower(Double power) {
    this.power = power;
  }

  public MachineModel cutNum(Double cutNum) {
    this.cutNum = cutNum;
    return this;
  }

   /**
   * Get cutNum
   * @return cutNum
  **/
  @ApiModelProperty(value = "")
  public Double getCutNum() {
    return cutNum;
  }

  public void setCutNum(Double cutNum) {
    this.cutNum = cutNum;
  }

  public MachineModel modelproperties(List<ModelProperty> modelproperties) {
    this.modelproperties = modelproperties;
    return this;
  }

  public MachineModel addModelpropertiesItem(ModelProperty modelpropertiesItem) {
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
    MachineModel machineModel = (MachineModel) o;
    return Objects.equals(this.id, machineModel.id) &&
        Objects.equals(this.machineType, machineModel.machineType) &&
        Objects.equals(this.brand, machineModel.brand) &&
        Objects.equals(this.modelNum, machineModel.modelNum) &&
        Objects.equals(this.power, machineModel.power) &&
        Objects.equals(this.cutNum, machineModel.cutNum) &&
        Objects.equals(this.modelproperties, machineModel.modelproperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, machineType, brand, modelNum, power, cutNum, modelproperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MachineModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    machineType: ").append(toIndentedString(machineType)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    modelNum: ").append(toIndentedString(modelNum)).append("\n");
    sb.append("    power: ").append(toIndentedString(power)).append("\n");
    sb.append("    cutNum: ").append(toIndentedString(cutNum)).append("\n");
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

