package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.OptionCluster;
import cn.bangnongmang.server.swagger.model.OptionDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * ModelProperty
 */

public class ModelProperty  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("optionCluster")
  private OptionCluster optionCluster = null;

  @JsonProperty("optionDetials")
  private List<OptionDetail> optionDetials = null;

  public ModelProperty optionCluster(OptionCluster optionCluster) {
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

  public ModelProperty optionDetials(List<OptionDetail> optionDetials) {
    this.optionDetials = optionDetials;
    return this;
  }

  public ModelProperty addOptionDetialsItem(OptionDetail optionDetialsItem) {
    if (this.optionDetials == null) {
      this.optionDetials = new ArrayList<OptionDetail>();
    }
    this.optionDetials.add(optionDetialsItem);
    return this;
  }

   /**
   * Get optionDetials
   * @return optionDetials
  **/
  @ApiModelProperty(value = "")
  public List<OptionDetail> getOptionDetials() {
    return optionDetials;
  }

  public void setOptionDetials(List<OptionDetail> optionDetials) {
    this.optionDetials = optionDetials;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelProperty modelProperty = (ModelProperty) o;
    return Objects.equals(this.optionCluster, modelProperty.optionCluster) &&
        Objects.equals(this.optionDetials, modelProperty.optionDetials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(optionCluster, optionDetials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelProperty {\n");
    
    sb.append("    optionCluster: ").append(toIndentedString(optionCluster)).append("\n");
    sb.append("    optionDetials: ").append(toIndentedString(optionDetials)).append("\n");
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

