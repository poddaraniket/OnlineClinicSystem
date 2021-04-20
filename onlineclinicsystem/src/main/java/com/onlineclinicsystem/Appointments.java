package com.onlineclinicsystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long AptId;
	private String AptDate;
	private String AptTime;
	private String patientAge;
	private String visit;
	
	@ManyToOne
	@JoinColumn(name="patient_id",referencedColumnName="patientId",nullable=false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="doctor_id",referencedColumnName="doctorId",nullable=false)
	private Doctor doctor;

	public String getAptDate() {
		return AptDate;
	}

	public void setAptDate(String aptDate) {
		AptDate = aptDate;
	}

	public String getAptTime() {
		return AptTime;
	}

	public void setAptTime(String aptTime) {
		AptTime = aptTime;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public long getAptId() {
		return AptId;
	}

	public void setAptId(long aptId) {
		AptId = aptId;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	@Override
	public String toString() {
		return "Appointments [AptId=" + AptId + ", AptDate=" + AptDate + ", AptTime=" + AptTime + ", patientAge="
				+ patientAge + ", visit=" + visit + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
	
}

