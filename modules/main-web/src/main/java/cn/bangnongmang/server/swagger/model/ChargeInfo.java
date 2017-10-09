package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * ChargeInfo
 */

public class ChargeInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("infoJsonString")
  private String infoJsonString = null;

  public ChargeInfo infoJsonString(String infoJsonString) {
    this.infoJsonString = infoJsonString;
    return this;
  }

   /**
   * Get infoJsonString
   * @return infoJsonString
  **/
  @ApiModelProperty(value = "")
  public String getInfoJsonString() {
    return infoJsonString;
  }

  public void setInfoJsonString(String infoJsonString) {
    this.infoJsonString = infoJsonString;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargeInfo chargeInfo = (ChargeInfo) o;
    return Objects.equals(this.infoJsonString, chargeInfo.infoJsonString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(infoJsonString);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChargeInfo {\n");
    
    sb.append("    infoJsonString: ").append(toIndentedString(infoJsonString)).append("\n");
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

