package com.example.commandlinerunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandlinerunnerApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(CommandlinerunnerApplication.class);
		System.setProperty("main.ran", "true");
		application.run(args);
	}

}
