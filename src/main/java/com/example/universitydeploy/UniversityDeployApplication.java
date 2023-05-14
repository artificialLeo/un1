package com.example.universitydeploy;

import com.example.universitydeploy.Service.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityDeployApplication implements CommandLineRunner {

    private final EntityGenerator entityGenerator;

    public static void main(String[] args) {
        SpringApplication.run(UniversityDeployApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        entityGenerator.prepareTestData();

    }

}
