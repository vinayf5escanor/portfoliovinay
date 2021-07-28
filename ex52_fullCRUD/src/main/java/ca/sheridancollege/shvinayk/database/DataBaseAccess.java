package ca.sheridancollege.shvinayk.database;
import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.shvinayk.beans.Appointment;

@Repository
public class DataBaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void addAppoitment(Appointment appointment) {
		String query="INSERT INTO appointment (firstName, email, appointmentDate, appointmentTime) VALUES (:myname, :e, :ad,:at)";

		MapSqlParameterSource namedParameters = 
						new MapSqlParameterSource();
		namedParameters.addValue("myname", appointment.getFirstName());
		namedParameters.addValue("e", appointment.getEmail());
		namedParameters.addValue("ad", appointment.getAppointmentDate());
		namedParameters.addValue("at", appointment.getAppointmentTime());
		
		jdbc.update(query, namedParameters);
	}
	
	 public void removeAppointment(int id) {
		 String query="delete from appointment where id=:id";
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 params.put("id", id);
		 jdbc.update(query, params);
	 }
	
	public ArrayList<Appointment> getAppointment() {
		String q = "SELECT id,firstName,email,appointmentDate,appointmentTime FROM APPOINTMENT ";
		ArrayList<Appointment> appointment =
				(ArrayList<Appointment>)jdbc.query(q,
				new BeanPropertyRowMapper<Appointment>(Appointment.class));
				return appointment;
	
	}
	
	 public Appointment getAppointmentById(int id) {
		 MapSqlParameterSource parameters = new MapSqlParameterSource();
		 String query = "SELECT * FROM appointment WHERE id=:id";
		 parameters.addValue("id", id);
		 ArrayList<Appointment> appointments =
		 (ArrayList<Appointment>)jdbc.query(query, parameters,
		 new BeanPropertyRowMapper<Appointment>(Appointment.class));
		 if (appointments.size()>0)
		 return appointments.get(0);
		 return null;
		 }
	 
	 public void modifyAppointment(Appointment a) {
		 String query="UPDATE appointment SET firstName=:f, email=:e, appointmentDate=:d, appointmentTime=:t WHERE id=:id";
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 params.put("f",a.getFirstName());
		 params.put("e",a.getEmail());
		 params.put("d",a.getAppointmentDate());
		 params.put("t",a.getAppointmentTime());
		 params.put("id",a.getId());
		 jdbc.update(query, params);
		 }
}
