package com.gustavo.rachadinha_api.controllers;

import com.gustavo.rachadinha_api.dtos.BalancoDTO;
import com.gustavo.rachadinha_api.services.BalancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos/{idGrupo}/balanco")
public class BalancoController {

    private final BalancoService balancoService;

    @Autowired
    public BalancoController(BalancoService balancoService) {
        this.balancoService = balancoService;
    }

    @GetMapping
    public ResponseEntity<List<BalancoDTO>> calcularBalanco(@PathVariable Long idGrupo) {
        List<BalancoDTO> balanco = balancoService.calcularBalanco(idGrupo);
        return ResponseEntity.ok(balanco);
    }
}