package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderFarmerCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderFarmerCriteria() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("county like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("county not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andTownIsNull() {
            addCriterion("town is null");
            return (Criteria) this;
        }

        public Criteria andTownIsNotNull() {
            addCriterion("town is not null");
            return (Criteria) this;
        }

        public Criteria andTownEqualTo(String value) {
            addCriterion("town =", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotEqualTo(String value) {
            addCriterion("town <>", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownGreaterThan(String value) {
            addCriterion("town >", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownGreaterThanOrEqualTo(String value) {
            addCriterion("town >=", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLessThan(String value) {
            addCriterion("town <", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLessThanOrEqualTo(String value) {
            addCriterion("town <=", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLike(String value) {
            addCriterion("town like", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotLike(String value) {
            addCriterion("town not like", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownIn(List<String> values) {
            addCriterion("town in", values, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotIn(List<String> values) {
            addCriterion("town not in", values, "town");
            return (Criteria) this;
        }

        public Criteria andTownBetween(String value1, String value2) {
            addCriterion("town between", value1, value2, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotBetween(String value1, String value2) {
            addCriterion("town not between", value1, value2, "town");
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

        public Criteria andDesire_numIsNull() {
            addCriterion("desire_num is null");
            return (Criteria) this;
        }

        public Criteria andDesire_numIsNotNull() {
            addCriterion("desire_num is not null");
            return (Criteria) this;
        }

        public Criteria andDesire_numEqualTo(Integer value) {
            addCriterion("desire_num =", value, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numNotEqualTo(Integer value) {
            addCriterion("desire_num <>", value, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numGreaterThan(Integer value) {
            addCriterion("desire_num >", value, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("desire_num >=", value, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numLessThan(Integer value) {
            addCriterion("desire_num <", value, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numLessThanOrEqualTo(Integer value) {
            addCriterion("desire_num <=", value, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numIn(List<Integer> values) {
            addCriterion("desire_num in", values, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numNotIn(List<Integer> values) {
            addCriterion("desire_num not in", values, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numBetween(Integer value1, Integer value2) {
            addCriterion("desire_num between", value1, value2, "desire_num");
            return (Criteria) this;
        }

        public Criteria andDesire_numNotBetween(Integer value1, Integer value2) {
            addCriterion("desire_num not between", value1, value2, "desire_num");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Double value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Double value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Double value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Double value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Double value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Double> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Double> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Double value1, Double value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Double value1, Double value2) {
            addCriterion("size not between", value1, value2, "size");
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

        public Criteria andDesire_start_timeIsNull() {
            addCriterion("desire_start_time is null");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeIsNotNull() {
            addCriterion("desire_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeEqualTo(Long value) {
            addCriterion("desire_start_time =", value, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeNotEqualTo(Long value) {
            addCriterion("desire_start_time <>", value, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeGreaterThan(Long value) {
            addCriterion("desire_start_time >", value, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("desire_start_time >=", value, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeLessThan(Long value) {
            addCriterion("desire_start_time <", value, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeLessThanOrEqualTo(Long value) {
            addCriterion("desire_start_time <=", value, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeIn(List<Long> values) {
            addCriterion("desire_start_time in", values, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeNotIn(List<Long> values) {
            addCriterion("desire_start_time not in", values, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeBetween(Long value1, Long value2) {
            addCriterion("desire_start_time between", value1, value2, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andDesire_start_timeNotBetween(Long value1, Long value2) {
            addCriterion("desire_start_time not between", value1, value2, "desire_start_time");
            return (Criteria) this;
        }

        public Criteria andUni_priceIsNull() {
            addCriterion("uni_price is null");
            return (Criteria) this;
        }

        public Criteria andUni_priceIsNotNull() {
            addCriterion("uni_price is not null");
            return (Criteria) this;
        }

        public Criteria andUni_priceEqualTo(Integer value) {
            addCriterion("uni_price =", value, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceNotEqualTo(Integer value) {
            addCriterion("uni_price <>", value, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceGreaterThan(Integer value) {
            addCriterion("uni_price >", value, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceGreaterThanOrEqualTo(Integer value) {
            addCriterion("uni_price >=", value, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceLessThan(Integer value) {
            addCriterion("uni_price <", value, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceLessThanOrEqualTo(Integer value) {
            addCriterion("uni_price <=", value, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceIn(List<Integer> values) {
            addCriterion("uni_price in", values, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceNotIn(List<Integer> values) {
            addCriterion("uni_price not in", values, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceBetween(Integer value1, Integer value2) {
            addCriterion("uni_price between", value1, value2, "uni_price");
            return (Criteria) this;
        }

        public Criteria andUni_priceNotBetween(Integer value1, Integer value2) {
            addCriterion("uni_price not between", value1, value2, "uni_price");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateIsNull() {
            addCriterion("pre_pay_rate is null");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateIsNotNull() {
            addCriterion("pre_pay_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateEqualTo(Integer value) {
            addCriterion("pre_pay_rate =", value, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateNotEqualTo(Integer value) {
            addCriterion("pre_pay_rate <>", value, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateGreaterThan(Integer value) {
            addCriterion("pre_pay_rate >", value, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_pay_rate >=", value, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateLessThan(Integer value) {
            addCriterion("pre_pay_rate <", value, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateLessThanOrEqualTo(Integer value) {
            addCriterion("pre_pay_rate <=", value, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateIn(List<Integer> values) {
            addCriterion("pre_pay_rate in", values, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateNotIn(List<Integer> values) {
            addCriterion("pre_pay_rate not in", values, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateBetween(Integer value1, Integer value2) {
            addCriterion("pre_pay_rate between", value1, value2, "pre_pay_rate");
            return (Criteria) this;
        }

        public Criteria andPre_pay_rateNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_pay_rate not between", value1, value2, "pre_pay_rate");
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

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andGot_timeIsNull() {
            addCriterion("got_time is null");
            return (Criteria) this;
        }

        public Criteria andGot_timeIsNotNull() {
            addCriterion("got_time is not null");
            return (Criteria) this;
        }

        public Criteria andGot_timeEqualTo(Long value) {
            addCriterion("got_time =", value, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeNotEqualTo(Long value) {
            addCriterion("got_time <>", value, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeGreaterThan(Long value) {
            addCriterion("got_time >", value, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("got_time >=", value, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeLessThan(Long value) {
            addCriterion("got_time <", value, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeLessThanOrEqualTo(Long value) {
            addCriterion("got_time <=", value, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeIn(List<Long> values) {
            addCriterion("got_time in", values, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeNotIn(List<Long> values) {
            addCriterion("got_time not in", values, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeBetween(Long value1, Long value2) {
            addCriterion("got_time between", value1, value2, "got_time");
            return (Criteria) this;
        }

        public Criteria andGot_timeNotBetween(Long value1, Long value2) {
            addCriterion("got_time not between", value1, value2, "got_time");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderIsNull() {
            addCriterion("driver_leader is null");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderIsNotNull() {
            addCriterion("driver_leader is not null");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderEqualTo(Long value) {
            addCriterion("driver_leader =", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderNotEqualTo(Long value) {
            addCriterion("driver_leader <>", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderGreaterThan(Long value) {
            addCriterion("driver_leader >", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_leader >=", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderLessThan(Long value) {
            addCriterion("driver_leader <", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderLessThanOrEqualTo(Long value) {
            addCriterion("driver_leader <=", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderIn(List<Long> values) {
            addCriterion("driver_leader in", values, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderNotIn(List<Long> values) {
            addCriterion("driver_leader not in", values, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderBetween(Long value1, Long value2) {
            addCriterion("driver_leader between", value1, value2, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderNotBetween(Long value1, Long value2) {
            addCriterion("driver_leader not between", value1, value2, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeIsNull() {
            addCriterion("actual_start_time is null");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeIsNotNull() {
            addCriterion("actual_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeEqualTo(Long value) {
            addCriterion("actual_start_time =", value, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeNotEqualTo(Long value) {
            addCriterion("actual_start_time <>", value, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeGreaterThan(Long value) {
            addCriterion("actual_start_time >", value, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_start_time >=", value, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeLessThan(Long value) {
            addCriterion("actual_start_time <", value, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeLessThanOrEqualTo(Long value) {
            addCriterion("actual_start_time <=", value, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeIn(List<Long> values) {
            addCriterion("actual_start_time in", values, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeNotIn(List<Long> values) {
            addCriterion("actual_start_time not in", values, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeBetween(Long value1, Long value2) {
            addCriterion("actual_start_time between", value1, value2, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_start_timeNotBetween(Long value1, Long value2) {
            addCriterion("actual_start_time not between", value1, value2, "actual_start_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeIsNull() {
            addCriterion("actual_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeIsNotNull() {
            addCriterion("actual_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeEqualTo(Long value) {
            addCriterion("actual_finish_time =", value, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeNotEqualTo(Long value) {
            addCriterion("actual_finish_time <>", value, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeGreaterThan(Long value) {
            addCriterion("actual_finish_time >", value, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_finish_time >=", value, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeLessThan(Long value) {
            addCriterion("actual_finish_time <", value, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeLessThanOrEqualTo(Long value) {
            addCriterion("actual_finish_time <=", value, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeIn(List<Long> values) {
            addCriterion("actual_finish_time in", values, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeNotIn(List<Long> values) {
            addCriterion("actual_finish_time not in", values, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeBetween(Long value1, Long value2) {
            addCriterion("actual_finish_time between", value1, value2, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andActual_finish_timeNotBetween(Long value1, Long value2) {
            addCriterion("actual_finish_time not between", value1, value2, "actual_finish_time");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusIsNull() {
            addCriterion("company_bonus is null");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusIsNotNull() {
            addCriterion("company_bonus is not null");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusEqualTo(Integer value) {
            addCriterion("company_bonus =", value, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusNotEqualTo(Integer value) {
            addCriterion("company_bonus <>", value, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusGreaterThan(Integer value) {
            addCriterion("company_bonus >", value, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_bonus >=", value, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusLessThan(Integer value) {
            addCriterion("company_bonus <", value, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusLessThanOrEqualTo(Integer value) {
            addCriterion("company_bonus <=", value, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusIn(List<Integer> values) {
            addCriterion("company_bonus in", values, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusNotIn(List<Integer> values) {
            addCriterion("company_bonus not in", values, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusBetween(Integer value1, Integer value2) {
            addCriterion("company_bonus between", value1, value2, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andCompany_bonusNotBetween(Integer value1, Integer value2) {
            addCriterion("company_bonus not between", value1, value2, "company_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusIsNull() {
            addCriterion("leader_bonus is null");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusIsNotNull() {
            addCriterion("leader_bonus is not null");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusEqualTo(Integer value) {
            addCriterion("leader_bonus =", value, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusNotEqualTo(Integer value) {
            addCriterion("leader_bonus <>", value, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusGreaterThan(Integer value) {
            addCriterion("leader_bonus >", value, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusGreaterThanOrEqualTo(Integer value) {
            addCriterion("leader_bonus >=", value, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusLessThan(Integer value) {
            addCriterion("leader_bonus <", value, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusLessThanOrEqualTo(Integer value) {
            addCriterion("leader_bonus <=", value, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusIn(List<Integer> values) {
            addCriterion("leader_bonus in", values, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusNotIn(List<Integer> values) {
            addCriterion("leader_bonus not in", values, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusBetween(Integer value1, Integer value2) {
            addCriterion("leader_bonus between", value1, value2, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andLeader_bonusNotBetween(Integer value1, Integer value2) {
            addCriterion("leader_bonus not between", value1, value2, "leader_bonus");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceIsNull() {
            addCriterion("driver_insurance is null");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceIsNotNull() {
            addCriterion("driver_insurance is not null");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceEqualTo(Integer value) {
            addCriterion("driver_insurance =", value, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceNotEqualTo(Integer value) {
            addCriterion("driver_insurance <>", value, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceGreaterThan(Integer value) {
            addCriterion("driver_insurance >", value, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceGreaterThanOrEqualTo(Integer value) {
            addCriterion("driver_insurance >=", value, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceLessThan(Integer value) {
            addCriterion("driver_insurance <", value, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceLessThanOrEqualTo(Integer value) {
            addCriterion("driver_insurance <=", value, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceIn(List<Integer> values) {
            addCriterion("driver_insurance in", values, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceNotIn(List<Integer> values) {
            addCriterion("driver_insurance not in", values, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceBetween(Integer value1, Integer value2) {
            addCriterion("driver_insurance between", value1, value2, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDriver_insuranceNotBetween(Integer value1, Integer value2) {
            addCriterion("driver_insurance not between", value1, value2, "driver_insurance");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateIsNull() {
            addCriterion("day_money_out_rate is null");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateIsNotNull() {
            addCriterion("day_money_out_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateEqualTo(Integer value) {
            addCriterion("day_money_out_rate =", value, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateNotEqualTo(Integer value) {
            addCriterion("day_money_out_rate <>", value, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateGreaterThan(Integer value) {
            addCriterion("day_money_out_rate >", value, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_money_out_rate >=", value, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateLessThan(Integer value) {
            addCriterion("day_money_out_rate <", value, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateLessThanOrEqualTo(Integer value) {
            addCriterion("day_money_out_rate <=", value, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateIn(List<Integer> values) {
            addCriterion("day_money_out_rate in", values, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateNotIn(List<Integer> values) {
            addCriterion("day_money_out_rate not in", values, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateBetween(Integer value1, Integer value2) {
            addCriterion("day_money_out_rate between", value1, value2, "day_money_out_rate");
            return (Criteria) this;
        }

        public Criteria andDay_money_out_rateNotBetween(Integer value1, Integer value2) {
            addCriterion("day_money_out_rate not between", value1, value2, "day_money_out_rate");
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