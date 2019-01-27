package com.ibs.project.dao;

import java.util.List;

import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RecruiterToRequest;

public interface RCMDaoInterface {

	public List<RaiseRequestEntity> getRequestsRCM();
	
	public List<EmployeeEntity> getRecruiters();
	
	public int saveRecruiter(RecruiterToRequest recruiterToRequestEntity);

}
