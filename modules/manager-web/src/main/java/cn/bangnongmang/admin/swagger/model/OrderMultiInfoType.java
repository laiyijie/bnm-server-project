package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets OrderMultiInfoType
 */
public enum OrderMultiInfoType {
  
  SIZE("SIZE"),
  
  PHOTO("PHOTO");

  private String value;

  OrderMultiInfoType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OrderMultiInfoType fromValue(String text) {
    for (OrderMultiInfoType b : OrderMultiInfoType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

