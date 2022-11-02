package com.bancoaq4.api.models;

public enum TipoTransacao {

    SAQUE, DEPOSITO, PAGAMENTO_BOLETO, BOLETO, TRANSFERENCIA;

    public int id;

    TipoTransacao() {
        this.id = id;
    }
}
