package pers.jssd.ark.rpc.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TMessageLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TMessageLogExample() {
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

        public Criteria andMesLogIdIsNull() {
            addCriterion("mes_log_id is null");
            return (Criteria) this;
        }

        public Criteria andMesLogIdIsNotNull() {
            addCriterion("mes_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andMesLogIdEqualTo(Integer value) {
            addCriterion("mes_log_id =", value, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdNotEqualTo(Integer value) {
            addCriterion("mes_log_id <>", value, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdGreaterThan(Integer value) {
            addCriterion("mes_log_id >", value, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mes_log_id >=", value, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdLessThan(Integer value) {
            addCriterion("mes_log_id <", value, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("mes_log_id <=", value, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdIn(List<Integer> values) {
            addCriterion("mes_log_id in", values, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdNotIn(List<Integer> values) {
            addCriterion("mes_log_id not in", values, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdBetween(Integer value1, Integer value2) {
            addCriterion("mes_log_id between", value1, value2, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mes_log_id not between", value1, value2, "mesLogId");
            return (Criteria) this;
        }

        public Criteria andMesIdIsNull() {
            addCriterion("mes_id is null");
            return (Criteria) this;
        }

        public Criteria andMesIdIsNotNull() {
            addCriterion("mes_id is not null");
            return (Criteria) this;
        }

        public Criteria andMesIdEqualTo(Integer value) {
            addCriterion("mes_id =", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdNotEqualTo(Integer value) {
            addCriterion("mes_id <>", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdGreaterThan(Integer value) {
            addCriterion("mes_id >", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mes_id >=", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdLessThan(Integer value) {
            addCriterion("mes_id <", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdLessThanOrEqualTo(Integer value) {
            addCriterion("mes_id <=", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdIn(List<Integer> values) {
            addCriterion("mes_id in", values, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdNotIn(List<Integer> values) {
            addCriterion("mes_id not in", values, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdBetween(Integer value1, Integer value2) {
            addCriterion("mes_id between", value1, value2, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mes_id not between", value1, value2, "mesId");
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