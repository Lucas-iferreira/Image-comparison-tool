package io.github.lucasiferreira.facecomparison.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.lucasiferreira.facecomparison"})
public class FacecomparisoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacecomparisoApplication.class, args);
    }

    @Bean
    public RekognitionClient rekognitionClient() {
        return RekognitionClient.create();
    }

}
