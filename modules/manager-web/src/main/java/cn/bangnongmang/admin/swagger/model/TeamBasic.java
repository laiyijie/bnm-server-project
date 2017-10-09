package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * TeamBasic
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = TeamDetail.class, name = "TeamDetail"),
})

public class TeamBasic  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("leaderUid")
  private Long leaderUid = null;

  @JsonProperty("leaderName")
  private String leaderName = null;

  @JsonProperty("leaderTel")
  private String leaderTel = null;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("teamId")
  private Long teamId = null;

  @JsonProperty("teamDescription")
  private String teamDescription = null;

  public TeamBasic leaderUid(Long leaderUid) {
    this.leaderUid = leaderUid;
    return this;
  }

   /**
   * Get leaderUid
   * @return leaderUid
  **/
  @ApiModelProperty(value = "")
  public Long getLeaderUid() {
    return leaderUid;
  }

  public void setLeaderUid(Long leaderUid) {
    this.leaderUid = leaderUid;
  }

  public TeamBasic leaderName(String leaderName) {
    this.leaderName = leaderName;
    return this;
  }

   /**
   * Get leaderName
   * @return leaderName
  **/
  @ApiModelProperty(value = "")
  public String getLeaderName() {
    return leaderName;
  }

  public void setLeaderName(String leaderName) {
    this.leaderName = leaderName;
  }

  public TeamBasic leaderTel(String leaderTel) {
    this.leaderTel = leaderTel;
    return this;
  }

   /**
   * Get leaderTel
   * @return leaderTel
  **/
  @ApiModelProperty(value = "")
  public String getLeaderTel() {
    return leaderTel;
  }

  public void setLeaderTel(String leaderTel) {
    this.leaderTel = leaderTel;
  }

  public TeamBasic orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(value = "")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public TeamBasic teamId(Long teamId) {
    this.teamId = teamId;
    return this;
  }

   /**
   * Get teamId
   * @return teamId
  **/
  @ApiModelProperty(value = "")
  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public TeamBasic teamDescription(String teamDescription) {
    this.teamDescription = teamDescription;
    return this;
  }

   /**
   * Get teamDescription
   * @return teamDescription
  **/
  @ApiModelProperty(value = "")
  public String getTeamDescription() {
    return teamDescription;
  }

  public void setTeamDescription(String teamDescription) {
    this.teamDescription = teamDescription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamBasic teamBasic = (TeamBasic) o;
    return Objects.equals(this.leaderUid, teamBasic.leaderUid) &&
        Objects.equals(this.leaderName, teamBasic.leaderName) &&
        Objects.equals(this.leaderTel, teamBasic.leaderTel) &&
        Objects.equals(this.orderId, teamBasic.orderId) &&
        Objects.equals(this.teamId, teamBasic.teamId) &&
        Objects.equals(this.teamDescription, teamBasic.teamDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leaderUid, leaderName, leaderTel, orderId, teamId, teamDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamBasic {\n");
    
    sb.append("    leaderUid: ").append(toIndentedString(leaderUid)).append("\n");
    sb.append("    leaderName: ").append(toIndentedString(leaderName)).append("\n");
    sb.append("    leaderTel: ").append(toIndentedString(leaderTel)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    teamId: ").append(toIndentedString(teamId)).append("\n");
    sb.append("    teamDescription: ").append(toIndentedString(teamDescription)).append("\n");
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

