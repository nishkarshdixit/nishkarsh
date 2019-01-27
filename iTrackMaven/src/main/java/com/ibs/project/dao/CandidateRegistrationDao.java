package com.ibs.project.dao;
import java.util.List;


import com.ibs.project.entity.CandidateRegistrationEntity;

public interface CandidateRegistrationDao {
	public int save(CandidateRegistrationEntity candidateRegistrationEntity);
	public List<CandidateRegistrationEntity> candDaolist();
	
}
