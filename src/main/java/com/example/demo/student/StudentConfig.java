package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student aziz = new Student(
                "Aziz Nabli",
                "aziz.nabli@gmail.com",
                LocalDate.of(2002,Month.JANUARY,24),
                20
            );

            Student younes = new Student(
                "Younes Chouikh",
                "younes.jmell10@gmail.com",
                LocalDate.of(2002,Month.JANUARY,24),
                20
            );
            repository.saveAll(List.of(aziz,younes));
        };
    }
    
}
