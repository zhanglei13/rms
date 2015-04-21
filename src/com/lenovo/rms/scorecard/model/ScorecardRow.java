package com.lenovo.rms.scorecard.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ScorecardRow {
    private String itCode;

    private String nameEn;

    private List<String> eval_types;

    private List<Integer> eval_levels;

    public ScorecardRow() {
        eval_types = new ArrayList<>();
        eval_levels = new ArrayList<>();
    }

    public ScorecardRow(String itCode, String nameEn, List<String> eval_types, List<Integer> eval_levels) {
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

    public List<String> getEval_types() {
        return eval_types;
    }

    public void setEval_types(List<String> eval_types) {
        this.eval_types = eval_types;
    }

    public List<Integer> getEval_levels() {
        return eval_levels;
    }

    public void setEval_levels(List<Integer> eval_levels) {
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
