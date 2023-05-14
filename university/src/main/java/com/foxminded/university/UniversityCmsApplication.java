package com.foxminded.university;

import com.foxminded.university.Service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UniversityCmsApplication implements CommandLineRunner {

	private final EntityGenerator entityGenerator;

	public UniversityCmsApplication(EntityGenerator entityGenerator) {
		this.entityGenerator = entityGenerator;
	}

	public static void main(String[] args) {
		SpringApplication.run(UniversityCmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		entityGenerator.prepareTestData();

	}
}
