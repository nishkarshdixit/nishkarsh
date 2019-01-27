package com.ibs.project.service;

import java.util.List;

import com.ibs.project.model.CandidateRegistrationModel;

public interface CandidateRegistrationService {
	public int saveCandidate(CandidateRegistrationModel candidateRegistrationModel);
	public List<CandidateRegistrationModel> candidateList();
	
}
