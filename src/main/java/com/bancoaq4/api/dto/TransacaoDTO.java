package com.bancoaq4.api.dto;

import com.bancoaq4.api.models.TipoTransacao;
import lombok.Data;

@Data
public class TransacaoDTO {

    private long id;
    private long idConta;
    private String nomeConta;
    private String data;
    private double valor;
    private TipoTransacao tipoTransacao;
}