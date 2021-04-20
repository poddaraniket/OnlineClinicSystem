package com.onlineclinicsystem;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository docRepo;

	public List<Doctor> listAll() {
		return docRepo.findAll();
	}

	public void save(Doctor doctor) {
		docRepo.save(doctor);
	}

	public Doctor get(Long userId) {
		return docRepo.findById(userId).get();
	}

	public void delete(Long userId) {
		docRepo.deleteById(userId);
	}
}
