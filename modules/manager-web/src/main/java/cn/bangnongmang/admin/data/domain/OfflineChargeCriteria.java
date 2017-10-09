package cn.bangnongmang.admin.data.domain;

import java.util.ArrayList;
import java.util.List;

public class OfflineChargeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OfflineChargeCriteria() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRequesterIsNull() {
            addCriterion("requester is null");
            return (Criteria) this;
        }

        public Criteria andRequesterIsNotNull() {
            addCriterion("requester is not null");
            return (Criteria) this;
        }

        public Criteria andRequesterEqualTo(String value) {
            addCriterion("requester =", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterNotEqualTo(String value) {
            addCriterion("requester <>", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterGreaterThan(String value) {
            addCriterion("requester >", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterGreaterThanOrEqualTo(String value) {
            addCriterion("requester >=", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterLessThan(String value) {
            addCriterion("requester <", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterLessThanOrEqualTo(String value) {
            addCriterion("requester <=", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterLike(String value) {
            addCriterion("requester like", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterNotLike(String value) {
            addCriterion("requester not like", value, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterIn(List<String> values) {
            addCriterion("requester in", values, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterNotIn(List<String> values) {
            addCriterion("requester not in", values, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterBetween(String value1, String value2) {
            addCriterion("requester between", value1, value2, "requester");
            return (Criteria) this;
        }

        public Criteria andRequesterNotBetween(String value1, String value2) {
            addCriterion("requester not between", value1, value2, "requester");
            return (Criteria) this;
        }

        public Criteria andResponserIsNull() {
            addCriterion("responser is null");
            return (Criteria) this;
        }

        public Criteria andResponserIsNotNull() {
            addCriterion("responser is not null");
            return (Criteria) this;
        }

        public Criteria andResponserEqualTo(String value) {
            addCriterion("responser =", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserNotEqualTo(String value) {
            addCriterion("responser <>", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserGreaterThan(String value) {
            addCriterion("responser >", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserGreaterThanOrEqualTo(String value) {
            addCriterion("responser >=", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserLessThan(String value) {
            addCriterion("responser <", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserLessThanOrEqualTo(String value) {
            addCriterion("responser <=", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserLike(String value) {
            addCriterion("responser like", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserNotLike(String value) {
            addCriterion("responser not like", value, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserIn(List<String> values) {
            addCriterion("responser in", values, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserNotIn(List<String> values) {
            addCriterion("responser not in", values, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserBetween(String value1, String value2) {
            addCriterion("responser between", value1, value2, "responser");
            return (Criteria) this;
        }

        public Criteria andResponserNotBetween(String value1, String value2) {
            addCriterion("responser not between", value1, value2, "responser");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andTargetIsNull() {
            addCriterion("target is null");
            return (Criteria) this;
        }

        public Criteria andTargetIsNotNull() {
            addCriterion("target is not null");
            return (Criteria) this;
        }

        public Criteria andTargetEqualTo(String value) {
            addCriterion("target =", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotEqualTo(String value) {
            addCriterion("target <>", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThan(String value) {
            addCriterion("target >", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanOrEqualTo(String value) {
            addCriterion("target >=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThan(String value) {
            addCriterion("target <", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThanOrEqualTo(String value) {
            addCriterion("target <=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLike(String value) {
            addCriterion("target like", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotLike(String value) {
            addCriterion("target not like", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetIn(List<String> values) {
            addCriterion("target in", values, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotIn(List<String> values) {
            addCriterion("target not in", values, "target");
            return (Criteria) this;
        }

        public Criteria andTargetBetween(String value1, String value2) {
            addCriterion("target between", value1, value2, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotBetween(String value1, String value2) {
            addCriterion("target not between", value1, value2, "target");
            return (Criteria) this;
        }

        public Criteria andRequest_timeIsNull() {
            addCriterion("request_time is null");
            return (Criteria) this;
        }

        public Criteria andRequest_timeIsNotNull() {
            addCriterion("request_time is not null");
            return (Criteria) this;
        }

        public Criteria andRequest_timeEqualTo(Long value) {
            addCriterion("request_time =", value, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeNotEqualTo(Long value) {
            addCriterion("request_time <>", value, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeGreaterThan(Long value) {
            addCriterion("request_time >", value, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("request_time >=", value, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeLessThan(Long value) {
            addCriterion("request_time <", value, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeLessThanOrEqualTo(Long value) {
            addCriterion("request_time <=", value, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeIn(List<Long> values) {
            addCriterion("request_time in", values, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeNotIn(List<Long> values) {
            addCriterion("request_time not in", values, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeBetween(Long value1, Long value2) {
            addCriterion("request_time between", value1, value2, "request_time");
            return (Criteria) this;
        }

        public Criteria andRequest_timeNotBetween(Long value1, Long value2) {
            addCriterion("request_time not between", value1, value2, "request_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeIsNull() {
            addCriterion("response_time is null");
            return (Criteria) this;
        }

        public Criteria andResponse_timeIsNotNull() {
            addCriterion("response_time is not null");
            return (Criteria) this;
        }

        public Criteria andResponse_timeEqualTo(Long value) {
            addCriterion("response_time =", value, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeNotEqualTo(Long value) {
            addCriterion("response_time <>", value, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeGreaterThan(Long value) {
            addCriterion("response_time >", value, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("response_time >=", value, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeLessThan(Long value) {
            addCriterion("response_time <", value, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeLessThanOrEqualTo(Long value) {
            addCriterion("response_time <=", value, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeIn(List<Long> values) {
            addCriterion("response_time in", values, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeNotIn(List<Long> values) {
            addCriterion("response_time not in", values, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeBetween(Long value1, Long value2) {
            addCriterion("response_time between", value1, value2, "response_time");
            return (Criteria) this;
        }

        public Criteria andResponse_timeNotBetween(Long value1, Long value2) {
            addCriterion("response_time not between", value1, value2, "response_time");
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

        public Criteria andPsIsNull() {
            addCriterion("ps is null");
            return (Criteria) this;
        }

        public Criteria andPsIsNotNull() {
            addCriterion("ps is not null");
            return (Criteria) this;
        }

        public Criteria andPsEqualTo(String value) {
            addCriterion("ps =", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsNotEqualTo(String value) {
            addCriterion("ps <>", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsGreaterThan(String value) {
            addCriterion("ps >", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsGreaterThanOrEqualTo(String value) {
            addCriterion("ps >=", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsLessThan(String value) {
            addCriterion("ps <", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsLessThanOrEqualTo(String value) {
            addCriterion("ps <=", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsLike(String value) {
            addCriterion("ps like", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsNotLike(String value) {
            addCriterion("ps not like", value, "ps");
            return (Criteria) this;
        }

        public Criteria andPsIn(List<String> values) {
            addCriterion("ps in", values, "ps");
            return (Criteria) this;
        }

        public Criteria andPsNotIn(List<String> values) {
            addCriterion("ps not in", values, "ps");
            return (Criteria) this;
        }

        public Criteria andPsBetween(String value1, String value2) {
            addCriterion("ps between", value1, value2, "ps");
            return (Criteria) this;
        }

        public Criteria andPsNotBetween(String value1, String value2) {
            addCriterion("ps not between", value1, value2, "ps");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
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