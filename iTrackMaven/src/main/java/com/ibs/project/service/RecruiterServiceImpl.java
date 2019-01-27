package com.ibs.project.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibs.project.dao.RecruiterDaoInterface;
import com.ibs.project.entity.CandidateRegistrationEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.ShortlistCandidateEntity;
import com.ibs.project.model.CandidateRegistrationModel;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.ShortlistCandidateModel;


@Service("recruiterService")
public class RecruiterServiceImpl implements RecruiterServiceInterface {

	@Autowired
	RaiseRequestModel raiseRequestModel;
	
	@Autowired
	RecruiterDaoInterface recruiterDaoInterface;
	
	
	@Override
	@Transactional
	public List<RaiseRequestModel> getRequests(String id) {												//Converting request entity to model
		
		List<RaiseRequestModel> modelList = new ArrayList<RaiseRequestModel>();
		
		for(RaiseRequestEntity entity:recruiterDaoInterface.getRequests(id))
				{
					RaiseRequestModel requestModel = new RaiseRequestModel();
					
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
	public List<String> saveShortlistCandidates(List<ShortlistCandidateModel> modelList) {				//Converting model to rntity
		
		List<ShortlistCandidateEntity> entList=new ArrayList<ShortlistCandidateEntity>();
		for(ShortlistCandidateModel s:modelList)
		{
			ShortlistCandidateEntity ent=new ShortlistCandidateEntity();
			
			CandidateRegistrationEntity cand=new CandidateRegistrationEntity();
			cand.setCandidateId(s.getCandidateId());
			ent.setCandidateId(cand);
			
			RaiseRequestEntity rs=new RaiseRequestEntity();
			rs.setRequestId(s.getRequestId());
			ent.setRequestId(rs);
			
			ent.setDate(s.getDate());
			
			entList.add(ent);
		}
		
		return recruiterDaoInterface.saveShortlistCandidates(entList);
	}


	@Override
	@Transactional
	public List<CandidateRegistrationModel> getCandidateList(int requestId) {							//Converting list of entity to model
		List<CandidateRegistrationModel> modelList=new ArrayList<CandidateRegistrationModel>();
		
		for(CandidateRegistrationEntity ent:recruiterDaoInterface.getShortlistCandidates(requestId))
		{
			CandidateRegistrationModel model=new CandidateRegistrationModel();
			model.setCandidateId(ent.getCandidateId());
			model.setStatus(ent.getStatus());
			model.setFirstName(ent.getFirstName());
			modelList.add(model);
		}
		
		return modelList;
	}


	@Override
	@Transactional
	public void saveUpdate(int status, int candidateId,String remark) {
		
		recruiterDaoInterface.saveUpdate(status,candidateId,remark);
		
	}


	@Override
	@Transactional
	public List<CandidateRegistrationModel> viewSelectedCandidates() {		//Selected candidates entity list to model list
	
		List<CandidateRegistrationModel> modelList = new ArrayList<CandidateRegistrationModel>();
		
		for(CandidateRegistrationEntity entity:recruiterDaoInterface.viewSelectedCandidates())
		{
			CandidateRegistrationModel candModel = new CandidateRegistrationModel();
			
			candModel.setCandidateId(entity.getCandidateId());
			candModel.setTitle(entity.getTitle());
			candModel.setFirstName(entity.getFirstName());
			candModel.setMiddleName(entity.getMiddleName());
			candModel.setLastName(entity.getLastName());
			candModel.setDob(entity.getDob());
			candModel.setPhoneNumber(entity.getPhoneNumber());
			candModel.setCurrentLocation(entity.getCurrentLocation());
			candModel.setPreferredLocation(entity.getPreferredLocation());
			candModel.setEmail(entity.getEmail());
			candModel.setTotalExperience(entity.getTotalExperience());
			candModel.setRelevantExperience(entity.getRelevantExperience());
			candModel.setCv(entity.getCv());
			
			modelList.add(candModel);
		}
		
			return modelList;
	}


	@Override
	@Transactional
	public void resolveRequest(int requestId) {				//Passing requestId to dao to be resolved
		
		recruiterDaoInterface.resolveRequest(requestId);
		
	}
		
		
		
		
}
