package ru.tigran.cardcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {
    
    public static void main(String[] args){
        for(String str : args) {
            String[] data = str.split("=");
            switch (data[0]){
                case "-debug":
                    Config.DEBUG = Boolean.parseBoolean(data[1]);
            }
        }
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
