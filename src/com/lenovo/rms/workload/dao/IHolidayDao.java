package com.lenovo.rms.workload.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lenovo.rms.model.Holiday;

public interface IHolidayDao {

    List<Holiday> findAllByYear(String year);

    Set<String> listHolidaysByYear(String year);

}
