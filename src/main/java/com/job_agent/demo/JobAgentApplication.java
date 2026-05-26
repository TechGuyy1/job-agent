package com.job_agent.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(
//		exclude = DataSourceAutoConfiguration.class
)
public class JobAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAgentApplication.class, args);
	}

}
