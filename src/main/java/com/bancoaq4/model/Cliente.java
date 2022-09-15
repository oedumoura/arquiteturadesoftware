package com.bancoaq4.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
public class Cliente {
    @Id
    @Column(unique = true)
    private Long id;
    private String cpf;
    private String nome;
    private ArrayList<ContaCorrente> contaCorrentes;



}
