package com.ibs.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="G2SHORTLISTCANDIDATE")
public class ShortlistCandidateEntity {

	private int status;
	private String offer,remark,shortlistId;
	private RaiseRequestEntity requestId;
	private CandidateRegistrationEntity candidateId;
	private Date date;
	
	
	@Id
	@Column(name="SHORTLISTID")
	public String getShortlistId() {
		return shortlistId;
	}
	public void setShortlistId(String shortlistId) {
		this.shortlistId = shortlistId;
	}
	
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name="OFFER")
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@ManyToOne
	@JoinColumn(name="REQUESTID")
	public RaiseRequestEntity getRequestId() {
		return requestId;
	}
	public void setRequestId(RaiseRequestEntity requestId) {
		this.requestId = requestId;
	}
	
	@ManyToOne
	@JoinColumn(name="CANDIDATEID")
	public CandidateRegistrationEntity getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(CandidateRegistrationEntity candidateId) {
		this.candidateId = candidateId;
	}
	
	@Column(name="SHORTLISTDATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
