package com.bancoaq4.api.services;

import com.bancoaq4.api.dto.TransacaoDTO;
import com.bancoaq4.api.exceptions.ContaBloqueadaException;
import com.bancoaq4.api.exceptions.SaldoInsuficienteException;
import com.bancoaq4.api.models.ContaCorrente;
import com.bancoaq4.api.models.TipoTransacao;
import com.bancoaq4.api.models.Transacao;
import com.bancoaq4.api.repositories.ContaCorrenteRepository;
import com.bancoaq4.api.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public TransacaoDTO findById(long id){
        Transacao obj = this.transacaoRepository.findById(id).get();
        return toTransacaoDTO(obj);
    }

    public List<TransacaoDTO> findAll(){
        return this.transacaoRepository.findAll()
                .stream()
                .map(this::toTransacaoDTO)
                .collect(Collectors.toList());

    }

    public TransacaoDTO deposito(long idConta, double valor) throws ContaBloqueadaException {
        ContaCorrente contaCorrente = contaCorrenteRepository.findById(idConta).get();
        contaCorrente.somaSaldo(valor);
        contaCorrenteRepository.save(contaCorrente);

        Transacao transacao = new Transacao();
        transacao.setContaCorrente(contaCorrente);
        transacao.setValor(valor);
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setData(fmt.format(new Date()));

        contaCorrente.AddTransacao(transacao);
        transacaoRepository.save(transacao);

        return toTransacaoDTO(transacao);
    }

    public TransacaoDTO boleto(long idConta, double valor) throws ContaBloqueadaException, SaldoInsuficienteException {
        ContaCorrente contaCorrente = contaCorrenteRepository.findById(idConta).get();
        contaCorrente.subtraiSaldo(valor);
        contaCorrenteRepository.save(contaCorrente);

        Transacao transacao = new Transacao();
        transacao.setContaCorrente(contaCorrente);
        transacao.setValor(valor);
        transacao.setTipoTransacao(TipoTransacao.PAGAMENTO_BOLETO);
        transacao.setData(fmt.format(new Date()));

        contaCorrente.AddTransacao(transacao);
        transacaoRepository.save(transacao);

        return toTransacaoDTO(transacao);
    }

    public TransacaoDTO saque(long idConta, double valor) throws ContaBloqueadaException, SaldoInsuficienteException {
        ContaCorrente contaCorrente = contaCorrenteRepository.findById(idConta).get();
        contaCorrente.subtraiSaldo(valor);
        contaCorrenteRepository.save(contaCorrente);

        Transacao transacao = new Transacao();
        transacao.setContaCorrente(contaCorrente);
        transacao.setValor(valor);
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        transacao.setData(fmt.format(new Date()));

        contaCorrente.AddTransacao(transacao);
        transacaoRepository.save(transacao);

        return toTransacaoDTO(transacao);
    }

    public TransacaoDTO toTransacaoDTO(Transacao obj) {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setIdTransacao(obj.getId());
        transacaoDTO.setData(obj.getData());
        transacaoDTO.setIdConta(obj.getContaCorrente().getId());
        transacaoDTO.setNomeConta(obj.getContaCorrente().getCliente().getNome());
        transacaoDTO.setTipoTransacao(obj.getTipoTransacao());
        transacaoDTO.setValor(obj.getValor());
        return transacaoDTO;
    }
}
