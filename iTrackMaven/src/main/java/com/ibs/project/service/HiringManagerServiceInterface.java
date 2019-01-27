package com.ibs.project.service;

import java.util.List;

import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;

public interface HiringManagerServiceInterface {

	public int saveRequest(RaiseRequestModel raiseRequestModel);
	
	public List<RaiseRequestModel> viewRequests(String employeeId);
	
	public List<RequestStatusModel> viewStatus(String employeeId);
}
