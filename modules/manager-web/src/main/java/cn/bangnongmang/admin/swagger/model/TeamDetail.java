package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.TeamBasic;
import cn.bangnongmang.admin.swagger.model.TeamMember;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * TeamDetail
 */

public class TeamDetail extends TeamBasic implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("teamMember")
  private List<TeamMember> teamMember = null;

  public TeamDetail teamMember(List<TeamMember> teamMember) {
    this.teamMember = teamMember;
    return this;
  }

  public TeamDetail addTeamMemberItem(TeamMember teamMemberItem) {
    if (this.teamMember == null) {
      this.teamMember = new ArrayList<TeamMember>();
    }
    this.teamMember.add(teamMemberItem);
    return this;
  }

   /**
   * Get teamMember
   * @return teamMember
  **/
  @ApiModelProperty(value = "")
  public List<TeamMember> getTeamMember() {
    return teamMember;
  }

  public void setTeamMember(List<TeamMember> teamMember) {
    this.teamMember = teamMember;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamDetail teamDetail = (TeamDetail) o;
    return Objects.equals(this.teamMember, teamDetail.teamMember) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teamMember, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    teamMember: ").append(toIndentedString(teamMember)).append("\n");
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

