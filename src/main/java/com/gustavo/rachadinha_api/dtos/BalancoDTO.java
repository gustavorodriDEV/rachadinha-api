package com.gustavo.rachadinha_api.dtos;

import java.math.BigDecimal;

public class BalancoDTO {

    private String devedor;
    private String credor;
    private BigDecimal valor;

    public BalancoDTO(String credor, String devedor, BigDecimal valor) {
        this.credor = credor;
        this.devedor = devedor;
        this.valor = valor;
    }

    public String getDevedor() {
        return devedor;
    }

    public void setDevedor(String devedor) {
        this.devedor = devedor;
    }

    public String getCredor() {
        return credor;
    }

    public void setCredor(String credor) {
        this.credor = credor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
