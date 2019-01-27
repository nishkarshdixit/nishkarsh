package com.ibs.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibs.project.dao.RCMDaoInterface;
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RecruiterToRequest;
import com.ibs.project.entity.RequestStatusEntity;
import com.ibs.project.model.EmployeeModel;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RecruiterToRequestModel;
import com.ibs.project.model.RequestStatusModel;


@Service("rcmService")
public class RCMServiceImpl implements RCMServiceInterface {

	@Autowired
	RaiseRequestModel requestModel;
	
	@Autowired
	RCMDaoInterface rcmInterfaceDao;
	
	@Autowired
	EmployeeModel employeeModel;
	
	@Override
	@Transactional
	public List<RaiseRequestModel> retRequestList() {															//converting request list from entity to model type
		
		List<RaiseRequestModel> modelList=new ArrayList<RaiseRequestModel>();
		
		for(RaiseRequestEntity entity:rcmInterfaceDao.getRequestsRCM())
		{
			RaiseRequestModel requestModel=new RaiseRequestModel();
			
			requestModel.setDeadline(entity.getDeadline());
			requestModel.setIssueDate(entity.getIssueDate());
			requestModel.setPrimarySkill(entity.getPrimarySkill());
			requestModel.setSecondarySkill(entity.getSecondarySkill());
			requestModel.setProjectName(entity.getProjectName());
			requestModel.setResourceCount(entity.getResourceCount());
			requestModel.setRequestId(entity.getRequestId());
			requestModel.setJobDescription(entity.getJobDescription());
			requestModel.setCostCenterName(entity.getCostCenterId().getCostCenter());
			requestModel.setEmployeeName(entity.getHiringManagerId().getEmployeeName());
			modelList.add(requestModel);
		}
		
		return modelList;
	}

	@Override
	@Transactional
	public List<EmployeeModel> retRecruiterList() {																//fetching recruiter list
		
		List<EmployeeModel> recruitList = new ArrayList<EmployeeModel>();
		
		for(EmployeeEntity emp:rcmInterfaceDao.getRecruiters())
		{
			EmployeeModel employeeModel = new EmployeeModel();
			
			employeeModel.setEmployeeId(emp.getEmployeeId());
			employeeModel.setEmployeeName(emp.getEmployeeName());
			recruitList.add(employeeModel);
		}
		
		return recruitList;
		
		
	}

	@Override
	@Transactional
	public int saveRecruiter(RecruiterToRequestModel recruiterToRequestModel) {									//setting recruiter details in database
		
		RecruiterToRequest recruit = new RecruiterToRequest();
		RaiseRequestEntity rEntity=new RaiseRequestEntity();
		rEntity.setRequestId(recruiterToRequestModel.getIrequestId());
		EmployeeEntity emp=new EmployeeEntity();
		emp.setEmployeeId(recruiterToRequestModel.getSrecruiterId());
		
		recruit.setRecruiterId(emp);
		recruit.setRequestId(rEntity);
		
		return rcmInterfaceDao.saveRecruiter(recruit);
	}


}
