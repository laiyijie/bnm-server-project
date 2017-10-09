package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * AliCredentials
 */

public class AliCredentials  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("securityToken")
  private String securityToken = null;

  @JsonProperty("accessKeySecret")
  private String accessKeySecret = null;

  @JsonProperty("accessKeyId")
  private String accessKeyId = null;

  @JsonProperty("expiration")
  private String expiration = null;

  public AliCredentials securityToken(String securityToken) {
    this.securityToken = securityToken;
    return this;
  }

   /**
   * Get securityToken
   * @return securityToken
  **/
  @ApiModelProperty(value = "")
  public String getSecurityToken() {
    return securityToken;
  }

  public void setSecurityToken(String securityToken) {
    this.securityToken = securityToken;
  }

  public AliCredentials accessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
    return this;
  }

   /**
   * Get accessKeySecret
   * @return accessKeySecret
  **/
  @ApiModelProperty(value = "")
  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
  }

  public AliCredentials accessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
    return this;
  }

   /**
   * Get accessKeyId
   * @return accessKeyId
  **/
  @ApiModelProperty(value = "")
  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public AliCredentials expiration(String expiration) {
    this.expiration = expiration;
    return this;
  }

   /**
   * Get expiration
   * @return expiration
  **/
  @ApiModelProperty(value = "")
  public String getExpiration() {
    return expiration;
  }

  public void setExpiration(String expiration) {
    this.expiration = expiration;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AliCredentials aliCredentials = (AliCredentials) o;
    return Objects.equals(this.securityToken, aliCredentials.securityToken) &&
        Objects.equals(this.accessKeySecret, aliCredentials.accessKeySecret) &&
        Objects.equals(this.accessKeyId, aliCredentials.accessKeyId) &&
        Objects.equals(this.expiration, aliCredentials.expiration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(securityToken, accessKeySecret, accessKeyId, expiration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AliCredentials {\n");
    
    sb.append("    securityToken: ").append(toIndentedString(securityToken)).append("\n");
    sb.append("    accessKeySecret: ").append(toIndentedString(accessKeySecret)).append("\n");
    sb.append("    accessKeyId: ").append(toIndentedString(accessKeyId)).append("\n");
    sb.append("    expiration: ").append(toIndentedString(expiration)).append("\n");
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

