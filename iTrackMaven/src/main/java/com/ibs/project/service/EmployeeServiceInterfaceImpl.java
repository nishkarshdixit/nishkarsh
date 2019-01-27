package com.ibs.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibs.project.dao.EmployeeInterfaceDao;
import com.ibs.project.dao.EmployeeInterfaceImplDao;
import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.model.CostCenterModel;
import com.ibs.project.model.EmployeeModel;

@Service("employeeDescriptorService")
public class EmployeeServiceInterfaceImpl implements EmployeeServiceInterface {
	
	@Autowired
	EmployeeModel employeeModel;
	
	@Autowired
	CostCenterModel costCenterModel;
	
	@Autowired
	EmployeeInterfaceDao employeeInterfaceDao;
	

	@Override
	@Transactional
	public String saveEmployee(EmployeeModel employeeModel) {
		
		EmployeeEntity employeeEntity=new EmployeeEntity();								//Converting model list to entity list
		
		employeeEntity.setEmployeeId(employeeModel.getEmployeeId());
		employeeEntity.setEmployeeName(employeeModel.getEmployeeName());
		employeeEntity.setAddress(employeeModel.getAddress());
		employeeEntity.setDob(employeeModel.getDob());
		employeeEntity.setDoj(employeeModel.getDoj());
		employeeEntity.setDesignation(employeeModel.getDesignation());
		employeeEntity.setLocation(employeeModel.getLocation());
		employeeEntity.setPhone(employeeModel.getPhone());
		employeeEntity.setEmail(employeeModel.getEmail());
		employeeEntity.setPassword(employeeModel.getPassword());
		return employeeInterfaceDao.saveEmployee(employeeEntity);
	}


	@Override
	@Transactional
	public List<EmployeeModel> emplist() {
		
		List<EmployeeModel> empModelList=new ArrayList<EmployeeModel>();
		
		for(com.ibs.project.entity.EmployeeEntity employeeEntity:employeeInterfaceDao.viewEmployees()) 		//Converting entity list to model list
		{
			EmployeeModel employeeModel=new EmployeeModel();
			employeeModel.setEmployeeId(employeeEntity.getEmployeeId());
			employeeModel.setEmployeeName(employeeEntity.getEmployeeName());
			employeeModel.setAddress(employeeEntity.getAddress());
			employeeModel.setDob(employeeEntity.getDob());
			employeeModel.setDoj(employeeEntity.getDoj());
			employeeModel.setDesignation(employeeEntity.getDesignation());
			employeeModel.setLocation(employeeEntity.getPhone());
			employeeModel.setEmail(employeeEntity.getEmail());
			employeeModel.setPassword(employeeEntity.getPassword());
			employeeModel.setPhone(employeeEntity.getPhone());
			empModelList.add(employeeModel);
		}
		return empModelList;
	}
	
	@Override
	@Transactional
	public EmployeeModel authenticateEmployee(EmployeeModel employeeModelRec)
	{
		EmployeeEntity employeeEntity=new EmployeeEntity();
		
		employeeEntity.setEmployeeId(employeeModelRec.getEmployeeId());						//Receiving data from controller
		employeeEntity.setEmployeeName(employeeModelRec.getEmployeeName());
		employeeEntity.setAddress(employeeModelRec.getAddress());
		employeeEntity.setDob(employeeModelRec.getDob());
		employeeEntity.setDoj(employeeModelRec.getDoj());
		employeeEntity.setDesignation(employeeModelRec.getDesignation());
		employeeEntity.setLocation(employeeModelRec.getLocation());
		employeeEntity.setPhone(employeeModelRec.getPhone());
		employeeEntity.setEmail(employeeModelRec.getEmail());
		employeeEntity.setPassword(employeeModelRec.getPassword());
		
		EmployeeEntity retEntity=employeeInterfaceDao.authenticateEmployee(employeeEntity);
		
										//Returning data to controller
		
		employeeModel.setEmployeeId(retEntity.getEmployeeId());
		employeeModel.setEmployeeName(retEntity.getEmployeeName());
		employeeModel.setAddress(retEntity.getAddress());
		employeeModel.setDob(retEntity.getDob());
		employeeModel.setDoj(retEntity.getDoj());
		employeeModel.setDesignation(retEntity.getDesignation());
		employeeModel.setLocation(retEntity.getPhone());
		employeeModel.setEmail(retEntity.getEmail());
		employeeModel.setPassword(retEntity.getPassword());
		employeeModel.setPhone(retEntity.getPhone());
		
		
		
		return employeeModel;
		
	}
	
	/*CostCenterServiceImpl*/
	@Override
	@Transactional
	public int saveCostCenter(CostCenterModel ccModel) {													//Converting model list to entity list
		CostCenterEntity costCenterEntity=new CostCenterEntity();
		costCenterEntity.setCostCenter(ccModel.getCostCenter());
		costCenterEntity.setProjectId(ccModel.getProjectId());
		costCenterEntity.setHmId(ccModel.getHmId());
		costCenterEntity.setRmId(ccModel.getRmId());
		costCenterEntity.setBfmId(ccModel.getBfmId());
		return employeeInterfaceDao.saveCostCenter(costCenterEntity);
	}


	@Override
	public List<CostCenterModel> cclist() {																	//Fetching cost center details
		
		List<CostCenterModel> modelList=new ArrayList<CostCenterModel>();
		
		for(CostCenterEntity entity:employeeInterfaceDao.costCenterDaolist())
		{
			CostCenterModel ccModel=new CostCenterModel();
			//EmployeeEntity emp=entity.getBfmId();
			System.out.println("Ent data "+entity.getCostCenterId());
			ccModel.setCostCenter(entity.getCostCenter());
			ccModel.setCcId(entity.getCostCenterId());
			modelList.add(ccModel);
		}
		
		System.out.println("Inside Employee Service");
		for(CostCenterModel m:modelList)
		{
			System.out.println(m.getCcId());															//getting cost center Id
		}
		
		return modelList;
	}


	@Override
	public List<EmployeeModel> viewEmployees() {														//Viewing All Employees
		
		List<EmployeeModel> empList = new ArrayList<EmployeeModel>();
		
		for(EmployeeEntity ent:employeeInterfaceDao.viewEmployees())
		{
			EmployeeModel employeeModel = new EmployeeModel();										//Converting entity list to model list							
																										
			employeeModel.setEmployeeId(ent.getEmployeeId());																	
			employeeModel.setEmployeeName(ent.getEmployeeName());
			employeeModel.setDob(ent.getDob());
			employeeModel.setAddress(ent.getAddress());
			employeeModel.setPhone(ent.getPhone());
			employeeModel.setEmail(ent.getEmail());
			employeeModel.setPassword(ent.getPassword());
			employeeModel.setLocation(ent.getLocation());
			employeeModel.setDoj(ent.getDoj());
			employeeModel.setDesignation(ent.getDesignation());
			
			empList.add(employeeModel);
		}
		
		return empList;
	}

}
