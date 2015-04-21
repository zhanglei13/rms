package com.lenovo.rms.scorecard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.model.MemberScoreCard;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;
import com.lenovo.rms.scorecard.dao.IScorecardDao;
import com.lenovo.rms.scorecard.model.ScorecardRow;
import com.lenovo.rms.scorecard.service.IScorecardService;

@Service("scorecardService")
public class ScorecardServiceImpl implements IScorecardService {

    protected static Logger logger = Logger.getLogger(ScorecardServiceImpl.class);

    @Autowired
    protected IScorecardDao scorecardDao;

    @Autowired
    protected IProjectDao projectDao;

    @Override
    public void saveScorecard(String projectNo, ScorecardRow scorecardRow) {
        List<MemberScoreCard> scorecards = new ArrayList<>();
        for (int i = 0; i < scorecardRow.getEval_types().length; i++) {
            MemberScoreCard card = new MemberScoreCard();
            card.setItCode(scorecardRow.getItCode());
            card.setProjectNo(projectNo);
            card.setEvalType(scorecardRow.getEval_types()[i]);
            card.setEvalLevel(scorecardRow.getEval_levels()[i]);
            card.setCreatedDate(new Date());
        }
        scorecardDao.saveScorecards(scorecards);
    }

    @Override
    public void saveScorecards(String projectNo, List<ScorecardRow> scorecardRows) {
        for (ScorecardRow row : scorecardRows) {
            saveScorecard(projectNo, row);
        }
    }
    
    @Override
    public List<Project> getProjectsByItLeader(String itCode) {
        
    }
}
