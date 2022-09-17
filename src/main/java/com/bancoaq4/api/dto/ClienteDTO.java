package com.bancoaq4.api.dto;

import com.bancoaq4.api.models.ContaCorrente;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    private List<ContaCorrente> contaCorrentes;
}
