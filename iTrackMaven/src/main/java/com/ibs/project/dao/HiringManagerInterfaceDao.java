package com.ibs.project.dao;

import java.util.List;

import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RequestStatusEntity;

public interface HiringManagerInterfaceDao {

	public int save(RaiseRequestEntity raiseRequestEntity,int costCenterId);
	public boolean checkRm(int costCenterId);
	public List<RaiseRequestEntity> viewRequests(String employeeId);
	public List<RequestStatusEntity> viewStatus(String employeeId);
}
