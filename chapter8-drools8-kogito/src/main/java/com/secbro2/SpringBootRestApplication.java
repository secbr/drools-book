package com.secbro2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sec
 * @version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"org.kie.kogito.**", "com.secbro2"})
public class SpringBootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}
