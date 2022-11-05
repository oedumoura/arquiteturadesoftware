package com.bancoaq4.api;

import com.bancoaq4.api.models.ContaCorrente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class BancoAq4Application {

    public static void main(String[] args) {

        SpringApplication.run(BancoAq4Application.class, args);
    }

}
