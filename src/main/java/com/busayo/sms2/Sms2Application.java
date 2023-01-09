package com.busayo.sms2;

import com.busayo.sms2.entity.Student;
import com.busayo.sms2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sms2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Sms2Application.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
//		Student student1 = new Student("Opeyebi", "Dada", "opeboy@yahoo.com");
//		studentRepository.save(student1);
//
//		Student student2 = new Student("Busayo", "Dada", "toyinoluwabusayo@gmail.com");
//		studentRepository.save(student2);
//
//		Student student3 = new Student("Oluwakemi", "Dada", "dadaoluwakemi97@gmail.com");
//		studentRepository.save(student3);

	}
}
