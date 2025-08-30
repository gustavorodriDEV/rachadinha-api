package com.gustavo.rachadinha_api.services;

import com.gustavo.rachadinha_api.entities.Grupo;
import com.gustavo.rachadinha_api.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    private GrupoRepository grupoRepository;

    @Autowired
    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Transactional
    public Grupo criarGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional(readOnly = true)
    public List<Grupo> listarGrupos() {
        return grupoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Grupo buscarPorId(Long id) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(id);
        return grupoOptional.orElseThrow(() -> new RuntimeException("Grupo não encontrado com o ID: " + id));
    }

    @Transactional
    public Grupo atualizarGrupo(Long id, Grupo grupoAtualizado){
        Grupo grupoExistente = buscarPorId(id);
        grupoExistente.setNome(grupoAtualizado.getNome());
        grupoExistente.setDescricao(grupoAtualizado.getDescricao());
        return grupoRepository.save(grupoExistente);
    }

    public void deletarGrupo(Long id){
        if (!grupoRepository.existsById(id)){
            throw new RuntimeException("Grupo não encontrado com o ID: " + id);
        }
        grupoRepository.deleteById(id);
    }
}
