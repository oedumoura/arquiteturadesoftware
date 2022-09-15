package com.bancoaq4.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private int numeroConta;
    private double saldo;
    private String extrato = "Extrato da Conta:";

    public void depositar(double valor){
        this.setSaldo(valor+saldo);
        String mensagem ="\nDepositou R$" + valor;
        this.extrato = extrato + mensagem;
    }

    public void sacar(double valor){ //quando for pagar o boleto fazer um if
        this.setSaldo(saldo-valor);
        String mensagem ="\nSacou R$" + valor;
        this.extrato = extrato + mensagem;
    }

    public String extratoCompleto(){
        return "\n" + this.extrato + "\nSaldo da Conta R$ " + this.getSaldo();
    }



}
