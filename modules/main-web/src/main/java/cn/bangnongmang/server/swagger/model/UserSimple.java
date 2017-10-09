package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
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
 * UserSimple
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = FriendSimple.class, name = "FriendSimple"),
  @JsonSubTypes.Type(value = UserDetail.class, name = "UserDetail"),
  @JsonSubTypes.Type(value = FriendDetail.class, name = "FriendDetail"),
  @JsonSubTypes.Type(value = FriendRequest.class, name = "FriendRequest"),
})

public class UserSimple  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("portraitUrl")
  private String portraitUrl = null;

  @JsonProperty("leaderStar")
  private Double leaderStar = null;

  @JsonProperty("memberStar")
  private Double memberStar = null;

  @JsonProperty("authed")
  private Boolean authed = null;

  /**
   * 用户的类型：  * DRIVER - 普通机手  * FARMER - 农户  * DRIVER_LEADER - 队长 
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

  public UserSimple uid(Long uid) {
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

  public UserSimple name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 用户的姓名，如果没有认证会返回 未认证用户
   * @return name
  **/
  @ApiModelProperty(value = "用户的姓名，如果没有认证会返回 未认证用户")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserSimple portraitUrl(String portraitUrl) {
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

  public UserSimple leaderStar(Double leaderStar) {
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

  public UserSimple memberStar(Double memberStar) {
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

  public UserSimple authed(Boolean authed) {
    this.authed = authed;
    return this;
  }

   /**
   * 是否实名认证过
   * @return authed
  **/
  @ApiModelProperty(value = "是否实名认证过")
  public Boolean getAuthed() {
    return authed;
  }

  public void setAuthed(Boolean authed) {
    this.authed = authed;
  }

  public UserSimple role(RoleEnum role) {
    this.role = role;
    return this;
  }

   /**
   * 用户的类型：  * DRIVER - 普通机手  * FARMER - 农户  * DRIVER_LEADER - 队长 
   * @return role
  **/
  @ApiModelProperty(value = "用户的类型：  * DRIVER - 普通机手  * FARMER - 农户  * DRIVER_LEADER - 队长 ")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSimple userSimple = (UserSimple) o;
    return Objects.equals(this.uid, userSimple.uid) &&
        Objects.equals(this.name, userSimple.name) &&
        Objects.equals(this.portraitUrl, userSimple.portraitUrl) &&
        Objects.equals(this.leaderStar, userSimple.leaderStar) &&
        Objects.equals(this.memberStar, userSimple.memberStar) &&
        Objects.equals(this.authed, userSimple.authed) &&
        Objects.equals(this.role, userSimple.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, name, portraitUrl, leaderStar, memberStar, authed, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSimple {\n");
    
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    portraitUrl: ").append(toIndentedString(portraitUrl)).append("\n");
    sb.append("    leaderStar: ").append(toIndentedString(leaderStar)).append("\n");
    sb.append("    memberStar: ").append(toIndentedString(memberStar)).append("\n");
    sb.append("    authed: ").append(toIndentedString(authed)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

