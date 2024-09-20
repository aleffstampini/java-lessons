package br.com.javalessons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class JavaLessonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLessonsApplication.class, args);
	}

}
