package com.lenovo.rms.scorecard.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ScorecardCollection {
    private Map<String, List<ScorecardRow>> collections;

    public ScorecardCollection() {
        collections = new HashMap<>();
    }
    
    public ScorecardCollection(Map<String, List<ScorecardRow>> collections) {
        this.collections = collections;
    }
    
    public Map<String, List<ScorecardRow>> getCollections() {
        return collections;
    }

    public void setCollections(Map<String, List<ScorecardRow>> collections) {
        this.collections = collections;
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
