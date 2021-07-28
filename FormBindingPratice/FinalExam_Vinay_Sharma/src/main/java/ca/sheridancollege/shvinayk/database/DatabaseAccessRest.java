package ca.sheridancollege.shvinayk.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.shvinayk.bean.Student;


@Repository
public class DatabaseAccessRest {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public List<Student> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Students";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
	}

	public List<Student> findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Students WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters,
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	public Long save(Student s) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO Students (name,course,mark) VALUES(:name,:course,:mark)";
		namedParameters.addValue("name", s.getName());
		namedParameters.addValue("course", s.getCourse());
		namedParameters.addValue("mark", s.getMark());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}

	public String updateItem(Long id, Student s) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE Students SET name=:name,course=:course,mark=:mark where id=:id";
		namedParameters.addValue("id", id);
		namedParameters.addValue("name", s.getName());
		namedParameters.addValue("course", s.getCourse());
		namedParameters.addValue("mark", s.getMark());
		jdbc.update(query, namedParameters);
		return "Item info updated";
	}

	public void deleteAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM Students";
		jdbc.update(query, namedParameters);
	}

	public void deleteItem(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM Students where id = :id";
		jdbc.update(query, namedParameters);
	}

	public Long count() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT count(*) FROM Students";
		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}

	public void saveAll(List<Student> StudentList) {
		for (Student s : StudentList) {
			save(s);
		}
	}
}


