package com.alipour.learning.activemqcamel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivemqCamelApplication implements CommandLineRunner {

	@Autowired
	private ActivemqToConsul activemqToConsul;

	public static void main(String[] args) {
		SpringApplication.run(ActivemqCamelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		activemqToConsul.printQueueMessage();

	}
}
