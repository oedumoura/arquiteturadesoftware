package com.bancoaq4.api.controllers;

import com.bancoaq4.api.dto.ClienteDTO;
import com.bancoaq4.api.models.Cliente;
import com.bancoaq4.api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<ClienteDTO> obj = this.clienteService.findAll();
        return new ResponseEntity(obj, HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable long id){
        return new ResponseEntity(this.clienteService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody Cliente cliente){
        return new ResponseEntity(this.clienteService.create(cliente), HttpStatus.CREATED);
    }
}
