package com.example.Hari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class HariApplication {
    public static void main(String[] args) {


		SpringApplication.run(HariApplication.class, args);

// Get all beans
//        ApplicationContext beans = SpringApplication.run(HariApplication.class, args);
//        String[] allBeans = beans.getBeanDefinitionNames();
//        Arrays.stream(allBeans).sorted().forEach(System.out::println);

    }

}
