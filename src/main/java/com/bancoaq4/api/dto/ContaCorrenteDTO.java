package com.bancoaq4.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContaCorrenteDTO {

    private long id;
    private String numeroConta;
    private String agencia;
    private long idCliente;
    private String nomeCliente;
    private double saldo;
    private boolean status;
    private List<TransacaoDTO> transcoes;
}
