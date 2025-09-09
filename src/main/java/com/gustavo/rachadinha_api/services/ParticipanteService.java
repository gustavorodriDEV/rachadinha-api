package com.gustavo.rachadinha_api.services;

import com.gustavo.rachadinha_api.dtos.ParticipanteDTO;
import com.gustavo.rachadinha_api.entities.Grupo;
import com.gustavo.rachadinha_api.entities.Participante;
import com.gustavo.rachadinha_api.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipanteService {

    private ParticipanteRepository participanteRepository;
    private GrupoService grupoService;

    @Autowired
    public ParticipanteService(ParticipanteRepository participamteRepository, GrupoService grupoService) {
        this.participanteRepository = participamteRepository;
        this.grupoService = grupoService;
    }

    @Transactional
    public Participante adicionarParticipante(Long idGrupo, ParticipanteDTO participanteDTO) {
        Grupo grupo = grupoService.buscarPorId(idGrupo);
        Participante novoParticipante = new Participante();
        novoParticipante.setNome(participanteDTO.getNome());
        novoParticipante.setEmail(participanteDTO.getEmail());
        novoParticipante.setGrupo(grupo);
        return participanteRepository.save(novoParticipante);
    }

    @Transactional(readOnly = true)
    public Participante buscarParticipante(Long idParticipante) {
        return participanteRepository.findById(idParticipante)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado com o ID: " + idParticipante));
    }

    @Transactional
    public Participante atualizarParticipante(Long idParticipante, ParticipanteDTO participanteDTO) {
        Participante participanteExistente = buscarParticipante(idParticipante);

        participanteExistente.setNome(participanteDTO.getNome());
        participanteExistente.setEmail(participanteDTO.getEmail());

        return participanteRepository.save(participanteExistente);
    }

    @Transactional
    public void deletarParticipante(Long idParticipante) {
        if (!participanteRepository.existsById(idParticipante)) {
            throw new RuntimeException("Esse participante não existe. ID: " + idParticipante);
        }
        participanteRepository.deleteById(idParticipante);
    }

    @Transactional(readOnly = true)
    public List<Participante> listarParticipantesPorGrupo(Long idGrupo) {
        grupoService.buscarPorId(idGrupo);
        return participanteRepository.findByGrupoId(idGrupo);
    }
}
