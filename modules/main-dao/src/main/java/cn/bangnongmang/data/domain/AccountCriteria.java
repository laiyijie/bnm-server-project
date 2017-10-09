package cn.bangnongmang.data.domain;

import java.util.ArrayList;
import java.util.List;

public class AccountCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountCriteria() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSalt_stringIsNull() {
            addCriterion("salt_string is null");
            return (Criteria) this;
        }

        public Criteria andSalt_stringIsNotNull() {
            addCriterion("salt_string is not null");
            return (Criteria) this;
        }

        public Criteria andSalt_stringEqualTo(String value) {
            addCriterion("salt_string =", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringNotEqualTo(String value) {
            addCriterion("salt_string <>", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringGreaterThan(String value) {
            addCriterion("salt_string >", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringGreaterThanOrEqualTo(String value) {
            addCriterion("salt_string >=", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringLessThan(String value) {
            addCriterion("salt_string <", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringLessThanOrEqualTo(String value) {
            addCriterion("salt_string <=", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringLike(String value) {
            addCriterion("salt_string like", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringNotLike(String value) {
            addCriterion("salt_string not like", value, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringIn(List<String> values) {
            addCriterion("salt_string in", values, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringNotIn(List<String> values) {
            addCriterion("salt_string not in", values, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringBetween(String value1, String value2) {
            addCriterion("salt_string between", value1, value2, "salt_string");
            return (Criteria) this;
        }

        public Criteria andSalt_stringNotBetween(String value1, String value2) {
            addCriterion("salt_string not between", value1, value2, "salt_string");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordIsNull() {
            addCriterion("temp_password is null");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordIsNotNull() {
            addCriterion("temp_password is not null");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordEqualTo(String value) {
            addCriterion("temp_password =", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordNotEqualTo(String value) {
            addCriterion("temp_password <>", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordGreaterThan(String value) {
            addCriterion("temp_password >", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordGreaterThanOrEqualTo(String value) {
            addCriterion("temp_password >=", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordLessThan(String value) {
            addCriterion("temp_password <", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordLessThanOrEqualTo(String value) {
            addCriterion("temp_password <=", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordLike(String value) {
            addCriterion("temp_password like", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordNotLike(String value) {
            addCriterion("temp_password not like", value, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordIn(List<String> values) {
            addCriterion("temp_password in", values, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordNotIn(List<String> values) {
            addCriterion("temp_password not in", values, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordBetween(String value1, String value2) {
            addCriterion("temp_password between", value1, value2, "temp_password");
            return (Criteria) this;
        }

        public Criteria andTemp_passwordNotBetween(String value1, String value2) {
            addCriterion("temp_password not between", value1, value2, "temp_password");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Long value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Long value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Long value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Long> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
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

        public Criteria andTemp_available_timeIsNull() {
            addCriterion("temp_available_time is null");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeIsNotNull() {
            addCriterion("temp_available_time is not null");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeEqualTo(Long value) {
            addCriterion("temp_available_time =", value, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeNotEqualTo(Long value) {
            addCriterion("temp_available_time <>", value, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeGreaterThan(Long value) {
            addCriterion("temp_available_time >", value, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("temp_available_time >=", value, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeLessThan(Long value) {
            addCriterion("temp_available_time <", value, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeLessThanOrEqualTo(Long value) {
            addCriterion("temp_available_time <=", value, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeIn(List<Long> values) {
            addCriterion("temp_available_time in", values, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeNotIn(List<Long> values) {
            addCriterion("temp_available_time not in", values, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeBetween(Long value1, Long value2) {
            addCriterion("temp_available_time between", value1, value2, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andTemp_available_timeNotBetween(Long value1, Long value2) {
            addCriterion("temp_available_time not between", value1, value2, "temp_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordIsNull() {
            addCriterion("onetime_temp_password is null");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordIsNotNull() {
            addCriterion("onetime_temp_password is not null");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordEqualTo(String value) {
            addCriterion("onetime_temp_password =", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordNotEqualTo(String value) {
            addCriterion("onetime_temp_password <>", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordGreaterThan(String value) {
            addCriterion("onetime_temp_password >", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordGreaterThanOrEqualTo(String value) {
            addCriterion("onetime_temp_password >=", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordLessThan(String value) {
            addCriterion("onetime_temp_password <", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordLessThanOrEqualTo(String value) {
            addCriterion("onetime_temp_password <=", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordLike(String value) {
            addCriterion("onetime_temp_password like", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordNotLike(String value) {
            addCriterion("onetime_temp_password not like", value, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordIn(List<String> values) {
            addCriterion("onetime_temp_password in", values, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordNotIn(List<String> values) {
            addCriterion("onetime_temp_password not in", values, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordBetween(String value1, String value2) {
            addCriterion("onetime_temp_password between", value1, value2, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_temp_passwordNotBetween(String value1, String value2) {
            addCriterion("onetime_temp_password not between", value1, value2, "onetime_temp_password");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeIsNull() {
            addCriterion("onetime_available_time is null");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeIsNotNull() {
            addCriterion("onetime_available_time is not null");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeEqualTo(Long value) {
            addCriterion("onetime_available_time =", value, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeNotEqualTo(Long value) {
            addCriterion("onetime_available_time <>", value, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeGreaterThan(Long value) {
            addCriterion("onetime_available_time >", value, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeGreaterThanOrEqualTo(Long value) {
            addCriterion("onetime_available_time >=", value, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeLessThan(Long value) {
            addCriterion("onetime_available_time <", value, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeLessThanOrEqualTo(Long value) {
            addCriterion("onetime_available_time <=", value, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeIn(List<Long> values) {
            addCriterion("onetime_available_time in", values, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeNotIn(List<Long> values) {
            addCriterion("onetime_available_time not in", values, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeBetween(Long value1, Long value2) {
            addCriterion("onetime_available_time between", value1, value2, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andOnetime_available_timeNotBetween(Long value1, Long value2) {
            addCriterion("onetime_available_time not between", value1, value2, "onetime_available_time");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
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

        public Criteria andWechat_union_idIsNull() {
            addCriterion("wechat_union_id is null");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idIsNotNull() {
            addCriterion("wechat_union_id is not null");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idEqualTo(String value) {
            addCriterion("wechat_union_id =", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idNotEqualTo(String value) {
            addCriterion("wechat_union_id <>", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idGreaterThan(String value) {
            addCriterion("wechat_union_id >", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_union_id >=", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idLessThan(String value) {
            addCriterion("wechat_union_id <", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idLessThanOrEqualTo(String value) {
            addCriterion("wechat_union_id <=", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idLike(String value) {
            addCriterion("wechat_union_id like", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idNotLike(String value) {
            addCriterion("wechat_union_id not like", value, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idIn(List<String> values) {
            addCriterion("wechat_union_id in", values, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idNotIn(List<String> values) {
            addCriterion("wechat_union_id not in", values, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idBetween(String value1, String value2) {
            addCriterion("wechat_union_id between", value1, value2, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_union_idNotBetween(String value1, String value2) {
            addCriterion("wechat_union_id not between", value1, value2, "wechat_union_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idIsNull() {
            addCriterion("wechat_open_id is null");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idIsNotNull() {
            addCriterion("wechat_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idEqualTo(String value) {
            addCriterion("wechat_open_id =", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idNotEqualTo(String value) {
            addCriterion("wechat_open_id <>", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idGreaterThan(String value) {
            addCriterion("wechat_open_id >", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_open_id >=", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idLessThan(String value) {
            addCriterion("wechat_open_id <", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idLessThanOrEqualTo(String value) {
            addCriterion("wechat_open_id <=", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idLike(String value) {
            addCriterion("wechat_open_id like", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idNotLike(String value) {
            addCriterion("wechat_open_id not like", value, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idIn(List<String> values) {
            addCriterion("wechat_open_id in", values, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idNotIn(List<String> values) {
            addCriterion("wechat_open_id not in", values, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idBetween(String value1, String value2) {
            addCriterion("wechat_open_id between", value1, value2, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andWechat_open_idNotBetween(String value1, String value2) {
            addCriterion("wechat_open_id not between", value1, value2, "wechat_open_id");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andIdcard_idIsNull() {
            addCriterion("idcard_id is null");
            return (Criteria) this;
        }

        public Criteria andIdcard_idIsNotNull() {
            addCriterion("idcard_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdcard_idEqualTo(String value) {
            addCriterion("idcard_id =", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idNotEqualTo(String value) {
            addCriterion("idcard_id <>", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idGreaterThan(String value) {
            addCriterion("idcard_id >", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_id >=", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idLessThan(String value) {
            addCriterion("idcard_id <", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idLessThanOrEqualTo(String value) {
            addCriterion("idcard_id <=", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idLike(String value) {
            addCriterion("idcard_id like", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idNotLike(String value) {
            addCriterion("idcard_id not like", value, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idIn(List<String> values) {
            addCriterion("idcard_id in", values, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idNotIn(List<String> values) {
            addCriterion("idcard_id not in", values, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idBetween(String value1, String value2) {
            addCriterion("idcard_id between", value1, value2, "idcard_id");
            return (Criteria) this;
        }

        public Criteria andIdcard_idNotBetween(String value1, String value2) {
            addCriterion("idcard_id not between", value1, value2, "idcard_id");
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

        public Criteria andDriver_leaderIsNull() {
            addCriterion("driver_leader is null");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderIsNotNull() {
            addCriterion("driver_leader is not null");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderEqualTo(Integer value) {
            addCriterion("driver_leader =", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderNotEqualTo(Integer value) {
            addCriterion("driver_leader <>", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderGreaterThan(Integer value) {
            addCriterion("driver_leader >", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderGreaterThanOrEqualTo(Integer value) {
            addCriterion("driver_leader >=", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderLessThan(Integer value) {
            addCriterion("driver_leader <", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderLessThanOrEqualTo(Integer value) {
            addCriterion("driver_leader <=", value, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderIn(List<Integer> values) {
            addCriterion("driver_leader in", values, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderNotIn(List<Integer> values) {
            addCriterion("driver_leader not in", values, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderBetween(Integer value1, Integer value2) {
            addCriterion("driver_leader between", value1, value2, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andDriver_leaderNotBetween(Integer value1, Integer value2) {
            addCriterion("driver_leader not between", value1, value2, "driver_leader");
            return (Criteria) this;
        }

        public Criteria andWechat_infoIsNull() {
            addCriterion("wechat_info is null");
            return (Criteria) this;
        }

        public Criteria andWechat_infoIsNotNull() {
            addCriterion("wechat_info is not null");
            return (Criteria) this;
        }

        public Criteria andWechat_infoEqualTo(String value) {
            addCriterion("wechat_info =", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoNotEqualTo(String value) {
            addCriterion("wechat_info <>", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoGreaterThan(String value) {
            addCriterion("wechat_info >", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_info >=", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoLessThan(String value) {
            addCriterion("wechat_info <", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoLessThanOrEqualTo(String value) {
            addCriterion("wechat_info <=", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoLike(String value) {
            addCriterion("wechat_info like", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoNotLike(String value) {
            addCriterion("wechat_info not like", value, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoIn(List<String> values) {
            addCriterion("wechat_info in", values, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoNotIn(List<String> values) {
            addCriterion("wechat_info not in", values, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoBetween(String value1, String value2) {
            addCriterion("wechat_info between", value1, value2, "wechat_info");
            return (Criteria) this;
        }

        public Criteria andWechat_infoNotBetween(String value1, String value2) {
            addCriterion("wechat_info not between", value1, value2, "wechat_info");
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