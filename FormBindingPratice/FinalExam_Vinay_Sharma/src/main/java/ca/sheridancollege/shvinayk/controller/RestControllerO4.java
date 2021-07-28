package ca.sheridancollege.shvinayk.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.shvinayk.bean.Student;
import ca.sheridancollege.shvinayk.database.DatabaseAccessRest;

@RestController
@RequestMapping("/StudentRest")
public class RestControllerO4 {
	@Autowired
	DatabaseAccessRest da;

	@GetMapping
	public List<Student> getStudentCollection() {
//		if (da.findAll().size() == 0) {
//			for (int i = 1; i <= 5; i++) {
//				da.save(new Item("Item " + i));
//			}
//		}
		return da.findAll();
	}

	@GetMapping("/{id}")
	public Student getIndividualItem(@PathVariable Long id) {
		return da.findById(id).get(0);
	}

	@PostMapping(consumes = "application/json")
	public Long postStudent(@RequestBody Student s) {
		return da.save(s);
	}

	@PutMapping(consumes = "application/json")
	public String putStudentCollection(@RequestBody List<Student> StudentList) {
		da.deleteAll();
		da.saveAll(StudentList);
		return "Total Records: " + da.count();
	}

	@PutMapping(path = "/{id}", consumes = "application/json")
	public String putStudent(@PathVariable Long id, @RequestBody Student s) {
		return da.updateItem(id, s);

	}

	@DeleteMapping
	public String deleteStudentCollection() {
		da.deleteAll();
		return "Recoreds removed. Total Records: " + da.count();
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		da.deleteItem(id);
		return "Student removed.";
	}
}

