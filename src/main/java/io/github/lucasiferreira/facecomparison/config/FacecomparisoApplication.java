package io.github.lucasiferreira.facecomparison.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.lucasiferreira.facecomparison"})
public class FacecomparisoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacecomparisoApplication.class, args);
	}

}
