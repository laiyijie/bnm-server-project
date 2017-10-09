package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class GameCountCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameCountCriteria() {
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

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andHight_scoreIsNull() {
            addCriterion("hight_score is null");
            return (Criteria) this;
        }

        public Criteria andHight_scoreIsNotNull() {
            addCriterion("hight_score is not null");
            return (Criteria) this;
        }

        public Criteria andHight_scoreEqualTo(Integer value) {
            addCriterion("hight_score =", value, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreNotEqualTo(Integer value) {
            addCriterion("hight_score <>", value, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreGreaterThan(Integer value) {
            addCriterion("hight_score >", value, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("hight_score >=", value, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreLessThan(Integer value) {
            addCriterion("hight_score <", value, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreLessThanOrEqualTo(Integer value) {
            addCriterion("hight_score <=", value, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreIn(List<Integer> values) {
            addCriterion("hight_score in", values, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreNotIn(List<Integer> values) {
            addCriterion("hight_score not in", values, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreBetween(Integer value1, Integer value2) {
            addCriterion("hight_score between", value1, value2, "hight_score");
            return (Criteria) this;
        }

        public Criteria andHight_scoreNotBetween(Integer value1, Integer value2) {
            addCriterion("hight_score not between", value1, value2, "hight_score");
            return (Criteria) this;
        }

        public Criteria andNick_nameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNick_nameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNick_nameEqualTo(String value) {
            addCriterion("nick_name =", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameLessThan(String value) {
            addCriterion("nick_name <", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameLike(String value) {
            addCriterion("nick_name like", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotLike(String value) {
            addCriterion("nick_name not like", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameIn(List<String> values) {
            addCriterion("nick_name in", values, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nick_name");
            return (Criteria) this;
        }

        public Criteria andHigest_timeIsNull() {
            addCriterion("higest_time is null");
            return (Criteria) this;
        }

        public Criteria andHigest_timeIsNotNull() {
            addCriterion("higest_time is not null");
            return (Criteria) this;
        }

        public Criteria andHigest_timeEqualTo(Long value) {
            addCriterion("higest_time =", value, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeNotEqualTo(Long value) {
            addCriterion("higest_time <>", value, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeGreaterThan(Long value) {
            addCriterion("higest_time >", value, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("higest_time >=", value, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeLessThan(Long value) {
            addCriterion("higest_time <", value, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeLessThanOrEqualTo(Long value) {
            addCriterion("higest_time <=", value, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeIn(List<Long> values) {
            addCriterion("higest_time in", values, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeNotIn(List<Long> values) {
            addCriterion("higest_time not in", values, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeBetween(Long value1, Long value2) {
            addCriterion("higest_time between", value1, value2, "higest_time");
            return (Criteria) this;
        }

        public Criteria andHigest_timeNotBetween(Long value1, Long value2) {
            addCriterion("higest_time not between", value1, value2, "higest_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeIsNull() {
            addCriterion("recharge_time is null");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeIsNotNull() {
            addCriterion("recharge_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeEqualTo(Integer value) {
            addCriterion("recharge_time =", value, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeNotEqualTo(Integer value) {
            addCriterion("recharge_time <>", value, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeGreaterThan(Integer value) {
            addCriterion("recharge_time >", value, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recharge_time >=", value, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeLessThan(Integer value) {
            addCriterion("recharge_time <", value, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeLessThanOrEqualTo(Integer value) {
            addCriterion("recharge_time <=", value, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeIn(List<Integer> values) {
            addCriterion("recharge_time in", values, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeNotIn(List<Integer> values) {
            addCriterion("recharge_time not in", values, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeBetween(Integer value1, Integer value2) {
            addCriterion("recharge_time between", value1, value2, "recharge_time");
            return (Criteria) this;
        }

        public Criteria andRecharge_timeNotBetween(Integer value1, Integer value2) {
            addCriterion("recharge_time not between", value1, value2, "recharge_time");
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