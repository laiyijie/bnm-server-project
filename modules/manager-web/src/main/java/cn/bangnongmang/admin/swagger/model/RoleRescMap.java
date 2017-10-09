package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * RoleRescMap
 */

public class RoleRescMap  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("resc_id")
  private Integer rescId = null;

  @JsonProperty("role_id")
  private Integer roleId = null;

  /**
   * Gets or Sets method
   */
  public enum MethodEnum {
    ALL("ALL"),
    
    GET("GET");

    private String value;

    MethodEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MethodEnum fromValue(String text) {
      for (MethodEnum b : MethodEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("method")
  private MethodEnum method = MethodEnum.ALL;

  public RoleRescMap rescId(Integer rescId) {
    this.rescId = rescId;
    return this;
  }

   /**
   * Get rescId
   * @return rescId
  **/
  @ApiModelProperty(value = "")
  public Integer getRescId() {
    return rescId;
  }

  public void setRescId(Integer rescId) {
    this.rescId = rescId;
  }

  public RoleRescMap roleId(Integer roleId) {
    this.roleId = roleId;
    return this;
  }

   /**
   * Get roleId
   * @return roleId
  **/
  @ApiModelProperty(value = "")
  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public RoleRescMap method(MethodEnum method) {
    this.method = method;
    return this;
  }

   /**
   * Get method
   * @return method
  **/
  @ApiModelProperty(value = "")
  public MethodEnum getMethod() {
    return method;
  }

  public void setMethod(MethodEnum method) {
    this.method = method;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleRescMap roleRescMap = (RoleRescMap) o;
    return Objects.equals(this.rescId, roleRescMap.rescId) &&
        Objects.equals(this.roleId, roleRescMap.roleId) &&
        Objects.equals(this.method, roleRescMap.method);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rescId, roleId, method);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleRescMap {\n");
    
    sb.append("    rescId: ").append(toIndentedString(rescId)).append("\n");
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
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

