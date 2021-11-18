package com.emp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.bean.EmployeeDto;
import com.emp.dao.EmployeeDao;
import com.emp.entity.EmployeeEntity;

@Service
@Transactional
public class EmployeeServiceImpl implements com.emp.service.EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public int save(EmployeeDto dto) {
		EmployeeEntity entity = new EmployeeEntity();
		//entity.setId(dto.getId());
		entity.setSalary(dto.getSalary());
		entity.setName(dto.getName());
		entity.setDesignation(dto.getDesignation());
		return employeeDao.save(entity);
	}

	@Override
	public List<EmployeeDto> getEmployees() {
		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		List<EmployeeEntity> empList = employeeDao.getEmployees();

		for (EmployeeEntity employeeEntity : empList) {
			EmployeeDto dto = new EmployeeDto();
			dto.setId(employeeEntity.getId());
			dto.setSalary(employeeEntity.getSalary());
			dto.setName(employeeEntity.getName());
			dto.setDesignation(employeeEntity.getDesignation());
			list.add(dto);

		}

		return list;
	}

	@Override
	public EmployeeDto getEmpById(int id) {
		EmployeeEntity employeeEntity = employeeDao.getEmpById(id);
		EmployeeDto dto = new EmployeeDto();
		dto.setId(employeeEntity.getId());
		dto.setSalary(employeeEntity.getSalary());
		dto.setName(employeeEntity.getName());
		dto.setDesignation(employeeEntity.getDesignation());
		return dto;
	}

	@Override
	public int delete(int id) {

		return employeeDao.delete(id);
	}

	@Override
	public int update(EmployeeDto dto) {

		EmployeeEntity entity = new EmployeeEntity();
		entity.setId(dto.getId());
		entity.setSalary(dto.getSalary());
		entity.setName(dto.getName());
		entity.setDesignation(dto.getDesignation());

		return employeeDao.update(entity);
	}

}
