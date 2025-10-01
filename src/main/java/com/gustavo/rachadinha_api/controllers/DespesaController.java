package com.gustavo.rachadinha_api.controllers;

import com.gustavo.rachadinha_api.dtos.DespesaDTO;
import com.gustavo.rachadinha_api.entities.Despesa;
import com.gustavo.rachadinha_api.services.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grupos/{idGrupo}/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    @Autowired
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> criarDespesa(
            @PathVariable Long idGrupo,
            @Valid @RequestBody DespesaDTO despesaDTO) {

        Despesa novaDespesa = despesaService.criarDespesa(idGrupo, despesaDTO);
        return new ResponseEntity<>(new DespesaDTO(novaDespesa), HttpStatus.CREATED);
    }
}