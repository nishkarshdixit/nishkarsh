package com.ibs.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="G2EMPLOYEEDESCRIPTOR")
public class EmployeeEntity {

	
	private String employeeId;
	private String employeeName,address,designation,location,phone,email,password;
	private Date dob,doj;
	private List<RaiseRequestEntity> list;
	private List<RecruiterToRequest> recList; 
	
	
	@Id
	@Column(name="EMPLOYEEID",  length = 6)
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	@Column(name="EMPLOYEENAME",  length = 40)
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@Column(name="EMPLOYEEADDRESS",  length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="EMPLOYEEDATEOFBIRTH",  length = 20)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Column(name="EMPLOYEEDATEOFJOINING",  length = 20)
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	@Column(name="EMPLOYEEDESIGNATION",  length = 30)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name="EMPLOYEELOCATION",  length = 30)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name="EMPLOYEEPHONENO",  length = 10)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="EMPLOYEEEMAILID",  length = 60, unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="EMPLOYEEPASSWORD", length=30)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(mappedBy="hiringManagerId")
	@Basic(fetch=FetchType.LAZY)
	public List<RaiseRequestEntity> getList() {
		return list;
	}
	public void setList(List<RaiseRequestEntity> list) {
		this.list = list;
	}
	
	@OneToMany(mappedBy="recruiterId")
	public List<RecruiterToRequest> getRecList() {
		return recList;
	}
	public void setRecList(List<RecruiterToRequest> recList) {
		this.recList = recList;
	}
	
	
	
}
