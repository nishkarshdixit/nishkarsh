package com.ibs.project.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="G2COSTCENTER")
public class CostCenterEntity {

	private String costCenter;
	private int costCenterId;
	private String projectId;
	private String hmId;
	private String rmId;
	private String bfmId;
	private List<RaiseRequestEntity> raiseList;
	private List<RequestStatusEntity> requestList;
	
	
	
	@Id
	@Column(name="COSTCENTERID",length=10)
	public int getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(int costCenterId) {
		this.costCenterId = costCenterId;
	}
	
	@Column(name="COSTCENTER",length=35)
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	@Column(name="PROJECTID",length=10)
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Column(name="HIRINGMANAGERID",length=10)
	public String getHmId() {
		return hmId;
	}
	public void setHmId(String hmId) {
		this.hmId = hmId;
	}
	@Column(name="REPORTINGMANAGERID",length=10)
	public String getRmId() {
		return rmId;
	}
	public void setRmId(String rmId) {
		this.rmId = rmId;
	}
	@Column(name="BUSINESSFINANCEMANAGERID",length=10)
	public String getBfmId() {
		return bfmId;
	}
	public void setBfmId(String bfmId) {
		this.bfmId = bfmId;
	}
	
	@OneToMany(mappedBy = "costCenterId")
	public List<RaiseRequestEntity> getRaiseList() {
		return raiseList;
	}
	public void setRaiseList(List<RaiseRequestEntity> raiseList) {
		this.raiseList = raiseList;
	}
	
	
	@OneToMany(mappedBy = "reqCostCenterId")
	public List<RequestStatusEntity> getRequestList() {
		return requestList;
	}
	public void setRequestList(List<RequestStatusEntity> requestList) {
		this.requestList = requestList;
	}
	
	
	
	

}
