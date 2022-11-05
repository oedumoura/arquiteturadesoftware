package com.bancoaq4.api.dto;

import com.bancoaq4.api.models.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoDTO {

    private TipoTransacao tipoTransacao;
    private long idTransacao;
    private long idConta;
    private String nomeConta;
    private long idContaDestino;
    private String nomeContaDestino;
    private String data;
    private double valor;

}
