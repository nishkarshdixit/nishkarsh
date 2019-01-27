package com.ibs.project.dao;

import java.util.List;

import com.ibs.project.entity.CandidateRegistrationEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.ShortlistCandidateEntity;

public interface RecruiterDaoInterface {

	public List<RaiseRequestEntity> getRequests(String id);

	public List<CandidateRegistrationEntity> getShortlistCandidates(int requestId);

	public List<String> saveShortlistCandidates(List<ShortlistCandidateEntity> entList);
	
	public void saveUpdate(int status,int candidateId,String remark);
	
	public List<CandidateRegistrationEntity> viewSelectedCandidates();
	
	public void resolveRequest(int requestId);

}
