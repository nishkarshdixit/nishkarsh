package com.ibs.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="G2CANDIDATEREGISTRATION")
public class CandidateRegistrationEntity {
	
	private int candidateId,status;
	private String title,firstName,middleName,lastName,currentLocation,preferredLocation,email,phoneNumber;
	private Date dob;
	private String cv;
	private int totalExperience,relevantExperience;
	List<ShortlistCandidateEntity> list;
	
	
	
	@Id
	@Column(name="CandidateId", length = 10 )
	public int getCandidateId() {
		return candidateId;
	}
	
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	
	@Column(name="CANDIDATETITLE",length = 10)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="CANDIDATEFIRSTNAME", length=40)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="CANDIDATEMIDDLENAME", length=40)
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Column(name="CANDIDATELASTNAME", length=40)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="CANDIDATECURRENTLOCATION", length=40)
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	@Column(name="CANDIDATEPREFERREDLOCATION", length=40)
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	@Column(name="CANDIDATEEMAIL", length=60, unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="CANDIDATEDOB",  length = 20)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Lob
	@Column(name="CANDIDATECV", nullable = false)
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	@Column(name="CANDIDATEPHONENUMBER",  length=10)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name="CANDIDATETOTALEXPERIENCE",  length = 10)
	public int getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}
	@Column(name="CANDIDATERELEVANTEXPERIENCE",  length = 10)
	public int getRelevantExperience() {
		return relevantExperience;
	}
	public void setRelevantExperience(int relevantExperience) {
		this.relevantExperience = relevantExperience;
	}
	@Column(name="CANDIDATESTATUS",length=15)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(mappedBy="candidateId")
	public List<ShortlistCandidateEntity> getList() {
		return list;
	}

	public void setList(List<ShortlistCandidateEntity> list) {
		this.list = list;
	}
	
	
}
