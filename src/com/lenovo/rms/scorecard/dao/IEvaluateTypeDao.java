package com.lenovo.rms.scorecard.dao;

import java.util.List;

import com.lenovo.rms.model.EvaluateType;

public interface IEvaluateTypeDao {

    List<String> getAllTypeNames();
    
    List<String> getAllTypes();

    List<EvaluateType> getAll();

}
