<!-- package it.aulab.xjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class XjavaDemoDocApplication {
    public static void main(String[] args){
        SpringApplication.run(XjavaDemoDocApplication.class, args);
    }
    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper instanceModelMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
 -->