package com.lenovo.rms.scorecard.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.MemberScoreCard;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.scorecard.dao.IScorecardDao;

@Repository("scorecardDao")
public class ScorecardDaoImpl extends HibernateBaseDaoImpl<MemberScoreCard, Long> implements IScorecardDao {
    @Override
    public void saveScorecard(MemberScoreCard scorecard) {
        save(scorecard);
    }

    @Override
    public void saveScorecards(Collection<MemberScoreCard> scorecards) {
        saveAll(scorecards);
    }

    @Override
    public List<MemberScoreCard> findScorecards(Project project) {
        String hql = "from MemberScoreCard w where w.projectNo=:projectNo";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectNo", project.getProjectNo());
        List<MemberScoreCard> scorecards = findHql(hql, params);
        return scorecards;
    }
}
