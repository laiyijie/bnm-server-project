package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 钱包，单位（分）
 */
@ApiModel(description = "钱包，单位（分）")

public class Wallet  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("money")
  private Integer money = null;

  @JsonProperty("provisions")
  private Integer provisions = null;

  @JsonProperty("arrears")
  private Integer arrears = null;

  @JsonProperty("insurance")
  private Integer insurance = null;

  @JsonProperty("wattingIn")
  private Integer wattingIn = null;

  public Wallet uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * 钱包属于谁
   * @return uid
  **/
  @ApiModelProperty(value = "钱包属于谁")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Wallet money(Integer money) {
    this.money = money;
    return this;
  }

   /**
   * 当前可以提现的钱
   * @return money
  **/
  @ApiModelProperty(value = "当前可以提现的钱")
  public Integer getMoney() {
    return money;
  }

  public void setMoney(Integer money) {
    this.money = money;
  }

  public Wallet provisions(Integer provisions) {
    this.provisions = provisions;
    return this;
  }

   /**
   * 农户备付金
   * @return provisions
  **/
  @ApiModelProperty(value = "农户备付金")
  public Integer getProvisions() {
    return provisions;
  }

  public void setProvisions(Integer provisions) {
    this.provisions = provisions;
  }

  public Wallet arrears(Integer arrears) {
    this.arrears = arrears;
    return this;
  }

   /**
   * 农户欠款
   * @return arrears
  **/
  @ApiModelProperty(value = "农户欠款")
  public Integer getArrears() {
    return arrears;
  }

  public void setArrears(Integer arrears) {
    this.arrears = arrears;
  }

  public Wallet insurance(Integer insurance) {
    this.insurance = insurance;
    return this;
  }

   /**
   * 农机手保证金
   * @return insurance
  **/
  @ApiModelProperty(value = "农机手保证金")
  public Integer getInsurance() {
    return insurance;
  }

  public void setInsurance(Integer insurance) {
    this.insurance = insurance;
  }

  public Wallet wattingIn(Integer wattingIn) {
    this.wattingIn = wattingIn;
    return this;
  }

   /**
   * 不可提现的钱
   * @return wattingIn
  **/
  @ApiModelProperty(value = "不可提现的钱")
  public Integer getWattingIn() {
    return wattingIn;
  }

  public void setWattingIn(Integer wattingIn) {
    this.wattingIn = wattingIn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wallet wallet = (Wallet) o;
    return Objects.equals(this.uid, wallet.uid) &&
        Objects.equals(this.money, wallet.money) &&
        Objects.equals(this.provisions, wallet.provisions) &&
        Objects.equals(this.arrears, wallet.arrears) &&
        Objects.equals(this.insurance, wallet.insurance) &&
        Objects.equals(this.wattingIn, wallet.wattingIn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, money, provisions, arrears, insurance, wattingIn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Wallet {\n");
    
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
    sb.append("    provisions: ").append(toIndentedString(provisions)).append("\n");
    sb.append("    arrears: ").append(toIndentedString(arrears)).append("\n");
    sb.append("    insurance: ").append(toIndentedString(insurance)).append("\n");
    sb.append("    wattingIn: ").append(toIndentedString(wattingIn)).append("\n");
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

