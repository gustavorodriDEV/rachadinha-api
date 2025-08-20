package com.gustavo.rachadinha_api.controllers; // Pacote corrigido

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Define o prefixo "/api" para todos os endpoints nesta classe
public class TesteController { // Nome da classe corrigido e mais descritivo

    @GetMapping("/teste") // O endpoint será /api/teste
    public String teste(){
        return "Aplicação no ar! Teste OK em " + java.time.LocalDateTime.now();
    }
}