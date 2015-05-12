package com.lenovo.rms.workload.service;

import java.util.Date;
import java.util.List;

import com.lenovo.rms.workload.model.ITLeaderViewTable;
import com.lenovo.rms.model.Project;

public interface ILeaderApproveService {
	ITLeaderViewTable getITLeaderViewTable(String itCode,Date from, Date to);
}
