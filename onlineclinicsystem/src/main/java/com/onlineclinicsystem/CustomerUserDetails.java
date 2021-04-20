package com.onlineclinicsystem;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerUserDetails implements UserDetails{

	private Patient patient;
	
	public CustomerUserDetails(Patient patient) {
		this.patient = patient;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> roles=null;
		roles=Arrays.asList(new SimpleGrantedAuthority(patient.getRoles()));
		//SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(user.getRole());
		return roles; 
		//return roles;
		// return null; 
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return patient.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return patient.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
