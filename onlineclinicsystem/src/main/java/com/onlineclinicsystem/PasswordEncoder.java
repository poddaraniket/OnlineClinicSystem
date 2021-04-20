package com.onlineclinicsystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
 
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String rawpass="aniket";
		String encoded=encoder.encode(rawpass);
		System.out.println(encoded);
	}
}
