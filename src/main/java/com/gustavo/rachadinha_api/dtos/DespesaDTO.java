package com.gustavo.rachadinha_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaRequestDTO {

    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;

    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor deve ser positivo.")
    private BigDecimal valor;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    @NotNull(message = "O ID do pagador não pode ser nulo.")
    private Long idPagador;

    public DespesaRequestDTO() {
    }

    public DespesaRequestDTO(String descricao, BigDecimal valor, LocalDate data, Long idPagador) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.idPagador = idPagador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getIdPagador() {
        return idPagador;
    }

    public void setIdPagador(Long idPagador) {
        this.idPagador = idPagador;
    }
}