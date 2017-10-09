package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * StarInfo
 */

public class StarInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("valuted")
  private Long valuted = null;

  @JsonProperty("star")
  private Double star = null;

  @JsonProperty("time")
  private Long time = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    MEMBER("STAR_MEMBER"),
    
    LEADER("STAR_LEADER");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  public StarInfo id(Long id) {
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

  public StarInfo valuted(Long valuted) {
    this.valuted = valuted;
    return this;
  }

   /**
   * Get valuted
   * @return valuted
  **/
  @ApiModelProperty(value = "")
  public Long getValuted() {
    return valuted;
  }

  public void setValuted(Long valuted) {
    this.valuted = valuted;
  }

  public StarInfo star(Double star) {
    this.star = star;
    return this;
  }

   /**
   * Get star
   * @return star
  **/
  @ApiModelProperty(value = "")
  public Double getStar() {
    return star;
  }

  public void setStar(Double star) {
    this.star = star;
  }

  public StarInfo time(Long time) {
    this.time = time;
    return this;
  }

   /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")
  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public StarInfo type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StarInfo starInfo = (StarInfo) o;
    return Objects.equals(this.id, starInfo.id) &&
        Objects.equals(this.valuted, starInfo.valuted) &&
        Objects.equals(this.star, starInfo.star) &&
        Objects.equals(this.time, starInfo.time) &&
        Objects.equals(this.type, starInfo.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, valuted, star, time, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StarInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    valuted: ").append(toIndentedString(valuted)).append("\n");
    sb.append("    star: ").append(toIndentedString(star)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

