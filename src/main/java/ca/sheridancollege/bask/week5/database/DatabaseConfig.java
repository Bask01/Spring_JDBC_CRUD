package ca.sheridancollege.bask.week5.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Bean 
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSourse) {
		return new NamedParameterJdbcTemplate(dataSourse);
	}
}
