package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class UserMachineCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserMachineCriteria() {
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

        public Criteria andMachine_model_idIsNull() {
            addCriterion("machine_model_id is null");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idIsNotNull() {
            addCriterion("machine_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idEqualTo(Long value) {
            addCriterion("machine_model_id =", value, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idNotEqualTo(Long value) {
            addCriterion("machine_model_id <>", value, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idGreaterThan(Long value) {
            addCriterion("machine_model_id >", value, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idGreaterThanOrEqualTo(Long value) {
            addCriterion("machine_model_id >=", value, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idLessThan(Long value) {
            addCriterion("machine_model_id <", value, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idLessThanOrEqualTo(Long value) {
            addCriterion("machine_model_id <=", value, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idIn(List<Long> values) {
            addCriterion("machine_model_id in", values, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idNotIn(List<Long> values) {
            addCriterion("machine_model_id not in", values, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idBetween(Long value1, Long value2) {
            addCriterion("machine_model_id between", value1, value2, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andMachine_model_idNotBetween(Long value1, Long value2) {
            addCriterion("machine_model_id not between", value1, value2, "machine_model_id");
            return (Criteria) this;
        }

        public Criteria andBuy_timeIsNull() {
            addCriterion("buy_time is null");
            return (Criteria) this;
        }

        public Criteria andBuy_timeIsNotNull() {
            addCriterion("buy_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuy_timeEqualTo(Long value) {
            addCriterion("buy_time =", value, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeNotEqualTo(Long value) {
            addCriterion("buy_time <>", value, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeGreaterThan(Long value) {
            addCriterion("buy_time >", value, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("buy_time >=", value, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeLessThan(Long value) {
            addCriterion("buy_time <", value, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeLessThanOrEqualTo(Long value) {
            addCriterion("buy_time <=", value, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeIn(List<Long> values) {
            addCriterion("buy_time in", values, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeNotIn(List<Long> values) {
            addCriterion("buy_time not in", values, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeBetween(Long value1, Long value2) {
            addCriterion("buy_time between", value1, value2, "buy_time");
            return (Criteria) this;
        }

        public Criteria andBuy_timeNotBetween(Long value1, Long value2) {
            addCriterion("buy_time not between", value1, value2, "buy_time");
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

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Double value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Double value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Double value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Double value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Double value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Double> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Double> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Double value1, Double value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Double value1, Double value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andIntegrityIsNull() {
            addCriterion("integrity is null");
            return (Criteria) this;
        }

        public Criteria andIntegrityIsNotNull() {
            addCriterion("integrity is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrityEqualTo(Integer value) {
            addCriterion("integrity =", value, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityNotEqualTo(Integer value) {
            addCriterion("integrity <>", value, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityGreaterThan(Integer value) {
            addCriterion("integrity >", value, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityGreaterThanOrEqualTo(Integer value) {
            addCriterion("integrity >=", value, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityLessThan(Integer value) {
            addCriterion("integrity <", value, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityLessThanOrEqualTo(Integer value) {
            addCriterion("integrity <=", value, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityIn(List<Integer> values) {
            addCriterion("integrity in", values, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityNotIn(List<Integer> values) {
            addCriterion("integrity not in", values, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityBetween(Integer value1, Integer value2) {
            addCriterion("integrity between", value1, value2, "integrity");
            return (Criteria) this;
        }

        public Criteria andIntegrityNotBetween(Integer value1, Integer value2) {
            addCriterion("integrity not between", value1, value2, "integrity");
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