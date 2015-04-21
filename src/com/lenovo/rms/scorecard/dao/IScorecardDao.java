package com.lenovo.rms.scorecard.dao;

import java.util.Collection;
import java.util.List;

import com.lenovo.rms.model.MemberScoreCard;
import com.lenovo.rms.model.Project;

public interface IScorecardDao {

    void saveScorecard(MemberScoreCard scorecard);

    void saveScorecards(Collection<MemberScoreCard> scorecards);

    List<MemberScoreCard> findScorecards(Project project);

}
