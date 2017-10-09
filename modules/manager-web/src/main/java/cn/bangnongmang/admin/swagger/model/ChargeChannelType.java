package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ChargeChannelType
 */
public enum ChargeChannelType {
  
  UPACP("upacp"),
  
  WX_APP("wx_app"),
  
  WX_PUB("wx_pub");

  private String value;

  ChargeChannelType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ChargeChannelType fromValue(String text) {
    for (ChargeChannelType b : ChargeChannelType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

