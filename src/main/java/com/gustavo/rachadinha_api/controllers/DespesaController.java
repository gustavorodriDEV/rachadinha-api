package com.gustavo.rachadinha_api.controllers;

import com.gustavo.rachadinha_api.dtos.DespesaDTO;
import com.gustavo.rachadinha_api.entities.Despesa;
import com.gustavo.rachadinha_api.services.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> listarDespesasPorGrupo(@PathVariable Long idGrupo) {
        List<Despesa> despesas = despesaService.listarDespesasPorGrupo(idGrupo);
        List<DespesaDTO> despesasDTO = despesas.stream()
                .map(DespesaDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(despesasDTO);
    }


    @GetMapping("/{idDespesa}")
    public ResponseEntity<DespesaDTO> buscarDespesaPorId(@PathVariable Long idDespesa) {
        Despesa despesa = despesaService.buscarDespesaPorId(idDespesa);
        return ResponseEntity.ok(new DespesaDTO(despesa));
    }

    @PutMapping("/{idDespesa}")
    public ResponseEntity<DespesaDTO> atualizarDespesa(
            @PathVariable Long idDespesa,
            @Valid @RequestBody DespesaDTO despesaDTO) {

        Despesa despesaAtualizada = despesaService.atualizarDespesa(idDespesa, despesaDTO);
        return ResponseEntity.ok(new DespesaDTO(despesaAtualizada));
    }

    @DeleteMapping("/{idDespesa}")
    public ResponseEntity<Void> deletarDespesa(@PathVariable Long idDespesa) {
        despesaService.deletarDespesa(idDespesa);
        return ResponseEntity.noContent().build();
    }
}