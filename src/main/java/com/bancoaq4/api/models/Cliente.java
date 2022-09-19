package com.bancoaq4.api.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    @OneToMany
    private List<ContaCorrente> contaCorrentes;

    public void addConta(ContaCorrente contaCorrente){
        contaCorrentes.add(contaCorrente);
    }
}
