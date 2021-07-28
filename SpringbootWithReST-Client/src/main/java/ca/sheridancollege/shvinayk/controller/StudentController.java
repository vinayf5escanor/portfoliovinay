package ca.sheridancollege.shvinayk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.shvinayk.bean.Student;

@Controller
public class StudentController {
// this is the index page mapping. The index.html page contains a link to the page that shows
// all students in the collection
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

// this mapping is for the view-students.html page that shows the content of the
// students collection
	@GetMapping("/view-students")
	public String viewStudents(Model model, RestTemplate restTemplate) {
		ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity("http://localhost:9090/students",
				Student[].class);
		model.addAttribute("studentList", responseEntity.getBody());
		return "view-students";
	}

// this mapping is to support the JS function that shows the student details
// when the hyperlink representing the student is clicked
@GetMapping(value="/fetch-student/{id}", produces="application/json")
@ResponseBody
public Map<String, Object> fetchStudent(@PathVariable int id, RestTemplate restTemplate) {
Map<String, Object> data = new HashMap<String, Object>();
ResponseEntity<Student> responseEntity =
restTemplate.getForEntity("http://localhost:9090/students/" + id, Student.class);
data.put("student", responseEntity.getBody());

return data;
}

// this mapping is for the view-student.html page that shows the student details
	@GetMapping("/view-student/{id}")
	public String viewStudent(Model model, @PathVariable int id, RestTemplate restTemplate) {
		ResponseEntity<Student> responseEntity = restTemplate.getForEntity("http://localhost:9090/students/" + id,
				Student.class);
		model.addAttribute("student", responseEntity.getBody());
		return "view-student";
	}

// this mapping is to display the add-student.html form
	@GetMapping("/add-student")
	public String getAddStudent(Model model, Student student) {
		model.addAttribute("student", student);
		return "add-student";
	}

// this mapping is to process the result of the form submission and to add the new
// student to the collection
	@PostMapping("/add-student")
	public String postAddStudent(@ModelAttribute Student student, RestTemplate restTemplate, HttpSession sess) {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:9090/students", student,
				String.class);
		sess.setAttribute("msg", "student is added. new student id: " + responseEntity.getBody());
		return "redirect:/view-students";
	}

// this mapping is to delete a student. The id must be supplied to delete a student.
@GetMapping("/delete-student/{id}")

public String deleteStudent(@PathVariable int id, RestTemplate restTemplate, HttpSession sess)
{
restTemplate.delete("http://localhost:9090/students/" + id);
sess.setAttribute("msg", "student was deleted.");
return "redirect:/view-students";
}

// this mapping is to delete all students from the collection
	@GetMapping("/delete-all-students")
	public String deleteAllStudents(RestTemplate restTemplate, HttpSession sess) {
		restTemplate.delete("http://localhost:9090/students");
		sess.setAttribute("msg", "all students were deleted.");
		return "redirect:/view-students";
	}

// this mapping is to display the update-student.html form that provides new data for the student
	@GetMapping("/update-student/{id}")
	public String getUpdateStudent(@PathVariable int id, Model model, Student student) {
		student.setId(Long.valueOf(id));
		model.addAttribute("student", student);
		return "update-student";
	}

// this mapping is to process the result of the form submission and update the student with the
// received data
@PostMapping("/update-student")
public String putUpdateStudent(@ModelAttribute Student student, RestTemplate restTemplate,
HttpSession sess) {
restTemplate.put("http://localhost:9090/students/" + student.getId(), student);
sess.setAttribute("msg", "student was updated.");
return "redirect:/view-students";
}
}