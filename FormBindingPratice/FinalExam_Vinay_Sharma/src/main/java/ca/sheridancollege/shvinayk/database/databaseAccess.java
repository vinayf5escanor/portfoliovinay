package ca.sheridancollege.shvinayk.database;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.shvinayk.bean.Student;

@Repository
public class databaseAccess {
	
	
		@Autowired
		protected NamedParameterJdbcTemplate jdbc;
		
		public void addStudent(Student s) {
			String query="INSERT INTO Students (name, course, mark) VALUES (:name, :c, :m)";

			MapSqlParameterSource namedParameters = 
							new MapSqlParameterSource();
			namedParameters.addValue("name", s.getName());
			namedParameters.addValue("c", s.getCourse());
			namedParameters.addValue("m", s.getMark());
			jdbc.update(query, namedParameters);
		}
		
		 public void removerStudent(int id) {
			 String query="delete from Students where id=:id";
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("id", id);
			 jdbc.update(query, params);
		 }
		
		public ArrayList<Student> getStudent() {
			String q = "SELECT * FROM Students ";
			ArrayList<Student> Studentg =
					(ArrayList<Student>)jdbc.query(q,
					new BeanPropertyRowMapper<Student>(Student.class));
					return Studentg;
		
		}
		
		 public Student getStudentById(int id) {
			 MapSqlParameterSource parameters = new MapSqlParameterSource();
			 String query = "SELECT * FROM Students WHERE id=:id";
			 parameters.addValue("id", id);
			 ArrayList<Student> Studentg =
			 (ArrayList<Student>)jdbc.query(query, parameters,
			 new BeanPropertyRowMapper<Student>(Student.class));
			 if (Studentg.size()>0)
			 return Studentg.get(0);
			 return null;
			 }
		 
		 public void modifyStudent(Student s) {
			 String query="UPDATE Students SET name=:name, course=:c, mark=:m WHERE id=:id";
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("name", s.getName());
			 params.put("c", s.getCourse());
			 params.put("m", s.getMark());
			 params.put("id",s.getId());
			 jdbc.update(query, params);
			 }


}
