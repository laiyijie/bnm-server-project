package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class PhoneVerifyCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhoneVerifyCriteria() {
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

        public Criteria andId_phoneverifyIsNull() {
            addCriterion("id_phoneverify is null");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyIsNotNull() {
            addCriterion("id_phoneverify is not null");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyEqualTo(Integer value) {
            addCriterion("id_phoneverify =", value, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyNotEqualTo(Integer value) {
            addCriterion("id_phoneverify <>", value, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyGreaterThan(Integer value) {
            addCriterion("id_phoneverify >", value, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_phoneverify >=", value, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyLessThan(Integer value) {
            addCriterion("id_phoneverify <", value, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyLessThanOrEqualTo(Integer value) {
            addCriterion("id_phoneverify <=", value, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyIn(List<Integer> values) {
            addCriterion("id_phoneverify in", values, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyNotIn(List<Integer> values) {
            addCriterion("id_phoneverify not in", values, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyBetween(Integer value1, Integer value2) {
            addCriterion("id_phoneverify between", value1, value2, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andId_phoneverifyNotBetween(Integer value1, Integer value2) {
            addCriterion("id_phoneverify not between", value1, value2, "id_phoneverify");
            return (Criteria) this;
        }

        public Criteria andPhonenumberIsNull() {
            addCriterion("phonenumber is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumberIsNotNull() {
            addCriterion("phonenumber is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumberEqualTo(String value) {
            addCriterion("phonenumber =", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotEqualTo(String value) {
            addCriterion("phonenumber <>", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberGreaterThan(String value) {
            addCriterion("phonenumber >", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberGreaterThanOrEqualTo(String value) {
            addCriterion("phonenumber >=", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberLessThan(String value) {
            addCriterion("phonenumber <", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberLessThanOrEqualTo(String value) {
            addCriterion("phonenumber <=", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberLike(String value) {
            addCriterion("phonenumber like", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotLike(String value) {
            addCriterion("phonenumber not like", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberIn(List<String> values) {
            addCriterion("phonenumber in", values, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotIn(List<String> values) {
            addCriterion("phonenumber not in", values, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberBetween(String value1, String value2) {
            addCriterion("phonenumber between", value1, value2, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotBetween(String value1, String value2) {
            addCriterion("phonenumber not between", value1, value2, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andSend_timeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSend_timeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSend_timeEqualTo(Long value) {
            addCriterion("send_time =", value, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeNotEqualTo(Long value) {
            addCriterion("send_time <>", value, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeGreaterThan(Long value) {
            addCriterion("send_time >", value, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("send_time >=", value, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeLessThan(Long value) {
            addCriterion("send_time <", value, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeLessThanOrEqualTo(Long value) {
            addCriterion("send_time <=", value, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeIn(List<Long> values) {
            addCriterion("send_time in", values, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeNotIn(List<Long> values) {
            addCriterion("send_time not in", values, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeBetween(Long value1, Long value2) {
            addCriterion("send_time between", value1, value2, "send_time");
            return (Criteria) this;
        }

        public Criteria andSend_timeNotBetween(Long value1, Long value2) {
            addCriterion("send_time not between", value1, value2, "send_time");
            return (Criteria) this;
        }

        public Criteria andVerify_codeIsNull() {
            addCriterion("verify_code is null");
            return (Criteria) this;
        }

        public Criteria andVerify_codeIsNotNull() {
            addCriterion("verify_code is not null");
            return (Criteria) this;
        }

        public Criteria andVerify_codeEqualTo(String value) {
            addCriterion("verify_code =", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeNotEqualTo(String value) {
            addCriterion("verify_code <>", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeGreaterThan(String value) {
            addCriterion("verify_code >", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeGreaterThanOrEqualTo(String value) {
            addCriterion("verify_code >=", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeLessThan(String value) {
            addCriterion("verify_code <", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeLessThanOrEqualTo(String value) {
            addCriterion("verify_code <=", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeLike(String value) {
            addCriterion("verify_code like", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeNotLike(String value) {
            addCriterion("verify_code not like", value, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeIn(List<String> values) {
            addCriterion("verify_code in", values, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeNotIn(List<String> values) {
            addCriterion("verify_code not in", values, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeBetween(String value1, String value2) {
            addCriterion("verify_code between", value1, value2, "verify_code");
            return (Criteria) this;
        }

        public Criteria andVerify_codeNotBetween(String value1, String value2) {
            addCriterion("verify_code not between", value1, value2, "verify_code");
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