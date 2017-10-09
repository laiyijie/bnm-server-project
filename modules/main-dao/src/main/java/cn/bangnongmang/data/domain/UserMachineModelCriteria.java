package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class UserMachineModelCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserMachineModelCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idIsNull() {
            addCriterion("user_machine_type_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idIsNotNull() {
            addCriterion("user_machine_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idEqualTo(Long value) {
            addCriterion("user_machine_type_id =", value, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idNotEqualTo(Long value) {
            addCriterion("user_machine_type_id <>", value, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idGreaterThan(Long value) {
            addCriterion("user_machine_type_id >", value, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idGreaterThanOrEqualTo(Long value) {
            addCriterion("user_machine_type_id >=", value, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idLessThan(Long value) {
            addCriterion("user_machine_type_id <", value, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idLessThanOrEqualTo(Long value) {
            addCriterion("user_machine_type_id <=", value, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idIn(List<Long> values) {
            addCriterion("user_machine_type_id in", values, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idNotIn(List<Long> values) {
            addCriterion("user_machine_type_id not in", values, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idBetween(Long value1, Long value2) {
            addCriterion("user_machine_type_id between", value1, value2, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andUser_machine_type_idNotBetween(Long value1, Long value2) {
            addCriterion("user_machine_type_id not between", value1, value2, "user_machine_type_id");
            return (Criteria) this;
        }

        public Criteria andModel_brandIsNull() {
            addCriterion("model_brand is null");
            return (Criteria) this;
        }

        public Criteria andModel_brandIsNotNull() {
            addCriterion("model_brand is not null");
            return (Criteria) this;
        }

        public Criteria andModel_brandEqualTo(String value) {
            addCriterion("model_brand =", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandNotEqualTo(String value) {
            addCriterion("model_brand <>", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandGreaterThan(String value) {
            addCriterion("model_brand >", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandGreaterThanOrEqualTo(String value) {
            addCriterion("model_brand >=", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandLessThan(String value) {
            addCriterion("model_brand <", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandLessThanOrEqualTo(String value) {
            addCriterion("model_brand <=", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandLike(String value) {
            addCriterion("model_brand like", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandNotLike(String value) {
            addCriterion("model_brand not like", value, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandIn(List<String> values) {
            addCriterion("model_brand in", values, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandNotIn(List<String> values) {
            addCriterion("model_brand not in", values, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandBetween(String value1, String value2) {
            addCriterion("model_brand between", value1, value2, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_brandNotBetween(String value1, String value2) {
            addCriterion("model_brand not between", value1, value2, "model_brand");
            return (Criteria) this;
        }

        public Criteria andModel_numberIsNull() {
            addCriterion("model_number is null");
            return (Criteria) this;
        }

        public Criteria andModel_numberIsNotNull() {
            addCriterion("model_number is not null");
            return (Criteria) this;
        }

        public Criteria andModel_numberEqualTo(String value) {
            addCriterion("model_number =", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberNotEqualTo(String value) {
            addCriterion("model_number <>", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberGreaterThan(String value) {
            addCriterion("model_number >", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberGreaterThanOrEqualTo(String value) {
            addCriterion("model_number >=", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberLessThan(String value) {
            addCriterion("model_number <", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberLessThanOrEqualTo(String value) {
            addCriterion("model_number <=", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberLike(String value) {
            addCriterion("model_number like", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberNotLike(String value) {
            addCriterion("model_number not like", value, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberIn(List<String> values) {
            addCriterion("model_number in", values, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberNotIn(List<String> values) {
            addCriterion("model_number not in", values, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberBetween(String value1, String value2) {
            addCriterion("model_number between", value1, value2, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_numberNotBetween(String value1, String value2) {
            addCriterion("model_number not between", value1, value2, "model_number");
            return (Criteria) this;
        }

        public Criteria andModel_widthIsNull() {
            addCriterion("model_width is null");
            return (Criteria) this;
        }

        public Criteria andModel_widthIsNotNull() {
            addCriterion("model_width is not null");
            return (Criteria) this;
        }

        public Criteria andModel_widthEqualTo(Double value) {
            addCriterion("model_width =", value, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthNotEqualTo(Double value) {
            addCriterion("model_width <>", value, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthGreaterThan(Double value) {
            addCriterion("model_width >", value, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthGreaterThanOrEqualTo(Double value) {
            addCriterion("model_width >=", value, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthLessThan(Double value) {
            addCriterion("model_width <", value, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthLessThanOrEqualTo(Double value) {
            addCriterion("model_width <=", value, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthIn(List<Double> values) {
            addCriterion("model_width in", values, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthNotIn(List<Double> values) {
            addCriterion("model_width not in", values, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthBetween(Double value1, Double value2) {
            addCriterion("model_width between", value1, value2, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_widthNotBetween(Double value1, Double value2) {
            addCriterion("model_width not between", value1, value2, "model_width");
            return (Criteria) this;
        }

        public Criteria andModel_powerIsNull() {
            addCriterion("model_power is null");
            return (Criteria) this;
        }

        public Criteria andModel_powerIsNotNull() {
            addCriterion("model_power is not null");
            return (Criteria) this;
        }

        public Criteria andModel_powerEqualTo(Double value) {
            addCriterion("model_power =", value, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerNotEqualTo(Double value) {
            addCriterion("model_power <>", value, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerGreaterThan(Double value) {
            addCriterion("model_power >", value, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerGreaterThanOrEqualTo(Double value) {
            addCriterion("model_power >=", value, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerLessThan(Double value) {
            addCriterion("model_power <", value, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerLessThanOrEqualTo(Double value) {
            addCriterion("model_power <=", value, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerIn(List<Double> values) {
            addCriterion("model_power in", values, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerNotIn(List<Double> values) {
            addCriterion("model_power not in", values, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerBetween(Double value1, Double value2) {
            addCriterion("model_power between", value1, value2, "model_power");
            return (Criteria) this;
        }

        public Criteria andModel_powerNotBetween(Double value1, Double value2) {
            addCriterion("model_power not between", value1, value2, "model_power");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoIsNull() {
            addCriterion("special_info is null");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoIsNotNull() {
            addCriterion("special_info is not null");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoEqualTo(String value) {
            addCriterion("special_info =", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoNotEqualTo(String value) {
            addCriterion("special_info <>", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoGreaterThan(String value) {
            addCriterion("special_info >", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoGreaterThanOrEqualTo(String value) {
            addCriterion("special_info >=", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoLessThan(String value) {
            addCriterion("special_info <", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoLessThanOrEqualTo(String value) {
            addCriterion("special_info <=", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoLike(String value) {
            addCriterion("special_info like", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoNotLike(String value) {
            addCriterion("special_info not like", value, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoIn(List<String> values) {
            addCriterion("special_info in", values, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoNotIn(List<String> values) {
            addCriterion("special_info not in", values, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoBetween(String value1, String value2) {
            addCriterion("special_info between", value1, value2, "special_info");
            return (Criteria) this;
        }

        public Criteria andSpecial_infoNotBetween(String value1, String value2) {
            addCriterion("special_info not between", value1, value2, "special_info");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlIsNull() {
            addCriterion("more_info_url is null");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlIsNotNull() {
            addCriterion("more_info_url is not null");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlEqualTo(String value) {
            addCriterion("more_info_url =", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlNotEqualTo(String value) {
            addCriterion("more_info_url <>", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlGreaterThan(String value) {
            addCriterion("more_info_url >", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlGreaterThanOrEqualTo(String value) {
            addCriterion("more_info_url >=", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlLessThan(String value) {
            addCriterion("more_info_url <", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlLessThanOrEqualTo(String value) {
            addCriterion("more_info_url <=", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlLike(String value) {
            addCriterion("more_info_url like", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlNotLike(String value) {
            addCriterion("more_info_url not like", value, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlIn(List<String> values) {
            addCriterion("more_info_url in", values, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlNotIn(List<String> values) {
            addCriterion("more_info_url not in", values, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlBetween(String value1, String value2) {
            addCriterion("more_info_url between", value1, value2, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andMore_info_urlNotBetween(String value1, String value2) {
            addCriterion("more_info_url not between", value1, value2, "more_info_url");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(String value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(String value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(String value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(String value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(String value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(String value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLike(String value) {
            addCriterion("width like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotLike(String value) {
            addCriterion("width not like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<String> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<String> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(String value1, String value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(String value1, String value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andCut_numIsNull() {
            addCriterion("cut_num is null");
            return (Criteria) this;
        }

        public Criteria andCut_numIsNotNull() {
            addCriterion("cut_num is not null");
            return (Criteria) this;
        }

        public Criteria andCut_numEqualTo(Double value) {
            addCriterion("cut_num =", value, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numNotEqualTo(Double value) {
            addCriterion("cut_num <>", value, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numGreaterThan(Double value) {
            addCriterion("cut_num >", value, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numGreaterThanOrEqualTo(Double value) {
            addCriterion("cut_num >=", value, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numLessThan(Double value) {
            addCriterion("cut_num <", value, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numLessThanOrEqualTo(Double value) {
            addCriterion("cut_num <=", value, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numIn(List<Double> values) {
            addCriterion("cut_num in", values, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numNotIn(List<Double> values) {
            addCriterion("cut_num not in", values, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numBetween(Double value1, Double value2) {
            addCriterion("cut_num between", value1, value2, "cut_num");
            return (Criteria) this;
        }

        public Criteria andCut_numNotBetween(Double value1, Double value2) {
            addCriterion("cut_num not between", value1, value2, "cut_num");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}