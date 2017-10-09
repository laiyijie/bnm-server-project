package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class StarUserCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StarUserCriteria() {
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

        public Criteria andCaptain_starIsNull() {
            addCriterion("captain_star is null");
            return (Criteria) this;
        }

        public Criteria andCaptain_starIsNotNull() {
            addCriterion("captain_star is not null");
            return (Criteria) this;
        }

        public Criteria andCaptain_starEqualTo(Double value) {
            addCriterion("captain_star =", value, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starNotEqualTo(Double value) {
            addCriterion("captain_star <>", value, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starGreaterThan(Double value) {
            addCriterion("captain_star >", value, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starGreaterThanOrEqualTo(Double value) {
            addCriterion("captain_star >=", value, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starLessThan(Double value) {
            addCriterion("captain_star <", value, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starLessThanOrEqualTo(Double value) {
            addCriterion("captain_star <=", value, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starIn(List<Double> values) {
            addCriterion("captain_star in", values, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starNotIn(List<Double> values) {
            addCriterion("captain_star not in", values, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starBetween(Double value1, Double value2) {
            addCriterion("captain_star between", value1, value2, "captain_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_starNotBetween(Double value1, Double value2) {
            addCriterion("captain_star not between", value1, value2, "captain_star");
            return (Criteria) this;
        }

        public Criteria andMember_starIsNull() {
            addCriterion("member_star is null");
            return (Criteria) this;
        }

        public Criteria andMember_starIsNotNull() {
            addCriterion("member_star is not null");
            return (Criteria) this;
        }

        public Criteria andMember_starEqualTo(Double value) {
            addCriterion("member_star =", value, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starNotEqualTo(Double value) {
            addCriterion("member_star <>", value, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starGreaterThan(Double value) {
            addCriterion("member_star >", value, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starGreaterThanOrEqualTo(Double value) {
            addCriterion("member_star >=", value, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starLessThan(Double value) {
            addCriterion("member_star <", value, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starLessThanOrEqualTo(Double value) {
            addCriterion("member_star <=", value, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starIn(List<Double> values) {
            addCriterion("member_star in", values, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starNotIn(List<Double> values) {
            addCriterion("member_star not in", values, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starBetween(Double value1, Double value2) {
            addCriterion("member_star between", value1, value2, "member_star");
            return (Criteria) this;
        }

        public Criteria andMember_starNotBetween(Double value1, Double value2) {
            addCriterion("member_star not between", value1, value2, "member_star");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsIsNull() {
            addCriterion("captain_evaluations is null");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsIsNotNull() {
            addCriterion("captain_evaluations is not null");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsEqualTo(Integer value) {
            addCriterion("captain_evaluations =", value, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsNotEqualTo(Integer value) {
            addCriterion("captain_evaluations <>", value, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsGreaterThan(Integer value) {
            addCriterion("captain_evaluations >", value, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsGreaterThanOrEqualTo(Integer value) {
            addCriterion("captain_evaluations >=", value, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsLessThan(Integer value) {
            addCriterion("captain_evaluations <", value, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsLessThanOrEqualTo(Integer value) {
            addCriterion("captain_evaluations <=", value, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsIn(List<Integer> values) {
            addCriterion("captain_evaluations in", values, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsNotIn(List<Integer> values) {
            addCriterion("captain_evaluations not in", values, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsBetween(Integer value1, Integer value2) {
            addCriterion("captain_evaluations between", value1, value2, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andCaptain_evaluationsNotBetween(Integer value1, Integer value2) {
            addCriterion("captain_evaluations not between", value1, value2, "captain_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsIsNull() {
            addCriterion("member_evaluations is null");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsIsNotNull() {
            addCriterion("member_evaluations is not null");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsEqualTo(Integer value) {
            addCriterion("member_evaluations =", value, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsNotEqualTo(Integer value) {
            addCriterion("member_evaluations <>", value, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsGreaterThan(Integer value) {
            addCriterion("member_evaluations >", value, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_evaluations >=", value, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsLessThan(Integer value) {
            addCriterion("member_evaluations <", value, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsLessThanOrEqualTo(Integer value) {
            addCriterion("member_evaluations <=", value, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsIn(List<Integer> values) {
            addCriterion("member_evaluations in", values, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsNotIn(List<Integer> values) {
            addCriterion("member_evaluations not in", values, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsBetween(Integer value1, Integer value2) {
            addCriterion("member_evaluations between", value1, value2, "member_evaluations");
            return (Criteria) this;
        }

        public Criteria andMember_evaluationsNotBetween(Integer value1, Integer value2) {
            addCriterion("member_evaluations not between", value1, value2, "member_evaluations");
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