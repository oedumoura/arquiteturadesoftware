package com.bancoaq4.api.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn( name = "idContaCorrente")
    private ContaCorrente contaCorrente;
    @ManyToOne
    @JoinColumn(name = "idContaCorrenteDestino", nullable = true)
    private ContaCorrente contaCorrenteDestino;
    private String data;
    private double valor;
    private TipoTransacao tipoTransacao;
}
