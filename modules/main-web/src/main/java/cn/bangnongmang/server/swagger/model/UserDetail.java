package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import cn.bangnongmang.server.swagger.model.UserSimple;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * UserDetail
 */

public class UserDetail extends UserSimple implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("region")
  private String region = null;

  @JsonProperty("sex")
  private String sex = null;

  @JsonProperty("age")
  private String age = null;

  @JsonProperty("friend")
  private Boolean friend = null;

  public UserDetail region(String region) {
    this.region = region;
    return this;
  }

   /**
   * 生源地，通过身份证号确认的
   * @return region
  **/
  @ApiModelProperty(value = "生源地，通过身份证号确认的")
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public UserDetail sex(String sex) {
    this.sex = sex;
    return this;
  }

   /**
   * 性别，通过身份证号确认的
   * @return sex
  **/
  @ApiModelProperty(value = "性别，通过身份证号确认的")
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public UserDetail age(String age) {
    this.age = age;
    return this;
  }

   /**
   * 年龄，通过身份证号确认
   * @return age
  **/
  @ApiModelProperty(value = "年龄，通过身份证号确认")
  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public UserDetail friend(Boolean friend) {
    this.friend = friend;
    return this;
  }

   /**
   * 是否是好友
   * @return friend
  **/
  @ApiModelProperty(value = "是否是好友")
  public Boolean getFriend() {
    return friend;
  }

  public void setFriend(Boolean friend) {
    this.friend = friend;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetail userDetail = (UserDetail) o;
    return Objects.equals(this.region, userDetail.region) &&
        Objects.equals(this.sex, userDetail.sex) &&
        Objects.equals(this.age, userDetail.age) &&
        Objects.equals(this.friend, userDetail.friend) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(region, sex, age, friend, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    friend: ").append(toIndentedString(friend)).append("\n");
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

