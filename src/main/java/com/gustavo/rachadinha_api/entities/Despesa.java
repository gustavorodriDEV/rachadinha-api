package com.gustavo.rachadinha_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    //Relacionamentos

    //Muitas despesas  pertencem a UM grupo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    //Muitas despesas podem ser pagas por UmParticipante (o pagador)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagador", nullable = false)
    private Participante pagador;

    public Despesa() {
    }

    public Despesa(Long id, String descricao, BigDecimal valor, LocalDate data, Grupo grupo, Participante pagador) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.grupo = grupo;
        this.pagador = pagador;
    }

    public LocalDate getData() {
        return data;
    }

    public Despesa setData(LocalDate data) {
        this.data = data;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Despesa setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Despesa setGrupo(Grupo grupo) {
        this.grupo = grupo;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Despesa setId(Long id) {
        this.id = id;
        return this;
    }

    public Participante getPagador() {
        return pagador;
    }

    public Despesa setPagador(Participante pagador) {
        this.pagador = pagador;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Despesa setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }
}

