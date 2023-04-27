package ru.mirea.task22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.nio.file.Path;

@SpringBootApplication
public class Task22Application {

	public static void main(String[] args) {
//		Path path = Path.of("ru\\mirea\\task22\\resources");
//		FileWriter fileWriter = new FileWriter(path);
//
//		fileWriter.close();
		SpringApplication.run(Task22Application.class, args);
	}

}
