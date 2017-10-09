package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * Option
 */

public class Option  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("clusterName")
  private String clusterName = null;

  @JsonProperty("clusterId")
  private Long clusterId = null;

  public Option id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * 选项的id
   * @return id
  **/
  @ApiModelProperty(value = "选项的id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Option name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 选项的名称
   * @return name
  **/
  @ApiModelProperty(value = "选项的名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Option clusterName(String clusterName) {
    this.clusterName = clusterName;
    return this;
  }

   /**
   * 选项类型的名称
   * @return clusterName
  **/
  @ApiModelProperty(value = "选项类型的名称")
  public String getClusterName() {
    return clusterName;
  }

  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  public Option clusterId(Long clusterId) {
    this.clusterId = clusterId;
    return this;
  }

   /**
   * 选项类型id
   * @return clusterId
  **/
  @ApiModelProperty(value = "选项类型id")
  public Long getClusterId() {
    return clusterId;
  }

  public void setClusterId(Long clusterId) {
    this.clusterId = clusterId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Option option = (Option) o;
    return Objects.equals(this.id, option.id) &&
        Objects.equals(this.name, option.name) &&
        Objects.equals(this.clusterName, option.clusterName) &&
        Objects.equals(this.clusterId, option.clusterId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, clusterName, clusterId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Option {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    clusterName: ").append(toIndentedString(clusterName)).append("\n");
    sb.append("    clusterId: ").append(toIndentedString(clusterId)).append("\n");
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

