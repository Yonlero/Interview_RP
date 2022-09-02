package com.rp.interview_rp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.rp.interview_rp.config",
                "com.rp.interview_rp.controller",
                "com.rp.interview_rp.model.utils",
                "com.rp.interview_rp.model.services",
                "com.rp.interview_rp.model.repositories"
        }
)
public class InterviewRpApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterviewRpApplication.class, args);
    }

}
