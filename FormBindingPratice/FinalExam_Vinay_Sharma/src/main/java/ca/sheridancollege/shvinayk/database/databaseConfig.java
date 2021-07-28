package ca.sheridancollege.shvinayk.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class databaseConfig {
	

		
		@Bean
		public NamedParameterJdbcTemplate namedParemterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
		}
		
		@Bean
		public DataSource loadSchema() {
			return new EmbeddedDatabaseBuilder()
					.setType(EmbeddedDatabaseType.H2)
					.addScript("classpath:schema.sql") // i know automatically but to be safe
					.build();
	}
		
	
}

