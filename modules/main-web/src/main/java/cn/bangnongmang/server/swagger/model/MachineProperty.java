package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.OptionCluster;
import cn.bangnongmang.server.swagger.model.OptionDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * MachineProperty
 */

public class MachineProperty  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("optionCluster")
  private OptionCluster optionCluster = null;

  @JsonProperty("optionDetail")
  private OptionDetail optionDetail = null;

  public MachineProperty optionCluster(OptionCluster optionCluster) {
    this.optionCluster = optionCluster;
    return this;
  }

   /**
   * Get optionCluster
   * @return optionCluster
  **/
  @ApiModelProperty(value = "")
  public OptionCluster getOptionCluster() {
    return optionCluster;
  }

  public void setOptionCluster(OptionCluster optionCluster) {
    this.optionCluster = optionCluster;
  }

  public MachineProperty optionDetail(OptionDetail optionDetail) {
    this.optionDetail = optionDetail;
    return this;
  }

   /**
   * Get optionDetail
   * @return optionDetail
  **/
  @ApiModelProperty(value = "")
  public OptionDetail getOptionDetail() {
    return optionDetail;
  }

  public void setOptionDetail(OptionDetail optionDetail) {
    this.optionDetail = optionDetail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MachineProperty machineProperty = (MachineProperty) o;
    return Objects.equals(this.optionCluster, machineProperty.optionCluster) &&
        Objects.equals(this.optionDetail, machineProperty.optionDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(optionCluster, optionDetail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MachineProperty {\n");
    
    sb.append("    optionCluster: ").append(toIndentedString(optionCluster)).append("\n");
    sb.append("    optionDetail: ").append(toIndentedString(optionDetail)).append("\n");
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

