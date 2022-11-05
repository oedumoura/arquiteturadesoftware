package com.bancoaq4.api.controllers;

import com.bancoaq4.api.dto.TransacaoDTO;
import com.bancoaq4.api.exceptions.ContaBloqueadaException;
import com.bancoaq4.api.exceptions.SaldoInsuficienteException;
import com.bancoaq4.api.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<TransacaoDTO> findById(@PathVariable long id){
        return new ResponseEntity(this.transacaoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> findAll(){
        return new ResponseEntity(this.transacaoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/deposito")
    public ResponseEntity<TransacaoDTO> deposito(@RequestBody TransacaoDTO transacaoDTO) throws ContaBloqueadaException {
        TransacaoDTO transacao = this.transacaoService.deposito(transacaoDTO.getIdConta(), transacaoDTO.getValor());
        return new ResponseEntity(transacao, HttpStatus.OK);
    }

    @PostMapping(path = "/saque")
    public ResponseEntity<TransacaoDTO> saque(@RequestBody TransacaoDTO transacaoDTO) throws ContaBloqueadaException, SaldoInsuficienteException {
        TransacaoDTO transacao = this.transacaoService.saque(transacaoDTO.getIdConta(), transacaoDTO.getValor());
        return new ResponseEntity(transacao, HttpStatus.OK);
    }
    @PostMapping(path = "/boleto")
    public ResponseEntity<TransacaoDTO> boleto(@RequestBody TransacaoDTO transacaoDTO) throws ContaBloqueadaException, SaldoInsuficienteException {
        TransacaoDTO transacao = this.transacaoService.boleto(transacaoDTO.getIdConta(), transacaoDTO.getValor());
        return new ResponseEntity(transacao, HttpStatus.OK);
    }

    @PostMapping(path = "/transferencia")
    public ResponseEntity<TransacaoDTO> transferencia(@RequestBody TransacaoDTO transacaoDTO) throws SaldoInsuficienteException, ContaBloqueadaException {
        TransacaoDTO transacao = this.transacaoService.tranferencia(
                        transacaoDTO.getIdConta(),
                        transacaoDTO.getIdContaDestino(),
                        transacaoDTO.getValor());
        return new ResponseEntity(transacao, HttpStatus.OK);
    }

}
