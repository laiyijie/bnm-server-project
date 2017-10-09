package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class AccountRealNameAuthCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountRealNameAuthCriteria() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeEqualTo(Long value) {
            addCriterion("update_time =", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThan(Long value) {
            addCriterion("update_time >", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThan(Long value) {
            addCriterion("update_time <", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIn(List<Long> values) {
            addCriterion("update_time in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "update_time");
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

        public Criteria andId_card_numberIsNull() {
            addCriterion("id_card_number is null");
            return (Criteria) this;
        }

        public Criteria andId_card_numberIsNotNull() {
            addCriterion("id_card_number is not null");
            return (Criteria) this;
        }

        public Criteria andId_card_numberEqualTo(String value) {
            addCriterion("id_card_number =", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberNotEqualTo(String value) {
            addCriterion("id_card_number <>", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberGreaterThan(String value) {
            addCriterion("id_card_number >", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_number >=", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberLessThan(String value) {
            addCriterion("id_card_number <", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberLessThanOrEqualTo(String value) {
            addCriterion("id_card_number <=", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberLike(String value) {
            addCriterion("id_card_number like", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberNotLike(String value) {
            addCriterion("id_card_number not like", value, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberIn(List<String> values) {
            addCriterion("id_card_number in", values, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberNotIn(List<String> values) {
            addCriterion("id_card_number not in", values, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberBetween(String value1, String value2) {
            addCriterion("id_card_number between", value1, value2, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andId_card_numberNotBetween(String value1, String value2) {
            addCriterion("id_card_number not between", value1, value2, "id_card_number");
            return (Criteria) this;
        }

        public Criteria andReal_nameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andReal_nameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andReal_nameEqualTo(String value) {
            addCriterion("real_name =", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameGreaterThan(String value) {
            addCriterion("real_name >", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameLessThan(String value) {
            addCriterion("real_name <", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameLike(String value) {
            addCriterion("real_name like", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameNotLike(String value) {
            addCriterion("real_name not like", value, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameIn(List<String> values) {
            addCriterion("real_name in", values, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "real_name");
            return (Criteria) this;
        }

        public Criteria andReal_nameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "real_name");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonIsNull() {
            addCriterion("failed_reason is null");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonIsNotNull() {
            addCriterion("failed_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonEqualTo(String value) {
            addCriterion("failed_reason =", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonNotEqualTo(String value) {
            addCriterion("failed_reason <>", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonGreaterThan(String value) {
            addCriterion("failed_reason >", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonGreaterThanOrEqualTo(String value) {
            addCriterion("failed_reason >=", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonLessThan(String value) {
            addCriterion("failed_reason <", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonLessThanOrEqualTo(String value) {
            addCriterion("failed_reason <=", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonLike(String value) {
            addCriterion("failed_reason like", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonNotLike(String value) {
            addCriterion("failed_reason not like", value, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonIn(List<String> values) {
            addCriterion("failed_reason in", values, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonNotIn(List<String> values) {
            addCriterion("failed_reason not in", values, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonBetween(String value1, String value2) {
            addCriterion("failed_reason between", value1, value2, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andFailed_reasonNotBetween(String value1, String value2) {
            addCriterion("failed_reason not between", value1, value2, "failed_reason");
            return (Criteria) this;
        }

        public Criteria andDown_sideIsNull() {
            addCriterion("down_side is null");
            return (Criteria) this;
        }

        public Criteria andDown_sideIsNotNull() {
            addCriterion("down_side is not null");
            return (Criteria) this;
        }

        public Criteria andDown_sideEqualTo(String value) {
            addCriterion("down_side =", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideNotEqualTo(String value) {
            addCriterion("down_side <>", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideGreaterThan(String value) {
            addCriterion("down_side >", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideGreaterThanOrEqualTo(String value) {
            addCriterion("down_side >=", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideLessThan(String value) {
            addCriterion("down_side <", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideLessThanOrEqualTo(String value) {
            addCriterion("down_side <=", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideLike(String value) {
            addCriterion("down_side like", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideNotLike(String value) {
            addCriterion("down_side not like", value, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideIn(List<String> values) {
            addCriterion("down_side in", values, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideNotIn(List<String> values) {
            addCriterion("down_side not in", values, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideBetween(String value1, String value2) {
            addCriterion("down_side between", value1, value2, "down_side");
            return (Criteria) this;
        }

        public Criteria andDown_sideNotBetween(String value1, String value2) {
            addCriterion("down_side not between", value1, value2, "down_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideIsNull() {
            addCriterion("up_side is null");
            return (Criteria) this;
        }

        public Criteria andUp_sideIsNotNull() {
            addCriterion("up_side is not null");
            return (Criteria) this;
        }

        public Criteria andUp_sideEqualTo(String value) {
            addCriterion("up_side =", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideNotEqualTo(String value) {
            addCriterion("up_side <>", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideGreaterThan(String value) {
            addCriterion("up_side >", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideGreaterThanOrEqualTo(String value) {
            addCriterion("up_side >=", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideLessThan(String value) {
            addCriterion("up_side <", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideLessThanOrEqualTo(String value) {
            addCriterion("up_side <=", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideLike(String value) {
            addCriterion("up_side like", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideNotLike(String value) {
            addCriterion("up_side not like", value, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideIn(List<String> values) {
            addCriterion("up_side in", values, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideNotIn(List<String> values) {
            addCriterion("up_side not in", values, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideBetween(String value1, String value2) {
            addCriterion("up_side between", value1, value2, "up_side");
            return (Criteria) this;
        }

        public Criteria andUp_sideNotBetween(String value1, String value2) {
            addCriterion("up_side not between", value1, value2, "up_side");
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