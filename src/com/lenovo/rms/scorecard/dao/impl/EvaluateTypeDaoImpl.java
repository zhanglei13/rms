package com.lenovo.rms.scorecard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.EvaluateType;
import com.lenovo.rms.scorecard.dao.IEvaluateTypeDao;

@Repository("evaluateTypeDao")
public class EvaluateTypeDaoImpl extends HibernateBaseDaoImpl<EvaluateType, Long> implements IEvaluateTypeDao {

    @Override
    public List<EvaluateType> getAll() {
        return findAll();
    }

    @Override
    public List<String> getAllTypeNames() {
        List<String> typeNames = new ArrayList<>();
        List<EvaluateType> types = getAll();
        for (EvaluateType type : types) {
            typeNames.add(type.getEvalTypeName());
        }
        return typeNames;
    }

    @Override
    public List<String> getAllTypes() {
        List<String> types = new ArrayList<>();
        List<EvaluateType> ts = getAll();
        for (EvaluateType type : ts) {
            types.add(type.getEvalType());
        }
        return types;
    }
}
