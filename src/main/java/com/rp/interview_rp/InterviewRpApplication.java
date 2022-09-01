package com.rp.interview_rp;

import com.rp.interview_rp.model.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewRpApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ClientService service;

    public static void main(String[] args) {
        SpringApplication.run(InterviewRpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.testDB().forEach(x -> logger.info(x.toString()));
    }
}
