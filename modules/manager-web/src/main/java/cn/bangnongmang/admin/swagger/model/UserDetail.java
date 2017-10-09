package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.admin.swagger.model.UserGeo;
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

public class UserDetail extends UserBasic implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("region")
  private String region = null;

  @JsonProperty("sex")
  private String sex = null;

  @JsonProperty("age")
  private String age = null;

  public UserDetail phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * 用户的手机号
   * @return phone
  **/
  @ApiModelProperty(value = "用户的手机号")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetail userDetail = (UserDetail) o;
    return Objects.equals(this.phone, userDetail.phone) &&
        Objects.equals(this.region, userDetail.region) &&
        Objects.equals(this.sex, userDetail.sex) &&
        Objects.equals(this.age, userDetail.age) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phone, region, sex, age, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
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

