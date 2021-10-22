package ru.tigran.cardcollector;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class ServingWebContentApplication {
    
    public static void main(String[] args){
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
