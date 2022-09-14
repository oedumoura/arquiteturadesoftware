package com.bancoaq4.api;

import lombok.Data;

@Data
public class ContaCorrente {

    private int numeroAgencia;
    private int numeroConta;
    private double saldo;

    public void depositar(double valor){
        this.setSaldo(valor+saldo);
    }

    public void sacar(double valor){
        this.setSaldo(saldo-valor);
    }


}
