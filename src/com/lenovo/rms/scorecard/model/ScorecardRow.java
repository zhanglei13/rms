package com.lenovo.rms.scorecard.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ScorecardRow {
    private String itCode;

    private String nameEn;

    private String[] eval_types;

    private Integer[] eval_levels;

    public ScorecardRow() {
    }

    public ScorecardRow(String itCode, String nameEn, String[] eval_types, Integer[] eval_levels) {
        this.itCode = itCode;
        this.nameEn = nameEn;
        this.eval_types = eval_types;
        this.eval_levels = eval_levels;
    }

    public String getItCode() {
        return itCode;
    }

    public void setItCode(String itCode) {
        this.itCode = itCode;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String[] getEval_types() {
        return eval_types;
    }

    public void setEval_types(String[] eval_types) {
        this.eval_types = eval_types;
    }

    public Integer[] getEval_levels() {
        return eval_levels;
    }

    public void setEval_levels(Integer[] eval_levels) {
        this.eval_levels = eval_levels;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
