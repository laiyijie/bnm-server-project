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
 * PhoneFriend
 */

public class PhoneFriend  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("user")
  private UserSimple user = null;

  @JsonProperty("phone")
  private String phone = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    FRIEND("Friend"),
    
    USER("USER"),
    
    NOTUSER("NOTUSER");

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

  public PhoneFriend user(UserSimple user) {
    this.user = user;
    return this;
  }

   /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")
  public UserSimple getUser() {
    return user;
  }

  public void setUser(UserSimple user) {
    this.user = user;
  }

  public PhoneFriend phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @ApiModelProperty(value = "")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public PhoneFriend state(StateEnum state) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneFriend phoneFriend = (PhoneFriend) o;
    return Objects.equals(this.user, phoneFriend.user) &&
        Objects.equals(this.phone, phoneFriend.phone) &&
        Objects.equals(this.state, phoneFriend.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, phone, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneFriend {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

