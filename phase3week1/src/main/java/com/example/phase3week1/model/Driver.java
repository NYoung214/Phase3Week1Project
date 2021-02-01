package com.example.phase3week1.model;

import java.util.Date;

import javax.persistence.*;


@Table(name="driver")
@Entity
public class Driver {

	@Id
	@Column(name="driver_id")
	private int driverId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date DOB;
	
	@Column(name="issue_date")
	@Temporal(TemporalType.DATE)
	private Date issueDate;
	
	@Column(name="expire_date")
	@Temporal(TemporalType.DATE)
	private Date expireDate;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip_code")
	private String zip;
	
	@Column(name="Sex")
	private String sex;

	public Driver() {
		super();
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", DOB=" + DOB + ", issueDate=" + issueDate + ", expireDate=" + expireDate
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", sex=" + sex
				+ "]";
	}
	
	
}
