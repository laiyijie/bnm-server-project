package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class OptionWorkingTypeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OptionWorkingTypeCriteria() {
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

        public Criteria andCrop_typeIsNull() {
            addCriterion("crop_type is null");
            return (Criteria) this;
        }

        public Criteria andCrop_typeIsNotNull() {
            addCriterion("crop_type is not null");
            return (Criteria) this;
        }

        public Criteria andCrop_typeEqualTo(String value) {
            addCriterion("crop_type =", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeNotEqualTo(String value) {
            addCriterion("crop_type <>", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeGreaterThan(String value) {
            addCriterion("crop_type >", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeGreaterThanOrEqualTo(String value) {
            addCriterion("crop_type >=", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeLessThan(String value) {
            addCriterion("crop_type <", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeLessThanOrEqualTo(String value) {
            addCriterion("crop_type <=", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeLike(String value) {
            addCriterion("crop_type like", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeNotLike(String value) {
            addCriterion("crop_type not like", value, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeIn(List<String> values) {
            addCriterion("crop_type in", values, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeNotIn(List<String> values) {
            addCriterion("crop_type not in", values, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeBetween(String value1, String value2) {
            addCriterion("crop_type between", value1, value2, "crop_type");
            return (Criteria) this;
        }

        public Criteria andCrop_typeNotBetween(String value1, String value2) {
            addCriterion("crop_type not between", value1, value2, "crop_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeIsNull() {
            addCriterion("working_type is null");
            return (Criteria) this;
        }

        public Criteria andWorking_typeIsNotNull() {
            addCriterion("working_type is not null");
            return (Criteria) this;
        }

        public Criteria andWorking_typeEqualTo(String value) {
            addCriterion("working_type =", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeNotEqualTo(String value) {
            addCriterion("working_type <>", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeGreaterThan(String value) {
            addCriterion("working_type >", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeGreaterThanOrEqualTo(String value) {
            addCriterion("working_type >=", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeLessThan(String value) {
            addCriterion("working_type <", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeLessThanOrEqualTo(String value) {
            addCriterion("working_type <=", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeLike(String value) {
            addCriterion("working_type like", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeNotLike(String value) {
            addCriterion("working_type not like", value, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeIn(List<String> values) {
            addCriterion("working_type in", values, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeNotIn(List<String> values) {
            addCriterion("working_type not in", values, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeBetween(String value1, String value2) {
            addCriterion("working_type between", value1, value2, "working_type");
            return (Criteria) this;
        }

        public Criteria andWorking_typeNotBetween(String value1, String value2) {
            addCriterion("working_type not between", value1, value2, "working_type");
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