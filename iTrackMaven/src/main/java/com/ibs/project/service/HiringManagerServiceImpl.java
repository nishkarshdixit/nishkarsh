package com.ibs.project.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibs.project.dao.HiringManagerInterfaceDao;
import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RequestStatusEntity;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;

@Service("raiseRequestService")
public class HiringManagerServiceImpl implements HiringManagerServiceInterface {

	
	@Autowired
	RaiseRequestModel raiseRequestModel;
	
	@Autowired
	HiringManagerInterfaceDao hiringManagerInterfaceDao;
	
	@Override
	@Transactional
	public int saveRequest(RaiseRequestModel raiseRequestModel) {											//converting request model to entity type
	
		RaiseRequestEntity raiseRequestEntity = new RaiseRequestEntity();
		raiseRequestEntity.setExperience(raiseRequestModel.getExperience());
		raiseRequestEntity.setLocation(raiseRequestModel.getLocation());
		raiseRequestEntity.setPrimarySkill(raiseRequestModel.getPrimarySkill());
		raiseRequestEntity.setSecondarySkill(raiseRequestModel.getSecondarySkill());
		raiseRequestEntity.setDeadline(raiseRequestModel.getDeadline());
		raiseRequestEntity.setResourceCount(raiseRequestModel.getResourceCount());
		raiseRequestEntity.setJobDescription(raiseRequestModel.getJobDescription());
		
		CostCenterEntity cst=new CostCenterEntity();													
		cst.setCostCenterId(raiseRequestModel.getCostCenter());
		raiseRequestEntity.setCostCenterId(cst);
		
		EmployeeEntity ent=new EmployeeEntity();
		ent.setEmployeeId(raiseRequestModel.getHmId());
		raiseRequestEntity.setHiringManagerId(ent);
		
		raiseRequestEntity.setProjectName(raiseRequestModel.getProjectName());
		raiseRequestEntity.setIssueDate(raiseRequestModel.getIssueDate());
		return hiringManagerInterfaceDao.save(raiseRequestEntity,raiseRequestModel.getCostCenter());
		
	}

	@Override
	public List<RaiseRequestModel> viewRequests(String employeeId) {										//converting entity to model list
		
		List<RaiseRequestModel> modelList = new ArrayList<RaiseRequestModel>();
		
		for(RaiseRequestEntity entity:hiringManagerInterfaceDao.viewRequests(employeeId))
		{
			RaiseRequestModel requestModel = new RaiseRequestModel();
			
			requestModel.setRequestId(entity.getRequestId());
			
			requestModel.setCostCenterName(entity.getCostCenterId().getCostCenter());
			requestModel.setCostCenter(entity.getCostCenterId().getCostCenterId());
			requestModel.setProjectName(entity.getProjectName());
			requestModel.setIssueDate(entity.getIssueDate());
			requestModel.setPrimarySkill(entity.getPrimarySkill());
			requestModel.setSecondarySkill(entity.getSecondarySkill());
			requestModel.setResourceCount(entity.getResourceCount());
			requestModel.setLocation(entity.getLocation());
			requestModel.setExperience(entity.getExperience());
			requestModel.setDeadline(entity.getDeadline());
			
			modelList.add(requestModel);
			
			
		}
		
		return modelList;
		
		
		
	}

	@Override
	public List<RequestStatusModel> viewStatus(String employeeId) {											//converting request status entity list to model type
		
		List<RequestStatusModel> modelList = new ArrayList<RequestStatusModel>();
		
		for(RequestStatusEntity ent:hiringManagerInterfaceDao.viewStatus(employeeId))
		{
			RequestStatusModel model = new RequestStatusModel();
			
			model.setRequestId(ent.getRequestId().getRequestId());
			model.setRmStatus(ent.getRmStatus());
			model.setBfmStatus(ent.getBfmStatus());
			model.setRequestStatus(ent.getRequestStatus());
			
			modelList.add(model);
		}
		
		
		
		
		return modelList;
	}

}
