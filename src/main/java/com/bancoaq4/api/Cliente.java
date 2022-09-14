package com.bancoaq4.api;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Cliente {

    private String cpf;
    private String nome;
    private ArrayList<ContaCorrente> contaCorrentes;



}
