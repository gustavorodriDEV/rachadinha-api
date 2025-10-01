package com.gustavo.rachadinha_api.dtos;

import com.gustavo.rachadinha_api.entities.Despesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaDTO {

    // Campos da Resposta
    private Long id;
    private Long idGrupo;
    private String nomePagador;

    // Campos da Requisição (e também da Resposta)
    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;

    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor deve ser positivo.")
    private BigDecimal valor;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    @NotNull(message = "O ID do pagador não pode ser nulo.")
    private Long idPagador;

    public DespesaDTO() {
    }

    public DespesaDTO(Despesa entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.data = entity.getData();
        this.idGrupo = entity.getGrupo().getId();
        this.idPagador = entity.getPagador().getId();
        this.nomePagador = entity.getPagador().getNome();
    }

    // Getters e Setters para TODOS os campos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomePagador() {
        return nomePagador;
    }

    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
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