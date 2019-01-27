package com.ibs.project.model;

import org.springframework.stereotype.Component;

@Component("costCenterModel")
public class CostCenterModel {

	private String costCenter;
	private String projectId;
	private String hmId;
	private String rmId;
	private String bfmId;
	private int ccId;
	
	
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getHmId() {
		return hmId;
	}
	public void setHmId(String hmId) {
		this.hmId = hmId;
	}
	public String getRmId() {
		return rmId;
	}
	public void setRmId(String rmId) {
		this.rmId = rmId;
	}
	public String getBfmId() {
		return bfmId;
	}
	public void setBfmId(String bfmId) {
		this.bfmId = bfmId;
	}
	public int getCcId() {
		return ccId;
	}
	public void setCcId(int ccId) {
		this.ccId = ccId;
	}
}
