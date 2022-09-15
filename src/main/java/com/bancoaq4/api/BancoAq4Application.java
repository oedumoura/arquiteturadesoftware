package com.bancoaq4.api;

import com.bancoaq4.model.ContaCorrente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BancoAq4Application {

    public static void main(String[] args) {
        SpringApplication.run(BancoAq4Application.class, args);
    }

    @GetMapping("/")
    public String index(){

        ContaCorrente Conta1 = new ContaCorrente();
        Conta1.depositar(1000);
        Conta1.depositar(1000);
        System.out.println(Conta1.extratoCompleto());
        return Conta1.extratoCompleto();
    }
}
