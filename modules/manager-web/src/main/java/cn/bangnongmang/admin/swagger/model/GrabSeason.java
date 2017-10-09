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
 * 抢单场次
 */
@ApiModel(description = "抢单场次")

public class GrabSeason  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("seasonId")
  private String seasonId = null;

  @JsonProperty("startTime")
  private Long startTime = null;

  @JsonProperty("endTime")
  private Long endTime = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    PUBLISHED("PUBLISHED"),
    
    DRAFT("DRAFT");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("state")
  private StateEnum state = null;

  @JsonProperty("ps")
  private String ps = null;

  public GrabSeason seasonId(String seasonId) {
    this.seasonId = seasonId;
    return this;
  }

   /**
   * 场次的id
   * @return seasonId
  **/
  @ApiModelProperty(value = "场次的id")
  public String getSeasonId() {
    return seasonId;
  }

  public void setSeasonId(String seasonId) {
    this.seasonId = seasonId;
  }

  public GrabSeason startTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * 场次开始时间
   * @return startTime
  **/
  @ApiModelProperty(value = "场次开始时间")
  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public GrabSeason endTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * 场次结束时间
   * @return endTime
  **/
  @ApiModelProperty(value = "场次结束时间")
  public Long getEndTime() {
    return endTime;
  }

  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  public GrabSeason state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public GrabSeason ps(String ps) {
    this.ps = ps;
    return this;
  }

   /**
   * 备注
   * @return ps
  **/
  @ApiModelProperty(value = "备注")
  public String getPs() {
    return ps;
  }

  public void setPs(String ps) {
    this.ps = ps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrabSeason grabSeason = (GrabSeason) o;
    return Objects.equals(this.seasonId, grabSeason.seasonId) &&
        Objects.equals(this.startTime, grabSeason.startTime) &&
        Objects.equals(this.endTime, grabSeason.endTime) &&
        Objects.equals(this.state, grabSeason.state) &&
        Objects.equals(this.ps, grabSeason.ps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seasonId, startTime, endTime, state, ps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrabSeason {\n");
    
    sb.append("    seasonId: ").append(toIndentedString(seasonId)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    ps: ").append(toIndentedString(ps)).append("\n");
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

