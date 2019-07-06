package com.alipour.learning.filecopier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileCopierApplication implements CommandLineRunner {
	@Autowired
	private FileCopier fileCopier;

	public static void main(String[] args) {
		SpringApplication.run(FileCopierApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileCopier.copyFile();
	}
}
