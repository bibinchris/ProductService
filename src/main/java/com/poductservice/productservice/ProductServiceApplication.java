package com.poductservice.productservice;

import com.poductservice.productservice.inheritanceRelations.singleTable.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public ProductServiceApplication(@Qualifier("st_mentorrepository") MentorRepository mentorRepository,
                                     @Qualifier("st_studentrepository") StudentRepository studentRepository,
                                     @Qualifier("st_userrepository") UserRepository userRepository) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mentor mentor = new Mentor();
        mentor.setName("Bibin");
        mentor.setEmail("bibin0chris@gmail.com");
        mentor.setAvgRating(4.8);
        Mentor m = this.mentorRepository.save(mentor);
        System.out.println("m = " + m);

        Student student = new Student();
        student.setName("Abhi");
        student.setEmail("Abhi@xyz.com");
        student.setPsp(88);
        studentRepository.save(student);

        User user = new User();
        user.setName("Jaydu");
        user.setEmail("Jaydu@xyz.com");
        userRepository.save(user);

        List<User> userList = userRepository.findAll();

        userList.forEach(User-> System.out.println(user));
    }
}
