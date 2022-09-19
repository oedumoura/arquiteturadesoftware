package com.bancoaq4.api.services;

import com.bancoaq4.api.dto.ClienteDTO;
import com.bancoaq4.api.models.Cliente;
import com.bancoaq4.api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    ContaCorrenteService contaCorrenteService = new ContaCorrenteService();

    public ClienteDTO findById(long id){
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return toClienteDTO(cliente.get());
    }

    public Cliente findClientById(long id){
        return this.clienteRepository.findById(id).get();
    }

    public List<ClienteDTO> findAll(){
        return this.clienteRepository.findAll()
                .stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO create(Cliente cliente){
        this.clienteRepository.save(cliente);
        return toClienteDTO(cliente);
    }

    public ClienteDTO toClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setDataDeNascimento(cliente.getDataDeNascimento());
        clienteDTO.setContasCorrente(cliente.getContaCorrentes() == null? null: cliente.getContaCorrentes()
                .stream()
                .map(contaCorrenteService::toContaCorrenteDTO)
                .collect(Collectors.toList()));
        return clienteDTO;
    }
}
