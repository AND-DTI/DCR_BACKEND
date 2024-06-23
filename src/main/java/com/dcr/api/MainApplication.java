package com.dcr.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import com.dcr.api.schedule.ScheduleController;
//import com.dcr.api.service.as400.ScheduleService;
//import jakarta.annotation.PostConstruct;



@SpringBootApplication
@EnableConfigurationProperties
public class MainApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
}
