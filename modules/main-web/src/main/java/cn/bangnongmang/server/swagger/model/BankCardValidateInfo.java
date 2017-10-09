package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 银行卡验证信息
 */
@ApiModel(description = "银行卡验证信息")

public class BankCardValidateInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("cardNum")
  private String cardNum = null;

  @JsonProperty("bank")
  private String bank = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("phone")
  private String phone = null;

  public BankCardValidateInfo cardNum(String cardNum) {
    this.cardNum = cardNum;
    return this;
  }

   /**
   * Get cardNum
   * @return cardNum
  **/
  @ApiModelProperty(value = "")
  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BankCardValidateInfo bank(String bank) {
    this.bank = bank;
    return this;
  }

   /**
   * Get bank
   * @return bank
  **/
  @ApiModelProperty(value = "")
  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public BankCardValidateInfo type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BankCardValidateInfo phone(String phone) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankCardValidateInfo bankCardValidateInfo = (BankCardValidateInfo) o;
    return Objects.equals(this.cardNum, bankCardValidateInfo.cardNum) &&
        Objects.equals(this.bank, bankCardValidateInfo.bank) &&
        Objects.equals(this.type, bankCardValidateInfo.type) &&
        Objects.equals(this.phone, bankCardValidateInfo.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardNum, bank, type, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankCardValidateInfo {\n");
    
    sb.append("    cardNum: ").append(toIndentedString(cardNum)).append("\n");
    sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

