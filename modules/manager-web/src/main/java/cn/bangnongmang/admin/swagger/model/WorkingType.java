package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 作物种类和作业类型
 */
@ApiModel(description = "作物种类和作业类型")

public class WorkingType  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("cropType")
  private String cropType = null;

  @JsonProperty("workingType")
  private String workingType = null;

  public WorkingType id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * 作物种类和作业类型的ID
   * @return id
  **/
  @ApiModelProperty(value = "作物种类和作业类型的ID")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public WorkingType cropType(String cropType) {
    this.cropType = cropType;
    return this;
  }

   /**
   * 作物（如：玉米）
   * @return cropType
  **/
  @ApiModelProperty(value = "作物（如：玉米）")
  public String getCropType() {
    return cropType;
  }

  public void setCropType(String cropType) {
    this.cropType = cropType;
  }

  public WorkingType workingType(String workingType) {
    this.workingType = workingType;
    return this;
  }

   /**
   * 作业种类（如：收割）
   * @return workingType
  **/
  @ApiModelProperty(value = "作业种类（如：收割）")
  public String getWorkingType() {
    return workingType;
  }

  public void setWorkingType(String workingType) {
    this.workingType = workingType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkingType workingType = (WorkingType) o;
    return Objects.equals(this.id, workingType.id) &&
        Objects.equals(this.cropType, workingType.cropType) &&
        Objects.equals(this.workingType, workingType.workingType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cropType, workingType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkingType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cropType: ").append(toIndentedString(cropType)).append("\n");
    sb.append("    workingType: ").append(toIndentedString(workingType)).append("\n");
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

