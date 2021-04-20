package com.onlineclinicsystem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientId;

	@Column(unique = true)
	private String email;

	private String firstName, lastName, gender, location, city, state, pincode, mobile, password;

	@Enumerated(EnumType.STRING)
	@Column(name = "auth_provider")
	private AuthenticationProvider authProvider;

	private String dateOfBirth;

	private String Roles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	private List<Appointments> appointments;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		this.Roles = roles;
	}

	public AuthenticationProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", location=" + location + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", mobile=" + mobile + ", password=" + password + ", dateOfBirth="
				+ dateOfBirth + ", Roles=" + Roles + "]";
	}

}
