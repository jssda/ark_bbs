package pers.jssd.ark.rpc.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TArticleExample() {
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

        public Criteria andArtIdIsNull() {
            addCriterion("art_id is null");
            return (Criteria) this;
        }

        public Criteria andArtIdIsNotNull() {
            addCriterion("art_id is not null");
            return (Criteria) this;
        }

        public Criteria andArtIdEqualTo(Integer value) {
            addCriterion("art_id =", value, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdNotEqualTo(Integer value) {
            addCriterion("art_id <>", value, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdGreaterThan(Integer value) {
            addCriterion("art_id >", value, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("art_id >=", value, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdLessThan(Integer value) {
            addCriterion("art_id <", value, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdLessThanOrEqualTo(Integer value) {
            addCriterion("art_id <=", value, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdIn(List<Integer> values) {
            addCriterion("art_id in", values, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdNotIn(List<Integer> values) {
            addCriterion("art_id not in", values, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdBetween(Integer value1, Integer value2) {
            addCriterion("art_id between", value1, value2, "artId");
            return (Criteria) this;
        }

        public Criteria andArtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("art_id not between", value1, value2, "artId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdIsNull() {
            addCriterion("art_user_id is null");
            return (Criteria) this;
        }

        public Criteria andArtUserIdIsNotNull() {
            addCriterion("art_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andArtUserIdEqualTo(Integer value) {
            addCriterion("art_user_id =", value, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdNotEqualTo(Integer value) {
            addCriterion("art_user_id <>", value, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdGreaterThan(Integer value) {
            addCriterion("art_user_id >", value, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("art_user_id >=", value, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdLessThan(Integer value) {
            addCriterion("art_user_id <", value, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("art_user_id <=", value, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdIn(List<Integer> values) {
            addCriterion("art_user_id in", values, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdNotIn(List<Integer> values) {
            addCriterion("art_user_id not in", values, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdBetween(Integer value1, Integer value2) {
            addCriterion("art_user_id between", value1, value2, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("art_user_id not between", value1, value2, "artUserId");
            return (Criteria) this;
        }

        public Criteria andArtTitleIsNull() {
            addCriterion("art_title is null");
            return (Criteria) this;
        }

        public Criteria andArtTitleIsNotNull() {
            addCriterion("art_title is not null");
            return (Criteria) this;
        }

        public Criteria andArtTitleEqualTo(String value) {
            addCriterion("art_title =", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotEqualTo(String value) {
            addCriterion("art_title <>", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleGreaterThan(String value) {
            addCriterion("art_title >", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleGreaterThanOrEqualTo(String value) {
            addCriterion("art_title >=", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleLessThan(String value) {
            addCriterion("art_title <", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleLessThanOrEqualTo(String value) {
            addCriterion("art_title <=", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleLike(String value) {
            addCriterion("art_title like", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotLike(String value) {
            addCriterion("art_title not like", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleIn(List<String> values) {
            addCriterion("art_title in", values, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotIn(List<String> values) {
            addCriterion("art_title not in", values, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleBetween(String value1, String value2) {
            addCriterion("art_title between", value1, value2, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotBetween(String value1, String value2) {
            addCriterion("art_title not between", value1, value2, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtSecIdIsNull() {
            addCriterion("art_sec_id is null");
            return (Criteria) this;
        }

        public Criteria andArtSecIdIsNotNull() {
            addCriterion("art_sec_id is not null");
            return (Criteria) this;
        }

        public Criteria andArtSecIdEqualTo(Integer value) {
            addCriterion("art_sec_id =", value, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdNotEqualTo(Integer value) {
            addCriterion("art_sec_id <>", value, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdGreaterThan(Integer value) {
            addCriterion("art_sec_id >", value, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("art_sec_id >=", value, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdLessThan(Integer value) {
            addCriterion("art_sec_id <", value, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdLessThanOrEqualTo(Integer value) {
            addCriterion("art_sec_id <=", value, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdIn(List<Integer> values) {
            addCriterion("art_sec_id in", values, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdNotIn(List<Integer> values) {
            addCriterion("art_sec_id not in", values, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdBetween(Integer value1, Integer value2) {
            addCriterion("art_sec_id between", value1, value2, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtSecIdNotBetween(Integer value1, Integer value2) {
            addCriterion("art_sec_id not between", value1, value2, "artSecId");
            return (Criteria) this;
        }

        public Criteria andArtHotNumIsNull() {
            addCriterion("art_hot_num is null");
            return (Criteria) this;
        }

        public Criteria andArtHotNumIsNotNull() {
            addCriterion("art_hot_num is not null");
            return (Criteria) this;
        }

        public Criteria andArtHotNumEqualTo(Integer value) {
            addCriterion("art_hot_num =", value, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumNotEqualTo(Integer value) {
            addCriterion("art_hot_num <>", value, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumGreaterThan(Integer value) {
            addCriterion("art_hot_num >", value, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("art_hot_num >=", value, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumLessThan(Integer value) {
            addCriterion("art_hot_num <", value, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumLessThanOrEqualTo(Integer value) {
            addCriterion("art_hot_num <=", value, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumIn(List<Integer> values) {
            addCriterion("art_hot_num in", values, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumNotIn(List<Integer> values) {
            addCriterion("art_hot_num not in", values, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumBetween(Integer value1, Integer value2) {
            addCriterion("art_hot_num between", value1, value2, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtHotNumNotBetween(Integer value1, Integer value2) {
            addCriterion("art_hot_num not between", value1, value2, "artHotNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumIsNull() {
            addCriterion("art_like_num is null");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumIsNotNull() {
            addCriterion("art_like_num is not null");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumEqualTo(Integer value) {
            addCriterion("art_like_num =", value, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumNotEqualTo(Integer value) {
            addCriterion("art_like_num <>", value, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumGreaterThan(Integer value) {
            addCriterion("art_like_num >", value, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("art_like_num >=", value, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumLessThan(Integer value) {
            addCriterion("art_like_num <", value, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumLessThanOrEqualTo(Integer value) {
            addCriterion("art_like_num <=", value, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumIn(List<Integer> values) {
            addCriterion("art_like_num in", values, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumNotIn(List<Integer> values) {
            addCriterion("art_like_num not in", values, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumBetween(Integer value1, Integer value2) {
            addCriterion("art_like_num between", value1, value2, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andArtLikeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("art_like_num not between", value1, value2, "artLikeNum");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNull() {
            addCriterion("is_top is null");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNotNull() {
            addCriterion("is_top is not null");
            return (Criteria) this;
        }

        public Criteria andIsTopEqualTo(String value) {
            addCriterion("is_top =", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotEqualTo(String value) {
            addCriterion("is_top <>", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThan(String value) {
            addCriterion("is_top >", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThanOrEqualTo(String value) {
            addCriterion("is_top >=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThan(String value) {
            addCriterion("is_top <", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThanOrEqualTo(String value) {
            addCriterion("is_top <=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLike(String value) {
            addCriterion("is_top like", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotLike(String value) {
            addCriterion("is_top not like", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopIn(List<String> values) {
            addCriterion("is_top in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotIn(List<String> values) {
            addCriterion("is_top not in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopBetween(String value1, String value2) {
            addCriterion("is_top between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotBetween(String value1, String value2) {
            addCriterion("is_top not between", value1, value2, "isTop");
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