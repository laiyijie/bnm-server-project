package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.Order;
import cn.bangnongmang.server.swagger.model.TeamJoinRequest;
import cn.bangnongmang.server.swagger.model.UserSimple;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 队伍信息
 */
@ApiModel(description = "队伍信息")

public class Team  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("teamId")
  private Long teamId = null;

  @JsonProperty("leader")
  private UserSimple leader = null;

  @JsonProperty("teamMembers")
  private List<UserSimple> teamMembers = null;

  @JsonProperty("joinRequests")
  private List<TeamJoinRequest> joinRequests = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("order")
  private Order order = null;

  public Team teamId(Long teamId) {
    this.teamId = teamId;
    return this;
  }

   /**
   * 队伍的ID
   * @return teamId
  **/
  @ApiModelProperty(value = "队伍的ID")
  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public Team leader(UserSimple leader) {
    this.leader = leader;
    return this;
  }

   /**
   * Get leader
   * @return leader
  **/
  @ApiModelProperty(value = "")
  public UserSimple getLeader() {
    return leader;
  }

  public void setLeader(UserSimple leader) {
    this.leader = leader;
  }

  public Team teamMembers(List<UserSimple> teamMembers) {
    this.teamMembers = teamMembers;
    return this;
  }

  public Team addTeamMembersItem(UserSimple teamMembersItem) {
    if (this.teamMembers == null) {
      this.teamMembers = new ArrayList<UserSimple>();
    }
    this.teamMembers.add(teamMembersItem);
    return this;
  }

   /**
   * 已入队的队员
   * @return teamMembers
  **/
  @ApiModelProperty(value = "已入队的队员")
  public List<UserSimple> getTeamMembers() {
    return teamMembers;
  }

  public void setTeamMembers(List<UserSimple> teamMembers) {
    this.teamMembers = teamMembers;
  }

  public Team joinRequests(List<TeamJoinRequest> joinRequests) {
    this.joinRequests = joinRequests;
    return this;
  }

  public Team addJoinRequestsItem(TeamJoinRequest joinRequestsItem) {
    if (this.joinRequests == null) {
      this.joinRequests = new ArrayList<TeamJoinRequest>();
    }
    this.joinRequests.add(joinRequestsItem);
    return this;
  }

   /**
   * 入队请求
   * @return joinRequests
  **/
  @ApiModelProperty(value = "入队请求")
  public List<TeamJoinRequest> getJoinRequests() {
    return joinRequests;
  }

  public void setJoinRequests(List<TeamJoinRequest> joinRequests) {
    this.joinRequests = joinRequests;
  }

  public Team description(String description) {
    this.description = description;
    return this;
  }

   /**
   * 组队的时候填写的描述
   * @return description
  **/
  @ApiModelProperty(value = "组队的时候填写的描述")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Team order(Order order) {
    this.order = order;
    return this;
  }

   /**
   * Get order
   * @return order
  **/
  @ApiModelProperty(value = "")
  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Team team = (Team) o;
    return Objects.equals(this.teamId, team.teamId) &&
        Objects.equals(this.leader, team.leader) &&
        Objects.equals(this.teamMembers, team.teamMembers) &&
        Objects.equals(this.joinRequests, team.joinRequests) &&
        Objects.equals(this.description, team.description) &&
        Objects.equals(this.order, team.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teamId, leader, teamMembers, joinRequests, description, order);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Team {\n");
    
    sb.append("    teamId: ").append(toIndentedString(teamId)).append("\n");
    sb.append("    leader: ").append(toIndentedString(leader)).append("\n");
    sb.append("    teamMembers: ").append(toIndentedString(teamMembers)).append("\n");
    sb.append("    joinRequests: ").append(toIndentedString(joinRequests)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
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

