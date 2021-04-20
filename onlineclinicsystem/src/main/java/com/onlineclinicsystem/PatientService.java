package com.onlineclinicsystem;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.ModelAttribute;


@Service
public class PatientService{

	@Autowired
	private PatientRepository patRepo;
	
	/*
	 * public List<Product> listAll() { return repo.findAll(); }
	 */
	
	public void save(Patient patient) {
		patRepo.save(patient);
	}
	
	public Patient get(Long userId) {
		return patRepo.findById(userId).get();
	}
	
	public void delete(Long userId) {
		patRepo.deleteById(userId);
	}

	public void createNewCustomerAfterOAuthLoginSuccess(String email,String name,AuthenticationProvider provider) {
		Patient patient=new Patient();
		patient.setEmail(email);
		patient.setFirstName(name);
		patient.setAuthProvider(provider);
		patRepo.save(patient);
	}

	public void updateNewCustomerAfterOAuthLoginSuccess(Patient patient, String name, AuthenticationProvider provider) {

		patient.setFirstName(name);
		patient.setAuthProvider(provider);
		
		patRepo.save(patient);
	}
	
	/*public void delete(Long id) {
		repo.deleteById(id);
	}*/
	
}
