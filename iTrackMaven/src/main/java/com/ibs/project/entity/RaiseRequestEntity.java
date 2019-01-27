package com.ibs.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="G2RAISEREQUEST")
public class RaiseRequestEntity {

	private int requestId;
	private String projectName,primarySkill,secondarySkill,location,jobDescription;
	private int resourceCount,experience;
	private Date deadline,issueDate;
	private EmployeeEntity hiringManagerId;
	private CostCenterEntity costCenterId;
	List<ShortlistCandidateEntity> list;
	
	

	@Id
	@Column(name="REQUESTID", length = 10)
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	@Column(name="PROJECTNAME",  length = 40)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name="PRIMARYSKILL", length = 30)
	public String getPrimarySkill() {
		return primarySkill;
	}
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}
	
	@Column(name="SECONDARYSKILL",  length = 30)
	public String getSecondarySkill() {
		return secondarySkill;
	}
	public void setSecondarySkill(String secondarySkill) {
		this.secondarySkill = secondarySkill;
	}
	
	@Column(name="EXPERIENCE",  length = 20)
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	
	@Column(name="LOCATION",  length = 20)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Lob
	@Column(name="JOBDESCRIPTION")
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	@Column(name="RESOURCECOUNT",  length = 3)
	public int getResourceCount() {
		return resourceCount;
	}
	public void setResourceCount(int resourceCount) {
		this.resourceCount = resourceCount;
	}
	
	@Column(name="DEADLINE",  length = 20)
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Column(name="DATEOFISSUE",  length = 20)
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	
	

	@ManyToOne
    @JoinColumn(name="EMPLOYEEID")
	public EmployeeEntity getHiringManagerId() {
		return hiringManagerId;
	}
	public void setHiringManagerId(EmployeeEntity hiringManagerId) {
		this.hiringManagerId = hiringManagerId;
	}
		

	@ManyToOne
	@JoinColumn(name="COSTCENTERID")
	public CostCenterEntity getCostCenterId() {

		return costCenterId;
	}
	public void setCostCenterId(CostCenterEntity costCenterId) {
		this.costCenterId = costCenterId;
	}
	
	@OneToMany(mappedBy="requestId")
	public List<ShortlistCandidateEntity> getList() {
		return list;
	}
	public void setList(List<ShortlistCandidateEntity> list) {
		this.list = list;
	}

	
}


