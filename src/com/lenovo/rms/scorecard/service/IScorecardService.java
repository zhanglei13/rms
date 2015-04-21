package com.lenovo.rms.scorecard.service;

import java.util.List;

import com.lenovo.rms.scorecard.model.ScorecardCollection;
import com.lenovo.rms.scorecard.model.ScorecardRow;

public interface IScorecardService {

    void saveScorecard(String projectNo, ScorecardRow scorecardRow);
    
    void saveScorecards(String projectNo, List<ScorecardRow> scorecardRows);

    ScorecardCollection getScorecardCollection(String itCode);

}
