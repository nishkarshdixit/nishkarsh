package com.ibs.project.service;

import java.util.List;

import com.ibs.project.model.EmployeeModel;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RecruiterToRequestModel;
import com.ibs.project.model.RequestStatusModel;

public interface RCMServiceInterface {

	public List<RaiseRequestModel> retRequestList();
	public List<EmployeeModel> retRecruiterList();
	public int saveRecruiter(RecruiterToRequestModel recruiterToRequestModel);
}
