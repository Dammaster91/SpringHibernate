package com.emp.service;

import java.util.List;

import com.emp.bean.EmployeeDto;

public interface EmployeeService {

	public int save(EmployeeDto p);

	public List<EmployeeDto> getEmployees();

	public EmployeeDto getEmpById(int id);

	public int delete(int id);

	public int update(EmployeeDto p);

}
