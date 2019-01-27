package com.ibs.project.service;

import java.util.List;

import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;

public interface RmBfmServiceInterface {
		
	public void rmStatus(RequestStatusModel rsRmModel);
	public void bfmStatus(RequestStatusModel rsBfmModel);
	
	public List<RaiseRequestModel> retRequestListRm(String Id);
	public List<RaiseRequestModel> retRequestListBfm(String Id);
}	

