package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PostCommentApp {

	public static void main(String[] args) {
		try {
			//GenerateSQL.generateSQLFile();
		}
		catch (Exception io) {
			io.printStackTrace();
		}

		SpringApplication.run(PostCommentApp.class, args);
	}

}
