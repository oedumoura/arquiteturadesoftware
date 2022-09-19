package com.bancoaq4.api.controllers;

import com.bancoaq4.api.dto.ContaCorrenteDTO;
import com.bancoaq4.api.services.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/contas")
public class ContaCorrenteController {

    @Autowired
    ContaCorrenteService contaCorrenteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContaCorrenteDTO> findById(@PathVariable long id){
        return new ResponseEntity(this.contaCorrenteService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ContaCorrenteDTO>> findAll(){
        return new ResponseEntity(this.contaCorrenteService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContaCorrenteDTO> create(@RequestBody ContaCorrenteDTO contaCorrenteDTO){
        return new ResponseEntity(this.contaCorrenteService.create(contaCorrenteDTO), HttpStatus.CREATED);
    }
}
