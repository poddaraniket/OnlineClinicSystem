package com.onlineclinicsystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping("/")
	public String entryPoint() {
		return "doctor/entry";
	}
	
	@RequestMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("doctor",new Doctor());
		return "doctor/Register";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String Register(@ModelAttribute("doctor")Doctor doctor,Model model) {
		System.out.println(doctor.getDoctorId()+" "+doctor.getEmail());
		doctor.setRoles("ROLE_DOCTOR");
		doctorService.save(doctor);
		model.addAttribute("doctor",doctor);
		return "doctor/dashboard";
	}
	
	@RequestMapping("/dashboard/{userId}")
	public String Register(@PathVariable("userId")Long userId,Model m) {
		Doctor doctor=doctorService.get(userId);
		//System.out.println(doctor.getEmail());
		//mav.addObject("patient", patient);
		m.addAttribute("doctor",doctor);
		return "doctor/dashboard";
	}
	
	@RequestMapping("/viewDetails/{userId}")
	public String viewDetails(@PathVariable("userId")Long userId,Model m) { 
		//ModelAndView mav = new ModelAndView("edit_patient");
		Doctor doctor=doctorService.get(userId);
		System.out.println(doctor.getEmail());
		//mav.addObject("patient", patient);
		m.addAttribute("doctor",doctor);
		return "doctor/view_doctor";
	  }
	
	@RequestMapping("/editDetails/{userId}")
	public ModelAndView editDetails(@PathVariable(name="userId")Long userId,Model m) { 
		ModelAndView mav = new ModelAndView("doctor/edit_doctor");
		Doctor doctor=doctorService.get(userId);
		System.out.println(doctor.getEmail());
		mav.addObject("doctor", doctor);
		//m.addAttribute("patient",patient);
		return mav;
	  }
	
	@RequestMapping("/viewApointment/{userId}")
	public String viewAppointment(@PathVariable(name = "userId") Long userId, Model m) {
		//List<Patient> patientList=new ArrayList<>();
		List<Appointments> apt = appointmentService.findAll();
		List<Appointments> appointment=new ArrayList<>();
		for (Appointments a : apt) {
			Doctor doctorExist = a.getDoctor();
			if (doctorExist.getDoctorId() == userId) {
				System.out.println(doctorExist);
				m.addAttribute(doctorExist);
				appointment.add(a);
			}
		}
		if(appointment!=null) {
			m.addAttribute("appointment",appointment);
//			for(Appointments a:appointment) {
//			System.out.println(appointment);
			 return "doctor/viewAppointment";
		}
	    return "errorPage";
	}
	
	@RequestMapping("/deleteDetails/{userId}")
	public String deleteDoctor(@PathVariable(name = "userId") Long userId, Model m) {
		doctorService.delete(userId);
		return "redirect:/";
	}

}
