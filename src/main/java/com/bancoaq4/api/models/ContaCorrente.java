package com.bancoaq4.api.models;

import com.bancoaq4.api.exceptions.ContaBloqueadaException;
import com.bancoaq4.api.exceptions.SaldoInsuficienteException;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class ContaCorrente implements Serializable {

    @Id
    @GeneratedValue
    private long id;
//  @Column(unique = true)
    private String numeroConta;
    private String agencia;
    @OneToOne
    private Cliente cliente;
    private double saldo;
    private boolean status = true;
    @OneToMany
    private List<Transacao> transcoes;

    public void somaSaldo(double valor) throws ContaBloqueadaException {
        if(!status) {
            throw new ContaBloqueadaException();
        }

        this.saldo = saldo + valor;

    }
    public void pagarBoleto(double valor) throws ContaBloqueadaException, SaldoInsuficienteException {
        if(!status) {
            throw new ContaBloqueadaException();
        }else if(valor > saldo ) {

        throw new SaldoInsuficienteException();
    }

        this.saldo = saldo - valor;

    }

    public void subtraiSaldo(double valor) throws SaldoInsuficienteException, ContaBloqueadaException{
        if(!status) {
            throw new ContaBloqueadaException();
        }else if(valor > saldo ) {

            throw new SaldoInsuficienteException();
        }

        this.saldo = saldo - valor;
    }

    public void AddTransacao(Transacao transacao){
        transcoes.add(transacao);
    }


}
