package com.lenovo.rms.scorecard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.model.MemberScoreCard;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;
import com.lenovo.rms.scorecard.dao.IEvaluateTypeDao;
import com.lenovo.rms.scorecard.dao.IScorecardDao;
import com.lenovo.rms.scorecard.model.ScorecardCollection;
import com.lenovo.rms.scorecard.model.ScorecardRow;
import com.lenovo.rms.scorecard.service.IScorecardService;

@Service("scorecardService")
public class ScorecardServiceImpl implements IScorecardService {

    protected static Logger logger = Logger.getLogger(ScorecardServiceImpl.class);

    @Autowired
    protected IScorecardDao scorecardDao;

    @Autowired
    protected IProjectDao projectDao;

    @Autowired
    protected IEmployeeDao employeeDao;

    @Autowired
    protected IEvaluateTypeDao evaluateTypeDao;

    @Override
    public void saveScorecard(String projectNo, ScorecardRow scorecardRow) {
        List<MemberScoreCard> scorecards = new ArrayList<>();
        for (int i = 0; i < scorecardRow.getEval_types().size(); i++) {
            MemberScoreCard card = new MemberScoreCard();
            card.setItCode(scorecardRow.getItCode());
            card.setProjectNo(projectNo);
            card.setEvalType(scorecardRow.getEval_types().get(i).toString());
            card.setEvalLevel(scorecardRow.getEval_levels().get(i));
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

    /**
     * 
     * 返回前端所需信息
     * 
     * @date 2015年4月21日 下午1:47:12
     * @author zhanglei
     * @param itCode
     * @return List<Project>
     */
    @Override
    public ScorecardCollection getScorecardCollection(String itCode) {
        List<String> types = evaluateTypeDao.getAllTypes();
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < types.size(); i++) {
            index.put(types.get(i), i);
        }

        ScorecardCollection scorecardCollection = new ScorecardCollection();
        List<String> projects = projectDao.getByItLeader(itCode);
        for (String projectNo : projects) {
            Project p = new Project();
            p.setProjectNo(projectNo);
            List<MemberScoreCard> scorecards = scorecardDao.findScorecards(p);
            List<ScorecardRow> rows = new ArrayList<>();

            if (scorecards.size() == 0) {
                List<String> members = projectDao.getProjectMembers(projectNo);

                for (String code : members) {
                    ScorecardRow row = new ScorecardRow();
                    row.setItCode(code);
                    row.setNameEn(employeeDao.getByItCode(code).getNameEn());
                    row.setEval_types(types);
                    rows.add(row);
                }
            } else {
                Map<String, ScorecardRow> map = new HashMap<>();
                for (MemberScoreCard card : scorecards) {
                    String code = card.getItCode();
                    if (map.containsKey(code)) {
                        map.get(code).getEval_levels().set(index.get(card.getEvalType()), card.getEvalLevel());
                    } else {
                        ScorecardRow sr = new ScorecardRow();
                        sr.setItCode(code);
                        sr.setNameEn(employeeDao.getByItCode(itCode).getNameEn());
                        sr.setEval_types(types);
                        sr.getEval_levels().set(index.get(card.getEvalType()), card.getEvalLevel());
                        map.put(code, sr);
                    }
                }

                for (String key : map.keySet()) {
                    rows.add(map.get(key));
                }

            }
            scorecardCollection.getCollections().put(projectNo, rows);
        }

        return scorecardCollection;
    }
}
