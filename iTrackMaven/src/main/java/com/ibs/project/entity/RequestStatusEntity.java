package com.ibs.project.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="G2REQUESTSTATUS")
public class RequestStatusEntity {
	private int requestStatusId;
	private String rmStatus,bfmStatus,requestStatus,remark;
	private RaiseRequestEntity requestId;
	private CostCenterEntity reqCostCenterId;
	
	@Id
	@Column(name="REQUESTSTATUSID",length=10)
	public int getRequestStatusId() {
		return requestStatusId;
	}
	public void setRequestStatusId(int requestStatusId) {
		this.requestStatusId = requestStatusId;
	}
	
	
	@OneToOne
	@JoinColumn(name="REQUESTID")
	public RaiseRequestEntity getRequestId() {
		return requestId;
	}
	public void setRequestId(RaiseRequestEntity requestId) {
		this.requestId = requestId;
	}
	@Column(name="REPORTINGMANAGERMSTATUS",length=20)
	public String getRmStatus() {
		return rmStatus;
	}
	public void setRmStatus(String rmStatus) {
		this.rmStatus = rmStatus;
	}
	
	@Column(name="BUSINESSFINANCESTATUS",length=20)
	public String getBfmStatus() {
		return bfmStatus;
	}
	public void setBfmStatus(String bfmStatus) {
		this.bfmStatus = bfmStatus;
	}
	
	@Column(name="REQUESTSTATUS",length=20)
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	@Column(name="REMARK",length=20)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	@ManyToOne
	@JoinColumn(name="COSTCENTERID")
	public CostCenterEntity getReqCostCenterId() {
		return reqCostCenterId;
	}
	public void setReqCostCenterId(CostCenterEntity reqCostCenterId) {
		this.reqCostCenterId = reqCostCenterId;
	}
	
	

}
