package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class PocketCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PocketCriteria() {
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

        public Criteria andCurr_moneyIsNull() {
            addCriterion("curr_money is null");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyIsNotNull() {
            addCriterion("curr_money is not null");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyEqualTo(Integer value) {
            addCriterion("curr_money =", value, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyNotEqualTo(Integer value) {
            addCriterion("curr_money <>", value, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyGreaterThan(Integer value) {
            addCriterion("curr_money >", value, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("curr_money >=", value, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyLessThan(Integer value) {
            addCriterion("curr_money <", value, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyLessThanOrEqualTo(Integer value) {
            addCriterion("curr_money <=", value, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyIn(List<Integer> values) {
            addCriterion("curr_money in", values, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyNotIn(List<Integer> values) {
            addCriterion("curr_money not in", values, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyBetween(Integer value1, Integer value2) {
            addCriterion("curr_money between", value1, value2, "curr_money");
            return (Criteria) this;
        }

        public Criteria andCurr_moneyNotBetween(Integer value1, Integer value2) {
            addCriterion("curr_money not between", value1, value2, "curr_money");
            return (Criteria) this;
        }

        public Criteria andProvisionsIsNull() {
            addCriterion("provisions is null");
            return (Criteria) this;
        }

        public Criteria andProvisionsIsNotNull() {
            addCriterion("provisions is not null");
            return (Criteria) this;
        }

        public Criteria andProvisionsEqualTo(Integer value) {
            addCriterion("provisions =", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsNotEqualTo(Integer value) {
            addCriterion("provisions <>", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsGreaterThan(Integer value) {
            addCriterion("provisions >", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsGreaterThanOrEqualTo(Integer value) {
            addCriterion("provisions >=", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsLessThan(Integer value) {
            addCriterion("provisions <", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsLessThanOrEqualTo(Integer value) {
            addCriterion("provisions <=", value, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsIn(List<Integer> values) {
            addCriterion("provisions in", values, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsNotIn(List<Integer> values) {
            addCriterion("provisions not in", values, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsBetween(Integer value1, Integer value2) {
            addCriterion("provisions between", value1, value2, "provisions");
            return (Criteria) this;
        }

        public Criteria andProvisionsNotBetween(Integer value1, Integer value2) {
            addCriterion("provisions not between", value1, value2, "provisions");
            return (Criteria) this;
        }

        public Criteria andArrearsIsNull() {
            addCriterion("arrears is null");
            return (Criteria) this;
        }

        public Criteria andArrearsIsNotNull() {
            addCriterion("arrears is not null");
            return (Criteria) this;
        }

        public Criteria andArrearsEqualTo(Integer value) {
            addCriterion("arrears =", value, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsNotEqualTo(Integer value) {
            addCriterion("arrears <>", value, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsGreaterThan(Integer value) {
            addCriterion("arrears >", value, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrears >=", value, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsLessThan(Integer value) {
            addCriterion("arrears <", value, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsLessThanOrEqualTo(Integer value) {
            addCriterion("arrears <=", value, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsIn(List<Integer> values) {
            addCriterion("arrears in", values, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsNotIn(List<Integer> values) {
            addCriterion("arrears not in", values, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsBetween(Integer value1, Integer value2) {
            addCriterion("arrears between", value1, value2, "arrears");
            return (Criteria) this;
        }

        public Criteria andArrearsNotBetween(Integer value1, Integer value2) {
            addCriterion("arrears not between", value1, value2, "arrears");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Integer value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Integer value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Integer value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Integer value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Integer value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Integer> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Integer> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Integer value1, Integer value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andInsuranceIsNull() {
            addCriterion("insurance is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceIsNotNull() {
            addCriterion("insurance is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceEqualTo(Integer value) {
            addCriterion("insurance =", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotEqualTo(Integer value) {
            addCriterion("insurance <>", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceGreaterThan(Integer value) {
            addCriterion("insurance >", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceGreaterThanOrEqualTo(Integer value) {
            addCriterion("insurance >=", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceLessThan(Integer value) {
            addCriterion("insurance <", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceLessThanOrEqualTo(Integer value) {
            addCriterion("insurance <=", value, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceIn(List<Integer> values) {
            addCriterion("insurance in", values, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotIn(List<Integer> values) {
            addCriterion("insurance not in", values, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceBetween(Integer value1, Integer value2) {
            addCriterion("insurance between", value1, value2, "insurance");
            return (Criteria) this;
        }

        public Criteria andInsuranceNotBetween(Integer value1, Integer value2) {
            addCriterion("insurance not between", value1, value2, "insurance");
            return (Criteria) this;
        }

        public Criteria andWaitting_inIsNull() {
            addCriterion("waitting_in is null");
            return (Criteria) this;
        }

        public Criteria andWaitting_inIsNotNull() {
            addCriterion("waitting_in is not null");
            return (Criteria) this;
        }

        public Criteria andWaitting_inEqualTo(Integer value) {
            addCriterion("waitting_in =", value, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inNotEqualTo(Integer value) {
            addCriterion("waitting_in <>", value, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inGreaterThan(Integer value) {
            addCriterion("waitting_in >", value, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inGreaterThanOrEqualTo(Integer value) {
            addCriterion("waitting_in >=", value, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inLessThan(Integer value) {
            addCriterion("waitting_in <", value, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inLessThanOrEqualTo(Integer value) {
            addCriterion("waitting_in <=", value, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inIn(List<Integer> values) {
            addCriterion("waitting_in in", values, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inNotIn(List<Integer> values) {
            addCriterion("waitting_in not in", values, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inBetween(Integer value1, Integer value2) {
            addCriterion("waitting_in between", value1, value2, "waitting_in");
            return (Criteria) this;
        }

        public Criteria andWaitting_inNotBetween(Integer value1, Integer value2) {
            addCriterion("waitting_in not between", value1, value2, "waitting_in");
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