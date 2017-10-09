package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderDriverCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDriverCriteria() {
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

        public Criteria andOrder_idIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrder_idIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_idEqualTo(String value) {
            addCriterion("order_id =", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotEqualTo(String value) {
            addCriterion("order_id <>", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThan(String value) {
            addCriterion("order_id >", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThan(String value) {
            addCriterion("order_id <", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLike(String value) {
            addCriterion("order_id like", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotLike(String value) {
            addCriterion("order_id not like", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idIn(List<String> values) {
            addCriterion("order_id in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotIn(List<String> values) {
            addCriterion("order_id not in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idIsNull() {
            addCriterion("order_farmer_id is null");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idIsNotNull() {
            addCriterion("order_farmer_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idEqualTo(String value) {
            addCriterion("order_farmer_id =", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idNotEqualTo(String value) {
            addCriterion("order_farmer_id <>", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idGreaterThan(String value) {
            addCriterion("order_farmer_id >", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idGreaterThanOrEqualTo(String value) {
            addCriterion("order_farmer_id >=", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idLessThan(String value) {
            addCriterion("order_farmer_id <", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idLessThanOrEqualTo(String value) {
            addCriterion("order_farmer_id <=", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idLike(String value) {
            addCriterion("order_farmer_id like", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idNotLike(String value) {
            addCriterion("order_farmer_id not like", value, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idIn(List<String> values) {
            addCriterion("order_farmer_id in", values, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idNotIn(List<String> values) {
            addCriterion("order_farmer_id not in", values, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idBetween(String value1, String value2) {
            addCriterion("order_farmer_id between", value1, value2, "order_farmer_id");
            return (Criteria) this;
        }

        public Criteria andOrder_farmer_idNotBetween(String value1, String value2) {
            addCriterion("order_farmer_id not between", value1, value2, "order_farmer_id");
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

        public Criteria andService_startIsNull() {
            addCriterion("service_start is null");
            return (Criteria) this;
        }

        public Criteria andService_startIsNotNull() {
            addCriterion("service_start is not null");
            return (Criteria) this;
        }

        public Criteria andService_startEqualTo(Long value) {
            addCriterion("service_start =", value, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startNotEqualTo(Long value) {
            addCriterion("service_start <>", value, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startGreaterThan(Long value) {
            addCriterion("service_start >", value, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startGreaterThanOrEqualTo(Long value) {
            addCriterion("service_start >=", value, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startLessThan(Long value) {
            addCriterion("service_start <", value, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startLessThanOrEqualTo(Long value) {
            addCriterion("service_start <=", value, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startIn(List<Long> values) {
            addCriterion("service_start in", values, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startNotIn(List<Long> values) {
            addCriterion("service_start not in", values, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startBetween(Long value1, Long value2) {
            addCriterion("service_start between", value1, value2, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_startNotBetween(Long value1, Long value2) {
            addCriterion("service_start not between", value1, value2, "service_start");
            return (Criteria) this;
        }

        public Criteria andService_endIsNull() {
            addCriterion("service_end is null");
            return (Criteria) this;
        }

        public Criteria andService_endIsNotNull() {
            addCriterion("service_end is not null");
            return (Criteria) this;
        }

        public Criteria andService_endEqualTo(Long value) {
            addCriterion("service_end =", value, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endNotEqualTo(Long value) {
            addCriterion("service_end <>", value, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endGreaterThan(Long value) {
            addCriterion("service_end >", value, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endGreaterThanOrEqualTo(Long value) {
            addCriterion("service_end >=", value, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endLessThan(Long value) {
            addCriterion("service_end <", value, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endLessThanOrEqualTo(Long value) {
            addCriterion("service_end <=", value, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endIn(List<Long> values) {
            addCriterion("service_end in", values, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endNotIn(List<Long> values) {
            addCriterion("service_end not in", values, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endBetween(Long value1, Long value2) {
            addCriterion("service_end between", value1, value2, "service_end");
            return (Criteria) this;
        }

        public Criteria andService_endNotBetween(Long value1, Long value2) {
            addCriterion("service_end not between", value1, value2, "service_end");
            return (Criteria) this;
        }

        public Criteria andActual_sizeIsNull() {
            addCriterion("actual_size is null");
            return (Criteria) this;
        }

        public Criteria andActual_sizeIsNotNull() {
            addCriterion("actual_size is not null");
            return (Criteria) this;
        }

        public Criteria andActual_sizeEqualTo(Double value) {
            addCriterion("actual_size =", value, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeNotEqualTo(Double value) {
            addCriterion("actual_size <>", value, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeGreaterThan(Double value) {
            addCriterion("actual_size >", value, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_size >=", value, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeLessThan(Double value) {
            addCriterion("actual_size <", value, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeLessThanOrEqualTo(Double value) {
            addCriterion("actual_size <=", value, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeIn(List<Double> values) {
            addCriterion("actual_size in", values, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeNotIn(List<Double> values) {
            addCriterion("actual_size not in", values, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeBetween(Double value1, Double value2) {
            addCriterion("actual_size between", value1, value2, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_sizeNotBetween(Double value1, Double value2) {
            addCriterion("actual_size not between", value1, value2, "actual_size");
            return (Criteria) this;
        }

        public Criteria andActual_moneyIsNull() {
            addCriterion("actual_money is null");
            return (Criteria) this;
        }

        public Criteria andActual_moneyIsNotNull() {
            addCriterion("actual_money is not null");
            return (Criteria) this;
        }

        public Criteria andActual_moneyEqualTo(Integer value) {
            addCriterion("actual_money =", value, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyNotEqualTo(Integer value) {
            addCriterion("actual_money <>", value, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyGreaterThan(Integer value) {
            addCriterion("actual_money >", value, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_money >=", value, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyLessThan(Integer value) {
            addCriterion("actual_money <", value, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyLessThanOrEqualTo(Integer value) {
            addCriterion("actual_money <=", value, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyIn(List<Integer> values) {
            addCriterion("actual_money in", values, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyNotIn(List<Integer> values) {
            addCriterion("actual_money not in", values, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyBetween(Integer value1, Integer value2) {
            addCriterion("actual_money between", value1, value2, "actual_money");
            return (Criteria) this;
        }

        public Criteria andActual_moneyNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_money not between", value1, value2, "actual_money");
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

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
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