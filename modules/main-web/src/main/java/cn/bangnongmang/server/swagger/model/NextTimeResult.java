package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * NextTimeResult
 */

public class NextTimeResult  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("nextTime")
  private Long nextTime = null;

  public NextTimeResult nextTime(Long nextTime) {
    this.nextTime = nextTime;
    return this;
  }

   /**
   * Get nextTime
   * @return nextTime
  **/
  @ApiModelProperty(value = "")
  public Long getNextTime() {
    return nextTime;
  }

  public void setNextTime(Long nextTime) {
    this.nextTime = nextTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NextTimeResult nextTimeResult = (NextTimeResult) o;
    return Objects.equals(this.nextTime, nextTimeResult.nextTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nextTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NextTimeResult {\n");
    
    sb.append("    nextTime: ").append(toIndentedString(nextTime)).append("\n");
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

