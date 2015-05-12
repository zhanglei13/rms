package com.lenovo.rms.workload.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.common.util.DateUtils;
import com.lenovo.rms.model.Holiday;
import com.lenovo.rms.workload.dao.IHolidayDao;

@Repository("holidayDao")
public class HolidayDaoImpl extends HibernateBaseDaoImpl<Holiday, Long> implements IHolidayDao {
    @Override
    public List<Holiday> findAllByYear(String year) {
        String hql = "from Holiday w where w.year=:year";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("year", year);
        List<Holiday> hols = findHql(hql, params);
        return hols;
    }

    @Override
    public Set<String> listHolidaysByYear(String year) {
        List<Holiday> hols = findAllByYear(year);
        Set<String> results = new HashSet<>();
        for (Holiday holiday : hols)
            results.add(DateUtils.formatDate(holiday.getDate()));
        return results;
    }
}
