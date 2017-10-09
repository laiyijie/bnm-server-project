package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * OrderTag
 */

public class OrderTag  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("tagName")
  private String tagName = null;

  public OrderTag tagName(String tagName) {
    this.tagName = tagName;
    return this;
  }

   /**
   * tag的名称
   * @return tagName
  **/
  @ApiModelProperty(value = "tag的名称")
  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderTag orderTag = (OrderTag) o;
    return Objects.equals(this.tagName, orderTag.tagName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tagName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderTag {\n");
    
    sb.append("    tagName: ").append(toIndentedString(tagName)).append("\n");
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

