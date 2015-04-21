package com.lenovo.rms.scorecard.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.common.util.Constants;
import com.lenovo.rms.common.util.JsonUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.scorecard.model.ScorecardCollection;
import com.lenovo.rms.scorecard.model.ScorecardRow;
import com.lenovo.rms.scorecard.service.IScorecardService;

@Controller
@RequestMapping("/scorecard")
public class ScorecardController {
    protected static Logger logger = Logger.getLogger(ScorecardController.class);

    @Autowired
    private IScorecardService scorecardService;

    @RequestMapping("/list")
    @ResponseBody
    public ScorecardCollection getScorecardCollection(HttpSession session) {
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_USERINFO_KEY);
        return scorecardService.getScorecardCollection(employee.getItCode());
    }

    @RequestMapping("/save")
    @ResponseBody
    public void saveScorecards(@RequestParam("scorecardRows") String scorecardRowsString,
            @RequestParam("projectNo") String projectNo) {
        List<ScorecardRow> scorecardRows = JsonUtils.jsonList2JavaList(scorecardRowsString, ScorecardRow.class);
        scorecardService.saveScorecards(projectNo, scorecardRows);
    }
}
