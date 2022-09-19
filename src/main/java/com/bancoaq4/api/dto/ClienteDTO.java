package com.bancoaq4.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    @JsonIgnoreProperties("idCliente")
    private List<ContaCorrenteDTO> contasCorrente;

}
