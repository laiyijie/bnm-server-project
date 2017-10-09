package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class OptionClusterWorkingTypeMappingCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OptionClusterWorkingTypeMappingCriteria() {
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

        public Criteria andCluster_idIsNull() {
            addCriterion("cluster_id is null");
            return (Criteria) this;
        }

        public Criteria andCluster_idIsNotNull() {
            addCriterion("cluster_id is not null");
            return (Criteria) this;
        }

        public Criteria andCluster_idEqualTo(Long value) {
            addCriterion("cluster_id =", value, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idNotEqualTo(Long value) {
            addCriterion("cluster_id <>", value, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idGreaterThan(Long value) {
            addCriterion("cluster_id >", value, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idGreaterThanOrEqualTo(Long value) {
            addCriterion("cluster_id >=", value, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idLessThan(Long value) {
            addCriterion("cluster_id <", value, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idLessThanOrEqualTo(Long value) {
            addCriterion("cluster_id <=", value, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idIn(List<Long> values) {
            addCriterion("cluster_id in", values, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idNotIn(List<Long> values) {
            addCriterion("cluster_id not in", values, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idBetween(Long value1, Long value2) {
            addCriterion("cluster_id between", value1, value2, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andCluster_idNotBetween(Long value1, Long value2) {
            addCriterion("cluster_id not between", value1, value2, "cluster_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idIsNull() {
            addCriterion("working_type_id is null");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idIsNotNull() {
            addCriterion("working_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idEqualTo(Long value) {
            addCriterion("working_type_id =", value, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idNotEqualTo(Long value) {
            addCriterion("working_type_id <>", value, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idGreaterThan(Long value) {
            addCriterion("working_type_id >", value, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idGreaterThanOrEqualTo(Long value) {
            addCriterion("working_type_id >=", value, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idLessThan(Long value) {
            addCriterion("working_type_id <", value, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idLessThanOrEqualTo(Long value) {
            addCriterion("working_type_id <=", value, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idIn(List<Long> values) {
            addCriterion("working_type_id in", values, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idNotIn(List<Long> values) {
            addCriterion("working_type_id not in", values, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idBetween(Long value1, Long value2) {
            addCriterion("working_type_id between", value1, value2, "working_type_id");
            return (Criteria) this;
        }

        public Criteria andWorking_type_idNotBetween(Long value1, Long value2) {
            addCriterion("working_type_id not between", value1, value2, "working_type_id");
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