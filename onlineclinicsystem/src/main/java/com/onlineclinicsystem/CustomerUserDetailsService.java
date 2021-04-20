package com.onlineclinicsystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private PatientRepository patRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Patient patient=patRepo.findByEmail(email);
		
		if(patient==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		/*
		 * List<GrantedAuthority> grantedAuthorities=new ArrayList<>(); gr for(String
		 * role : patient.ge) { grantedAuthorities.add(new
		 * SimpleGrantedAuthority(role)); }
		 */
		return new CustomerUserDetails(patient);
	}

}
