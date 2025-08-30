package com.gustavo.rachadinha_api.controllers;

import com.gustavo.rachadinha_api.entities.Grupo;
import com.gustavo.rachadinha_api.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    @Autowired
    public GrupoController(GrupoService grupoService){
        this.grupoService = grupoService;
    }

    @PostMapping
    public ResponseEntity<Grupo> criarGrupo(@RequestBody Grupo grupo){
        Grupo grupoSalvo = grupoService.criarGrupo(grupo);
        return  new ResponseEntity<>(grupoSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> listarGrupo(){
        List<Grupo> grupos = grupoService.listarGrupos();
        return  ResponseEntity.ok(grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> buscarGrupo(@PathVariable Long id){
        Grupo grupo = grupoService.buscarPorId(id);
        return ResponseEntity.ok(grupo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> atualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupoAtualizado){
        Grupo grupo = grupoService.atualizarGrupo(id, grupoAtualizado);
        return ResponseEntity.ok(grupo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGrupo(@PathVariable Long id){
         grupoService.deletarGrupo(id);
         return ResponseEntity.noContent().build();
    }
}
