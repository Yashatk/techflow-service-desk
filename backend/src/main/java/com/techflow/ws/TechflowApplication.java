package com.techflow.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration(exclude = PropertyPlaceholderAutoConfiguration.class)
public class TechflowApplication implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {		
		SpringApplication.run(TechflowApplication.class, args);
	}

	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }    
}
