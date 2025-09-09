package com.gustavo.rachadinha_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ParticipanteDTO {

    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "o email não pode ser vazio")
    @Email(message = "O formato do email é inválido")
    private String email;

    private Long idGrupo;

    public ParticipanteDTO() {
    }

    public ParticipanteDTO(Long id, String nome, String email, Long idGrupo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idGrupo = idGrupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }
}
