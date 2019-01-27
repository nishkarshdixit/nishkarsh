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
@Table(name="G2RECRUITERTOREQUEST")
public class RecruiterToRequest {
	
	private int rtrId;
	private String status;
	private EmployeeEntity recruiterId;
	private RaiseRequestEntity requestId;
	
	@Id
	@Column(name="RTRID",length=10)
	public int getRtrId() {
		return rtrId;
	}
	public void setRtrId(int rtrId) {
		this.rtrId = rtrId;
	}
	
	@Column(name="STATUS",length=5)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne
    @JoinColumn(name="EMPLOYEEID")
	public EmployeeEntity getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(EmployeeEntity recruiterId) {
		this.recruiterId = recruiterId;
	}
	
	@OneToOne
	@JoinColumn(name="REQUESTID")
	public RaiseRequestEntity getRequestId() {
		return requestId;
	}
	public void setRequestId(RaiseRequestEntity requestId) {
		this.requestId = requestId;
	}
	
	
	
}
