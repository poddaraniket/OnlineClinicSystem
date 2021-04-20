package com.onlineclinicsystem;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository aptRepo;
	
	public List<Appointments> findAll(){
		return aptRepo.findAll();
	}
	
//	public Appointments findByforeignIds(long doctorId,long patientId) {
//		return aptRepo.findByforeignIds(doctorId,patientId);
//	}
	
	public Appointments findById(long id) {
		return aptRepo.findById(id).get();
	}
	
	public void save(Appointments appointment) {
		aptRepo.save(appointment);
	}
	
	public void delete(Long userId) {
		aptRepo.deleteById(userId);
	}
	
}
