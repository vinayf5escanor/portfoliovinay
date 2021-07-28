package ca.sheridancollege.shvinayk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.shvinayk.bean.Student;
import ca.sheridancollege.shvinayk.bean.UserRegistration;
import ca.sheridancollege.shvinayk.database.databaseAccess;

@Controller
public class MyController {
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private databaseAccess da;
	
	@GetMapping("/")
	public String Home() {
		return"index";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@GetMapping("/access-denied")
	public String denied() {
		return "/error/access-denied";
	}
	
	@GetMapping("/view")
	public String getIndex(Model model,Student s) {
		model.addAttribute("s", da.getStudent() );
		return "view";
	}
	
	@GetMapping("/add")
	public String add(Student s){
	return "add";
	}
	
	@PostMapping("/addValue")
	public String addValue(Model model, @ModelAttribute Student s) {
	da.addStudent(s);
	model.addAttribute("s", da.getStudent());
	return "view";
	}
	
	@GetMapping("/edit/{id}")
	public String editr(Model model, @PathVariable int id){
		Student s = da.getStudentById(id);
	model.addAttribute("s", s);
	return "edit";
	}
	
	
	@PostMapping("/modify")
	public String modifyStudent(Model model, @ModelAttribute Student s) {
	da.modifyStudent(s);
	model.addAttribute("s", da.getStudent());
	return "view";
	}
	
	@GetMapping("/delete/{id}")
	public String removeStudents(Model model, @PathVariable int id) {
	da.removerStudent(id);
	model.addAttribute("s", da.getStudent());
	return "view";
	}	
	
	@GetMapping("/register")
	public String register(Model model, UserRegistration user) {
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute UserRegistration user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		User newuser = new User(user.getUsername(), encodedPassword, authorities);
		jdbcUserDetailsManager.createUser(newuser);
		return "redirect:/";
	}
	
}
