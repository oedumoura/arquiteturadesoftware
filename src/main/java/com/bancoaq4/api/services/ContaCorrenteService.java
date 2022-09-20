package com.bancoaq4.api.services;

import com.bancoaq4.api.dto.ClienteDTO;
import com.bancoaq4.api.dto.ContaCorrenteDTO;
import com.bancoaq4.api.models.Cliente;
import com.bancoaq4.api.models.ContaCorrente;
import com.bancoaq4.api.repositories.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaCorrenteService {

    @Autowired
    ContaCorrenteRepository contaCorrenteRepository;
    @Autowired
    ClienteService clienteService;
    
    TransacaoService transacaoService = new TransacaoService();

    public ContaCorrenteDTO findById(long id){
        ContaCorrente contaCorrente = this.contaCorrenteRepository.findById(id).get();
        return toContaCorrenteDTO(contaCorrente);
    }

    public List<ContaCorrenteDTO> findAll(){
        return this.contaCorrenteRepository.findAll()
                .stream()
                .map(this::toContaCorrenteDTO)
                .collect(Collectors.toList());
    }

    public ContaCorrenteDTO create(ContaCorrenteDTO contaCorrenteDTO){
        Cliente cliente = clienteService.findClientById(contaCorrenteDTO.getIdCliente());

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setNumeroConta(contaCorrenteDTO.getNumeroConta());
        contaCorrente.setAgencia(contaCorrenteDTO.getAgencia());
        contaCorrente.setSaldo(contaCorrenteDTO.getSaldo());
        contaCorrente.setCliente(cliente);


        cliente.addConta(contaCorrente);

        this.contaCorrenteRepository.save(contaCorrente);
        return toContaCorrenteDTO(contaCorrente);
    }

    public ContaCorrenteDTO toContaCorrenteDTO(ContaCorrente contaCorrente) {
        ContaCorrenteDTO contaCorrenteDTO = new ContaCorrenteDTO();
        contaCorrenteDTO.setId(contaCorrente.getId());
        contaCorrenteDTO.setNumeroConta(contaCorrente.getNumeroConta());
        contaCorrenteDTO.setAgencia(contaCorrente.getAgencia());
        contaCorrenteDTO.setIdCliente(contaCorrente.getCliente().getId());
        contaCorrenteDTO.setNomeCliente(contaCorrente.getCliente().getNome());
        contaCorrenteDTO.setStatus(contaCorrente.isStatus());
        contaCorrenteDTO.setSaldo(contaCorrente.getSaldo());
        contaCorrenteDTO.setTranscoes(contaCorrente.getTranscoes() == null? null: contaCorrente.getTranscoes()
        .stream()
        .map(transacaoService::toTransacaoDTO)
        .collect(Collectors.toList()));
        return contaCorrenteDTO;
    }
}
