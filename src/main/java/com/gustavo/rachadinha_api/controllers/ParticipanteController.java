package com.gustavo.rachadinha_api.controllers;

import com.gustavo.rachadinha_api.dtos.ParticipanteDTO;
import com.gustavo.rachadinha_api.entities.Participante;
import com.gustavo.rachadinha_api.services.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grupos/{idGrupo}/participantes")
public class ParticipanteController {

    private ParticipanteService participanteService;

    @Autowired
    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @PostMapping
    public ResponseEntity<Participante> adicionarParticipante(
            @PathVariable Long idGrupo,
            @Valid @RequestBody ParticipanteDTO participanteDTO) {

        Participante novoParticipante = participanteService.adicionarParticipante(idGrupo, participanteDTO);
        return new ResponseEntity<>(novoParticipante, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Participante>> listarParticipantesPorGrupo(@PathVariable Long idGrupo) {
        List<Participante> participantes = participanteService.listarParticipantesPorGrupo(idGrupo);
        return ResponseEntity.ok(participantes);
    }

}
