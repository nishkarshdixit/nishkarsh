package com.ibs.project.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibs.project.dao.RmBfmInterfaceDao;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RequestStatusEntity;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;


@Service("rmBfmService")
public class RmBfmServiceImpl implements RmBfmServiceInterface {

	@Autowired
	RequestStatusModel requestStatusModel;
	
	@Autowired
	RmBfmInterfaceDao rmBfmInterfaceDao;
	
	@Override
	@Transactional
	public void rmStatus(RequestStatusModel rsRmModel) {					//Passing desired status and ids to dao
		RequestStatusEntity	putRmStatus	= new RequestStatusEntity();									
		putRmStatus.setRmStatus(rsRmModel.getStatus());
		putRmStatus.setRemark(rsRmModel.getRemark());
		int requestId=rsRmModel.getRequestId();
		rmBfmInterfaceDao.saveRmStatus(putRmStatus,requestId);
	}

	@Override
	@Transactional
	public void bfmStatus(RequestStatusModel rsBfmModel) {
		
		RequestStatusEntity	putBfmStatus = new RequestStatusEntity();									
		putBfmStatus.setBfmStatus(rsBfmModel.getStatus());
		putBfmStatus.setRemark(rsBfmModel.getRemark());
		int requestId=rsBfmModel.getRequestId();
		System.out.println("Inside service ID" + requestId);
		rmBfmInterfaceDao.saveBfmStatus(putBfmStatus,requestId);
	}


	@Override
	@Transactional
	public List<RaiseRequestModel> retRequestListRm(String Id) {					//Converting requests entity to model
		List<RaiseRequestModel> listRRModel=new ArrayList<RaiseRequestModel>();
		System.out.println("RmId for service is "+Id);
		for(RaiseRequestEntity ent:rmBfmInterfaceDao.getPendingRequestRM(Id))
		{
			RaiseRequestModel model=new RaiseRequestModel();
			model.setEmployeeName(ent.getHiringManagerId().getEmployeeName());
			model.setHmId(ent.getHiringManagerId().getEmployeeId());
			model.setIssueDate(ent.getIssueDate());
			model.setDeadline(ent.getDeadline());
			model.setProjectName(ent.getProjectName());
			model.setResourceCount(ent.getResourceCount());
			model.setRequestId(ent.getRequestId());
			model.setJobDescription(ent.getJobDescription());
			model.setCostCenterName(ent.getCostCenterId().getCostCenter());
			
			listRRModel.add(model);
		}
		
		return listRRModel;

	}
	
	@Override
	@Transactional
	public List<RaiseRequestModel> retRequestListBfm(String Id) {						//Converting BFM rquests from entity to model
		
		List<RaiseRequestModel> listRRModel = new ArrayList<RaiseRequestModel>();
		System.out.println("BfmId for service is "+Id);
		for(RaiseRequestEntity ent:rmBfmInterfaceDao.getPendingRequestBFM(Id))
		{
			RaiseRequestModel model=new RaiseRequestModel();
			model.setEmployeeName(ent.getHiringManagerId().getEmployeeName());
			model.setHmId(ent.getHiringManagerId().getEmployeeId());
			model.setIssueDate(ent.getIssueDate());
			model.setDeadline(ent.getDeadline());
			model.setProjectName(ent.getProjectName());
			model.setResourceCount(ent.getResourceCount());
			model.setRequestId(ent.getRequestId());
			model.setJobDescription(ent.getJobDescription());
			model.setCostCenterName(ent.getCostCenterId().getCostCenter());
			listRRModel.add(model);
			
		}
		
		return listRRModel;
	}

}
