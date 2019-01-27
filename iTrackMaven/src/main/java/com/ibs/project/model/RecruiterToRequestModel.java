package com.ibs.project.model;

import org.springframework.stereotype.Component;

import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;

@Component("recruiterToRequest")
public class RecruiterToRequestModel {

	private int rtrId;
	private String status;
	private EmployeeEntity recruiterId;
	private RaiseRequestEntity requestId;
	private String srecruiterId;
	private int irequestId;
	public int getRtrId() {
		return rtrId;
	}
	public void setRtrId(int rtrId) {
		this.rtrId = rtrId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EmployeeEntity getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(EmployeeEntity recruiterId) {
		this.recruiterId = recruiterId;
	}
	public RaiseRequestEntity getRequestId() {
		return requestId;
	}
	public void setRequestId(RaiseRequestEntity requestId) {
		this.requestId = requestId;
	}
	public String getSrecruiterId() {
		return srecruiterId;
	}
	public void setSrecruiterId(String srecruiterId) {
		this.srecruiterId = srecruiterId;
	}
	public int getIrequestId() {
		return irequestId;
	}
	public void setIrequestId(int irequestId) {
		this.irequestId = irequestId;
	}
	
	
	
}
