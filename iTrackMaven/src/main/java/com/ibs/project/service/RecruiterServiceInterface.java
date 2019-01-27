package com.ibs.project.service;

import java.util.List;

import com.ibs.project.model.CandidateRegistrationModel;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.ShortlistCandidateModel;

public interface RecruiterServiceInterface {

	public List<RaiseRequestModel> getRequests(String id);
	public List<String> saveShortlistCandidates(List<ShortlistCandidateModel> modelList);
	public List<CandidateRegistrationModel> getCandidateList(int requestId);
	public void saveUpdate(int status,int candidateId,String remark);
	public List<CandidateRegistrationModel> viewSelectedCandidates();
	public void resolveRequest(int requestId);
}
