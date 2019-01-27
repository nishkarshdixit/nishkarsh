package com.ibs.project.model;

import org.springframework.stereotype.Component;

@Component("requestStatusModel")
public class RequestStatusModel {
	
	private String status,requestStatus,remark;
	private String id,designation;
	private String rmStatus,bfmStatus;
	private int requestId;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getRmStatus() {
		return rmStatus;
	}
	public void setRmStatus(String rmStatus) {
		this.rmStatus = rmStatus;
	}
	public String getBfmStatus() {
		return bfmStatus;
	}
	public void setBfmStatus(String bfmStatus) {
		this.bfmStatus = bfmStatus;
	}
	
	
	

}
