package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets AuthState
 */
public enum AuthState {
  
  WAITING_AUTH("WAITING_AUTH"),
  
  AUTHED("AUTHED"),
  
  DENIED("DENIED");

  private String value;

  AuthState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AuthState fromValue(String text) {
    for (AuthState b : AuthState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

