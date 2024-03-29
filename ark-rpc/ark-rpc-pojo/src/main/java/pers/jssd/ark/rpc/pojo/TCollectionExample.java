package pers.jssd.ark.rpc.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCollectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCollectionExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andColIdIsNull() {
            addCriterion("col_id is null");
            return (Criteria) this;
        }

        public Criteria andColIdIsNotNull() {
            addCriterion("col_id is not null");
            return (Criteria) this;
        }

        public Criteria andColIdEqualTo(Integer value) {
            addCriterion("col_id =", value, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdNotEqualTo(Integer value) {
            addCriterion("col_id <>", value, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdGreaterThan(Integer value) {
            addCriterion("col_id >", value, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("col_id >=", value, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdLessThan(Integer value) {
            addCriterion("col_id <", value, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdLessThanOrEqualTo(Integer value) {
            addCriterion("col_id <=", value, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdIn(List<Integer> values) {
            addCriterion("col_id in", values, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdNotIn(List<Integer> values) {
            addCriterion("col_id not in", values, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdBetween(Integer value1, Integer value2) {
            addCriterion("col_id between", value1, value2, "colId");
            return (Criteria) this;
        }

        public Criteria andColIdNotBetween(Integer value1, Integer value2) {
            addCriterion("col_id not between", value1, value2, "colId");
            return (Criteria) this;
        }

        public Criteria andColUserIdIsNull() {
            addCriterion("col_user_id is null");
            return (Criteria) this;
        }

        public Criteria andColUserIdIsNotNull() {
            addCriterion("col_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andColUserIdEqualTo(Integer value) {
            addCriterion("col_user_id =", value, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdNotEqualTo(Integer value) {
            addCriterion("col_user_id <>", value, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdGreaterThan(Integer value) {
            addCriterion("col_user_id >", value, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("col_user_id >=", value, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdLessThan(Integer value) {
            addCriterion("col_user_id <", value, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("col_user_id <=", value, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdIn(List<Integer> values) {
            addCriterion("col_user_id in", values, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdNotIn(List<Integer> values) {
            addCriterion("col_user_id not in", values, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdBetween(Integer value1, Integer value2) {
            addCriterion("col_user_id between", value1, value2, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("col_user_id not between", value1, value2, "colUserId");
            return (Criteria) this;
        }

        public Criteria andColArtIdIsNull() {
            addCriterion("col_art_id is null");
            return (Criteria) this;
        }

        public Criteria andColArtIdIsNotNull() {
            addCriterion("col_art_id is not null");
            return (Criteria) this;
        }

        public Criteria andColArtIdEqualTo(Integer value) {
            addCriterion("col_art_id =", value, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdNotEqualTo(Integer value) {
            addCriterion("col_art_id <>", value, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdGreaterThan(Integer value) {
            addCriterion("col_art_id >", value, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("col_art_id >=", value, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdLessThan(Integer value) {
            addCriterion("col_art_id <", value, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdLessThanOrEqualTo(Integer value) {
            addCriterion("col_art_id <=", value, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdIn(List<Integer> values) {
            addCriterion("col_art_id in", values, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdNotIn(List<Integer> values) {
            addCriterion("col_art_id not in", values, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdBetween(Integer value1, Integer value2) {
            addCriterion("col_art_id between", value1, value2, "colArtId");
            return (Criteria) this;
        }

        public Criteria andColArtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("col_art_id not between", value1, value2, "colArtId");
            return (Criteria) this;
        }

        public Criteria andCreateIsNull() {
            addCriterion("`create` is null");
            return (Criteria) this;
        }

        public Criteria andCreateIsNotNull() {
            addCriterion("`create` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateEqualTo(Date value) {
            addCriterion("`create` =", value, "create");
            return (Criteria) this;
        }

        public Criteria andCreateNotEqualTo(Date value) {
            addCriterion("`create` <>", value, "create");
            return (Criteria) this;
        }

        public Criteria andCreateGreaterThan(Date value) {
            addCriterion("`create` >", value, "create");
            return (Criteria) this;
        }

        public Criteria andCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("`create` >=", value, "create");
            return (Criteria) this;
        }

        public Criteria andCreateLessThan(Date value) {
            addCriterion("`create` <", value, "create");
            return (Criteria) this;
        }

        public Criteria andCreateLessThanOrEqualTo(Date value) {
            addCriterion("`create` <=", value, "create");
            return (Criteria) this;
        }

        public Criteria andCreateIn(List<Date> values) {
            addCriterion("`create` in", values, "create");
            return (Criteria) this;
        }

        public Criteria andCreateNotIn(List<Date> values) {
            addCriterion("`create` not in", values, "create");
            return (Criteria) this;
        }

        public Criteria andCreateBetween(Date value1, Date value2) {
            addCriterion("`create` between", value1, value2, "create");
            return (Criteria) this;
        }

        public Criteria andCreateNotBetween(Date value1, Date value2) {
            addCriterion("`create` not between", value1, value2, "create");
            return (Criteria) this;
        }

        public Criteria andUpdateIsNull() {
            addCriterion("`update` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateIsNotNull() {
            addCriterion("`update` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateEqualTo(Date value) {
            addCriterion("`update` =", value, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateNotEqualTo(Date value) {
            addCriterion("`update` <>", value, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateGreaterThan(Date value) {
            addCriterion("`update` >", value, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("`update` >=", value, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateLessThan(Date value) {
            addCriterion("`update` <", value, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateLessThanOrEqualTo(Date value) {
            addCriterion("`update` <=", value, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateIn(List<Date> values) {
            addCriterion("`update` in", values, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateNotIn(List<Date> values) {
            addCriterion("`update` not in", values, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateBetween(Date value1, Date value2) {
            addCriterion("`update` between", value1, value2, "update");
            return (Criteria) this;
        }

        public Criteria andUpdateNotBetween(Date value1, Date value2) {
            addCriterion("`update` not between", value1, value2, "update");
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