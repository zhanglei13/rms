package com.lenovo.rms.employee.dao;

import com.lenovo.rms.model.Organization;

public interface IOrgnizationDao {
	Organization getByItCode(String itcode);
}
