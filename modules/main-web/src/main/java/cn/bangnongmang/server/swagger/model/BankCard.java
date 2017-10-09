package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 银行卡信息
 */
@ApiModel(description = "银行卡信息")

public class BankCard  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("cardNum")
  private String cardNum = null;

  @JsonProperty("bank")
  private String bank = null;

  @JsonProperty("bankName")
  private String bankName = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("phone")
  private String phone = null;

  public BankCard uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * 属于谁
   * @return uid
  **/
  @ApiModelProperty(value = "属于谁")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public BankCard cardNum(String cardNum) {
    this.cardNum = cardNum;
    return this;
  }

   /**
   * 银行卡卡号
   * @return cardNum
  **/
  @ApiModelProperty(value = "银行卡卡号")
  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BankCard bank(String bank) {
    this.bank = bank;
    return this;
  }

   /**
   * 属于哪个银行
   * @return bank
  **/
  @ApiModelProperty(value = "属于哪个银行")
  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public BankCard bankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

   /**
   * 银行中文名
   * @return bankName
  **/
  @ApiModelProperty(value = "银行中文名")
  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public BankCard type(String type) {
    this.type = type;
    return this;
  }

   /**
   * 类型
   * @return type
  **/
  @ApiModelProperty(value = "类型")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BankCard id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * 唯一标识
   * @return id
  **/
  @ApiModelProperty(value = "唯一标识")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BankCard phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * 预留手机号
   * @return phone
  **/
  @ApiModelProperty(value = "预留手机号")
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
    BankCard bankCard = (BankCard) o;
    return Objects.equals(this.uid, bankCard.uid) &&
        Objects.equals(this.cardNum, bankCard.cardNum) &&
        Objects.equals(this.bank, bankCard.bank) &&
        Objects.equals(this.bankName, bankCard.bankName) &&
        Objects.equals(this.type, bankCard.type) &&
        Objects.equals(this.id, bankCard.id) &&
        Objects.equals(this.phone, bankCard.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, cardNum, bank, bankName, type, id, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankCard {\n");
    
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    cardNum: ").append(toIndentedString(cardNum)).append("\n");
    sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

