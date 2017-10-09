package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class VersionControlCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VersionControlCriteria() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMain_versionIsNull() {
            addCriterion("main_version is null");
            return (Criteria) this;
        }

        public Criteria andMain_versionIsNotNull() {
            addCriterion("main_version is not null");
            return (Criteria) this;
        }

        public Criteria andMain_versionEqualTo(Integer value) {
            addCriterion("main_version =", value, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionNotEqualTo(Integer value) {
            addCriterion("main_version <>", value, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionGreaterThan(Integer value) {
            addCriterion("main_version >", value, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionGreaterThanOrEqualTo(Integer value) {
            addCriterion("main_version >=", value, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionLessThan(Integer value) {
            addCriterion("main_version <", value, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionLessThanOrEqualTo(Integer value) {
            addCriterion("main_version <=", value, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionIn(List<Integer> values) {
            addCriterion("main_version in", values, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionNotIn(List<Integer> values) {
            addCriterion("main_version not in", values, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionBetween(Integer value1, Integer value2) {
            addCriterion("main_version between", value1, value2, "main_version");
            return (Criteria) this;
        }

        public Criteria andMain_versionNotBetween(Integer value1, Integer value2) {
            addCriterion("main_version not between", value1, value2, "main_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionIsNull() {
            addCriterion("sub_version is null");
            return (Criteria) this;
        }

        public Criteria andSub_versionIsNotNull() {
            addCriterion("sub_version is not null");
            return (Criteria) this;
        }

        public Criteria andSub_versionEqualTo(Integer value) {
            addCriterion("sub_version =", value, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionNotEqualTo(Integer value) {
            addCriterion("sub_version <>", value, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionGreaterThan(Integer value) {
            addCriterion("sub_version >", value, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_version >=", value, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionLessThan(Integer value) {
            addCriterion("sub_version <", value, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionLessThanOrEqualTo(Integer value) {
            addCriterion("sub_version <=", value, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionIn(List<Integer> values) {
            addCriterion("sub_version in", values, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionNotIn(List<Integer> values) {
            addCriterion("sub_version not in", values, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionBetween(Integer value1, Integer value2) {
            addCriterion("sub_version between", value1, value2, "sub_version");
            return (Criteria) this;
        }

        public Criteria andSub_versionNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_version not between", value1, value2, "sub_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionIsNull() {
            addCriterion("bugfix_version is null");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionIsNotNull() {
            addCriterion("bugfix_version is not null");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionEqualTo(Integer value) {
            addCriterion("bugfix_version =", value, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionNotEqualTo(Integer value) {
            addCriterion("bugfix_version <>", value, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionGreaterThan(Integer value) {
            addCriterion("bugfix_version >", value, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionGreaterThanOrEqualTo(Integer value) {
            addCriterion("bugfix_version >=", value, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionLessThan(Integer value) {
            addCriterion("bugfix_version <", value, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionLessThanOrEqualTo(Integer value) {
            addCriterion("bugfix_version <=", value, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionIn(List<Integer> values) {
            addCriterion("bugfix_version in", values, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionNotIn(List<Integer> values) {
            addCriterion("bugfix_version not in", values, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionBetween(Integer value1, Integer value2) {
            addCriterion("bugfix_version between", value1, value2, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andBugfix_versionNotBetween(Integer value1, Integer value2) {
            addCriterion("bugfix_version not between", value1, value2, "bugfix_version");
            return (Criteria) this;
        }

        public Criteria andForce_updateIsNull() {
            addCriterion("force_update is null");
            return (Criteria) this;
        }

        public Criteria andForce_updateIsNotNull() {
            addCriterion("force_update is not null");
            return (Criteria) this;
        }

        public Criteria andForce_updateEqualTo(Integer value) {
            addCriterion("force_update =", value, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateNotEqualTo(Integer value) {
            addCriterion("force_update <>", value, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateGreaterThan(Integer value) {
            addCriterion("force_update >", value, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateGreaterThanOrEqualTo(Integer value) {
            addCriterion("force_update >=", value, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateLessThan(Integer value) {
            addCriterion("force_update <", value, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateLessThanOrEqualTo(Integer value) {
            addCriterion("force_update <=", value, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateIn(List<Integer> values) {
            addCriterion("force_update in", values, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateNotIn(List<Integer> values) {
            addCriterion("force_update not in", values, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateBetween(Integer value1, Integer value2) {
            addCriterion("force_update between", value1, value2, "force_update");
            return (Criteria) this;
        }

        public Criteria andForce_updateNotBetween(Integer value1, Integer value2) {
            addCriterion("force_update not between", value1, value2, "force_update");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsIsNull() {
            addCriterion("update_items is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsIsNotNull() {
            addCriterion("update_items is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsEqualTo(String value) {
            addCriterion("update_items =", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsNotEqualTo(String value) {
            addCriterion("update_items <>", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsGreaterThan(String value) {
            addCriterion("update_items >", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsGreaterThanOrEqualTo(String value) {
            addCriterion("update_items >=", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsLessThan(String value) {
            addCriterion("update_items <", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsLessThanOrEqualTo(String value) {
            addCriterion("update_items <=", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsLike(String value) {
            addCriterion("update_items like", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsNotLike(String value) {
            addCriterion("update_items not like", value, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsIn(List<String> values) {
            addCriterion("update_items in", values, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsNotIn(List<String> values) {
            addCriterion("update_items not in", values, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsBetween(String value1, String value2) {
            addCriterion("update_items between", value1, value2, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_itemsNotBetween(String value1, String value2) {
            addCriterion("update_items not between", value1, value2, "update_items");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlIsNull() {
            addCriterion("update_url is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlIsNotNull() {
            addCriterion("update_url is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlEqualTo(String value) {
            addCriterion("update_url =", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlNotEqualTo(String value) {
            addCriterion("update_url <>", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlGreaterThan(String value) {
            addCriterion("update_url >", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlGreaterThanOrEqualTo(String value) {
            addCriterion("update_url >=", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlLessThan(String value) {
            addCriterion("update_url <", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlLessThanOrEqualTo(String value) {
            addCriterion("update_url <=", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlLike(String value) {
            addCriterion("update_url like", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlNotLike(String value) {
            addCriterion("update_url not like", value, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlIn(List<String> values) {
            addCriterion("update_url in", values, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlNotIn(List<String> values) {
            addCriterion("update_url not in", values, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlBetween(String value1, String value2) {
            addCriterion("update_url between", value1, value2, "update_url");
            return (Criteria) this;
        }

        public Criteria andUpdate_urlNotBetween(String value1, String value2) {
            addCriterion("update_url not between", value1, value2, "update_url");
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