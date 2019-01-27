package com.ibs.project.dao;

import java.util.List;

import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RequestStatusEntity;
import com.ibs.project.model.RaiseRequestModel;

public interface RmBfmInterfaceDao {
	
	public List<RaiseRequestEntity> getPendingRequestRM(String Id);
	
	public List<RaiseRequestEntity> getPendingRequestBFM(String Id);
	
	public void saveRmStatus(RequestStatusEntity requestStatus,int requestId);
	public void saveBfmStatus(RequestStatusEntity requestStatus,int requestId);

}
