package com.ibs.project.dao;

import java.util.List;

import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;

public interface EmployeeInterfaceDao {

	public String saveEmployee(EmployeeEntity employeeEntity);
	
	public EmployeeEntity authenticateEmployee(EmployeeEntity employee);
	
	public int saveCostCenter(CostCenterEntity ccEntity);
	public List<CostCenterEntity> costCenterDaolist();
	
	public List<EmployeeEntity> viewEmployees();
}
