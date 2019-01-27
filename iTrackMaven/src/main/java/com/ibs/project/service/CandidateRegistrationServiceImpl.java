package com.ibs.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ibs.project.dao.CandidateRegistrationDao;
import com.ibs.project.entity.CandidateRegistrationEntity;
import com.ibs.project.model.CandidateRegistrationModel;

@Service("candidateRegistrationService")
public class CandidateRegistrationServiceImpl implements
		CandidateRegistrationService {
	
	
	
	@Autowired
	CandidateRegistrationDao candidateRegistrationDao;
	
	
	
	@Autowired
	CandidateRegistrationModel candidateRegistrationModel;

	@Override
	@Transactional
	public int saveCandidate(CandidateRegistrationModel candidateRegistrationModel) {						///Converting candidate details to Entity type
		
		CandidateRegistrationEntity candidateRegistrationEntity=new CandidateRegistrationEntity();
		candidateRegistrationEntity.setTitle(candidateRegistrationModel.getTitle());
		candidateRegistrationEntity.setFirstName(candidateRegistrationModel.getFirstName());
		candidateRegistrationEntity.setMiddleName(candidateRegistrationModel.getMiddleName());
		candidateRegistrationEntity.setLastName(candidateRegistrationModel.getLastName());
		candidateRegistrationEntity.setCurrentLocation(candidateRegistrationModel.getCurrentLocation());
		candidateRegistrationEntity.setPreferredLocation(candidateRegistrationModel.getPreferredLocation());
		candidateRegistrationEntity.setEmail(candidateRegistrationModel.getEmail());
		candidateRegistrationEntity.setDob(candidateRegistrationModel.getDob());
		candidateRegistrationEntity.setCv(candidateRegistrationModel.getCv());
		candidateRegistrationEntity.setPhoneNumber(candidateRegistrationModel.getPhoneNumber());
		candidateRegistrationEntity.setTotalExperience(candidateRegistrationModel.getTotalExperience());
		candidateRegistrationEntity.setRelevantExperience(candidateRegistrationModel.getRelevantExperience());
		return candidateRegistrationDao.save(candidateRegistrationEntity);
	
	}

	@Override
	@Transactional
	public List<CandidateRegistrationModel> candidateList() {
		List<CandidateRegistrationModel> candModelList=new ArrayList<CandidateRegistrationModel>();
		List<CandidateRegistrationEntity> candEntityList=candidateRegistrationDao.candDaolist();
		if(candEntityList.isEmpty()){System.out.println("Service empty list from dao");}
		for(CandidateRegistrationEntity candEntity:candEntityList){										//Converting entity list to model list
			CandidateRegistrationModel candidateRegistrationModel=new CandidateRegistrationModel();
			candidateRegistrationModel.setTitle(candEntity.getTitle());
			candidateRegistrationModel.setFirstName(candEntity.getFirstName());
			candidateRegistrationModel.setMiddleName(candEntity.getMiddleName());
			candidateRegistrationModel.setLastName(candEntity.getLastName());
			candidateRegistrationModel.setCurrentLocation(candEntity.getCurrentLocation());
			candidateRegistrationModel.setPreferredLocation(candEntity.getPreferredLocation());
			candidateRegistrationModel.setEmail(candEntity.getEmail());
			candidateRegistrationModel.setDob(candEntity.getDob());
			//candidateRegistrationModel.setCv(candEntity.getCv());
			candidateRegistrationModel.setPhoneNumber(candEntity.getPhoneNumber());
			candidateRegistrationModel.setTotalExperience(candEntity.getTotalExperience());
			candidateRegistrationModel.setRelevantExperience(candEntity.getRelevantExperience());
			candidateRegistrationModel.setCandidateId(candEntity.getCandidateId());
			candidateRegistrationModel.setStatus(candEntity.getStatus());
			candidateRegistrationModel.setCv(candEntity.getCv());
			candModelList.add(candidateRegistrationModel);
		}
		return candModelList;
	}
	
/*	@Override
	@Transactional
	public boolean checkExistingCandidate(String email)
	{
		if(candidateRegistrationDao.checkCandidateMail(email))
		{
			return true;
		}
		
		return false;
	}*/

}
