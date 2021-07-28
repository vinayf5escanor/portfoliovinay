package ca.sheridancollege.shvinayk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import ca.sheridancollege.shvinayk.beans.Appointment;
import ca.sheridancollege.shvinayk.database.DataBaseAccess;

@SpringBootTest
@AutoConfigureMockMvc
public class testController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DataBaseAccess databaseAccess;

	@Test
	public void testAddDatabase() {
		Appointment appointment = new Appointment();
		int originalsize = databaseAccess.getAppointment().size();

		databaseAccess.addAppoitment(appointment);
		int foundsize = databaseAccess.getAppointment().size();

		assertThat(foundsize).isEqualTo(originalsize + 1);
	}

	@Test
	public void testdelete() throws Exception {
		int originalsize = databaseAccess.getAppointment().size();
		databaseAccess.removeAppointment(1);
		int foundsize = databaseAccess.getAppointment().size();
		assertThat(foundsize).isEqualTo(originalsize -1);
	}

	@Test
	public void testmodify() throws Exception {
		this.mockMvc.perform(get("/modify")).andExpect(view().name("view"));
	}

	@Test
	public void testview() throws Exception {
		this.mockMvc.perform(get("/view")).andExpect(view().name("view"));
	}

	@Test
	public void testForm() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(view().name("index"));
	}
}
