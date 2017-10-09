package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class FriendshipCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendshipCriteria() {
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

        public Criteria andUseraIsNull() {
            addCriterion("usera is null");
            return (Criteria) this;
        }

        public Criteria andUseraIsNotNull() {
            addCriterion("usera is not null");
            return (Criteria) this;
        }

        public Criteria andUseraEqualTo(Long value) {
            addCriterion("usera =", value, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraNotEqualTo(Long value) {
            addCriterion("usera <>", value, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraGreaterThan(Long value) {
            addCriterion("usera >", value, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraGreaterThanOrEqualTo(Long value) {
            addCriterion("usera >=", value, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraLessThan(Long value) {
            addCriterion("usera <", value, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraLessThanOrEqualTo(Long value) {
            addCriterion("usera <=", value, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraIn(List<Long> values) {
            addCriterion("usera in", values, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraNotIn(List<Long> values) {
            addCriterion("usera not in", values, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraBetween(Long value1, Long value2) {
            addCriterion("usera between", value1, value2, "usera");
            return (Criteria) this;
        }

        public Criteria andUseraNotBetween(Long value1, Long value2) {
            addCriterion("usera not between", value1, value2, "usera");
            return (Criteria) this;
        }

        public Criteria andUserbIsNull() {
            addCriterion("userb is null");
            return (Criteria) this;
        }

        public Criteria andUserbIsNotNull() {
            addCriterion("userb is not null");
            return (Criteria) this;
        }

        public Criteria andUserbEqualTo(Long value) {
            addCriterion("userb =", value, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbNotEqualTo(Long value) {
            addCriterion("userb <>", value, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbGreaterThan(Long value) {
            addCriterion("userb >", value, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbGreaterThanOrEqualTo(Long value) {
            addCriterion("userb >=", value, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbLessThan(Long value) {
            addCriterion("userb <", value, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbLessThanOrEqualTo(Long value) {
            addCriterion("userb <=", value, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbIn(List<Long> values) {
            addCriterion("userb in", values, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbNotIn(List<Long> values) {
            addCriterion("userb not in", values, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbBetween(Long value1, Long value2) {
            addCriterion("userb between", value1, value2, "userb");
            return (Criteria) this;
        }

        public Criteria andUserbNotBetween(Long value1, Long value2) {
            addCriterion("userb not between", value1, value2, "userb");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Long value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Long value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Long value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Long> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
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