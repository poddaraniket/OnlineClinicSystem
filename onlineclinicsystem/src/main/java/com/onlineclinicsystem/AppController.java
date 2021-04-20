package com.onlineclinicsystem;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	/*
	 * @Autowired private PatientService patientService;
	 */
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private CustomOAuth2UserService oAuthUserDetailsService;
	
	@Autowired
	private CustomerUserDetailsService userDetailsService;
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping("/")
	public String viewHomePage() {
		return "Home";
	}
	
	@RequestMapping("/doctorList")
	public String doctorsList(Model m) {
		List<Doctor> doctorList=doctorService.listAll();
		m.addAttribute("doctorList",doctorList);
		return "doctorList";
	}
	
	@RequestMapping("/patientLogin")
	public String login(Model m) {
	//	List<Doctor> doctorList=doctorService.listAll();
		//m.addAttribute("doctorList",doctorList);
		return "patientLogin";
	}
	
	@RequestMapping("/patientsignup")
	public String signup(Model m) {
		m.addAttribute("patient", new Patient());
		return "patient/Register";
	}
	
	@RequestMapping(value = "/patientsave", method = RequestMethod.POST)
	public String Register(@ModelAttribute("patient") Patient patient, Model model) {
		System.out.println(patient.getPatientId() + " " + patient.getEmail());
		patient.setRoles("ROLE_USER");
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPassword=encoder.encode(patient.getPassword());
		patient.setPassword(encodedPassword);
		patientService.save(patient);
		model.addAttribute("patient", patient);
		//System.out.println(patient.getPatientId());
		return "redirect:/";
	}
	
	@RequestMapping("/about")
	public String About() {
		return "About";
	}
	
	@RequestMapping("/logout")
	public String logout() {
	//	List<Doctor> doctorList=doctorService.listAll();
		//m.addAttribute("doctorList",doctorList);
		return "logout";
	}
	
	@RequestMapping("/patient")
	@ResponseBody
	public Principal patient(Principal principal) {
		return principal;
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
			//authenticating the user details token
			authenticationManager.authenticate(
					//standard token which Spring MVC uses
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		}
		//handling exception if authentication fails
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		//if you complete the above section means u have successfully authenticated and now u need to return the JWT
		//u have created a util that will take in user details and create a JWT
	    // get access to userdetails from userdetails service
		
		//here we finally get the user details
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		//final UserDetails userDetails1 = oAuthUserDetailsService.loadUser(authenticationRequest);
		

		//use JwtUtil to get jwt out of this userdetails
		//here u finally create the token
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		//create an authentication response instance and pass it back 
		//Spring MVC will respond a 200 ok response ad pay load of the response is going to be a new  AuthenticationResponse of jwt
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
