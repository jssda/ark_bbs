package pers.jssd.ark.rpc.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TMessageExample() {
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

        public Criteria andMesTitleIsNull() {
            addCriterion("mes_title is null");
            return (Criteria) this;
        }

        public Criteria andMesTitleIsNotNull() {
            addCriterion("mes_title is not null");
            return (Criteria) this;
        }

        public Criteria andMesTitleEqualTo(String value) {
            addCriterion("mes_title =", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotEqualTo(String value) {
            addCriterion("mes_title <>", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleGreaterThan(String value) {
            addCriterion("mes_title >", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleGreaterThanOrEqualTo(String value) {
            addCriterion("mes_title >=", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleLessThan(String value) {
            addCriterion("mes_title <", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleLessThanOrEqualTo(String value) {
            addCriterion("mes_title <=", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleLike(String value) {
            addCriterion("mes_title like", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotLike(String value) {
            addCriterion("mes_title not like", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleIn(List<String> values) {
            addCriterion("mes_title in", values, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotIn(List<String> values) {
            addCriterion("mes_title not in", values, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleBetween(String value1, String value2) {
            addCriterion("mes_title between", value1, value2, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotBetween(String value1, String value2) {
            addCriterion("mes_title not between", value1, value2, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesContentIsNull() {
            addCriterion("mes_content is null");
            return (Criteria) this;
        }

        public Criteria andMesContentIsNotNull() {
            addCriterion("mes_content is not null");
            return (Criteria) this;
        }

        public Criteria andMesContentEqualTo(String value) {
            addCriterion("mes_content =", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentNotEqualTo(String value) {
            addCriterion("mes_content <>", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentGreaterThan(String value) {
            addCriterion("mes_content >", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentGreaterThanOrEqualTo(String value) {
            addCriterion("mes_content >=", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentLessThan(String value) {
            addCriterion("mes_content <", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentLessThanOrEqualTo(String value) {
            addCriterion("mes_content <=", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentLike(String value) {
            addCriterion("mes_content like", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentNotLike(String value) {
            addCriterion("mes_content not like", value, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentIn(List<String> values) {
            addCriterion("mes_content in", values, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentNotIn(List<String> values) {
            addCriterion("mes_content not in", values, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentBetween(String value1, String value2) {
            addCriterion("mes_content between", value1, value2, "mesContent");
            return (Criteria) this;
        }

        public Criteria andMesContentNotBetween(String value1, String value2) {
            addCriterion("mes_content not between", value1, value2, "mesContent");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNull() {
            addCriterion("from_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNotNull() {
            addCriterion("from_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdEqualTo(Integer value) {
            addCriterion("from_user_id =", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotEqualTo(Integer value) {
            addCriterion("from_user_id <>", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThan(Integer value) {
            addCriterion("from_user_id >", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_user_id >=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThan(Integer value) {
            addCriterion("from_user_id <", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("from_user_id <=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIn(List<Integer> values) {
            addCriterion("from_user_id in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotIn(List<Integer> values) {
            addCriterion("from_user_id not in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdBetween(Integer value1, Integer value2) {
            addCriterion("from_user_id between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("from_user_id not between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNull() {
            addCriterion("to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNotNull() {
            addCriterion("to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdEqualTo(Integer value) {
            addCriterion("to_user_id =", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotEqualTo(Integer value) {
            addCriterion("to_user_id <>", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThan(Integer value) {
            addCriterion("to_user_id >", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_user_id >=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThan(Integer value) {
            addCriterion("to_user_id <", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_user_id <=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIn(List<Integer> values) {
            addCriterion("to_user_id in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotIn(List<Integer> values) {
            addCriterion("to_user_id not in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdBetween(Integer value1, Integer value2) {
            addCriterion("to_user_id between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_user_id not between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andMesTypeIsNull() {
            addCriterion("mes_type is null");
            return (Criteria) this;
        }

        public Criteria andMesTypeIsNotNull() {
            addCriterion("mes_type is not null");
            return (Criteria) this;
        }

        public Criteria andMesTypeEqualTo(String value) {
            addCriterion("mes_type =", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeNotEqualTo(String value) {
            addCriterion("mes_type <>", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeGreaterThan(String value) {
            addCriterion("mes_type >", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mes_type >=", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeLessThan(String value) {
            addCriterion("mes_type <", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeLessThanOrEqualTo(String value) {
            addCriterion("mes_type <=", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeLike(String value) {
            addCriterion("mes_type like", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeNotLike(String value) {
            addCriterion("mes_type not like", value, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeIn(List<String> values) {
            addCriterion("mes_type in", values, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeNotIn(List<String> values) {
            addCriterion("mes_type not in", values, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeBetween(String value1, String value2) {
            addCriterion("mes_type between", value1, value2, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesTypeNotBetween(String value1, String value2) {
            addCriterion("mes_type not between", value1, value2, "mesType");
            return (Criteria) this;
        }

        public Criteria andMesStateIsNull() {
            addCriterion("mes_state is null");
            return (Criteria) this;
        }

        public Criteria andMesStateIsNotNull() {
            addCriterion("mes_state is not null");
            return (Criteria) this;
        }

        public Criteria andMesStateEqualTo(String value) {
            addCriterion("mes_state =", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateNotEqualTo(String value) {
            addCriterion("mes_state <>", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateGreaterThan(String value) {
            addCriterion("mes_state >", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateGreaterThanOrEqualTo(String value) {
            addCriterion("mes_state >=", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateLessThan(String value) {
            addCriterion("mes_state <", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateLessThanOrEqualTo(String value) {
            addCriterion("mes_state <=", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateLike(String value) {
            addCriterion("mes_state like", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateNotLike(String value) {
            addCriterion("mes_state not like", value, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateIn(List<String> values) {
            addCriterion("mes_state in", values, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateNotIn(List<String> values) {
            addCriterion("mes_state not in", values, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateBetween(String value1, String value2) {
            addCriterion("mes_state between", value1, value2, "mesState");
            return (Criteria) this;
        }

        public Criteria andMesStateNotBetween(String value1, String value2) {
            addCriterion("mes_state not between", value1, value2, "mesState");
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