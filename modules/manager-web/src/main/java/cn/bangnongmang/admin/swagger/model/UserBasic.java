package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.UserGeo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * UserBasic
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = UserDetail.class, name = "UserDetail"),
  @JsonSubTypes.Type(value = RealNameAuthDetail.class, name = "RealNameAuthDetail"),
})

public class UserBasic  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phoneStar")
  private String phoneStar = null;

  @JsonProperty("createTime")
  private Long createTime = null;

  /**
   * 用户的类型：  * DRIVER - 机手  * FARMER - 农户 
   */
  public enum RoleEnum {
    DRIVER("DRIVER"),
    
    FARMER("FARMER"),
    
    DRIVER_LEADER("DRIVER_LEADER");

    private String value;

    RoleEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoleEnum fromValue(String text) {
      for (RoleEnum b : RoleEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("role")
  private RoleEnum role = RoleEnum.DRIVER;

  @JsonProperty("leader")
  private Boolean leader = null;

  /**
   * Gets or Sets authed
   */
  public enum AuthedEnum {
    WAITING_AUTH("WAITING_AUTH"),
    
    AUTHED("AUTHED"),
    
    DENIED("DENIED");

    private String value;

    AuthedEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AuthedEnum fromValue(String text) {
      for (AuthedEnum b : AuthedEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("authed")
  private AuthedEnum authed = null;

  @JsonProperty("portraitUrl")
  private String portraitUrl = null;

  @JsonProperty("leaderStar")
  private Double leaderStar = null;

  @JsonProperty("memberStar")
  private Double memberStar = null;

  @JsonProperty("geoInfo")
  private UserGeo geoInfo = null;

  public UserBasic uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * 用户的唯一标识
   * @return uid
  **/
  @ApiModelProperty(value = "用户的唯一标识")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public UserBasic name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 用户的名字
   * @return name
  **/
  @ApiModelProperty(value = "用户的名字")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserBasic phoneStar(String phoneStar) {
    this.phoneStar = phoneStar;
    return this;
  }

   /**
   * 用户的手机号，打了马赛克的
   * @return phoneStar
  **/
  @ApiModelProperty(value = "用户的手机号，打了马赛克的")
  public String getPhoneStar() {
    return phoneStar;
  }

  public void setPhoneStar(String phoneStar) {
    this.phoneStar = phoneStar;
  }

  public UserBasic createTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * 创建时间，单位 ms
   * @return createTime
  **/
  @ApiModelProperty(value = "创建时间，单位 ms")
  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public UserBasic role(RoleEnum role) {
    this.role = role;
    return this;
  }

   /**
   * 用户的类型：  * DRIVER - 机手  * FARMER - 农户 
   * @return role
  **/
  @ApiModelProperty(value = "用户的类型：  * DRIVER - 机手  * FARMER - 农户 ")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public UserBasic leader(Boolean leader) {
    this.leader = leader;
    return this;
  }

   /**
   * 是不是队长
   * @return leader
  **/
  @ApiModelProperty(value = "是不是队长")
  public Boolean getLeader() {
    return leader;
  }

  public void setLeader(Boolean leader) {
    this.leader = leader;
  }

  public UserBasic authed(AuthedEnum authed) {
    this.authed = authed;
    return this;
  }

   /**
   * Get authed
   * @return authed
  **/
  @ApiModelProperty(value = "")
  public AuthedEnum getAuthed() {
    return authed;
  }

  public void setAuthed(AuthedEnum authed) {
    this.authed = authed;
  }

  public UserBasic portraitUrl(String portraitUrl) {
    this.portraitUrl = portraitUrl;
    return this;
  }

   /**
   * 用户的头像链接，需要从阿里云获取
   * @return portraitUrl
  **/
  @ApiModelProperty(value = "用户的头像链接，需要从阿里云获取")
  public String getPortraitUrl() {
    return portraitUrl;
  }

  public void setPortraitUrl(String portraitUrl) {
    this.portraitUrl = portraitUrl;
  }

  public UserBasic leaderStar(Double leaderStar) {
    this.leaderStar = leaderStar;
    return this;
  }

   /**
   * 队长分，非队长没有，总分五分
   * @return leaderStar
  **/
  @ApiModelProperty(value = "队长分，非队长没有，总分五分")
  public Double getLeaderStar() {
    return leaderStar;
  }

  public void setLeaderStar(Double leaderStar) {
    this.leaderStar = leaderStar;
  }

  public UserBasic memberStar(Double memberStar) {
    this.memberStar = memberStar;
    return this;
  }

   /**
   * 队员分，总分五分
   * @return memberStar
  **/
  @ApiModelProperty(value = "队员分，总分五分")
  public Double getMemberStar() {
    return memberStar;
  }

  public void setMemberStar(Double memberStar) {
    this.memberStar = memberStar;
  }

  public UserBasic geoInfo(UserGeo geoInfo) {
    this.geoInfo = geoInfo;
    return this;
  }

   /**
   * Get geoInfo
   * @return geoInfo
  **/
  @ApiModelProperty(value = "")
  public UserGeo getGeoInfo() {
    return geoInfo;
  }

  public void setGeoInfo(UserGeo geoInfo) {
    this.geoInfo = geoInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBasic userBasic = (UserBasic) o;
    return Objects.equals(this.uid, userBasic.uid) &&
        Objects.equals(this.name, userBasic.name) &&
        Objects.equals(this.phoneStar, userBasic.phoneStar) &&
        Objects.equals(this.createTime, userBasic.createTime) &&
        Objects.equals(this.role, userBasic.role) &&
        Objects.equals(this.leader, userBasic.leader) &&
        Objects.equals(this.authed, userBasic.authed) &&
        Objects.equals(this.portraitUrl, userBasic.portraitUrl) &&
        Objects.equals(this.leaderStar, userBasic.leaderStar) &&
        Objects.equals(this.memberStar, userBasic.memberStar) &&
        Objects.equals(this.geoInfo, userBasic.geoInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, name, phoneStar, createTime, role, leader, authed, portraitUrl, leaderStar, memberStar, geoInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserBasic {\n");
    
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phoneStar: ").append(toIndentedString(phoneStar)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    leader: ").append(toIndentedString(leader)).append("\n");
    sb.append("    authed: ").append(toIndentedString(authed)).append("\n");
    sb.append("    portraitUrl: ").append(toIndentedString(portraitUrl)).append("\n");
    sb.append("    leaderStar: ").append(toIndentedString(leaderStar)).append("\n");
    sb.append("    memberStar: ").append(toIndentedString(memberStar)).append("\n");
    sb.append("    geoInfo: ").append(toIndentedString(geoInfo)).append("\n");
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

