package ca.sheridancollege.shvinayk.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

	//Used in our DatabaseAccess class to submit JDBC Query Strings
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	// NOTE
	// DO NOT ENABLE THE CODE BELOW IF WORKING WITH JUNIT TESTING
	
	//Creates our connection to our database.  In this case h2
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUrl("jdbc:h2:mem:testdb");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		return dataSource;
//	}
	
	//Load any default sql files when we compile the project.
//	@Bean
//	public DataSource loadSchema() {
//	    return new EmbeddedDatabaseBuilder()
//	      .setType(EmbeddedDatabaseType.H2)
//	      .addScript("classpath:schema.sql")
//	      //You can include additional .addScript() for multiple sql files.
//	      .build();
//	}
}