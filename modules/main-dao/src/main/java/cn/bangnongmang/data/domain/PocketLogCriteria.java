package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class PocketLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PocketLogCriteria() {
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

        public Criteria andPocket_log_idIsNull() {
            addCriterion("pocket_log_id is null");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idIsNotNull() {
            addCriterion("pocket_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idEqualTo(String value) {
            addCriterion("pocket_log_id =", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idNotEqualTo(String value) {
            addCriterion("pocket_log_id <>", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idGreaterThan(String value) {
            addCriterion("pocket_log_id >", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idGreaterThanOrEqualTo(String value) {
            addCriterion("pocket_log_id >=", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idLessThan(String value) {
            addCriterion("pocket_log_id <", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idLessThanOrEqualTo(String value) {
            addCriterion("pocket_log_id <=", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idLike(String value) {
            addCriterion("pocket_log_id like", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idNotLike(String value) {
            addCriterion("pocket_log_id not like", value, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idIn(List<String> values) {
            addCriterion("pocket_log_id in", values, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idNotIn(List<String> values) {
            addCriterion("pocket_log_id not in", values, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idBetween(String value1, String value2) {
            addCriterion("pocket_log_id between", value1, value2, "pocket_log_id");
            return (Criteria) this;
        }

        public Criteria andPocket_log_idNotBetween(String value1, String value2) {
            addCriterion("pocket_log_id not between", value1, value2, "pocket_log_id");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Long value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Long value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Long value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Long value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Long value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Long> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Long> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Long value1, Long value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Long value1, Long value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Integer value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Integer value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Integer value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Integer value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Integer> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Integer> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Integer value1, Integer value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andConnect_order_idIsNull() {
            addCriterion("connect_order_id is null");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idIsNotNull() {
            addCriterion("connect_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idEqualTo(String value) {
            addCriterion("connect_order_id =", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idNotEqualTo(String value) {
            addCriterion("connect_order_id <>", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idGreaterThan(String value) {
            addCriterion("connect_order_id >", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idGreaterThanOrEqualTo(String value) {
            addCriterion("connect_order_id >=", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idLessThan(String value) {
            addCriterion("connect_order_id <", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idLessThanOrEqualTo(String value) {
            addCriterion("connect_order_id <=", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idLike(String value) {
            addCriterion("connect_order_id like", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idNotLike(String value) {
            addCriterion("connect_order_id not like", value, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idIn(List<String> values) {
            addCriterion("connect_order_id in", values, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idNotIn(List<String> values) {
            addCriterion("connect_order_id not in", values, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idBetween(String value1, String value2) {
            addCriterion("connect_order_id between", value1, value2, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andConnect_order_idNotBetween(String value1, String value2) {
            addCriterion("connect_order_id not between", value1, value2, "connect_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idIsNull() {
            addCriterion("trade_order_id is null");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idIsNotNull() {
            addCriterion("trade_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idEqualTo(String value) {
            addCriterion("trade_order_id =", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idNotEqualTo(String value) {
            addCriterion("trade_order_id <>", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idGreaterThan(String value) {
            addCriterion("trade_order_id >", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idGreaterThanOrEqualTo(String value) {
            addCriterion("trade_order_id >=", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idLessThan(String value) {
            addCriterion("trade_order_id <", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idLessThanOrEqualTo(String value) {
            addCriterion("trade_order_id <=", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idLike(String value) {
            addCriterion("trade_order_id like", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idNotLike(String value) {
            addCriterion("trade_order_id not like", value, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idIn(List<String> values) {
            addCriterion("trade_order_id in", values, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idNotIn(List<String> values) {
            addCriterion("trade_order_id not in", values, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idBetween(String value1, String value2) {
            addCriterion("trade_order_id between", value1, value2, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andTrade_order_idNotBetween(String value1, String value2) {
            addCriterion("trade_order_id not between", value1, value2, "trade_order_id");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(Integer value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(Integer value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(Integer value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(Integer value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(Integer value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<Integer> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<Integer> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(Integer value1, Integer value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoIsNull() {
            addCriterion("wechat_pay_info is null");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoIsNotNull() {
            addCriterion("wechat_pay_info is not null");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoEqualTo(String value) {
            addCriterion("wechat_pay_info =", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoNotEqualTo(String value) {
            addCriterion("wechat_pay_info <>", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoGreaterThan(String value) {
            addCriterion("wechat_pay_info >", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_pay_info >=", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoLessThan(String value) {
            addCriterion("wechat_pay_info <", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoLessThanOrEqualTo(String value) {
            addCriterion("wechat_pay_info <=", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoLike(String value) {
            addCriterion("wechat_pay_info like", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoNotLike(String value) {
            addCriterion("wechat_pay_info not like", value, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoIn(List<String> values) {
            addCriterion("wechat_pay_info in", values, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoNotIn(List<String> values) {
            addCriterion("wechat_pay_info not in", values, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoBetween(String value1, String value2) {
            addCriterion("wechat_pay_info between", value1, value2, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andWechat_pay_infoNotBetween(String value1, String value2) {
            addCriterion("wechat_pay_info not between", value1, value2, "wechat_pay_info");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
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