package cn.bangnongmang.admin.swagger.model;

import java.util.Objects;
import cn.bangnongmang.admin.swagger.model.Option;
import cn.bangnongmang.admin.swagger.model.OrderBasic;
import cn.bangnongmang.admin.swagger.model.OrderGeo;
import cn.bangnongmang.admin.swagger.model.OrderTag;
import cn.bangnongmang.admin.swagger.model.UserBasic;
import cn.bangnongmang.admin.swagger.model.WorkingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.constraints.*;
/**
 * OrderDetail
 */

public class OrderDetail extends OrderBasic implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("belongto")
  private UserBasic belongto = null;

  @JsonProperty("tags")
  private List<OrderTag> tags = null;

  @JsonProperty("inviteLeader")
  private UserBasic inviteLeader = null;

  @JsonProperty("farmerDiscount")
  private Integer farmerDiscount = null;

  @JsonProperty("farmerPrepayRate")
  private Integer farmerPrepayRate = null;

  @JsonProperty("driverInsurance")
  private Integer driverInsurance = null;

  @JsonProperty("leaderBonus")
  private Integer leaderBonus = null;

  @JsonProperty("companyBonus")
  private Integer companyBonus = null;

  public OrderDetail belongto(UserBasic belongto) {
    this.belongto = belongto;
    return this;
  }

   /**
   * Get belongto
   * @return belongto
  **/
  @ApiModelProperty(value = "")
  public UserBasic getBelongto() {
    return belongto;
  }

  public void setBelongto(UserBasic belongto) {
    this.belongto = belongto;
  }

  public OrderDetail tags(List<OrderTag> tags) {
    this.tags = tags;
    return this;
  }

  public OrderDetail addTagsItem(OrderTag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<OrderTag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * tags
   * @return tags
  **/
  @ApiModelProperty(value = "tags")
  public List<OrderTag> getTags() {
    return tags;
  }

  public void setTags(List<OrderTag> tags) {
    this.tags = tags;
  }

  public OrderDetail inviteLeader(UserBasic inviteLeader) {
    this.inviteLeader = inviteLeader;
    return this;
  }

   /**
   * Get inviteLeader
   * @return inviteLeader
  **/
  @ApiModelProperty(value = "")
  public UserBasic getInviteLeader() {
    return inviteLeader;
  }

  public void setInviteLeader(UserBasic inviteLeader) {
    this.inviteLeader = inviteLeader;
  }

  public OrderDetail farmerDiscount(Integer farmerDiscount) {
    this.farmerDiscount = farmerDiscount;
    return this;
  }

   /**
   * 对农户的打折 (1-100)
   * @return farmerDiscount
  **/
  @ApiModelProperty(value = "对农户的打折 (1-100)")
  public Integer getFarmerDiscount() {
    return farmerDiscount;
  }

  public void setFarmerDiscount(Integer farmerDiscount) {
    this.farmerDiscount = farmerDiscount;
  }

  public OrderDetail farmerPrepayRate(Integer farmerPrepayRate) {
    this.farmerPrepayRate = farmerPrepayRate;
    return this;
  }

   /**
   * 农户的预付比例（1-100）
   * @return farmerPrepayRate
  **/
  @ApiModelProperty(value = "农户的预付比例（1-100）")
  public Integer getFarmerPrepayRate() {
    return farmerPrepayRate;
  }

  public void setFarmerPrepayRate(Integer farmerPrepayRate) {
    this.farmerPrepayRate = farmerPrepayRate;
  }

  public OrderDetail driverInsurance(Integer driverInsurance) {
    this.driverInsurance = driverInsurance;
    return this;
  }

   /**
   * 接单的农机手需要交的保证金（单位：分）
   * @return driverInsurance
  **/
  @ApiModelProperty(value = "接单的农机手需要交的保证金（单位：分）")
  public Integer getDriverInsurance() {
    return driverInsurance;
  }

  public void setDriverInsurance(Integer driverInsurance) {
    this.driverInsurance = driverInsurance;
  }

  public OrderDetail leaderBonus(Integer leaderBonus) {
    this.leaderBonus = leaderBonus;
    return this;
  }

   /**
   * 队长每亩的作业提成,取值范围（0-10000）意味着万分之几
   * @return leaderBonus
  **/
  @ApiModelProperty(value = "队长每亩的作业提成,取值范围（0-10000）意味着万分之几")
  public Integer getLeaderBonus() {
    return leaderBonus;
  }

  public void setLeaderBonus(Integer leaderBonus) {
    this.leaderBonus = leaderBonus;
  }

  public OrderDetail companyBonus(Integer companyBonus) {
    this.companyBonus = companyBonus;
    return this;
  }

   /**
   * 公司每亩作业的提成,取值范围（0-10000）意味着万分之几
   * @return companyBonus
  **/
  @ApiModelProperty(value = "公司每亩作业的提成,取值范围（0-10000）意味着万分之几")
  public Integer getCompanyBonus() {
    return companyBonus;
  }

  public void setCompanyBonus(Integer companyBonus) {
    this.companyBonus = companyBonus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetail orderDetail = (OrderDetail) o;
    return Objects.equals(this.belongto, orderDetail.belongto) &&
        Objects.equals(this.tags, orderDetail.tags) &&
        Objects.equals(this.inviteLeader, orderDetail.inviteLeader) &&
        Objects.equals(this.farmerDiscount, orderDetail.farmerDiscount) &&
        Objects.equals(this.farmerPrepayRate, orderDetail.farmerPrepayRate) &&
        Objects.equals(this.driverInsurance, orderDetail.driverInsurance) &&
        Objects.equals(this.leaderBonus, orderDetail.leaderBonus) &&
        Objects.equals(this.companyBonus, orderDetail.companyBonus) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(belongto, tags, inviteLeader, farmerDiscount, farmerPrepayRate, driverInsurance, leaderBonus, companyBonus, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetail {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    belongto: ").append(toIndentedString(belongto)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    inviteLeader: ").append(toIndentedString(inviteLeader)).append("\n");
    sb.append("    farmerDiscount: ").append(toIndentedString(farmerDiscount)).append("\n");
    sb.append("    farmerPrepayRate: ").append(toIndentedString(farmerPrepayRate)).append("\n");
    sb.append("    driverInsurance: ").append(toIndentedString(driverInsurance)).append("\n");
    sb.append("    leaderBonus: ").append(toIndentedString(leaderBonus)).append("\n");
    sb.append("    companyBonus: ").append(toIndentedString(companyBonus)).append("\n");
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

