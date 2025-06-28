package com.tracksphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tracksphere")
public class TrackSphereApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrackSphereApplication.class, args);
    }
}

