package com.emp.dao;

import java.util.List;

import com.emp.entity.EmployeeEntity;

public interface EmployeeDao {

	public int save(EmployeeEntity p);

	public List<EmployeeEntity> getEmployees();

	public EmployeeEntity getEmpById(int id);

	public int delete(int id);

	public int update(EmployeeEntity p);

}
