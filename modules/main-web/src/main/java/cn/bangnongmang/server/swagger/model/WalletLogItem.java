package cn.bangnongmang.server.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * 钱包日志
 */
@ApiModel(description = "钱包日志")

public class WalletLogItem  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("uid")
  private Long uid = null;

  @JsonProperty("logId")
  private String logId = null;

  @JsonProperty("time")
  private Long time = null;

  @JsonProperty("money")
  private Integer money = null;

  @JsonProperty("connectOrderId")
  private String connectOrderId = null;

  @JsonProperty("detail")
  private String detail = null;

  /**
   * 交易类型
   */
  public enum TradeTypeEnum {
    INCOME("INCOME"),
    
    REFUND("REFUND"),
    
    REFUND_INSURANCE("REFUND_INSURANCE"),
    
    PAY("PAY"),
    
    PROVISIONS_PAY("PROVISIONS_PAY"),
    
    PROVISIONS("PROVISIONS"),
    
    PAY_INSURANCE("PAY_INSURANCE"),
    
    INCOME_UN_FREEZE("INCOME_UN_FREEZE"),
    
    RECHARGE("RECHARGE"),
    
    WITHDRAW("WITHDRAW");

    private String value;

    TradeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TradeTypeEnum fromValue(String text) {
      for (TradeTypeEnum b : TradeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("tradeType")
  private TradeTypeEnum tradeType = null;

  /**
   * 日志状态
   */
  public enum StateEnum {
    DRAFT("DRAFT"),
    
    DONE("DONE"),
    
    CANCEL("CANCEL");

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

  public WalletLogItem uid(Long uid) {
    this.uid = uid;
    return this;
  }

   /**
   * 是谁的记录
   * @return uid
  **/
  @ApiModelProperty(value = "是谁的记录")
  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public WalletLogItem logId(String logId) {
    this.logId = logId;
    return this;
  }

   /**
   * logid
   * @return logId
  **/
  @ApiModelProperty(value = "logid")
  public String getLogId() {
    return logId;
  }

  public void setLogId(String logId) {
    this.logId = logId;
  }

  public WalletLogItem time(Long time) {
    this.time = time;
    return this;
  }

   /**
   * 记录时间
   * @return time
  **/
  @ApiModelProperty(value = "记录时间")
  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public WalletLogItem money(Integer money) {
    this.money = money;
    return this;
  }

   /**
   * 金额
   * @return money
  **/
  @ApiModelProperty(value = "金额")
  public Integer getMoney() {
    return money;
  }

  public void setMoney(Integer money) {
    this.money = money;
  }

  public WalletLogItem connectOrderId(String connectOrderId) {
    this.connectOrderId = connectOrderId;
    return this;
  }

   /**
   * 关联的Order
   * @return connectOrderId
  **/
  @ApiModelProperty(value = "关联的Order")
  public String getConnectOrderId() {
    return connectOrderId;
  }

  public void setConnectOrderId(String connectOrderId) {
    this.connectOrderId = connectOrderId;
  }

  public WalletLogItem detail(String detail) {
    this.detail = detail;
    return this;
  }

   /**
   * 交易详情
   * @return detail
  **/
  @ApiModelProperty(value = "交易详情")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public WalletLogItem tradeType(TradeTypeEnum tradeType) {
    this.tradeType = tradeType;
    return this;
  }

   /**
   * 交易类型
   * @return tradeType
  **/
  @ApiModelProperty(value = "交易类型")
  public TradeTypeEnum getTradeType() {
    return tradeType;
  }

  public void setTradeType(TradeTypeEnum tradeType) {
    this.tradeType = tradeType;
  }

  public WalletLogItem state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * 日志状态
   * @return state
  **/
  @ApiModelProperty(value = "日志状态")
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
    WalletLogItem walletLogItem = (WalletLogItem) o;
    return Objects.equals(this.uid, walletLogItem.uid) &&
        Objects.equals(this.logId, walletLogItem.logId) &&
        Objects.equals(this.time, walletLogItem.time) &&
        Objects.equals(this.money, walletLogItem.money) &&
        Objects.equals(this.connectOrderId, walletLogItem.connectOrderId) &&
        Objects.equals(this.detail, walletLogItem.detail) &&
        Objects.equals(this.tradeType, walletLogItem.tradeType) &&
        Objects.equals(this.state, walletLogItem.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, logId, time, money, connectOrderId, detail, tradeType, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WalletLogItem {\n");
    
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    logId: ").append(toIndentedString(logId)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
    sb.append("    connectOrderId: ").append(toIndentedString(connectOrderId)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    tradeType: ").append(toIndentedString(tradeType)).append("\n");
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

