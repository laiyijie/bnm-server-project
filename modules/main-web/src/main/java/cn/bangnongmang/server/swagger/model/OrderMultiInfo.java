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
 * 订单的多维度数据，根据type的不同，extra_info 也是不同的，现在包含两种 
 */
@ApiModel(description = "订单的多维度数据，根据type的不同，extra_info 也是不同的，现在包含两种 ")

public class OrderMultiInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("order_id")
  private String orderId = null;

  @JsonProperty("create_time")
  private Long createTime = null;

  @JsonProperty("update_time")
  private Long updateTime = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    SIZE("SIZE"),
    
    PHOTO("PHOTO");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("extra_info")
  private String extraInfo = null;

  public OrderMultiInfo id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderMultiInfo orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(value = "")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public OrderMultiInfo createTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * Get createTime
   * @return createTime
  **/
  @ApiModelProperty(value = "")
  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public OrderMultiInfo updateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

   /**
   * Get updateTime
   * @return updateTime
  **/
  @ApiModelProperty(value = "")
  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  public OrderMultiInfo type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public OrderMultiInfo content(String content) {
    this.content = content;
    return this;
  }

   /**
   * 一句话内容
   * @return content
  **/
  @ApiModelProperty(value = "一句话内容")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public OrderMultiInfo extraInfo(String extraInfo) {
    this.extraInfo = extraInfo;
    return this;
  }

   /**
   * 根据不同的type有不同的extra_info，如果是 size 则没有，如果是 photo那么就是一个string 的list 的json串
   * @return extraInfo
  **/
  @ApiModelProperty(value = "根据不同的type有不同的extra_info，如果是 size 则没有，如果是 photo那么就是一个string 的list 的json串")
  public String getExtraInfo() {
    return extraInfo;
  }

  public void setExtraInfo(String extraInfo) {
    this.extraInfo = extraInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderMultiInfo orderMultiInfo = (OrderMultiInfo) o;
    return Objects.equals(this.id, orderMultiInfo.id) &&
        Objects.equals(this.orderId, orderMultiInfo.orderId) &&
        Objects.equals(this.createTime, orderMultiInfo.createTime) &&
        Objects.equals(this.updateTime, orderMultiInfo.updateTime) &&
        Objects.equals(this.type, orderMultiInfo.type) &&
        Objects.equals(this.content, orderMultiInfo.content) &&
        Objects.equals(this.extraInfo, orderMultiInfo.extraInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderId, createTime, updateTime, type, content, extraInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderMultiInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    extraInfo: ").append(toIndentedString(extraInfo)).append("\n");
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

