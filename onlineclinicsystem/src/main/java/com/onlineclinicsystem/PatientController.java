package com.onlineclinicsystem;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientRepository patRepo;
	
	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AppointmentService appointmentService;
	
	/*
	 * @RequestMapping("/dashboard/{userId}") public String
	 * Register(@PathVariable("userId")Long userId,Model m) { Patient
	 * patient=patientService.get(userId); //System.out.println(doctor.getEmail());
	 * //mav.addObject("patient", patient); m.addAttribute("patient", patient);
	 * return "patient/dashboard"; }
	 */
	
	@RequestMapping("/dashboard")
	public String Register(Model m,Principal p) {
		String email=p.getName();
		System.out.println(email+"register");
		Patient patient=patRepo.findByEmail(email);
		m.addAttribute("patient", patient);
		return "patient/dashboard";
	}

	@RequestMapping("/viewDetails")
	public String viewDetails(Model m,Principal p) {
		String email=p.getName();
		System.out.println(email+"view");
		Patient patient=patRepo.findByEmail(email);
		// ModelAndView mav = new ModelAndView("edit_patient");
//		Patient patient = patientService.get(userId);
//		System.out.println(patient.getEmail());
		// mav.addObject("patient", patient);
		m.addAttribute("patient", patient);
		return "patient/view_patient";
	}

	@RequestMapping("/editDetails")
	public ModelAndView editDetails(Model m,Principal p) {
		ModelAndView mav = new ModelAndView("patient/edit_patient");
		String email=p.getName();
		System.out.println(email);
		Patient patient=patRepo.findByEmail(email);
//		Patient patient = patientService.get(userId);
//		System.out.println(patient.getEmail());
		mav.addObject("patient", patient);
		// m.addAttribute("patient",patient);
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String Register(@ModelAttribute("patient") Patient patient, Model model) {
		System.out.println(patient.getPatientId() + " " + patient.getEmail());
		patientService.save(patient);
		model.addAttribute("patient", patient);
		//System.out.println(patient.getPatientId());
		return "redirect:/patient/dashboard";
	}

	@RequestMapping("/bookApointment")
	public String bookApointment(Model m,Principal p) {
		String email=p.getName();
		System.out.println(email);
		Patient patient=patRepo.findByEmail(email);
		List<Doctor> doctor = doctorService.listAll();
		System.out.println(patient);
		System.out.println(doctor);
		m.addAttribute("appointment", new Appointments());
		m.addAttribute("doctor", doctor);
		m.addAttribute("patient", patient);
		return "appointmentRegister";
		// return "patient/dashboard";
	}

	@RequestMapping(value = "/booked", method = RequestMethod.POST)
	public String BookingRegister(Appointments appointment,Model m) {

		Doctor doctorSearch = appointment.getDoctor();
		Patient patientSearch = appointment.getPatient();

		// appointment.setRoles("ROLE_USER");
		List<Appointments> apt = appointmentService.findAll();
		for (Appointments a : apt) {
			Doctor doctorExist = a.getDoctor();
			Patient patientExist = a.getPatient();

			if (doctorExist.getDoctorId() == doctorSearch.getDoctorId()
					&& patientExist.getPatientId() == patientSearch.getPatientId()) {
				return "errorPage";
			}
		}
		appointmentService.save(appointment);
		System.out.println("Job Done");
		m.addAttribute("patient",patientSearch);
		return "redirect:/patient/dashboard";

		// Appointments
		// apt=appointmentService.findByforeignIds(doctor.getDoctorId(),patient.getPatientId());
//		if(apt==null) {
//			appointmentService.save(appointment);
//		}
//		else {
//			System.out.println("Appointment there");
//		}
		// System.out.println(appointment);
		// model.addAttribute("patient",patient);
		// return "patient/dashboard";
	}

	@RequestMapping("/viewApointment")
	public String viewAppointment(Model m,Principal p) {
		String email=p.getName();
		System.out.println(email);
		Patient patient=patRepo.findByEmail(email);
		long userId=patient.getPatientId();
		//List<Patient> patientList=new ArrayList<>();
		List<Appointments> apt = appointmentService.findAll();
		List<Appointments> appointment=new ArrayList<>();
		for (Appointments a : apt) {
			Patient patientExist = a.getPatient();
			if (patientExist.getPatientId() == userId) {
				System.out.println(patientExist);
				m.addAttribute(patientExist);
				appointment.add(a);
			}
		}
		if(appointment!=null) {
			m.addAttribute("appointment",appointment);
//			for(Appointments a:appointment) {
//			System.out.println(appointment);
			 return "patient/viewAppointment";
		}
	    return "errorPage";
	}
	
	/*
	 * @RequestMapping("/deleteDetails/{userId}") public String
	 * deletePatient(@PathVariable(name = "userId") Long userId, Model m) {
	 * patientService.delete(userId); return "redirect:/"; }
	 */
	
	@RequestMapping("/deleteDetails")
	public String deletePatient(Principal p, Model m) {
		String email=p.getName();
		System.out.println(email);
		Patient patient=patRepo.findByEmail(email);
		patientService.delete(patient.getPatientId());
		return "redirect:/";
	}
	
	@RequestMapping("/deleteAppointment/{userId}")
	public String deleteAppointment(@PathVariable(name = "userId") Long userId, Model m) {
		Patient patient=(appointmentService.findById(userId)).getPatient();
		appointmentService.delete(userId);
		m.addAttribute("patient",patient);
		return "redirect:/patient/dashboard";
	}
	
	
//	@RequestMapping("/deleteAppointment")
//	public String deleteAppointment(Principal p, Model m) {
//		String email=p.getName();
//		System.out.println(email);
//		Patient pat=patRepo.findByEmail(email);
//		long userId=pat.getPatientId();
//		List<Appointments> appointment=appointmentService.findAll();
//		for(Appointments apt: appointment) {
//			if(apt.getPatient().getPatientId()==userId) {
//				appointmentService.delete(apt.getAptId());
//			}
//		}
//		//m.addAttribute("patient",patient);
//		return "patient/dashboard";
//	}
	
}
