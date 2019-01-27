package com.ibs.project.service;

import java.util.List;

import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.model.CostCenterModel;
import com.ibs.project.model.EmployeeModel;

public interface EmployeeServiceInterface {
 public String saveEmployee(EmployeeModel employeeModel);
 public List<EmployeeModel> emplist();
 public EmployeeModel authenticateEmployee(EmployeeModel employeeModelRec);
 
 /*CostCenterServiceInterface*/
 public int saveCostCenter(CostCenterModel ccModel);
 public List<CostCenterModel> cclist();
 
 public List<EmployeeModel> viewEmployees();
 
}
