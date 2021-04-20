package com.onlineclinicsystem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long doctorId;

	@Column(unique = true)
	private String serviceNo;

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	private String firstName, lastName, gender, location, city, state, pincode, mobile, password, specs, qualification,
			email;

	private String dateOfBirth;

	private String Roles;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctor")
	private List<Appointments> appointments;
	
	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRoles() {
		return Roles;
	}

	public void setRoles(String roles) {
		Roles = roles;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", serviceNo=" + serviceNo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", location=" + location + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", mobile=" + mobile + ", password=" + password + ", specs=" + specs
				+ ", qualification=" + qualification + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", Roles="
				+ Roles + "]";
	}

	
	
}
