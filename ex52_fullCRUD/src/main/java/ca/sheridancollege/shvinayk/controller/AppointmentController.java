package ca.sheridancollege.shvinayk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.shvinayk.beans.Appointment;
import ca.sheridancollege.shvinayk.database.DataBaseAccess;


@Controller
public class AppointmentController {
	@Autowired
	private DataBaseAccess da;
	
	@GetMapping("/")
	public String getIndex(Appointment appointment) {
		return "index";
	}
	
	@PostMapping("/")
	public String postIndex(@ModelAttribute Appointment appointment) {
		da.addAppoitment(appointment);
		return "index";
}
	@GetMapping("/view")
	public String viewStudents(Model model) {
	model.addAttribute("appointments", da.getAppointment());
	return "view";
	}
	
	@GetMapping("/edit/{id}")
	public String editLink(Model model, @PathVariable int id){
		Appointment a = da.getAppointmentById(id);
	model.addAttribute("appointment", a);
	return "edit";
	}
	
	@GetMapping("/modify")
	public String modifyStudent(Model model, @ModelAttribute Appointment appointment) {
	da.modifyAppointment(appointment);
	model.addAttribute("appointments", da.getAppointment());
	return "view";
	}
	
	@GetMapping("/delete/{id}")
	public String removeStudents(Model model, @PathVariable int id) {
	da.removeAppointment(id);
	model.addAttribute("appointments", da.getAppointment());
	return "view";
	}
}
