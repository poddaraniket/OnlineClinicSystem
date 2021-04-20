package com.onlineclinicsystem;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {

//	@Query("select a from Appointments a where a.doctor_id =:docId and a.patient_id =:patId")
//	public Appointments findByforeignIds(@Param("docId")long doctorId,@Param("patId")long patientId);
}
