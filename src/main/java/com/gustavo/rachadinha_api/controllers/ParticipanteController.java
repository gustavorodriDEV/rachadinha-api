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
    public ResponseEntity<ParticipanteDTO> adicionarParticipante(
            @PathVariable Long idGrupo,
            @Valid @RequestBody ParticipanteDTO participanteDTO) {

        Participante novoParticipante = participanteService.adicionarParticipante(idGrupo, participanteDTO);

        ParticipanteDTO responseDTO = new ParticipanteDTO(
                novoParticipante.getId(),
                novoParticipante.getNome(),
                novoParticipante.getEmail(),
                novoParticipante.getGrupo().getId()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarParticipante(@PathVariable Long id) {
        Participante participante = participanteService.buscarParticipante(id);
        return ResponseEntity.ok(participante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> atualizarParticipante(
            @PathVariable Long id,
            @Valid @RequestBody ParticipanteDTO participanteAtualizado) {

        Participante participante = participanteService.atualizarParticipante(id, participanteAtualizado);

        ParticipanteDTO responseDTO = new ParticipanteDTO(
                participante.getId(),
                participante.getNome(),
                participante.getEmail(),
                participante.getGrupo().getId()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParticipante(@PathVariable Long id) {
        participanteService.deletarParticipante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Participante>> listarParticipantesPorGrupo(@PathVariable Long idGrupo) {
        List<Participante> participantes = participanteService.listarParticipantesPorGrupo(idGrupo);
        return ResponseEntity.ok(participantes);
    }

}
